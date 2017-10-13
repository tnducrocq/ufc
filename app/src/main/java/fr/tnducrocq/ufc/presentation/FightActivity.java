package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.EventFight;

public class FightActivity extends AppCompatActivity {

    private static final String TAG = "FightActivity";
    private static final String ARG_EVENT_FIGHT = "event-fight";

    private EventFight mEventFight;

    @BindView(R.id.fighter1Name)
    TextView fighter1Name;

    @BindView(R.id.fighter1)
    ImageView fighter1;

    @BindView(R.id.fighter2Name)
    TextView fighter2Name;

    @BindView(R.id.fighter2)
    ImageView fighter2;

    @BindView(R.id.event_fight_collapsing)
    public CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.event_fight_toolbar)
    public Toolbar mToolbar;

    public static Intent newIntent(Context context, EventFight fight) {
        Intent intent = new Intent(context, FightActivity.class);
        intent.putExtra(ARG_EVENT_FIGHT, fight);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        ButterKnife.bind(this);

        mEventFight = (EventFight) getIntent().getParcelableExtra(ARG_EVENT_FIGHT);

        fighter1Name.setText(mEventFight.getFighter1LastName());
        Glide.with(fighter1.getContext()).
                load(mEventFight.getFighter1ProfileImage()).
                asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).
                placeholder(R.drawable.fighter_placeholder).
                animate(R.anim.fade_in).
                into(new ImageViewTarget<Bitmap>(fighter1) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        fighter1.setImageBitmap(resource);
                    }
                });

        fighter2Name.setText(mEventFight.getFighter2LastName());
        Glide.with(fighter2.getContext()).
                load(mEventFight.getFighter2ProfileImage()).
                asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).
                placeholder(R.drawable.fighter_placeholder).
                animate(R.anim.fade_in).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                into(new ImageViewTarget<Bitmap>(fighter2) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        fighter2.setImageBitmap(resource);
                    }
                });

        mCollapsingToolbarLayout.setTitle(mEventFight.getFighter1LastName() + " vs " + mEventFight.getFighter2LastName());
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
