package com.oc.rss.appfordocter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.DossierMedical;
import com.oc.rss.appfordocter.Model.Immunisation;
import com.oc.rss.appfordocter.Model.Medecin;
import com.oc.rss.appfordocter.Model.Patient;
import com.oc.rss.appfordocter.TableImmunisation.ImmunisationTableDataAdapter;
import com.oc.rss.appfordocter.TableImmunisation.ImmunisationTableView;
import com.oc.rss.appfordocter.WebService.RestService;

import java.io.Serializable;
import java.util.List;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

 public Context acc=this ;
    RestService restService ;
    public int DossierId;
    public DossierMedical dossierMedical;
    public Patient patientdata;
    static  public String Nom;
    public Medecin med =null;
    CalendarView cl;
    TextView mission;

    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acc=this;
            TextView NomMedecin =(TextView)findViewById(R.id.MedecinNom);
        TextView Specilaite =(TextView)findViewById(R.id.MedecinSpecialite);
        CircleImageView  photo =(CircleImageView)findViewById(R.id.profile_image);
        /* if(med.Photo.) {
    byte[] imageBytes = Base64.decode(med.Photo
            , Base64.DEFAULT);
    Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    photo.setImageBitmap(bitmap);
             }*/
        med=Activity_Login._medecin;
        NomMedecin.setText("Dr "+med.Prenom+" "+med.Nom);
        Specilaite.setText(med.Specialite);

        final EditText dossierId =(EditText)findViewById(R.id.recherche) ;
        final EditText nom =(EditText)findViewById(R.id.rechercheParNom) ;
        Button btn = (Button) findViewById(R.id.Chercher);
        Button btn2=(Button) findViewById(R.id.ChercherParNom);
        final TextView erreur1 =(TextView) findViewById(R.id.txterreu1);
        final TextView erreur2 =(TextView) findViewById(R.id.txterreu2);

         cl = (CalendarView)findViewById(R.id.calendarView1);

        mission =(TextView)findViewById(R.id.LabelMission);
        mission.setVisibility(View.VISIBLE);
        cl.setVisibility(View.VISIBLE);
         restService =new RestService();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // DossierId= Integer.parseInt(dossierId.va);


                if(dossierId.getText().toString().equals(""))
                {

                    erreur1.setVisibility(View.VISIBLE);

                }
                else {
                    erreur1.setVisibility(View.INVISIBLE);
                    erreur2.setVisibility(View.INVISIBLE);
                    DossierId=Integer.parseInt(dossierId.getText().toString());
                    new TaskActivity().execute();
                /*Context context =acc;
                Intent intent = new Intent(acc, ActivityDossierMedical.class);
                context.startActivity(intent);*/
                }


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nom.getText().toString().equals(""))
                {


                    erreur2.setVisibility(View.VISIBLE);

                }
                else {
                    mission.setVisibility(View.INVISIBLE);
                    cl.setVisibility(View.INVISIBLE);
                    erreur1.setVisibility(View.INVISIBLE);
                    erreur2.setVisibility(View.INVISIBLE);
                    Nom=nom.getText().toString();
                    Bundle arguments = new Bundle();
                    // arguments.putString(DetailFragmentRecherchePatient.ARG_ITEM_ID, holder.mItem);
                    DetailFragmentRecherchePatient fragment = new DetailFragmentRecherchePatient();
                    fragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.list_patient, fragment)
                            .commit();
                }
            }
        });


       // Intent intent = new Intent(context, ActivityDossierMedical.class);
        //  intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, 54);
    }


    public class TaskActivity extends AsyncTask<Void,Void,Void>  {



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


            restService.getService().getPatientById(DossierId, new Callback<Patient>() {
                @Override
                public void success(Patient patient, Response response) {

                    patientdata=patient;



                     DetailFragmentPatientInformation.i=0;
                    Context context =acc;
                    Intent intent = new Intent(acc, ActivityDossierMedical.class);
                    intent.putExtra("patient",patientdata);
                    context.startActivity(intent);
                }

                @Override
                public void failure(RetrofitError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(acc);

                    builder.setMessage("ce numero n'existe pas ou il y a probleme de connexion au serveur vérifiez votre connexion internet")
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
