package fr.tnducrocq.ufc.presentation.ui.fight;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 12/09/2017.
 */

public class FightHeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fighter_1_surname)
    TextView fighter1Surname;

    @BindView(R.id.fighter_1_name)
    TextView fighter1Name;

    @BindView(R.id.fighter_1_image)
    ImageView fighter1;

    @BindView(R.id.fighter_1_stats)
    TextView fighter1Stats;

    @BindView(R.id.fighter_2_surname)
    TextView fighter2Surname;

    @BindView(R.id.fighter_2_name)
    TextView fighter2Name;

    @BindView(R.id.fighter_2_image)
    ImageView fighter2;

    @BindView(R.id.fighter_2_stats)
    TextView fighter2Stats;

    View mView;

    FightHeaderViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final EventFight fight) {
        fighter1Surname.setText(fight.getFighter1Nickname());
        fighter1Stats.setText(String.format("%d-%d-%d (W-L-D)", fight.getFighter1Wins(), fight.getFighter1Draws(), fight.getFighter1Losses()));
        fighter1Name.setText(fight.getFighter1FirstName() + " " + fight.getFighter1LastName());
        Glide.with(fighter1.getContext()).
                load(fight.getFighter1ProfileImage()).
                asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).
                placeholder(R.drawable.fighter_placeholder).
                animate(R.anim.fade_in).
                into(new ImageViewTarget<Bitmap>(fighter1) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        fighter1.setImageBitmap(resource);
                    }
                });

        fighter2Surname.setText(fight.getFighter2Nickname());
        fighter2Stats.setText(String.format("%d-%d-%d (W-L-D)", fight.getFighter2Wins(), fight.getFighter2Draws(), fight.getFighter2Losses()));
        fighter2Name.setText(fight.getFighter2FirstName() + " " + fight.getFighter2LastName());
        Glide.with(fighter2.getContext()).
                load(fight.getFighter2ProfileImage()).
                asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).
                placeholder(R.drawable.fighter_placeholder).
                animate(R.anim.fade_in).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                into(new ImageViewTarget<Bitmap>(fighter2) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        fighter2.setImageBitmap(resource);
                    }
                });
    }

}
