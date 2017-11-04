package com.radbrad.to_dolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DoingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing);

        String[] values = {"1", "2", "3"};

        ArrayList<String> list = new ArrayList<String>();

        for(int i = 0; i < values.length; i++){

            list.add(values[i]);

        }

        ListView lv = (ListView)findViewById(R.id.doing_listView);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, R.id.title_textView, list);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



            }
        });
    }
}
