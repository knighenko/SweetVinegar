package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.knighenko.sweetvinegar.R;

public class AdvertisementActivity extends AppCompatActivity {

    private TextView titleView;
    private TextView descriptionView;
    private ImageView imageView;



    private String url;
    private String title;
    private String imageSrc;
    private String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);
        this.descriptionView = findViewById(R.id.textViewDescription);
        this.url=getIntent().getStringExtra("urlAdv");
        this.title=getIntent().getStringExtra("title");
        descriptionView.setText(title);
    }

    public void advClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}