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

public class ToDoItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item_details);

        final TextView title = (TextView)findViewById(R.id.to_do_title_textView);
        TextView date = (TextView)findViewById(R.id.to_do_date_textView);
        TextView desc = (TextView)findViewById(R.id.to_do_desc_textView);
        Button doingBTN = (Button)findViewById(R.id.to_do_doing_button);

        title.setText(Storage.currTask.getTitle());
        date.setText(Storage.currTask.getDate() + "");
        desc.setText(Storage.currTask.getDesc());

        doingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task newTask = Storage.todoQueue.remove();
                Storage.doingQueue.add(newTask);

                try{

                    Storage.writeQueues(getApplicationContext());

                }catch(IOException e){

                    Log.e("InternalStorage", e.getMessage());

                }

                Intent intent = new Intent(getApplicationContext(), ToDoActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

}
