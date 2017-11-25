package com.example.carlos.gamify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    private ImageView VerPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Resources res = getResources();
        int user_score = 0;

        //final Button motivar_button = findViewById(R.id.motivar_button);
        final Button logout_button = findViewById(R.id.logout_button);
        //final Button historico_button = findViewById(R.id.historico_button);
        final Button ver_breaktimes_favores_button = findViewById(R.id.ver_breaktimes_favores_button);
        final Button pedir_favor_button = findViewById(R.id.pedir_favor_button);
        final Button breaktime_button = findViewById(R.id.breaktime_button);
        final Button button_testNotification = findViewById(R.id.button_notification);

        final Intent breaktime_intent = new Intent(this, BreaktimeActivity.class);
        final Intent perfil_intent = new Intent(this, PerfilActivity.class);
        final Intent pedir_favor_intent = new Intent(this, PedirFavorActivity.class);
        final Intent ver_breaktime_favores_intent = new Intent(this, BreaktimesAndFavorsActivity.class);
        //final Intent motivar_intent = new Intent(this, MotivarActivity.class);
        //final Intent historico_intent = new Intent(this, HistoricoActivity.class);
        final Intent logout_intent = new Intent(this, MainActivity.class);

        TextView score = (TextView) this.findViewById(R.id.score_text);
        score.setText(res.getString(R.string.score_string) + " " + Integer.toString(user_score));

        VerPerfil = (ImageView) this.findViewById(R.id.ver_perfil);


        breaktime_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(breaktime_intent);
                finish();

            }
        });


        pedir_favor_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(pedir_favor_intent);
                finish();

            }
        });

        ver_breaktimes_favores_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(ver_breaktime_favores_intent);
                finish();

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
                finish();

            }
        });

        // set a onclick listener for when the button gets clicked
        VerPerfil.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                startActivity(perfil_intent);
                finish();
            }
        });

        button_testNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("vai notification");
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(view.getContext())
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!");

                // Creates an explicit intent for an Activity in your app
                Intent resultIntent = new Intent(view.getContext(), BreaktimeActivity.class);

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
        });

    }


}
