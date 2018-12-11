package com.oc.rss.appfordocter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.oc.rss.appfordocter.WebService.RestService;

/**
 * Created by Ghassen on 06/05/2017.
 */

public class ActivityImageShow extends AppCompatActivity {
    RestService restService;
    ImageView image;
    View form;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_examen_full_screen);
       Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

            image = (ImageView) findViewById(R.id.image_preview);

            Bundle extras = getIntent().getExtras();
            Bitmap bmp = (Bitmap) extras.getParcelable("imagebitmap");

            image.setImageBitmap(bmp);


        }

    }
}


