package fr.tnducrocq.ufc.presentation.ui.fight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.event.EventFight;
import fr.tnducrocq.ufc.presentation.R;

public class FightRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;

    private EventFight mFight;
    private List<FightLine> mLines;

    private FightHeaderViewHolder.OnFighterInteractionListener mOnFighterInteractionListener;

    public FightRecyclerViewAdapter(@NonNull Context context) {
        mContext = context;
    }

    public void setFight(@NonNull EventFight fight) {
        mFight = fight;
        mLines = !isFinish() ? getItemsFight(mContext, fight) : getItemsFightFinished(mContext, fight);
        notifyDataSetChanged();
    }

    public boolean isFinish() {
        return mFight.getResult() != null && mFight.getResult().getMethod() != null;
    }

    @Override
    public int getItemViewType(int position) {
        if (isFinish()) {
            return (position == 0 || position == 1) ? position : 2;
        }
        return position == 0 ? 0 : 2;
    }

    @Override
    public int getItemCount() {
        if (mFight == null) {
            return 0;
        }

        if (isFinish()) {
            return 1 + 1 + mLines.size();
        }
        return 1 + mLines.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_header, parent, false);
            FightHeaderViewHolder holder = new FightHeaderViewHolder(view);
            holder.setOnFighterInteractionListener(mOnFighterInteractionListener);
            return holder;
        } else if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_status, parent, false);
            return new FightRowViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fight_row, parent, false);
            return new FightRowViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 0) {
            FightHeaderViewHolder cHolder = (FightHeaderViewHolder) holder;
            cHolder.bindData(mFight);
        } else if (type == 1) {
            FightRowViewHolder cHolder = (FightRowViewHolder) holder;
            if (mFight.getFighter1IsWinner() == mFight.getFighter2IsWinner()) {
                cHolder.bindData(mContext.getString(R.string.fighter_draw), "", mContext.getString(R.string.fighter_draw));
            } else if (mFight.getFighter1IsWinner()) {
                cHolder.bindData(mContext.getString(R.string.fighter_win), "", mContext.getString(R.string.fighter_loose));
            } else {
                cHolder.bindData(mContext.getString(R.string.fighter_loose), "", mContext.getString(R.string.fighter_win));
            }
        } else {
            FightLine line = mLines.get(position - (isFinish() ? 2 : 1));
            FightRowViewHolder cHolder = (FightRowViewHolder) holder;
            cHolder.bindData(line.mLine1info, line.mLineTitle, line.mLine2info);
        }
    }

    private static List<FightLine> getItemsFight(Context context, EventFight fight) {
        List<FightLine> lines = new ArrayList<>();
        if (fight.getFighter1() == null && fight.getFighter2() == null) {
            return lines;
        }

        String[] locations = {"/", "/"};
        if (fight.getFighter1() != null && fight.getFighter1().getLocation() != null) {
            locations[0] = fight.getFighter1().getLocation();
        }
        if (fight.getFighter2() != null && fight.getFighter2().getLocation() != null) {
            locations[1] = fight.getFighter2().getLocation();
        }
        lines.add(new FightLine.Builder().withLineTitle(context.getString(R.string.fighter_country)).withLine1info(locations[0]).withLine2info(locations[1]).build());

        String[] height = {"/", "/"};
        if (fight.getFighter1() != null && fight.getFighter1().getHeight_cm() != null) {
            height[0] = Integer.toString(fight.getFighter1().getHeight_cm()) + "cm";
        }
        if (fight.getFighter2() != null && fight.getFighter2().getHeight_cm() != null) {
            height[1] = Integer.toString(fight.getFighter2().getHeight_cm()) + "cm";
        }
        lines.add(new FightLine.Builder().withLineTitle(context.getString(R.string.fighter_height)).withLine1info(height[0]).withLine2info(height[1]).build());

        String[] weight = {"/", "/"};
        if (fight.getFighter1() != null && fight.getFighter1().getWeight_kg() != null) {
            weight[0] = Integer.toString(fight.getFighter1().getWeight_kg()) + "kg";
        }
        if (fight.getFighter2() != null && fight.getFighter2().getWeight_kg() != null) {
            weight[1] = Integer.toString(fight.getFighter2().getWeight_kg()) + "kg";
        }
        lines.add(new FightLine.Builder().withLineTitle(context.getString(R.string.fighter_weight)).withLine1info(weight[0]).withLine2info(weight[0]).build());

        String[] reach = {"/", "/"};
        if (fight.getFighter1() != null && fight.getFighter1().getReach_cm() != null) {
            reach[0] = Integer.toString(fight.getFighter1().getReach_cm()) + "cm";
        }
        if (fight.getFighter2() != null && fight.getFighter2().getReach_cm() != null) {
            reach[1] = Integer.toString(fight.getFighter2().getReach_cm()) + "cm";
        }
        lines.add(new FightLine.Builder().withLineTitle(context.getString(R.string.fighter_reach)).withLine1info(reach[0]).withLine2info(reach[1]).build());

        String[] legReach = {"/", "/"};
        if (fight.getFighter1() != null && fight.getFighter1().getLegReach_cm() != null) {
            legReach[0] = Integer.toString(fight.getFighter1().getLegReach_cm()) + "cm";
        }
        if (fight.getFighter2() != null && fight.getFighter2().getLegReach_cm() != null) {
            legReach[1] = Integer.toString(fight.getFighter2().getLegReach_cm()) + "cm";
        }
        lines.add(new FightLine.Builder().withLineTitle(context.getString(R.string.fighter_leg_reach)).withLine1info(legReach[0]).withLine2info(legReach[1]).build());

        return lines;
    }

    private static List<FightLine> getItemsFightFinished(Context context, EventFight fight) {
        List<FightLine> lines = new ArrayList<>();
        lines.addAll(getItemsFight(context, fight));
        return lines;
    }

    private static class FightLine {
        @NonNull
        String mLine1info = "";
        @NonNull
        String mLineTitle = "";
        @NonNull
        String mLine2info = "";

        public static class Builder {
            @NonNull
            String mLine1info = "";
            @NonNull
            String mLineTitle = "";
            @NonNull
            String mLine2info = "";

            public Builder withLine1info(String line1info) {
                mLine1info = line1info;
                return this;
            }

            public Builder withLineTitle(String lineTitle) {
                mLineTitle = lineTitle;
                return this;
            }

            public Builder withLine2info(String line2info) {
                mLine2info = line2info;
                return this;
            }

            public FightLine build() {
                FightLine line = new FightLine();
                line.mLine1info = mLine1info;
                line.mLineTitle = mLineTitle;
                line.mLine2info = mLine2info;
                return line;
            }
        }
    }

    public void setOnFighterInteractionListener(FightHeaderViewHolder.OnFighterInteractionListener onFighterInteractionListener) {
        this.mOnFighterInteractionListener = onFighterInteractionListener;
    }
}
