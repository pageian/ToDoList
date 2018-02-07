package com.radbrad.to_dolist;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
                startActivity(intent);

            }
        });

        //reading queues
        try {

            Storage.readQueues(getApplicationContext());

        }catch (ClassNotFoundException e){

            Log.e("InternalStorage", e.getMessage());

        }catch(IOException e){

            Log.e("InternalStorage", e.getMessage());

        }

        notifier();

        Button toDo = (Button)findViewById(R.id.to_do_button);
        final Button doing = (Button)findViewById(R.id.doing_button);

        toDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toDoI = new Intent(getApplicationContext(), ToDoActivity.class);
                startActivity(toDoI);

            }
        });

        doing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent doingI = new Intent(getApplicationContext(), DoingActivity.class);
                startActivity(doingI);

            }
        });

    }

    //***
    //odd implementation of title and text of notification
    public void sendNotification(){

        //getting NotificationManager
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.notif_title))
                .setContentText(getString(R.string.notif_text));

        //getting NotificationManager service
        NotificationManager notifManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //sending notification
        notifManager.notify(001, builder.build());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //manages when sendNotification is called
    public void notifier(){

        Runnable notifWrapper = new Runnable() {
            @Override
            public void run() {

                notifier();

            }
        };

        //defining time params
        int min;
        int hour;
        int day;
        int year;

        //***
        //eventually put in settings class
        int notifInterval = 1;

        //retrieving curr time
        min = Calendar.getInstance().get(Calendar.MINUTE);
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        year = Calendar.getInstance().get(Calendar.YEAR);

        //checking if it's time for notif
        if((Math.abs(Storage.getNotifMin() - min) <= notifInterval)
                &&(Storage.getNotifHour() == hour)){
            //time for notif
            sendNotification();

        }else{
        //not time for notif
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(notifWrapper, Storage.getNotifInterval(), TimeUnit.MINUTES);

        }

    }

}
