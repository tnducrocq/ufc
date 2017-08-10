package fr.tnducrocq.ufc.data.source.local.utils.event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.source.local.utils.AbstractProvider;
import fr.tnducrocq.ufc.data.source.local.utils.QueryBuilder;

/**
 * Created by tony on 10/08/2017.
 */

public class EventProvider extends AbstractProvider<Event, EventSQLHelper> {

    public EventProvider(Context context) {
        super(new EventSQLHelper(context));
    }

    @Override
    protected String getTable() {
        return EventContact.Tables.EVENTS;
    }

    @Override
    protected String getColumnId() {
        return EventContact.EventColumns.EVENT_ID;
    }

    @Override
    protected ContentValues convertToValues(Event item) {
        return EventContact.convertToValues(item);
    }

    @Override
    protected Event convertToObject(Cursor cursor) {
        return EventContact.convertToObject(cursor);
    }

    public List<Event> getPastEvent(@NonNull Date max) {
        QueryBuilder builder = new QueryBuilder().table(getTable());
        builder = builder.where(EventContact.Events.EVENT_DATE + "<?", Long.toString(max.getTime()));
        Cursor cursor = builder.query(db, null, EventContact.Events.EVENT_DATE + " DESC");
        if (cursor.moveToFirst()) {
            List<Event> fighterList = new ArrayList<>();
            do {
                fighterList.add(convertToObject(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }

    public List<Event> getFutureEvent(@NonNull Date min) {
        QueryBuilder builder = new QueryBuilder().table(getTable());
        builder = builder.where(EventContact.Events.EVENT_DATE + ">?", Long.toString(min.getTime()));
        Cursor cursor = builder.query(db, null, EventContact.Events.EVENT_DATE + " ASC");
        if (cursor.moveToFirst()) {
            List<Event> fighterList = new ArrayList<>();
            do {
                fighterList.add(convertToObject(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }
}
