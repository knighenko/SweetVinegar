package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

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
        connectToServerSearch("https://www.olx.ua/elektronika/kharkov/?search%5Bfilter_float_price%3Afrom%5D=free&search%5Bdist%5D=15");
    }

    /**
     * Method connecting to server with parameter Request
     */

    private void connectToServerSearch(String request) {


        new Thread(() -> {
            try {

                ConnectServer connectServer = new ConnectServer(SERVER_IP, PORT);
                String string = connectServer.readJsonString(request);
                System.out.println(string);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }).start();
    }

    /**
     * Method doing when button Enter (Вход) onClick
     */
    public void btnEnter(View view) {
        String e_mail;
        String password;
        e_mail = ((EditText) findViewById(R.id.edit_text_e_mail)).getText().toString();
        password = ((EditText) findViewById(R.id.edit_text_password)).getText().toString();
        connectToServerSearch(e_mail + "=" + password);
    }
}
