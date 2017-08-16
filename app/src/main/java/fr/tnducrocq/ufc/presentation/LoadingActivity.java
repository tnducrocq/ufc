package fr.tnducrocq.ufc.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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


        Observable<List<Fighter>> obsFighters = App.getInstance().getFighterRepository().get();
        Observable<List<Event>> obsEvents = App.getInstance().getEventRepository().get();

        Observable.zip(obsEvents, obsFighters, new Func2<List<Event>, List<Fighter>, LoadingDatas>() {
            @Override
            public LoadingDatas call(List<Event> events, List<Fighter> fighters) {
                LoadingDatas datas = new LoadingDatas();
                datas.eventList = events;
                datas.fighterList = fighters;
                return datas;
            }
        }).subscribeOn(App.getInstance().schedulerProvider.multi()).//
                observeOn(App.getInstance().schedulerProvider.ui()).//
                subscribe(new Observer<LoadingDatas>() {

            LoadingDatas mValue;
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
            public void onNext(LoadingDatas pair) {
                mValue = pair;
            }
        });

    }

    static class LoadingDatas {
        List<Event> eventList;
        List<Fighter> fighterList;
    }
}
