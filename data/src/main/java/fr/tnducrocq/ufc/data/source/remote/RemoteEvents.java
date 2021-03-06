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

import fr.tnducrocq.ufc.data.entity.event.Event;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by tony on 08/08/2017.
 */
@Singleton
public class RemoteEvents implements IRepository<Event> {

    private final Gson gson;

    @Inject
    public RemoteEvents() {
        gson = new GsonBuilder().create();
    }

    @Override
    public Observable<List<Event>> get() {
        return Observable.create(subscriber -> {
            try {
                String url = API_EVENTS.toString();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                Type listType = new TypeToken<ArrayList<Event>>() {
                }.getType();
                List<Event> eventList = gson.fromJson(response.body().charStream(), listType);
                subscriber.onNext(eventList);
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        });
    }
}
