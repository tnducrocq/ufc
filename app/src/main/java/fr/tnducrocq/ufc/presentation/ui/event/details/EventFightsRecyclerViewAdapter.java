package fr.tnducrocq.ufc.presentation.ui.event.details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.main.event.EventFragment;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link EventFragment.OnEventFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventFightsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<EventFight> mFights;
    private final List<EventMedia> mMedias;
    private final EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener mListener;

    public interface OnEventFightsInteractionListener {
        void onListFragmentInteraction(EventFight item);
    }

    public EventFightsRecyclerViewAdapter(EventInformations details, EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener listener) {
        mFights = details.fights;
        mMedias = details.medias;
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mFights.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_event_medias, parent, false);
            return new EventMediasViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_item, parent, false);
            return new EventFightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == 0) {
            ((EventMediasViewHolder) holder).bindData(mMedias);
        } else {
            EventFight fight = mFights.get(position - 1);
            ((EventFightViewHolder) holder).bindData(fight);
        }
    }

}
