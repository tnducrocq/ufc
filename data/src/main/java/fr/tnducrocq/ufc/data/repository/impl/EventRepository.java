package fr.tnducrocq.ufc.data.repository.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.repository.IRepository;
import fr.tnducrocq.ufc.data.source.EventDataSource;
import fr.tnducrocq.ufc.data.source.qualifier.Local;
import fr.tnducrocq.ufc.data.source.qualifier.Remote;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class EventRepository extends AbstractRepository<Event, EventDataSource, IRepository<Event>> implements EventDataSource {

    @Inject
    public EventRepository(@NonNull Context context, @NonNull @Local EventDataSource localSource, @NonNull @Remote IRepository<Event> remoteSource, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(context, schedulerProvider);
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    @Override
    public Observable<List<Event>> getFuture(Date min) {
        return localSource.getFuture(min)//
                .doOnNext(list -> cacheData(list));
    }

    @Override
    public Observable<List<Event>> getPast(Date max) {
        return localSource.getPast(max)//
                .doOnNext(list -> cacheData(list));
    }
}
