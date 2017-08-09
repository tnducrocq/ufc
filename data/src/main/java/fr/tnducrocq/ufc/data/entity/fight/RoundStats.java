package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class RoundStats {

    @SerializedName("Round1")
    @Expose
    private FightStats round1;

    @SerializedName("Round2")
    @Expose
    private FightStats round2;

    @SerializedName("Round3")
    @Expose
    private FightStats round3;

    @SerializedName("Round4")
    @Expose
    private FightStats round4;

    @SerializedName("Round5")
    @Expose
    private FightStats round5;

    public FightStats getRound1() {
        return round1;
    }

    public void setRound1(FightStats round1) {
        this.round1 = round1;
    }

    public FightStats getRound2() {
        return round2;
    }

    public void setRound2(FightStats round2) {
        this.round2 = round2;
    }

    public FightStats getRound3() {
        return round3;
    }

    public void setRound3(FightStats round3) {
        this.round3 = round3;
    }

    public FightStats getRound4() {
        return round4;
    }

    public void setRound4(FightStats round4) {
        this.round4 = round4;
    }

    public FightStats getRound5() {
        return round5;
    }

    public void setRound5(FightStats round5) {
        this.round5 = round5;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RoundStats{");
        sb.append("round1=").append(round1);
        sb.append(", round2=").append(round2);
        sb.append(", round3=").append(round3);
        sb.append(", round4=").append(round4);
        sb.append(", round5=").append(round5);
        sb.append('}');
        return sb.toString();
    }
}
