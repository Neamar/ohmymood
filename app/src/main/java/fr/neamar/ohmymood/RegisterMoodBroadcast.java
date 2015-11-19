package fr.neamar.ohmymood;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import fr.neamar.ohmymood.db.DBHelper;

public class RegisterMoodBroadcast extends BroadcastReceiver {
    public static String TAG = "RegisterMoodBroadcast";

    @Override
    public void onReceive(Context context, Intent intent) {
        int moodId = intent.getIntExtra("mood", -1);
        Toast.makeText(context, "GOTCHA" + moodId, Toast.LENGTH_SHORT).show();

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.NOTIFICATION_ID);

        Log.i(TAG, "Current mood: " + moodId);

        DBHelper.insertMood(context, moodId);
    }
}