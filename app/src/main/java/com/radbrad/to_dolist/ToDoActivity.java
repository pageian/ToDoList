package com.radbrad.to_dolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        Storage.setCurrTask(new Task());

        ListView lv = (ListView)findViewById(R.id.to_do_listView);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, R.id.item_title_textView, Storage.getTodoQueue().titles);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String currTitle = ((TextView)view.findViewById(R.id.item_title_textView)).getText().toString();
                Storage.setCurrTask(Storage.getTodoQueue().get(currTitle));

                Intent intent = new Intent(getApplicationContext(), ToDoItemDetails.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
