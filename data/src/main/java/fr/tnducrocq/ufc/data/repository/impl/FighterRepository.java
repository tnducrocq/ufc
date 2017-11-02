package fr.tnducrocq.ufc.data.repository.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.source.local.FighterDataSource;
import fr.tnducrocq.ufc.data.source.remote.RemoteFighters;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class FighterRepository extends AbstractRepository<Fighter, FighterDataSource, RemoteFighters> implements FighterDataSource {

    public FighterRepository(@NonNull Context context, @NonNull FighterDataSource localSource, @NonNull RemoteFighters remoteSource, @NonNull BaseSchedulerProvider schedulerProvider) {
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

    @Override
    protected int getNetworkExpirationInMinutes() {
        return 24;
    }
}
