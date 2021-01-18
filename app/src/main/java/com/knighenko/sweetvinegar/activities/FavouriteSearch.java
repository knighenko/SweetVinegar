package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.knighenko.sweetvinegar.R;
import com.knighenko.sweetvinegar.entity.Constants;
import com.knighenko.sweetvinegar.model.ConnectServer;

import java.io.IOException;
import java.net.URL;

import me.pushy.sdk.Pushy;
import me.pushy.sdk.util.exceptions.PushyException;


public class FavouriteSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_search);
    }

    /**
     * Method doing when button Search (Мониторить) onClick
     */

    public void btnSearch(View view) {

  /*
       String[] request = {"3:" + getIntent().getStringExtra("e_mail")};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String deviceToken = Pushy.register(getApplicationContext());
                    request[0] = request[0] + ":" + deviceToken;
                    ConnectServer connectServer = new ConnectServer(Constants.SERVER_IP, Constants.PORT);
                    connectServer.readResponse(request[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        */




    }



}