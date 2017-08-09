package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class Grappling {

    @SerializedName("Action")
    @Expose
    private Action takedowns;

    @SerializedName("Submissions")
    @Expose
    private Action submissions;

    @SerializedName("Reversals")
    @Expose
    private Action reversals;

    @SerializedName("Standups")
    @Expose
    private Action standups;

    public Action getTakedowns() {
        return takedowns;
    }

    public void setTakedowns(Action takedowns) {
        this.takedowns = takedowns;
    }

    public Action getSubmissions() {
        return submissions;
    }

    public void setSubmissions(Action submissions) {
        this.submissions = submissions;
    }

    public Action getReversals() {
        return reversals;
    }

    public void setReversals(Action reversals) {
        this.reversals = reversals;
    }

    public Action getStandups() {
        return standups;
    }

    public void setStandups(Action standups) {
        this.standups = standups;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Grappling{");
        sb.append("takedowns=").append(takedowns);
        sb.append(", submissions=").append(submissions);
        sb.append(", reversals=").append(reversals);
        sb.append(", standups=").append(standups);
        sb.append('}');
        return sb.toString();
    }
}
