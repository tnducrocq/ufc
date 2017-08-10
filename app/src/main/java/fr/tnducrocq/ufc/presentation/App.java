package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.repository.IRepository;
import fr.tnducrocq.ufc.data.repository.impl.EventRepository;
import fr.tnducrocq.ufc.data.repository.impl.FighterRepository;
import fr.tnducrocq.ufc.data.source.EventDataSource;
import fr.tnducrocq.ufc.data.source.FighterDataSource;
import fr.tnducrocq.ufc.data.source.local.LocalEvents;
import fr.tnducrocq.ufc.data.source.local.LocalFighters;
import fr.tnducrocq.ufc.data.source.remote.RemoteEvents;
import fr.tnducrocq.ufc.data.source.remote.RemoteFighters;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import fr.tnducrocq.ufc.data.utils.scheduler.SchedulerProvider;

/**
 * Created by tony on 08/08/2017.
 */

public class App extends MultiDexApplication {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    Context context;
    BaseSchedulerProvider schedulerProvider;
    FighterRepository fighterRepository;
    EventRepository eventRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        instance = this;
    }

    private void initializeComponent() {
        context = this;
        schedulerProvider = new SchedulerProvider();

        FighterDataSource localSource = new LocalFighters(context);
        IRepository<Fighter> remoteSource = new RemoteFighters();
        fighterRepository = new FighterRepository(context, localSource, remoteSource, schedulerProvider);

        EventDataSource localEvent = new LocalEvents(context);
        IRepository<Event> remoteEvent = new RemoteEvents();
        eventRepository = new EventRepository(context, localEvent, remoteEvent, schedulerProvider);
    }

    public Context getContext() {
        return context;
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
}
