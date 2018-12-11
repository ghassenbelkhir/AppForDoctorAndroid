package com.oc.rss.appfordocter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Alerte;

import java.util.List;

/**
 * Created by Ghassen on 05/05/2017.
 */



public class MyAdapterAlerte extends RecyclerView.Adapter<MyAdapterAlerte.ViewHolder> {
        private List<Alerte> itemsData;
        Context context;
        public MyAdapterAlerte(List<Alerte> itemsData , Context context) {
            this.itemsData = itemsData;
            this.context=context;

        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapterAlerte.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyecleview_alerte,  parent, false);
            return new ViewHolder(view);

        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {

            // - get data from your itemsData at this position
            // - replace the contents of the view with that itemsData

            viewHolder.txtDdate.setText(itemsData.get(position).GetDate().substring(0,10));

            viewHolder.txtContenu.setText(itemsData.get(position).GetContenu());



            final int  myindex=position;
            viewHolder.buttondelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemsData.remove(myindex);
                    notifyItemRemoved(myindex);
                    notifyItemRangeRemoved(myindex,getItemCount());
                }
            });


          /*  viewHolder.buttonadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // itemsData.add(myindex,new ItemData("new item listed",R.drawable.android));
                    notifyDataSetChanged();
                }
            });
            // itemsData.remove(ind);
            // notifyItemRemoved(ind);
            // notifyItemRangeRemoved(ind, getItemCount());
            // itemsData.add(ind,new ItemData("hi",R.drawable.rec1));
            //notifyDataSetChanged();
*/

        }

        // inner class to hold a reference to each item of RecyclerView
        public static class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txtDdate;

            public TextView  txtContenu;
            public Button buttonadd;
            public Button buttondelete;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                txtDdate = (TextView) itemLayoutView.findViewById(R.id.alerteDate);

                txtContenu =(TextView) itemLayoutView.findViewById(R.id.alerteText);
              //  buttonadd = (Button) itemLayoutView.findViewById(R.id.buadd);
                buttondelete = (Button) itemLayoutView.findViewById(R.id.budelete);
            }
        }


        // Return the size of your itemsData (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return itemsData.size();
        }
    }

