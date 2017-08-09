package fr.tnducrocq.ufc.presentation.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fr.tnducrocq.ufc.data.Provider;
import fr.tnducrocq.ufc.data.entity.event.Events;
import fr.tnducrocq.ufc.presentation.R;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tony on 01/08/2017.
 */

public class PastEventFragment extends Fragment {

    public static FutureEventFragment newFragment() {
        FutureEventFragment fragment = new FutureEventFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private EventFragment.OnEventFragmentInteractionListener mListener;


    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    private Unbinder unbinder;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PastEventFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_event, container, false);
        unbinder = ButterKnife.bind(this, view);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            Observable<Events> obsEvents = Provider.getInstance().requestEvents();
            Subscription mSubscription = obsEvents.subscribeOn(Schedulers.newThread())//
                    .observeOn(AndroidSchedulers.mainThread())//
                    .subscribe(new Observer<Events>() {
                        Events mValue;

                        @Override
                        public void onCompleted() {
                            System.out.println("onCompleted()");
                            recyclerView.setAdapter(new EventRecyclerViewAdapter(mValue.pastEvents, mListener));
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println("onError()");

                        }

                        @Override
                        public void onNext(Events value) {
                            System.out.println("onNext()");
                            mValue = value;
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