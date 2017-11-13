package com.radbrad.to_dolist;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

public class EntryActivity extends AppCompatActivity {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        final EditText titleET = (EditText)findViewById(R.id.title_editText);
        final TextView dateTV = (TextView)findViewById(R.id.dateTextView);
        final TextView timeTV = (TextView)findViewById(R.id.timeTextView);
        final EditText descET = (EditText)findViewById(R.id.desc_editText);
        Button submitBTN = (Button)findViewById(R.id.submit_button);

        dateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConstraintLayout entryLayout = (ConstraintLayout)findViewById(R.id.entry_layout);

                LayoutInflater inflate = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflate.inflate(R.layout.date_entry_window, null);

                final int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupWindow.showAtLocation(entryLayout, Gravity.CENTER, 0, 0);

                final DatePicker dp = (DatePicker)popupView.findViewById(R.id.datePicker);

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                        year = dp.getYear();
                        month = dp.getMonth();
                        day = dp.getDayOfMonth();

                        dateTV.setText(day + "-" + month + "-" + year);

                    }
                });

            }
        });

        timeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConstraintLayout entryLayout = (ConstraintLayout)findViewById(R.id.entry_layout);

                LayoutInflater inflate = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflate.inflate(R.layout.time_entry_window, null);

                int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupWindow.showAtLocation(entryLayout, Gravity.CENTER, 0, 0);

                final TimePicker timePicker = (TimePicker)popupView.findViewById(R.id.timePicker);

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                        hour = timePicker.getHour();
                        minute = timePicker.getMinute();

                        timeTV.setText(hour + ":" + minute);

                    }
                });

            }
        });

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = titleET.getText().toString();
                String desc = descET.getText().toString();

                Task task = new Task(title, desc, year , month, day, hour, minute);
                Storage.todoQueue.add(task);

                finish();

            }
        });

    }
}
