package fr.tnducrocq.ufc.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.HasId;
import fr.tnducrocq.ufc.data.source.local.utils.AbstractProvider;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */
public class AbstractLocal<T extends HasId, P extends AbstractProvider<T, ?>> implements IDataSource<T> {

    protected P provider;

    public AbstractLocal(Context context, P provider) {
        this.provider = provider;
        if (this.provider != null) {
            this.provider.open();
        }
    }

    @Override
    public boolean save(@NonNull T item) {
        if (provider == null) {
            return false;
        }

        T fighter = provider.get(item.id());
        if (fighter == null) {
            provider.insert(item);
        } else {
            provider.update(item);
        }
        return true;
    }

    @Override
    public boolean save(@NonNull List<T> items) {
        if (provider == null) {
            return false;
        }

        provider.saveAll(items);
        return true;
    }

    @Override
    public Observable<T> get(String id) {
        if (provider == null) {
            return Observable.create(subscriber -> {
                subscriber.onCompleted();
            });
        }

        return Observable.create(subscriber -> {
            T fighter = provider.get(id);
            subscriber.onNext(fighter);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<T>> get() {
        if (provider == null) {
            return Observable.create(subscriber -> {
                subscriber.onCompleted();
            });
        }

        return Observable.create(subscriber -> {
            List<T> fighters = provider.get();
            subscriber.onNext(fighters);
            subscriber.onCompleted();
        });
    }
}
