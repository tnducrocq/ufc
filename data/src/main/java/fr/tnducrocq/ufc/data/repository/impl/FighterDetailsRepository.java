package fr.tnducrocq.ufc.data.repository.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDetails;
import fr.tnducrocq.ufc.data.source.local.IDataSource;
import fr.tnducrocq.ufc.data.source.remote.RemoteFighterDetails;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

/**
 * Created by tony on 03/10/2017.
 */

public class FighterDetailsRepository extends AbstractRepository<FighterDetails, IDataSource<FighterDetails>, RemoteFighterDetails> {

    public FighterDetailsRepository(@NonNull Context context, @NonNull RemoteFighterDetails remoteSource, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(context, schedulerProvider);
        this.remoteSource = remoteSource;
    }

    public Observable<FighterDetails> get(Fighter fighter) {
        if (!isCached(fighter.id())) {
            return remoteSource.get(fighter)//
                    .doOnNext(item -> cache(item.id(), item));
        }
        return fromCache(fighter.id());
    }
}
