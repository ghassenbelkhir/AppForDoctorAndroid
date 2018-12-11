package com.oc.rss.appfordocter;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Alerte;
import com.oc.rss.appfordocter.Model.Medecin;
import com.oc.rss.appfordocter.WebService.RestService;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Ghassen on 08/05/2017.
 */

public class DialogNouveauMedecin extends DialogFragment {


    RestService restService ;
    TextView identifiant ;
    TextView MotDePasse;

    DatePicker datePicker;
    Button Sauvegarder;
    Button Cancel;
    View form;
    Medecin med=null;
    int _MedecinId;;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        form =inflater.inflate(R.layout.dialog_nouveau_medecin,container ,false);

        identifiant =(TextView)form.findViewById(R.id.identifantMedecin);
        MotDePasse =(TextView)form.findViewById(R.id.MotDePAsse);

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
                _MedecinId = Integer.parseInt(identifiant.getText().toString());
                restService = new RestService();


                restService.getService().getMedecinById(_MedecinId, new Callback<Medecin>() {
                    @Override
                    public void success(Medecin medecin, Response response) {

                        med = medecin;
                        restService.getService().updateMedecinsById(_MedecinId, med, new Callback<Medecin>() {
                            @Override
                            public void success(Medecin medecin, Response response) {
                                Toast.makeText(getActivity(), "Vous pouvez connecter maintenant", Toast.LENGTH_LONG).show();


                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(getActivity(), "errr", Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        builder.setMessage("Votre Identifant ou votre mot de passe est incorrecte")
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

            }
        });


         return form;


            }



}

