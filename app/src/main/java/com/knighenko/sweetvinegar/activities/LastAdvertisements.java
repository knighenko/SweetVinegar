package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.knighenko.sweetvinegar.R;
import com.knighenko.sweetvinegar.adapter.AdvAdapter;
import com.knighenko.sweetvinegar.entity.Advertisement;
import com.knighenko.sweetvinegar.model.JsonToObject;

import java.util.ArrayList;

public class LastAdvertisements extends AppCompatActivity {
    private RecyclerView listAdvRecyclerView;
    private AdvAdapter advAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_advertisements);
        String jsonString = getIntent().getStringExtra("jsonString");
        ArrayList<Advertisement> advertisementsList = JsonToObject.getAdvertisements(jsonString);
        for (Advertisement advertisement : advertisementsList) {
            System.out.println(advertisement.getId());
        }
        listAdvRecyclerView = findViewById(R.id.recyclerTenAdv);
        listAdvRecyclerView.setHasFixedSize(true);
        listAdvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdvAdapter.OnAdvertisementClickListener onAdvertisementClickListener=new AdvAdapter.OnAdvertisementClickListener() {
            @Override
            public void onAdvClick(Advertisement advertisement) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(advertisement.getUrl()));
                startActivity(intent);
            }
        };
        advAdapter = new AdvAdapter(onAdvertisementClickListener);
        advAdapter.setListAdv(advertisementsList);
        listAdvRecyclerView.setAdapter(advAdapter);


    }
}