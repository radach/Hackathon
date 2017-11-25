package com.example.carlos.gamify;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//import AuxClass.AsyncResponse;
import AuxClass.Transport;

/**
 * Created by rsantos on 11/25/17.
 */

public class SendToServer extends AsyncTask<Transport,Void, Transport> {
    Socket sok;
    ObjectInputStream in;
    ObjectOutputStream out;
    //public AsyncResponse delegte = null;



    @Override
    protected Transport doInBackground(Transport... trans) {
        try {
            sok = new Socket("192.168.10.170",6001);//10.0.2.2 is the localhost ip interface

        } catch (IOException e) {
            Log.d("Error", "Error connecting to server");
        }
        try {
            out = new ObjectOutputStream(sok.getOutputStream());
            out.flush();
            in = new ObjectInputStream(sok.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Log.d("Teste","TENTAR ESCREVER "+trans[0].getUser().getUsername());
            out.writeObject(trans[0]);
            trans[0]=(Transport) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return trans[0];
    }

    protected void onPostExecute(Transport result) {

        return ;

    }
}
