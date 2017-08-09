package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class Action {

    @SerializedName("Landed")
    @Expose
    private String landed;

    @SerializedName("Attempts")
    @Expose
    private String attempts;

    public String getLanded() {
        return landed;
    }

    public void setLanded(String landed) {
        this.landed = landed;
    }

    public String getAttempts() {
        return attempts;
    }

    public void setAttempts(String attempts) {
        this.attempts = attempts;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Action{");
        sb.append("landed='").append(landed).append('\'');
        sb.append(", attempts='").append(attempts).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
