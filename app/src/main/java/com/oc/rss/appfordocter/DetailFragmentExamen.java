package com.oc.rss.appfordocter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Examen;
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

public class DetailFragmentExamen extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private String mItem;
    RecyclerView recyclerView;


    private static List<Examen> DataFactory = new ArrayList<Examen>();
    RestService restService;
    private ProgressDialog progress;

    View  rootView;
    GridLayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getString(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem);
            }
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restService=new RestService();
        new TaskActivity().execute();




        final FragmentActivity c = getActivity();
       layoutManager = new GridLayoutManager(getActivity(),4);

       rootView = inflater.inflate(R.layout.fragment_detail_examen, container, false);
      /*  RecyclerView recyclerView =(RecyclerView) rootView.findViewById(R.id.my_recycler_view_exmaen);

//DataFactory.add(new alerte("hhg","fgj"));
        recyclerView.setLayoutManager(layoutManager);
        // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MyAdapterExamen mAdapter = new MyAdapterExamen(  DataFactory ,getContext());
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
*/


        return rootView;
    }



    public class TaskActivity extends AsyncTask<Void, Void, Void> {


        protected void onPreExecute() {
            progress = ProgressDialog.show(getContext(),
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement), true);

        }


        @Override
        protected Void doInBackground(Void... Params) {


            restService.getService().getExamensByIdDossier(DetailFragmentPatientInformation.PatientId,new Callback<List<Examen>>() {

                @Override
                public void success(List<Examen> examens, Response response) {

                    Log.e("fd", "dfd");
                    DataFactory = examens;
                    RecyclerView recyclerView =(RecyclerView) rootView.findViewById(R.id.my_recycler_view_exmaen);

//DataFactory.add(new alerte("hhg","fgj"));
                    recyclerView.setLayoutManager(layoutManager);
                    // recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    MyAdapterExamen mAdapter = new MyAdapterExamen(  DataFactory ,getContext());
                    // 4. set adapter
                    recyclerView.setAdapter(mAdapter);
                    // 5. set item animator to DefaultAnimator
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }


                @Override
                public void failure(RetrofitError error) {
                    Log.e("fd", "dfd");
                    Toast.makeText(getContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
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
