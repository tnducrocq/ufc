package fr.tnducrocq.ufc.data.source.local;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventDao;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */
public class LocalEvents extends AbstractLocal<Event> implements EventDataSource {

    public LocalEvents(App application) {
        super(application);
    }

    @Override
    public Observable<List<Event>> getPast(Date max) {
        return Observable.create(subscriber -> {
            QueryBuilder<Event> queryBuilder = application.getSession().getEventDao().queryBuilder();
            List<Event> eventList = queryBuilder
                    .where(EventDao.Properties.EventDate.lt(max))
                    .orderDesc(EventDao.Properties.EventDate)
                    .list();
            subscriber.onNext(eventList);
            subscriber.onCompleted();
        });
    }

    @Override
    public Observable<List<Event>> getFuture(Date min) {
        return Observable.create(subscriber -> {
            QueryBuilder<Event> queryBuilder = application.getSession().getEventDao().queryBuilder();
            List<Event> eventList = queryBuilder
                    .where(EventDao.Properties.EventDate.gt(min))
                    .orderAsc(EventDao.Properties.EventDate)
                    .list();
            subscriber.onNext(eventList);
            subscriber.onCompleted();
        });
    }

    @Override
    public boolean save(List<Event> list) {
        application.getSession().getEventDao().insertOrReplaceInTx(list);
        return true;
    }

    @Override
    public boolean save(Event item) {
        application.getSession().getEventDao().insertOrReplaceInTx(item);
        return true;
    }
}