package fr.tnducrocq.ufc.presentation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.roughike.bottombar.BottomBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import fr.tnducrocq.ufc.presentation.ui.main.FightersFragment;
import fr.tnducrocq.ufc.presentation.ui.main.MainTypePagerAdapter;
import fr.tnducrocq.ufc.presentation.ui.main.events.AbstractEventsFragment;
import fr.tnducrocq.ufc.presentation.ui.utils.PresentationUtils;
import fr.tnducrocq.ufc.presentation.ui.view.MainPager;


public class MainActivity extends AppCompatActivity implements AbstractEventsFragment.OnEventFragmentInteractionListener, FightersFragment.OnFighterFragmentInteractionListener {


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @BindView(R.id.actionBar)
    protected Toolbar mToolbar;

    @BindView(R.id.drawerLayout)
    protected DrawerLayout mDrawerLayout;

    @BindView(R.id.navigation)
    protected NavigationView mNavigationView;

    @BindView(R.id.bottom_navigation)
    protected BottomBar mBottomNavigation;

    @BindView(R.id.main_pager)
    protected MainPager mMainPager;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ButterKnife.bind(this);
        setUI();
    }

    private void setBottomNavigation() {
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

    private void setMovieTypePager() {
        mMainPager.setAdapter(new MainTypePagerAdapter(getSupportFragmentManager(), this));
        mMainPager.setOffscreenPageLimit(2);
    }

    private void setActionBar() {
        int statusBarHeight = PresentationUtils.getStatusBarHeight(getResources());
        mToolbar.getLayoutParams().height += statusBarHeight;
        mToolbar.setPadding(0, statusBarHeight, 0, 0);
        setSupportActionBar(mToolbar);
        mNavigationView.setCheckedItem(R.id.events);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setDrawer() {
        this.mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(item -> {
            item.setChecked(true);
            switch (item.getItemId()) {
                case R.id.settings:
                    //startActivity(new Intent(this, SettingsActivity.class));
                    return true;

            }
            return false;
        });
    }

    private void setUI() {
        setDrawer();
        setActionBar();
        setMovieTypePager();
        setBottomNavigation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void inject() {
        //App.appInstance().appComponent().inject(this);
    }

    public void handleEvent(@NonNull Object event) {
        Log.d(MainActivity.class.getSimpleName(), "HandleEvent");
    }

    @Override
    public void onListFragmentInteraction(Event item) {
        Log.d("MainActivity", item.toString());
       /* Intent intent = EventActivity.newIntent(this, item, color);
        startActivity(intent);*/
    }

    @Override
    public void onSeeAllFragmentInteraction(WeightCategory category) {

    }

    @Override
    public void onListFragmentInteraction(Fighter item) {
        Intent intent = FighterActivity.newIntent(this, item);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //finish();
        moveTaskToBack(true);
    }
}
