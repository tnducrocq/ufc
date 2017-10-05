package fr.tnducrocq.ufc.data.entity.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventFight implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("fighter1reach")
    @Expose
    private Integer fighter1reach;

    @SerializedName("fighter2weight")
    @Expose
    private Integer fighter2weight;

    @SerializedName("fighter2height")
    @Expose
    private Integer fighter2height;

    @SerializedName("fighter2record")
    @Expose
    private String fighter2record;

    @SerializedName("fighter2reach")
    @Expose
    private Integer fighter2reach;

    @SerializedName("event_id")
    @Expose
    private Integer eventId;

    @SerializedName("fighter1height")
    @Expose
    private Integer fighter1height;

    @SerializedName("fighter1weight")
    @Expose
    private Integer fighter1weight;
    @SerializedName("fightcard_order")
    @Expose
    private Integer fightcardOrder;

    @SerializedName("fighter1record")
    @Expose
    private String fighter1record;
    @SerializedName("is_title_fight")
    @Expose
    private Boolean isTitleFight;
    @SerializedName("fighter1_id")
    @Expose
    private Integer fighter1Id;
    @SerializedName("fighter2_id")
    @Expose
    private Integer fighter2Id;
    @SerializedName("is_main_event")
    @Expose
    private Boolean isMainEvent;

    @SerializedName("is_prelim")
    @Expose
    private Boolean isPrelim;
    @SerializedName("fighter1odds")
    @Expose
    private String fighter1Nickname;
    @SerializedName("fighter1_wins")
    @Expose
    private Integer fighter1Wins;
    @SerializedName("fighter1_statid")
    @Expose
    private Integer fighter1Statid;
    @SerializedName("fighter1_losses")
    @Expose
    private Integer fighter1Losses;
    @SerializedName("fighter1_last_name")
    @Expose
    private String fighter1LastName;
    @SerializedName("fighter1_weight_class")
    @Expose
    private String fighter1WeightClass;
    @SerializedName("fighter1_draws")
    @Expose
    private Integer fighter1Draws;
    @SerializedName("fighter1_first_name")
    @Expose
    private String fighter1FirstName;

    @SerializedName("fighter2_wins")
    @Expose
    private Integer fighter2Wins;
    @SerializedName("fighter2_statid")
    @Expose
    private Integer fighter2Statid;
    @SerializedName("fighter2_losses")
    @Expose
    private Integer fighter2Losses;
    @SerializedName("fighter2_last_name")
    @Expose
    private String fighter2LastName;
    @SerializedName("fighter2_weight_class")
    @Expose
    private String fighter2WeightClass;
    @SerializedName("fighter2_draws")
    @Expose
    private Integer fighter2Draws;
    @SerializedName("fighter2_first_name")
    @Expose
    private String fighter2FirstName;

    @SerializedName("fighter1_full_body_image")
    @Expose
    private String fighter1FullBodyImage;
    @SerializedName("fighter2_full_body_image")
    @Expose
    private String fighter2FullBodyImage;
    @SerializedName("fighter1_profile_image")
    @Expose
    private String fighter1ProfileImage;
    @SerializedName("fighter2_profile_image")
    @Expose
    private String fighter2ProfileImage;
    @SerializedName("ending_time")
    @Expose
    private String endingTime;
    @SerializedName("fm_stats_feed_url")
    @Expose
    private String fmStatsFeedUrl;
    @SerializedName("fm_fight_rhythm_feed_url")
    @Expose
    private String fmFightRhythmFeedUrl;
    @SerializedName("fighter1_averagefighttime")
    @Expose
    private String fighter1Averagefighttime;
    @SerializedName("fighter1_averagefighttime_seconds")
    @Expose
    private String fighter1AveragefighttimeSeconds;
    @SerializedName("fighter1_kdaverage")
    @Expose
    private String fighter1Kdaverage;
    @SerializedName("fighter1_slpm")
    @Expose
    private String fighter1Slpm;
    @SerializedName("fighter1_strikingaccuracy")
    @Expose
    private String fighter1Strikingaccuracy;
    @SerializedName("fighter1_sapm")
    @Expose
    private String fighter1Sapm;
    @SerializedName("fighter1_strikingdefense")
    @Expose
    private String fighter1Strikingdefense;
    @SerializedName("fighter1_takedownaverage")
    @Expose
    private String fighter1Takedownaverage;

    @SerializedName("fighter1_takedowndefense")
    @Expose
    private String fighter1Takedowndefense;
    @SerializedName("fighter1_submissionsaverage")
    @Expose
    private String fighter1Submissionsaverage;
    @SerializedName("fighter2_averagefighttime")
    @Expose
    private String fighter2Averagefighttime;
    @SerializedName("fighter2_averagefighttime_seconds")
    @Expose
    private String fighter2AveragefighttimeSeconds;
    @SerializedName("fighter2_kdaverage")
    @Expose
    private String fighter2Kdaverage;
    @SerializedName("fighter2_slpm")
    @Expose
    private String fighter2Slpm;
    @SerializedName("fighter2_strikingaccuracy")
    @Expose
    private String fighter2Strikingaccuracy;
    @SerializedName("fighter2_sapm")
    @Expose
    private String fighter2Sapm;
    @SerializedName("fighter2_strikingdefense")
    @Expose
    private String fighter2Strikingdefense;
    @SerializedName("fighter2_takedownaverage")
    @Expose
    private String fighter2Takedownaverage;
    @SerializedName("fighter2_takedownaccuracy")
    @Expose
    private String fighter2Takedownaccuracy;

    @SerializedName("fighter2_takedowndefense")
    @Expose
    private String fighter2Takedowndefense;

    @SerializedName("fighter2_submissionsaverage")
    @Expose
    private String fighter2Submissionsaverage;

    @SerializedName("fighter1_is_winner")
    @Expose
    private Boolean fighter1IsWinner;

    @SerializedName("fighter2_is_winner")
    @Expose
    private Boolean fighter2IsWinner;

    @SerializedName("result")
    @Expose
    private EventFightResult result;

    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFighter1reach() {
        return fighter1reach;
    }

    public void setFighter1reach(Integer fighter1reach) {
        this.fighter1reach = fighter1reach;
    }

    public Integer getFighter2weight() {
        return fighter2weight;
    }

    public void setFighter2weight(Integer fighter2weight) {
        this.fighter2weight = fighter2weight;
    }

    public Integer getFighter2height() {
        return fighter2height;
    }

    public void setFighter2height(Integer fighter2height) {
        this.fighter2height = fighter2height;
    }

    public String getFighter2record() {
        return fighter2record;
    }

    public void setFighter2record(String fighter2record) {
        this.fighter2record = fighter2record;
    }

    public Integer getFighter2reach() {
        return fighter2reach;
    }

    public void setFighter2reach(Integer fighter2reach) {
        this.fighter2reach = fighter2reach;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getFighter1height() {
        return fighter1height;
    }

    public void setFighter1height(Integer fighter1height) {
        this.fighter1height = fighter1height;
    }

    public Integer getFighter1weight() {
        return fighter1weight;
    }

    public void setFighter1weight(Integer fighter1weight) {
        this.fighter1weight = fighter1weight;
    }

    public Integer getFightcardOrder() {
        return fightcardOrder;
    }

    public void setFightcardOrder(Integer fightcardOrder) {
        this.fightcardOrder = fightcardOrder;
    }

    public String getFighter1record() {
        return fighter1record;
    }

    public void setFighter1record(String fighter1record) {
        this.fighter1record = fighter1record;
    }

    public Boolean getIsTitleFight() {
        return isTitleFight;
    }

    public void setIsTitleFight(Boolean isTitleFight) {
        this.isTitleFight = isTitleFight;
    }

    public Integer getFighter1Id() {
        return fighter1Id;
    }

    public void setFighter1Id(Integer fighter1Id) {
        this.fighter1Id = fighter1Id;
    }

    public Integer getFighter2Id() {
        return fighter2Id;
    }

    public void setFighter2Id(Integer fighter2Id) {
        this.fighter2Id = fighter2Id;
    }

    public Boolean getIsMainEvent() {
        return isMainEvent;
    }

    public void setIsMainEvent(Boolean isMainEvent) {
        this.isMainEvent = isMainEvent;
    }

    public Boolean getIsPrelim() {
        return isPrelim;
    }

    public void setIsPrelim(Boolean isPrelim) {
        this.isPrelim = isPrelim;
    }

    public String getFighter1Nickname() {
        return fighter1Nickname;
    }

    public void setFighter1Nickname(String fighter1Nickname) {
        this.fighter1Nickname = fighter1Nickname;
    }

    public Integer getFighter1Wins() {
        return fighter1Wins;
    }

    public void setFighter1Wins(Integer fighter1Wins) {
        this.fighter1Wins = fighter1Wins;
    }

    public Integer getFighter1Statid() {
        return fighter1Statid;
    }

    public void setFighter1Statid(Integer fighter1Statid) {
        this.fighter1Statid = fighter1Statid;
    }

    public Integer getFighter1Losses() {
        return fighter1Losses;
    }

    public void setFighter1Losses(Integer fighter1Losses) {
        this.fighter1Losses = fighter1Losses;
    }

    public String getFighter1LastName() {
        return fighter1LastName;
    }

    public void setFighter1LastName(String fighter1LastName) {
        this.fighter1LastName = fighter1LastName;
    }

    public String getFighter1WeightClass() {
        return fighter1WeightClass;
    }

    public void setFighter1WeightClass(String fighter1WeightClass) {
        this.fighter1WeightClass = fighter1WeightClass;
    }

    public Integer getFighter1Draws() {
        return fighter1Draws;
    }

    public void setFighter1Draws(Integer fighter1Draws) {
        this.fighter1Draws = fighter1Draws;
    }

    public String getFighter1FirstName() {
        return fighter1FirstName;
    }

    public void setFighter1FirstName(String fighter1FirstName) {
        this.fighter1FirstName = fighter1FirstName;
    }

    public Integer getFighter2Wins() {
        return fighter2Wins;
    }

    public void setFighter2Wins(Integer fighter2Wins) {
        this.fighter2Wins = fighter2Wins;
    }

    public Integer getFighter2Statid() {
        return fighter2Statid;
    }

    public void setFighter2Statid(Integer fighter2Statid) {
        this.fighter2Statid = fighter2Statid;
    }

    public Integer getFighter2Losses() {
        return fighter2Losses;
    }

    public void setFighter2Losses(Integer fighter2Losses) {
        this.fighter2Losses = fighter2Losses;
    }

    public String getFighter2LastName() {
        return fighter2LastName;
    }

    public void setFighter2LastName(String fighter2LastName) {
        this.fighter2LastName = fighter2LastName;
    }

    public String getFighter2WeightClass() {
        return fighter2WeightClass;
    }

    public void setFighter2WeightClass(String fighter2WeightClass) {
        this.fighter2WeightClass = fighter2WeightClass;
    }

    public Integer getFighter2Draws() {
        return fighter2Draws;
    }

    public void setFighter2Draws(Integer fighter2Draws) {
        this.fighter2Draws = fighter2Draws;
    }

    public String getFighter2FirstName() {
        return fighter2FirstName;
    }

    public void setFighter2FirstName(String fighter2FirstName) {
        this.fighter2FirstName = fighter2FirstName;
    }

    public String getFighter1FullBodyImage() {
        return fighter1FullBodyImage;
    }

    public void setFighter1FullBodyImage(String fighter1FullBodyImage) {
        this.fighter1FullBodyImage = fighter1FullBodyImage;
    }

    public String getFighter2FullBodyImage() {
        return fighter2FullBodyImage;
    }

    public void setFighter2FullBodyImage(String fighter2FullBodyImage) {
        this.fighter2FullBodyImage = fighter2FullBodyImage;
    }

    public String getFighter1ProfileImage() {
        return fighter1ProfileImage;
    }

    public void setFighter1ProfileImage(String fighter1ProfileImage) {
        this.fighter1ProfileImage = fighter1ProfileImage;
    }

    public String getFighter2ProfileImage() {
        return fighter2ProfileImage;
    }

    public void setFighter2ProfileImage(String fighter2ProfileImage) {
        this.fighter2ProfileImage = fighter2ProfileImage;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getFmStatsFeedUrl() {
        return fmStatsFeedUrl;
    }

    public void setFmStatsFeedUrl(String fmStatsFeedUrl) {
        this.fmStatsFeedUrl = fmStatsFeedUrl;
    }

    public String getFmFightRhythmFeedUrl() {
        return fmFightRhythmFeedUrl;
    }

    public void setFmFightRhythmFeedUrl(String fmFightRhythmFeedUrl) {
        this.fmFightRhythmFeedUrl = fmFightRhythmFeedUrl;
    }

    public String getFighter1Averagefighttime() {
        return fighter1Averagefighttime;
    }

    public void setFighter1Averagefighttime(String fighter1Averagefighttime) {
        this.fighter1Averagefighttime = fighter1Averagefighttime;
    }

    public String getFighter1AveragefighttimeSeconds() {
        return fighter1AveragefighttimeSeconds;
    }

    public void setFighter1AveragefighttimeSeconds(String fighter1AveragefighttimeSeconds) {
        this.fighter1AveragefighttimeSeconds = fighter1AveragefighttimeSeconds;
    }

    public String getFighter1Kdaverage() {
        return fighter1Kdaverage;
    }

    public void setFighter1Kdaverage(String fighter1Kdaverage) {
        this.fighter1Kdaverage = fighter1Kdaverage;
    }

    public String getFighter1Slpm() {
        return fighter1Slpm;
    }

    public void setFighter1Slpm(String fighter1Slpm) {
        this.fighter1Slpm = fighter1Slpm;
    }

    public String getFighter1Strikingaccuracy() {
        return fighter1Strikingaccuracy;
    }

    public void setFighter1Strikingaccuracy(String fighter1Strikingaccuracy) {
        this.fighter1Strikingaccuracy = fighter1Strikingaccuracy;
    }

    public String getFighter1Sapm() {
        return fighter1Sapm;
    }

    public void setFighter1Sapm(String fighter1Sapm) {
        this.fighter1Sapm = fighter1Sapm;
    }

    public String getFighter1Strikingdefense() {
        return fighter1Strikingdefense;
    }

    public void setFighter1Strikingdefense(String fighter1Strikingdefense) {
        this.fighter1Strikingdefense = fighter1Strikingdefense;
    }

    public String getFighter1Takedownaverage() {
        return fighter1Takedownaverage;
    }

    public void setFighter1Takedownaverage(String fighter1Takedownaverage) {
        this.fighter1Takedownaverage = fighter1Takedownaverage;
    }

    public String getFighter1Takedowndefense() {
        return fighter1Takedowndefense;
    }

    public void setFighter1Takedowndefense(String fighter1Takedowndefense) {
        this.fighter1Takedowndefense = fighter1Takedowndefense;
    }

    public String getFighter1Submissionsaverage() {
        return fighter1Submissionsaverage;
    }

    public void setFighter1Submissionsaverage(String fighter1Submissionsaverage) {
        this.fighter1Submissionsaverage = fighter1Submissionsaverage;
    }

    public String getFighter2Averagefighttime() {
        return fighter2Averagefighttime;
    }

    public void setFighter2Averagefighttime(String fighter2Averagefighttime) {
        this.fighter2Averagefighttime = fighter2Averagefighttime;
    }

    public String getFighter2AveragefighttimeSeconds() {
        return fighter2AveragefighttimeSeconds;
    }

    public void setFighter2AveragefighttimeSeconds(String fighter2AveragefighttimeSeconds) {
        this.fighter2AveragefighttimeSeconds = fighter2AveragefighttimeSeconds;
    }

    public String getFighter2Kdaverage() {
        return fighter2Kdaverage;
    }

    public void setFighter2Kdaverage(String fighter2Kdaverage) {
        this.fighter2Kdaverage = fighter2Kdaverage;
    }

    public String getFighter2Slpm() {
        return fighter2Slpm;
    }

    public void setFighter2Slpm(String fighter2Slpm) {
        this.fighter2Slpm = fighter2Slpm;
    }

    public String getFighter2Strikingaccuracy() {
        return fighter2Strikingaccuracy;
    }

    public void setFighter2Strikingaccuracy(String fighter2Strikingaccuracy) {
        this.fighter2Strikingaccuracy = fighter2Strikingaccuracy;
    }

    public String getFighter2Sapm() {
        return fighter2Sapm;
    }

    public void setFighter2Sapm(String fighter2Sapm) {
        this.fighter2Sapm = fighter2Sapm;
    }

    public String getFighter2Strikingdefense() {
        return fighter2Strikingdefense;
    }

    public void setFighter2Strikingdefense(String fighter2Strikingdefense) {
        this.fighter2Strikingdefense = fighter2Strikingdefense;
    }

    public String getFighter2Takedownaverage() {
        return fighter2Takedownaverage;
    }

    public void setFighter2Takedownaverage(String fighter2Takedownaverage) {
        this.fighter2Takedownaverage = fighter2Takedownaverage;
    }

    public String getFighter2Takedownaccuracy() {
        return fighter2Takedownaccuracy;
    }

    public void setFighter2Takedownaccuracy(String fighter2Takedownaccuracy) {
        this.fighter2Takedownaccuracy = fighter2Takedownaccuracy;
    }

    public String getFighter2Takedowndefense() {
        return fighter2Takedowndefense;
    }

    public void setFighter2Takedowndefense(String fighter2Takedowndefense) {
        this.fighter2Takedowndefense = fighter2Takedowndefense;
    }

    public String getFighter2Submissionsaverage() {
        return fighter2Submissionsaverage;
    }

    public void setFighter2Submissionsaverage(String fighter2Submissionsaverage) {
        this.fighter2Submissionsaverage = fighter2Submissionsaverage;
    }

    public Boolean getFighter1IsWinner() {
        return fighter1IsWinner;
    }

    public void setFighter1IsWinner(Boolean fighter1IsWinner) {
        this.fighter1IsWinner = fighter1IsWinner;
    }

    public Boolean getFighter2IsWinner() {
        return fighter2IsWinner;
    }

    public void setFighter2IsWinner(Boolean fighter2IsWinner) {
        this.fighter2IsWinner = fighter2IsWinner;
    }

    public EventFightResult getResult() {
        return result;
    }

    public void setResult(EventFightResult result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.fighter1reach);
        dest.writeValue(this.fighter2weight);
        dest.writeValue(this.fighter2height);
        dest.writeString(this.fighter2record);
        dest.writeValue(this.fighter2reach);
        dest.writeValue(this.eventId);
        dest.writeValue(this.fighter1height);
        dest.writeValue(this.fighter1weight);
        dest.writeValue(this.fightcardOrder);
        dest.writeString(this.fighter1record);
        dest.writeValue(this.isTitleFight);
        dest.writeValue(this.fighter1Id);
        dest.writeValue(this.fighter2Id);
        dest.writeValue(this.isMainEvent);
        dest.writeValue(this.isPrelim);
        dest.writeString(this.fighter1Nickname);
        dest.writeValue(this.fighter1Wins);
        dest.writeValue(this.fighter1Statid);
        dest.writeValue(this.fighter1Losses);
        dest.writeString(this.fighter1LastName);
        dest.writeString(this.fighter1WeightClass);
        dest.writeValue(this.fighter1Draws);
        dest.writeString(this.fighter1FirstName);
        dest.writeValue(this.fighter2Wins);
        dest.writeValue(this.fighter2Statid);
        dest.writeValue(this.fighter2Losses);
        dest.writeString(this.fighter2LastName);
        dest.writeString(this.fighter2WeightClass);
        dest.writeValue(this.fighter2Draws);
        dest.writeString(this.fighter2FirstName);
        dest.writeString(this.fighter1FullBodyImage);
        dest.writeString(this.fighter2FullBodyImage);
        dest.writeString(this.fighter1ProfileImage);
        dest.writeString(this.fighter2ProfileImage);
        dest.writeString(this.endingTime);
        dest.writeString(this.fmStatsFeedUrl);
        dest.writeString(this.fmFightRhythmFeedUrl);
        dest.writeString(this.fighter1Averagefighttime);
        dest.writeString(this.fighter1AveragefighttimeSeconds);
        dest.writeString(this.fighter1Kdaverage);
        dest.writeString(this.fighter1Slpm);
        dest.writeString(this.fighter1Strikingaccuracy);
        dest.writeString(this.fighter1Sapm);
        dest.writeString(this.fighter1Strikingdefense);
        dest.writeString(this.fighter1Takedownaverage);
        dest.writeString(this.fighter1Takedowndefense);
        dest.writeString(this.fighter1Submissionsaverage);
        dest.writeString(this.fighter2Averagefighttime);
        dest.writeString(this.fighter2AveragefighttimeSeconds);
        dest.writeString(this.fighter2Kdaverage);
        dest.writeString(this.fighter2Slpm);
        dest.writeString(this.fighter2Strikingaccuracy);
        dest.writeString(this.fighter2Sapm);
        dest.writeString(this.fighter2Strikingdefense);
        dest.writeString(this.fighter2Takedownaverage);
        dest.writeString(this.fighter2Takedownaccuracy);
        dest.writeString(this.fighter2Takedowndefense);
        dest.writeString(this.fighter2Submissionsaverage);
        dest.writeValue(this.fighter1IsWinner);
        dest.writeValue(this.fighter2IsWinner);
        dest.writeParcelable(this.result, flags);
    }

    protected EventFight(Parcel in) {
        this.id = in.readString();
        this.fighter1reach = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2weight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2record = in.readString();
        this.fighter2reach = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eventId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1weight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fightcardOrder = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1record = in.readString();
        this.isTitleFight = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.fighter1Id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2Id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isMainEvent = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isPrelim = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.fighter1Nickname = in.readString();
        this.fighter1Wins = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1Statid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1Losses = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1LastName = in.readString();
        this.fighter1WeightClass = in.readString();
        this.fighter1Draws = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1FirstName = in.readString();
        this.fighter2Wins = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2Statid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2Losses = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2LastName = in.readString();
        this.fighter2WeightClass = in.readString();
        this.fighter2Draws = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2FirstName = in.readString();
        this.fighter1FullBodyImage = in.readString();
        this.fighter2FullBodyImage = in.readString();
        this.fighter1ProfileImage = in.readString();
        this.fighter2ProfileImage = in.readString();
        this.endingTime = in.readString();
        this.fmStatsFeedUrl = in.readString();
        this.fmFightRhythmFeedUrl = in.readString();
        this.fighter1Averagefighttime = in.readString();
        this.fighter1AveragefighttimeSeconds = in.readString();
        this.fighter1Kdaverage = in.readString();
        this.fighter1Slpm = in.readString();
        this.fighter1Strikingaccuracy = in.readString();
        this.fighter1Sapm = in.readString();
        this.fighter1Strikingdefense = in.readString();
        this.fighter1Takedownaverage = in.readString();
        this.fighter1Takedowndefense = in.readString();
        this.fighter1Submissionsaverage = in.readString();
        this.fighter2Averagefighttime = in.readString();
        this.fighter2AveragefighttimeSeconds = in.readString();
        this.fighter2Kdaverage = in.readString();
        this.fighter2Slpm = in.readString();
        this.fighter2Strikingaccuracy = in.readString();
        this.fighter2Sapm = in.readString();
        this.fighter2Strikingdefense = in.readString();
        this.fighter2Takedownaverage = in.readString();
        this.fighter2Takedownaccuracy = in.readString();
        this.fighter2Takedowndefense = in.readString();
        this.fighter2Submissionsaverage = in.readString();
        this.fighter1IsWinner = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.fighter2IsWinner = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.result = in.readParcelable(EventFightResult.class.getClassLoader());
    }

    public static final Parcelable.Creator<EventFight> CREATOR = new Parcelable.Creator<EventFight>() {
        @Override
        public EventFight createFromParcel(Parcel source) {
            return new EventFight(source);
        }

        @Override
        public EventFight[] newArray(int size) {
            return new EventFight[size];
        }
    };
}