package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.gturedi.views.StatefulLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventFightDao;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.fight.FightHeaderViewHolder;
import fr.tnducrocq.ufc.presentation.ui.fight.FightRecyclerViewAdapter;
import rx.Observable;
import rx.Observer;

public class FightActivity extends AppCompatActivity implements FightHeaderViewHolder.OnFighterInteractionListener {

    private static final String TAG = "FightActivity";
    private static final String ARG_EVENT_FIGHT = "event-fight";

    private EventFight mEventFight;

    @BindView(R.id.fight_toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.fight_stateful)
    public StatefulLayout mStateful;

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

        EventFight tmp = getIntent().getParcelableExtra(ARG_EVENT_FIGHT);
        mEventFight = App.getInstance().getSession().getEventFightDao().queryBuilder().where(EventFightDao.Properties.Uid.eq(tmp.getUid())).unique();
        mStateful.setAnimationEnabled(false);
        mStateful.showLoading();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new FightRecyclerViewAdapter(this);
        mAdapter.setOnFighterInteractionListener(FightActivity.this);

        mToolbar.setTitle(mEventFight.getFighter1LastName() + " vs " + mEventFight.getFighter2LastName());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Observable<Fighter> obsFighter1 = App.getInstance().getFighterRepository().get(Integer.toString(mEventFight.getFighter1Id()));
        Observable<Fighter> obsFighter2 = App.getInstance().getFighterRepository().get(Integer.toString(mEventFight.getFighter2Id()));
        Observable.zip(obsFighter1, obsFighter2, (fighter1, fighter2) -> new Fighter[]{fighter1, fighter2}).
                subscribeOn(App.getInstance().getSchedulerProvider().multi()).//
                observeOn(App.getInstance().getSchedulerProvider().ui()).//
                subscribe(new Observer<Fighter[]>() {

            @Override
            public void onCompleted() {
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onError(Throwable e) {
                mStateful.setAnimationEnabled(true);
                mStateful.showEmpty();
                Log.e(TAG, "onError", e);
                Snackbar snackbar = Snackbar.make(FightActivity.this.findViewById(android.R.id.content), e.getLocalizedMessage(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onNext(Fighter[] fighters) {
                mEventFight.setFighter1(fighters[0]);
                mEventFight.setFighter2(fighters[1]);
                mAdapter.setFight(mEventFight);
                onFighterResult(fighters[0], fighters[1]);
            }
        });
    }

    protected void onFighterResult(@NonNull Fighter fighter1, @NonNull Fighter fighter2) {
        Observable<Fighter> obsFighter1 = App.getInstance().getFighterRepository().fetchDetail(fighter1);
        Observable<Fighter> obsFighter2 = App.getInstance().getFighterRepository().fetchDetail(fighter2);
        Observable.zip(obsFighter1, obsFighter2, (fullFighter1, fullFighter2) -> new Fighter[]{fullFighter1, fullFighter2}).
                subscribeOn(App.getInstance().getSchedulerProvider().multi()).//
                observeOn(App.getInstance().getSchedulerProvider().ui()).//
                subscribe(new Observer<Fighter[]>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                mStateful.showEmpty();

                Log.e(TAG, "onError", e);
                Snackbar snackbar = Snackbar.make(FightActivity.this.findViewById(android.R.id.content), e.getLocalizedMessage(), Snackbar.LENGTH_LONG);
                snackbar.show();
            }

            @Override
            public void onNext(Fighter[] fighters) {
                mEventFight.setFighter1(fighters[0]);
                mEventFight.setFighter2(fighters[1]);
                mAdapter.setFight(mEventFight);
                mStateful.setAnimationEnabled(true);
                mStateful.showContent();
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

    @Override
    public void onFighterInteraction(Fighter fighter) {
        Intent intent = FighterActivity.newIntent(this, fighter);
        startActivity(intent);
    }
}
