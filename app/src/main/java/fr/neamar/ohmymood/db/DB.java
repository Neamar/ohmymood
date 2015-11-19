package fr.neamar.ohmymood.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by neamar on 19/11/15.
 */
public class DB extends SQLiteOpenHelper {
    final static String MOODS_TABLE = "moods";

    final static int DB_VERSION = 1;
    final static String DB_NAME = "ohmymood.s3db";
    Context context;

    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // Store the context for later use
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + MOODS_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, mood INTEGER NNOT NULL, date TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            switch (oldVersion) {
                case 1:
                    break;
            }
        }
    }
}