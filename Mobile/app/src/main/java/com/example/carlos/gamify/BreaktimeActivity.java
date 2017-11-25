package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class BreaktimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breaktime);

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

        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(intent_back);
                finish();
            }
        });

        final Intent intent_submit = new Intent(this, HomeActivity.class);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String aux = spinner_breaktime.getSelectedItem().toString();

                String aux2 = spinner_timeout.getSelectedItem().toString();

                Toast.makeText(BreaktimeActivity.this, aux2, Toast.LENGTH_SHORT).show();

                //Toast.makeText(BreaktimeActivity.this, aux2, Toast.LENGTH_LONG).show();
                //startActivity(intent);
            }
        });
    }
}
