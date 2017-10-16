package fr.tnducrocq.ufc.presentation.ui.main.events;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import org.apache.commons.lang3.time.DateFormatUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.presentation.EventActivity;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.utils.PaletteColors;

import static fr.tnducrocq.ufc.presentation.ui.utils.ColorUtils.fetchDominantSwatch;
import static fr.tnducrocq.ufc.presentation.ui.utils.ColorUtils.setDrawableColor;

/**
 * Created by tony on 13/10/2017.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.event_fights_image)
    ImageView posterImage;

    @BindView(R.id.event_title)
    TextView eventTitle;

    @BindView(R.id.event_tag_line)
    TextView eventTag;

    @BindView(R.id.event_release_year)
    TextView releaseYear;

    Palette.Swatch mSwatch;

//        @BindView(R.id.event_ratings)
//        TextView ratings;


    @BindView(R.id.details_background)
    View background;

    EventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    //that's where the magic happens
    private void applyPalette(Palette palette) {

        Palette.Swatch swatch = fetchDominantSwatch(palette);
        //apply if not null
        if (swatch != null) {
            mSwatch = swatch;
            background.setBackgroundColor(swatch.getRgb());

            eventTitle.setTextColor(swatch.getTitleTextColor());
            eventTag.setTextColor(swatch.getBodyTextColor());
            releaseYear.setTextColor(swatch.getBodyTextColor());
            //ratings.setTextColor(swatch.getBodyTextColor());
            setDrawableColor(releaseYear, swatch.getBodyTextColor());
            //setDrawableColor(ratings, swatch.getBodyTextColor());
        } else {
            Context context = background.getContext();
            background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorEventItemBackground));

            eventTitle.setTextColor(ContextCompat.getColor(context, R.color.colorEventItemTitleColor));
            eventTag.setTextColor(ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
            releaseYear.setTextColor(ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
            //ratings.setTextColor(ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
            setDrawableColor(releaseYear, ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
            //setDrawableColor(ratings, ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
        }
    }

    public void bindData(final Event event, AbstractEventsFragment.OnEventFragmentInteractionListener listener) {

        String date = DateFormatUtils.format(event.getEventDate(), "dd/MM/yyyy");
        releaseYear.setText(date);
        eventTitle.setText(event.getBaseTitle());
        eventTag.setText(event.getTitleTagLine());

        Bitmap placeholder = BitmapFactory.decodeResource(background.getContext().getResources(), R.drawable.event_placeholder);
        posterImage.setImageBitmap(placeholder);
        new Palette.Builder(placeholder).generate(EventViewHolder.this::applyPalette);

        Glide.with(itemView.getContext()) //
                .load(event.getFeatureImage()) //
                .asBitmap() //
                .priority(Priority.IMMEDIATE) //
                .diskCacheStrategy(DiskCacheStrategy.ALL) //
                .placeholder(R.drawable.event_placeholder) //
                .animate(R.anim.fade_in) //
                .into(new ImageViewTarget<Bitmap>(posterImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        posterImage.setImageBitmap(resource);
                        new Palette.Builder(resource).generate(EventViewHolder.this::applyPalette);
                    }
                });

        posterImage.setOnClickListener(v -> {
            Intent intent = EventActivity.newIntent(v.getContext(), event, PaletteColors.fromSwatch(mSwatch));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) v.getContext(), (View) posterImage, "profile");
            v.getContext().startActivity(intent, options.toBundle());
            listener.onListFragmentInteraction(event);
        });
    }
}