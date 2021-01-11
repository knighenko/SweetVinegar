package com.knighenko.sweetvinegar.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.knighenko.sweetvinegar.R;
import com.knighenko.sweetvinegar.entity.Constants;
import com.knighenko.sweetvinegar.model.ConnectServer;

import java.io.IOException;
import java.net.URL;

import me.pushy.sdk.Pushy;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Pushy.listen(this);
        if (!Pushy.isRegistered(this)) {
            new RegisterForPushNotificationsAsync(this).execute();
        }
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

                    ConnectServer connectServer = new ConnectServer(Constants.SERVER_IP, Constants.PORT);
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
        } else {
            Intent intent = new Intent(this, FavouriteSearch.class);
            intent.putExtra("e_mail", e_mail);
            intent.putExtra("password", password);
            startActivity(intent);
        }
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

                // Registration succeeded, log token to logcat
                Log.d("Pushy", "Pushy device token: " + deviceToken);

                // Send the token to your backend server via an HTTP GET request
                new URL("https://{YOUR_API_HOSTNAME}/register/device?token=" + deviceToken).openConnection();

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
