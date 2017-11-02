package fr.tnducrocq.ufc.data.repository.impl;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventFights;
import fr.tnducrocq.ufc.data.source.local.IDataSource;
import fr.tnducrocq.ufc.data.source.remote.IRepository;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public class EventFightsRepository extends AbstractRepository<EventFights, IDataSource<EventFights>, IRepository<EventFights>> {

    public EventFightsRepository(@NonNull Context context, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(context, schedulerProvider);
    }

    @Override
    public Observable<EventFights> get(String eventId) {
        if (!isCached(eventId)) {
            return getEventFight(eventId)
                    .doOnNext(item -> cache(eventId, item));
        }
        return fromCache(eventId);
    }

    public Observable<EventFights> getEventFight(String eventId) {
        return Observable.create(subscriber -> {
            try {
                String url = API_EVENT_FIGHTS.put("id", eventId).toString();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                Type listType = new TypeToken<ArrayList<EventFight>>() {
                }.getType();
                List<EventFight> eventList = new Gson().fromJson(response.body().charStream(), listType);

                EventFights eventFights = new EventFights();
                eventFights.setId(eventId);
                eventFights.setFightList(eventList);

                subscriber.onNext(eventFights);
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        });
    }
}
