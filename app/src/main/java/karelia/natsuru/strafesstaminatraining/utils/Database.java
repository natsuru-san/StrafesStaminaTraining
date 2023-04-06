//Author - Natsuru-san (natsuru-san@mail.com)
//Created 05.04.2023

package karelia.natsuru.strafesstaminatraining.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public static final String scoreName = "scores";
    public static final String settingsName = "settings";
    private static final String dbName = "app";
    private static final String scoreTable = "CREATE TABLE " + scoreName + "(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, raceInterval INTEGER, numFailPress INTEGER, numSuccessPress INTEGER, dateTime TEXT);";
    private static final String settingsTable = "CREATE TABLE " + settingsName + "(_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, param INTEGER, description TEXT);";
    private static final int version = 1;

    public Database(Context context) {
        super(context, dbName, null, version);
    }

    /*
    Uses when app has first running after install and invoking only once
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(settingsTable);
        db.execSQL("INSERT INTO settings(_id, param, description) VALUES(1, 60000, 'raceInterval');");
        db.execSQL(scoreTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Not yet implemented. This method don't using...
    }
}