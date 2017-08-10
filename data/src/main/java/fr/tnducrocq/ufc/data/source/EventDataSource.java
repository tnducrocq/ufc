package fr.tnducrocq.ufc.data.source;

import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */

public interface EventDataSource extends IDataSource<Event> {

    Observable<List<Event>> getPast(Date max);

    Observable<List<Event>> getFuture(Date min);

}
