package fr.tnducrocq.ufc.data.source;

import java.util.List;

import fr.tnducrocq.ufc.data.repository.IFighterRepository;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import rx.Observable;

public interface FighterDataSource<T> extends IFighterRepository<T> {

    Observable<T> get(String id);

    Observable<List<T>> get(WeightCategory type);

    boolean save(T item);

    boolean save(List<T> item);

}
