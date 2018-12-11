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

import com.oc.rss.appfordocter.Model.Antecedents;
import com.oc.rss.appfordocter.Model.DossierMedical;
import com.oc.rss.appfordocter.Model.Immunisation;
import com.oc.rss.appfordocter.Model.Patient;
import com.oc.rss.appfordocter.TableAntecedents.AntecedentTableDataAdapter;
import com.oc.rss.appfordocter.TableAntecedents.AntecedentTableView;
import com.oc.rss.appfordocter.WebService.RestService;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 04/05/2017.
 */

public class DetailFragmentAntecedent extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private String mItem;
    RestService restService;
    View rootView;
    private ProgressDialog progress;
    private static List<Antecedents> DataFactory = new ArrayList<Antecedents>();
    private AntecedentTableView TableView;
    private Button Ajout;



    public DetailFragmentAntecedent() {

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

        rootView = inflater.inflate(R.layout.fragment_detail_antecedent, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //
        }
        Ajout = (Button) rootView.findViewById(R.id.AjoutAntecedent);
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

        new TaskActivity().execute();


        return rootView;
    }


    private void refreshScreen() {

        if (TableView != null) {
            final AntecedentTableDataAdapter antecedentTableDataAdapter = new AntecedentTableDataAdapter(getActivity(), DataFactory, TableView);
            TableView.setDataAdapter(antecedentTableDataAdapter);
            TableView.setSwipeToRefreshEnabled(true);
            TableView.setScrollbarFadingEnabled(true);
            TableView.setVerticalScrollBarEnabled(true);
            TableView.addDataClickListener(new ImmunisationClickListener());
            Toast.makeText(getContext(), "Rien", Toast.LENGTH_LONG).show();

        }

    }

    private class ImmunisationClickListener implements TableDataClickListener<Antecedents> {
        @Override
        public void onDataClicked(int rowIndex, Antecedents clicked) {

            Toast.makeText(getContext(), clicked.GetDiagnostic(), Toast.LENGTH_SHORT).show();
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


            restService.getService().getAntecedentsByIdDossier(DetailFragmentPatientInformation.PatientId,new Callback<List<Antecedents>>() {

                @Override
                public void success(List<Antecedents> antecedents, Response response) {


                    DataFactory = antecedents;
                    TableView = (AntecedentTableView) rootView.findViewById(R.id.antecedentstableView);

                    if (TableView != null) {

                        final AntecedentTableDataAdapter antecedentTableDataAdapter = new AntecedentTableDataAdapter(getActivity(), DataFactory, TableView);
                        TableView.setDataAdapter(antecedentTableDataAdapter);

                        TableView.setVerticalScrollBarEnabled(true);


                        TableView.addDataClickListener(new ImmunisationClickListener());
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
