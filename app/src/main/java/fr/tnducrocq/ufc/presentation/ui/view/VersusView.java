package fr.tnducrocq.ufc.presentation.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 09/10/2017.
 */

public class VersusView extends FrameLayout {

    private static final String TAG = "CardPieView";

    public VersusView(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_card_pie, this);
        ButterKnife.bind(this);
    }

    public VersusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public VersusView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.view_versus, this);
        ButterKnife.bind(this);
    }
}
