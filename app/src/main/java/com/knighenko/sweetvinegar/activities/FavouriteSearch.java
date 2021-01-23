package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.knighenko.sweetvinegar.R;
import com.knighenko.sweetvinegar.entity.Constants;
import com.knighenko.sweetvinegar.model.ConnectServer;
import com.knighenko.sweetvinegar.model.SaveSharedPreference;

import java.io.IOException;
import java.net.URL;

import me.pushy.sdk.Pushy;
import me.pushy.sdk.util.exceptions.PushyException;


public class FavouriteSearch extends AppCompatActivity {
    private Button buttonSearch;
    private boolean push;
    private String e_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("sddddddddddddddddddddddddddddddddd    "+SaveSharedPreference.getUserName(FavouriteSearch.this).length());
        if(SaveSharedPreference.getUserName(FavouriteSearch.this).length() == 0)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Pushy.listen(this);
            e_mail = SaveSharedPreference.getUserName(getApplicationContext());
            push=Boolean.valueOf(ConnectServer.connectToServerSearch("3:"+e_mail));
            System.out.println("ssssssssssssssssssssssssssssssssssss=========="+e_mail);
            System.out.println("ssssssssssssssssssssssssssssssssssss=========="+push);
            setContentView(R.layout.activity_favourite_search);
            buttonSearch = findViewById(R.id.button_search);
            if (push==false) {
                buttonSearch.setText("Начать получать сообщения");
                buttonSearch.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                buttonSearch.setText("Отменить получать сообщения");
                buttonSearch.setBackgroundColor(getResources().getColor(R.color.red));
            }
        }


    }

    /**
     * Method doing when button Search (Начать Мониторинг или Отменить мониторинг) onClick
     */

    public void btnSearch(View view) {
        buttonSearch = findViewById(R.id.button_search);

        boolean push = SaveSharedPreference.getUserPush(getApplicationContext());
        String response = ConnectServer.connectToServerSearch("4:" + e_mail + ":" + String.valueOf(push));

        if (response.equals("true")) {
            if (push==true) {
                SaveSharedPreference.setUserPush(getApplicationContext(),false);
                buttonSearch.setText("Отменить получать сообщения");
                buttonSearch.setBackgroundColor(getResources().getColor(R.color.red));
            } else {
                SaveSharedPreference.setUserPush(getApplicationContext(),true);
                buttonSearch.setText("Начать получать сообщения");
                buttonSearch.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }

    }
}


