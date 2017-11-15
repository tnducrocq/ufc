package fr.tnducrocq.ufc.data.repository.impl;

import android.support.annotation.NonNull;

import java.util.List;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.WeightCategory;
import fr.tnducrocq.ufc.data.source.local.FighterDataSource;
import fr.tnducrocq.ufc.data.source.remote.RemoteFighters;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class FighterRepository extends AbstractRepository<Fighter, FighterDataSource, RemoteFighters> implements FighterDataSource {

    public FighterRepository(@NonNull App context, @NonNull FighterDataSource localSource, @NonNull RemoteFighters remoteSource, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(context, schedulerProvider);
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    @Override
    public Observable<List<Fighter>> get(WeightCategory type) {
        return localSource.get(type);
    }

    @Override
    public Observable<List<Fighter>> getChampions() {
        return localSource.getChampions();
    }

    public Observable<Fighter> fetchDetail(Fighter fighter) {
        //String networkKey = getClass().getSimpleName() + "->fetchDetail(fighterId:" + fighter.getId() + ")";
        if (fighter.hasDetails() || !isNetworkConnection() /*|| !expiration(networkKey)*/) {
            return Observable.create(subscriber -> {
                subscriber.onNext(fighter);
                subscriber.onCompleted();
            });
        }
        return remoteSource.fetchDetail(fighter)
                .doOnNext(item -> save(item))
                /*.doOnNext(item -> putNetworkCall(networkKey))*/;
    }

    @Override
    protected int getNetworkExpirationInMinutes() {
        return 60 * 24;
    }
}
