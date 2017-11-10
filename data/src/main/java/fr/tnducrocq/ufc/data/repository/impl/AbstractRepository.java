package fr.tnducrocq.ufc.data.repository.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Calendar;
import java.util.List;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.HasId;
import fr.tnducrocq.ufc.data.source.local.IDataSource;
import fr.tnducrocq.ufc.data.source.remote.IRepository;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

abstract class AbstractRepository<T extends HasId, L extends IDataSource<T>, R extends IRepository<T>> implements IDataSource<T> {

    private static final int DEFAULT_CACHE_SIZE = 150; //max 150 items
    private static final int DEFAULT_CACHE_DURATION = 60; //20 minutes
    private static final int DEFAULT_NETWORK_EXPIRATION_DURATION = 60; //60 minutes

    protected App application;
    protected final BaseSchedulerProvider schedulerProvider;

    protected L localSource;
    protected R remoteSource;

    AbstractRepository(App application, BaseSchedulerProvider schedulerProvider) {
        this(application, schedulerProvider, DEFAULT_CACHE_SIZE, DEFAULT_CACHE_DURATION);
    }

    private AbstractRepository(App application, BaseSchedulerProvider schedulerProvider, int cacheSize, int expiresAfter) {
        this.application = application;
        this.schedulerProvider = schedulerProvider;
    }

    boolean isNetworkConnection() {
        ConnectivityManager manager = ConnectivityManager.class.cast(application.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    boolean expiration(String key) {
        SharedPreferences sharedPref = application.getSharedPreferences(application.getString(fr.tnducrocq.ufc.data.R.string.app_name), Context.MODE_PRIVATE);
        long lastTime = sharedPref.getLong(key, 0);
        if (lastTime == 0) return true;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(lastTime);
        cal.add(Calendar.MINUTE, getNetworkExpirationInMinutes());
        return cal.getTime().before(Calendar.getInstance().getTime());
    }

    void putNetworkCall(String key) {
        SharedPreferences sharedPref = application.getSharedPreferences(application.getString(fr.tnducrocq.ufc.data.R.string.app_name), Context.MODE_PRIVATE);
        sharedPref.edit().putLong(key, Calendar.getInstance().getTimeInMillis()).commit();
    }

    @Override
    public Observable<List<T>> get() {
        String networkKey = getClass().getSimpleName() + "->get()";
        if (isNetworkConnection() && expiration(networkKey)) {
            return remoteSource.get()//
                    .doOnNext(list -> save(list))//
                    .doOnNext(list -> putNetworkCall(networkKey));
        }
        return localSource.get();
    }

    @Override
    public Observable<T> get(String id) {
        return localSource.get(id);
    }

    @Override
    public boolean save(T item) {
        return localSource.save(item);
    }

    @Override
    public boolean save(List<T> list) {
        if (list != null) {
            localSource.save(list);
            return true;
        }
        return false;
    }

    protected int getNetworkExpirationInMinutes() {
        return DEFAULT_NETWORK_EXPIRATION_DURATION;
    }

}
