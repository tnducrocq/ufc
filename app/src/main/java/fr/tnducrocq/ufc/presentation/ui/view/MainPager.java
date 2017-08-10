package fr.tnducrocq.ufc.presentation.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import fr.tnducrocq.ufc.data.entity.event.Events;
import fr.tnducrocq.ufc.data.entity.fighter.Fighters;


public class MainPager extends ViewPager {

    private boolean enabled;

    public MainPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    /**
     * Enable or disable the swipe navigation
     *
     * @param enabled
     */
    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}