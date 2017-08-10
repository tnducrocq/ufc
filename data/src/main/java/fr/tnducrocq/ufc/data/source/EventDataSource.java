package fr.tnducrocq.ufc.data.source;

import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.repository.IEventRepository;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */

public interface EventDataSource<T> extends IEventRepository<T> {

    Observable<T> get(String id);

    Observable<List<T>> getPast(Date max);

    Observable<List<T>> getFuture(Date min);

    boolean save(T item);

    boolean save(List<T> item);

}
