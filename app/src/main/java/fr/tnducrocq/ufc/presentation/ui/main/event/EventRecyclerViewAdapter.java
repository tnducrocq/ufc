package fr.tnducrocq.ufc.presentation.ui.main.event;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.vpaliy.chips_lover.ChipBuilder;
import com.vpaliy.chips_lover.ChipsLayout;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.entity.event.Event;
import fr.tnducrocq.ufc.presentation.R;

import static fr.tnducrocq.ufc.presentation.ui.utils.ColorUtils.fetchDominantSwatch;
import static fr.tnducrocq.ufc.presentation.ui.utils.ColorUtils.setDrawableColor;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Event} and makes a call to the
 * specified {@link EventFragment.OnEventFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder> {

    private final List<Event> mValues;
    private final EventFragment.OnEventFragmentInteractionListener mListener;


    public EventRecyclerViewAdapter(List<Event> items, EventFragment.OnEventFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.event_poster)
        ImageView posterImage;

        @BindView(R.id.event_title)
        TextView eventTitle;

        @BindView(R.id.event_tag_line)
        TextView eventTag;

        @BindView(R.id.event_release_year)
        TextView releaseYear;

        @BindView(R.id.event_ratings)
        TextView ratings;

        @BindView(R.id.chipsContainer)
        ChipsLayout chipsContainer;

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
                background.setBackgroundColor(swatch.getRgb());
                ChipBuilder builder = chipsContainer.getChipBuilder().setBackgroundColor(swatch.getTitleTextColor()).setTextColor(swatch.getBodyTextColor());
                chipsContainer.updateChipColors(builder);
                eventTitle.setTextColor(swatch.getTitleTextColor());
                eventTag.setTextColor(swatch.getBodyTextColor());
                releaseYear.setTextColor(swatch.getBodyTextColor());
                ratings.setTextColor(swatch.getBodyTextColor());
                setDrawableColor(releaseYear, swatch.getBodyTextColor());
                setDrawableColor(ratings, swatch.getBodyTextColor());
            } else {
                Context context = background.getContext();
                background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorEventItemBackground));
                ChipBuilder builder = chipsContainer.getChipBuilder().
                        setBackgroundColor(ContextCompat.getColor(context, R.color.colorEventItemTitleColor)).
                        setTextColor(ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
                chipsContainer.updateChipColors(builder);
                eventTitle.setTextColor(ContextCompat.getColor(context, R.color.colorEventItemTitleColor));
                eventTag.setTextColor(ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
                releaseYear.setTextColor(ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
                ratings.setTextColor(ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
                setDrawableColor(releaseYear, ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
                setDrawableColor(ratings, ContextCompat.getColor(context, R.color.colorEventItemBodyColor));
            }
        }

        public void bindData(final Event event) {

            String date = DateFormatUtils.format(event.getEventDate(), "dd/MM/yyyy");
            releaseYear.setText(date);
            eventTitle.setText(event.getBaseTitle());
            eventTag.setText(event.getTitleTagLine());

            Glide.with(itemView.getContext()).load(event.getFeatureImage()).asBitmap().priority(Priority.IMMEDIATE).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.placeholder).animate(R.anim.fade_in).into(new ImageViewTarget<Bitmap>(posterImage) {
                @Override
                protected void setResource(Bitmap resource) {
                    posterImage.setImageBitmap(resource);
                    new Palette.Builder(resource).generate(EventViewHolder.this::applyPalette);
                }
            });

            posterImage.setOnClickListener(v -> {
                mListener.onListFragmentInteraction(event);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_event_item, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventViewHolder holder, int position) {
        Event event = mValues.get(position);
        holder.bindData(event);
    }
}
