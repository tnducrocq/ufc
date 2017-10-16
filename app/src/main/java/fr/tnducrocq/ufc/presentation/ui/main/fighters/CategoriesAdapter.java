package fr.tnducrocq.ufc.presentation.ui.main.fighters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.data.utils.WeightCategory;
import fr.tnducrocq.ufc.presentation.R;
import fr.tnducrocq.ufc.presentation.ui.base.AbstractFighterAdapter;
import fr.tnducrocq.ufc.presentation.ui.main.FightersFragment;

/**
 * Created by tony on 09/08/2017.
 */

public class CategoriesAdapter extends AbstractFighterAdapter<CategoriesAdapter.FighterCategoryWrapper> {

    private final FightersFragment.OnFighterFragmentInteractionListener mListener;

    public CategoriesAdapter(@NonNull Context context, FightersFragment.OnFighterFragmentInteractionListener listener) {
        super(context);
        mListener = listener;
    }

    class CategoryViewHolder extends GenericViewHolder implements View.OnClickListener {

        @BindView(R.id.fighter_list)
        RecyclerView list;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.more)
        TextView more;

        CategoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            list.setNestedScrollingEnabled(false);
            more.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            FighterCategoryWrapper wrapper = at(getAdapterPosition());
            mListener.onSeeAllFragmentInteraction(wrapper.sortType);
        }

        @Override
        public void onBindData() {
            FighterCategoryWrapper wrapper = at(getAdapterPosition());
            list.setAdapter(wrapper.adapter);
            title.setText(wrapper.text);
            more.setTextColor(wrapper.color);

        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.adapter_fighter_type_item, parent, false);
        return new CategoryViewHolder(root);
    }

    public static class FighterCategoryWrapper {
        private final String text;
        private final int color;
        private final WeightCategory sortType;
        private final RecyclerView.Adapter<?> adapter;

        private FighterCategoryWrapper(@NonNull String text, @NonNull RecyclerView.Adapter<?> adapter, @NonNull WeightCategory sortType, int color) {
            this.text = text;
            this.adapter = adapter;
            this.sortType = sortType;
            this.color = color;
        }

        public static FighterCategoryWrapper wrap(@NonNull String text, @NonNull WeightCategory sortType, @NonNull RecyclerView.Adapter<?> adapter, int color) {
            return new FighterCategoryWrapper(text, adapter, sortType, color);
        }
    }
}
