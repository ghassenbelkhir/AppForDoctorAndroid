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

import com.oc.rss.appfordocter.Model.Consultation;
import com.oc.rss.appfordocter.Model.Medicament;
import com.oc.rss.appfordocter.WebService.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ghassen on 04/05/2017.
 */

public class DialogAjoutConsultaion extends DialogFragment {
    RestService restService ;
    TextView Etablisseement ;
    TextView Raison;
    DatePicker datePicker;
    Button Sauvegarder;
    Button Cancel;
    View form;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        form =inflater.inflate(R.layout.dialog_consultation_ajout,container ,false);
        Etablisseement=(TextView)form.findViewById(R.id.ConsultationEtablissement);
        Raison =(TextView)form.findViewById(R.id.ConsultationRaison);
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

                Consultation consultation = new Consultation();
                consultation.SetDossierId(DetailFragmentPatientInformation.PatientId);
                consultation.SetMedecinId(Activity_Login._MedecinId);
                consultation.SetEtablissemnt(Etablisseement.getText().toString());
                consultation.SetRaison(Raison.getText().toString());
                consultation.SetDate(datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth());

                restService.getService().addConsultation(consultation, new Callback<Consultation>() {
                    @Override
                    public void success(Consultation consultation, Response response) {
                        Toast.makeText(getContext(), "consultation ajouté.", Toast.LENGTH_LONG).show();
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
