package fr.tnducrocq.ufc.presentation.ui.champions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 17/10/2017.
 */

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionViewHolder> {

    private final List<Fighter> mFighters;

    public ChampionsAdapter(List<Fighter> fighter) {
        mFighters = fighter;
    }

    @Override
    public int getItemCount() {
        return mFighters.size();
    }


    @Override
    public ChampionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_champion, parent, false);
        return new ChampionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ChampionViewHolder holder, int position) {
        Fighter fighter = mFighters.get(position);
        holder.bindData(fighter);
    }
}
