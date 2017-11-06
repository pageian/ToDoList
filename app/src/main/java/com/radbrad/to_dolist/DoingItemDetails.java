package com.radbrad.to_dolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoingItemDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_item_details);

        final TextView title = (TextView)findViewById(R.id.doing_title_textView);
        TextView date = (TextView)findViewById(R.id.doing_date_textView);
        TextView desc = (TextView)findViewById(R.id.doing_desc_textView);
        Button switchBTN = (Button)findViewById(R.id.doing_button);

        title.setText(Storage.currTask.getTitle());
        date.setText(Storage.currTask.getDate() + "");
        desc.setText(Storage.currTask.getDesc());

        switchBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Storage.doingQueue.remove();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                finish();

            }
        });

    }
}
