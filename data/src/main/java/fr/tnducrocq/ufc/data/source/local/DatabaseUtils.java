package fr.tnducrocq.ufc.data.source.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import fr.tnducrocq.ufc.data.entity.event.Event;

import static android.provider.BaseColumns._ID;
import static fr.tnducrocq.ufc.data.source.local.utils.fighter.FighterContact.Fighters;


public final class DatabaseUtils {

    private static final String TAG = "DatabaseUtils";

    private DatabaseUtils() {
        throw new UnsupportedOperationException("Can't be created");
    }

    public static ContentValues convertToValues(Event item) {
        if (item == null) return null;
        ContentValues values = new ContentValues();
        values.put(_ID, item.id());
        values.put(Fighters.FIGHTER_ID, item.id());
        return values;
    }


    public static Event convertToEvent(Cursor cursor) {
        if (cursor == null) return null;
        Event event = new Event();

        return event;
    }

    public static String convertToJsonString(Object object, Type type) {
        if (object == null) return null;
        Gson gson = new Gson();
        return gson.toJson(object, type);
    }

    public static <T> T convertFromJsonString(String jsonString, Type type) {
        if (jsonString == null) return null;
        Gson gson = new Gson();
        return gson.fromJson(jsonString, type);
    }

}
