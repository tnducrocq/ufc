package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class FightDetail {


    @SerializedName("Fighters")
    @Expose
    private Fighters fighters;

    @SerializedName("RoundStats")
    @Expose
    private RoundStats roundStats;

    @SerializedName("FightStats")
    @Expose
    private FightStats fightStats;

    @SerializedName("EventID")
    @Expose
    private String eventID;

    @SerializedName("CurrentRound")
    @Expose
    private String currentRound;

    @SerializedName("FightID")
    @Expose
    private String fightID;

    @SerializedName("MaxRounds")
    @Expose
    private String maxRounds;

    @SerializedName("Accolade")
    @Expose
    private String accolade;

    @SerializedName("Referee")
    @Expose
    private String referee;

    @SerializedName("WeightClass")
    @Expose
    private String weightClass;

    @SerializedName("Status")
    @Expose
    private String status;

    @SerializedName("CurrentRoundTime")
    @Expose
    private String currentRoundTime;

    public Fighters getFighters() {
        return fighters;
    }

    public void setFighters(Fighters fighters) {
        this.fighters = fighters;
    }

    public RoundStats getRoundStats() {
        return roundStats;
    }

    public void setRoundStats(RoundStats roundStats) {
        this.roundStats = roundStats;
    }

    public FightStats getFightStats() {
        return fightStats;
    }

    public void setFightStats(FightStats fightStats) {
        this.fightStats = fightStats;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(String currentRound) {
        this.currentRound = currentRound;
    }

    public String getFightID() {
        return fightID;
    }

    public void setFightID(String fightID) {
        this.fightID = fightID;
    }

    public String getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(String maxRounds) {
        this.maxRounds = maxRounds;
    }

    public String getAccolade() {
        return accolade;
    }

    public void setAccolade(String accolade) {
        this.accolade = accolade;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(String weightClass) {
        this.weightClass = weightClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentRoundTime() {
        return currentRoundTime;
    }

    public void setCurrentRoundTime(String currentRoundTime) {
        this.currentRoundTime = currentRoundTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FightDetail{");
        sb.append("fighters=").append(fighters);
        sb.append(", roundStats=").append(roundStats);
        sb.append(", fightStats=").append(fightStats);
        sb.append(", eventID='").append(eventID).append('\'');
        sb.append(", currentRound='").append(currentRound).append('\'');
        sb.append(", fightID='").append(fightID).append('\'');
        sb.append(", maxRounds='").append(maxRounds).append('\'');
        sb.append(", accolade='").append(accolade).append('\'');
        sb.append(", referee='").append(referee).append('\'');
        sb.append(", weightClass='").append(weightClass).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", currentRoundTime='").append(currentRoundTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
