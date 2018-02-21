package com.radbrad.to_dolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class ToDoItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item_details);

        final TextView title = findViewById(R.id.to_do_title_textView);
        TextView date = findViewById(R.id.to_do_date_textView);
        TextView desc = findViewById(R.id.to_do_desc_textView);
        Button doingBTN = findViewById(R.id.to_do_doing_button);

        title.setText(Storage.getCurrTask().getTitle());
        date.setText(Storage.getCurrTask().getDay() + ", "
            + Storage.getCurrTask().getYear() + ", "
            + Storage.getCurrTask().getHour() + ":"
            + Storage.getCurrTask().getMin());
        desc.setText(Storage.getCurrTask().getDesc());

        doingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    Storage.markDoing(Storage.getCurrTask());
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
