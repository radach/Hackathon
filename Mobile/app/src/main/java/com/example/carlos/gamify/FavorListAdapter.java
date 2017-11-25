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

public class FavorListAdapter extends BaseAdapter {

    public ArrayList<Favor> favorArrayList;
    Activity activity;

    public FavorListAdapter(Activity activity, ArrayList<Favor> favorArrayList) {
        super();
        this.activity = activity;
        this.favorArrayList = favorArrayList;
    }

    @Override
    public int getCount() {
        return favorArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return favorArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView btitle;
        TextView btime_remaining;
        TextView blowest_bid;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.favors_rows, null);
            holder = new ViewHolder();
            holder.btitle = (TextView) convertView.findViewById(R.id.favor_title);
            holder.btime_remaining = (TextView) convertView.findViewById(R.id.favor_timeout);
            holder.blowest_bid = (TextView) convertView.findViewById(R.id.lowest_bid);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Favor item = favorArrayList.get(position);
        holder.btitle.setText(item.getTitle().toString());
        holder.btime_remaining.setText(Integer.toString(item.getTime_remaining()));
        holder.blowest_bid.setText(Integer.toString(item.getLowest_bid()));

        return convertView;
    }
}

