package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class FighterMin {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("FighterID")
    @Expose
    private String fighterID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFighterID() {
        return fighterID;
    }

    public void setFighterID(String fighterID) {
        this.fighterID = fighterID;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FighterMin{");
        sb.append("name='").append(name).append('\'');
        sb.append(", fighterID='").append(fighterID).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
