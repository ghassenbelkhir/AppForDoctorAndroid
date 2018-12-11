package com.oc.rss.appfordocter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Consultation;
import com.oc.rss.appfordocter.TableConsultation.ConsultationTableDataAdapter;
import com.oc.rss.appfordocter.TableConsultation.ConsultationTableView;
import com.oc.rss.appfordocter.WebService.RestService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.codecrafters.tableview.listeners.OnScrollListener;
import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 04/05/2017.
 */

public class DetailFragmentConsultation extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private String mItem;
    RestService restService;
    View rootView;
    private ProgressDialog progress;
    private static List<Consultation> DataFactory = new ArrayList<Consultation>();
    private ConsultationTableView TableView;
    private Button Ajout;
    private final Set<OnScrollListener> onScrollListeners = new HashSet<>();

    public DetailFragmentConsultation() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getString(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem);
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_detail_consultation, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //
        }
        Ajout = (Button) rootView.findViewById(R.id.AjoutConsultation);
        Ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manger = getFragmentManager();
                DialogAjoutConsultaion dl1 = new DialogAjoutConsultaion();
                dl1.show(manger, null);
                refreshScreen();

            }
        });
        restService = new RestService();

        new TaskActivity().execute();


        return rootView;
    }


    private void refreshScreen() {

        if (TableView != null) {
            final ConsultationTableDataAdapter consultationTableDataAdapter = new ConsultationTableDataAdapter(getActivity(), DataFactory, TableView);
            TableView.setDataAdapter(consultationTableDataAdapter);
            TableView.setSwipeToRefreshEnabled(true);
            TableView.setScrollbarFadingEnabled(true);
            TableView.setVerticalScrollBarEnabled(true);
            Toast.makeText(getContext(), "Rien", Toast.LENGTH_LONG).show();

        }

    }


    public class TaskActivity extends AsyncTask<Void, Void, Void> {


        protected void onPreExecute() {
            progress = ProgressDialog.show(getContext(),
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement), true);

        }


        @Override
        protected Void doInBackground(Void... Params) {


            restService.getService().getConsultationsByIdDossier(DetailFragmentPatientInformation.PatientId,new Callback<List<Consultation>>() {

                @Override
                public void success(List<Consultation> consultation, Response response) {


                    DataFactory = consultation;
                    TableView = (ConsultationTableView) rootView.findViewById(R.id.consultationtableView);

                    if (TableView != null) {

                        final ConsultationTableDataAdapter consultationTableDataAdapter = new ConsultationTableDataAdapter(getActivity(), DataFactory, TableView);
                        TableView.setDataAdapter(consultationTableDataAdapter);

                        TableView.setVerticalScrollBarEnabled(true);

                        // carTableView.addDataClickListener(new immunisationClickListener());
                        //  carTableView.addDataLongClickListener(new CarLongClickListener());

                        TableView.setSwipeToRefreshEnabled(true);
                        TableView.setSwipeToRefreshListener(new SwipeToRefreshListener() {
                            @Override
                            public void onRefresh(final RefreshIndicator refreshIndicator) {
                                TableView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        refreshScreen();
                                        refreshIndicator.hide();
                                        //  Toast.makeText(MainActivity.this, "Added: " + randomCar, Toast.LENGTH_SHORT).show();
                                    }
                                }, 3000);
                            }
                        });
                    }


                }


                @Override
                public void failure(RetrofitError error) {
                    Log.e("fd", "dfd");
                    Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... aafficher) {

        }

        @Override
        protected void onPostExecute(Void result) {
            progress.dismiss();


        }
        private class InternalOnScrollListener implements AbsListView.OnScrollListener {

            @Override
            public void onScrollStateChanged(final AbsListView absListView, final int scrollStateValue) {
                final OnScrollListener.ScrollState scrollState = OnScrollListener.ScrollState.fromValue(scrollStateValue);

                for (final OnScrollListener onScrollListener : onScrollListeners) {
                    onScrollListener.onScrollStateChanged(null,scrollState);
                }
            }

            @Override
            public void onScroll(final AbsListView absListView, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {

                for (final OnScrollListener onScrollListener : onScrollListeners) {
                    onScrollListener.onScroll(null, firstVisibleItem, visibleItemCount, totalItemCount);
                }
            }
        }

    }

}
