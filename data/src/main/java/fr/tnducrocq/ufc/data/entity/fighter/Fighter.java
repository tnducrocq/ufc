package fr.tnducrocq.ufc.data.entity.fighter;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fr.tnducrocq.ufc.data.entity.HasId;
import fr.tnducrocq.ufc.data.utils.WeightCategory;

public class Fighter implements HasId, Parcelable {

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

    @SerializedName("rank")
    @Expose
    private String _rank;

    @SerializedName("pound_for_pound_rank")
    @Expose
    private String poundForPoundRank;

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
    private WeightCategory weightClass;

    public Fighter() {
    }

    public Fighter(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String id() {
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

    public WeightCategory getWeightClass() {
        return weightClass;
    }

    public void setWeightClass(WeightCategory weightClass) {
        this.weightClass = weightClass;
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

    public int getRank() {
        try {
            return Integer.valueOf(_rank);
        } catch (NumberFormatException e) {
            return Integer.MAX_VALUE;
        }
    }

    public void setRank(int rank) {
        this._rank = Integer.toString(rank);
    }

    public String get_rank() {
        return _rank;
    }

    public void set_rank(String _rank) {
        this._rank = _rank;
    }

    public String getPoundForPoundRank() {
        return poundForPoundRank;
    }

    public void setPoundForPoundRank(String poundForPoundRank) {
        this.poundForPoundRank = poundForPoundRank;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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
        dest.writeValue(this._rank);
        dest.writeString(this.poundForPoundRank);
        dest.writeString(this.thumbnail);
        dest.writeString(this.beltThumbnail);
        dest.writeString(this.leftFullBodyImage);
        dest.writeString(this.rightFullBodyImage);
        dest.writeString(this.profileImage);
        dest.writeString(this.link);
        dest.writeInt(this.weightClass == null ? -1 : this.weightClass.ordinal());
    }

    protected Fighter(Parcel in) {
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
        this._rank = in.readString();
        this.poundForPoundRank = in.readString();
        this.thumbnail = in.readString();
        this.beltThumbnail = in.readString();
        this.leftFullBodyImage = in.readString();
        this.rightFullBodyImage = in.readString();
        this.profileImage = in.readString();
        this.link = in.readString();
        int tmpWeightClass = in.readInt();
        this.weightClass = tmpWeightClass == -1 ? null : WeightCategory.values()[tmpWeightClass];
    }

    public static final Parcelable.Creator<Fighter> CREATOR = new Parcelable.Creator<Fighter>() {
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
        final StringBuffer sb = new StringBuffer("Fighter{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nickname='").append(nickname).append('\'');
        sb.append(", wins=").append(wins);
        sb.append(", statid=").append(statid);
        sb.append(", losses=").append(losses);
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", titleHolder=").append(titleHolder);
        sb.append(", draws=").append(draws);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", fighterStatus='").append(fighterStatus).append('\'');
        sb.append(", rank='").append(getRank()).append('\'');
        sb.append(", poundForPoundRank='").append(poundForPoundRank).append('\'');
        sb.append(", thumbnail='").append(thumbnail).append('\'');
        sb.append(", beltThumbnail='").append(beltThumbnail).append('\'');
        sb.append(", leftFullBodyImage='").append(leftFullBodyImage).append('\'');
        sb.append(", rightFullBodyImage='").append(rightFullBodyImage).append('\'');
        sb.append(", profileImage='").append(profileImage).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", weightClass=").append(weightClass);
        sb.append('}');
        return sb.toString();
    }
}