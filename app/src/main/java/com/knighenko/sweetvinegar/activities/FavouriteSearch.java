package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.knighenko.sweetvinegar.R;
import com.knighenko.sweetvinegar.entity.Constants;
import com.knighenko.sweetvinegar.model.ConnectServer;
import java.io.IOException;


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
        String request = "2:"+getIntent().getStringExtra("e_mail");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ConnectServer connectServer = new ConnectServer(Constants.SERVER_IP, Constants.PORT);
                    connectServer.readResponse(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



}