package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import AuxClass.SocketClient;
import AuxClass.Transport;
import AuxClass.User;

public class BreaktimeActivity extends AppCompatActivity {
    User user;
    SocketClient conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breaktime);

        user = (User) getIntent().getSerializableExtra("user");
        conn = (SocketClient) getIntent().getSerializableExtra("connection");

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
        intent_back.putExtra("userId", user);
        intent_back.putExtra("connection", conn);

        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent_back);
            }
        });

        final Intent intent_submit = new Intent(this, HomeActivity.class);
        intent_submit.putExtra("userId", user);
        intent_submit.putExtra("connection", conn);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String breaktime = spinner_breaktime.getSelectedItem().toString();
                String timeout = spinner_timeout.getSelectedItem().toString();
                Toast.makeText(BreaktimeActivity.this, timeout, Toast.LENGTH_SHORT).show();

                sendMessage(v, user, conn, breaktime, timeout);

                //Toast.makeText(BreaktimeActivity.this, aux2, Toast.LENGTH_LONG).show();
                //startActivity(intent);
            }
        });
    }

    public void sendMessage(View v, User user, SocketClient conn, String breaktime, String timeout) {
        Transport trans = new Transport();
        trans.setUser(user);
        trans.setOpc(2);

        BreakTime bt = new BreakTime();
        bt.setCreator(user);
        bt.setType(breaktime);
        bt.setDelay(timeout);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
        bt.setDate(date);


    }
}
