package com.example.carlos.gamify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BreaktimeDetail extends AppCompatActivity {

    private ImageView AcceptBreaktime;
    private ImageView RefuseBreaktime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breaktime_detail);

        Intent received_intent = getIntent();

        Breaktime break_info = (Breaktime)received_intent.getSerializableExtra("Breaktime");

        String detail_breaktime_text = break_info.getTitle();
        TextView breaktime_detail =  (TextView) findViewById(R.id.breaktime_detail);
        breaktime_detail.setText(detail_breaktime_text);



        final Intent intent_breaktime_detail = new Intent(this, BreaktimesAndFavorsActivity.class);
        intent_breaktime_detail.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Button breaktime_detail_back_button = (Button) findViewById(R.id.breaktime_detail_back_button);
        breaktime_detail_back_button.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                startActivity(intent_breaktime_detail);
            }
        });


        final Intent intent_home = new Intent(this, HomeActivity.class);
        intent_home.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        AcceptBreaktime = (ImageView) this.findViewById(R.id.accept_breaktime);
        AcceptBreaktime.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                startActivity(intent_home);
            }
        });

        RefuseBreaktime = (ImageView) this.findViewById(R.id.refuse_breaktime);
        RefuseBreaktime.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {

                startActivity(intent_breaktime_detail);
            }
        });
    }
}
