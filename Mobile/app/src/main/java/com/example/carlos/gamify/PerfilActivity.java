package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import AuxClass.User;

public class PerfilActivity extends AppCompatActivity {

    private ImageView EditPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);



        String email = "jose@gmail.com";
        String password = "XYZ123";
        String pontuacao1 = "123";
        String pontuacao2 = "342";
        String nome = "Jose Antunes";
        String id = "1";
        String andar = "4º";

        User user = (User) getIntent().getSerializableExtra("user");

        final Intent edit_perfil_intent = new Intent(this, EditPerfilActivity.class);
        edit_perfil_intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
/*
        final Intent back_intent = new Intent(this, HomeActivity.class);
        back_intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
*/

        TextView nome_layout =  (TextView) findViewById(R.id.Nome_utilizador_perfil);
        nome_layout.setText(nome);

        TextView ID_layout =  (TextView) findViewById(R.id.ID_perfil);
        ID_layout.setText(id);

        TextView email_layout =  (TextView) findViewById(R.id.email_perfil_layout);
        email_layout.setText(email);

        TextView andar_layout =  (TextView) findViewById(R.id.andar_perfil_layout);
        andar_layout.setText(andar);

        TextView pontuacao1_layout =  (TextView) findViewById(R.id.pontuacao1_perfil_layout);
        pontuacao1_layout.setText(pontuacao1);
        TextView pontuacao2_layout =  (TextView) findViewById(R.id.pontuacao2_perfil_layout);
        pontuacao2_layout.setText(pontuacao2);


        EditPerfil = (ImageView) this.findViewById(R.id.edit_perfil_button);
        edit_perfil_intent.putExtra("user", user);
        EditPerfil.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                startActivity(edit_perfil_intent);
            }
        });

        Button back_button = (Button) this.findViewById(R.id.profile_back_button);
        //back_intent.putExtra("user", user);

        back_button.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                //startActivity(back_intent);
                finish();
            }
        });
    }





}
