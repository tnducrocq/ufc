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
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDetails;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.fight.FightRecyclerViewAdapter;
import rx.Observable;
import rx.Observer;

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

        Fighter fighter1 = new Fighter(Integer.toString(mEventFight.getFighter1Id()), mEventFight.getFighter1FirstName(), mEventFight.getFighter1LastName());
        Fighter fighter2 = new Fighter(Integer.toString(mEventFight.getFighter2Id()), mEventFight.getFighter2FirstName(), mEventFight.getFighter2LastName());

        Observable<FighterDetails> obsFighter1 = App.getInstance().getFighterDetailsRepository().get(fighter1);
        Observable<FighterDetails> obsFighter2 = App.getInstance().getFighterDetailsRepository().get(fighter2);
        Observable.zip(obsFighter1, obsFighter2, (fighter1Details, fighter2Details) -> new FighterDetails[]{fighter1Details, fighter2Details}).
                subscribeOn(App.getInstance().schedulerProvider.multi()).//
                observeOn(App.getInstance().schedulerProvider.ui()).//
                subscribe(new Observer<FighterDetails[]>() {

            FighterDetails[] mValues;
            Throwable mError;

            @Override
            public void onCompleted() {
                if (mValues != null) {
                    mEventFight.setFighter1(mValues[0]);
                    mEventFight.setFighter2(mValues[1]);
                    mAdapter = new FightRecyclerViewAdapter(mEventFight);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onError(Throwable e) {
                mError = e;
            }

            @Override
            public void onNext(FighterDetails[] fighters) {
                mValues = fighters;
            }
        });
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
