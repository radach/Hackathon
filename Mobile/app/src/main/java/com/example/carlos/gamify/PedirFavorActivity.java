package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import AuxClass.Auction;
import AuxClass.SocketClient;
import AuxClass.Transport;
import AuxClass.User;

public class PedirFavorActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_favor);

        user = (User) getIntent().getSerializableExtra("user");

        final Button submit_button = findViewById(R.id.favor_submit_button);
        final Button back_button = findViewById(R.id.favor_back_button);
        final EditText favor_description = (EditText) findViewById(R.id.favor_description);
        final EditText credits = (EditText) findViewById(R.id.max_credits);

        final Spinner spinner_timeout = (Spinner) findViewById(R.id.spinner_favor_timeout);
        ArrayAdapter<CharSequence> timeout_adapter = ArrayAdapter.createFromResource(this,
                R.array.timeout_options, android.R.layout.simple_spinner_item);
        timeout_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_timeout.setAdapter(timeout_adapter);

        final Intent intent_back = new Intent(this, HomeActivity.class);
        intent_back.putExtra("user", user);
        intent_back.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(intent_back);
            }
        });

        final Intent intent_submit = new Intent(this, HomeActivity.class);
        intent_submit.putExtra("user", user);
        intent_submit.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String auction_title = favor_description.getText().toString();
                String max = spinner_timeout.getSelectedItem().toString();
                String timeout = credits.getText().toString();

                sendMessage(v, user, auction_title, timeout, max);

                Toast.makeText(PedirFavorActivity.this, timeout, Toast.LENGTH_SHORT).show();

                Toast.makeText(PedirFavorActivity.this, max, Toast.LENGTH_LONG).show();
                startActivity(intent_submit);
            }
        });
    }

    public void sendMessage(View v, User user, String auction_title, String timeout, String max) {
        SocketClient conn = new SocketClient();
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Transport trans = new Transport();
        trans.setUser(user);
        trans.setOpc(3);    // leilao

        Auction auc = new Auction();
        auc.setUser(user);
        auc.setMax(Integer.parseInt(max));
        auc.setType(auction_title);
        auc.setDelay(Integer.parseInt(timeout));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
        auc.setDate(date);

        trans.setAuction(auc);

        try {
            trans = new SendToServer().execute(trans, conn).get();
            conn.disconnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
