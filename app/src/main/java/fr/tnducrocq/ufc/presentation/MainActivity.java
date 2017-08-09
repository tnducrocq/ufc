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
import fr.tnducrocq.ufc.presentation.ui.main.EventFragment;
import fr.tnducrocq.ufc.presentation.ui.main.MainTypePagerAdapter;
import fr.tnducrocq.ufc.presentation.ui.utils.PresentationUtils;
import fr.tnducrocq.ufc.presentation.ui.view.MainPager;


public class MainActivity extends AppCompatActivity implements EventFragment.OnEventFragmentInteractionListener {


    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @BindView(R.id.actionBar)
    protected Toolbar actionBar;

    @BindView(R.id.drawerLayout)
    protected DrawerLayout drawerLayout;

    @BindView(R.id.navigation)
    protected NavigationView navigationView;

    @BindView(R.id.bottom_navigation)
    protected BottomBar bottomNavigation;

    @BindView(R.id.media_pager)
    protected MainPager mediaPager;

    private ActionBarDrawerToggle drawerToggle;

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
        bottomNavigation.setOnTabSelectListener((tabId -> mediaPager.animate().alpha(0).setDuration(duration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                switch (tabId) {
                    case R.id.future_events:
                        actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.colorFutureEvent)));
                        actionBar.setTitle(R.string.future_events);
                        mediaPager.setCurrentItem(0, false);
                        break;
                    case R.id.past_events:
                        actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.colorPastEvent)));
                        actionBar.setTitle(R.string.past_events);
                        mediaPager.setCurrentItem(1, false);
                        break;
                    case R.id.boxers:
                        actionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.colorBoxer)));
                        actionBar.setTitle(R.string.boxers);
                        mediaPager.setCurrentItem(2, false);
                        break;
                }
                mediaPager.animate().alpha(1.f).setDuration(duration).setListener(null).start();
            }
        }).start()));
    }

    private void setMovieTypePager() {
        mediaPager.setAdapter(new MainTypePagerAdapter(getSupportFragmentManager(), this ));
        mediaPager.setOffscreenPageLimit(2);
    }

    private void setActionBar() {
        int statusBarHeight = PresentationUtils.getStatusBarHeight(getResources());
        actionBar.getLayoutParams().height += statusBarHeight;
        actionBar.setPadding(0, statusBarHeight, 0, 0);
        setSupportActionBar(actionBar);
        navigationView.setCheckedItem(R.id.future_events);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void setDrawer() {
        this.drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, actionBar, 0, 0);
        drawerLayout.setDrawerListener(drawerToggle);
        navigationView.setNavigationItemSelectedListener(item -> {
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
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
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
        Intent intent = EventFightsActivity.newIntent(this, Integer.toString(item.getId()));
        startActivity(intent);
    }
}
