package fr.tnducrocq.ufc.presentation.ui.event;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.main.events.AbstractEventsFragment;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link AbstractEventsFragment.OnEventFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventFightsRecyclerViewAdapter extends RecyclerView.Adapter<EventFightViewHolder> {

    private final List<EventFight> mFights;
    private final EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener mListener;

    public interface OnEventFightsInteractionListener {
        void onListFragmentInteraction(EventFight item);
    }

    public EventFightsRecyclerViewAdapter(EventInformations details, EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener listener) {
        mFights = details.fights;
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mFights.size();
    }

    @Override
    public EventFightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_item, parent, false);
        return new EventFightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventFightViewHolder holder, int position) {
        EventFight fight = mFights.get(position);
        EventFightViewHolder eHolder = (EventFightViewHolder) holder;
        eHolder.bindData(fight);
        eHolder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onListFragmentInteraction(fight);
            }
        });

    }

}
