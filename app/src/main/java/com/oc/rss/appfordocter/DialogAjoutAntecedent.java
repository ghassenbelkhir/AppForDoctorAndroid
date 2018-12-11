package com.oc.rss.appfordocter;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Antecedents;
import com.oc.rss.appfordocter.Model.Consultation;
import com.oc.rss.appfordocter.WebService.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ghassen on 04/05/2017.
 */

public class DialogAjoutAntecedent extends DialogFragment {
    RestService restService ;
    TextView Diagnostic ;
    TextView Etat;
    DatePicker datePicker;
    Button Sauvegarder;
    Button Cancel;
    View form;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        form =inflater.inflate(R.layout.dialog_consultation_ajout,container ,false);
        Diagnostic=(TextView)form.findViewById(R.id.antecedentDagnostic);
        Etat =(TextView)form.findViewById(R.id.antecedentEtat);
        datePicker=(DatePicker)form.findViewById(R.id.dateAjout);
        Sauvegarder=(Button) form.findViewById(R.id.SouvgerderAjout);
        Cancel=(Button) form.findViewById(R.id.CancelAjout);
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Log.e("kl","sdfs");
            }
        });

        Sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restService= new RestService();
                Antecedents antecedents = new Antecedents();
                antecedents.SetDiagnostic(Diagnostic.getText().toString());
                antecedents.SetEtat(Etat.getText().toString());
                antecedents.SetDate(datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth());

                restService.getService().addAntecedents(antecedents, new Callback<Antecedents>() {
                    @Override
                    public void success(Antecedents antecedents, Response response) {
                        Toast.makeText(getContext(), "antecedent ajouté.", Toast.LENGTH_LONG).show();
                        dismiss();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        dismiss();

                    }
                });

            }
        });



        return form;
    }

}
