package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.knighenko.sweetvinegar.R;
import com.knighenko.sweetvinegar.model.ConnectServer;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    // private static final String SERVER_IP = "91.235.129.33";
    private static final String SERVER_IP = "10.0.2.2";
    private static final int PORT = 8080;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Method connecting to server with parameter Request
     */

    private String connectToServerSearch(String request) {

        final String[] response = {"false"};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    ConnectServer connectServer = new ConnectServer(SERVER_IP, PORT);
                    response[0] = connectServer.readResponse(request);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response[0];
    }

    /**
     * Method doing when button Enter (Вход) onClick
     */
    public void btnEnter(View view) {
        String e_mail;
        String password;
        e_mail = ((EditText) findViewById(R.id.edit_text_e_mail)).getText().toString();
        password = ((EditText) findViewById(R.id.edit_text_password)).getText().toString();
        String response = connectToServerSearch("1:" + e_mail + ":" + password);
        System.out.println("Response is: " + response);
        if (response.equals("false")) {
            Toast toast = Toast.makeText(this, "Неправильно введён логин или пароль!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
        else {
            Intent  intent = new Intent(this, FavouriteSearch.class);
            startActivity(intent);
        }
    }

    /**
     * Method doing when button Search (Мониторить) onClick
     */

    public void btnSearch(View view) {
        String e_mail;
        String password;
    }
}
