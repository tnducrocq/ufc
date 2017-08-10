package fr.tnducrocq.ufc.data.source.local.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.HasId;

/**
 * Created by tony on 10/08/2017.
 */

public abstract class AbstractProvider<T extends HasId, H extends SQLiteOpenHelper> {

    protected SQLiteDatabase db;
    protected final H helper;

    public AbstractProvider(H helper) {
        this.helper = helper;
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
        return db.delete(getTable(), null, null);
    }

    public long insert(T item) {
        ContentValues values = convertToValues(item);
        return db.insert(getTable(), null, values);
    }

    public void saveAll(List<T> items) {
        db.beginTransaction();
        db.delete(getTable(), null, null);
        for (T item : items) {
            ContentValues values = convertToValues(item);
            db.insert(getTable(), null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public int update(T item) {
        ContentValues values = convertToValues(item);
        final QueryBuilder builder = new QueryBuilder().table(getTable()).where(getColumnId() + "=?", item.id());
        return builder.update(db, values);
    }

    public T get(String id) {
        final QueryBuilder builder = new QueryBuilder().table(getTable()).where(getColumnId() + "=?", id);
        Cursor cursor = builder.query(db, null, null);
        if (cursor.moveToFirst()) {
            return convertToObject(cursor);
        }
        return null;
    }

    public List<T> get() {
        final QueryBuilder builder = new QueryBuilder().table(getTable());
        Cursor cursor = builder.query(db, null, null);
        if (cursor.moveToFirst()) {
            List<T> fighterList = new ArrayList<>();
            do {
                fighterList.add(convertToObject(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }

    protected abstract String getTable();

    protected abstract String getColumnId();

    protected abstract ContentValues convertToValues(T item);

    protected abstract T convertToObject(Cursor cursor);
}
