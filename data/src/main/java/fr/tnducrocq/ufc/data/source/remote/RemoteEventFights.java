package fr.tnducrocq.ufc.data.source.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventFights;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDeserializer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by tony on 08/08/2017.
 */
@Singleton
public class RemoteEventFights implements IRepository<EventFights> {

    private final Gson gson;

    @Inject
    public RemoteEventFights() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Fighter.class, new FighterDeserializer());
        builder.excludeFieldsWithoutExposeAnnotation();
        gson = builder.create();
    }

    @Override
    public Observable<List<EventFights>> get() {
        return null;
    }

    public Observable<EventFights> get(String eventId) {
        return Observable.create(subscriber -> {
            try {
                String url = API_EVENT_FIGHTS.put("id", eventId).toString();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                Type listType = new TypeToken<ArrayList<EventFight>>() {
                }.getType();
                List<EventFight> eventFightList = gson.fromJson(response.body().charStream(), listType);

                EventFights eventFights = new EventFights();
                eventFights.setEventId(eventId);
                eventFights.addEventFights(eventFightList);

                subscriber.onNext(eventFights);
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        });
    }
}
