package fr.tnducrocq.ufc.presentation.ui.event.details;

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

public class EventFightViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fighter1)
    ImageView fighter1;
    @BindView(R.id.fighter1Name)
    TextView fighter1Name;
    @BindView(R.id.fighter1Record)
    TextView fighter1Record;
    @BindView(R.id.fighter1Height)
    TextView fighter1Height;
    @BindView(R.id.fighter1Weight)
    TextView fighter1Weight;
    @BindView(R.id.fighter1Reach)
    TextView fighter1Reach;

    @BindView(R.id.fighter2)
    ImageView fighter2;
    @BindView(R.id.fighter2Name)
    TextView fighter2Name;
    @BindView(R.id.fighter2Record)
    TextView fighter2Record;
    @BindView(R.id.fighter2Height)
    TextView fighter2Height;
    @BindView(R.id.fighter2Weight)
    TextView fighter2Weight;
    @BindView(R.id.fighter2Reach)
    TextView fighter2Reach;

    //@BindView(R.id.fightMore)
    //Button fightMore;

    EventFightViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final EventFight fight) {
        Glide.with(itemView.getContext()).
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
        fighter1Name.setText(fight.getFighter1LastName());
        fighter1Record.setText(fight.getFighter1record());
        fighter1Height.setText(toCm(fight.getFighter1height()));
        fighter1Weight.setText(toKg(fight.getFighter1weight()));
        fighter1Reach.setText(toCm(fight.getFighter1reach()));

        Glide.with(itemView.getContext()).
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
        fighter2Name.setText(fight.getFighter2LastName());
        fighter2Record.setText(fight.getFighter2record());
        fighter2Height.setText(toCm(fight.getFighter2height()));
        fighter2Weight.setText(toKg(fight.getFighter2weight()));
        fighter2Reach.setText(toCm(fight.getFighter2reach()));

            /*if (fight.getResult() != null) {
                fightMore.setVisibility(View.VISIBLE);
            } else {
                fightMore.setVisibility(View.INVISIBLE);
            }*/
    }

    private String toKg(Integer lbs) {
        if (lbs == null) return "";
        double kg = 0.453592 * (double) lbs;
        return Integer.toString((int) kg) + " kg";
    }

    private String toCm(Integer in) {
        if (in == null) return "";
        double cm = 2.54 * (double) in;
        return Integer.toString((int) cm) + " cm";
    }

    private String toString(Integer value) {
        return value == null ? "" : Integer.toString(value);
    }

}
