package com.oc.rss.appfordocter;
import android.support.v4.app.FragmentActivity;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.DossierMedical;
import com.oc.rss.appfordocter.Model.Medecin;
import com.oc.rss.appfordocter.Model.Patient;
import com.oc.rss.appfordocter.WebService.RestService;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 08/05/2017.
 */

public class Activity_Login extends AppCompatActivity {

    public Context acc=this ;
    RestService restService ;
   static public int _MedecinId;
    private String _MotDePasse;
    static public Medecin _medecin;



    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        acc=this;


        final EditText PersonneId =(EditText)findViewById(R.id.PersonneId) ;
        final EditText MotDePasse =(EditText)findViewById(R.id.MotDePAsse) ;
        final TextView erreur =(TextView) findViewById(R.id.txterreu);
        Button btn = (Button) findViewById(R.id.aller);
        Button btn2 =(Button) findViewById(R.id.nouveau);
        restService =new RestService();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(PersonneId.getText().toString().equals("")||(MotDePasse.getText().toString().equals("")))
                {
                    erreur.setVisibility(View.VISIBLE);

                }
                else {
                    erreur.setVisibility(View.INVISIBLE);


                    _MedecinId = Integer.parseInt(PersonneId.getText().toString());
                    _MotDePasse = MotDePasse.getText().toString();
                    //new TaskActivity().execute();
                    progress = ProgressDialog.show(acc,
                            getResources().getString(R.string.app_name),
                            getResources().getString(R.string.chargement),true);

                    restService.getService().getMedecinBypsswd(_MedecinId,_MotDePasse ,new Callback<Medecin>() {
                        @Override
                        public void success(Medecin medecin, Response response) {


                            if(medecin!=null) {
                                _medecin = medecin;
                                Context context = acc;
                                Intent intent = new Intent(acc, MainActivity.class);
                                // intent.putExtra("patient",5);
                                context.startActivity(intent);
                                 progress.dismiss();
                            }
                            else
                            {

                                AlertDialog.Builder builder = new AlertDialog.Builder(acc);


                                builder.setMessage("votre identifiant ou mot de passe est incorrecte")
                                        .setTitle("Erreur");
                                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                    }
                                });

                                AlertDialog dialog = builder.create();

                                dialog.show();
                                progress.dismiss();
                            }

                        }









                        @Override
                        public void failure(RetrofitError error) {


                            AlertDialog.Builder builder = new AlertDialog.Builder(acc);


                            builder.setMessage("probleme de connexion au serveur vérifiez votre connexion internet")
                                    .setTitle("Erreur");
                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {


                                }
                            });

                            AlertDialog dialog = builder.create();

                            dialog.show();
                            progress.dismiss();


                        }
                    });


                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bushow(v);
                FragmentManager fm= getFragmentManager();
                DialogNouveauMedecin popTime=new DialogNouveauMedecin();
                popTime.show(fm,"bkbvccn");




            }
        });


        // Intent intent = new Intent(context, ActivityDossierMedical.class);
        //  intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, 54);
    }

/*
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


            restService.getService().getMedecinBypsswd(_MedecinId,_MotDePasse ,new Callback<Medecin>() {
                @Override
                public void success(Medecin medecin, Response response) {


                    if(medecin!=null) {
                        _medecin = medecin;
                        Context context = acc;
                        Intent intent = new Intent(acc, MainActivity.class);
                        // intent.putExtra("patient",5);
                        context.startActivity(intent);
                       // progress.dismiss();
                    }
                    else
                    {

                        AlertDialog.Builder builder = new AlertDialog.Builder(acc);


                        builder.setMessage("votre ifentifiant ou mot de passe est inccorecte")
                                .setTitle("Erreur");
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                            }
                        });

                        AlertDialog dialog = builder.create();

                        dialog.show();

                    }

                }









                @Override
                public void failure(RetrofitError error) {


                    AlertDialog.Builder builder = new AlertDialog.Builder(acc);


                    builder.setMessage("probleme de connexion au serveur vérifiez votre connexion internet")
                            .setTitle("Erreur");
                    builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {


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




    }*/

}
