package fr.neamar.ohmymood.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by neamar on 19/11/15.
 */
public class DBHelper {
    private static SQLiteDatabase getDB(Context context) {
        DB dbScheme = new DB(context);
        return dbScheme.getReadableDatabase();
    }

    public static void insertMood(Context context, int moodId) {
        SQLiteDatabase db = getDB(context);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat sqlFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = sqlFormatter.format(cal.getTime());

        ContentValues values = new ContentValues();
        values.put("mood", moodId);
        values.put("date", date);

        db.insert(DB.MOODS_TABLE, null, values);
        db.close();
    }

    public static ArrayList<Pair<Integer, Integer>> getMoods(Context context) {
        SQLiteDatabase db = getDB(context);

        ArrayList<Pair<Integer, Integer>> moods = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT mood, COUNT(*) AS count FROM " + DB.MOODS_TABLE + " GROUP BY mood", null);
        if (c.moveToFirst()) {
            do {
                moods.add(new Pair<Integer, Integer>(c.getInt(0), c.getInt(1)));
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return moods;
    }
}
