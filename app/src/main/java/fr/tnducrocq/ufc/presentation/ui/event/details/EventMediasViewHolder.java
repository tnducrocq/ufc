package fr.tnducrocq.ufc.presentation.ui.event.details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.EventMedia;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 18/09/2017.
 */

public class EventMediasViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.media_list)
    RecyclerView list;

    EventMediasViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        list.setNestedScrollingEnabled(false);
    }

    public void bindData(final List<EventMedia> medias) {
        EventMediasRecyclerViewAdapter adapter = new EventMediasRecyclerViewAdapter(medias, null);
        list.setLayoutManager(new LinearLayoutManager(list.getContext(), LinearLayoutManager.HORIZONTAL, true));
        list.setAdapter(adapter);
    }

}
