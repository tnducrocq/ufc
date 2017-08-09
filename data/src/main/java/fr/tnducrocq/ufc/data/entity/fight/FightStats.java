package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class FightStats {

    @SerializedName("Red")
    @Expose
    private FighterStats red;

    @SerializedName("Blue")
    @Expose
    private FighterStats blue;

    public FighterStats getRed() {
        return red;
    }

    public void setRed(FighterStats red) {
        this.red = red;
    }

    public FighterStats getBlue() {
        return blue;
    }

    public void setBlue(FighterStats blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FightStats{");
        sb.append("red=").append(red);
        sb.append(", blue=").append(blue);
        sb.append('}');
        return sb.toString();
    }
}
