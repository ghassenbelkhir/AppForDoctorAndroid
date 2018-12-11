package com.oc.rss.appfordocter;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Consultation;
import com.oc.rss.appfordocter.Model.FicheDeSoins;
import com.oc.rss.appfordocter.Model.Hospitalisation;
import com.oc.rss.appfordocter.WebService.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 08/05/2017.
 */

public class DialogAjoutHospitalisation extends DialogFragment {

    RestService restService ;
    EditText Etablisseement ;
    EditText DateEntree;
    EditText DateSortie;
    private ProgressDialog progress;
Hospitalisation _hospitalisation;
    Button Sauvegarder;
    Button Cancel;
    View form;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    form =inflater.inflate(R.layout.dialog_hospitalisation_ajout,container ,false);
    Etablisseement=(EditText) form.findViewById(R.id.HospitalisationEtablissement);
        DateEntree =(EditText) form.findViewById(R.id.DateDeEntree);
        DateSortie=(EditText) form.findViewById(R.id.DateDeSortie);

    Sauvegarder=(Button) form.findViewById(R.id.SouvgerderAjout);
    Cancel=(Button) form.findViewById(R.id.CancelAjout);


        Cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {



        }
    });

        Sauvegarder.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            restService= new RestService();
            Hospitalisation hospitalisation= new Hospitalisation();

            hospitalisation.SetEtablissement(Etablisseement.getText().toString());
            hospitalisation.SetDateEntree(DateEntree.getText().toString());
            hospitalisation.SetDateSortie(DateSortie.getText().toString());
            hospitalisation.SetDossierId(DetailFragmentPatientInformation.PatientId);
            hospitalisation.SetMedecinId(Activity_Login._MedecinId);
            ActivityAjouterFicheDesSoins.hospitalisation=hospitalisation;

            restService.getService().addHospitalisation(hospitalisation, new Callback<Hospitalisation>() {
                @Override
                public void success(Hospitalisation hospitalisation, Response response) {
                   // Toast.makeText(getContext(), "consultation ajouté.", Toast.LENGTH_LONG).show();
                    _hospitalisation=hospitalisation;
                  new TaskActivity().execute();
                }

                @Override
                public void failure(RetrofitError error) {
                   // Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();


                }
            });

        }
    });



        return form;
}



    public class TaskActivity extends AsyncTask<Void,Void,Void> {



        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress = ProgressDialog.show(getActivity(),
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement),true);


        }


        @Override
        protected Void doInBackground(Void... Params) {


            ActivityAjouterFicheDesSoins.ficheDeSoins.SetHospitalisationId(_hospitalisation.GetHospitalisationId());


            restService.getService().addFicheDeSoins(ActivityAjouterFicheDesSoins.ficheDeSoins, new Callback<FicheDeSoins>() {
                @Override
                public void success(FicheDeSoins ficheDeSoins, Response response) {
                    Toast.makeText(getActivity(), "consultation ajouté.", Toast.LENGTH_LONG).show();
                    dismiss();

                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("gg",ActivityAjouterFicheDesSoins.ficheDeSoins.GetMotif());
                    Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    dismiss();


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
