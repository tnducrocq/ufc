package fr.tnducrocq.ufc.presentation.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import fr.tnducrocq.ufc.presentation.FighterActivity;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.main.events.AbstractEventsFragment;
import fr.tnducrocq.ufc.presentation.ui.main.fighters.CategoriesAdapter;
import fr.tnducrocq.ufc.presentation.ui.main.fighters.FightersAdapter;
import fr.tnducrocq.ufc.presentation.ui.main.fighters.OnFighterFragmentInteractionListener;
import rx.Observable;
import rx.Observer;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link AbstractEventsFragment.OnEventFragmentInteractionListener}
 * interface.
 */
public class FightersFragment extends Fragment implements OnFighterFragmentInteractionListener {
    private static final String TAG = "FightersFragment";
    private static final WeightCategory[] CATEGORIES = new WeightCategory[]{
            // MEN
            WeightCategory.HEAVYWEIGHT, //
            WeightCategory.LIGHT_HEAVYWEIGHT,//
            WeightCategory.MIDDLEWEIGHT, //
            WeightCategory.WELTERWEIGHT, //
            WeightCategory.LIGHTWEIGHT, //
            WeightCategory.FEATHERWEIGHT, //
            WeightCategory.BANTAMWEIGHT, //
            WeightCategory.FLYWEIGHT,//
            // WOMEN
            WeightCategory.WOMEN_FEATHERWEIGHT,//
            WeightCategory.WOMEN_BANTAMWEIGHT,//
            WeightCategory.WOMEN_STRAWWEIGHT//
    };

    private OnFighterFragmentInteractionListener mListener;
    private Map<WeightCategory, FightersAdapter> mFighterAdapters;
    private CategoriesAdapter mFighterCategoryAdapter;

    @BindView(R.id.boxers_list)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FightersFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fighters, container, false);
        unbinder = ButterKnife.bind(this, view);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();

            mFighterAdapters = new LinkedHashMap<>();
            mFighterCategoryAdapter = new CategoriesAdapter(context, this);
            mRecyclerView.setAdapter(mFighterCategoryAdapter);

            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            for (WeightCategory category : CATEGORIES) {
                mFighterAdapters.put(category, new FightersAdapter(context, this));
                requestCategory(category);
            }
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AbstractEventsFragment.OnEventFragmentInteractionListener) {
            mListener = (OnFighterFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AbstractEventsFragment.OnEventFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void requestCategory(WeightCategory category) {
        Observable<List<Fighter>> obsFighters = App.getInstance().getFighterRepository().get(category);
        obsFighters.subscribeOn(App.getInstance().getSchedulerProvider().multi())//
                .observeOn(App.getInstance().getSchedulerProvider().ui())//
                .subscribe(new Observer<List<Fighter>>() {

                    List<Fighter> mFighters;
                    Throwable mError;

                    @Override
                    public void onCompleted() {
                        if (mFighters != null) {
                            mFighterAdapters.get(category).appendData(mFighters);
                            FightersAdapter adapter = mFighterAdapters.get(category);

                            String categoryTitle = getContext().getString(category.getResourceId());
                            CategoriesAdapter.FighterCategoryWrapper wrapper = CategoriesAdapter.FighterCategoryWrapper.wrap(categoryTitle, category, adapter, R.color.red_color);
                            mFighterCategoryAdapter.addItem(wrapper);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        this.mError = e;
                        Log.e(TAG, "ERROR", e);
                    }

                    @Override
                    public void onNext(List<Fighter> fighters) {
                        this.mFighters = fighters;
                    }
                });
    }


    public void onSeeAllFragmentInteraction(WeightCategory category) {
        if (mListener != null) {
            mListener.onSeeAllFragmentInteraction(category);
        }
    }

    public void onListFragmentInteraction(Fighter item) {
        if (mListener != null) {
            mListener.onListFragmentInteraction(item);
        }

        Intent intent = FighterActivity.newIntent(getActivity(), item);
        startActivity(intent);
    }
}
