package fr.tnducrocq.ufc.data.entity.event;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

import java.util.Date;

import fr.tnducrocq.ufc.data.entity.HasId;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tony on 25/07/2017.
 */
@Entity(indexes = {
        @Index(value = "id ASC", unique = true)
})
public class Event implements Comparable<Event>, HasId, Parcelable {

    @Id(autoincrement = true)
    private Long uid;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("event_date")
    @Expose
    private Date eventDate;

    @SerializedName("secondary_feature_image")
    @Expose
    private String secondaryFeatureImage;

    @SerializedName("ticket_image")
    @Expose
    private String ticketImage;

    @SerializedName("event_time_zone_text")
    @Expose
    private String eventTimeZoneText;

    @SerializedName("short_description")
    @Expose
    private String shortDescription;

    @SerializedName("event_dategmt")
    @Expose
    private String eventDategmt;

    @SerializedName("end_event_dategmt")
    @Expose
    private String endEventDategmt;

    @SerializedName("ticketurl")
    @Expose
    private String ticketurl;

    @SerializedName("base_title")
    @Expose
    private String baseTitle;

    @SerializedName("title_tag_line")
    @Expose
    private String titleTagLine;

    @SerializedName("twitter_hashtag")
    @Expose
    private String twitterHashtag;

    @SerializedName("feature_image")
    @Expose
    private String featureImage;

    @SerializedName("event_time_text")
    @Expose
    private String eventTimeText;

    @SerializedName("ticket_general_sale_text")
    @Expose
    private String ticketGeneralSaleText;

    @SerializedName("subtitle")
    @Expose
    private String subtitle;

    @SerializedName("event_status")
    @Expose
    private String eventStatus;

    @SerializedName("isppvevent")
    @Expose
    private Boolean isppvevent;

    @SerializedName("corner_audio_available")
    @Expose
    private Boolean cornerAudioAvailable;

    @SerializedName("last_modified")
    @Expose
    private String lastModified;

    @SerializedName("url_name")
    @Expose
    private String urlName;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("arena")
    @Expose
    private String arena;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("fm_fnt_feed_url")
    @Expose
    private String fmFntFeedUrl;

    @SerializedName("main_event_fighter1_id")
    @Expose
    private Integer mainEventFighter1Id;

    @SerializedName("main_event_fighter2_id")
    @Expose
    private Integer mainEventFighter2Id;

