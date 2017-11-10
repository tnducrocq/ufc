package fr.tnducrocq.ufc.data.entity.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import fr.tnducrocq.ufc.data.entity.HasId;
import fr.tnducrocq.ufc.data.entity.fighter.DaoSession;
import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.WeightCategory;
import fr.tnducrocq.ufc.data.entity.fighter.WeightCategoryConverter;


@Entity(indexes = {
        @Index(value = "id ASC", unique = true)
})
public class EventFight implements HasId, Parcelable {

    @Id(autoincrement = true)
    private Long uid;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("event_id")
    @Expose
    private String eventId;

    @SerializedName("fightcard_order")
    @Expose
    private Integer fightcardOrder;

    @SerializedName("is_title_fight")
    @Expose
    private Boolean isTitleFight;

    @SerializedName("is_main_event")
    @Expose
    private Boolean isMainEvent;

    @SerializedName("is_prelim")
    @Expose
    private Boolean isPrelim;

    @SerializedName("ending_time")
    @Expose
    private String endingTime;

    @SerializedName("ending_round_number")
    @Expose
    private String endingRoundNumber;

    @SerializedName("fm_stats_feed_url")
    @Expose
    private String fmStatsFeedUrl;

    @SerializedName("fm_fight_rhythm_feed_url")
    @Expose
    private String fmFightRhythmFeedUrl;

    private Long resultId;

    @SerializedName("result")
    @Expose
    @Transient
    private EventFightResult _result;

    @ToOne(joinProperty = "resultId")
    private EventFightResult result;

    @Transient
    private Fighter fighter1;

    @SerializedName("fighter1_id")
    @Expose
    private Integer fighter1Id;

    @SerializedName("fighter1weight")
    @Expose
    private Integer fighter1weight;

    @SerializedName("fighter1height")
    @Expose
    private Integer fighter1height;

    @SerializedName("fighter1reach")
    @Expose
    private Integer fighter1reach;

    @SerializedName("fighter1_nickname")
    @Expose
    private String fighter1Nickname;

    @SerializedName("fighter1_last_name")
    @Expose
    private String fighter1LastName;

    @SerializedName("fighter1_first_name")
    @Expose
    private String fighter1FirstName;

    @SerializedName("fighter1_weight_class")
    @Expose
    @Convert(converter = WeightCategoryConverter.class, columnType = Integer.class)
    private WeightCategory fighter1WeightClass;

    @SerializedName("fighter1_is_winner")
    @Expose
    private Boolean fighter1IsWinner;

    @SerializedName("fighter1_full_body_image")
    @Expose
    private String fighter1FullBodyImage;

    @SerializedName("fighter1_profile_image")
    @Expose
    private String fighter1ProfileImage;

    @SerializedName("fighter1record")
    @Expose
    private String fighter1record;

    @SerializedName("fighter1_wins")
    @Expose
    private Integer fighter1Wins;

    @SerializedName("fighter1_losses")
    @Expose
    private Integer fighter1Losses;

    @SerializedName("fighter1_draws")
    @Expose
    private Integer fighter1Draws;

    @SerializedName("fighter1_statid")
    @Expose
    private Integer fighter1Statid;

    @SerializedName("fighter1_rank")
    @Expose
    private String fighter1Rank;

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

    @SerializedName("fighter1_takedownaccuracy")
    @Expose
    private String fighter1Takedownaccuracy;

    @SerializedName("fighter1_takedowndefense")
    @Expose
    private String fighter1Takedowndefense;

    @SerializedName("fighter1_submissionsaverage")
    @Expose
    private String fighter1Submissionsaverage;

    @Transient
    private Fighter fighter2;

    @SerializedName("fighter2_id")
    @Expose
    private Integer fighter2Id;

    @SerializedName("fighter2weight")
    @Expose
    private Integer fighter2weight;

    @SerializedName("fighter2height")
    @Expose
    private Integer fighter2height;

    @SerializedName("fighter2reach")
    @Expose
    private Integer fighter2reach;

    @SerializedName("fighter2_nickname")
    @Expose
    private String fighter2Nickname;

    @SerializedName("fighter2_last_name")
    @Expose
    private String fighter2LastName;

