package com.example.carlos.gamify;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import AuxClass.Auction;
import AuxClass.BreackTime;
import AuxClass.SocketClient;
import AuxClass.Transport;
import AuxClass.User;

public class BreaktimesAndFavorsActivity extends AppCompatActivity {
    private ArrayList<Breaktime> breaktimesList;
    private ArrayList<Favor> favorsList;

    SocketClient conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breaktimes_and_favors);

        breaktimesList = new ArrayList<Breaktime>();
        favorsList = new ArrayList<Favor>();
        ListView lview_breaktimes = (ListView) findViewById(R.id.breaktime_list);
        BreaktimeListAdapter breaktime_adapter = new BreaktimeListAdapter(this, breaktimesList);
        lview_breaktimes.setAdapter(breaktime_adapter);

        populateBreaktimesLists();
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

        /*ListView lview_favors = (ListView) findViewById(R.id.favor_list);

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
        });*/
    }

    private void populateBreaktimesLists() {
        conn = new SocketClient();
        try {
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Transport trans = new Transport();
        trans.setOpc(4);
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

        ArrayList<BreackTime> aux = trans.getBreackTimes();
        Breaktime aux2;
        for (int i=0; i<aux.size(); i++){
            aux2 = new Breaktime(aux.get(i).getType(), aux.get(i).getDelay(), i);
            breaktimesList.add(aux2);
        }
    }

}
