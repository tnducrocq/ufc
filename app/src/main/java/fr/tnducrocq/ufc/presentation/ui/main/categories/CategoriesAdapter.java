package fr.tnducrocq.ufc.presentation.ui.main.categories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.WeightCategory;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 17/10/2017.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesViewHolder> {

    private List<Fighter> mFighters;
    private OnWeightCategoryInteractionListener mOnWeightCategoryInteractionListener;

    public CategoriesAdapter() {
    }

    public void setFighters(List<Fighter> fighters) {
        mFighters = fighters;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mFighters == null ? 0 : mFighters.size();
    }


    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_champion, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoriesViewHolder holder, int position) {
        Fighter fighter = mFighters.get(position);
        holder.bindData(fighter);
        holder.view.setOnClickListener(v -> {
            if (mOnWeightCategoryInteractionListener != null) {
                mOnWeightCategoryInteractionListener.onWeightCategoryInteraction(fighter.getWeightClass());
            }
        });
    }

    public void setOnWeightCategoryInteractionListener(OnWeightCategoryInteractionListener onWeightCategoryInteractionListener) {
        this.mOnWeightCategoryInteractionListener = onWeightCategoryInteractionListener;
    }

    public interface OnWeightCategoryInteractionListener {
        void onWeightCategoryInteraction(WeightCategory category);
    }
}
