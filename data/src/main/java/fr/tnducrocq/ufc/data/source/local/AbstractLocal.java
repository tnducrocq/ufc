package fr.tnducrocq.ufc.data.source.local;

import java.util.List;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.HasId;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */
public abstract class AbstractLocal<T extends HasId> implements IDataSource<T> {

    protected App application;

    public AbstractLocal(App application) {
        this.application = application;
    }

    public Observable<T> get(String id) {
        return Observable.create(subscriber -> {
            subscriber.onNext(null);
            subscriber.onCompleted();
        });
    }

    public Observable<List<T>> get() {
        return Observable.create(subscriber -> {
            subscriber.onNext(null);
            subscriber.onCompleted();
        });
    }

    public boolean save(T item) {
        return false;
    }

    public boolean save(List<T> list) {
        for (T item : list) {
            save(item);
        }
        return true;
    }

}
