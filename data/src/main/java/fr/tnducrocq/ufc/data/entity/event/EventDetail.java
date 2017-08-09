package fr.tnducrocq.ufc.data.entity.event;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class EventDetail implements Parcelable {

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

    @SerializedName("embedded_id")
    @Expose
    private String embeddedId;

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

    @SerializedName("keyword_ids")
    @Expose
    private List<Integer> keywordIds = null;

    @SerializedName("published_start_date")
    @Expose
    private String publishedStartDate;

    public EventDetail() {
    }

    public Integer getId() {
        return id;
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

    public String getEmbeddedId() {
        return embeddedId;
    }

    public void setEmbeddedId(String embeddedId) {
        this.embeddedId = embeddedId;
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

    public List<Integer> getKeywordIds() {
        return keywordIds;
    }

    public void setKeywordIds(List<Integer> keywordIds) {
        this.keywordIds = keywordIds;
    }

    public String getPublishedStartDate() {
        return publishedStartDate;
    }

    public void setPublishedStartDate(String publishedStartDate) {
        this.publishedStartDate = publishedStartDate;
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
        dest.writeString(this.embeddedId);
        dest.writeString(this.mobileStreamUrl);
        dest.writeString(this.mobileVideoUrl);
        dest.writeString(this.lastModified);
        dest.writeString(this.urlName);
        dest.writeString(this.created);
        dest.writeList(this.keywordIds);
        dest.writeString(this.publishedStartDate);
    }

    protected EventDetail(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mediaDate = in.readString();
        this.type = in.readString();
        this.description = in.readString();
        this.moreLinkText = in.readString();
        this.thumbnail = in.readString();
        this.internalUrl = in.readString();
        this.title = in.readString();
        this.moreLinkurl = in.readString();
        this.embeddedId = in.readString();
        this.mobileStreamUrl = in.readString();
        this.mobileVideoUrl = in.readString();
        this.lastModified = in.readString();
        this.urlName = in.readString();
        this.created = in.readString();
        this.keywordIds = new ArrayList<Integer>();
        in.readList(this.keywordIds, Integer.class.getClassLoader());
        this.publishedStartDate = in.readString();
    }

    public static final Parcelable.Creator<EventDetail> CREATOR = new Parcelable.Creator<EventDetail>() {
        @Override
        public EventDetail createFromParcel(Parcel source) {
            return new EventDetail(source);
        }

        @Override
        public EventDetail[] newArray(int size) {
            return new EventDetail[size];
        }
    };
}