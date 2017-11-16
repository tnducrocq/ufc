package fr.tnducrocq.ufc.presentation.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.main.events.AbstractEventsFragment;
import fr.tnducrocq.ufc.presentation.ui.main.events.FutureEventsFragment;
import fr.tnducrocq.ufc.presentation.ui.main.events.PastEventsFragment;
import rx.Observer;

/**
 * Created by tony on 16/10/2017.
 */

public class EventsFragment extends Fragment {

    private static final String TAG = "EventsFragment";

    private Unbinder unbinder;

    @BindView(R.id.pager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    EventsPagerAdapter mEventsPagerAdapter;
    List<EventsSyncListener> mEventsListenerList = new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        unbinder = ButterKnife.bind(this, view);

        mEventsPagerAdapter = new EventsPagerAdapter(getFragmentManager(), getContext());
        mViewPager.setAdapter(mEventsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //TODO send event on bus
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(1);

        App.getInstance().getEventRepository().get().subscribeOn(App.getInstance().getSchedulerProvider().multi())//
                .observeOn(App.getInstance().getSchedulerProvider().ui())//
                .subscribe(new Observer<List<Event>>() {

                    @Override
                    public void onCompleted() {
                        for (EventsSyncListener listener : mEventsListenerList) {
                            listener.onEventReloaded();
                        }
                    }

                    @Override
                    public void onNext(List<Event> events) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public class EventsPagerAdapter extends FragmentPagerAdapter {

        private Context mContext;

        public EventsPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            AbstractEventsFragment fragment;
            switch (position) {
                case 0:
                    fragment = new PastEventsFragment();
                    break;
                default:
                    fragment = new FutureEventsFragment();
            }
            addEventsSyncListener(fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return mContext.getResources().getString(R.string.past_events);
                default:
                    return mContext.getResources().getString(R.string.future_events);
            }
        }
    }

    public interface EventsSyncListener {
        void onEventReloaded();
    }

    public void addEventsSyncListener(EventsSyncListener eventsSyncListener) {
        mEventsListenerList.add(eventsSyncListener);
    }
}
