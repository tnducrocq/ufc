package fr.tnducrocq.ufc.presentation.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import fr.tnducrocq.ufc.data.repository.impl.EventRepository;
import fr.tnducrocq.ufc.data.repository.impl.FighterDetailsRepository;
import fr.tnducrocq.ufc.data.repository.impl.FighterRepository;
import fr.tnducrocq.ufc.data.source.local.EventDataSource;
import fr.tnducrocq.ufc.data.source.local.LocalEvents;
import fr.tnducrocq.ufc.data.source.local.LocalFighters;
import fr.tnducrocq.ufc.data.source.remote.EventRemoteSource;
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

        LocalFighters localSource = new LocalFighters(context);
        RemoteFighters remoteSource = new RemoteFighters();
        fighterRepository = new FighterRepository(context, localSource, remoteSource, schedulerProvider);

        RemoteFighterDetails remoteFighterDetails = new RemoteFighterDetails();
        fighterDetailsRepository = new FighterDetailsRepository(context, remoteFighterDetails, schedulerProvider);

        EventDataSource localEvent = new LocalEvents(context);
        EventRemoteSource remoteEvent = new RemoteEvents();
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

    public FighterDetailsRepository getFighterDetailsRepository() {
        return fighterDetailsRepository;
    }

    public EventRepository getEventRepository() {
        return eventRepository;
    }
}
