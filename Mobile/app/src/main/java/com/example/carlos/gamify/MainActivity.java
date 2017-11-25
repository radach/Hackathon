package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = findViewById(R.id.login_button);

        final Intent intent = new Intent(this, HomeActivity.class);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText email_input = (EditText) findViewById(R.id.email_input);
                EditText password_input = (EditText) findViewById(R.id.password_input);
                String email_value = email_input.getText().toString();
                String password_value = password_input.getText().toString();

                intent.putExtra("email", email_value);
                intent.putExtra("password",password_value);

                startActivity(intent);

            }
        });
    }


}
