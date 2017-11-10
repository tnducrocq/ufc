package fr.tnducrocq.ufc.presentation.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fr.tnducrocq.ufc.presentation.R;

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
                return context.getString(R.string.events);
            case 1:
                return context.getString(R.string.fighters);
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new EventsFragment();
            case 1:
                return new CategoriesFragment();//new FightersFragment();
        }
        return null;
    }
}
