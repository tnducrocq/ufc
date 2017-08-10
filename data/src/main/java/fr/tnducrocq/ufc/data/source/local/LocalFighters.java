package fr.tnducrocq.ufc.data.source.local;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.source.FighterDataSource;
import fr.tnducrocq.ufc.data.source.local.utils.fighter.FighterProvider;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import rx.Observable;

public class LocalFighters implements FighterDataSource<Fighter> {

    private FighterProvider provider;

    @Inject
    public LocalFighters(Context context) {
        provider = new FighterProvider(context);
        provider.open();
    }

    @Override
    public boolean save(@NonNull Fighter item) {

        Fighter fighter = provider.get(item.id());
        if (fighter == null) {
            provider.insert(item);
        } else {
            provider.update(item);
        }
        return true;
    }

    @Override
    public boolean save(@NonNull List<Fighter> items) {
        provider.saveAll(items);
        return true;
    }

    @Override
    public Observable<Fighter> get(String id) {
        return Observable.create(subscriber -> {
            Fighter fighter = provider.get(id);
            subscriber.onNext(fighter);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Fighter>> get() {
        return Observable.create(subscriber -> {
            List<Fighter> fighters = provider.get();
            subscriber.onNext(fighters);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Fighter>> get(WeightCategory type) {
        return Observable.create(subscriber -> {
            try {
                List<Fighter> fighters = provider.get(type);
                subscriber.onNext(fighters);
            } catch (Exception e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        });
    }
}
