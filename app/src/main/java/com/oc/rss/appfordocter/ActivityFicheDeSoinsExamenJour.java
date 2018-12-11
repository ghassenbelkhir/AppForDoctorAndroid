package com.oc.rss.appfordocter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.EditText;

import com.oc.rss.appfordocter.Model.FicheDeSoins;
import com.oc.rss.appfordocter.Model.Hospitalisation;
import com.oc.rss.appfordocter.WebService.RestService;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Ghassen on 08/05/2017.
 */

public class ActivityFicheDeSoinsExamenJour extends AppCompatActivity implements Serializable {

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

    FicheDeSoins ficheDeSoins=null;
    Hospitalisation hospa=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_de_soins_examen_jour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        hospa=DetailFragmentHospitalisation.hospa;
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


        ficheDeSoins = DetailFragmentHospitalisation.fichedata;

        Motif.setText(ficheDeSoins.GetMotif());
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate();
       /* java.util.Date dte =Date.valueOf(hospa.GetDateSortie());
        simpleCalendarView.setMaxDate(Date.valueOf(hospa.GetDateSortie()).getTime());
        simpleCalendarView.setMaxDate(Date.valueOf(hospa.GetDateEntree()).getTime());*/


    }
}
