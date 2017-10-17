package fr.tnducrocq.ufc.data.source.local.utils.fighter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.source.local.utils.AbstractProvider;
import fr.tnducrocq.ufc.data.source.local.utils.QueryBuilder;
import fr.tnducrocq.ufc.data.utils.WeightCategory;

/**
 * Created by tony on 08/08/2017.
 */
public class FighterProvider extends AbstractProvider<Fighter, FighterSQLHelper> {

    public FighterProvider(Context context) {
        super(new FighterSQLHelper(context));
    }

    @Override
    protected String getTable() {
        return FighterContact.Tables.FIGHTERS;
    }

    @Override
    protected String getColumnId() {
        return FighterContact.FighterColumns.FIGHTER_ID;
    }

    @Override
    protected ContentValues convertToValues(Fighter item) {
        return FighterContact.convertToValues(item);
    }

    @Override
    protected Fighter convertToObject(Cursor cursor) {
        return FighterContact.convertToObject(cursor);
    }

    public List<Fighter> get(WeightCategory type) {
        final QueryBuilder builder = new QueryBuilder().table(FighterContact.Tables.FIGHTERS).where(FighterContact.Fighters.FIGHTER_WEIGHT_CLASS + "=?", type.toString());
        Cursor cursor = builder.query(db, null, null);
        if (cursor.moveToFirst()) {
            List<Fighter> fighterList = new ArrayList<>();
            do {
                fighterList.add(convertToObject(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }

    public List<Fighter> getChampions() {
        final QueryBuilder builder = new QueryBuilder().table(FighterContact.Tables.FIGHTERS).where(FighterContact.Fighters.FIGHTER_TITLE_HOLDER + "=?", "1");
        Cursor cursor = builder.query(db, null, FighterContact.Fighters.FIGHTER_WEIGHT_CLASS);
        if (cursor.moveToFirst()) {
            List<Fighter> fighterList = new ArrayList<>();
            do {
                fighterList.add(convertToObject(cursor));
            } while (cursor.moveToNext());

            return fighterList;
        }
        return null;
    }
}
