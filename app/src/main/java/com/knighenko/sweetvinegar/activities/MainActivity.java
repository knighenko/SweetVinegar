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

    }



    /**
     * Method doing when button Enter (Вход) onClick
     */
    public void btnEnter(View view) {
        String e_mail;
        String password;
        e_mail = ((EditText) findViewById(R.id.edit_text_e_mail)).getText().toString();
        password = ((EditText) findViewById(R.id.edit_text_password)).getText().toString();
        String response = ConnectServer.connectToServerSearch("1:" + e_mail + ":" + password);

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

/**Method create new user when click on "Создать новый аккаунт"*/
    public void createUser(View view) {
        Intent intent = new Intent(getBaseContext(), CreateUserActivity.class);
        startActivity(intent);
    }
}
