package fr.tnducrocq.ufc.presentation.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.main.events.FutureEventsFragment;
import fr.tnducrocq.ufc.presentation.ui.main.events.PastEventsFragment;
import fr.tnducrocq.ufc.presentation.ui.main.fighters.FightersFragment;

/**
 * Created by tony on 31/07/2017.
 */

public class MainTypePagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public MainTypePagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.future_events);
            case 1:
                return context.getString(R.string.past_events);
            case 2:
                return context.getString(R.string.boxers);
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FutureEventsFragment();
            case 1:
                return new PastEventsFragment();
            case 2:
                return new FightersFragment();
        }
        return null;
    }

}
