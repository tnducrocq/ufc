package fr.tnducrocq.ufc.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.WeightCategory;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.category.FightersAdapter;
import rx.Observer;

/**
 * Created by tony on 11/08/2017.
 */

public class CategoryActivity extends AppCompatActivity {

    public static final String TAG = "CategoryActivity";
    public static final String EXTRA_CATEGORY = "extra_category";

    public static Intent newIntent(Context context, WeightCategory category) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        return intent;
    }

    public WeightCategory mWeightCategory;

    @BindView(R.id.category_toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.category_fighters)
    public RecyclerView mRecyclerView;

    private FightersAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        mWeightCategory = (WeightCategory) getIntent().getSerializableExtra(EXTRA_CATEGORY);

        mToolbar.setTitle(mWeightCategory.getResourceId());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        App.getInstance().getFighterRepository().get(mWeightCategory).
                subscribeOn(App.getInstance().getSchedulerProvider().multi()).
                observeOn(App.getInstance().getSchedulerProvider().ui()).
                subscribe(new Observer<List<Fighter>>() {

                    protected List<Fighter> mFighters;

                    @Override
                    public void onCompleted() {
                        if (mFighters != null) {
                            setAdapter(mFighters);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError", e);
                        Snackbar snackbar = Snackbar.make(CategoryActivity.this.findViewById(android.R.id.content), e.getLocalizedMessage(), Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                    @Override
                    public void onNext(List<Fighter> values) {
                        mFighters = values;
                    }
                });
    }

    private void setAdapter(List<Fighter> fighters) {
        mAdapter = new FightersAdapter(fighters);
        mAdapter.setOnFighterInteractionListener(fighter -> {
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
