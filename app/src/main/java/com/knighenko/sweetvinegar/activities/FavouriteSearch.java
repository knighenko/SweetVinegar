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
       String[] request = {"2:" + getIntent().getStringExtra("e_mail")};
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
            new RegisterForPushNotificationsAsync(this).execute();



    }

    /**
     * Pushy class
     */
    private class RegisterForPushNotificationsAsync extends AsyncTask<Void, Void, Object> {
        Activity mActivity;

        public RegisterForPushNotificationsAsync(Activity activity) {
            this.mActivity = activity;
        }

        protected Object doInBackground(Void... params) {
            try {
                // Register the device for notifications
                String deviceToken = Pushy.register(getApplicationContext());
                String request = "2:" + getIntent().getStringExtra("e_mail")+":"+deviceToken;

                ConnectServer connectServer = new ConnectServer(Constants.SERVER_IP, Constants.PORT);
                connectServer.readResponse(request);
                // Registration succeeded, log token to logcat
                Log.d("Pushy", "Pushy device token: " + deviceToken);

                // Send the token to your backend server via an HTTP GET request
                //  new URL("https://{YOUR_API_HOSTNAME}/register/device?token=" + deviceToken).openConnection();

                // Provide token to onPostExecute()
                return deviceToken;
            } catch (Exception exc) {
                // Registration failed, provide exception to onPostExecute()
                return exc;
            }
        }

        @Override
        protected void onPostExecute(Object result) {
            String message;

            // Registration failed?
            if (result instanceof Exception) {
                // Log to console
                Log.e("Pushy", result.toString());

                // Display error in alert
                message = ((Exception) result).getMessage();
            } else {
                message = "Pushy device token: " + result.toString() + "\n\n(copy from logcat)";
            }

            // Registration succeeded, display an alert with the device token
            new android.app.AlertDialog.Builder(this.mActivity)
                    .setTitle("Pushy")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, null)
                    .show();
        }
    }

}