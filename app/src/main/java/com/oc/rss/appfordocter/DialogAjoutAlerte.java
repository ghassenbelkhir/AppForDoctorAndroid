package com.oc.rss.appfordocter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Alerte;
import com.oc.rss.appfordocter.Model.Medicament;
import com.oc.rss.appfordocter.WebService.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ghassen on 08/05/2017.
 */

public class DialogAjoutAlerte extends DialogFragment {

    RestService restService ;
    TextView Contenu ;

    EditText date;
    Button Sauvegarder;
    Button Cancel;
    View form;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        form =inflater.inflate(R.layout.dialog_alerte_ajout,container ,false);

        Contenu =(TextView)form.findViewById(R.id.AlerteContenu);
        date=(EditText)form.findViewById(R.id.dateAjout);
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
                Alerte alerte = new Alerte();
                alerte.SetMedecinId(Activity_Login._MedecinId);
                alerte.SetDossierId(DetailFragmentPatientInformation.PatientId);
                alerte.SetContenu(Contenu.getText().toString());

                alerte.SetDate(date.getText().toString());

                restService.getService().addAlertes(alerte, new Callback<Alerte>() {
                    @Override
                    public void success(Alerte alerte1, Response response) {
                        Toast.makeText(getContext(), "Alerte ajouté.", Toast.LENGTH_LONG).show();
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
