package fr.tnducrocq.ufc.presentation.ui.fighter.details;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rd.PageIndicatorView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.utils.Constants;
import fr.tnducrocq.ufc.presentation.ui.utils.PresentationUtils;
import fr.tnducrocq.ufc.presentation.ui.view.ElasticDismissLayout;
import fr.tnducrocq.ufc.presentation.ui.view.FABToggle;
import fr.tnducrocq.ufc.presentation.ui.view.ParallaxImageView;
import fr.tnducrocq.ufc.presentation.ui.view.ParallaxRatioViewPager;
import fr.tnducrocq.ufc.presentation.ui.view.TranslatableLayout;

import static fr.tnducrocq.ufc.presentation.ui.utils.ColorUtils.setDrawableColor;

//TODO change opacity
//TODO back transition
//TODO fade button
//TODO change color
//TODO remove responsibilities
//TODO transition to similar movies
//TODO transition to actors
//TODO transition to more
//TODO transition from media to details
//TODO remove lambda

//TODO change opacity
//TODO back transition
//TODO fade button
//TODO change color
//TODO remove responsibilities
//TODO transition to similar movies
//TODO transition to actors
//TODO transition to more
//TODO transition from media to details
//TODO remove lambda

/**
 * Created by tony on 11/08/2017.
 */

public class FighterDetailsFragment extends Fragment {

    @BindView(R.id.backdrop_pager)
    protected ParallaxRatioViewPager pager;

    @BindView(R.id.page_indicator)
    protected PageIndicatorView indicatorView;

    @BindView(R.id.back_wrapper)
    protected View actionBar;

    @BindView(R.id.details_background)
    protected TranslatableLayout detailsParent;

    @BindView(R.id.media_title)
    protected TextView mediaTitle;

    @BindView(R.id.media_ratings)
    protected TextView mediaRatings;


    @BindView(R.id.media_release_year)
    protected TextView releaseYear;

    @BindView(R.id.media_description)
    protected TextView mediaDescription;

    @BindView(R.id.details)
    protected RecyclerView info;

    @BindView(R.id.poster)
    protected ParallaxImageView poster;

    @BindView(R.id.share_fab)
    protected FABToggle toggle;

    @BindView(R.id.media_duration)
    protected TextView duration;

    @BindView(R.id.media_money)
    protected TextView money;

    @BindView(R.id.parent)
    protected ElasticDismissLayout parent;

    private Unbinder unbinder;

    private InfoAdapter infoAdapter;


    private Fighter mFighter;

    //private MovieBackdropsAdapter adapter;
    // private String mediaId;
    // private String sharedPath;
    // private String sharedPosterPath;


    public static FighterDetailsFragment newInstance(Bundle args) {
        //boolean isTvShow = args.getBoolean(Constants.EXTRA_IS_TV, false);
        FighterDetailsFragment fragment = new FighterDetailsFragment();
        args.getParcelable(Constants.EXTRA_FIGHTER);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (savedInstanceState == null) savedInstanceState = getArguments();

        mFighter = (Fighter) savedInstanceState.getParcelable(Constants.EXTRA_FIGHTER);
        showCover(mFighter);

        //mediaId = savedInstanceState.getString(Constants.EXTRA_ID);
        //sharedPath = savedInstanceState.getString(Constants.EXTRA_DATA);
        //sharedPosterPath = savedInstanceState.getString(Constants.EXTRA_POSTER_PATH);
        initializeDependencies();
    }

    public void initializeDependencies() {
    }

