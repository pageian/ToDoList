package com.radbrad.to_dolist;

import java.util.Date;

/**
 * Created by radbradmac on 2017-11-04.
 */

public class Task {

    //defining vars
    String title, desc;
    Date date;

    public Task(){



    }

    public Task(String title, String desc, int yr, int mth, int day){

        Date date = new Date(yr, mth, day);

        this.title = title;
        this.desc = desc;
        this.date = date;

    }

    //getters
    public String getTitle(){

        return title;

    }

    public String getDesc(){

        return title;

    }

    public Date getDate(){

        return date;

    }

    //setters
    public void setTitle(String title){

        this.title = title;

    }

    public void setDesc(String desc){

        this.desc = desc;

    }

    public void setDate(int yr, int mth, int day){

        Date date = new Date(yr, mth, day);

        this.date = date;

    }

}
