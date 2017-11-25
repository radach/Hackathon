package com.example.carlos.gamify;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//import AuxClass.AsyncResponse;
import AuxClass.SocketClient;
import AuxClass.Transport;

/**
 * Created by rsantos on 11/25/17.
 */

public class SendToServer extends AsyncTask<Object,Void, Transport> {
    SocketClient sok;

    //public AsyncResponse delegte = null;



    @Override
    protected Transport doInBackground(Object... trans) {

            //sok = new Socket("192.168.10.170",6001);//10.0.2.2 is the localhost ip interface

        sok = (SocketClient) trans[1];
        Transport aux = (Transport)trans[0];

        try {
            //Log.d("Teste","TENTAR ESCREVER " + aux.getUser().getUsername());
            aux = (Transport) sok.sendMessage((Transport) trans[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return aux;
    }

    protected void onPostExecute(Transport result) {

        return ;

    }
}
