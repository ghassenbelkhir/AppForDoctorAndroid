package com.oc.rss.appfordocter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Antecedents;
import com.oc.rss.appfordocter.Model.FicheDeSoins;
import com.oc.rss.appfordocter.Model.Hospitalisation;
import com.oc.rss.appfordocter.Model.Patient;
import com.oc.rss.appfordocter.TableAntecedents.AntecedentTableDataAdapter;
import com.oc.rss.appfordocter.TableAntecedents.AntecedentTableView;
import com.oc.rss.appfordocter.TableHospitalisation.HospitalisationTableDataAdapter;
import com.oc.rss.appfordocter.TableHospitalisation.HospitalisationTableView;
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
 * Created by Ghassen on 06/05/2017.
 */

public class DetailFragmentHospitalisation extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private String mItem;
    RestService restService;
    View rootView;
    private ProgressDialog progress;
    private static List<Hospitalisation> DataFactory = new ArrayList<Hospitalisation>();
    private HospitalisationTableView TableView;
    private Button Ajout;
    static  public FicheDeSoins fichedata;
    static  public Hospitalisation hospa =null;
    int hospitalisationID;

    public DetailFragmentHospitalisation() {

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

        rootView = inflater.inflate(R.layout.fragment_detail_hospitalisation, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //
        }
        Ajout = (Button) rootView.findViewById(R.id.AjoutHospitalisation);
        Ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refreshScreen();

            }
        });
        restService = new RestService();

        new TaskActivity().execute();


        return rootView;
    }


    private void refreshScreen() {

        if (TableView != null) {
            final HospitalisationTableDataAdapter hospitalisationTableDataAdapter = new HospitalisationTableDataAdapter(getActivity(), DataFactory, TableView);
            TableView.setDataAdapter(hospitalisationTableDataAdapter);
            TableView.setSwipeToRefreshEnabled(true);
            TableView.setScrollbarFadingEnabled(true);
            TableView.setVerticalScrollBarEnabled(true);
            TableView.addDataClickListener(new HospitalisationClickListener());
            Toast.makeText(getContext(), "Rien", Toast.LENGTH_LONG).show();

        }

    }

    private class HospitalisationClickListener implements TableDataClickListener<Hospitalisation> {
        @Override
        public void onDataClicked(int rowIndex, Hospitalisation clicked) {

            hospitalisationID = clicked.GetHospitalisationId();
            hospa=clicked;
            new FicheDeSoinsTaskActivity().execute();

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


            restService.getService().getHospitalisationsByIdDossier(DetailFragmentPatientInformation.PatientId, new Callback<List<Hospitalisation>>() {

                @Override
                public void success(List<Hospitalisation> hospitalisation, Response response) {


                    DataFactory = hospitalisation;
                    TableView = (HospitalisationTableView) rootView.findViewById(R.id.HospitalisationtableView);

                    if (TableView != null) {

                        final HospitalisationTableDataAdapter hospitalisationTableDataAdapter = new HospitalisationTableDataAdapter(getActivity(), DataFactory, TableView);
                        TableView.setDataAdapter(hospitalisationTableDataAdapter);

                        TableView.setVerticalScrollBarEnabled(true);


                        TableView.addDataClickListener(new HospitalisationClickListener());
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


    public class FicheDeSoinsTaskActivity extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(getActivity(),
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement), true);


        }


        @Override
        protected Void doInBackground(Void... Params) {


            restService.getService().getFicheDeSoinsById(hospitalisationID, new Callback<FicheDeSoins>() {
                @Override
                public void success(FicheDeSoins fiche, Response response) {

                    fichedata = fiche;


                    Context context = getContext();
                    Intent intent = new Intent(getContext(), ActivityFicheDeSoinsExamenJour.class);
                    intent.putExtra("fiche", fichedata);
                    context.startActivity(intent);
                }

                @Override
                public void failure(RetrofitError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage("ce numero n'existe pas ou il y a probleme de connexion au serveur vérifiez votre connexion internet")
                            .setTitle("Erreur");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();


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
