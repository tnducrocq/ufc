package fr.tnducrocq.ufc.data.source.local.utils.fighter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.source.local.utils.QueryBuilder;
import fr.tnducrocq.ufc.data.utils.WeightCategory;

/**
 * Created by tony on 08/08/2017.
 */
public class FighterProvider {

    private SQLiteDatabase db;
    private FighterSQLHelper helper;

    public FighterProvider(Context context) {
        helper = new FighterSQLHelper(context);
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
        return db.delete(FighterContact.Tables.FIGHTERS, null, null);
    }

    public long insert(Fighter item) {
        ContentValues values = FighterContact.convertToValues(item);
        return db.insert(FighterContact.Tables.FIGHTERS, null, values);
    }

    public void saveAll(List<Fighter> items) {
        db.beginTransaction();
        db.delete(FighterContact.Tables.FIGHTERS, null, null);
        for (Fighter item : items) {
            ContentValues values = FighterContact.convertToValues(item);
            db.insert(FighterContact.Tables.FIGHTERS, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public int update(Fighter item) {
        ContentValues values = FighterContact.convertToValues(item);
        final QueryBuilder builder = new QueryBuilder().table(FighterContact.Tables.FIGHTERS).where(FighterContact.Fighters.FIGHTER_ID + "=?", item.id());
        return builder.update(db, values);
    }

    public Fighter get(String id) {
        final QueryBuilder builder = new QueryBuilder().table(FighterContact.Tables.FIGHTERS).where(FighterContact.Fighters.FIGHTER_ID + "=?", id);
        Cursor cursor = builder.query(db, null, null);
        if (cursor.moveToFirst()) {
            return FighterContact.convertToFighter(cursor);
        }
        return null;
    }

    public List<Fighter> get() {
        final QueryBuilder builder = new QueryBuilder().table(FighterContact.Tables.FIGHTERS);
        Cursor cursor = builder.query(db, null, null);
        if (cursor.moveToFirst()) {
            List<Fighter> fighterList = new ArrayList<>();
            do {
                fighterList.add(FighterContact.convertToFighter(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }

    public List<Fighter> get(WeightCategory type) {
        final QueryBuilder builder = new QueryBuilder().table(FighterContact.Tables.FIGHTERS).where(FighterContact.Fighters.FIGHTER_WEIGHT_CLASS + "=?", type.toString());
        Cursor cursor = builder.query(db, null, null);
        if (cursor.moveToFirst()) {
            List<Fighter> fighterList = new ArrayList<>();
            do {
                fighterList.add(FighterContact.convertToFighter(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }
}
