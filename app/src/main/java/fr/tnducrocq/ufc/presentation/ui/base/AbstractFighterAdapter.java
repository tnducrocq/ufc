package fr.tnducrocq.ufc.presentation.ui.base;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tony on 09/08/2017.
 */

@SuppressWarnings("WeakerAccess")
public abstract class AbstractFighterAdapter<T> extends RecyclerView.Adapter<AbstractFighterAdapter<T>.GenericViewHolder> {

    protected List<T> data;
    protected LayoutInflater inflater;
    private Handler handler = new Handler();

    public AbstractFighterAdapter(@NonNull Context context) {
        this.inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public abstract class GenericViewHolder extends RecyclerView.ViewHolder {
        public GenericViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void onBindData();
    }

    protected T at(int index) {
        return data.get(index);
    }

    public void appendData(@NonNull List<T> data) {
        int size = getItemCount();
        this.data.addAll(data);
        notifyItemRangeInserted(size, getItemCount());
    }

    public AbstractFighterAdapter<T> addItem(T item) {
        int size = data.size();
        data.add(item);
        notifyItemRangeInserted(size, getItemCount());
        return this;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }
}