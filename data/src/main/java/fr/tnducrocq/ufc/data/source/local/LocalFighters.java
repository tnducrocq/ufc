package fr.tnducrocq.ufc.data.source.local;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDao;
import fr.tnducrocq.ufc.data.entity.fighter.WeightCategory;
import fr.tnducrocq.ufc.data.entity.fighter.WeightCategoryConverter;
import rx.Observable;

public class LocalFighters extends AbstractLocal<Fighter> implements FighterDataSource {

    public LocalFighters(App application) {
        super(application);
    }

    @Override
    public Observable<List<Fighter>> get(WeightCategory type) {
        return Observable.create(subscriber -> {
            try {
                QueryBuilder<Fighter> queryBuilder = application.getSession().getFighterDao().queryBuilder();
                WeightCategoryConverter converter = new WeightCategoryConverter();
                List<Fighter> fighterList = queryBuilder
                        .where(FighterDao.Properties.WeightClass.eq(converter.convertToDatabaseValue(type)))
                        .orderAsc(FighterDao.Properties.Rank)
                        .list();
                subscriber.onNext(fighterList);
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
                QueryBuilder<Fighter> queryBuilder = application.getSession().getFighterDao().queryBuilder();
                List<Fighter> fighterList = queryBuilder
                        .where(FighterDao.Properties.TitleHolder.eq(true))
                        .orderAsc(FighterDao.Properties.WeightClass)
                        .list();
                subscriber.onNext(fighterList);
            } catch (Exception e) {
                subscriber.onError(e);
            }
            subscriber.onCompleted();
        });
    }


    @Override
    public boolean save(List<Fighter> list) {
        application.getSession().getFighterDao().insertOrReplaceInTx(list);
        return true;
    }

    @Override
    public boolean save(Fighter item) {
        application.getSession().getFighterDao().insertOrReplace(item);
        return true;
    }

    public Observable<Fighter> get(String id) {
        return Observable.create(subscriber -> {
            Fighter fighter = application.getSession().getFighterDao().queryBuilder().where(FighterDao.Properties.Id.eq(id)).unique();
            subscriber.onNext(fighter);
            subscriber.onCompleted();
        });
    }

}
