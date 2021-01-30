package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.knighenko.sweetvinegar.R;
import com.knighenko.sweetvinegar.entity.Advertisement;
import com.knighenko.sweetvinegar.model.JsonToObject;

import java.util.ArrayList;

public class LastAdvertisements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_advertisements);
        String jsonString = getIntent().getStringExtra("jsonString");



        ArrayList<Advertisement> advertisements= JsonToObject.getAdvertisements(jsonString);
               for (Advertisement advertisement:advertisements            ) {
                 System.out.println(advertisement.getId());
        }


    }
}