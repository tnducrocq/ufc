package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventFights;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.data.entity.event.EventMedias;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.event.EventFightsRecyclerViewAdapter;
import fr.tnducrocq.ufc.presentation.ui.utils.PaletteColors;
import fr.tnducrocq.ufc.presentation.ui.utils.PresentationUtils;
import rx.Observer;

public class EventActivity extends AppCompatActivity implements EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener {

    private static final String TAG = "EventActivity";

    private static final String ARG_EVENT = "event";
    private static final String ARG_EVENT_PALETTE = "event-palette";

    private Event mEvent;
    private PaletteColors mEventPalette;
    protected List<EventFight> mFights;
    protected List<EventMedia> mMedias;

    @BindView(R.id.event_fights_list)
    public RecyclerView mRecyclerView;

    @BindView(R.id.event_fights_image)
    public ImageView mImageView;

    @BindView(R.id.event_fights_toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.event_fights_title)
    public TextView mTitle;

    @BindView(R.id.event_fights_subhead)
    public TextView mSubhead;

    @BindView(R.id.toolbar_layout)
    public CollapsingToolbarLayout mToolbarLayout;

    private RecyclerView.Adapter mAdapter;

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

        mToolbarLayout.setStatusBarScrimColor(mEventPalette.rgb);
        mToolbarLayout.setContentScrimColor(mEventPalette.rgb);
        mToolbarLayout.setTitle(mEvent.getTitleTagLine().toUpperCase());

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (new Date().after(mEvent.getEventDate())) {
            mSubhead.setText(getString(R.string.event_past_title));
        } else {
            mSubhead.setText(getString(R.string.event_future_title));
        }
        mTitle.setText(mEvent.getBaseTitle());


        int statusBarHeight = PresentationUtils.getStatusBarHeight(getResources());
        mToolbar.getLayoutParams().height += statusBarHeight;
        mToolbar.setPadding(0, statusBarHeight, 0, 0);

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

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        App.getInstance().getEventFightsRepository().get(mEvent.id()).
                subscribeOn(App.getInstance().schedulerProvider.multi()).
                observeOn(App.getInstance().schedulerProvider.ui()).
                subscribe(new Observer<EventFights>() {

                    protected Throwable mError;

                    @Override
                    public void onCompleted() {
                        if (mFights != null) {
                            mAdapter = new EventFightsRecyclerViewAdapter(mFights, EventActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mError = e;
                    }

                    @Override
                    public void onNext(EventFights values) {
                        mFights = values.getFightList();
                    }
                });

        App.getInstance().getEventMediasRepository().get(mEvent.id()).
                subscribeOn(App.getInstance().schedulerProvider.multi()).
                observeOn(App.getInstance().schedulerProvider.ui()).
                subscribe(new Observer<EventMedias>() {

                    protected Throwable mError;

                    @Override
                    public void onCompleted() {
                        if (mMedias != null) {
                            EventActivity.this.invalidateOptionsMenu();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mError = e;
                    }

                    @Override
                    public void onNext(EventMedias values) {
                        mMedias = values.getMediaList();
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_event, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem register = menu.findItem(R.id.action_video);
        boolean hide = mMedias == null || mMedias == null || mMedias.isEmpty();
        register.setVisible(!hide);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (menuItem.getItemId() == R.id.action_video) {
            Intent intent = PlayerActivity.newIntent(this, mMedias.get(0).getMobileVideoUrl());
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
