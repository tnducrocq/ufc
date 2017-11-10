package fr.tnducrocq.ufc.presentation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.presentation.app.App;
import rx.Observable;
import rx.Observer;

public class LoadingActivity extends AppCompatActivity {

    private static final String TAG = "LoadingActivity";
    private static final String LAST_REQUEST_TIME = "last-request-time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Observable<List<Fighter>> obsFighters = App.getInstance().getFighterRepository().get();
        Observable<List<Event>> obsEvents = App.getInstance().getEventRepository().get();

        Observable.zip(obsEvents, obsFighters, (events, fighters) -> {
            LoadingDatas datas = new LoadingDatas();
            datas.eventList = events;
            datas.fighterList = fighters;
            return datas;
        }).subscribeOn(App.getInstance().getSchedulerProvider().multi()).//
                observeOn(App.getInstance().getSchedulerProvider().ui()).//
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
                Log.e(TAG, "onError", e);
                Snackbar snackbar = Snackbar.make(LoadingActivity.this.findViewById(android.R.id.content), e.getLocalizedMessage(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onNext(LoadingDatas pair) {
                mValue = pair;
            }
        });
    }

    private boolean requestsIsNeeded() {
        SharedPreferences settings = getSharedPreferences(TAG, 0);
        long lastRequestTimeInMs = settings.getLong(LAST_REQUEST_TIME, -1);
        if (lastRequestTimeInMs == -1) {
            return true;
        }

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTimeInMillis(lastRequestTimeInMs);
        cal.add(Calendar.DATE, 1);
        boolean isNeeded = cal.getTime().before(GregorianCalendar.getInstance().getTime());
        return isNeeded;
    }

    static class LoadingDatas {
        List<Event> eventList;
        List<Fighter> fighterList;
    }
}
