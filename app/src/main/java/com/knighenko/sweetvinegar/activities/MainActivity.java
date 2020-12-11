package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        connectToServerSearch();
    }

    /**
     * Метод соединяется  сервером
     */

    private void connectToServerSearch() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    ConnectServer connectServer = new ConnectServer(SERVER_IP, PORT);
                    String string = connectServer.readJsonString("https://www.olx.ua/elektronika/kharkov/?search%5Bfilter_float_price%3Afrom%5D=free&search%5Bdist%5D=15");
                    System.out.println(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

}
