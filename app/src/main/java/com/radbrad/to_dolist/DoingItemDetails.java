package com.radbrad.to_dolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.IOException;

public class DoingItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_item_details);

        final TextView title = findViewById(R.id.doing_title_textView);
        TextView date = findViewById(R.id.doing_date_textView);
        TextView desc = findViewById(R.id.doing_desc_textView);
        Button doneBTN = findViewById(R.id.to_doing_done_button);

        title.setText(Storage.getCurrTask().getTitle());
        date.setText(Storage.getCurrTask().getDay() + ", "
                + Storage.getCurrTask().getYear() + ", "
                + Storage.getCurrTask().getHour() + ":"
                + Storage.getCurrTask().getMin());
        desc.setText(Storage.getCurrTask().getDesc());

        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //removing Task from Queue and saving change
                try{

                    Storage.removeFromDoing(Storage.getCurrTask());
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

}
