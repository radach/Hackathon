package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageView VerPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //final Button motivar_button = findViewById(R.id.motivar_button);
        final Button logout_button = findViewById(R.id.logout_button);
        //final Button historico_button = findViewById(R.id.historico_button);
        final Button ver_breaktimes_favores_button = findViewById(R.id.ver_breaktimes_favores_button);
        final Button pedir_favor_button = findViewById(R.id.pedir_favor_button);
        final Button breaktime_button = findViewById(R.id.breaktime_button);


        final Intent breaktime_intent = new Intent(this, BreaktimeActivity.class);
        final Intent perfil_intent = new Intent(this, PerfilActivity.class);
        final Intent pedir_favor_intent = new Intent(this, PedirFavorActivity.class);
        final Intent ver_breaktime_favores_intent = new Intent(this, BreaktimesAndFavorsActivity.class);
        //final Intent motivar_intent = new Intent(this, MotivarActivity.class);
        //final Intent historico_intent = new Intent(this, HistoricoActivity.class);
        final Intent logout_intent = new Intent(this, MainActivity.class);

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
    }


}
