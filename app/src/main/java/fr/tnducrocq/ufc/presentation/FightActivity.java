package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.presentation.ui.fight.FightRecyclerViewAdapter;

public class FightActivity extends AppCompatActivity {

    private static final String TAG = "FightActivity";
    private static final String ARG_EVENT_FIGHT = "event-fight";

    private EventFight mEventFight;

    @BindView(R.id.fight_toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.fight_list)
    public RecyclerView mRecyclerView;

    private FightRecyclerViewAdapter mAdapter;

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

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new FightRecyclerViewAdapter(mEventFight);
        mRecyclerView.setAdapter(mAdapter);
        mToolbar.setTitle(mEventFight.getFighter1LastName() + " vs " + mEventFight.getFighter2LastName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
