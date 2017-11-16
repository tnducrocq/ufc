package fr.tnducrocq.ufc.presentation.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDeserializer;
import fr.tnducrocq.ufc.data.repository.impl.EventFightsRepository;
import fr.tnducrocq.ufc.data.repository.impl.EventMediaRepository;
import fr.tnducrocq.ufc.data.repository.impl.EventRepository;
import fr.tnducrocq.ufc.data.repository.impl.FighterRepository;
import fr.tnducrocq.ufc.data.source.local.LocalEvents;
import fr.tnducrocq.ufc.data.source.local.LocalFighters;
import fr.tnducrocq.ufc.data.source.remote.RemoteEvents;
import fr.tnducrocq.ufc.data.source.remote.RemoteFighters;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import fr.tnducrocq.ufc.data.utils.scheduler.SchedulerProvider;
/**
 * Created by tony on 08/08/2017.
 */

public class App extends fr.tnducrocq.ufc.data.App {

    private static final String TAG = "App";
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private BaseSchedulerProvider schedulerProvider;
    private FighterRepository fighterRepository;
    private EventRepository eventRepository;
    private EventFightsRepository eventFightsRepository;
    private EventMediaRepository eventMediasRepository;

    @Override
    public void onCreate() {
        instance = this;

        super.onCreate();
        initializeComponent();

        SharedPreferences sharedPref = getSharedPreferences(getString(fr.tnducrocq.ufc.data.R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedPref.getBoolean("data-installed", false)) {
            if (installFighters() && installEvents()) {
                sharedPref.edit().putBoolean("data-installed", true).commit();
            }
        }
    }

    private void initializeComponent() {
        schedulerProvider = new SchedulerProvider();
        fighterRepository = new FighterRepository(this, new LocalFighters(this), new RemoteFighters(), schedulerProvider);
        eventRepository = new EventRepository(this, new LocalEvents(this), new RemoteEvents(), schedulerProvider);
        eventFightsRepository = new EventFightsRepository(this, schedulerProvider);
        eventMediasRepository = new EventMediaRepository(this, schedulerProvider);
    }

    private boolean installFighters() {
        try (InputStream is = getResources().getAssets().open("fighters.json");
             Reader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<ArrayList<Fighter>>() {
            }.getType();

            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Fighter.class, new FighterDeserializer());
            builder.excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder.create();
            List<Fighter> fighterList = gson.fromJson(reader, listType);
            for (Fighter item : fighterList) {
                getSession().getFighterDao().insert(item);
            }
        } catch (Exception e) {
            Log.e(TAG, "error parsing fighter", e);
            return false;
        }
        return true;
    }

    private boolean installEvents() {
        try (InputStream is = getResources().getAssets().open("events.json");
             Reader reader = new InputStreamReader(is)) {
            Type listType = new TypeToken<ArrayList<Event>>() {
            }.getType();
            Gson gson = new GsonBuilder().create();
            List<Event> eventList = gson.fromJson(reader, listType);
            for (Event item : eventList) {
                getSession().getEventDao().insert(item);
            }
        } catch (Exception e) {
            Log.e(TAG, "error parsing event", e);
            return false;
        }
        return true;
    }

    public Context getContext() {
        return this;
    }

    public BaseSchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public FighterRepository getFighterRepository() {
        return fighterRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }

    public EventFightsRepository getEventFightsRepository() {
        return eventFightsRepository;
    }

    public EventMediaRepository getEventMediasRepository() {
        return eventMediasRepository;
    }

}
