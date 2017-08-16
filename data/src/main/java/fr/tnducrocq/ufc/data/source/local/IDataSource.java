package fr.tnducrocq.ufc.data.source.local;

import java.util.List;

import fr.tnducrocq.ufc.data.source.remote.IRepository;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */

public interface IDataSource<T> extends IRepository<T> {

    Observable<T> get(String id);

    boolean save(T item);

    boolean save(List<T> item);

}
