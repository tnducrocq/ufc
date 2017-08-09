package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class FighterStats {

    @SerializedName("FighterID")
    @Expose
    private String fighterID;

    @SerializedName("Strikes")
    @Expose
    private Strikes strikes;

    @SerializedName("Grappling")
    @Expose
    private Grappling grappling;

    @SerializedName("TIP")
    @Expose
    private Tip tip;

    public String getFighterID() {
        return fighterID;
    }

    public void setFighterID(String fighterID) {
        this.fighterID = fighterID;
    }

    public Strikes getStrikes() {
        return strikes;
    }

    public void setStrikes(Strikes strikes) {
        this.strikes = strikes;
    }

    public Grappling getGrappling() {
        return grappling;
    }

    public void setGrappling(Grappling grappling) {
        this.grappling = grappling;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FighterStats{");
        sb.append("fighterID='").append(fighterID).append('\'');
        sb.append(", strikes=").append(strikes);
        sb.append(", grappling=").append(grappling);
        sb.append(", tip=").append(tip);
        sb.append('}');
        return sb.toString();
    }
}
