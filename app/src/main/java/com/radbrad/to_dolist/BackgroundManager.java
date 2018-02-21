package com.radbrad.to_dolist;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.round;

/**
 * Created by radbradmac on 2018-02-21.
 */

public class BackgroundManager extends IntentService {

    public BackgroundManager(){

        super("BackgroundManager");

    }

    @Override
    protected void onHandleIntent(Intent intent){

        String dataString = intent.getDataString();

        //calling notifier method
        notifier();

    }

    //manages when sendNotification is called
    public void notifier(){

        Runnable notifWrapper = new Runnable() {
            @Override
            public void run() {

                notifier();

            }
        };

        //defining current time params
        int min;
        int hour;

        //retrieving curr time
        min = Calendar.getInstance().get(Calendar.MINUTE);
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        //checking if it's time for notification
        if((Math.abs(Storage.getNotifMin() - min) <= round(Storage.getNotifInterval()/2))
                &&(Storage.getNotifHour() == hour)){
            //time for notif
            sendNotification();

        }

        //waiting to check for notification after interval
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(notifWrapper, Storage.getNotifInterval(), TimeUnit.MINUTES);

    }

    //***
    //odd implementation of title and text of notification
    public void sendNotification(){

        int day;
        int month;
        int year;
        String content = "";

        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH);
        year = Calendar.getInstance().get(Calendar.YEAR);

        for(int i = 0; i < Storage.getTodoQueue().size(); i++){

            if(Storage.getTodoQueue().get(i).getDay() == day
                    && Storage.getTodoQueue().get(i).getMonth() == month
                    && Storage.getTodoQueue().get(i).getYear() == year){

                content += Storage.getTodoQueue().get(i).getTitle() + " "
                        + Storage.monthTitle(Storage.getTodoQueue().get(i).getMonth()) + ", "
                        + Storage.getTodoQueue().get(i).getDay() + " "
                        + Storage.getTodoQueue().get(i).getHour() + ":"
                        + Storage.getTodoQueue().get(i).getMin() + "  ";

            }

        }

        for(int i = 0; i < Storage.getDoingQueue().size(); i++){

            if(Storage.getDoingQueue().get(i).getDay() == day
                    && Storage.getTodoQueue().get(i).getMonth() == month
                    && Storage.getDoingQueue().get(i).getYear() == year){

                content += Storage.getDoingQueue().get(i).getTitle() + " "
                        + Storage.monthTitle(Storage.getTodoQueue().get(i).getMonth()) + ", "
                        + Storage.getDoingQueue().get(i).getDay() + " "
                        + Storage.getDoingQueue().get(i).getHour() + ":"
                        + Storage.getDoingQueue().get(i).getMin() + "  ";

            }

        }

        //getting NotificationManager
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(getString(R.string.notif_title))
                        .setContentText(content);

        //getting NotificationManager service
        NotificationManager notifManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //sending notification
        notifManager.notify(001, builder.build());

    }

}
