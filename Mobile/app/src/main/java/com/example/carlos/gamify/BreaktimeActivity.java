package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import AuxClass.BreackTime;
import AuxClass.SocketClient;
import AuxClass.Transport;
import AuxClass.User;

public class BreaktimeActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breaktime);

        user = (User) getIntent().getSerializableExtra("user");

        final Button submit_button = findViewById(R.id.breaktime_submit_button);
        final Button back_button = findViewById(R.id.breaktime_back_button);

        final Spinner spinner_breaktime = (Spinner) findViewById(R.id.spinner_breaktime);
        ArrayAdapter<CharSequence> breaktime_adapter = ArrayAdapter.createFromResource(this,
        R.array.breaktime_options, android.R.layout.simple_spinner_item);
        breaktime_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_breaktime.setAdapter(breaktime_adapter);

        final Spinner spinner_timeout = (Spinner) findViewById(R.id.spinner_breaktime_timeout);
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
                String breaktime = spinner_breaktime.getSelectedItem().toString();
                String timeout = spinner_timeout.getSelectedItem().toString();

                sendMessage(v, user, breaktime, timeout);

                startActivity(intent_submit);
            }
        });
    }

    public void sendMessage(View v, User user, String breaktime, String timeout) {
        SocketClient conn = new SocketClient();
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Transport trans = new Transport();
        trans.setUser(user);
        trans.setOpc(2);    // breaktime

        BreackTime bt = new BreackTime();
        bt.setCreator(user);
        bt.setType(breaktime);
        bt.setDelay(Integer.parseInt(timeout));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
        bt.setDate(date);

        trans.setWorkBreak(bt);

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
