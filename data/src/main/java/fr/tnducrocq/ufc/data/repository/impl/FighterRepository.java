package fr.tnducrocq.ufc.data.repository.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.source.remote.IRepository;
import fr.tnducrocq.ufc.data.source.local.FighterDataSource;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class FighterRepository extends AbstractRepository<Fighter, FighterDataSource, IRepository<Fighter>> implements FighterDataSource {

    public FighterRepository(@NonNull Context context, @NonNull FighterDataSource localSource, @NonNull IRepository<Fighter> remoteSource, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(context, schedulerProvider);
        this.localSource = localSource;
        this.remoteSource = remoteSource;
    }

    @Override
    public Observable<List<Fighter>> get(WeightCategory type) {
        return localSource.get(type).doOnNext(this::cacheData);
    }
}
