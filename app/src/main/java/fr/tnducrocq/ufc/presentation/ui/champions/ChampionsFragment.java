package fr.tnducrocq.ufc.presentation.ui.champions;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.app.App;
import rx.Observable;
import rx.Observer;

/**
 * Created by tony on 17/10/2017.
 */

public class ChampionsFragment extends Fragment {

    private static final String TAG = "ChampionsFragment";
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


    @BindView(R.id.champion_list)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChampionsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champions, container, false);
        unbinder = ButterKnife.bind(this, view);

        requestChampions();

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
                            displayChampions(mFighters);
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

    private void displayChampions(List<Fighter> fighters) {
        List<Fighter> champions = new ArrayList<>();
        for (WeightCategory category : CATEGORIES) {
            Optional<Fighter> filtered = Stream.of(fighters).filter(value -> category == value.getWeightClass()).findFirst();
            if (filtered.isPresent()) {
                champions.add(filtered.get());
            }
        }

        ChampionsAdapter adapter = new ChampionsAdapter(champions);
        mRecyclerView.setAdapter(adapter);
    }

}
