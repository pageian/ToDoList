package com.radbrad.to_dolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by radbradmac on 2017-11-04.
 */

public class Storage{

    private Storage(){}

    /**
     * NOTE: notifInterval must be an odd int greater than 1
     */
    private static int notifInterval = 5;
    private static int notifMin;
    private static int notifHour;
    private static TaskQueue doingQueue = new TaskQueue();
    private static TaskQueue todoQueue = new TaskQueue();
    private static Task currTask;

    //reads queues from File
    public static void readQueues(Context context) throws IOException, ClassNotFoundException{

        //todoQueue
        FileInputStream todoFIS = context.openFileInput("todo_storage");
        ObjectInputStream todoOIS = new ObjectInputStream(todoFIS);
        todoQueue = (TaskQueue) todoOIS.readObject();
        todoOIS.close();
        todoFIS.close();

        //doingQueue
        FileInputStream doingFIS = context.openFileInput("doing_storage");
        ObjectInputStream doingOIS = new ObjectInputStream(doingFIS);
        doingQueue = (TaskQueue) doingOIS.readObject();
        doingOIS.close();
        doingOIS.close();

    }

    //writes changes of both queues to File
    public static void writeQueues(Context context) throws IOException{

        //todoQueue
        FileOutputStream todoFOS = context.openFileOutput("todo_storage", Context.MODE_PRIVATE);
        ObjectOutputStream todoOOS = new ObjectOutputStream(todoFOS);
        todoOOS.writeObject(todoQueue);
        todoOOS.close();
        todoFOS.close();

        //doingQueue
        FileOutputStream doingFOS = context.openFileOutput("doing_storage", Context.MODE_PRIVATE);
        ObjectOutputStream doingOOS = new ObjectOutputStream(doingFOS);
        doingOOS.writeObject(doingQueue);
        doingOOS.close();
        doingFOS.close();

    }

    //writes notification settings to File
    public static void writeNotifTimes(Context context) throws IOException{

        //minute
        FileOutputStream minFOS = context.openFileOutput("notif_min_storage", Context.MODE_PRIVATE);
        ObjectOutputStream minOOS = new ObjectOutputStream(minFOS);
        minOOS.writeObject(notifMin);
        minOOS.close();
        minFOS.close();

        //hour
        FileOutputStream hourFOS = context.openFileOutput("notif_hour_storage", Context.MODE_PRIVATE);
        ObjectOutputStream hourOOS = new ObjectOutputStream(hourFOS);
        hourOOS.writeObject(notifHour);
        hourOOS.close();
        hourFOS.close();

    }

    //reads notification settings from File
    public static  void readNotifTimes(Context context) throws IOException, ClassNotFoundException{

        //minute
        FileInputStream minFIS = context.openFileInput("notif_min_storage");
        ObjectInputStream minOIS = new ObjectInputStream(minFIS);
        notifMin = (int) minOIS.readObject();
        minOIS.close();
        minFIS.close();

        //hour
        FileInputStream hourFIS = context.openFileInput("notif_hour_storage");
        ObjectInputStream hourOIS = new ObjectInputStream(hourFIS);
        notifHour = (int) hourOIS.readObject();
        hourOIS.close();
        hourOIS.close();

    }

    //getters
    public static int getNotifMin(){

        return notifMin;

    }public static int getNotifHour(){

        return notifHour;

    }public static int getNotifInterval(){

        return notifInterval;

    }public static TaskQueue getTodoQueue(){

        return todoQueue;

    }public static TaskQueue getDoingQueue(){

        return doingQueue;

    }public static Task getCurrTask(){

        return currTask;

    }

    //setters
    public static void setNotifMin(int minute) {

        notifMin = minute;

    }public static void setNotifHour(int hour){

        notifHour = hour;

    }public static void setTodoQueue(TaskQueue queue){

        todoQueue = queue;

    }public static void setDoingQueue(TaskQueue queue){

        doingQueue = queue;

    }public static void setCurrTask(Task task){

        currTask = task;

    }

    //adds task to todoQueue
    public static void addTodo(Task task){

        todoQueue.add(task);

    }

    //switches task from todo to doing
    public static void markDoing(Task task){

        doingQueue.add(todoQueue.remove(task.getTitle()));

    }

    //removes stask from doing
    public static Task removeFromDoing(Task task){

        return doingQueue.remove(task.getTitle());

    }

}


