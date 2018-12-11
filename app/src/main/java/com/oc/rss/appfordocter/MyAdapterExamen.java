package com.oc.rss.appfordocter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oc.rss.appfordocter.Model.Examen;

import java.util.List;

/**
 * Created by Ghassen on 05/05/2017.
 */

public class MyAdapterExamen extends RecyclerView.Adapter<MyAdapterExamen.ViewHolder>  {

    private List<Examen> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyAdapterExamen(List<Examen> data, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyecleview_examen_, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.myTextView.setText(mData.get(position).GetDate().substring(0,10));
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myTextView;
        public ImageView imagefolder;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
            imagefolder=(ImageView)itemView.findViewById(R.id.folderExamen);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
         //   Toast.makeText(view.getContext(), myTextView.getText(), Toast.LENGTH_LONG).show();

            Context context = view.getContext();

            Intent intent = new Intent(context, ActivityExamenImages.class);
            intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, 54);

            context.startActivity(intent);
        }
    }

    // convenience method for getting data at click position
    public Examen getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
