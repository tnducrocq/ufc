package fr.tnducrocq.ufc.presentation.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import fr.tnducrocq.ufc.presentation.App;
import fr.tnducrocq.ufc.presentation.R;
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

    private EventFragment.OnEventFragmentInteractionListener mListener;

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

        App mApplication = (App) getActivity().getApplication();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            Observable<List<Fighter>> obsFighters = mApplication.getFighterRepository().get(WeightCategory.MIDDLEWEIGHT);
            obsFighters.subscribeOn(mApplication.getSchedulerProvider().multi())//
                    .observeOn(mApplication.getSchedulerProvider().ui())//
                    .subscribe(new Observer<List<Fighter>>() {

                        List<Fighter> mValue;
                        Throwable mError;

                        @Override
                        public void onCompleted() {
                            mValue.sort(new Comparator<Fighter>() {
                                @Override
                                public int compare(Fighter f1, Fighter f2) {
                                    return f1.getLastName().compareTo(f2.getLastName());
                                }
                            });

                            for (Fighter f : mValue) {
                                Log.d(TAG, f.toString());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            this.mError = e;
                            Log.e(TAG, "ERROR", e);
                        }

                        @Override
                        public void onNext(List<Fighter> fighters) {
                            this.mValue = fighters;
                        }
                    });

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EventFragment.OnEventFragmentInteractionListener) {
            mListener = (EventFragment.OnEventFragmentInteractionListener) context;
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
}