    @SerializedName("fighter2_first_name")
    @Expose
    private String fighter2FirstName;

    @SerializedName("fighter2_weight_class")
    @Expose
    @Convert(converter = WeightCategoryConverter.class, columnType = Integer.class)
    private WeightCategory fighter2WeightClass;

    @SerializedName("fighter2_is_winner")
    @Expose
    private Boolean fighter2IsWinner;

    @SerializedName("fighter2_full_body_image")
    @Expose
    private String fighter2FullBodyImage;

    @SerializedName("fighter2_profile_image")
    @Expose
    private String fighter2ProfileImage;

    @SerializedName("fighter2record")
    @Expose
    private String fighter2record;

    @SerializedName("fighter2_wins")
    @Expose
    private Integer fighter2Wins;

    @SerializedName("fighter2_losses")
    @Expose
    private Integer fighter2Losses;

    @SerializedName("fighter2_draws")
    @Expose
    private Integer fighter2Draws;

    @SerializedName("fighter2_statid")
    @Expose
    private Integer fighter2Statid;

    @SerializedName("fighter2_rank")
    @Expose
    private String fighter2Rank;

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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getFightcardOrder() {
        return fightcardOrder;
    }

    public void setFightcardOrder(Integer fightcardOrder) {
        this.fightcardOrder = fightcardOrder;
    }

    public Boolean getTitleFight() {
        return isTitleFight;
    }

    public void setTitleFight(Boolean titleFight) {
        isTitleFight = titleFight;
    }

    public Boolean getMainEvent() {
        return isMainEvent;
    }

    public void setMainEvent(Boolean mainEvent) {
        isMainEvent = mainEvent;
    }

    public Boolean getPrelim() {
        return isPrelim;
    }

