package fr.tnducrocq.ufc.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.utils.SwiftString;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tony on 25/07/2017.
 */
@Deprecated
public class Provider {

    private static final String API_URL = "http://ufc-data-api.ufc.com/api/v3";
    private static final SwiftString API_EVENTS = SwiftString.format(API_URL + "/events");
    private static final SwiftString API_FIGHTERS = SwiftString.format(API_URL + "/fighters");
    private static final SwiftString API_EVENTS_ID = SwiftString.format(API_URL + "/events/${id}");
    private static final SwiftString API_EVENT_FIGHTS = SwiftString.format(API_URL + "/events/${id}/fights");
    private static final SwiftString API_EVENT_MEDIAS = SwiftString.format(API_URL + "/events/${id}/media");

    final Gson gson;


    /**
     * Constructeur privé
     */
    private Provider() {
        gson = new GsonBuilder().create();
    }

    /**
     * Holder
     */
    private static class SingletonHolder {
        /**
         * Instance unique non préinitialisée
         */
        private final static Provider instance = new Provider();
    }

    /**
     * Point d'accès pour l'instance unique du singleton
     */
    public static Provider getInstance() {
        return SingletonHolder.instance;
    }

    public List<EventFight> requestEventFights(int id) {
        try {
            String url = API_EVENT_FIGHTS.put("id", Integer.toString(id)).toString();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            Type listType = new TypeToken<ArrayList<EventFight>>() {
            }.getType();
            List<EventFight> fightList = gson.fromJson(response.body().charStream(), listType);
            return fightList;
        } catch (IOException e) {
            return null;
        }
    }

}
