package fr.tnducrocq.ufc.presentation.ui.main.fighters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.base.AbstractFighterAdapter;

/**
 * Created by tony on 09/08/2017.
 */

public class FightersAdapter extends AbstractFighterAdapter<Fighter> {

    private OnFighterFragmentInteractionListener mListener;

    public FightersAdapter(@NonNull Context context, OnFighterFragmentInteractionListener listener) {
        super(context);
        mListener = listener;
    }

    public void setData(List<Fighter> data) {
        this.data = data;
    }

    public class FighterViewHolder extends GenericViewHolder {

        @BindView(R.id.fighter_profile)
        ImageView posterImage;

        @BindView(R.id.fighter_name)
        TextView fighterName;

        FighterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBindData() {
            Fighter fighter = at(getAdapterPosition());
            fighterName.setText(fighter.getLastName() + " " + fighter.getFirstName());
            Glide.with(itemView.getContext()) //
                    .load(fighter.getProfileImage()) //
                    .priority(Priority.IMMEDIATE) //
                    .diskCacheStrategy(DiskCacheStrategy.RESULT) //
                    .placeholder(R.drawable.fighter_placeholder) //
                    .animate(R.anim.fade_in) //
                    .into(posterImage);
            posterImage.setOnClickListener(v -> {
                mListener.onListFragmentInteraction(fighter);
            });
            fighterName.setOnClickListener(v -> {
                mListener.onListFragmentInteraction(fighter);
            });
        }
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public FighterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.adapter_fighter_item, parent, false);
        return new FighterViewHolder(root);
    }

}
