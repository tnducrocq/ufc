package fr.tnducrocq.ufc.data.source.local;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import rx.Observable;

public interface FighterDataSource extends IDataSource<Fighter> {

    Observable<List<Fighter>> get(WeightCategory type);

    Observable<List<Fighter>> getChampions();

}
