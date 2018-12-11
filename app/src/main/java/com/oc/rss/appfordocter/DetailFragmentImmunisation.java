package com.oc.rss.appfordocter;

/**
 * Created by Ghassen on 01/05/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.Immunisation;
import com.oc.rss.appfordocter.TableImmunisation.ImmunisationTableDataAdapter;
import com.oc.rss.appfordocter.TableImmunisation.ImmunisationTableView;

import com.oc.rss.appfordocter.WebService.RestService;

import java.util.ArrayList;
import java.util.List;

import de.codecrafters.tableview.listeners.SwipeToRefreshListener;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static java.lang.Thread.sleep;


public class DetailFragmentImmunisation extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private String mItem;
    RestService  restService ;
    View rootView;
    private ProgressDialog progress;

    public DetailFragmentImmunisation() {

    }

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


private  static List<Immunisation> DataFactory =new ArrayList<Immunisation>();
     ImmunisationTableView carTableView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




       rootView = inflater.inflate(R.layout.fragment_immunisation_detail, container, false);

        /************************************************************************/








        /**************************************************************************************/
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText("helooo");
        }

      //  DataFactory.add(new immunisation(1,"ll","12-01-1995",4));
        restService = new RestService();

      new  ImmunisationTaskActivity().execute();











        return rootView;
    }


    private void refreshScreen()
    {

                if (carTableView != null) {
                    final ImmunisationTableDataAdapter immunisationTableDataAdapter = new ImmunisationTableDataAdapter(getActivity(), DataFactory, carTableView);
                    carTableView.setDataAdapter(immunisationTableDataAdapter);
                    carTableView.setSwipeToRefreshEnabled(true);

                    Toast.makeText(getContext(), "Rien", Toast.LENGTH_LONG).show();

                }

            }







    public class ImmunisationTaskActivity extends AsyncTask<Void,Void,Void> {



        protected void onPreExecute()
        {
            progress =ProgressDialog.show(getContext(),
                    getResources().getString(R.string.app_name),
                    getResources().getString(R.string.chargement),true);

        }


         @Override
        protected Void doInBackground(Void... Params) {



            restService.getService().getImmunisationsById(DetailFragmentPatientInformation.PatientId,new Callback<List<Immunisation>>() {

                @Override
                public void success(List<Immunisation> immunisation, Response response) {


                    DataFactory=immunisation;
                    carTableView = (ImmunisationTableView) rootView.findViewById(R.id.tableView);

                    if (carTableView != null) {

                        final ImmunisationTableDataAdapter immunisationTableDataAdapter = new ImmunisationTableDataAdapter(getActivity(), DataFactory, carTableView);
                        carTableView.setDataAdapter(immunisationTableDataAdapter);

                        // carTableView.addDataClickListener(new immunisationClickListener());
                        //  carTableView.addDataLongClickListener(new CarLongClickListener());

                        carTableView.setSwipeToRefreshEnabled(true);
                        carTableView.setSwipeToRefreshListener(new SwipeToRefreshListener() {
                            @Override
                            public void onRefresh(final RefreshIndicator refreshIndicator) {
                                carTableView.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        refreshScreen();
                                        refreshIndicator.hide();
                                        //  Toast.makeText(MainActivity.this, "Added: " + randomCar, Toast.LENGTH_SHORT).show();
                                    }
                                }, 3000);
                            }
                        });
                    }





                }



                @Override
                public void failure(RetrofitError error) {
                    Log.e("fd","dfd");
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
