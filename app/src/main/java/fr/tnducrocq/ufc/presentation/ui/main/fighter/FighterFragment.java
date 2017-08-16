package fr.tnducrocq.ufc.presentation.ui.main.fighter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import fr.tnducrocq.ufc.presentation.App;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.main.event.EventFragment;
import rx.Observable;
import rx.Observer;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link EventFragment.OnEventFragmentInteractionListener}
 * interface.
 */
public class FighterFragment extends Fragment {
    private static final String TAG = "FighterFragment";
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


    public interface OnFighterFragmentInteractionListener {
        void onSeeAllFragmentInteraction(WeightCategory category);

        void onListFragmentInteraction(Fighter item);
    }

    private FighterFragment.OnFighterFragmentInteractionListener mListener;
    private Map<WeightCategory, FighterAdapter> mFighterAdapters;
    private FighterTypeAdapter mFighterCategoryAdapter;

    @BindView(R.id.boxers_list)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FighterFragment() {
    }

    public static FighterFragment newInstance(int columnCount) {
        FighterFragment fragment = new FighterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
            mFighterCategoryAdapter = new FighterTypeAdapter(context, mListener);
            mRecyclerView.setAdapter(mFighterCategoryAdapter);

            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            for (WeightCategory category : CATEGORIES) {
                mFighterAdapters.put(category, new FighterAdapter(context, mListener));
                requestCategory(category);
            }
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EventFragment.OnEventFragmentInteractionListener) {
            mListener = (FighterFragment.OnFighterFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement EventFragment.OnEventFragmentInteractionListener");
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
                            Collections.sort(mFighters, (f1, f2) -> {
                                return f1.getLastName().compareTo(f2.getLastName());
                            });
                            mFighterAdapters.get(category).appendData(mFighters);
                            FighterAdapter adapter = mFighterAdapters.get(category);

                            String categoryTitle = getContext().getString(category.getResourceId());
                            FighterTypeAdapter.FighterCategoryWrapper wrapper = FighterTypeAdapter.FighterCategoryWrapper.wrap(categoryTitle, category, adapter, R.color.red_color);
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
}
