package com.radbrad.to_dolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
                startActivity(intent);

            }
        });

        //reading Storage Files
        try {

            Storage.readQueues(getApplicationContext());
            Storage.readNotifTimes(getApplicationContext());

        }catch (ClassNotFoundException e){

            Log.e("InternalStorage", e.getMessage());

        }catch(IOException e){

            Log.e("InternalStorage", e.getMessage());

        }

        Intent backgroundIntent = new Intent(getApplicationContext(), BackgroundManager.class);
        getApplicationContext().startService(backgroundIntent);

        Button toDo = findViewById(R.id.to_do_button);
        final Button doing = findViewById(R.id.doing_button);

        toDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent toDoI = new Intent(getApplicationContext(), ToDoActivity.class);
                startActivity(toDoI);

            }
        });

        doing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent doingI = new Intent(getApplicationContext(), DoingActivity.class);
                startActivity(doingI);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(settingsIntent);

        }

        return super.onOptionsItemSelected(item);

    }

}
