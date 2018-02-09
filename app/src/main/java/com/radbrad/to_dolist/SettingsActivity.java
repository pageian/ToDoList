package com.radbrad.to_dolist;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {

    private int minute;
    private int hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //getting notifTime
        minute = Storage.getNotifMin();
        hour = Storage.getNotifHour();

        final TextView time_tv = (TextView)findViewById(R.id.notif_time_textView);
        Button submit_button = (Button)findViewById(R.id.notif_submit_button);

        time_tv.setText(hour + ":" + minute);

        //selects time for notification
        time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConstraintLayout settingsLayout = (ConstraintLayout)findViewById(R.id.settings_layout);

                LayoutInflater inflate = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflate.inflate(R.layout.time_entry_window, null);

                final int width = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupWindow.showAtLocation(settingsLayout, Gravity.CENTER, 0, 0);

                final TimePicker timePicker = (TimePicker)popupView.findViewById(R.id.timePicker);

                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                        hour = timePicker.getHour();
                        minute = timePicker.getMinute();

                        time_tv.setText(hour + ":" + minute);

                    }
                });

            }
        });

        //submits and writes notification settings to File
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    Storage.setNotifMin(minute);
                    Storage.setNotifHour(hour);
                    Storage.writeNotifTimes(getApplicationContext());

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }catch(IOException e){

                    Log.e("InternalStorage", e.getMessage());

                }

            }
        });

    }
}