    public Event() {
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

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getSecondaryFeatureImage() {
        return secondaryFeatureImage;
    }

    public void setSecondaryFeatureImage(String secondaryFeatureImage) {
        this.secondaryFeatureImage = secondaryFeatureImage;
    }

    public String getTicketImage() {
        return ticketImage;
    }

    public void setTicketImage(String ticketImage) {
        this.ticketImage = ticketImage;
    }

    public String getEventTimeZoneText() {
        return eventTimeZoneText;
    }

    public void setEventTimeZoneText(String eventTimeZoneText) {
        this.eventTimeZoneText = eventTimeZoneText;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getEventDategmt() {
        return eventDategmt;
    }

    public void setEventDategmt(String eventDategmt) {
        this.eventDategmt = eventDategmt;
    }

    public String getEndEventDategmt() {
        return endEventDategmt;
    }

    public void setEndEventDategmt(String endEventDategmt) {
        this.endEventDategmt = endEventDategmt;
    }

    public String getTicketurl() {
        return ticketurl;
    }

    public void setTicketurl(String ticketurl) {
        this.ticketurl = ticketurl;
    }

    public String getBaseTitle() {
        return baseTitle;
    }

    public void setBaseTitle(String baseTitle) {
        this.baseTitle = baseTitle;
    }

    public String getTitleTagLine() {
        return titleTagLine;
    }

    public void setTitleTagLine(String titleTagLine) {
        this.titleTagLine = titleTagLine;
    }

    public String getTwitterHashtag() {
        return twitterHashtag;
    }

    public void setTwitterHashtag(String twitterHashtag) {
        this.twitterHashtag = twitterHashtag;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getEventTimeText() {
        return eventTimeText;
    }

    public void setEventTimeText(String eventTimeText) {
        this.eventTimeText = eventTimeText;
    }

    public String getTicketGeneralSaleText() {
        return ticketGeneralSaleText;
    }

    public void setTicketGeneralSaleText(String ticketGeneralSaleText) {
        this.ticketGeneralSaleText = ticketGeneralSaleText;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Boolean getIsppvevent() {
        return isppvevent;
    }

    public void setIsppvevent(Boolean isppvevent) {
        this.isppvevent = isppvevent;
    }

    public Boolean getCornerAudioAvailable() {
        return cornerAudioAvailable;
    }

    public void setCornerAudioAvailable(Boolean cornerAudioAvailable) {
        this.cornerAudioAvailable = cornerAudioAvailable;
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

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFmFntFeedUrl() {
        return fmFntFeedUrl;
    }

    public void setFmFntFeedUrl(String fmFntFeedUrl) {
        this.fmFntFeedUrl = fmFntFeedUrl;
    }

    public Integer getMainEventFighter1Id() {
        return mainEventFighter1Id;
    }

    public void setMainEventFighter1Id(Integer mainEventFighter1Id) {
        this.mainEventFighter1Id = mainEventFighter1Id;
    }

    public Integer getMainEventFighter2Id() {
        return mainEventFighter2Id;
    }

    public void setMainEventFighter2Id(Integer mainEventFighter2Id) {
        this.mainEventFighter2Id = mainEventFighter2Id;
    }

    @Override
    public int compareTo(@NonNull Event event) {
        if (eventDate == null && event.eventDate == null) {
            return 0;
        }
        if (eventDate == null && event.eventDate != null) {
            return -1;
        }
        return eventDate.compareTo(event.eventDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.uid);
        dest.writeString(this.id);
        dest.writeLong(this.eventDate != null ? this.eventDate.getTime() : -1);
        dest.writeString(this.secondaryFeatureImage);
        dest.writeString(this.ticketImage);
        dest.writeString(this.eventTimeZoneText);
        dest.writeString(this.shortDescription);
        dest.writeString(this.eventDategmt);
        dest.writeString(this.endEventDategmt);
        dest.writeString(this.ticketurl);
        dest.writeString(this.baseTitle);
        dest.writeString(this.titleTagLine);
        dest.writeString(this.twitterHashtag);
        dest.writeString(this.featureImage);
        dest.writeString(this.eventTimeText);
        dest.writeString(this.ticketGeneralSaleText);
        dest.writeString(this.subtitle);
        dest.writeString(this.eventStatus);
        dest.writeValue(this.isppvevent);
        dest.writeValue(this.cornerAudioAvailable);
        dest.writeString(this.lastModified);
        dest.writeString(this.urlName);
        dest.writeString(this.created);
        dest.writeString(this.arena);
        dest.writeString(this.location);
        dest.writeString(this.fmFntFeedUrl);
        dest.writeValue(this.mainEventFighter1Id);
        dest.writeValue(this.mainEventFighter2Id);
    }

    protected Event(Parcel in) {
        this.uid = (Long) in.readValue(Long.class.getClassLoader());
        this.id = in.readString();
        long tmpEventDate = in.readLong();
        this.eventDate = tmpEventDate == -1 ? null : new Date(tmpEventDate);
        this.secondaryFeatureImage = in.readString();
        this.ticketImage = in.readString();
        this.eventTimeZoneText = in.readString();
        this.shortDescription = in.readString();
        this.eventDategmt = in.readString();
        this.endEventDategmt = in.readString();
        this.ticketurl = in.readString();
        this.baseTitle = in.readString();
        this.titleTagLine = in.readString();
        this.twitterHashtag = in.readString();
        this.featureImage = in.readString();
        this.eventTimeText = in.readString();
        this.ticketGeneralSaleText = in.readString();
        this.subtitle = in.readString();
        this.eventStatus = in.readString();
        this.isppvevent = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.cornerAudioAvailable = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.lastModified = in.readString();
        this.urlName = in.readString();
        this.created = in.readString();
        this.arena = in.readString();
        this.location = in.readString();
        this.fmFntFeedUrl = in.readString();
        this.mainEventFighter1Id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mainEventFighter2Id = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    @Generated(hash = 1763558353)
    public Event(Long uid, String id, Date eventDate, String secondaryFeatureImage,
            String ticketImage, String eventTimeZoneText, String shortDescription,
            String eventDategmt, String endEventDategmt, String ticketurl, String baseTitle,
            String titleTagLine, String twitterHashtag, String featureImage,
            String eventTimeText, String ticketGeneralSaleText, String subtitle,
            String eventStatus, Boolean isppvevent, Boolean cornerAudioAvailable,
            String lastModified, String urlName, String created, String arena,
            String location, String fmFntFeedUrl, Integer mainEventFighter1Id,
            Integer mainEventFighter2Id) {
        this.uid = uid;
        this.id = id;
        this.eventDate = eventDate;
        this.secondaryFeatureImage = secondaryFeatureImage;
        this.ticketImage = ticketImage;
        this.eventTimeZoneText = eventTimeZoneText;
        this.shortDescription = shortDescription;
        this.eventDategmt = eventDategmt;
        this.endEventDategmt = endEventDategmt;
        this.ticketurl = ticketurl;
        this.baseTitle = baseTitle;
        this.titleTagLine = titleTagLine;
        this.twitterHashtag = twitterHashtag;
        this.featureImage = featureImage;
        this.eventTimeText = eventTimeText;
        this.ticketGeneralSaleText = ticketGeneralSaleText;
        this.subtitle = subtitle;
        this.eventStatus = eventStatus;
        this.isppvevent = isppvevent;
        this.cornerAudioAvailable = cornerAudioAvailable;
        this.lastModified = lastModified;
        this.urlName = urlName;
        this.created = created;
        this.arena = arena;
        this.location = location;
        this.fmFntFeedUrl = fmFntFeedUrl;
        this.mainEventFighter1Id = mainEventFighter1Id;
        this.mainEventFighter2Id = mainEventFighter2Id;
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}