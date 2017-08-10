package fr.tnducrocq.ufc.data.source.local;

import android.content.Context;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.source.FighterDataSource;
import fr.tnducrocq.ufc.data.source.local.utils.fighter.FighterProvider;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import rx.Observable;

public class LocalFighters extends AbstractLocal<Fighter, FighterProvider> implements FighterDataSource {

    public LocalFighters(Context context) {
        super(context, new FighterProvider(context));
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
