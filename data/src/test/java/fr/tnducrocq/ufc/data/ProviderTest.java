package fr.tnducrocq.ufc.data;

import org.junit.Test;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;

/**
 * Created by tony on 07/08/2017.
 */

public class ProviderTest {

    public static final String TAG = "ProviderTest";

    @Test
    public void requestEventFights() throws Exception {
        List<EventFight> eventFightList = Provider.getInstance().requestEventFights(635395);
        for (EventFight event : eventFightList) {
            System.out.println(event.toString());
        }
    }

    @Test
    public void requestFighters() throws Exception {
        Observable<Fighters> fighters = Provider.getInstance().requestFighters();
        Subscription mSubscription = fighters.subscribeOn(Schedulers.immediate())//
                .observeOn(Schedulers.immediate())//
                .subscribe(new Observer<Fighters>() {
                    Fighters mFighters;

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted()");
                        for (Fighter fighter : mFighters.values) {
                            System.out.println(fighter.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError()");

                    }

                    @Override
                    public void onNext(Fighters fighters) {
                        System.out.println("onNext()");
                        mFighters = fighters;
                    }
                });


    }

}
