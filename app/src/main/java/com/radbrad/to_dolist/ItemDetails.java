package com.radbrad.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        TextView title = (TextView)findViewById(R.id.title_desc_textView);
        TextView date = (TextView)findViewById(R.id.date_desc_textView);
        TextView desc = (TextView)findViewById(R.id.desc_desc_textView);

        title.setText(Storage.currTask.getTitle());
        date.setText(Storage.currTask.getDate() + "");
        desc.setText(Storage.currTask.getDesc());

    }
}
