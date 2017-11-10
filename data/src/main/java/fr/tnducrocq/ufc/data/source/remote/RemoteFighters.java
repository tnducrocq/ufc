package fr.tnducrocq.ufc.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDeserializer;
import fr.tnducrocq.ufc.data.utils.SwiftString;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by tony on 08/08/2017.
 */
@Singleton
public class RemoteFighters implements IRepository<Fighter> {

    private static final String API_URL = "http://ufc-data-api.ufc.com/api/v3";
    private static final SwiftString API_FIGHTERS = SwiftString.format(API_URL + "/fighters");
    private final Gson gson;

    @Inject
    public RemoteFighters() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Fighter.class, new FighterDeserializer());
        builder.excludeFieldsWithoutExposeAnnotation();
        gson = builder.create();
    }

    @Override
    public Observable<List<Fighter>> get() {
        return Observable.create(subscriber -> {
            try {
                String url = API_FIGHTERS.toString();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                List<Fighter> fighterList = new ArrayList<>();
                Collections.addAll(fighterList, gson.fromJson(response.body().charStream(), Fighter[].class));

                subscriber.onNext(fighterList);
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<Fighter> fetchDetail(@NonNull Fighter fighter) {
        return Observable.create(subscriber -> {
            try {
                String urlDetails = String.format("http://fr.ufc.com/fighter/%s-%s", fighter.getFirstName(), fighter.getLastName());
                Request requestDetails = new Request.Builder().url(urlDetails).get().build();
                OkHttpClient okHttpClient = new OkHttpClient();
                Response responseDetails = okHttpClient.newCall(requestDetails).execute();
                String html = responseDetails.body().string();
                fighter.fillWithHtml(html);

                fighter.setDetails(true);
                subscriber.onNext(fighter);
                subscriber.onCompleted();
            } catch (Exception e) {

                fighter.setDetails(false);
                subscriber.onNext(fighter);
                subscriber.onCompleted();
            }
        });
    }

}
