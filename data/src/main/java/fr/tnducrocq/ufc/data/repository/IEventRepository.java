package fr.tnducrocq.ufc.data.repository;

import java.util.List;

import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public interface IEventRepository<T> {
    Observable<List<T>> get();
}
