package fr.tnducrocq.ufc.presentation.ui.category;

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

public class FightersAdapter extends RecyclerView.Adapter<FighterViewHolder> {

    private final List<Fighter> mFighters;
    private OnFighterInteractionListener mOnFighterInteractionListener;

    public FightersAdapter(List<Fighter> fighter) {
        mFighters = fighter;
    }

    @Override
    public int getItemCount() {
        return mFighters.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    @Override
    public FighterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fighter_champion, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fighter, parent, false);
        }
        return new FighterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FighterViewHolder holder, int position) {
        Fighter fighter = mFighters.get(position);
        holder.bindData(fighter);
        holder.view.setOnClickListener(v -> {
            if (mOnFighterInteractionListener != null) {
                mOnFighterInteractionListener.onFighterInteraction(fighter);
            }
        });
    }

    public void setOnFighterInteractionListener(OnFighterInteractionListener onFighterInteractionListener) {
        this.mOnFighterInteractionListener = onFighterInteractionListener;
    }

    public interface OnFighterInteractionListener {
        void onFighterInteraction(Fighter fighter);
    }
}
