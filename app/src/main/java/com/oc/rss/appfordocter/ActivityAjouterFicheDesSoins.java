package com.oc.rss.appfordocter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Examen;
import com.oc.rss.appfordocter.Model.FicheDeSoins;
import com.oc.rss.appfordocter.Model.Hospitalisation;
import com.oc.rss.appfordocter.Model.Patient;
import com.oc.rss.appfordocter.WebService.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 06/05/2017.
 */

public class ActivityAjouterFicheDesSoins  extends AppCompatActivity  {

    static Hospitalisation hospitalisation=null;
    private ProgressDialog progress;
   static public  FicheDeSoins ficheDeSoins=null;
    RestService restService ;

    public Context acc=this ;
    EditText Motif ;
    EditText HistoireDeLaMaladie;
    EditText RevueDeSysteme ;
    EditText ExamenCardiovasculaire ;
    EditText ExamenTeteEtCou ;
    EditText ExamenMembresInferieurs ;
    EditText AutresExamen ;
    EditText Decision ;

    // Examen physique
    EditText  Temperature ;
    EditText FrequenceCardiaque ;
    EditText TensionArterielle ;
    EditText FrequenceRespiratoire ;

    //Exmaen examenPleuroPulomnaire

    EditText Inspection ;
    EditText Palpation ;
    EditText Percussion ;
    EditText Auscultation ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_de_soins_formulaire);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        Motif=(EditText)findViewById(R.id.Motif);
        HistoireDeLaMaladie=(EditText)findViewById(R.id.HistoireMaladie);
        RevueDeSysteme=(EditText)findViewById(R.id.RevueSystme);
        ExamenCardiovasculaire=(EditText)findViewById(R.id.ExamenCardiovasculaire);
      //  ExamenTeteEtCou==(EditText)findViewById(R.id.E);
        ExamenMembresInferieurs=(EditText)findViewById(R.id.ExamenMembreInferieur);
        AutresExamen=(EditText)findViewById(R.id.AutreExamens);
        Decision=(EditText)findViewById(R.id.Decision);
        Temperature=(EditText)findViewById(R.id.Temperature);
        FrequenceCardiaque=(EditText)findViewById(R.id.frequenceCardiaque);
        TensionArterielle=(EditText)findViewById(R.id.frequenceartérielle);
        FrequenceRespiratoire=(EditText)findViewById(R.id.frequenceRespiratoire);
        Inspection=(EditText)findViewById(R.id.Inspection);
        Palpation=(EditText)findViewById(R.id.Palpation);
        Percussion=(EditText)findViewById(R.id.Percussion);
        Auscultation=(EditText)findViewById(R.id.Auscultation);
       // Activity activit =  getFragmentManager();
        Button Sauvegarder =(Button)findViewById(R.id.AjoutFiche);
          restService=new RestService();
     Sauvegarder.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {


             ficheDeSoins= new FicheDeSoins();


             ficheDeSoins.SetMotif(Motif.getText().toString());
             ficheDeSoins.SetHistoireDeLaMaladie(HistoireDeLaMaladie.getText().toString());
             ficheDeSoins.SetRevueDeSysteme(RevueDeSysteme.getText().toString());
             ficheDeSoins.SetExamenCardiovasculaire(ExamenCardiovasculaire.getText().toString());
             ficheDeSoins.SetExamenMembreInferieur(ExamenMembresInferieurs.getText().toString());
             ficheDeSoins.SetAutresExamen(AutresExamen.getText().toString());
             ficheDeSoins.SetDecision(Decision.getText().toString());
             ficheDeSoins.SetTemperature(Float.parseFloat(Temperature.getText().toString()));
             ficheDeSoins.SetFrequenceCardiaque(Integer.parseInt(FrequenceCardiaque.getText().toString()));
             ficheDeSoins.SetTensionArterielle(Integer.parseInt(TensionArterielle.getText().toString()));
             ficheDeSoins.SetFrequenceRespiratoire(Integer.parseInt(FrequenceRespiratoire.getText().toString()));
             ficheDeSoins.SetInspection(Inspection.getText().toString());
             ficheDeSoins.SetPalpation(Palpation.getText().toString());
             ficheDeSoins.SetPercussion(Percussion.getText().toString());
             ficheDeSoins.SetAuscultation(Auscultation.getText().toString());
             bushow(v);


         }
     });




        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }





    }

    public void bushow(View view) {
        android.app.FragmentManager fm= getFragmentManager();
        DialogAjoutHospitalisation popTime=new DialogAjoutHospitalisation();
        popTime.show(fm,"Show fragment");

    }


    public class TaskActivity extends AsyncTask<Void,Void,Void> {



        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress = ProgressDialog.show(acc,
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement),true);


        }


        @Override
        protected Void doInBackground(Void... Params) {


            restService.getService().addFicheDeSoins(ficheDeSoins, new Callback<FicheDeSoins>() {
                @Override
                public void success(FicheDeSoins ficheDeSoins, Response response) {
                     Toast.makeText(acc, "consultation ajouté.", Toast.LENGTH_LONG).show();

                }

                @Override
                public void failure(RetrofitError error) {
                     Toast.makeText(acc, error.getMessage().toString(), Toast.LENGTH_LONG).show();


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
