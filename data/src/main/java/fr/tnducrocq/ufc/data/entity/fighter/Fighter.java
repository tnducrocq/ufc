package fr.tnducrocq.ufc.data.entity.fighter;

import android.os.Parcel;
import android.os.Parcelable;

import com.fcannizzaro.jsoup.annotations.JsoupProcessor;
import com.fcannizzaro.jsoup.annotations.interfaces.Html;
import com.fcannizzaro.jsoup.annotations.interfaces.Text;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.math.NumberUtils;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.tnducrocq.ufc.data.entity.HasId;

@Entity(indexes = {
        @Index(value = "id ASC", unique = true)
})
public class Fighter implements HasId, Parcelable {

    @Id(autoincrement = true)
    private Long uid;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("wins")
    @Expose
    private Integer wins;

    @SerializedName("statid")
    @Expose
    private Integer statid;

    @SerializedName("losses")
    @Expose
    private Integer losses;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("title_holder")
    @Expose
    private Boolean titleHolder;

    @SerializedName("draws")
    @Expose
    private Integer draws;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("fighter_status")
    @Expose
    private String fighterStatus;

    private Integer rank;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("belt_thumbnail")
    @Expose
    private String beltThumbnail;

    @SerializedName("left_full_body_image")
    @Expose
    private String leftFullBodyImage;

    @SerializedName("right_full_body_image")
    @Expose
    private String rightFullBodyImage;

    @SerializedName("profile_image")
    @Expose
    private String profileImage;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("weight_class")
    @Expose
    @Convert(converter = WeightCategoryConverter.class, columnType = Integer.class)
    private WeightCategory weightClass;

    /**
     * JSOUP Field
     */

    private boolean hasDetails = false;

    @Text(value = "#fighter-details h1", optional = true)
    private String name;

    @Text(value = "td#fighter-from", optional = true)
    private String hometown;

    @Text(value = "td#fighter-lives-in", optional = true)
    private String location;

    @Text(value = "td#fighter-age", optional = true)
    private String age;

    private String height;
    private Integer height_cm;

    @Text(value = "td#fighter-height", optional = true)
    private void height(String text) {
        if (text != null) {
            //5' 9" ( 175 cm )
            Pattern p = Pattern.compile("([0-9]*' [0-9]*\") \\( ([0-9]*) cm \\)");
            Matcher m = p.matcher(text);
            if (m.find()) {
                MatchResult mr = m.toMatchResult();
                height = mr.group(1);//2.8
                height_cm = Integer.parseInt(mr.group(2));
            }
        }
    }

    private Integer weight;
    private Integer weight_kg;

    @Text(value = "td#fighter-weight", optional = true)
    private void weight(String text) {
        if (text != null) {
            //155 livres ( 70 kg )
            Pattern p = Pattern.compile("([0-9]*) livres \\( ([0-9]*) kg \\)");
            Matcher m = p.matcher(text);
            if (m.find()) {
                MatchResult mr = m.toMatchResult();
                weight = Integer.parseInt(mr.group(1));//2.8
                weight_kg = Integer.parseInt(mr.group(2));
            }
        }
    }

    private Integer reach;
    private Integer reach_cm;

    @Text(value = "td#fighter-reach", optional = true)
    private void reach(String text) {
        int value = NumberUtils.toInt(text.trim().replace("\"", ""), -1);
        if (value != -1) {
            reach = value;
            reach_cm = (int) (2.54 * value);
        }
    }

    private Integer legReach;
    private Integer legReach_cm;

    @Text(value = "td#fighter-leg-reach", optional = true)
    private void legReach(String text) {
        int value = NumberUtils.toInt(text.trim().replace("\"", ""), -1);
        if (value != -1) {
            legReach = value;
            legReach_cm = (int) (2.54 * value);
        }
    }

    @Text(value = "td#fighter-skill-record", optional = true)
    private String record;

    @Text(value = "td#fighter-college", optional = true)
    private String college;

    @Text(value = "td#fighter-degree", optional = true)
    private String degree;


    private Integer strikes_attempted;

