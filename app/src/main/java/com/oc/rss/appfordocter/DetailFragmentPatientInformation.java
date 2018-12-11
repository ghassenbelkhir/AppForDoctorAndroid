package com.oc.rss.appfordocter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.oc.rss.appfordocter.Model.Immunisation;
import com.oc.rss.appfordocter.Model.Patient;
import com.oc.rss.appfordocter.TableImmunisation.ImmunisationTableDataAdapter;
import com.oc.rss.appfordocter.TableImmunisation.ImmunisationTableView;
import com.oc.rss.appfordocter.WebService.RestService;

import java.io.Serializable;
import java.util.List;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 02/05/2017.
 */

public class DetailFragmentPatientInformation extends Fragment implements Serializable {

    TextView Nom;
    TextView Prenom;
    TextView DateNaissance;
    TextView Telephone;
    TextView Sexe;
    TextView Email;
    TextView Statut;
    ImageView photo;
    TextView Adresse;
    static int i = 0;
    static Patient _patient = null;
   static int PatientId;
    private ProgressDialog progress;
    RestService restService;

    public DetailFragmentPatientInformation() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {

            appBarLayout.setTitle("");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_patient_information, container, false);
        i++;

        restService = new RestService();
        Nom = (TextView) rootView.findViewById(R.id.Nom);
        Prenom = (TextView) rootView.findViewById(R.id.Prenom);
        DateNaissance = (TextView) rootView.findViewById(R.id.DateDeNaissance);
        Telephone = (TextView) rootView.findViewById(R.id.Telephone);
        Sexe = (TextView) rootView.findViewById(R.id.Sexe);
        Email = (TextView) rootView.findViewById(R.id.Email);
        photo = (ImageView) rootView.findViewById(R.id.photo);
        Adresse = (TextView) rootView.findViewById(R.id.Adresse);
        Statut= (TextView) rootView.findViewById(R.id.Statut) ;


        if (i == 1) {
            _patient = (Patient) getArguments().getSerializable(
                    "patient");
            PatientId=Integer.parseInt(String.valueOf(_patient.PersonneId));
            Nom.setText(_patient.Nom);
            Prenom.setText(_patient.Prenom);
            DateNaissance.setText(_patient.DNaissance.toString().substring(0,10));
            Telephone.setText(_patient.Tel);
            Sexe.setText(_patient.Sexe);
            Email.setText(_patient.Email);
            Adresse.setText(_patient.Adresse);
            Statut.setText(_patient.Statut);
            if(_patient.Photo!=null) {
                byte[] imageBytes = Base64.decode(_patient.Photo
                        , Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                photo.setImageBitmap(bitmap);
            }
        } else {
            new TaskActivity().execute();
        }


        return rootView;
    }

    public class TaskActivity extends AsyncTask<Void, Void, Void> {


        protected void onPreExecute() {
            progress = ProgressDialog.show(getContext(),
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement), true);

        }


        @Override
        protected Void doInBackground(Void... Params) {


            restService.getService().getPatientById(PatientId, new Callback<Patient>() {
                @Override
                public void success(Patient patient, Response response) {


                    Nom.setText(patient.Nom);
                    Prenom.setText(patient.Prenom);
                    DateNaissance.setText(patient.DNaissance.toString().substring(0,10));
                    Telephone.setText(patient.Tel);
                    Sexe.setText(patient.Sexe);
                    Email.setText(patient.Email);
                    Adresse.setText(patient.Adresse);
                    Statut.setText(_patient.Statut);
                   /* if(_patient.Photo!=null) {
                        byte[] imageBytes = Base64.decode(patient.Photo
                                , Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        photo.setImageBitmap(bitmap);
                    }*/
                }

                @Override
                public void failure(RetrofitError error) {
                    // Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();

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