    public void setPrelim(Boolean prelim) {
        isPrelim = prelim;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getEndingRoundNumber() {
        return endingRoundNumber;
    }

    public void setEndingRoundNumber(String endingRoundNumber) {
        this.endingRoundNumber = endingRoundNumber;
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

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public EventFightResult get_result() {
        return _result;
    }

    public void set_result(EventFightResult _result) {
        this._result = _result;
    }

    public Fighter getFighter1() {
        return fighter1;
    }

    public void setFighter1(Fighter fighter1) {
        this.fighter1 = fighter1;
    }

    public Integer getFighter1Id() {
        return fighter1Id;
    }

    public void setFighter1Id(Integer fighter1Id) {
        this.fighter1Id = fighter1Id;
    }

    public Integer getFighter1weight() {
        return fighter1weight;
    }

    public void setFighter1weight(Integer fighter1weight) {
        this.fighter1weight = fighter1weight;
    }

    public Integer getFighter1height() {
        return fighter1height;
    }

    public void setFighter1height(Integer fighter1height) {
        this.fighter1height = fighter1height;
    }

    public Integer getFighter1reach() {
        return fighter1reach;
    }

    public void setFighter1reach(Integer fighter1reach) {
        this.fighter1reach = fighter1reach;
    }

    public String getFighter1Nickname() {
        return fighter1Nickname;
    }

    public void setFighter1Nickname(String fighter1Nickname) {
        this.fighter1Nickname = fighter1Nickname;
    }

    public String getFighter1LastName() {
        return fighter1LastName;
    }

    public void setFighter1LastName(String fighter1LastName) {
        this.fighter1LastName = fighter1LastName;
    }

    public String getFighter1FirstName() {
        return fighter1FirstName;
    }

    public void setFighter1FirstName(String fighter1FirstName) {
        this.fighter1FirstName = fighter1FirstName;
    }

    public WeightCategory getFighter1WeightClass() {
        return fighter1WeightClass;
    }

    public void setFighter1WeightClass(WeightCategory fighter1WeightClass) {
        this.fighter1WeightClass = fighter1WeightClass;
    }

    public Boolean getFighter1IsWinner() {
        return fighter1IsWinner;
    }

    public void setFighter1IsWinner(Boolean fighter1IsWinner) {
        this.fighter1IsWinner = fighter1IsWinner;
    }

    public String getFighter1FullBodyImage() {
        return fighter1FullBodyImage;
    }

    public void setFighter1FullBodyImage(String fighter1FullBodyImage) {
        this.fighter1FullBodyImage = fighter1FullBodyImage;
    }

    public String getFighter1ProfileImage() {
        return fighter1ProfileImage;
    }

    public void setFighter1ProfileImage(String fighter1ProfileImage) {
        this.fighter1ProfileImage = fighter1ProfileImage;
    }

    public String getFighter1record() {
        return fighter1record;
    }

    public void setFighter1record(String fighter1record) {
        this.fighter1record = fighter1record;
    }

    public Integer getFighter1Wins() {
        return fighter1Wins;
    }

    public void setFighter1Wins(Integer fighter1Wins) {
        this.fighter1Wins = fighter1Wins;
    }

    public Integer getFighter1Losses() {
        return fighter1Losses;
    }

    public void setFighter1Losses(Integer fighter1Losses) {
        this.fighter1Losses = fighter1Losses;
    }

    public Integer getFighter1Draws() {
        return fighter1Draws;
    }

    public void setFighter1Draws(Integer fighter1Draws) {
        this.fighter1Draws = fighter1Draws;
    }

    public Integer getFighter1Statid() {
        return fighter1Statid;
    }

    public void setFighter1Statid(Integer fighter1Statid) {
        this.fighter1Statid = fighter1Statid;
    }

    public String getFighter1Rank() {
        return fighter1Rank;
    }

    public void setFighter1Rank(String fighter1Rank) {
        this.fighter1Rank = fighter1Rank;
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

    public String getFighter1Takedownaccuracy() {
        return fighter1Takedownaccuracy;
    }

    public void setFighter1Takedownaccuracy(String fighter1Takedownaccuracy) {
        this.fighter1Takedownaccuracy = fighter1Takedownaccuracy;
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

    public Fighter getFighter2() {
        return fighter2;
    }

    public void setFighter2(Fighter fighter2) {
        this.fighter2 = fighter2;
    }

    public Integer getFighter2Id() {
        return fighter2Id;
    }

    public void setFighter2Id(Integer fighter2Id) {
        this.fighter2Id = fighter2Id;
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

    public Integer getFighter2reach() {
        return fighter2reach;
    }

    public void setFighter2reach(Integer fighter2reach) {
        this.fighter2reach = fighter2reach;
    }

    public String getFighter2Nickname() {
        return fighter2Nickname;
    }

    public void setFighter2Nickname(String fighter2Nickname) {
        this.fighter2Nickname = fighter2Nickname;
    }

    public String getFighter2LastName() {
        return fighter2LastName;
    }

    public void setFighter2LastName(String fighter2LastName) {
        this.fighter2LastName = fighter2LastName;
    }

    public String getFighter2FirstName() {
        return fighter2FirstName;
    }

    public void setFighter2FirstName(String fighter2FirstName) {
        this.fighter2FirstName = fighter2FirstName;
    }

    public WeightCategory getFighter2WeightClass() {
        return fighter2WeightClass;
    }

    public void setFighter2WeightClass(WeightCategory fighter2WeightClass) {
        this.fighter2WeightClass = fighter2WeightClass;
    }

    public Boolean getFighter2IsWinner() {
        return fighter2IsWinner;
    }

    public void setFighter2IsWinner(Boolean fighter2IsWinner) {
        this.fighter2IsWinner = fighter2IsWinner;
    }

    public String getFighter2FullBodyImage() {
        return fighter2FullBodyImage;
    }

    public void setFighter2FullBodyImage(String fighter2FullBodyImage) {
        this.fighter2FullBodyImage = fighter2FullBodyImage;
    }

    public String getFighter2ProfileImage() {
        return fighter2ProfileImage;
    }

    public void setFighter2ProfileImage(String fighter2ProfileImage) {
        this.fighter2ProfileImage = fighter2ProfileImage;
    }

    public String getFighter2record() {
        return fighter2record;
    }

    public void setFighter2record(String fighter2record) {
        this.fighter2record = fighter2record;
    }

    public Integer getFighter2Wins() {
        return fighter2Wins;
    }

    public void setFighter2Wins(Integer fighter2Wins) {
        this.fighter2Wins = fighter2Wins;
    }

    public Integer getFighter2Losses() {
        return fighter2Losses;
    }

    public void setFighter2Losses(Integer fighter2Losses) {
        this.fighter2Losses = fighter2Losses;
    }

    public Integer getFighter2Draws() {
        return fighter2Draws;
    }

    public void setFighter2Draws(Integer fighter2Draws) {
        this.fighter2Draws = fighter2Draws;
    }

    public Integer getFighter2Statid() {
        return fighter2Statid;
    }

    public void setFighter2Statid(Integer fighter2Statid) {
        this.fighter2Statid = fighter2Statid;
    }

    public String getFighter2Rank() {
        return fighter2Rank;
    }

    public void setFighter2Rank(String fighter2Rank) {
        this.fighter2Rank = fighter2Rank;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EventFight{\n");
        sb.append("uid=").append(uid);
        sb.append(",\n\t id='").append(id).append('\'');
        sb.append(",\n\t eventId='").append(eventId).append('\'');
        sb.append(",\n\t fightcardOrder=").append(fightcardOrder);
        sb.append(",\n\t isTitleFight=").append(isTitleFight);
        sb.append(",\n\t isMainEvent=").append(isMainEvent);
        sb.append(",\n\t isPrelim=").append(isPrelim);
        sb.append(",\n\t endingTime='").append(endingTime).append('\'');
        sb.append(",\n\t endingRoundNumber='").append(endingRoundNumber).append('\'');
        sb.append(",\n\t fmStatsFeedUrl='").append(fmStatsFeedUrl).append('\'');
        sb.append(",\n\t fmFightRhythmFeedUrl='").append(fmFightRhythmFeedUrl).append('\'');
        sb.append(",\n\t resultId=").append(resultId);
        sb.append(",\n\t result=").append(result);
        sb.append(",\n\t fighter1=").append(fighter1);
        sb.append(",\n\t fighter1Id=").append(fighter1Id);
        sb.append(",\n\t fighter1weight=").append(fighter1weight);
        sb.append(",\n\t fighter1height=").append(fighter1height);
        sb.append(",\n\t fighter1reach=").append(fighter1reach);
        sb.append(",\n\t fighter1Nickname='").append(fighter1Nickname).append('\'');
        sb.append(",\n\t fighter1LastName='").append(fighter1LastName).append('\'');
        sb.append(",\n\t fighter1FirstName='").append(fighter1FirstName).append('\'');
        sb.append(",\n\t fighter1WeightClass=").append(fighter1WeightClass);
        sb.append(",\n\t fighter1IsWinner=").append(fighter1IsWinner);
        sb.append(",\n\t fighter1FullBodyImage='").append(fighter1FullBodyImage).append('\'');
        sb.append(",\n\t fighter1ProfileImage='").append(fighter1ProfileImage).append('\'');
        sb.append(",\n\t fighter1record='").append(fighter1record).append('\'');
        sb.append(",\n\t fighter1Wins=").append(fighter1Wins);
        sb.append(",\n\t fighter1Losses=").append(fighter1Losses);
        sb.append(",\n\t fighter1Draws=").append(fighter1Draws);
        sb.append(",\n\t fighter1Statid=").append(fighter1Statid);
        sb.append(",\n\t fighter1Rank='").append(fighter1Rank).append('\'');
        sb.append(",\n\t fighter1Averagefighttime='").append(fighter1Averagefighttime).append('\'');
        sb.append(",\n\t fighter1AveragefighttimeSeconds='").append(fighter1AveragefighttimeSeconds).append('\'');
        sb.append(",\n\t fighter1Kdaverage='").append(fighter1Kdaverage).append('\'');
        sb.append(",\n\t fighter1Slpm='").append(fighter1Slpm).append('\'');
        sb.append(",\n\t fighter1Strikingaccuracy='").append(fighter1Strikingaccuracy).append('\'');
        sb.append(",\n\t fighter1Sapm='").append(fighter1Sapm).append('\'');
        sb.append(",\n\t fighter1Strikingdefense='").append(fighter1Strikingdefense).append('\'');
        sb.append(",\n\t fighter1Takedownaverage='").append(fighter1Takedownaverage).append('\'');
        sb.append(",\n\t fighter1Takedownaccuracy='").append(fighter1Takedownaccuracy).append('\'');
        sb.append(",\n\t fighter1Takedowndefense='").append(fighter1Takedowndefense).append('\'');
        sb.append(",\n\t fighter1Submissionsaverage='").append(fighter1Submissionsaverage).append('\'');
        sb.append(",\n\t fighter2=").append(fighter2);
        sb.append(",\n\t fighter2Id=").append(fighter2Id);
        sb.append(",\n\t fighter2weight=").append(fighter2weight);
        sb.append(",\n\t fighter2height=").append(fighter2height);
        sb.append(",\n\t fighter2reach=").append(fighter2reach);
        sb.append(",\n\t fighter2Nickname='").append(fighter2Nickname).append('\'');
        sb.append(",\n\t fighter2LastName='").append(fighter2LastName).append('\'');
        sb.append(",\n\t fighter2FirstName='").append(fighter2FirstName).append('\'');
        sb.append(",\n\t fighter2WeightClass=").append(fighter2WeightClass);
        sb.append(",\n\t fighter2IsWinner=").append(fighter2IsWinner);
        sb.append(",\n\t fighter2FullBodyImage='").append(fighter2FullBodyImage).append('\'');
        sb.append(",\n\t fighter2ProfileImage='").append(fighter2ProfileImage).append('\'');
        sb.append(",\n\t fighter2record='").append(fighter2record).append('\'');
        sb.append(",\n\t fighter2Wins=").append(fighter2Wins);
        sb.append(",\n\t fighter2Losses=").append(fighter2Losses);
        sb.append(",\n\t fighter2Draws=").append(fighter2Draws);
        sb.append(",\n\t fighter2Statid=").append(fighter2Statid);
        sb.append(",\n\t fighter2Rank='").append(fighter2Rank).append('\'');
        sb.append(",\n\t fighter2Averagefighttime='").append(fighter2Averagefighttime).append('\'');
        sb.append(",\n\t fighter2AveragefighttimeSeconds='").append(fighter2AveragefighttimeSeconds).append('\'');
        sb.append(",\n\t fighter2Kdaverage='").append(fighter2Kdaverage).append('\'');
        sb.append(",\n\t fighter2Slpm='").append(fighter2Slpm).append('\'');
        sb.append(",\n\t fighter2Strikingaccuracy='").append(fighter2Strikingaccuracy).append('\'');
        sb.append(",\n\t fighter2Sapm='").append(fighter2Sapm).append('\'');
        sb.append(",\n\t fighter2Strikingdefense='").append(fighter2Strikingdefense).append('\'');
        sb.append(",\n\t fighter2Takedownaverage='").append(fighter2Takedownaverage).append('\'');
        sb.append(",\n\t fighter2Takedownaccuracy='").append(fighter2Takedownaccuracy).append('\'');
        sb.append(",\n\t fighter2Takedowndefense='").append(fighter2Takedowndefense).append('\'');
        sb.append(",\n\t fighter2Submissionsaverage='").append(fighter2Submissionsaverage).append('\'');
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.uid);
        dest.writeString(this.id);
        dest.writeString(this.eventId);
        dest.writeValue(this.fightcardOrder);
        dest.writeValue(this.isTitleFight);
        dest.writeValue(this.isMainEvent);
        dest.writeValue(this.isPrelim);
        dest.writeString(this.endingTime);
        dest.writeString(this.endingRoundNumber);
        dest.writeString(this.fmStatsFeedUrl);
        dest.writeString(this.fmFightRhythmFeedUrl);
        dest.writeValue(this.resultId);
        dest.writeParcelable(this.result, flags);
        dest.writeParcelable(this.fighter1, flags);
        dest.writeValue(this.fighter1Id);
        dest.writeValue(this.fighter1weight);
        dest.writeValue(this.fighter1height);
        dest.writeValue(this.fighter1reach);
        dest.writeString(this.fighter1Nickname);
        dest.writeString(this.fighter1LastName);
        dest.writeString(this.fighter1FirstName);
        dest.writeInt(this.fighter1WeightClass == null ? -1 : this.fighter1WeightClass.ordinal());
        dest.writeValue(this.fighter1IsWinner);
        dest.writeString(this.fighter1FullBodyImage);
        dest.writeString(this.fighter1ProfileImage);
        dest.writeString(this.fighter1record);
        dest.writeValue(this.fighter1Wins);
        dest.writeValue(this.fighter1Losses);
        dest.writeValue(this.fighter1Draws);
        dest.writeValue(this.fighter1Statid);
        dest.writeString(this.fighter1Rank);
        dest.writeString(this.fighter1Averagefighttime);
        dest.writeString(this.fighter1AveragefighttimeSeconds);
        dest.writeString(this.fighter1Kdaverage);
        dest.writeString(this.fighter1Slpm);
        dest.writeString(this.fighter1Strikingaccuracy);
        dest.writeString(this.fighter1Sapm);
        dest.writeString(this.fighter1Strikingdefense);
        dest.writeString(this.fighter1Takedownaverage);
        dest.writeString(this.fighter1Takedownaccuracy);
        dest.writeString(this.fighter1Takedowndefense);
        dest.writeString(this.fighter1Submissionsaverage);
        dest.writeParcelable(this.fighter2, flags);
        dest.writeValue(this.fighter2Id);
        dest.writeValue(this.fighter2weight);
        dest.writeValue(this.fighter2height);
        dest.writeValue(this.fighter2reach);
        dest.writeString(this.fighter2Nickname);
        dest.writeString(this.fighter2LastName);
        dest.writeString(this.fighter2FirstName);
        dest.writeInt(this.fighter2WeightClass == null ? -1 : this.fighter2WeightClass.ordinal());
        dest.writeValue(this.fighter2IsWinner);
        dest.writeString(this.fighter2FullBodyImage);
        dest.writeString(this.fighter2ProfileImage);
        dest.writeString(this.fighter2record);
        dest.writeValue(this.fighter2Wins);
        dest.writeValue(this.fighter2Losses);
        dest.writeValue(this.fighter2Draws);
        dest.writeValue(this.fighter2Statid);
        dest.writeString(this.fighter2Rank);
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
    }

    public Boolean getIsTitleFight() {
        return this.isTitleFight;
    }

    public void setIsTitleFight(Boolean isTitleFight) {
        this.isTitleFight = isTitleFight;
    }

    public Boolean getIsMainEvent() {
        return this.isMainEvent;
    }

    public void setIsMainEvent(Boolean isMainEvent) {
        this.isMainEvent = isMainEvent;
    }

    public Boolean getIsPrelim() {
        return this.isPrelim;
    }

    public void setIsPrelim(Boolean isPrelim) {
        this.isPrelim = isPrelim;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 442053779)
    public EventFightResult getResult() {
        Long __key = this.resultId;
        if (result__resolvedKey == null || !result__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EventFightResultDao targetDao = daoSession.getEventFightResultDao();
            EventFightResult resultNew = targetDao.load(__key);
            synchronized (this) {
                result = resultNew;
                result__resolvedKey = __key;
            }
        }
        return result;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 151856254)
    public void setResult(EventFightResult result) {
        synchronized (this) {
            this.result = result;
            resultId = result == null ? null : result.getUid();
            result__resolvedKey = resultId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1866111855)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEventFightDao() : null;
    }

    public EventFight() {
    }

    protected EventFight(Parcel in) {
        this.uid = (Long) in.readValue(Long.class.getClassLoader());
        this.id = in.readString();
        this.eventId = in.readString();
        this.fightcardOrder = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isTitleFight = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isMainEvent = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isPrelim = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.endingTime = in.readString();
        this.endingRoundNumber = in.readString();
        this.fmStatsFeedUrl = in.readString();
        this.fmFightRhythmFeedUrl = in.readString();
        this.resultId = (Long) in.readValue(Long.class.getClassLoader());
        this.result = in.readParcelable(EventFightResult.class.getClassLoader());
        this.fighter1 = in.readParcelable(Fighter.class.getClassLoader());
        this.fighter1Id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1weight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1reach = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1Nickname = in.readString();
        this.fighter1LastName = in.readString();
        this.fighter1FirstName = in.readString();
        int tmpFighter1WeightClass = in.readInt();
        this.fighter1WeightClass = tmpFighter1WeightClass == -1 ? null : WeightCategory.values()[tmpFighter1WeightClass];
        this.fighter1IsWinner = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.fighter1FullBodyImage = in.readString();
        this.fighter1ProfileImage = in.readString();
        this.fighter1record = in.readString();
        this.fighter1Wins = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1Losses = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1Draws = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1Statid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter1Rank = in.readString();
        this.fighter1Averagefighttime = in.readString();
        this.fighter1AveragefighttimeSeconds = in.readString();
        this.fighter1Kdaverage = in.readString();
        this.fighter1Slpm = in.readString();
        this.fighter1Strikingaccuracy = in.readString();
        this.fighter1Sapm = in.readString();
        this.fighter1Strikingdefense = in.readString();
        this.fighter1Takedownaverage = in.readString();
        this.fighter1Takedownaccuracy = in.readString();
        this.fighter1Takedowndefense = in.readString();
        this.fighter1Submissionsaverage = in.readString();
        this.fighter2 = in.readParcelable(Fighter.class.getClassLoader());
        this.fighter2Id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2weight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2height = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2reach = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2Nickname = in.readString();
        this.fighter2LastName = in.readString();
        this.fighter2FirstName = in.readString();
        int tmpFighter2WeightClass = in.readInt();
        this.fighter2WeightClass = tmpFighter2WeightClass == -1 ? null : WeightCategory.values()[tmpFighter2WeightClass];
        this.fighter2IsWinner = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.fighter2FullBodyImage = in.readString();
        this.fighter2ProfileImage = in.readString();
        this.fighter2record = in.readString();
        this.fighter2Wins = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2Losses = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2Draws = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2Statid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.fighter2Rank = in.readString();
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
    }

    @Generated(hash = 1764067168)
    public EventFight(Long uid, String id, String eventId, Integer fightcardOrder, Boolean isTitleFight,
                      Boolean isMainEvent, Boolean isPrelim, String endingTime, String endingRoundNumber, String fmStatsFeedUrl,
                      String fmFightRhythmFeedUrl, Long resultId, Integer fighter1Id, Integer fighter1weight, Integer fighter1height,
                      Integer fighter1reach, String fighter1Nickname, String fighter1LastName, String fighter1FirstName,
                      WeightCategory fighter1WeightClass, Boolean fighter1IsWinner, String fighter1FullBodyImage,
                      String fighter1ProfileImage, String fighter1record, Integer fighter1Wins, Integer fighter1Losses,
                      Integer fighter1Draws, Integer fighter1Statid, String fighter1Rank, String fighter1Averagefighttime,
                      String fighter1AveragefighttimeSeconds, String fighter1Kdaverage, String fighter1Slpm,
                      String fighter1Strikingaccuracy, String fighter1Sapm, String fighter1Strikingdefense,
                      String fighter1Takedownaverage, String fighter1Takedownaccuracy, String fighter1Takedowndefense,
                      String fighter1Submissionsaverage, Integer fighter2Id, Integer fighter2weight, Integer fighter2height,
                      Integer fighter2reach, String fighter2Nickname, String fighter2LastName, String fighter2FirstName,
                      WeightCategory fighter2WeightClass, Boolean fighter2IsWinner, String fighter2FullBodyImage,
                      String fighter2ProfileImage, String fighter2record, Integer fighter2Wins, Integer fighter2Losses,
                      Integer fighter2Draws, Integer fighter2Statid, String fighter2Rank, String fighter2Averagefighttime,
                      String fighter2AveragefighttimeSeconds, String fighter2Kdaverage, String fighter2Slpm,
                      String fighter2Strikingaccuracy, String fighter2Sapm, String fighter2Strikingdefense,
                      String fighter2Takedownaverage, String fighter2Takedownaccuracy, String fighter2Takedowndefense,
                      String fighter2Submissionsaverage) {
        this.uid = uid;
        this.id = id;
        this.eventId = eventId;
        this.fightcardOrder = fightcardOrder;
        this.isTitleFight = isTitleFight;
        this.isMainEvent = isMainEvent;
        this.isPrelim = isPrelim;
        this.endingTime = endingTime;
        this.endingRoundNumber = endingRoundNumber;
        this.fmStatsFeedUrl = fmStatsFeedUrl;
        this.fmFightRhythmFeedUrl = fmFightRhythmFeedUrl;
        this.resultId = resultId;
        this.fighter1Id = fighter1Id;
        this.fighter1weight = fighter1weight;
        this.fighter1height = fighter1height;
        this.fighter1reach = fighter1reach;
        this.fighter1Nickname = fighter1Nickname;
        this.fighter1LastName = fighter1LastName;
        this.fighter1FirstName = fighter1FirstName;
        this.fighter1WeightClass = fighter1WeightClass;
        this.fighter1IsWinner = fighter1IsWinner;
        this.fighter1FullBodyImage = fighter1FullBodyImage;
        this.fighter1ProfileImage = fighter1ProfileImage;
        this.fighter1record = fighter1record;
        this.fighter1Wins = fighter1Wins;
        this.fighter1Losses = fighter1Losses;
        this.fighter1Draws = fighter1Draws;
        this.fighter1Statid = fighter1Statid;
        this.fighter1Rank = fighter1Rank;
        this.fighter1Averagefighttime = fighter1Averagefighttime;
        this.fighter1AveragefighttimeSeconds = fighter1AveragefighttimeSeconds;
        this.fighter1Kdaverage = fighter1Kdaverage;
        this.fighter1Slpm = fighter1Slpm;
        this.fighter1Strikingaccuracy = fighter1Strikingaccuracy;
        this.fighter1Sapm = fighter1Sapm;
        this.fighter1Strikingdefense = fighter1Strikingdefense;
        this.fighter1Takedownaverage = fighter1Takedownaverage;
        this.fighter1Takedownaccuracy = fighter1Takedownaccuracy;
        this.fighter1Takedowndefense = fighter1Takedowndefense;
        this.fighter1Submissionsaverage = fighter1Submissionsaverage;
        this.fighter2Id = fighter2Id;
        this.fighter2weight = fighter2weight;
        this.fighter2height = fighter2height;
        this.fighter2reach = fighter2reach;
        this.fighter2Nickname = fighter2Nickname;
        this.fighter2LastName = fighter2LastName;
        this.fighter2FirstName = fighter2FirstName;
        this.fighter2WeightClass = fighter2WeightClass;
        this.fighter2IsWinner = fighter2IsWinner;
        this.fighter2FullBodyImage = fighter2FullBodyImage;
        this.fighter2ProfileImage = fighter2ProfileImage;
        this.fighter2record = fighter2record;
        this.fighter2Wins = fighter2Wins;
        this.fighter2Losses = fighter2Losses;
        this.fighter2Draws = fighter2Draws;
        this.fighter2Statid = fighter2Statid;
        this.fighter2Rank = fighter2Rank;
        this.fighter2Averagefighttime = fighter2Averagefighttime;
        this.fighter2AveragefighttimeSeconds = fighter2AveragefighttimeSeconds;
        this.fighter2Kdaverage = fighter2Kdaverage;
        this.fighter2Slpm = fighter2Slpm;
        this.fighter2Strikingaccuracy = fighter2Strikingaccuracy;
        this.fighter2Sapm = fighter2Sapm;
        this.fighter2Strikingdefense = fighter2Strikingdefense;
        this.fighter2Takedownaverage = fighter2Takedownaverage;
        this.fighter2Takedownaccuracy = fighter2Takedownaccuracy;
        this.fighter2Takedowndefense = fighter2Takedowndefense;
        this.fighter2Submissionsaverage = fighter2Submissionsaverage;
    }

    public static final Creator<EventFight> CREATOR = new Creator<EventFight>() {
        @Override
        public EventFight createFromParcel(Parcel source) {
            return new EventFight(source);
        }

        @Override
        public EventFight[] newArray(int size) {
            return new EventFight[size];
        }
    };

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1358543292)
    private transient EventFightDao myDao;

    @Generated(hash = 446399057)
    private transient Long result__resolvedKey;
}