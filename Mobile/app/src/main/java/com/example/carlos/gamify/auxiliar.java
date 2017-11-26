package com.example.carlos.gamify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import AuxClass.MqttHelper;

/**
 * Created by User on 26/11/2017.
 */

public class auxiliar extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        moveTaskToBack(true);
        super.onCreate(savedInstanceState);

        Boolean res = getIntent().getBooleanExtra("result", false);
        String owner = getIntent().getStringExtra("owner");

        if (res) {
            MqttHelper mqtt = new MqttHelper(getApplicationContext());
            String msg = owner + "/" + "response" + "/" + "true";
            MqttMessage message = new MqttMessage();
            message.setPayload(msg.getBytes());
            try {
                mqtt.mqttAndroidClient.publish("data", message);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

        finish();
    }
}
