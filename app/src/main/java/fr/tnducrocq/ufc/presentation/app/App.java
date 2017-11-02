package fr.tnducrocq.ufc.presentation.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import fr.tnducrocq.ufc.data.repository.impl.EventFightsRepository;
import fr.tnducrocq.ufc.data.repository.impl.EventMediaRepository;
import fr.tnducrocq.ufc.data.repository.impl.EventRepository;
import fr.tnducrocq.ufc.data.repository.impl.FighterDetailsRepository;
import fr.tnducrocq.ufc.data.repository.impl.FighterRepository;
import fr.tnducrocq.ufc.data.source.local.LocalEvents;
import fr.tnducrocq.ufc.data.source.local.LocalFighters;
import fr.tnducrocq.ufc.data.source.remote.RemoteEvents;
import fr.tnducrocq.ufc.data.source.remote.RemoteFighterDetails;
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

    public Context context;
    public BaseSchedulerProvider schedulerProvider;
    public FighterRepository fighterRepository;
    public EventRepository eventRepository;
    public EventFightsRepository eventFightsRepository;
    public EventMediaRepository eventMediasRepository;
    public FighterDetailsRepository fighterDetailsRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        instance = this;
    }

    private void initializeComponent() {
        context = this;
        schedulerProvider = new SchedulerProvider();

        fighterRepository = new FighterRepository(context, new LocalFighters(context), new RemoteFighters(), schedulerProvider);
        fighterDetailsRepository = new FighterDetailsRepository(context, new RemoteFighterDetails(), schedulerProvider);
        eventRepository = new EventRepository(context, new LocalEvents(context), new RemoteEvents(), schedulerProvider);
        eventFightsRepository = new EventFightsRepository(context, schedulerProvider);
        eventMediasRepository = new EventMediaRepository(context, schedulerProvider);
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

    public FighterDetailsRepository getFighterDetailsRepository() {
        return fighterDetailsRepository;
    }
}
