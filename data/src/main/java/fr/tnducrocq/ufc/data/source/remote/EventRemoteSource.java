package fr.tnducrocq.ufc.data.source.remote;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */

public interface EventRemoteSource extends IRepository<Event> {

    public Observable<List<EventMedia>> getEventMedia(String eventId);

    public Observable<List<EventFight>> getEventFight(String eventId);
}
