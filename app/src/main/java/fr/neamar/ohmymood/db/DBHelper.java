package fr.neamar.ohmymood.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by neamar on 19/11/15.
 */
public class DBHelper {
    private static SQLiteDatabase db = null;

    private static SQLiteDatabase getDB(Context context) {
        if(db == null) {
            DB dbScheme = new DB(context);
            db = dbScheme.getReadableDatabase();
        }

        return db;
    }
    public static void insertMood(Context context, int moodId) {
        SQLiteDatabase db = getDB(context);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat sqlFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = sqlFormatter.format(cal.getTime());

        ContentValues values = new ContentValues();
        values.put("mood", moodId);
        values.put("date", date);

        db.insert(DB.MOODS_TABLE, null, values);
    }
}
