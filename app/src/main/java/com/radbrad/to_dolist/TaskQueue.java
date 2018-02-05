package com.radbrad.to_dolist;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by radbradmac on 2017-11-04.
 */

public class TaskQueue implements Serializable{

    //defining vars
    ArrayList<Task> queue;
    ArrayList<String> titles;

    public TaskQueue(){

        queue = new ArrayList<Task>();
        titles = new ArrayList<String>();

    }

    public Task get(int i){

        return queue.get(i);

    }

    public Task get(String title){

        Task task = new Task();

        for(int i = 0; i < queue.size(); i++){

            if(queue.get(i).getTitle().contentEquals(title)) {

                task = queue.get(i);
                break;

            }

        }

        return task;

    }

    public void add(Task task){

        queue.add(task);
        titles.add(task.getTitle());

    }

    public Task remove(){

        titles.remove(0);
        return queue.remove(0);

    }

    public Task remove(String title){

        Task task = new Task();

        for(int i = 0; i < queue.size(); i++) {

            if (queue.get(i).getTitle().contentEquals(title)) {

                task = queue.remove(i);

            }

        }

        return task;

    }

}
