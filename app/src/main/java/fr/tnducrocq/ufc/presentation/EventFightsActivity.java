package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.presentation.ui.event.details.EventFightsRecyclerViewAdapter;
import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class EventFightsActivity extends AppCompatActivity implements EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener {

    private static final String TAG = "EventFightsActivity";

    private static final String ARG_EVENT_ID = "event-id";
    private String mEventId;

    @BindView(R.id.event_fights_list)
    public RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static Intent newIntent(Context context, String eventId) {
        Intent intent = new Intent(context, EventFightsActivity.class);
        intent.putExtra(ARG_EVENT_ID, eventId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_fights);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mEventId = bundle.getString(ARG_EVENT_ID);
        }
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Observable<List<EventFight>> obsEventFights = App.getInstance().getEventRepository().getEventFight(mEventId);
        Observable<List<EventMedia>> obsEventMedias = App.getInstance().getEventRepository().getEventMedia(mEventId);

        Observable.zip(obsEventFights, obsEventMedias, new Func2<List<EventFight>, List<EventMedia>, EventDetail>() {
            @Override
            public EventDetail call(List<EventFight> eventFights, List<EventMedia> eventMedias) {
                EventDetail detail = new EventDetail();
                detail.fights = eventFights;
                detail.medias = eventMedias;
                return detail;
            }
        }).subscribeOn(App.getInstance().schedulerProvider.multi()).//
                observeOn(App.getInstance().schedulerProvider.ui()).//
                subscribe(new Observer<EventDetail>() {

            EventDetail mDetail;
            Throwable mError;

            @Override
            public void onCompleted() {
                if (mDetail != null) {
                    mAdapter = new EventFightsRecyclerViewAdapter(mDetail.fights, EventFightsActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onError(Throwable e) {
                mError = e;
            }

            @Override
            public void onNext(EventDetail eventDetail) {
                mDetail = eventDetail;
            }
        });
    }

    static class EventDetail {
        List<EventFight> fights;
        List<EventMedia> medias;
    }

    @Override
    public void onListFragmentInteraction(EventFight item) {

    }
}
