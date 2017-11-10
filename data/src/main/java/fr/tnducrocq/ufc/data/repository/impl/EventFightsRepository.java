package fr.tnducrocq.ufc.data.repository.impl;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventFightDao;
import fr.tnducrocq.ufc.data.entity.event.EventFights;
import fr.tnducrocq.ufc.data.source.local.LocalEventFights;
import fr.tnducrocq.ufc.data.source.remote.RemoteEventFights;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class EventFightsRepository extends AbstractRepository<EventFights, LocalEventFights, RemoteEventFights> {

    public EventFightsRepository(@NonNull App application, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(application, schedulerProvider);
        localSource = new LocalEventFights(application);
        remoteSource = new RemoteEventFights();
    }

    @Override
    public Observable<EventFights> get(String eventId) {
        String networkKey = getClass().getSimpleName() + "->get(eventId:" + eventId + ")";

        QueryBuilder<EventFight> queryBuilder = application.getSession().getEventFightDao().queryBuilder();
        List<EventFight> eventFightList = queryBuilder.where(EventFightDao.Properties.EventId.eq(eventId)).list();
        if (eventFightList != null && !eventFightList.isEmpty() && eventFightList.get(0).getResult() != null && eventFightList.get(0).getResult().getMethod() != null) {
            return Observable.create(subscriber -> {
                EventFights eventFights = new EventFights();
                eventFights.setEventId(eventId);
                eventFights.addEventFights(eventFightList);

                subscriber.onNext(eventFights);
                subscriber.onCompleted();
            });
        }

        if (isNetworkConnection() && expiration(networkKey)) {
            return remoteSource.get(eventId)//
                    .doOnNext(item -> save(item))//
                    .doOnNext(item -> putNetworkCall(networkKey));
        }
        return localSource.get(eventId);
    }
}
