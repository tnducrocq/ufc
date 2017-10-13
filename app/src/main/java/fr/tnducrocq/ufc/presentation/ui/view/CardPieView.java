package fr.tnducrocq.ufc.presentation.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 04/10/2017.
 */

public class CardPieView extends CardView {

    private static final String TAG = "CardPieView";
    @BindView(R.id.text_percentage)
    public TextView mPercentage;

    @BindView(R.id.text_title)
    public TextView mTitle;

    @BindView(R.id.text_remaining)
    public TextView mRemaining;

    @BindView(R.id.deco_view)
    public DecoView mDecoView;


    private String percentage;
    private int percentageAppearance;

    private String remaining;
    private int remainingAppearance;

    private String title;
    private int titleAppearance;

    private float lineWidth;

    private int serieBackColor;
    private int serieItemColor;

    public CardPieView(Context context) {
        super(context);
        inflate(getContext(), R.layout.view_card_pie, this);
        ButterKnife.bind(this);
    }

    public CardPieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CardPieView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.view_card_pie, this);
        ButterKnife.bind(this);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CardPieView, 0, 0);
        try {
            remaining = a.getString(R.styleable.CardPieView_remaining);
            title = a.getString(R.styleable.CardPieView_title);
            lineWidth = a.getDimension(R.styleable.CardPieView_lineWidth, 42f);

            percentageAppearance = a.getResourceId(R.styleable.CardPieView_percentageAppearance, -1);
            if (percentageAppearance != -1) {
                mPercentage.setTextAppearance(context, percentageAppearance);
            }

            titleAppearance = a.getResourceId(R.styleable.CardPieView_titleAppearance, -1);
            if (titleAppearance != -1) {
                mTitle.setTextAppearance(context, titleAppearance);
            }

            remainingAppearance = a.getResourceId(R.styleable.CardPieView_remainingAppearance, -1);
            if (remainingAppearance != -1) {
                mRemaining.setTextAppearance(context, remainingAppearance);
            }
            serieBackColor = a.getColor(R.styleable.CardPieView_serieBackColor, getResources().getColor(R.color.colorSerieBack));
            serieItemColor = a.getColor(R.styleable.CardPieView_serieBackColor, getResources().getColor(R.color.colorSerieItem1));

        } finally {
            a.recycle();
        }
        mTitle.setText(title);
        mRemaining.setText(remaining);
    }

    public void init(int attempt, int success) {
        init(attempt, success, title, remaining);
    }

    public void init(int attempt, int success, String title, String remaining) {
        mTitle.setText(title);
        mPercentage.setText("");
        mRemaining.setText("");

        SeriesItem backSerie = new SeriesItem.Builder(serieBackColor).//
                setLineWidth(lineWidth).//
                setRange(0, attempt, 0).//
                setInitialVisibility(true).build();
        int backIndex = mDecoView.addSeries(backSerie);

        final SeriesItem seriesItem = new SeriesItem.Builder(serieItemColor).//
                setLineWidth(lineWidth).//
                setRange(0, attempt, 0).//
                setInitialVisibility(false).build();

        seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
            @Override
            public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));

                mPercentage.setText(String.format("%.0f%%", percentFilled * 100f));

                if (remaining != null) {
                    mRemaining.setText(String.format(remaining, (int) (percentFilled * attempt), attempt));
                }
            }

            @Override
            public void onSeriesItemDisplayProgress(float percentComplete) {

            }
        });

        int serieIndex = mDecoView.addSeries(seriesItem);

        mDecoView.executeReset();
        mDecoView.addEvent(new DecoEvent.Builder((float) attempt).setIndex(backIndex).setDuration(600).setDelay(100).build());
        mDecoView.addEvent(new DecoEvent.Builder((float) success).setIndex(serieIndex).setDuration(1000).setDelay(750).build());

    }


}