    public void bind(View root) {
        unbinder = ButterKnife.bind(this, root);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fighter_details, container, false);
        bind(root);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (view != null) {

            getActivity().supportPostponeEnterTransition();
            infoAdapter = new InfoAdapter(getContext());
            /*adapter=new MovieBackdropsAdapter(getContext());
            adapter.setData(Collections.singletonList(sharedPath));
            adapter.setCallback((image,bitmap)->{
                indicatorView.setAnimationType(AnimationType.THIN_WORM);
                indicatorView.setTranslationY(image.getHeight()-indicatorView.getHeight()*2.5f);
                detailsParent.setStaticOffset(image.getHeight());
                detailsParent.setOffset(image.getHeight());
                final float posterOffset=image.getHeight()-poster.getHeight()*.33f;
                poster.setMinOffset(ViewCompat.getMinimumHeight(image)+poster.getHeight()/2);
                poster.setStaticOffset(posterOffset);
                poster.setOffset(posterOffset);
                poster.setPinned(true);
                toggle.setMinOffset(ViewCompat.getMinimumHeight(pager)-toggle.getHeight()/2);
                new Palette.Builder(bitmap).generate(MediaDetailsFragment.this::applyPalette);
                presenter.start(mediaId);
            });*/
            info.setAdapter(infoAdapter);
            //pager.setAdapter(adapter);

            int height = PresentationUtils.getStatusBarHeight(getResources());
            actionBar.setPadding(0, height, 0, 0);

            info.addOnScrollListener(listener);
            info.setOnFlingListener(flingListener);
        }
    }

    private void applyPalette(Palette palette) {
        Palette.Swatch swatch = palette.getDarkVibrantSwatch();
        if (swatch == null) swatch = palette.getDominantSwatch();
        //apply if not null
        if (swatch != null) {
            toggle.setBackgroundTintList(ColorStateList.valueOf(swatch.getRgb()));
            detailsParent.setBackgroundColor(swatch.getRgb());

            mediaTitle.setTextColor(swatch.getBodyTextColor());
            releaseYear.setTextColor(swatch.getBodyTextColor());
            mediaRatings.setTextColor(swatch.getBodyTextColor());
            mediaDescription.setTextColor(swatch.getBodyTextColor());
            duration.setTextColor(swatch.getBodyTextColor());
            money.setTextColor(swatch.getBodyTextColor());
            setDrawableColor(money, swatch.getBodyTextColor());
            setDrawableColor(duration, swatch.getBodyTextColor());
            setDrawableColor(releaseYear, swatch.getBodyTextColor());
            setDrawableColor(mediaRatings, swatch.getBodyTextColor());
        }
    }


    private RecyclerView.OnFlingListener flingListener = new RecyclerView.OnFlingListener() {
        @Override
        public boolean onFling(int velocityX, int velocityY) {
            poster.setImmediatePin(true);
            pager.setImmediatePin(true);
            return false;
        }
    };

    private RecyclerView.OnScrollListener listener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            poster.setImmediatePin(newState == RecyclerView.SCROLL_STATE_SETTLING);
            pager.setImmediatePin(newState == RecyclerView.SCROLL_STATE_SETTLING);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            final int scrollY = infoAdapter.getBlank().getTop();
            pager.setOffset(scrollY);
            poster.setOffset(poster.getStaticOffset() + scrollY);
            detailsParent.setOffset(detailsParent.getStaticOffset() + scrollY);
            toggle.setOffset(toggle.getStaticOffset() + scrollY);

            float min = poster.getOffset() + poster.getHeight();
            float max = detailsParent.getStaticOffset() + detailsParent.getHeight();
            //hide the poster as it goes up
            float alpha = ((detailsParent.getOffset() + detailsParent.getHeight()) - min) / (max - min);
            poster.setAlpha(alpha);
            toggle.setAlpha(1f - alpha);
            max = pager.getHeight();
            min = indicatorView.getTranslationY() + indicatorView.getHeight();
            //hide the indicator as well
            indicatorView.setAlpha((pager.getOffset() + pager.getHeight() - min) / (max - min));
        }
    };

    public void showCover(@NonNull Fighter fighter) {
        mediaTitle.setText(fighter.getFirstName() + " " + fighter.getLastName());
        releaseYear.setText(fighter.getNickname());
        mediaRatings.setText(fighter.getRank());

        List<String> tagList = new ArrayList<>();
        tagList.add(fighter.getWeightClass().getName());


        Glide.with(this).load(fighter.getLeftFullBodyImage()).priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).into(poster);

    }

    public void showDescription(@NonNull String description) {
        mediaDescription.setText(description);
        detailsParent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                detailsParent.getViewTreeObserver().removeOnPreDrawListener(this);
                ParamsFactory.shiftElementsFrom(poster, Arrays.asList(mediaTitle, releaseYear, mediaDescription));
                detailsParent.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    @Override
                    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                        View blank = infoAdapter.getBlank();
                        ViewGroup.LayoutParams params = blank.getLayoutParams();
                        params.height = pager.getHeight() + detailsParent.getHeight();
                        blank.setLayoutParams(params);
                        int offset = pager.getHeight() + detailsParent.getHeight() - (toggle.getHeight() / 2);
                        toggle.setStaticOffset(offset);
                        toggle.setOffset(offset);
                        detailsParent.removeOnLayoutChangeListener(this);
                    }
                });
                getActivity().supportStartPostponedEnterTransition();
                return true;
            }
        });
    }

    private static class ParamsFactory {

        static void shiftElementsFrom(View target, List<View> shiftElements) {
            float posterY = getBottomY(target) + target.getHeight();
            final float posterX = target.getX() + target.getWidth();
            final float spacing = target.getResources().getDimension(R.dimen.spacing_media_details);
            final float offset = target.getWidth() + spacing;
            for (int index = 0; index < shiftElements.size(); index++) {
                View element = shiftElements.get(index);
                if (posterX >= element.getX() && (posterY + spacing) >= getBottomY(element)) {
                    if (index != 0 && shouldShiftVertically(posterY, element, spacing)) {
                        posterY = shiftVertically(element, posterY);
                    } else {
                        shiftHorizontally(element, offset);
                    }
                }
            }
        }

        static boolean shouldShiftVertically(float offsetY, View target, float spacing) {
            final float targetY = getBottomY(target);
            final float offsetDiff = targetY - offsetY;
            return offsetDiff >= 0 && offsetDiff <= spacing;
        }

        static float shiftVertically(View target, float posterY) {
            final float spacing = target.getResources().getDimension(R.dimen.spacing_media_details);
            ViewGroup.MarginLayoutParams params = ViewGroup.MarginLayoutParams.class.cast(target.getLayoutParams());
            final float offsetY = posterY - getBottomY(target) + spacing;
            params.topMargin += offsetY;
            posterY -= offsetY;
            target.setLayoutParams(params);
            return posterY;
        }

        static void shiftHorizontally(View target, float offset) {
            ViewGroup.MarginLayoutParams params = ViewGroup.MarginLayoutParams.class.cast(target.getLayoutParams());
            params.leftMargin += offset;
            target.setLayoutParams(params);
        }

        static float getBottomY(View view) {
            int[] screenLocation = new int[2];
            view.getLocationOnScreen(screenLocation);
            return screenLocation[1];
        }
    }

}
