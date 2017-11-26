package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import AuxClass.User;

public class EditPerfilActivity extends AppCompatActivity {

    private Button SaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);

        String email = "jose@gmail.com";
        String password = "XYZ123";
        String nome = "Jose Antunes";
        int pos_andar = 1;

        User user = (User) getIntent().getSerializableExtra("user");

        final Intent perfil_intent = new Intent(this, PerfilActivity.class);
        perfil_intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        perfil_intent.putExtra("user", user);


        EditText input_name_edit_perfil =  (EditText) findViewById(R.id.input_name_edit_perfil);
        input_name_edit_perfil.setText(nome);

        EditText input_password_edit_perfil =  (EditText) findViewById(R.id.input_password_edit_perfil);
        input_password_edit_perfil.setText(password);

        EditText input_email_edit_perfil =  (EditText) findViewById(R.id.input_email_edit_perfil);
        input_email_edit_perfil.setText(email);

        Spinner input_andar_edit_perfil =  (Spinner) findViewById(R.id.spinner_andar_edit_perfil);
        ArrayAdapter<CharSequence> adapter_spinner = ArrayAdapter.createFromResource(this,
                R.array.andares, android.R.layout.simple_spinner_item);
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_andar_edit_perfil.setAdapter(adapter_spinner);

        Button back_button = (Button) findViewById(R.id.edit_profile_back_button);


        input_andar_edit_perfil.setSelection(pos_andar);

        input_andar_edit_perfil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                System.out.println("posição spinner: "+position);
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        SaveChanges = (Button) this.findViewById(R.id.save_changes_perfil);
        SaveChanges.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                //TODO: IMPLEMENT SAVE
                //startActivity(perfil_intent);
                finish();
            }
        });

        final Intent intent = new Intent(this, PerfilActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("user", user);
        back_button.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                //startActivity(intent);
                finish();
            }
        });
    }
}
