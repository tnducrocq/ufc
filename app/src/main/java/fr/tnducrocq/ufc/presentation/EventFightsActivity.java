package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.presentation.ui.event.details.EventFightsRecyclerViewAdapter;
import fr.tnducrocq.ufc.presentation.ui.event.details.EventInformations;
import im.ene.toro.widget.Container;
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
    private static final String ARG_EVENT_TITLE = "event-title";
    private static final String ARG_EVENT_IMAGE = "event-feature-image";

    private String mEventId;
    private String mEventTitle;
    private String mEventFeatureImage;

    @BindView(R.id.event_fights_list)
    public Container mRecyclerView;

    @BindView(R.id.event_fights_image)
    public ImageView mImageView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static Intent newIntent(Context context, Event event) {
        Intent intent = new Intent(context, EventFightsActivity.class);
        intent.putExtra(ARG_EVENT_ID, event.id());
        intent.putExtra(ARG_EVENT_TITLE, event.getBaseTitle());
        intent.putExtra(ARG_EVENT_IMAGE, event.getFeatureImage());
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
            mEventTitle = bundle.getString(ARG_EVENT_TITLE);
            mEventFeatureImage = bundle.getString(ARG_EVENT_IMAGE);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.event_fights_toolbar);
        toolbar.setTitle(mEventTitle);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Glide.with(this) //
                .load(mEventFeatureImage) //
                .asBitmap() //
                .priority(Priority.IMMEDIATE) //
                .diskCacheStrategy(DiskCacheStrategy.RESULT) //
                .placeholder(R.drawable.event_placeholder) //
                .animate(R.anim.fade_in) //
                .into(mImageView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Observable<List<EventFight>> obsEventFights = App.getInstance().getEventRepository().getEventFight(mEventId);
        Observable<List<EventMedia>> obsEventMedias = App.getInstance().getEventRepository().getEventMedia(mEventId);

        Observable.zip(obsEventFights, obsEventMedias, new Func2<List<EventFight>, List<EventMedia>, EventInformations>() {
            @Override
            public EventInformations call(List<EventFight> eventFights, List<EventMedia> eventMedias) {
                EventInformations detail = new EventInformations();
                detail.fights = eventFights;
                detail.medias = eventMedias;
                return detail;
            }
        }).subscribeOn(App.getInstance().schedulerProvider.multi()).//
                observeOn(App.getInstance().schedulerProvider.ui()).//
                subscribe(new Observer<EventInformations>() {

            EventInformations mDetail;
            Throwable mError;

            @Override
            public void onCompleted() {
                if (mDetail != null) {
                    mAdapter = new EventFightsRecyclerViewAdapter(mDetail, EventFightsActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onError(Throwable e) {
                mError = e;
            }

            @Override
            public void onNext(EventInformations eventDetail) {
                mDetail = eventDetail;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            //Intent intent = new Intent(EventFightsActivity.this, MainActivity.class);
            //startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onListFragmentInteraction(EventFight item) {

    }
}
