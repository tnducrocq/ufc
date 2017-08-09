package fr.tnducrocq.ufc.data.repository.impl;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

import fr.tnducrocq.ufc.data.cache.CacheStore;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

abstract class AbstractRepository<T> {

    private static final int DEFAULT_CACHE_SIZE = 150; //max 150 items
    private static final int DEFAULT_CACHE_DURATION = 20; //20 minutes

    private Context context;
    private final CacheStore<String, T> cacheStore;
    protected final BaseSchedulerProvider schedulerProvider;

    AbstractRepository(Context context, BaseSchedulerProvider schedulerProvider) {
        this(context, schedulerProvider, DEFAULT_CACHE_SIZE, DEFAULT_CACHE_DURATION);
    }

    private AbstractRepository(Context context, BaseSchedulerProvider schedulerProvider, int cacheSize, int expiresAfter) {
        this.context = context;
        this.schedulerProvider = schedulerProvider;
        this.cacheStore = new CacheStore<>(CacheBuilder.newBuilder().maximumSize(cacheSize).expireAfterAccess(expiresAfter, TimeUnit.MINUTES).build());
    }

    boolean isCached(String key) {
        return cacheStore.isInCache(key);
    }

    void cache(String key, T data) {
        cacheStore.put(key, data);
    }

    Observable<T> fromCache(String id) {
        return cacheStore.getStream(id);
    }

    boolean isNetworkConnection() {
        ConnectivityManager manager = ConnectivityManager.class.cast(context.getSystemService(Context.CONNECTIVITY_SERVICE));
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
