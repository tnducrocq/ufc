package fr.tnducrocq.ufc.data.entity.event;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EventFightResult implements Parcelable {

    @Id(autoincrement = true)
    private Long uid;

    @SerializedName("Method")
    @Expose
    private String method;

    @SerializedName("EndingRound")
    @Expose
    private String endingRound;

    @SerializedName("EndingTime")
    @Expose
    private String endingTime;

    @SerializedName("Submission")
    @Expose
    private String submission;

    @SerializedName("EndStrike")
    @Expose
    private String endStrike;

    @SerializedName("EndTarget")
    @Expose
    private String endTarget;

    @SerializedName("EndPosition")
    @Expose
    private String endPosition;

    @SerializedName("EndNotes")
    @Expose
    private String endNotes;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndingRound() {
        return endingRound;
    }

    public void setEndingRound(String endingRound) {
        this.endingRound = endingRound;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public String getSubmission() {
        return submission;
    }

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public String getEndStrike() {
        return endStrike;
    }

    public void setEndStrike(String endStrike) {
        this.endStrike = endStrike;
    }

    public String getEndTarget() {
        return endTarget;
    }

    public void setEndTarget(String endTarget) {
        this.endTarget = endTarget;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    public String getEndNotes() {
        return endNotes;
    }

    public void setEndNotes(String endNotes) {
        this.endNotes = endNotes;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EventFightResult{\n");
        sb.append("uid=").append(uid);
        sb.append(",\n\t method='").append(method).append('\'');
        sb.append(",\n\t endingRound='").append(endingRound).append('\'');
        sb.append(",\n\t endingTime='").append(endingTime).append('\'');
        sb.append(",\n\t submission='").append(submission).append('\'');
        sb.append(",\n\t endStrike='").append(endStrike).append('\'');
        sb.append(",\n\t endTarget='").append(endTarget).append('\'');
        sb.append(",\n\t endPosition='").append(endPosition).append('\'');
        sb.append(",\n\t endNotes='").append(endNotes).append('\'');
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
        dest.writeString(this.method);
        dest.writeString(this.endingRound);
        dest.writeString(this.endingTime);
        dest.writeString(this.submission);
        dest.writeString(this.endStrike);
        dest.writeString(this.endTarget);
        dest.writeString(this.endPosition);
        dest.writeString(this.endNotes);
    }

    public EventFightResult() {
    }

    protected EventFightResult(Parcel in) {
        this.uid = (Long) in.readValue(Long.class.getClassLoader());
        this.method = in.readString();
        this.endingRound = in.readString();
        this.endingTime = in.readString();
        this.submission = in.readString();
        this.endStrike = in.readString();
        this.endTarget = in.readString();
        this.endPosition = in.readString();
        this.endNotes = in.readString();
    }

    @Generated(hash = 1039417050)
    public EventFightResult(Long uid, String method, String endingRound, String endingTime,
            String submission, String endStrike, String endTarget, String endPosition,
            String endNotes) {
        this.uid = uid;
        this.method = method;
        this.endingRound = endingRound;
        this.endingTime = endingTime;
        this.submission = submission;
        this.endStrike = endStrike;
        this.endTarget = endTarget;
        this.endPosition = endPosition;
        this.endNotes = endNotes;
    }

    public static final Creator<EventFightResult> CREATOR = new Creator<EventFightResult>() {
        @Override
        public EventFightResult createFromParcel(Parcel source) {
            return new EventFightResult(source);
        }

        @Override
        public EventFightResult[] newArray(int size) {
            return new EventFightResult[size];
        }
    };
}