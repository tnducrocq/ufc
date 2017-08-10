package fr.tnducrocq.ufc.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.source.EventDataSource;
import fr.tnducrocq.ufc.data.source.local.utils.event.EventProvider;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */

public class LocalEvents implements EventDataSource<Event> {

    private EventProvider provider;

    @Inject
    public LocalEvents(Context context) {
        provider = new EventProvider(context);
        provider.open();
    }

    @Override
    public boolean save(@NonNull Event item) {

        Event fighter = provider.get(item.id());
        if (fighter == null) {
            provider.insert(item);
        } else {
            provider.update(item);
        }
        return true;
    }

    @Override
    public boolean save(@NonNull List<Event> items) {
        provider.saveAll(items);
        return true;
    }

    @Override
    public Observable<Event> get(String id) {
        return Observable.create(subscriber -> {
            Event fighter = provider.get(id);
            subscriber.onNext(fighter);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Event>> get() {
        return Observable.create(subscriber -> {
            List<Event> fighters = provider.get();
            subscriber.onNext(fighters);
            subscriber.onCompleted();
        });
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
