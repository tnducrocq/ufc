package fr.tnducrocq.ufc.presentation.ui.event;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.main.events.EventsFragment;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link EventsFragment.OnEventFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventFightsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<EventFight> mFights;
    private final List<EventMedia> mMedias = new ArrayList<>();
    private final EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener mListener;

    public interface OnEventFightsInteractionListener {
        void onListFragmentInteraction(EventFight item);
    }

    public EventFightsRecyclerViewAdapter(EventInformations details, EventFightsRecyclerViewAdapter.OnEventFightsInteractionListener listener) {
        mFights = details.fights;
        for (EventMedia media : details.medias) {
            if (media.getMobileVideoUrl() != null) {
                mMedias.add(media);
            }
        }
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        if (mMedias.isEmpty()) {
            return mFights.size();
        }
        return mFights.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mMedias.isEmpty()) {
            return 1;
        }
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
            boolean hasMedia = !mMedias.isEmpty();
            int index = hasMedia ? position - 1 : position;
            EventFight fight = mFights.get(index);
            EventFightViewHolder eHolder = (EventFightViewHolder) holder;
            eHolder.bindData(fight);
            eHolder.itemView.setOnClickListener(view -> {
                if (mListener != null) {
                    mListener.onListFragmentInteraction(fight);
                }
            });
        }
    }

}
