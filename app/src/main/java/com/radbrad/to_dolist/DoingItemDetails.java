package com.radbrad.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DoingItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_item_details);

        final TextView title = (TextView)findViewById(R.id.doing_title_textView);
        TextView date = (TextView)findViewById(R.id.doing_date_textView);
        TextView desc = (TextView)findViewById(R.id.doing_desc_textView);
        Button doneBTN = (Button)findViewById(R.id.to_doing_done_button);

        title.setText(Storage.currTask.getTitle());
        date.setText(Storage.currTask.getDate() + "");
        desc.setText(Storage.currTask.getDesc());

        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Storage.doingQueue.remove();
                //saveToStorage();

                try{

                    Storage.writeQueues(getApplicationContext());

                }catch(IOException e){

                    Log.e("InternalStorage", e.getMessage());

                }

                Intent intent = new Intent(getApplicationContext(), DoingActivity.class);
                startActivity(intent);

                finish();

            }
        });

    }

    private void saveToStorage(){

        try{

            //TO-DO Queue storage
            FileOutputStream toDoFOS = openFileOutput("to_do_storage", Context.MODE_PRIVATE);
            ObjectOutputStream toDoOOS = new ObjectOutputStream(toDoFOS);
            toDoOOS.writeObject(Storage.todoQueue);
            toDoOOS.flush();
            toDoOOS.close();
            toDoFOS.close();

            //DOING Queue storage
            FileOutputStream doingFOS = openFileOutput("doing_storage", Context.MODE_PRIVATE);
            ObjectOutputStream doingOOS = new ObjectOutputStream(doingFOS);
            doingOOS.writeObject(Storage.doingQueue);
            doingOOS.flush();
            doingOOS.close();
            doingFOS.close();

        }catch(Exception e){

            Log.e("InternalStorage", e.getMessage());

        }

    }

}
