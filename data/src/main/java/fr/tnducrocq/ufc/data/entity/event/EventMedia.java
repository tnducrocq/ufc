package fr.tnducrocq.ufc.data.entity.event;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fr.tnducrocq.ufc.data.entity.HasId;

/**
 * Created by tony on 10/08/2017.
 */

public class EventMedia implements HasId, Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("media_date")
    @Expose
    private String mediaDate;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("more_link_text")
    @Expose
    private String moreLinkText;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("internal_url")
    @Expose
    private String internalUrl;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("more_linkurl")
    @Expose
    private String moreLinkurl;

    @SerializedName("mobile_stream_url")
    @Expose
    private String mobileStreamUrl;

    @SerializedName("mobile_video_url")
    @Expose
    private String mobileVideoUrl;

    @SerializedName("last_modified")
    @Expose
    private String lastModified;

    @SerializedName("url_name")
    @Expose
    private String urlName;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("published_start_date")
    @Expose
    private String publishedStartDate;

    @Override
    public String id() {
        return Integer.toString(id);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaDate() {
        return mediaDate;
    }

    public void setMediaDate(String mediaDate) {
        this.mediaDate = mediaDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoreLinkText() {
        return moreLinkText;
    }

    public void setMoreLinkText(String moreLinkText) {
        this.moreLinkText = moreLinkText;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getInternalUrl() {
        return internalUrl;
    }

    public void setInternalUrl(String internalUrl) {
        this.internalUrl = internalUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoreLinkurl() {
        return moreLinkurl;
    }

    public void setMoreLinkurl(String moreLinkurl) {
        this.moreLinkurl = moreLinkurl;
    }

    public String getMobileStreamUrl() {
        return mobileStreamUrl;
    }

    public void setMobileStreamUrl(String mobileStreamUrl) {
        this.mobileStreamUrl = mobileStreamUrl;
    }

    public String getMobileVideoUrl() {
        return mobileVideoUrl;
    }

    public void setMobileVideoUrl(String mobileVideoUrl) {
        this.mobileVideoUrl = mobileVideoUrl;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPublishedStartDate() {
        return publishedStartDate;
    }

    public void setPublishedStartDate(String publishedStartDate) {
        this.publishedStartDate = publishedStartDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EventMedia{");
        sb.append("id=").append(id);
        sb.append(", mediaDate='").append(mediaDate).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", moreLinkText='").append(moreLinkText).append('\'');
        sb.append(", thumbnail='").append(thumbnail).append('\'');
        sb.append(", internalUrl='").append(internalUrl).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", moreLinkurl='").append(moreLinkurl).append('\'');
        sb.append(", mobileStreamUrl='").append(mobileStreamUrl).append('\'');
        sb.append(", mobileVideoUrl='").append(mobileVideoUrl).append('\'');
        sb.append(", lastModified='").append(lastModified).append('\'');
        sb.append(", urlName='").append(urlName).append('\'');
        sb.append(", created='").append(created).append('\'');
        sb.append(", publishedStartDate='").append(publishedStartDate).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.mediaDate);
        dest.writeString(this.type);
        dest.writeString(this.description);
        dest.writeString(this.moreLinkText);
        dest.writeString(this.thumbnail);
        dest.writeString(this.internalUrl);
        dest.writeString(this.title);
        dest.writeString(this.moreLinkurl);
        dest.writeString(this.mobileStreamUrl);
        dest.writeString(this.mobileVideoUrl);
        dest.writeString(this.lastModified);
        dest.writeString(this.urlName);
        dest.writeString(this.created);
        dest.writeString(this.publishedStartDate);
    }

    public EventMedia() {
    }

    protected EventMedia(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mediaDate = in.readString();
        this.type = in.readString();
        this.description = in.readString();
        this.moreLinkText = in.readString();
        this.thumbnail = in.readString();
        this.internalUrl = in.readString();
        this.title = in.readString();
        this.moreLinkurl = in.readString();
        this.mobileStreamUrl = in.readString();
        this.mobileVideoUrl = in.readString();
        this.lastModified = in.readString();
        this.urlName = in.readString();
        this.created = in.readString();
        this.publishedStartDate = in.readString();
    }

    public static final Parcelable.Creator<EventMedia> CREATOR = new Parcelable.Creator<EventMedia>() {
        @Override
        public EventMedia createFromParcel(Parcel source) {
            return new EventMedia(source);
        }

        @Override
        public EventMedia[] newArray(int size) {
            return new EventMedia[size];
        }
    };
}
