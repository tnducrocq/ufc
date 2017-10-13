package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.event.EventFightsRecyclerViewAdapter;
import fr.tnducrocq.ufc.presentation.ui.event.EventInformations;
import fr.tnducrocq.ufc.presentation.ui.utils.PaletteColors;
import fr.tnducrocq.ufc.presentation.ui.utils.PresentationUtils;
import im.ene.toro.widget.Container;
import rx.Observable;
import rx.Observer;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class EventActivity extends AppCompatActivity implements EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener {

    private static final String TAG = "EventActivity";

    private static final String ARG_EVENT = "event";
    private static final String ARG_EVENT_PALETTE = "event-palette";

    private Event mEvent;
    private PaletteColors mEventPalette;
    private EventInformations mDetail;

    @BindView(R.id.event_fights_list)
    public Container mRecyclerView;

    @BindView(R.id.event_fights_image)
    public ImageView mImageView;

    @BindView(R.id.event_fights_toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.toolbar_layout)
    public CollapsingToolbarLayout mToolbarLayout;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public static Intent newIntent(@NonNull Context context, @NonNull Event event, @NonNull PaletteColors palette) {
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra(ARG_EVENT, event);
        intent.putExtra(ARG_EVENT_PALETTE, palette);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        mEvent = getIntent().getParcelableExtra(ARG_EVENT);
        mEventPalette = getIntent().getParcelableExtra(ARG_EVENT_PALETTE);

        mToolbarLayout.setBackgroundColor(mEventPalette.rgb);
        mToolbarLayout.setStatusBarScrimColor(mEventPalette.rgb);
        mToolbarLayout.setContentScrimColor(mEventPalette.rgb);

        mToolbar.setTitle(mEvent.getTitleTagLine());

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int statusBarHeight = PresentationUtils.getStatusBarHeight(getResources());
        mToolbar.getLayoutParams().height += statusBarHeight;
        mToolbar.setPadding(0, statusBarHeight, 0, 0);

        Bitmap placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.event_placeholder);
        mImageView.setImageBitmap(placeholder);
        Glide.with(this) //
                .load(mEvent.getFeatureImage()) //
                .asBitmap() //
                .priority(Priority.IMMEDIATE) //
                .diskCacheStrategy(DiskCacheStrategy.ALL) //
                .placeholder(R.drawable.event_placeholder) //
                .animate(R.anim.fade_in) //
                .into(new ImageViewTarget<Bitmap>(mImageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        mImageView.setImageBitmap(resource);
                    }
                });

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Observable<List<EventFight>> obsEventFights = App.getInstance().getEventRepository().getEventFight(mEvent.id());
        Observable<List<EventMedia>> obsEventMedias = App.getInstance().getEventRepository().getEventMedia(mEvent.id());

        Observable.zip(obsEventFights, obsEventMedias, (eventFights, eventMedias) -> {
            EventInformations detail = new EventInformations();
            detail.fights = eventFights;
            detail.medias = eventMedias;
            return detail;
        }).subscribeOn(App.getInstance().schedulerProvider.multi()).//
                observeOn(App.getInstance().schedulerProvider.ui()).//
                subscribe(new ObserverEventInformations());
    }

    public class ObserverEventInformations implements Observer<EventInformations> {

        private EventInformations mDetail;
        private Throwable mError;

        @Override
        public void onCompleted() {
            EventActivity.this.mDetail = mDetail;
            if (mDetail != null) {
                mAdapter = new EventFightsRecyclerViewAdapter(mDetail, EventActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                EventActivity.this.invalidateOptionsMenu();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        //R.menu.menu est l'id de notre menu
        inflater.inflate(R.menu.menu_event, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem register = menu.findItem(R.id.action_video);
        boolean hide = mDetail == null || mDetail.medias == null || mDetail.medias.isEmpty();
        register.setVisible(!hide);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (menuItem.getItemId() == R.id.action_video) {
            Intent intent = PlayerActivity.newIntent(this, mDetail.medias.get(0).getMobileVideoUrl());
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onListFragmentInteraction(EventFight item) {
        Intent intent = FightActivity.newIntent(this, item);
        startActivity(intent);
    }
}
