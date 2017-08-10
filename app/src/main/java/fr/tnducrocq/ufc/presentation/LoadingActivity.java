package fr.tnducrocq.ufc.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import java.util.List;

import fr.tnducrocq.ufc.data.Provider;
import fr.tnducrocq.ufc.data.entity.event.Events;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func2;

public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "LoadingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        App mApplication = ((App) getApplicationContext());
        Observable<List<Fighter>> obsFighters = mApplication.fighterRepository.get();

        Observable<Events> obsEvents = Provider.getInstance().requestEvents()//
                .doOnNext(new Action1<Events>() {
                    @Override
                    public void call(Events events) {
                        System.out.println("call(Events " + events + ")");
                    }
                });

        Observable.zip(obsEvents, obsFighters, new Func2<Events, List<Fighter>, Pair<Events, List<Fighter>>>() {
            @Override
            public Pair<Events, List<Fighter>> call(Events events, List<Fighter> fighters) {
                return new Pair<Events, List<Fighter>>(events, fighters);
            }
        }).subscribeOn(mApplication.schedulerProvider.multi()).//
                observeOn(mApplication.schedulerProvider.ui()).//
                subscribe(new Observer<Pair<Events, List<Fighter>>>() {

            Pair<Events, List<Fighter>> mValue;
            Throwable mError;

            @Override
            public void onCompleted() {
                finish();
                Intent intent = MainActivity.newIntent(LoadingActivity.this);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable e) {
                mError = e;
            }

            @Override
            public void onNext(Pair<Events, List<Fighter>> pair) {
                mValue = pair;
            }
        });


       /* Subscription mSubscription = Observable.zip(obsEvents, obsFighters, new Func2<Events, Fighters, Pair<Events, Fighters>>() {
            @Override
            public Pair<Events, Fighters> call(Events events, Fighters fighters) {
                return new Pair<Events, Fighters>(events, fighters);
            }
        }).subscribeOn(Schedulers.newThread())//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe(new Observer<Pair<Events, Fighters>>() {
                    Pair<Events, Fighters> mValue;

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted()");

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError()");

                    }

                    @Override
                    public void onNext(Pair<Events, Fighters> pair) {
                        System.out.println("onNext()");
                        mValue = pair;
                    }
                });*/

    }
}
