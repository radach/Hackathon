package com.example.carlos.gamify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BreaktimesAndFavorsActivity extends AppCompatActivity {
    private ArrayList<Breaktime> breaktimesList;
    private ArrayList<Favor> favorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breaktimes_and_favors);

        breaktimesList = new ArrayList<Breaktime>();
        favorsList = new ArrayList<Favor>();
        ListView lview_breaktimes = (ListView) findViewById(R.id.breaktime_list);
        BreaktimeListAdapter breaktime_adapter = new BreaktimeListAdapter(this, breaktimesList);
        lview_breaktimes.setAdapter(breaktime_adapter);

        populateBreaktimesList();
        breaktime_adapter.notifyDataSetChanged();

        lview_breaktimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String title = ((TextView)view.findViewById(R.id.breaktime_title)).getText().toString();
                String timeout = ((TextView)view.findViewById(R.id.breaktime_timeout)).getText().toString();

                Toast.makeText(getApplicationContext(),
                        "Title : " + title +"\n"
                                +"Timeout: " + timeout, Toast.LENGTH_SHORT).show();
            }
        });

        ListView lview_favors = (ListView) findViewById(R.id.favor_list);

        FavorListAdapter favor_adapter = new FavorListAdapter(this, favorsList);
        lview_favors.setAdapter(favor_adapter);

        populateFavorsList();

        favor_adapter.notifyDataSetChanged();

        lview_favors.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String title = ((TextView)view.findViewById(R.id.favor_title)).getText().toString();
                String timeout = ((TextView)view.findViewById(R.id.favor_timeout)).getText().toString();
                String lowest_bid = ((TextView)view.findViewById(R.id.lowest_bid)).getText().toString();

                Toast.makeText(getApplicationContext(),
                        "Title : " + title +"\n"
                                +"Timeout: " + timeout + "\n"
                                +"Lowest Bid: " + lowest_bid, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateBreaktimesList() {

        Breaktime item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14;

        item1 = new Breaktime("Cafe", 50, 50);
        breaktimesList.add(item1);

        item2 = new Breaktime("Cenas", 50, 50);
        breaktimesList.add(item2);

        item3 = new Breaktime("Whisky", 50, 50);
        breaktimesList.add(item3);

        item4 = new Breaktime("Cerveja", 50, 50);
        breaktimesList.add(item4);

        item5 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item5);

        item6 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item6);
        item7 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item7);
        item8 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item8);
        item9 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item9);
        item10 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item10);
        item11 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item11);
        item12 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item12);
        item13 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item13);
        item14 = new Breaktime("Vinho", 50, 50);
        breaktimesList.add(item14);
    }

    private void populateFavorsList() {

        Favor item1, item2, item3, item4, item5;

        item1 = new Favor("Cafe", 50, 50, 50);
        favorsList.add(item1);

        item2 = new Favor("Cenas", 50, 50, 50);
        favorsList.add(item2);

        item3 = new Favor("Whisky", 50, 50, 50);
        favorsList.add(item3);

        item4 = new Favor("Cerveja", 50, 50, 50);
        favorsList.add(item4);

        item5 = new Favor("Vinho", 50, 50, 50);
        favorsList.add(item5);

    }


}
