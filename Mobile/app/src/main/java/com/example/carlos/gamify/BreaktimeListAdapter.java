package com.example.carlos.gamify;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Carlos Santos on 25/11/2017.
 */

public class BreaktimeListAdapter extends BaseAdapter {

    public ArrayList<Breaktime> breaktimesArrayList;
    Activity activity;

    public BreaktimeListAdapter(Activity activity, ArrayList<Breaktime> breaktimesArrayList) {
        super();
        this.activity = activity;
        this.breaktimesArrayList = breaktimesArrayList;
    }

    @Override
    public int getCount() {
        return breaktimesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return breaktimesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView btitle;
        TextView btime_remaining;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.breaktime_rows, null);
            holder = new ViewHolder();
            holder.btitle = (TextView) convertView.findViewById(R.id.breaktime_title);
            holder.btime_remaining = (TextView) convertView.findViewById(R.id.breaktime_timeout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Breaktime item = breaktimesArrayList.get(position);
        holder.btitle.setText(item.getTitle().toString());
        holder.btime_remaining.setText(Integer.toString(item.getTime_remaining()));

        return convertView;
    }
}

