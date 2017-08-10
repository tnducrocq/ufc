package fr.tnducrocq.ufc.data.source.local.utils.fighter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * Created by tony on 04/08/2017.
 */

public class FighterSQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fighter.db";
    private static final int DATABASE_VERSION = 1;

    public FighterSQLHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + FighterContact.Tables.FIGHTERS + " (" + //
                FighterContact.Fighters._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //
                FighterContact.Fighters.FIGHTER_ID + " INTEGER NOT NULL," + //
                FighterContact.Fighters.FIGHTER_NICKNAME + " TEXT," + //
                FighterContact.Fighters.FIGHTER_WINS + " INTEGER," + //
                FighterContact.Fighters.FIGHTER_STATID + " INTEGER," + //
                FighterContact.Fighters.FIGHTER_LOSSES + " INTEGER," + //
                FighterContact.Fighters.FIGHTER_LAST_NAME + " TEXT," + //
                FighterContact.Fighters.FIGHTER_WEIGHT_CLASS + " TEXT," + //
                FighterContact.Fighters.FIGHTER_TITLE_HOLDER + " INTEGER," + //
                FighterContact.Fighters.FIGHTER_DRAWS + " INTEGER," + //
                FighterContact.Fighters.FIGHTER_FIRST_NAME + " TEXT," + //
                FighterContact.Fighters.FIGHTER_FIGHTER_STATUS + " TEXT," + //
                FighterContact.Fighters.FIGHTER_RANK + " TEXT," + //
                FighterContact.Fighters.FIGHTER_POUND_FOR_POUND_RANK + " TEXT," + //
                FighterContact.Fighters.FIGHTER_THUMBNAIL + " TEXT," + //
                FighterContact.Fighters.FIGHTER_BELT_THUMBNAIL + " TEXT," + //
                FighterContact.Fighters.FIGHTER_LEFT_FULL_BODY_IMAGE + " TEXT," + //
                FighterContact.Fighters.FIGHTER_RIGHT_FULL_BODY_IMAGE + " TEXT," + //
                FighterContact.Fighters.FIGHTER_PROFILE_IMAGE + " TEXT," + //
                FighterContact.Fighters.FIGHTER_LINK + " TEXT," + //
                " UNIQUE (" + FighterContact.Fighters.FIGHTER_ID + ") ON CONFLICT REPLACE)" //
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + FighterContact.Tables.FIGHTERS);
        onCreate(db);
    }

    static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
