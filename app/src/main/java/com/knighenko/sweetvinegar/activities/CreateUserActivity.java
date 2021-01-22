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

import me.pushy.sdk.Pushy;
import me.pushy.sdk.util.exceptions.PushyException;

public class CreateUserActivity extends AppCompatActivity {
    private String e_mail;
    private String password;
    private String deviceToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }

    public void btnCreate(View view) {
        e_mail = ((EditText) findViewById(R.id.edit_text_e_mail_create_user)).getText().toString();
        password = ((EditText) findViewById(R.id.edit_text_password_create_user)).getText().toString();
        new RegisterForPushNotificationsAsync(this).execute();





    }


    /**
     * Pushy class - create device Token and send on my server
     */
    private class RegisterForPushNotificationsAsync extends AsyncTask<Void, Void, Object> {
        Activity mActivity;

        public RegisterForPushNotificationsAsync(Activity activity) {
            this.mActivity = activity;
        }

        protected Object doInBackground(Void... params) {

            // Register the device for notifications

                try {
                    deviceToken = Pushy.register(getApplicationContext());
                } catch (PushyException e) {
                    e.printStackTrace();
                }



            // Registration succeeded, log token to logcat
            Log.d("Pushy", "Pushy device token: " + deviceToken);

            // Send the token to your backend server via an HTTP GET request
            //  new URL("https://{YOUR_API_HOSTNAME}/register/device?token=" + deviceToken).openConnection();

            // Provide token to onPostExecute()
            return deviceToken;

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
            String response = ConnectServer.connectToServerSearch("2:" + e_mail + ":" + password + ":" + deviceToken);
            System.out.println(response);
            if (response.equals("false")) {
                Toast toast = Toast.makeText(getBaseContext(), "Что-то пошло не так! Повторите регистрацию!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getBaseContext(), "Регистрация проведена успешно!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        }
    }
}