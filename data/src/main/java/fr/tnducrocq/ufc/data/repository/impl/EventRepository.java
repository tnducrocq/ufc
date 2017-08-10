package fr.tnducrocq.ufc.data.repository.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import fr.tnducrocq.ufc.data.entity.HasId;
import fr.tnducrocq.ufc.data.repository.IEventRepository;
import fr.tnducrocq.ufc.data.source.EventDataSource;
import fr.tnducrocq.ufc.data.source.qualifier.Local;
import fr.tnducrocq.ufc.data.source.qualifier.Remote;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Completable;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class EventRepository<F extends HasId> extends AbstractRepository<F> implements EventDataSource<F> {

    private final EventDataSource<F> localSource;
    private final IEventRepository<F> remoteSource;

    @Inject
    public EventRepository(@NonNull Context context, @NonNull @Local EventDataSource<F> localSource, @NonNull @Remote IEventRepository<F> remoteSource, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(context, schedulerProvider);
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    @Override
    public Observable<List<F>> get() {
        if (isNetworkConnection()) {
            return remoteSource.get()//
                    .doOnNext(list -> save(list))//
                    .doOnNext(list -> cacheData(list));
        }
        return localSource.get().doOnNext(list -> cacheData(list));
    }

    @Override
    public Observable<F> get(String id) {
        if (!isCached(id)) {
            return localSource.get(id)//
                    .doOnNext(item -> cache(item.id(), item));
        }
        return fromCache(id);
    }

    @Override
    public Observable<List<F>> getFuture(Date min) {
        return localSource.getFuture(min)//
                .doOnNext(list -> cacheData(list));
    }

    @Override
    public Observable<List<F>> getPast(Date max) {
        return localSource.getPast(max)//
                .doOnNext(list -> cacheData(list));
    }

    @Override
    public boolean save(F item) {
        return localSource.save(item);
    }

    @Override
    public boolean save(List<F> list) {
        if (list != null) {
            localSource.save(list);
            return true;
        }
        return false;
    }

    private void cacheData(List<F> list) {
        if (list != null) {
            for (F item : list) cache(item.id(), item);
        }
    }

    private void cache(F item) {
        if (item != null) {
            cache(item.id(), item);
        }
    }

    private void saveToDisk(F item) {
        if (item != null) {
            Completable.fromAction(() -> localSource.save(item))//
                    .subscribeOn(schedulerProvider.multi()).subscribe();
        }
    }

    private void inDisk(List<F> list) {
        Completable.fromCallable(() -> save(list))//
                .subscribeOn(schedulerProvider.multi())//
                .subscribe();
    }
}
