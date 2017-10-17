package fr.tnducrocq.ufc.data.source.local;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
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
                List<Fighter> temp = provider.get(type);
                List<Fighter> fighters = new ArrayList<>();
                for (Fighter f : temp) {
                    if ("Active".equalsIgnoreCase(f.getFighterStatus()) && f.get_rank() != null) {
                        fighters.add(f);
                    }
                }

                Collections.sort(fighters, (f1, f2) -> {
                    /*if (f1.getTitleHolder()) {
                        return -1;
                    }
                    if (f2.getTitleHolder()) {
                        return 1;
                    }*/
                    return Integer.compare(f1.getRank(), f2.getRank());
                });

                subscriber.onNext(fighters);
            } catch (Exception e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Fighter>> getChampions() {
        return Observable.create(subscriber -> {
            try {
                List<Fighter> fighters = provider.getChampions();
                subscriber.onNext(fighters);
            } catch (Exception e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        });
    }
}
