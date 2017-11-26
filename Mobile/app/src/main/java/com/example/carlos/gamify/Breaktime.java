package com.example.carlos.gamify;

import java.io.Serializable;

/**
 * Created by Carlos Santos on 25/11/2017.
 */

public class Breaktime implements Serializable{
    private static final long serialVersionUID = -6470090944414208496L;
    private String title;
    private int time_remaining;
    private int ID;

    public Breaktime(String title, int time_remaining, int ID){
        this.title = title;
        this.time_remaining = time_remaining;
        this.ID = ID;
    }

    public String getTitle(){
        return title;
    }

    public int getTime_remaining(){
        return time_remaining;
    }
}
