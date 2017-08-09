package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class Fight {

    @SerializedName("Type")
    @Expose
    private String type;

    @SerializedName("FMLiveFeed")
    @Expose
    private FightDetail fightDetail;

    @SerializedName("Timestamp")
    @Expose
    private String timestamp;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FightDetail getFightDetail() {
        return fightDetail;
    }

    public void setFightDetail(FightDetail fightDetail) {
        this.fightDetail = fightDetail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FighterMin{");
        sb.append("type='").append(type).append('\'');
        sb.append(", fightDetail=").append(fightDetail);
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
