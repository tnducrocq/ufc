package fr.tnducrocq.ufc.presentation.ui.main.events;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.main.EventsFragment;
import rx.Observable;
import rx.Observer;

/**
 * Created by tony on 01/08/2017.
 */

public abstract class AbstractEventsFragment extends Fragment implements EventsFragment.EventsSyncListener {

    private static final String TAG = "AbstractEventsFragment";

    @BindView(R.id.list)
    protected RecyclerView mRecyclerView;

    protected EventsRecyclerViewAdapter mAdapter;
    protected OnEventFragmentInteractionListener mListener;
    protected Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_event, container, false);
        unbinder = ButterKnife.bind(this, view);

        mAdapter = new EventsRecyclerViewAdapter();
        mAdapter.setListener(mListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        requestEvent();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AbstractEventsFragment.OnEventFragmentInteractionListener) {
            mListener = (AbstractEventsFragment.OnEventFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AbstractEventsFragment.OnEventFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void requestEvent() {
        getEvent().subscribeOn(App.getInstance().getSchedulerProvider().multi())//
                .observeOn(App.getInstance().getSchedulerProvider().ui())//
                .subscribe(new Observer<List<Event>>() {

                    List<Event> mEvents;
                    Throwable mError;

                    @Override
                    public void onCompleted() {
                        if (mEvents != null) {
                            mAdapter.setValues(mEvents);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        this.mError = e;
                        Log.e(TAG, "ERROR", e);
                    }

                    @Override
                    public void onNext(List<Event> fighters) {
                        this.mEvents = fighters;
                    }
                });

    }

    public abstract Observable<List<Event>> getEvent();

    @Override
    public void onEventReloaded() {
        requestEvent();
    }

    public void setListener(OnEventFragmentInteractionListener listener) {
        mListener = listener;
    }

    public interface OnEventFragmentInteractionListener {
        void onListFragmentInteraction(Event item);
    }

}
