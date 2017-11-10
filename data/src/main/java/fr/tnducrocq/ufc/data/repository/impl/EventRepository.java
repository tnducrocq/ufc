package fr.tnducrocq.ufc.data.repository.impl;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.source.local.EventDataSource;
import fr.tnducrocq.ufc.data.source.remote.IRepository;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class EventRepository extends AbstractRepository<Event, EventDataSource, IRepository<Event>> implements EventDataSource, IRepository<Event> {

    public EventRepository(@NonNull App application, @NonNull EventDataSource localSource, @NonNull IRepository<Event> remoteSource, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(application, schedulerProvider);
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    @Override
    public Observable<List<Event>> getFuture(Date min) {
        return localSource.getFuture(min);
    }

    @Override
    public Observable<List<Event>> getPast(Date max) {
        return localSource.getPast(max);
    }

    @Override
    protected int getNetworkExpirationInMinutes() {
        return 0;
    }
}
