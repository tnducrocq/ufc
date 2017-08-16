package fr.tnducrocq.ufc.data.source.local;

import android.content.Context;

import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.source.local.utils.event.EventProvider;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */
public class LocalEvents extends AbstractLocal<Event, EventProvider> implements EventDataSource {

    public LocalEvents(Context context) {
        super(context, new EventProvider(context));
    }

    @Override
    public Observable<List<Event>> getPast(Date max) {
        return Observable.create(subscriber -> {
            List<Event> eventList = provider.getPastEvent(max);
            subscriber.onNext(eventList);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Event>> getFuture(Date min) {
        return Observable.create(subscriber -> {
            List<Event> eventList = provider.getFutureEvent(min);
            subscriber.onNext(eventList);
            subscriber.onCompleted();
        });
    }
}