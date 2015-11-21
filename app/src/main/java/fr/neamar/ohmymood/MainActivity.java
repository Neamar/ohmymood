package fr.neamar.ohmymood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import fr.neamar.ohmymood.broadcast.DisplayNotification;
import fr.neamar.ohmymood.db.DBHelper;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNotification();
                Snackbar.make(view, "Notification vill be displayed in a few seconds.", Snackbar.LENGTH_LONG).show();
            }
        });

        ArrayList<Pair<Integer, Integer>> moods = DBHelper.getMoods(this);

        if(moods.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for(Pair<Integer, Integer> mood : moods) {
                builder.append(mood.first + " : " + mood.second + "\n");
            }

            ((TextView) findViewById(R.id.recap)).setText(builder.toString());
        }

    }

    public void displayNotification() {
        Intent notificationIntent = new Intent(DisplayNotification.BROADCAST);

        sendBroadcast(notificationIntent);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}