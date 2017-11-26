package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FavoresDetail extends AppCompatActivity {

    int valor_bid = 0;
    boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favores_detail);

        Intent received_intent = getIntent();

        Favor favor_info = (Favor)received_intent.getSerializableExtra("Favor");

        final int bid_minima = favor_info.getLowest_bid();
        String favor = favor_info.getTitle();
        String nome = favor_info.getUser().getName();
        String id = Integer.toString(favor_info.getUser().getId());


        TextView favores_detail = (TextView) findViewById(R.id.favores_detail);
        favores_detail.setText(favor);
        TextView nome_criador_favor = (TextView) findViewById(R.id.nome_criador_favor);
        nome_criador_favor.setText(nome);
        TextView id_criador_favor = (TextView) findViewById(R.id.id_criador_favor);
        id_criador_favor.setText(id);

        TextView valor_bid_favor =  (TextView) findViewById(R.id.valor_bid_favor);
        valor_bid_favor.setText(Integer.toString(bid_minima));

        final EditText my_bid_favor =  (EditText) findViewById(R.id.my_bid_favor);
        my_bid_favor.setText(Integer.toString(bid_minima));


        final Intent intent_home = new Intent(this, HomeActivity.class);
        intent_home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        final Button submit_bid_favor = (Button) findViewById(R.id.submit_bid_favor);

        my_bid_favor.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().length() == 0 ) {

                    submit_bid_favor.setEnabled(false);
                } else {
                    valor_bid = Integer.parseInt(my_bid_favor.getText().toString());
                    if(valor_bid >= bid_minima){
                        TextView error_message_favor =  (TextView) findViewById(R.id.error_message_favor);
                        error_message_favor.setText("Erro! Não é possivel introduzir um valor maior ou igual à oferta mais baixa!");
                        submit_bid_favor.setEnabled(false);
                    }else{

                        submit_bid_favor.setEnabled(true);

                    }


                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });

        if (submit_bid_favor.isEnabled()){

            submit_bid_favor.setOnClickListener(new View.OnClickListener() {
                // Start new list activity
                public void onClick(View v) {
                    startActivity(intent_home);
                }
            });

        }

        final Intent intent_favores_detail = new Intent(this, BreaktimesAndFavorsActivity.class);
        intent_favores_detail.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Button favores_detail_back_button = (Button) findViewById(R.id.favores_detail_back_button);
        favores_detail_back_button.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                startActivity(intent_favores_detail);
            }
        });






    }
}
