package com.radbrad.to_dolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by radbradmac on 2017-11-04.
 */

public class Storage{

    private Storage(){}

    public static TaskQueue doingQueue = new TaskQueue();
    public static TaskQueue todoQueue = new TaskQueue();
    public static Task currTask;

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

    //updates changes of both queues to File
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

}


