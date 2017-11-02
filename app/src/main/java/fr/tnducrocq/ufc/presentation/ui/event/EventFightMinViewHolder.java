package fr.tnducrocq.ufc.presentation.ui.event;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class EventFightMinViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.event_fighter_1_image)
    ImageView fighter1Image;
    @BindView(R.id.event_fighter_1_name)
    TextView fighter1Name;

    @BindView(R.id.event_fighter_2_image)
    ImageView fighter2Image;
    @BindView(R.id.event_fighter_2_name)
    TextView fighter2Name;

    @BindView(R.id.event_layout)
    LinearLayout layout;

    View mView;

    EventFightMinViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(final EventFight fight) {
        Glide.with(itemView.getContext()).
                load(fight.getFighter1ProfileImage()).
                asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).
                placeholder(R.drawable.fighter_placeholder).
                animate(R.anim.fade_in).
                into(new ImageViewTarget<Bitmap>(fighter1Image) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        fighter1Image.setImageBitmap(resource);
                    }
                });
        fighter1Name.setText(fight.getFighter1LastName());

        Glide.with(itemView.getContext()).
                load(fight.getFighter2ProfileImage()).
                asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).
                placeholder(R.drawable.fighter_placeholder).
                animate(R.anim.fade_in).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                into(new ImageViewTarget<Bitmap>(fighter2Image) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        fighter2Image.setImageBitmap(resource);
                    }
                });
        fighter2Name.setText(fight.getFighter2LastName());
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
