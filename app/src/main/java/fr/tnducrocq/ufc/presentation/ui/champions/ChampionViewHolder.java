package fr.tnducrocq.ufc.presentation.ui.champions;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 17/10/2017.
 */

public class ChampionViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.champion_image)
    ImageView championImage;

    ChampionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final Fighter fighter) {
        Glide.with(championImage.getContext()).
                load(fighter.getLeftFullBodyImage()).//
                asBitmap().//
                priority(Priority.IMMEDIATE).//

                diskCacheStrategy(DiskCacheStrategy.SOURCE).//
                placeholder(R.drawable.fighter_placeholder_red).//
                animate(R.anim.fade_in).//
                transform(new ChampionImageTransformation(championImage.getContext())).
                into(championImage);

        /*Glide.with(itemView.getContext()) //
                .load(fighter.getProfileImage()) //
                .centerCrop()
                .priority(Priority.IMMEDIATE) //
                .diskCacheStrategy(DiskCacheStrategy.RESULT) //
                .placeholder(R.drawable.fighter_placeholder) //
                .animate(R.anim.fade_in) //
                .into(championImage);*/

    }

}
