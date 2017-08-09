package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class Fighters {

    @SerializedName("Red")
    @Expose
    private FighterMin red;

    @SerializedName("Blue")
    @Expose
    private FighterMin blue;

    public FighterMin getRed() {
        return red;
    }

    public void setRed(FighterMin red) {
        this.red = red;
    }

    public FighterMin getBlue() {
        return blue;
    }

    public void setBlue(FighterMin blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Fighters{");
        sb.append("red=").append(red);
        sb.append(", blue=").append(blue);
        sb.append('}');
        return sb.toString();
    }
}
