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
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Examen;
import com.oc.rss.appfordocter.Model.Immunisation;
import com.oc.rss.appfordocter.Model.Patient;
import com.oc.rss.appfordocter.TableImmunisation.ImmunisationTableDataAdapter;
import com.oc.rss.appfordocter.TableImmunisation.ImmunisationTableView;
import com.oc.rss.appfordocter.TableRecherchePatient.RechercheTableDataAdapter;
import com.oc.rss.appfordocter.TableRecherchePatient.RechercheTableView;
import com.oc.rss.appfordocter.WebService.RestService;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.PATCH;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 08/05/2017.
 */

public class DetailFragmentRecherchePatient extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private String mItem;
    RestService  restService ;
    View rootView;
    private ProgressDialog progress;
    public int DossierId;

    public Patient patientdata= null;

    public DetailFragmentRecherchePatient() {

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
    private class RechercheClickListener implements TableDataClickListener<Patient> {
        @Override
        public void onDataClicked(int rowIndex, Patient clicked) {

            DossierId= clicked.GetPersonneId();
            new PatientTaskActivity().execute();


        }
    }

    private  static List<Patient> DataFactory =new ArrayList<Patient>();
    RechercheTableView TableView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_detail_chercher_patient, container, false);

        restService = new RestService();

        new TaskActivity().execute();


        return rootView;
    }


    private void refreshScreen()
    {

        if (TableView != null) {
            final RechercheTableDataAdapter rechercheTableDataAdapter = new RechercheTableDataAdapter(getActivity(), DataFactory, TableView);
            TableView.setDataAdapter(rechercheTableDataAdapter);
            TableView.setSwipeToRefreshEnabled(true);
            TableView.addDataClickListener(new RechercheClickListener());
            Toast.makeText(getContext(), "Rien", Toast.LENGTH_LONG).show();

        }

    }







    public class TaskActivity extends AsyncTask<Void,Void,Void> {



        protected void onPreExecute()
        {
            progress =ProgressDialog.show(getContext(),
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement),true);

        }


        @Override
        protected Void doInBackground(Void... Params) {



            restService.getService().getPatientByNom(MainActivity.Nom,new Callback<List<Patient>>() {

                @Override
                public void success(List<Patient> patients, Response response) {


                    DataFactory=patients;
                    TableView = (RechercheTableView) rootView.findViewById(R.id.tableView);

                    if (TableView != null) {

                        final RechercheTableDataAdapter rechercheTableDataAdapter = new RechercheTableDataAdapter(getActivity(), DataFactory, TableView);
                        TableView.setDataAdapter(rechercheTableDataAdapter);


                        // carTableView.addDataClickListener(new immunisationClickListener());
                        //  carTableView.addDataLongClickListener(new CarLongClickListener());

                        TableView.setSwipeToRefreshEnabled(true);
                        TableView.addDataClickListener(new RechercheClickListener());
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
                    Log.e("fd","dfd");
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage("probleme de connexion au serveur vérifiez votre connexion internet")
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


    public class PatientTaskActivity extends AsyncTask<Void,Void,Void>  {



        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress = ProgressDialog.show(getContext(),
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement),true);


        }


        @Override
        protected Void doInBackground(Void... Params) {


            restService.getService().getPatientById(DossierId, new Callback<Patient>() {
                @Override
                public void success(Patient patient, Response response) {

                    patientdata=patient;



                    DetailFragmentPatientInformation.i=0;
                    Context context =getContext();
                    Intent intent = new Intent(getContext(), ActivityDossierMedical.class);
                    intent.putExtra("patient",patientdata);
                    context.startActivity(intent);
                }

                @Override
                public void failure(RetrofitError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage("probleme de connexion au serveur vérifiez votre connexion internet")
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
