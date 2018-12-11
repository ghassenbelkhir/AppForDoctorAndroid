package com.oc.rss.appfordocter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import com.oc.rss.appfordocter.Model.Medicament;
import com.oc.rss.appfordocter.WebService.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ghassen on 03/05/2017.
 */

public class DialogAjoutMedicament extends DialogFragment {
    RestService restService ;
    TextView MedicamentNom ;
    TextView Ordonnance;
    DatePicker datePicker;
    Button Sauvegarder;
    Button Cancel;
    View form;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        form =inflater.inflate(R.layout.dialog_medicament_ajout,container ,false);
        MedicamentNom=(TextView)form.findViewById(R.id.MedicamentNom);
        Ordonnance =(TextView)form.findViewById(R.id.Ordonnance);
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
                Medicament medicament = new Medicament();
                medicament.SetMedecinId(Activity_Login._MedecinId);
                medicament.SetDossierId(DetailFragmentPatientInformation.PatientId);
                medicament.SetNomMedicament(MedicamentNom.getText().toString());
                medicament.SetOrdonnance(MedicamentNom.getText().toString());
                medicament.SetDate(datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth());

                restService.getService().addMedicamment(medicament, new Callback<Medicament>() {
                    @Override
                    public void success(Medicament medicament, Response response) {
                        Toast.makeText(getContext(), "New Student Inserted.", Toast.LENGTH_LONG).show();
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
