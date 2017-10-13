package fr.tnducrocq.ufc.presentation.ui.event;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 18/09/2017.
 */

public class EventMediasRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<EventMedia> mMedias;
    private final EventMediasRecyclerViewAdapter.OnEventFightsInteractionListener mListener;

    public interface OnEventFightsInteractionListener {
        void onListFragmentInteraction(EventFight item);
    }

    public EventMediasRecyclerViewAdapter(List<EventMedia> medias, EventMediasRecyclerViewAdapter.OnEventFightsInteractionListener listener) {
        mMedias = medias;
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mMedias.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_event_media, parent, false);
        return new EventMediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        ((EventMediaViewHolder) holder).bindData(mMedias.get(position));
    }

}
