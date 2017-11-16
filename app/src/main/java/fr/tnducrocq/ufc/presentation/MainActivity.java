package fr.tnducrocq.ufc.presentation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.roughike.bottombar.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.fighter.WeightCategory;
import fr.tnducrocq.ufc.presentation.ui.main.MainTypePagerAdapter;
import fr.tnducrocq.ufc.presentation.ui.main.categories.CategoriesAdapter;
import fr.tnducrocq.ufc.presentation.ui.main.events.AbstractEventsFragment;
import fr.tnducrocq.ufc.presentation.ui.view.MainPager;


public class MainActivity extends AppCompatActivity implements AbstractEventsFragment.OnEventFragmentInteractionListener, CategoriesAdapter.OnWeightCategoryInteractionListener {

    private static final String TAG = "MainActivity";

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @BindView(R.id.actionBar)
    protected Toolbar mToolbar;

    @BindView(R.id.appBarLayout)
    protected AppBarLayout mAppBarLayout;

    @BindView(R.id.navigation)
    protected NavigationView mNavigationView;

    @BindView(R.id.bottom_navigation)
    protected BottomBar mBottomNavigation;

    @BindView(R.id.main_pager)
    protected MainPager mMainPager;
    protected MainTypePagerAdapter mMainTypePagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUI();
    }

    private void setUI() {
        mNavigationView.setCheckedItem(R.id.events);

        mMainTypePagerAdapter = new MainTypePagerAdapter(getSupportFragmentManager(), this);
        mMainPager.setAdapter(mMainTypePagerAdapter);
        mMainPager.setOffscreenPageLimit(2);

        final int duration = getResources().getInteger(R.integer.page_fade_duration);
        mBottomNavigation.setOnTabSelectListener((tabId -> mMainPager.animate().alpha(0).setDuration(duration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                switch (tabId) {
                    case R.id.events:
                        mToolbar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)));
                        mToolbar.setTitle(R.string.events);
                        mMainPager.setCurrentItem(0, false);
                        break;
                    case R.id.fighters:
                        mToolbar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary)));
                        mToolbar.setTitle(R.string.fighters);
                        mMainPager.setCurrentItem(1, false);
                        break;
                }
                mMainPager.animate().alpha(1.f).setDuration(duration).setListener(null).start();
            }
        }).start()));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Event item) {
    }

    @Override
    public void onWeightCategoryInteraction(WeightCategory category) {

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
