package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDetails;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.view.CardPieView;
import rx.Observable;
import rx.Observer;

import static fr.tnducrocq.ufc.presentation.app.App.getInstance;

/**
 * Created by tony on 11/08/2017.
 */

public class FighterActivity extends AppCompatActivity {

    public static final String TAG = "fighterDetailsTag";
    public static final String EXTRA_FIGHTER = "extra_fighter";

    public static Intent newIntent(Context context, Fighter fighter) {
        Intent intent = new Intent(context, FighterActivity.class);
        intent.putExtra(EXTRA_FIGHTER, fighter);
        return intent;
    }

    public Fighter mFighter;

    @BindView(R.id.fighter_image)
    public ImageView mImageView;

    @BindView(R.id.toolbar_layout)
    public CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.fighter_toolbar)
    public Toolbar mToolbar;


    @BindView(R.id.fighter_kick_card)
    public CardPieView kickView;

    @BindView(R.id.fighter_grappling_card)
    public CardPieView grapplingView;

/*
    @BindView(R.id.fighter_kick_card2)
    public CardPieView kickView2;

    @BindView(R.id.fighter_grappling_card2)
    public CardPieView grapplingView2;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fighter);
        ButterKnife.bind(this);

        mFighter = (Fighter) getIntent().getParcelableExtra(EXTRA_FIGHTER);

        mToolbar.setTitle(mFighter.getFirstName() + " " + mFighter.getLastName());

        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mImageView.setVisibility(View.INVISIBLE);
        Glide.with(this).
                load(mFighter.getLeftFullBodyImage()).
                asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).
                placeholder(R.drawable.fighter_placeholder_red).
                animate(R.anim.fade_in).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                into(new ImageViewTarget<Bitmap>(mImageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        mImageView.setImageBitmap(resource);

                        final Matrix matrix = mImageView.getImageMatrix();
                        final float imageHeight = mImageView.getDrawable().getIntrinsicHeight();
                        final float imageViewHeight = mImageView.getHeight();

                        float newImageHeight = imageViewHeight * 2.0f;
                        final float scaleRatio = newImageHeight / imageHeight;
                        matrix.postScale(scaleRatio, scaleRatio);
                        mImageView.setImageMatrix(matrix);
                        mImageView.setVisibility(View.VISIBLE);
                    }
                });

        Log.d(TAG, "fighter: " + mFighter == null ? "null" : mFighter.toString());

        kickView.setVisibility(View.GONE);
        //kickView2.setVisibility(View.GONE);
        grapplingView.setVisibility(View.GONE);
        //grapplingView2.setVisibility(View.GONE);

        Observable<FighterDetails> observable = getInstance().getFighterDetailsRepository().get(mFighter);
        observable.subscribeOn(App.getInstance().getSchedulerProvider().multi())//
                .observeOn(App.getInstance().getSchedulerProvider().ui())//
                .subscribe(new Observer<FighterDetails>() {

                    FighterDetails mFighter;
                    Throwable mError;

                    @Override
                    public void onCompleted() {
                        if (mFighter != null) {
                            kickView.setVisibility(View.VISIBLE);
                            //kickView2.setVisibility(View.VISIBLE);
                            grapplingView.setVisibility(View.VISIBLE);
                            // grapplingView2.setVisibility(View.VISIBLE);

                            kickView.init(mFighter.getStrikes_attempted(), mFighter.getStrikes_successful());
                            grapplingView.init(mFighter.getTakedowns_attempted(), mFighter.getTakedowns_successful());

                            // kickView2.init(mFighter.getStrikes_attempted(), mFighter.getStrikes_successful());
                            // grapplingView2.init(mFighter.getTakedowns_attempted(), mFighter.getTakedowns_successful());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        this.mError = e;
                        Log.e(TAG, "ERROR", e);
                    }

                    @Override
                    public void onNext(FighterDetails fighter) {
                        this.mFighter = fighter;
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
