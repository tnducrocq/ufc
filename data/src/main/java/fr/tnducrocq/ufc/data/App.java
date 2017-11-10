package fr.tnducrocq.ufc.data;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import fr.tnducrocq.ufc.data.entity.fighter.DaoMaster;
import fr.tnducrocq.ufc.data.entity.fighter.DaoSession;

/**
 * Created by tony on 03/11/2017.
 */

public class App extends Application {

    private DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "ufc-db");
        Database db = helper.getWritableDb();
        session = new DaoMaster(db).newSession();
    }

    public DaoSession getSession() {
        return session;
    }
}
