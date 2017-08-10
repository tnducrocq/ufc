package fr.tnducrocq.ufc.presentation.ui.main.event;

import android.os.Bundle;

import java.util.Date;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.presentation.App;
import rx.Observable;

/**
 * Created by tony on 01/08/2017.
 */

public class PastEventFragment extends EventFragment {

    public static PastEventFragment newFragment() {
        PastEventFragment fragment = new PastEventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PastEventFragment() {
    }

    @Override
    public Observable<List<Event>> getEvent() {
        return App.getInstance().getEventRepository().getPast(new Date());
    }
}