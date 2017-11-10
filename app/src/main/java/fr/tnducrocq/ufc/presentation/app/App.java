package fr.tnducrocq.ufc.presentation.app;

import android.content.Context;

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

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    private Context context;
    private BaseSchedulerProvider schedulerProvider;
    private FighterRepository fighterRepository;
    private EventRepository eventRepository;
    private EventFightsRepository eventFightsRepository;
    private EventMediaRepository eventMediasRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        instance = this;
    }

    private void initializeComponent() {
        context = this;

        schedulerProvider = new SchedulerProvider();

        fighterRepository = new FighterRepository(this, new LocalFighters(this), new RemoteFighters(), schedulerProvider);

        eventRepository = new EventRepository(this, new LocalEvents(this), new RemoteEvents(), schedulerProvider);
        eventFightsRepository = new EventFightsRepository(this, schedulerProvider);
        eventMediasRepository = new EventMediaRepository(this, schedulerProvider);
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

    public EventFightsRepository getEventFightsRepository() {
        return eventFightsRepository;
    }

    public EventMediaRepository getEventMediasRepository() {
        return eventMediasRepository;
    }

}
