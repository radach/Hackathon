package com.example.carlos.gamify;

/**
 * Created by Carlos Santos on 25/11/2017.
 */

public class Favor {
    private String title;
    private int time_remaining;
    private int ID;
    private int lowest_bid;

    public Favor(String title, int time_remaining, int lowest_bid, int ID){
        this.title = title;
        this.time_remaining = time_remaining;
        this.lowest_bid = lowest_bid;
        this.ID = ID;
    }

    public String getTitle(){
        return title;
    }

    public int getTime_remaining(){
        return time_remaining;
    }

    public int getLowest_bid(){
        return lowest_bid;
    }

}