    @Text(value = "#fight-history > .overall-stats > .graph > .max-number", optional = true)
    private void strikes_attempted(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_attempted = value;
        }
    }

    private Integer strikes_successful;

    @Text(value = "#types-of-successful-strikes-graph-maximum", optional = true)
    private void strikes_successful(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_successful = value;
        }
    }

    private Integer strikes_standing;

    @Text(value = "#types-of-successful-strikes-graph > .red-text-bar > .bar-text", optional = true)
    private void strikes_standing(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_standing = value;
        }
    }

    private Integer strikes_clinch;

    @Text(value = "#types-of-successful-strikes-graph > .dark-red-text-bar > .bar-text", optional = true)
    private void strikes_clinch(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_clinch = value;
        }
    }

    private Integer strikes_ground;

    @Text(value = "#types-of-successful-strikes-graph > .grey-text-bar > .bar-text", optional = true)
    private void strikes_ground(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_ground = value;
        }
    }

    private Integer striking_defense_pecentage;

    @Text(value = "#striking-defense-pecentage", optional = true)
    private void striking_defense_pecentage(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            striking_defense_pecentage = value;
        }
    }

    private Integer takedowns_attempted;
    private Integer takedowns_successful;

    @Html(value = "#fight-history > .overall-stats", index = 1, optional = true)
    private void takedowns(Element elt) {

        Pattern p = Pattern.compile("([0-9]*).*");
        Matcher m = p.matcher(elt.select(".graph > .max-number").text());
        if (m.find()) {
            MatchResult mr = m.toMatchResult();
            this.takedowns_attempted = Integer.parseInt(mr.group(1));//2.8
        }

        String tmp = elt.select("#total-takedowns-number").text().trim();
        int value = NumberUtils.toInt(tmp.trim(), -1);
        if (value != -1) {
            takedowns_successful = value;
        }
    }

    private Integer takedowns_submissions;

    @Text(value = "#successful-submissions", optional = true)
    private void takedowns_submissions(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            takedowns_submissions = value;
        }
    }

    private Integer takedowns_passes;

    @Text(value = "#successful-passes", optional = true)
    private void takedowns_passes(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            takedowns_passes = value;
        }
    }

    private Integer takedowns_sweeps;

    @Text(value = "#successful-sweeps", optional = true)
    private void takedowns_sweeps(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            takedowns_sweeps = value;
        }
    }

    private Integer takedown_defense_pecentage;

    @Text(value = "#takedown-defense-percentage", optional = true)
    private void takedown_defense_pecentage(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            takedown_defense_pecentage = value;
        }
    }

    public Fighter() {
    }

    public Fighter(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fighter fighter = (Fighter) o;

        return id != null ? id.equals(fighter.id) : fighter.id == null;
    }

    public void fillWithHtml(String html) {
        Element document = Jsoup.parseBodyFragment(html);
        JsoupProcessor.from(document, Fighter.class, this);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getHeight_cm() {
        return height_cm;
    }

    public void setHeight_cm(Integer height_cm) {
        this.height_cm = height_cm;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getWeight_kg() {
        return weight_kg;
    }

    public void setWeight_kg(Integer weight_kg) {
        this.weight_kg = weight_kg;
    }

    public Integer getReach() {
        return reach;
    }

    public void setReach(Integer reach) {
        this.reach = reach;
    }

    public Integer getReach_cm() {
        return reach_cm;
    }

    public void setReach_cm(Integer reach_cm) {
        this.reach_cm = reach_cm;
    }

    public Integer getLegReach() {
        return legReach;
    }

    public void setLegReach(Integer legReach) {
        this.legReach = legReach;
    }

    public Integer getLegReach_cm() {
        return legReach_cm;
    }

    public void setLegReach_cm(Integer legReach_cm) {
        this.legReach_cm = legReach_cm;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Integer getStrikes_attempted() {
        return strikes_attempted;
    }

    public void setStrikes_attempted(Integer strikes_attempted) {
        this.strikes_attempted = strikes_attempted;
    }

    public Integer getStrikes_successful() {
        return strikes_successful;
    }

    public void setStrikes_successful(Integer strikes_successful) {
        this.strikes_successful = strikes_successful;
    }

    public Integer getStrikes_standing() {
        return strikes_standing;
    }

    public void setStrikes_standing(Integer strikes_standing) {
        this.strikes_standing = strikes_standing;
    }

    public Integer getStrikes_clinch() {
        return strikes_clinch;
    }

    public void setStrikes_clinch(Integer strikes_clinch) {
        this.strikes_clinch = strikes_clinch;
    }

    public Integer getStrikes_ground() {
        return strikes_ground;
    }

    public void setStrikes_ground(Integer strikes_ground) {
        this.strikes_ground = strikes_ground;
    }

    public Integer getStriking_defense_pecentage() {
        return striking_defense_pecentage;
    }

    public void setStriking_defense_pecentage(Integer striking_defense_pecentage) {
        this.striking_defense_pecentage = striking_defense_pecentage;
    }

    public Integer getTakedowns_attempted() {
        return takedowns_attempted;
    }

    public void setTakedowns_attempted(Integer takedowns_attempted) {
        this.takedowns_attempted = takedowns_attempted;
    }

    public Integer getTakedowns_successful() {
        return takedowns_successful;
    }

    public void setTakedowns_successful(Integer takedowns_successful) {
        this.takedowns_successful = takedowns_successful;
    }

    public Integer getTakedowns_submissions() {
        return takedowns_submissions;
    }

    public void setTakedowns_submissions(Integer takedowns_submissions) {
        this.takedowns_submissions = takedowns_submissions;
    }

    public Integer getTakedowns_passes() {
        return takedowns_passes;
    }

    public void setTakedowns_passes(Integer takedowns_passes) {
        this.takedowns_passes = takedowns_passes;
    }

    public Integer getTakedowns_sweeps() {
        return takedowns_sweeps;
    }

    public void setTakedowns_sweeps(Integer takedowns_sweeps) {
        this.takedowns_sweeps = takedowns_sweeps;
    }

    public Integer getTakedown_defense_pecentage() {
        return takedown_defense_pecentage;
    }

    public void setTakedown_defense_pecentage(Integer takedown_defense_pecentage) {
        this.takedown_defense_pecentage = takedown_defense_pecentage;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getStatid() {
        return statid;
    }

    public void setStatid(Integer statid) {
        this.statid = statid;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getTitleHolder() {
        return titleHolder;
    }

    public void setTitleHolder(Boolean titleHolder) {
        this.titleHolder = titleHolder;
    }

    public Integer getDraws() {
        return draws;
    }

    public void setDraws(Integer draws) {
        this.draws = draws;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFighterStatus() {
        return fighterStatus;
    }

    public void setFighterStatus(String fighterStatus) {
        this.fighterStatus = fighterStatus;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBeltThumbnail() {
        return beltThumbnail;
    }

    public void setBeltThumbnail(String beltThumbnail) {
        this.beltThumbnail = beltThumbnail;
    }

    public String getLeftFullBodyImage() {
        return leftFullBodyImage;
    }

    public void setLeftFullBodyImage(String leftFullBodyImage) {
        this.leftFullBodyImage = leftFullBodyImage;
    }

    public String getRightFullBodyImage() {
        return rightFullBodyImage;
    }

    public void setRightFullBodyImage(String rightFullBodyImage) {
        this.rightFullBodyImage = rightFullBodyImage;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public WeightCategory getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(WeightCategory weightClass) {
        this.weightClass = weightClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasDetails() {
        return hasDetails;
    }

    public void setDetails(boolean hasDetails) {
        this.hasDetails = hasDetails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.uid);
        dest.writeString(this.id);
        dest.writeString(this.nickname);
        dest.writeValue(this.wins);
        dest.writeValue(this.statid);
        dest.writeValue(this.losses);
        dest.writeString(this.lastName);
        dest.writeValue(this.titleHolder);
        dest.writeValue(this.draws);
        dest.writeString(this.firstName);
        dest.writeString(this.fighterStatus);
        dest.writeValue(this.rank);
        dest.writeString(this.thumbnail);
        dest.writeString(this.beltThumbnail);
        dest.writeString(this.leftFullBodyImage);
        dest.writeString(this.rightFullBodyImage);
        dest.writeString(this.profileImage);
        dest.writeString(this.link);
        dest.writeInt(this.weightClass == null ? -1 : this.weightClass.ordinal());
        dest.writeByte(this.hasDetails ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
        dest.writeString(this.hometown);
        dest.writeString(this.location);
        dest.writeString(this.age);
        dest.writeString(this.height);
        dest.writeValue(this.height_cm);
        dest.writeValue(this.weight);
        dest.writeValue(this.weight_kg);
        dest.writeValue(this.reach);
        dest.writeValue(this.reach_cm);
        dest.writeValue(this.legReach);
        dest.writeValue(this.legReach_cm);
        dest.writeString(this.record);
        dest.writeString(this.college);
        dest.writeString(this.degree);
        dest.writeValue(this.strikes_attempted);
        dest.writeValue(this.strikes_successful);
        dest.writeValue(this.strikes_standing);
        dest.writeValue(this.strikes_clinch);
        dest.writeValue(this.strikes_ground);
        dest.writeValue(this.striking_defense_pecentage);
        dest.writeValue(this.takedowns_attempted);
        dest.writeValue(this.takedowns_successful);
        dest.writeValue(this.takedowns_submissions);
        dest.writeValue(this.takedowns_passes);
        dest.writeValue(this.takedowns_sweeps);
        dest.writeValue(this.takedown_defense_pecentage);
    }

    protected Fighter(Parcel in) {
        this.uid = (Long) in.readValue(Long.class.getClassLoader());
        this.id = in.readString();
        this.nickname = in.readString();
        this.wins = (Integer) in.readValue(Integer.class.getClassLoader());
        this.statid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.losses = (Integer) in.readValue(Integer.class.getClassLoader());
        this.lastName = in.readString();
        this.titleHolder = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.draws = (Integer) in.readValue(Integer.class.getClassLoader());
        this.firstName = in.readString();
        this.fighterStatus = in.readString();
        this.rank = (Integer) in.readValue(Integer.class.getClassLoader());
        this.thumbnail = in.readString();
        this.beltThumbnail = in.readString();
        this.leftFullBodyImage = in.readString();
        this.rightFullBodyImage = in.readString();
        this.profileImage = in.readString();
        this.link = in.readString();
        int tmpWeightClass = in.readInt();
        this.weightClass = tmpWeightClass == -1 ? null : WeightCategory.values()[tmpWeightClass];
        this.hasDetails = in.readByte() != 0;
        this.name = in.readString();
        this.hometown = in.readString();
        this.location = in.readString();
        this.age = in.readString();
        this.height = in.readString();
        this.height_cm = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weight_kg = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reach = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reach_cm = (Integer) in.readValue(Integer.class.getClassLoader());
        this.legReach = (Integer) in.readValue(Integer.class.getClassLoader());
        this.legReach_cm = (Integer) in.readValue(Integer.class.getClassLoader());
        this.record = in.readString();
        this.college = in.readString();
        this.degree = in.readString();
        this.strikes_attempted = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strikes_successful = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strikes_standing = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strikes_clinch = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strikes_ground = (Integer) in.readValue(Integer.class.getClassLoader());
        this.striking_defense_pecentage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_attempted = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_successful = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_submissions = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_passes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_sweeps = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedown_defense_pecentage = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    @Generated(hash = 2018069130)
    public Fighter(Long uid, String id, String nickname, Integer wins, Integer statid, Integer losses,
                   String lastName, Boolean titleHolder, Integer draws, String firstName, String fighterStatus,
                   Integer rank, String thumbnail, String beltThumbnail, String leftFullBodyImage,
                   String rightFullBodyImage, String profileImage, String link, WeightCategory weightClass,
                   boolean hasDetails, String name, String hometown, String location, String age, String height,
                   Integer height_cm, Integer weight, Integer weight_kg, Integer reach, Integer reach_cm,
                   Integer legReach, Integer legReach_cm, String record, String college, String degree,
                   Integer strikes_attempted, Integer strikes_successful, Integer strikes_standing,
                   Integer strikes_clinch, Integer strikes_ground, Integer striking_defense_pecentage,
                   Integer takedowns_attempted, Integer takedowns_successful, Integer takedowns_submissions,
                   Integer takedowns_passes, Integer takedowns_sweeps, Integer takedown_defense_pecentage) {
        this.uid = uid;
        this.id = id;
        this.nickname = nickname;
        this.wins = wins;
        this.statid = statid;
        this.losses = losses;
        this.lastName = lastName;
        this.titleHolder = titleHolder;
        this.draws = draws;
        this.firstName = firstName;
        this.fighterStatus = fighterStatus;
        this.rank = rank;
        this.thumbnail = thumbnail;
        this.beltThumbnail = beltThumbnail;
        this.leftFullBodyImage = leftFullBodyImage;
        this.rightFullBodyImage = rightFullBodyImage;
        this.profileImage = profileImage;
        this.link = link;
        this.weightClass = weightClass;
        this.hasDetails = hasDetails;
        this.name = name;
        this.hometown = hometown;
        this.location = location;
        this.age = age;
        this.height = height;
        this.height_cm = height_cm;
        this.weight = weight;
        this.weight_kg = weight_kg;
        this.reach = reach;
        this.reach_cm = reach_cm;
        this.legReach = legReach;
        this.legReach_cm = legReach_cm;
        this.record = record;
        this.college = college;
        this.degree = degree;
        this.strikes_attempted = strikes_attempted;
        this.strikes_successful = strikes_successful;
        this.strikes_standing = strikes_standing;
        this.strikes_clinch = strikes_clinch;
        this.strikes_ground = strikes_ground;
        this.striking_defense_pecentage = striking_defense_pecentage;
        this.takedowns_attempted = takedowns_attempted;
        this.takedowns_successful = takedowns_successful;
        this.takedowns_submissions = takedowns_submissions;
        this.takedowns_passes = takedowns_passes;
        this.takedowns_sweeps = takedowns_sweeps;
        this.takedown_defense_pecentage = takedown_defense_pecentage;
    }

    public static final Creator<Fighter> CREATOR = new Creator<Fighter>() {
        @Override
        public Fighter createFromParcel(Parcel source) {
            return new Fighter(source);
        }

        @Override
        public Fighter[] newArray(int size) {
            return new Fighter[size];
        }
    };

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Fighter{\n");
        sb.append("uid=").append(uid);
        sb.append(",\n\t id='").append(id).append('\'');
        sb.append(",\n\t nickname='").append(nickname).append('\'');
        sb.append(",\n\t wins=").append(wins);
        sb.append(",\n\t statid=").append(statid);
        sb.append(",\n\t losses=").append(losses);
        sb.append(",\n\t lastName='").append(lastName).append('\'');
        sb.append(",\n\t titleHolder=").append(titleHolder);
        sb.append(",\n\t draws=").append(draws);
        sb.append(",\n\t firstName='").append(firstName).append('\'');
        sb.append(",\n\t fighterStatus='").append(fighterStatus).append('\'');
        sb.append(",\n\t rank=").append(rank);
        sb.append(",\n\t thumbnail='").append(thumbnail).append('\'');
        sb.append(",\n\t beltThumbnail='").append(beltThumbnail).append('\'');
        sb.append(",\n\t leftFullBodyImage='").append(leftFullBodyImage).append('\'');
        sb.append(",\n\t rightFullBodyImage='").append(rightFullBodyImage).append('\'');
        sb.append(",\n\t profileImage='").append(profileImage).append('\'');
        sb.append(",\n\t link='").append(link).append('\'');
        sb.append(",\n\t weightClass=").append(weightClass);
        sb.append(",\n\t hasDetails=").append(hasDetails);
        sb.append(",\n\t name='").append(name).append('\'');
        sb.append(",\n\t hometown='").append(hometown).append('\'');
        sb.append(",\n\t location='").append(location).append('\'');
        sb.append(",\n\t age='").append(age).append('\'');
        sb.append(",\n\t height='").append(height).append('\'');
        sb.append(",\n\t height_cm=").append(height_cm);
        sb.append(",\n\t weight=").append(weight);
        sb.append(",\n\t weight_kg=").append(weight_kg);
        sb.append(",\n\t reach=").append(reach);
        sb.append(",\n\t reach_cm=").append(reach_cm);
        sb.append(",\n\t legReach=").append(legReach);
        sb.append(",\n\t legReach_cm=").append(legReach_cm);
        sb.append(",\n\t record='").append(record).append('\'');
        sb.append(",\n\t college='").append(college).append('\'');
        sb.append(",\n\t degree='").append(degree).append('\'');
        sb.append(",\n\t strikes_attempted=").append(strikes_attempted);
        sb.append(",\n\t strikes_successful=").append(strikes_successful);
        sb.append(",\n\t strikes_standing=").append(strikes_standing);
        sb.append(",\n\t strikes_clinch=").append(strikes_clinch);
        sb.append(",\n\t strikes_ground=").append(strikes_ground);
        sb.append(",\n\t striking_defense_pecentage=").append(striking_defense_pecentage);
        sb.append(",\n\t takedowns_attempted=").append(takedowns_attempted);
        sb.append(",\n\t takedowns_successful=").append(takedowns_successful);
        sb.append(",\n\t takedowns_submissions=").append(takedowns_submissions);
        sb.append(",\n\t takedowns_passes=").append(takedowns_passes);
        sb.append(",\n\t takedowns_sweeps=").append(takedowns_sweeps);
        sb.append(",\n\t takedown_defense_pecentage=").append(takedown_defense_pecentage);
        sb.append("\n}");
        return sb.toString();
    }

    public boolean getHasDetails() {
        return this.hasDetails;
    }

    public void setHasDetails(boolean hasDetails) {
        this.hasDetails = hasDetails;
    }
}