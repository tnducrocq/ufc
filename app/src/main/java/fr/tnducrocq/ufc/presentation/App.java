package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.repository.IFighterRepository;
import fr.tnducrocq.ufc.data.repository.impl.FighterRepository;
import fr.tnducrocq.ufc.data.source.FighterDataSource;
import fr.tnducrocq.ufc.data.source.local.LocalFighters;
import fr.tnducrocq.ufc.data.source.remote.RemoteFighters;
import fr.tnducrocq.ufc.data.utils.scheduler.BaseSchedulerProvider;
import fr.tnducrocq.ufc.data.utils.scheduler.SchedulerProvider;

/**
 * Created by tony on 08/08/2017.
 */

public class App extends MultiDexApplication {

    private static App INSTANCE;

    Context context;
    BaseSchedulerProvider schedulerProvider;
    FighterRepository<Fighter> fighterRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        INSTANCE = this;
    }

    private void initializeComponent() {
        context = this;
        schedulerProvider = new SchedulerProvider();

        FighterDataSource<Fighter> localSource = new LocalFighters(context);
        IFighterRepository<Fighter> remoteSource = new RemoteFighters();
        fighterRepository = new FighterRepository<>(context, localSource, remoteSource, schedulerProvider);
    }

    public Context getContext() {
        return context;
    }

    public BaseSchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public FighterRepository<Fighter> getFighterRepository() {
        return fighterRepository;
    }
}
