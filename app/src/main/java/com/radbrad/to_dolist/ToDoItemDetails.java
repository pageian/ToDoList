package com.radbrad.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ToDoItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item_details);

        final TextView title = (TextView)findViewById(R.id.to_do_title_textView);
        TextView date = (TextView)findViewById(R.id.to_do_date_textView);
        TextView desc = (TextView)findViewById(R.id.to_do_desc_textView);
        Button switchBTN = (Button)findViewById(R.id.to_do_doing_button);

        title.setText(Storage.currTask.getTitle());
        date.setText(Storage.currTask.getDate() + "");
        desc.setText(Storage.currTask.getDesc());

        switchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task newTask = Storage.todoQueue.remove();
                Storage.doingQueue.add(newTask);



                finish();

            }
        });

    }
}
