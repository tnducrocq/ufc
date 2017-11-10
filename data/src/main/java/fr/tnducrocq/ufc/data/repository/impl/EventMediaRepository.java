package fr.tnducrocq.ufc.data.repository.impl;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.App;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.data.entity.event.EventMedias;
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

public class EventMediaRepository extends AbstractRepository<EventMedias, IDataSource<EventMedias>, IRepository<EventMedias>> {

    public EventMediaRepository(@NonNull App application, @NonNull BaseSchedulerProvider schedulerProvider) {
        super(application, schedulerProvider);
    }

    public Observable<EventMedias> get(String eventId) {
        return getEventMedia(eventId);
    }

    private Observable<EventMedias> getEventMedia(String eventId) {
        return Observable.create(subscriber -> {
            try {
                String url = API_EVENT_MEDIAS.put("id", eventId).toString();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                Type listType = new TypeToken<ArrayList<EventMedia>>() {
                }.getType();
                List<EventMedia> eventList = new Gson().fromJson(response.body().charStream(), listType);

                EventMedias eventMedias = new EventMedias();
                eventMedias.setId(eventId);
                eventMedias.setMediaList(eventList);

                subscriber.onNext(eventMedias);
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        });
    }
}
