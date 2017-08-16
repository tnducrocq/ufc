package fr.tnducrocq.ufc.data.repository.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.data.source.local.EventDataSource;
import fr.tnducrocq.ufc.data.source.remote.EventRemoteSource;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class EventRepository extends AbstractRepository<Event, EventDataSource, EventRemoteSource> implements EventDataSource, EventRemoteSource {

    public EventRepository(@NonNull Context context, @NonNull EventDataSource localSource, @NonNull EventRemoteSource remoteSource, @NonNull BaseSchedulerProvider schedulerProvider) {
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

    @Override
    public Observable<List<EventMedia>> getEventMedia(String eventId) {
        return remoteSource.getEventMedia(eventId);
    }

    @Override
    public Observable<List<EventFight>> getEventFight(String eventId) {
        return remoteSource.getEventFight(eventId);
    }
}
