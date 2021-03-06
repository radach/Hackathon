package com.example.carlos.gamify;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.os.StrictMode;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import AuxClass.SocketClient;
import AuxClass.Transport;
import AuxClass.User;

public class MainActivity extends AppCompatActivity {
    //Socket sok;
    SocketClient conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button button = findViewById(R.id.login_button);
        final Intent intent_menu = new Intent(this, HomeActivity.class);
        intent_menu.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText email_input = (EditText) findViewById(R.id.email_input);
                EditText password_input = (EditText) findViewById(R.id.password_input);
                String email_value = email_input.getText().toString();
                String password_value = password_input.getText().toString();

                User user = new User();
                user.setUsername(email_value);
                user.setPass(password_value);
                user = connect(v, user);
                intent_menu.putExtra("user", user);

                startActivity(intent_menu);
                finish();
            }
        });
    }


    public User connect(View view, User user) {
        conn=new SocketClient();
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Transport trans = new Transport();
        User us = new User();
        //us.setUsername("rsantos");
        //us.setPass("rsantos");
        us = user;
        trans.setUser(us);
        trans.setOpc(1);
        trans.setLogin(false);
        try {
            trans = new SendToServer().execute(trans,conn).get();
            conn.disconnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("RETURN"," " + trans.getResullt());

        return trans.getUser();
    }

}
