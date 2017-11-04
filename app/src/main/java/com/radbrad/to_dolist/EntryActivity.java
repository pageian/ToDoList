package com.radbrad.to_dolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final EditText titleET = (EditText)findViewById(R.id.title_editText);
        final EditText dateET = (EditText)findViewById(R.id.date_editText);
        final EditText descET = (EditText)findViewById(R.id.date_editText);
        Button submitBTN = (Button)findViewById(R.id.submit_button);

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = titleET.getText().toString();
                String desc = descET.getText().toString();
                String date = dateET.getText().toString();
                int yr = Integer.parseInt(date);
                int mth = Integer.parseInt(date);
                int day = Integer.parseInt(date);

                Task task = new Task(title, desc, yr , mth, day);
                Storage.todoQueue.add(task);

            }
        });

    }
}
