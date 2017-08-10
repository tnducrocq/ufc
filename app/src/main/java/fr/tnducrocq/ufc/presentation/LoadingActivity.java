package fr.tnducrocq.ufc.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "LoadingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        Observable<List<Fighter>> obsFighters = App.getInstance().fighterRepository.get();
        Observable<List<Event>> obsEvents = App.getInstance().getEventRepository().get();

        Observable.zip(obsEvents, obsFighters, new Func2<List<Event>, List<Fighter>, Pair<List<Event>, List<Fighter>>>() {
            @Override
            public Pair<List<Event>, List<Fighter>> call(List<Event> events, List<Fighter> fighters) {
                return new Pair<List<Event>, List<Fighter>>(events, fighters);
            }
        }).subscribeOn(App.getInstance().schedulerProvider.multi()).//
                observeOn(App.getInstance().schedulerProvider.ui()).//
                subscribe(new Observer<Pair<List<Event>, List<Fighter>>>() {

            Pair<List<Event>, List<Fighter>> mValue;
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
            public void onNext(Pair<List<Event>, List<Fighter>> pair) {
                mValue = pair;
            }
        });

    }
}
