package fr.neamar.ohmymood;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class RegisterMoodBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int smileyId = intent.getIntExtra("smiley", -1);
        Toast.makeText(context, "GOTCHA" + smileyId, Toast.LENGTH_SHORT).show();

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.NOTIFICATION_ID);

        Log.e("WTF", "SMILEY " + smileyId);
    }
}