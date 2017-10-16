package fr.tnducrocq.ufc.presentation.ui.main.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.presentation.R;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link AbstractEventsFragment.OnEventFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private final List<Event> mValues;
    private final Context mContext;
    private final AbstractEventsFragment.OnEventFragmentInteractionListener mListener;


    public EventsRecyclerViewAdapter(List<Event> items, Context context, AbstractEventsFragment.OnEventFragmentInteractionListener listener) {
        mValues = items;
        mContext = context;
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventViewHolder holder, int position) {
        Event event = mValues.get(position);
        holder.bindData(event, mListener);
    }
}
