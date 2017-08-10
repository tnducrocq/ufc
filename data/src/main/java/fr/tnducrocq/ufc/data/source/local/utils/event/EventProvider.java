package fr.tnducrocq.ufc.data.source.local.utils.event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.source.local.utils.QueryBuilder;

/**
 * Created by tony on 10/08/2017.
 */

public class EventProvider {

    private SQLiteDatabase db;
    private EventSQLHelper helper;

    public EventProvider(Context context) {
        helper = new EventSQLHelper(context);
    }


    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public int deleteAll() {
        return db.delete(EventContact.Tables.EVENTS, null, null);
    }

    public long insert(Event item) {
        ContentValues values = EventContact.convertToValues(item);
        return db.insert(EventContact.Tables.EVENTS, null, values);
    }

    public void saveAll(List<Event> items) {
        db.beginTransaction();
        db.delete(EventContact.Tables.EVENTS, null, null);
        for (Event item : items) {
            ContentValues values = EventContact.convertToValues(item);
            db.insert(EventContact.Tables.EVENTS, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public int update(Event item) {
        ContentValues values = EventContact.convertToValues(item);
        final QueryBuilder builder = new QueryBuilder().table(EventContact.Tables.EVENTS).where(EventContact.Events.EVENT_ID + "=?", item.id());
        return builder.update(db, values);
    }

    public Event get(String id) {
        final QueryBuilder builder = new QueryBuilder().table(EventContact.Tables.EVENTS).where(EventContact.Events.EVENT_ID + "=?", id);
        Cursor cursor = builder.query(db, null, null);
        if (cursor.moveToFirst()) {
            return EventContact.convertToObject(cursor);
        }
        return null;
    }

    public List<Event> get() {
        final QueryBuilder builder = new QueryBuilder().table(EventContact.Tables.EVENTS);
        Cursor cursor = builder.query(db, null, null);
        if (cursor.moveToFirst()) {
            List<Event> fighterList = new ArrayList<>();
            do {
                fighterList.add(EventContact.convertToObject(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }

    public List<Event> getPastEvent(@NonNull Date max) {
        QueryBuilder builder = new QueryBuilder().table(EventContact.Tables.EVENTS);
        builder = builder.where(EventContact.Events.EVENT_DATE + "<?", Long.toString(max.getTime()));
        Cursor cursor = builder.query(db, null, EventContact.Events.EVENT_DATE + " DESC");
        if (cursor.moveToFirst()) {
            List<Event> fighterList = new ArrayList<>();
            do {
                fighterList.add(EventContact.convertToObject(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }

    public List<Event> getFutureEvent(@NonNull Date min) {
        QueryBuilder builder = new QueryBuilder().table(EventContact.Tables.EVENTS);
        builder = builder.where(EventContact.Events.EVENT_DATE + ">?", Long.toString(min.getTime()));
        Cursor cursor = builder.query(db, null, EventContact.Events.EVENT_DATE + " ASC");
        if (cursor.moveToFirst()) {
            List<Event> fighterList = new ArrayList<>();
            do {
                fighterList.add(EventContact.convertToObject(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }
}
