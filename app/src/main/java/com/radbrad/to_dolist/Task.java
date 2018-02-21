package com.radbrad.to_dolist;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by radbradmac on 2017-11-04.
 */

public class Task implements Serializable{

    //defining vars
    private String title, desc;
    private int min, hour, day, month, year;

    public Task(){



    }

    public Task(String title, String desc, int year, int month, int day, int hour, int min){

        this.title = title;
        this.desc = desc;
        this.min = min;
        this.hour = hour;
        this.day = day;
        this.month = month;
        this.year = year;

    }

    //getters
    public String getTitle(){

        return title;

    }

    public String getDesc(){

        return desc;

    }

    public int getMin(){

        return min;

    }

    public int getHour(){

        return hour;

    }

    public int getDay(){

        return day;

    }

    public int getMonth(){

        return month;

    }

    public int getYear(){

        return year;

    }

    //setters
    public void setTitle(String title){

        this.title = title;

    }

    public void setDesc(String desc){

        this.desc = desc;

    }

}
