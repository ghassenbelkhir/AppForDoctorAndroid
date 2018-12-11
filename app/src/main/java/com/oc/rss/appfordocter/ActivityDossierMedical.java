package com.oc.rss.appfordocter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Patient;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Created by Ghassen on 30/04/2017.
 */

public class ActivityDossierMedical extends AppCompatActivity {
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
     *
     *
     */
    private Patient patient;
   private boolean mTwoPane;
    TextView Nom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        patient= (Patient) getIntent().getSerializableExtra("patient");



        DetailFragmentPatientInformation fragment2 = new DetailFragmentPatientInformation();
        Bundle bundle = new Bundle();
        bundle.putSerializable("patient", patient);
        fragment2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.patient_information, fragment2)
                .commit();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Activity_Login.class);

                context.startActivity(intent);

            }
        });

        // Recycler View for items
        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        recyclerView.setFocusable(false);
        setupRecyclerView((RecyclerView) recyclerView);
        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;




        }
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
         if(item.getItemId()==R.id.file)
         {
             Context context =this;
             Intent intent = new Intent(context, ActivityAjouterFicheDesSoins.class);
           //  intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, 54);

             context.startActivity(intent);
         }
                return super.onOptionsItemSelected(item);

    }



    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter());


    }




    public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>
    {
        private final ArrayList<String> mValues;

        public SimpleItemRecyclerViewAdapter()
        {
            mValues = new ArrayList<String>();
            mValues.add("Alertes");
            mValues.add("Immunisations");
            mValues.add("Consultations");
            mValues.add("Medicaments");
            mValues.add("Antecedents");
            mValues.add("examens");
            mValues.add("Hospitalisation");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_list_composant, parent, false);
            return new ViewHolder(view);
        }



        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position));
          //  holder.mContentView.setText(mValues.get(position));

            String choix=holder.mItem;



            switch (choix) {
                case "Immunisations": { holder.icon.setImageResource(R.drawable.immunisation);
                }
                break;
                case "Medicaments": {holder.icon.setImageResource(R.drawable.medicament);
                }
                break;
                case "Consultations": {holder.icon.setImageResource(R.drawable.consultation);
                }
                break;

                case "Antecedents": { holder.icon.setImageResource(R.drawable.antecedent);
                }
                break;
                case "Alertes": {holder.icon.setImageResource(R.drawable.alerte);
                }
                break;

                case "examens": {  holder.icon.setImageResource(R.drawable.examens);
                }
                break;
                case "Hospitalisation": {  holder.icon.setImageResource(R.drawable.hospitalisation);
                }
                break;
            }








            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String choix=holder.mItem;
                    switch (choix) {
                        case "Immunisations": {
                            if (mTwoPane) {
                                Bundle arguments = new Bundle();
                                arguments.putString(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);
                                DetailFragmentImmunisation fragment = new DetailFragmentImmunisation();
                                fragment.setArguments(arguments);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, fragment)
                                        .commit();


                            } else {
                                Context context = v.getContext();
                                Intent intent = new Intent(context, ActivityItemDetail.class);
                                intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);

                                context.startActivity(intent);
                            }
                        }break;
                        case "Medicaments": {
                            if (mTwoPane) {
                                Bundle arguments = new Bundle();
                                arguments.putString(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);
                                DetailFragmentMedicament fragment = new DetailFragmentMedicament();
                                fragment.setArguments(arguments);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, fragment)
                                        .commit();


                            } else {
                                Context context = v.getContext();
                                Intent intent = new Intent(context, ActivityItemDetail.class);
                                intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);

                                context.startActivity(intent);
                            }
                        }break;
                        case "Consultations":
                        {
                            if (mTwoPane) {
                                Bundle arguments = new Bundle();
                                arguments.putString(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);
                                DetailFragmentConsultation fragment = new DetailFragmentConsultation();
                                fragment.setArguments(arguments);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, fragment)
                                        .commit();


                            } else {
                                Context context = v.getContext();
                                Intent intent = new Intent(context, ActivityItemDetail.class);
                                intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);

                                context.startActivity(intent);
                            }
                        }
                        break;

                        case "Antecedents":
                        {
                            if (mTwoPane) {
                                Bundle arguments = new Bundle();
                                arguments.putString(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);
                                DetailFragmentAntecedent fragment = new DetailFragmentAntecedent();
                                fragment.setArguments(arguments);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, fragment)
                                        .commit();


                            } else {
                                Context context = v.getContext();
                                Intent intent = new Intent(context, ActivityItemDetail.class);
                                intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);

                                context.startActivity(intent);
                            }
                        }

                        break;
                        case "Alertes" :
                        {
                            if (mTwoPane) {
                                Bundle arguments = new Bundle();
                                arguments.putString(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);
                                DetailFragmentAlerte fragment = new DetailFragmentAlerte();
                                fragment.setArguments(arguments);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, fragment)
                                        .commit();


                            } else {
                                Context context = v.getContext();
                                Intent intent = new Intent(context, ActivityItemDetail.class);
                                intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);

                                context.startActivity(intent);
                            }

                        }break;

                        case "examens":
                        {
                            if (mTwoPane) {
                                Bundle arguments = new Bundle();
                                arguments.putString(DetailFragmentExamen.ARG_ITEM_ID, holder.mItem);
                               DetailFragmentExamen fragment = new DetailFragmentExamen();
                                fragment.setArguments(arguments);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, fragment)
                                        .commit();


                            } else {
                                Context context = v.getContext();
                                Intent intent = new Intent(context, ActivityItemDetail.class);
                                intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, holder.mItem);

                                context.startActivity(intent);
                            }

                        }break;

                        case "Hospitalisation":
                        {
                            if (mTwoPane) {
                                Bundle arguments = new Bundle();
                                arguments.putString(DetailFragmentHospitalisation.ARG_ITEM_ID, holder.mItem);
                                DetailFragmentHospitalisation fragment = new DetailFragmentHospitalisation();
                                fragment.setArguments(arguments);
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.item_detail_container, fragment)
                                        .commit();


                            } else {
                                Context context = v.getContext();
                                Intent intent = new Intent(context, ActivityItemDetail.class);
                                intent.putExtra(DetailFragmentHospitalisation.ARG_ITEM_ID, holder.mItem);

                                context.startActivity(intent);
                            }

                        }break;

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }













        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final ImageView icon;
            public String mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                icon = (ImageView) view.findViewById(R.id.iconn);
            }


        }
    }


}
