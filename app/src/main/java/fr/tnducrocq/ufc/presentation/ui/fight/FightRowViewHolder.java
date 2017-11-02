package fr.tnducrocq.ufc.presentation.ui.fight;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 12/09/2017.
 */

public class FightRowViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fight_row_fighter1)
    TextView fighter1;

    @BindView(R.id.fight_row_title)
    TextView title;

    @BindView(R.id.fight_row_fighter2)
    TextView fighter2;

    View mView;

    FightRowViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void bindData(String info1, String label, String info2) {
        fighter1.setText(info1);
        title.setText(label);
        fighter2.setText(info2);
    }

}
