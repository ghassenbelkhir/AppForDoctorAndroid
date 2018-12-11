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
import android.widget.Button;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Medicament;
import com.oc.rss.appfordocter.TableMedicament.MedicamentTableDataAdapter;
import com.oc.rss.appfordocter.TableMedicament.MedicamentTableView;
import com.oc.rss.appfordocter.WebService.RestService;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 03/05/2017.
 */

public class DetailFragmentMedicament extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private String mItem;
    RestService restService;
    View rootView;
    private ProgressDialog progress;
    private static List<Medicament> DataFactory = new ArrayList<Medicament>();
    private MedicamentTableView TableView;
    private Button Ajout;


    public DetailFragmentMedicament() {

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

        rootView = inflater.inflate(R.layout.fragment_detail_medicament, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //
        }
        Ajout = (Button) rootView.findViewById(R.id.AjoutMedicament);
        Ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manger = getFragmentManager();
                DialogAjoutMedicament dl1 = new DialogAjoutMedicament();
                dl1.show(manger, null);
                refreshScreen();

            }
        });
        restService = new RestService();

        new DetailFragmentMedicament.TaskActivity().execute();


        return rootView;
    }


    private void refreshScreen() {

        if (TableView != null) {
            final MedicamentTableDataAdapter medicamentTableDataAdapter = new MedicamentTableDataAdapter(getActivity(), DataFactory, TableView);
            TableView.setDataAdapter(medicamentTableDataAdapter);
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


            restService.getService().getMedicamentsByIdDossier(DetailFragmentPatientInformation.PatientId,new Callback<List<Medicament>>() {

                @Override
                public void success(List<Medicament> medicaments, Response response) {


                    DataFactory = medicaments;
                    TableView = (MedicamentTableView) rootView.findViewById(R.id.MedicamenttableView);

                    if (TableView != null) {

                        final MedicamentTableDataAdapter medicamentTableDataAdapter = new MedicamentTableDataAdapter(getActivity(), DataFactory, TableView);
                        TableView.setDataAdapter(medicamentTableDataAdapter);

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


    }








}
