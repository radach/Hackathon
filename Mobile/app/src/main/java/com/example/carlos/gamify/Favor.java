package com.example.carlos.gamify;

import java.io.Serializable;

import AuxClass.User;

/**
 * Created by Carlos Santos on 25/11/2017.
 */

public class Favor implements Serializable{
    private static final long serialVersionUID = -6470090944414208496L;

    private String title;
    private int time_remaining;
    private int ID;
    private int lowest_bid;
    private User user;
    private String date;

    public Favor(String title, String date, int time_remaining, int lowest_bid, int ID, User user){
        this.title = title;
        this.time_remaining = time_remaining;
        this.lowest_bid = lowest_bid;
        this.ID = ID;
        this.user = user;
        this.date = date;
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

    public User getUser(){
        return user;
    }
}
