package com.example.carlos.gamify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.w3c.dom.Text;

import java.io.IOException;
import AuxClass.MqttHelper;
import AuxClass.SocketClient;
import AuxClass.Transport;
import AuxClass.User;

public class HomeActivity extends AppCompatActivity {
    private boolean asyncCreated;
    MqttHelper mqttHelper;
    private ImageView VerPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        startMqtt(getBaseContext());
        Resources res = getResources();
        int user_score = 0;
        User user = (User) getIntent().getSerializableExtra("user");
        asyncCreated = getIntent().getBooleanExtra("async", false);
        if (asyncCreated == false)
            asyncCreated = true;

        //final Button motivar_button = findViewById(R.id.motivar_button);
        final Button logout_button = findViewById(R.id.logout_button);
        //final Button historico_button = findViewById(R.id.historico_button);
        final Button ver_breaktimes_favores_button = findViewById(R.id.ver_breaktimes_favores_button);
        final Button pedir_favor_button = findViewById(R.id.pedir_favor_button);
        final Button breaktime_button = findViewById(R.id.breaktime_button);
        //final Button button_testNotification = findViewById(R.id.button_notification);

        final Intent breaktime_intent = new Intent(this, BreaktimeActivity.class);
        breaktime_intent.putExtra("user", user);
        breaktime_intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        final Intent perfil_intent = new Intent(this, PerfilActivity.class);
        perfil_intent.putExtra("user", user);
        perfil_intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        final Intent pedir_favor_intent = new Intent(this, PedirFavorActivity.class);
        pedir_favor_intent.putExtra("user", user);
        pedir_favor_intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        final Intent ver_breaktime_favores_intent = new Intent(this, BreaktimesAndFavorsActivity.class);
        ver_breaktime_favores_intent.putExtra("user", user);
        ver_breaktime_favores_intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        //final Intent motivar_intent = new Intent(this, MotivarActivity.class);
        //final Intent historico_intent = new Intent(this, HistoricoActivity.class);
        final Intent logout_intent = new Intent(this, MainActivity.class);
        logout_intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        TextView score = (TextView) this.findViewById(R.id.score_text);
        score.setText(res.getString(R.string.score_string) + " " + Integer.toString(user_score));

        VerPerfil = (ImageView) this.findViewById(R.id.ver_perfil);

        breaktime_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(breaktime_intent);
            }
        });


        pedir_favor_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(pedir_favor_intent);
            }
        });

        ver_breaktimes_favores_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(ver_breaktime_favores_intent);
            }
        });

        /*motivar_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(motivar_intent);

            }
        });

        historico_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(historico_intent);

            }
        });*/

        logout_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(logout_intent);
            }
        });

        // set a onclick listener for when the button gets clicked
        VerPerfil.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                startActivity(perfil_intent);
            }
        });


        /*button_testNotification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(view.getContext())
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!");

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(view.getContext(), BreaktimeActivity.class);
                resultIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                // The stack builder object will contain an artificial back stack for the started Activity.
                // This ensures that navigating backward from the Activity leads out of your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(view.getContext());
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(HomeActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                Notification notif = mBuilder.build();
                mNotificationManager.notify(33, notif);
            }
        });*/
    }

    private void startMqtt(final Context cont){
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.w("Debug","Connected");
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug",mqttMessage.toString());

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(cont)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!");

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(cont, BreaktimeActivity.class);

                // The stack builder object will contain an artificial back stack for the started Activity.
                // This ensures that navigating backward from the Activity leads out of your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(cont);
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(HomeActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                Notification notif = mBuilder.build();
                mNotificationManager.notify(33, notif);
                //mChart.addEntry(Float.valueOf(mqttMessage.toString()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }



}
