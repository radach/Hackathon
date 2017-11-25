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

public class PedirFavorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_favor);

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

        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(intent_back);
                finish();
            }
        });

        final Intent intent_submit = new Intent(this, HomeActivity.class);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String aux = favor_description.getText().toString();

                String aux2 = spinner_timeout.getSelectedItem().toString();

                String aux3 = credits.getText().toString();

                Toast.makeText(PedirFavorActivity.this, aux3, Toast.LENGTH_SHORT).show();

                //Toast.makeText(BreaktimeActivity.this, aux2, Toast.LENGTH_LONG).show();
                //startActivity(intent);
            }
        });
    }
}
