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

import java.io.IOException;
import java.net.URL;

import me.pushy.sdk.Pushy;
import me.pushy.sdk.util.exceptions.PushyException;


public class FavouriteSearch extends AppCompatActivity {
    private Button buttonSearch;
    private String push;
    private String e_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        push = getIntent().getStringExtra("push");
        e_mail = getIntent().getStringExtra("e_mail");


        setContentView(R.layout.activity_favourite_search);
        buttonSearch = findViewById(R.id.button_search);
        if (push.equals("false")) {
            buttonSearch.setText("Начать получать сообщения");
            buttonSearch.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            buttonSearch.setText("Отменить полчать сообщения");
            buttonSearch.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

    /**
     * Method doing when button Search (Начать Мониторинг или Отменить мониторинг) onClick
     */

    public void btnSearch(View view) {

        boolean flag = buttonSearch.getText().equals("Начать получать сообщения");
        String response = ConnectServer.connectToServerSearch("4:" + e_mail + ":" + flag);
        buttonSearch = findViewById(R.id.button_search);
        if (response.equals("true")) {
            if (!flag) {
                buttonSearch.setText("Начать получать сообщения");
                buttonSearch.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                buttonSearch.setText("Отменить получать сообщения");
                buttonSearch.setBackgroundColor(getResources().getColor(R.color.red));
            }
        }

    }
}


