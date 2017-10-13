package fr.tnducrocq.ufc.presentation.ui.main.events;

import android.os.Bundle;

import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.presentation.app.App;
import rx.Observable;

/**
 * Created by tony on 01/08/2017.
 */

public class FutureEventsFragment extends EventsFragment {

    public static FutureEventsFragment newFragment() {
        FutureEventsFragment fragment = new FutureEventsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FutureEventsFragment() {
    }

    @Override
    public Observable<List<Event>> getEvent() {
        return App.getInstance().getEventRepository().getFuture(new Date());
    }
}