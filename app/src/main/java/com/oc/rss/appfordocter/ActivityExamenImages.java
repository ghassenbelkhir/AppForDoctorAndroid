package com.oc.rss.appfordocter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.ExamenImage;
import com.oc.rss.appfordocter.WebService.RestService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 05/05/2017.
 */

public class ActivityExamenImages extends AppCompatActivity {
    public static final String ARG_ITEM_ID = "item_id";
    private String mItem;
    RecyclerView recyclerView;


    private static List<ExamenImage> DataFactory = new ArrayList<ExamenImage>();
    RestService restService;
    private ProgressDialog progress;

    View  rootView;
    GridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen_images);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        restService=new RestService();
        new TaskActivity().execute();




        final FragmentActivity c = this;
        layoutManager = new GridLayoutManager(this,4);




    }

    public class TaskActivity extends AsyncTask<Void, Void, Void> {


        protected void onPreExecute() {
            progress = ProgressDialog.show(ActivityExamenImages.this,
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement), true);

        }


        @Override
        protected Void doInBackground(Void... Params) {


            restService.getService().getExamensImagesByIdDossier(2,new Callback<List<ExamenImage>>() {

                @Override
                public void success(List<ExamenImage> examens, Response response) {

                    Log.e("fd", "dfd");
                    DataFactory = examens;
                    RecyclerView recyclerView =(RecyclerView) findViewById(R.id.my_recycler_exmaen_images);

//DataFactory.add(new alerte("hhg","fgj"));
                    recyclerView.setLayoutManager(layoutManager);
                    // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    MyAdapterExamenImage mAdapter = new MyAdapterExamenImage(  DataFactory ,ActivityExamenImages.this);
                    // 4. set adapter
                    recyclerView.setAdapter(mAdapter);
                    // 5. set item animator to DefaultAnimator
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }


                @Override
                public void failure(RetrofitError error) {
                    Log.e("fd", "dfd");
                    Toast.makeText(ActivityExamenImages.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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
