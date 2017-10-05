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

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDetails;
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
        gson = new GsonBuilder().create();
    }

    @Override
    public Observable<List<Fighter>> get() {
        return Observable.create(subscriber -> {
            try {
                String url = API_FIGHTERS.toString();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();

                Type listType = new TypeToken<ArrayList<Fighter>>() {
                }.getType();
                List<Fighter> fighterList = gson.fromJson(response.body().charStream(), listType);
                subscriber.onNext(fighterList);
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        });
    }


}
