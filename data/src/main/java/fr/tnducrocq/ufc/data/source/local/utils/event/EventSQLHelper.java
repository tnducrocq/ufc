package fr.tnducrocq.ufc.data.source.local.utils.event;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * Created by tony on 10/08/2017.
 */

public class EventSQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "event.db";
    private static final int DATABASE_VERSION = 1;

    public EventSQLHelper(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EventContact.Tables.EVENTS + " (" + //
                EventContact.Events._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //
                EventContact.Events.EVENT_ID + " INTEGER," + //
                EventContact.Events.EVENT_DATE + " INTEGER," + //
                EventContact.Events.EVENT_SECONDARY_FEATURE_IMAGE + " TEXT," + //
                EventContact.Events.EVENT_TICKET_IMAGE + " TEXT," + //
                EventContact.Events.EVENT_TIME_ZONE_TEXT + " TEXT," + //
                EventContact.Events.EVENT_SHORT_DESCRIPTION + " TEXT," + //
                EventContact.Events.EVENT_DATEGMT + " TEXT," + //
                EventContact.Events.EVENT_END_EVENT_DATEGMT + " TEXT," + //
                EventContact.Events.EVENT_TICKETURL + " TEXT," + //
                EventContact.Events.EVENT_BASE_TITLE + " TEXT," + //
                EventContact.Events.EVENT_TITLE_TAG_LINE + " TEXT," + //
                EventContact.Events.EVENT_TWITTER_HASHTAG + " TEXT," + //
                EventContact.Events.EVENT_FEATURE_IMAGE + " TEXT," + //
                EventContact.Events.EVENT_TIME_TEXT + " TEXT," + //
                EventContact.Events.EVENT_TICKET_GENERAL_SALE_TEXT + " TEXT," + //
                EventContact.Events.EVENT_SUBTITLE + " TEXT," + //
                EventContact.Events.EVENT_STATUS + " TEXT," + //
                EventContact.Events.EVENT_ISPPVEVENT + " INTEGER," + //
                EventContact.Events.EVENT_CORNER_AUDIO_AVAILABLE + " INTEGER," + //
                EventContact.Events.EVENT_LAST_MODIFIED + " TEXT," + //
                EventContact.Events.EVENT_URL_NAME + " TEXT," + //
                EventContact.Events.EVENT_CREATED + " TEXT," + //
                EventContact.Events.EVENT_ARENA + " TEXT," + //
                EventContact.Events.EVENT_LOCATION + " TEXT," + //
                EventContact.Events.EVENT_FM_FNT_FEED_URL + " TEXT," + //
                EventContact.Events.EVENT_MAIN_EVENT_FIGHTER1_ID + " INTEGER," + //
                EventContact.Events.EVENT_MAIN_EVENT_FIGHTER2_ID + " INTEGER," + //
                " UNIQUE (" + EventContact.Events.EVENT_ID + ") ON CONFLICT REPLACE)" //
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + EventContact.Tables.EVENTS);
        onCreate(db);
    }

    static void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
