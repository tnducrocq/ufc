package fr.tnducrocq.ufc.presentation.ui.fight;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.presentation.R;

public class FightRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final EventFight mFight;

    public FightRecyclerViewAdapter(EventFight fight) {
        mFight = fight;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_header, parent, false);
            return new FightHeaderViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_row, parent, false);
            return new FightRowViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 0) {
            FightHeaderViewHolder cHolder = (FightHeaderViewHolder) holder;
            cHolder.bindData(mFight);
        } else {
            FightRowViewHolder cHolder = (FightRowViewHolder) holder;
            cHolder.bindData("x", "label", "y");
        }

    }

}
