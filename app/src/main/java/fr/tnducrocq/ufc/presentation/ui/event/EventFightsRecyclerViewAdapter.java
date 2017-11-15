package fr.tnducrocq.ufc.presentation.ui.event;

import android.support.v7.widget.GridLayoutManager;
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

    public EventFightsRecyclerViewAdapter(List<EventFight> fights, EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener listener) {
        mFights = fights;
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return mFights.size();
    }

    @Override
    public EventFightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_card, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_min_card, parent, false);
        }
        return new EventFightViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final EventFightViewHolder holder, int position) {
        EventFight fight = mFights.get(position);
        holder.bindData(fight);

        holder.mView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onListFragmentInteraction(fight);
            }
        });

        if (position != 0 && position % 2 == 1) {
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.layout.getLayoutParams();
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin / 2, params.bottomMargin);
            holder.layout.setLayoutParams(params);
        } else if (position != 0 && position % 2 == 0) {
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.layout.getLayoutParams();
            params.setMargins(params.leftMargin / 2, params.topMargin, params.rightMargin, params.bottomMargin);
            holder.layout.setLayoutParams(params);
        }
    }

}
