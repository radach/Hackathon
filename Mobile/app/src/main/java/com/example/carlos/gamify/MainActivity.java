package com.example.carlos.gamify;

import android.os.StrictMode;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

import AuxClass.Transport;
import AuxClass.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void connect(View view) {
        Transport trans = new Transport();
        User us = new User();
        us.setUsername("rsantos");
        us.setPass("rsantos");
        trans.setUser(us);
        trans.setOpc(1);
        trans.setLogin(false);
        try {
            trans=new SendToServer().execute(trans).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("RETURN"," "+trans.getLogin());


    }
}
