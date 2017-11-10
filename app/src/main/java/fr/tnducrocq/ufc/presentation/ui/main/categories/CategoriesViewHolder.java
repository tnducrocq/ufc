package fr.tnducrocq.ufc.presentation.ui.main.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

public class CategoriesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.champion_category)
    TextView championCategory;

    @BindView(R.id.champion_name)
    TextView championName;

    @BindView(R.id.champion_stats)
    TextView championStats;

    @BindView(R.id.champion_image)
    ImageView championImage;

    View view;

    CategoriesViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final Fighter fighter) {
        Context context = championImage.getContext();

        championCategory.setText(context.getString(fighter.getWeightClass().getResourceId()));
        championName.setText(fighter.getFirstName() + " " + fighter.getLastName());
        championStats.setText(context.getResources().getString(R.string.fighter_score, fighter.getWins(), fighter.getLosses(), fighter.getDraws()));

        Glide.with(context).
                load(fighter.getLeftFullBodyImage()).
                asBitmap().
                priority(Priority.IMMEDIATE).
                diskCacheStrategy(DiskCacheStrategy.SOURCE).
                placeholder(R.drawable.fighter_placeholder_red).
                animate(R.anim.fade_in).
                transform(new FighterImageTransformation(context)).
                into(championImage);

    }

}
