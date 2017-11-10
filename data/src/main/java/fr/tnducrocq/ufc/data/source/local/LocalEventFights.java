package fr.tnducrocq.ufc.data.source.local;

import org.greenrobot.greendao.query.QueryBuilder;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventFights;
import fr.tnducrocq.ufc.data.entity.event.EventFightsDao;
import rx.Observable;

/**
 * Created by tony on 10/08/2017.
 */
public class LocalEventFights extends AbstractLocal<EventFights> {

    public LocalEventFights(App application) {
        super(application);
    }

    public Observable<EventFights> get(String eventId) {
        return Observable.create(subscriber -> {
            try {
                QueryBuilder<EventFights> queryBuilder = application.getSession().getEventFightsDao().queryBuilder();
                EventFights eventFights = queryBuilder.where(EventFightsDao.Properties.EventId.eq(eventId)).unique();
                subscriber.onNext(eventFights);
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public boolean save(EventFights item) {
        application.getSession().getEventFightsDao().insertOrReplaceInTx(item);
        for (EventFight eventFight : item.getFightList()) {
            if (eventFight.get_result() != null) {
                long resultId = application.getSession().getEventFightResultDao().insertOrReplace(eventFight.get_result());
                eventFight.setResultId(resultId);
            }
            application.getSession().getEventFightDao().insertOrReplaceInTx(eventFight);
        }
        return true;
    }
}