package fr.tnducrocq.ufc.presentation.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.presentation.CategoryActivity;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.app.App;
import fr.tnducrocq.ufc.presentation.ui.main.categories.CategoriesAdapter;
import rx.Observable;
import rx.Observer;

/**
 * Created by tony on 17/10/2017.
 */

public class CategoriesFragment extends Fragment {

    private static final String TAG = "CategoriesFragment";

    @BindView(R.id.champion_list)
    RecyclerView mRecyclerView;

    private CategoriesAdapter mAdapter;
    private Unbinder unbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CategoriesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champions, container, false);
        unbinder = ButterKnife.bind(this, view);

        mAdapter = new CategoriesAdapter();
        mAdapter.setOnWeightCategoryInteractionListener(category -> {
            Intent intent = CategoryActivity.newIntent(getActivity(), category);
            startActivity(intent);
        });
        mRecyclerView.setAdapter(mAdapter);
        requestChampions();

        App.getInstance().getFighterRepository().get().subscribeOn(App.getInstance().getSchedulerProvider().multi())//
                .observeOn(App.getInstance().getSchedulerProvider().ui())//
                .subscribe(new Observer<List<Fighter>>() {
                    @Override
                    public void onCompleted() {
                        requestChampions();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Fighter> fighters) {

                    }
                });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void requestChampions() {
        Observable<List<Fighter>> obsFighters = App.getInstance().getFighterRepository().getChampions();
        obsFighters.subscribeOn(App.getInstance().getSchedulerProvider().multi())//
                .observeOn(App.getInstance().getSchedulerProvider().ui())//
                .subscribe(new Observer<List<Fighter>>() {

                    List<Fighter> mFighters;
                    Throwable mError;

                    @Override
                    public void onCompleted() {
                        if (mFighters != null) {
                            mAdapter.setFighters(mFighters);
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
