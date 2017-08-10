package fr.tnducrocq.ufc.data.repository.impl;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import fr.tnducrocq.ufc.data.cache.CacheStore;
import fr.tnducrocq.ufc.data.entity.HasId;
import fr.tnducrocq.ufc.data.repository.IRepository;
import fr.tnducrocq.ufc.data.source.IDataSource;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Completable;
import rx.Observable;

abstract class AbstractRepository<T extends HasId, L extends IDataSource<T>, R extends IRepository<T>> implements IDataSource<T> {

    private static final int DEFAULT_CACHE_SIZE = 150; //max 150 items
    private static final int DEFAULT_CACHE_DURATION = 20; //20 minutes

    private Context context;
    private final CacheStore<String, T> cacheStore;
    protected final BaseSchedulerProvider schedulerProvider;

    protected L localSource;
    protected R remoteSource;

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


    @Override
    public Observable<List<T>> get() {
        if (isNetworkConnection()) {
            return remoteSource.get()//
                    .doOnNext(list -> save(list))//
                    .doOnNext(list -> cacheData(list));
        }
        return localSource.get().doOnNext(list -> cacheData(list));
    }

    @Override
    public Observable<T> get(String id) {
        if (!isCached(id)) {
            return localSource.get(id)//
                    .doOnNext(item -> cache(item.id(), item));
        }
        return fromCache(id);
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

    protected void cacheData(List<T> list) {
        if (list != null) {
            for (T item : list) cache(item.id(), item);
        }
    }

    protected void cache(T item) {
        if (item != null) {
            cache(item.id(), item);
        }
    }

    protected void saveToDisk(T item) {
        if (item != null) {
            Completable.fromAction(() -> localSource.save(item))//
                    .subscribeOn(schedulerProvider.multi()).subscribe();
        }
    }

    protected void inDisk(List<T> list) {
        Completable.fromCallable(() -> save(list))//
                .subscribeOn(schedulerProvider.multi())//
                .subscribe();
    }
}
