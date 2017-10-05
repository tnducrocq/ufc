package fr.tnducrocq.ufc.data.source.remote;

import org.apache.commons.lang3.NotImplementedException;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDetails;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;

/**
 * Created by tony on 03/10/2017.
 */

public class RemoteFighterDetails implements IRepository<FighterDetails> {

    @Inject
    public RemoteFighterDetails() {
    }

    public Observable<FighterDetails> get(Fighter fighter) {
        return Observable.create(subscriber -> {
            try {
                String url = String.format("http://fr.ufc.com/fighter/%s-%s", fighter.getFirstName(), fighter.getLastName());
                Request request = new Request.Builder().url(url).get().build();
                OkHttpClient okHttpClient = new OkHttpClient();
                Response response = okHttpClient.newCall(request).execute();
                String html = response.body().string();

                FighterDetails details = FighterDetails.parse(html);
                details.setId(fighter.id());
                subscriber.onNext(details);
                subscriber.onCompleted();
            } catch (IOException e) {
                subscriber.onError(e);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<FighterDetails>> get() {
        throw new NotImplementedException("method useless");
    }
}
