package com.oc.rss.appfordocter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oc.rss.appfordocter.Model.ExamenImage;

import java.util.List;

/**
 * Created by Ghassen on 05/05/2017.
 */

public class MyAdapterExamenImage extends RecyclerView.Adapter<MyAdapterExamenImage.ViewHolder>{


    private List<ExamenImage> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyAdapterExamenImage(List<ExamenImage> data, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public MyAdapterExamenImage.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_examen_images_recyeclerview, parent, false);
        MyAdapterExamenImage.ViewHolder viewHolder = new MyAdapterExamenImage.ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(MyAdapterExamenImage.ViewHolder viewHolder, int position) {
        viewHolder.myTextView.setText(String.valueOf(mData.get(position).GetExamenImageId()));
        byte[] imageBytes = Base64.decode(mData.get(position).GetImage()
                , Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        viewHolder.imageExamen.setImageBitmap(bitmap);

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myTextView;
        public ImageView imageExamen;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.image_id);
            imageExamen=(ImageView)itemView.findViewById(R.id.imageExamen);
            imageExamen.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Toast.makeText(view.getContext(), myTextView.getText(), Toast.LENGTH_LONG).show();
            imageExamen.setVisibility(View.VISIBLE);
          /*  Context context = view.getContext();
            Intent intent = new Intent(context, ActivityExamenImages.class);
            intent.putExtra(DetailFragmentImmunisation.ARG_ITEM_ID, 54);



            context.startActivity(intent);*/
            imageExamen.buildDrawingCache();
            Bitmap image= imageExamen.getDrawingCache();
            Context context = view.getContext();
            Intent intent = new Intent(context, ActivityImageShow.class);

            Bundle extras = new Bundle();
            extras.putParcelable("imagebitmap",image);
            intent.putExtras(extras);
            context.startActivity(intent);


        }
    }

    // convenience method for getting data at click position
    public ExamenImage getItem(int id) {
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
