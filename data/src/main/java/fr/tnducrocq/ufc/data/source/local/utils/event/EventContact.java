package fr.tnducrocq.ufc.data.source.local.utils.event;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.Date;

import fr.tnducrocq.ufc.data.entity.event.Event;

import static android.provider.BaseColumns._ID;

/**
 * Created by tony on 10/08/2017.
 */

public class EventContact {

    private static final String TAG = "FighterContact";

    interface Tables {
        String EVENTS = "events";
    }

    interface EventColumns extends BaseColumns {
        String EVENT_ID = "event_id";
        String EVENT_DATE = "event_date";
        String EVENT_SECONDARY_FEATURE_IMAGE = "secondary_feature_image";
        String EVENT_TICKET_IMAGE = "ticket_image";
        String EVENT_TIME_ZONE_TEXT = "event_time_zone_text";
        String EVENT_SHORT_DESCRIPTION = "short_description";
        String EVENT_DATEGMT = "event_dategmt";
        String EVENT_END_EVENT_DATEGMT = "end_event_dategmt";
        String EVENT_TICKETURL = "ticketurl";
        String EVENT_BASE_TITLE = "base_title";
        String EVENT_TITLE_TAG_LINE = "title_tag_line";
        String EVENT_TWITTER_HASHTAG = "twitter_hashtag";
        String EVENT_FEATURE_IMAGE = "feature_image";
        String EVENT_TIME_TEXT = "event_time_text";
        String EVENT_TICKET_GENERAL_SALE_TEXT = "ticket_general_sale_text";
        String EVENT_SUBTITLE = "subtitle";
        String EVENT_STATUS = "event_status";
        String EVENT_ISPPVEVENT = "isppvevent";
        String EVENT_CORNER_AUDIO_AVAILABLE = "corner_audio_available";
        String EVENT_LAST_MODIFIED = "last_modified";
        String EVENT_URL_NAME = "url_name";
        String EVENT_CREATED = "created";
        String EVENT_ARENA = "arena";
        String EVENT_LOCATION = "location";
        String EVENT_FM_FNT_FEED_URL = "fm_fnt_feed_url";
        String EVENT_MAIN_EVENT_FIGHTER1_ID = "main_event_fighter1_id";
        String EVENT_MAIN_EVENT_FIGHTER2_ID = "main_event_fighter2_id";
    }

    public static final String CONTENT_AUTHORITY = "fr.tnducrocq.ufc";
    public static final String PREFIX = "content://";

    public static final Uri BASE_CONTENT_URI = Uri.parse(PREFIX + CONTENT_AUTHORITY);
    public static final String PATH_EVENT = "event";

    public static class Events implements EventContact.EventColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_EVENT).build();

        /**
         * Build {@link Uri} for requested {@link #EVENT_ID}.
         */
        public static Uri buildFighterUri(String eventId) {
            return CONTENT_URI.buildUpon().appendPath(eventId).build();
        }

        /**
         * Read {@link #EVENT_ID} from {@link Sessions} {@link Uri}.
         */
        public static String getEventId(Uri uri) {
            return Long.toString(ContentUris.parseId(uri));
        }

    }


    public static ContentValues convertToValues(Event item) {
        if (item == null) return null;

        ContentValues values = new ContentValues();
        values.put(_ID, item.id());
        values.put(Events.EVENT_ID, item.id());
        values.put(Events.EVENT_DATE, item.getEventDate() != null ? item.getEventDate().getTime() : -1);
        values.put(Events.EVENT_SECONDARY_FEATURE_IMAGE, item.getSecondaryFeatureImage());
        values.put(Events.EVENT_TICKET_IMAGE, item.getTicketImage());
        values.put(Events.EVENT_TIME_ZONE_TEXT, item.getEventTimeZoneText());
        values.put(Events.EVENT_SHORT_DESCRIPTION, item.getShortDescription());
        values.put(Events.EVENT_END_EVENT_DATEGMT, item.getEventDategmt());
        values.put(Events.EVENT_END_EVENT_DATEGMT, item.getEndEventDategmt());
        values.put(Events.EVENT_TICKETURL, item.getTicketurl());
        values.put(Events.EVENT_BASE_TITLE, item.getBaseTitle());
        values.put(Events.EVENT_TITLE_TAG_LINE, item.getTitleTagLine());
        values.put(Events.EVENT_TWITTER_HASHTAG, item.getTwitterHashtag());
        values.put(Events.EVENT_FEATURE_IMAGE, item.getFeatureImage());
        values.put(Events.EVENT_TIME_TEXT, item.getEventTimeText());
        values.put(Events.EVENT_TICKET_GENERAL_SALE_TEXT, item.getTicketGeneralSaleText());
        values.put(Events.EVENT_SUBTITLE, item.getSubtitle());
        values.put(Events.EVENT_STATUS, item.getEventStatus());
        values.put(Events.EVENT_ISPPVEVENT, item.getIsppvevent());
        values.put(Events.EVENT_CORNER_AUDIO_AVAILABLE, item.getCornerAudioAvailable());
        values.put(Events.EVENT_LAST_MODIFIED, item.getLastModified());
        values.put(Events.EVENT_URL_NAME, item.getUrlName());
        values.put(Events.EVENT_CREATED, item.getCreated());
        values.put(Events.EVENT_ARENA, item.getArena());
        values.put(Events.EVENT_LOCATION, item.getLocation());
        values.put(Events.EVENT_FM_FNT_FEED_URL, item.getFmFntFeedUrl());
        values.put(Events.EVENT_MAIN_EVENT_FIGHTER1_ID, item.getMainEventFighter1Id());
        values.put(Events.EVENT_MAIN_EVENT_FIGHTER2_ID, item.getMainEventFighter2Id());

        return values;
    }

    public static Event convertToObject(Cursor cursor) {
        if (cursor == null) return null;
        Event event = new Event();

        final String id = cursor.getString(cursor.getColumnIndex(Events.EVENT_ID));

        long eventTime = cursor.getLong(cursor.getColumnIndex(Events.EVENT_DATE));
        final Date eventDate = eventTime != -1 ? new Date(eventTime) : null;

        final String secondaryFeatureImage = cursor.getString(cursor.getColumnIndex(Events.EVENT_SECONDARY_FEATURE_IMAGE));
        final String ticketImage = cursor.getString(cursor.getColumnIndex(Events.EVENT_TICKET_IMAGE));
        final String eventTimeZoneText = cursor.getString(cursor.getColumnIndex(Events.EVENT_TIME_ZONE_TEXT));
        final String shortDescription = cursor.getString(cursor.getColumnIndex(Events.EVENT_SHORT_DESCRIPTION));
        final String eventDategmt = cursor.getString(cursor.getColumnIndex(Events.EVENT_DATEGMT));
        final String endEventDategmt = cursor.getString(cursor.getColumnIndex(Events.EVENT_END_EVENT_DATEGMT));
        final String ticketurl = cursor.getString(cursor.getColumnIndex(Events.EVENT_TICKETURL));
        final String baseTitle = cursor.getString(cursor.getColumnIndex(Events.EVENT_BASE_TITLE));
        final String titleTagLine = cursor.getString(cursor.getColumnIndex(Events.EVENT_TITLE_TAG_LINE));
        final String twitterHashtag = cursor.getString(cursor.getColumnIndex(Events.EVENT_TWITTER_HASHTAG));
        final String featureImage = cursor.getString(cursor.getColumnIndex(Events.EVENT_FEATURE_IMAGE));
        final String eventTimeText = cursor.getString(cursor.getColumnIndex(Events.EVENT_TIME_TEXT));
        final String ticketGeneralSaleText = cursor.getString(cursor.getColumnIndex(Events.EVENT_TICKET_GENERAL_SALE_TEXT));
        final String subtitle = cursor.getString(cursor.getColumnIndex(Events.EVENT_SUBTITLE));
        final String eventStatus = cursor.getString(cursor.getColumnIndex(Events.EVENT_STATUS));
        final Boolean isppvevent = cursor.getInt(cursor.getColumnIndex(Events.EVENT_ISPPVEVENT)) > 0 ? true : false;
        final Boolean cornerAudioAvailable = cursor.getInt(cursor.getColumnIndex(Events.EVENT_CORNER_AUDIO_AVAILABLE)) > 0 ? true : false;
        final String lastModified = cursor.getString(cursor.getColumnIndex(Events.EVENT_LAST_MODIFIED));
        final String urlName = cursor.getString(cursor.getColumnIndex(Events.EVENT_URL_NAME));
        final String created = cursor.getString(cursor.getColumnIndex(Events.EVENT_CREATED));
        final String arena = cursor.getString(cursor.getColumnIndex(Events.EVENT_ARENA));
        final String location = cursor.getString(cursor.getColumnIndex(Events.EVENT_LOCATION));
        final String fmFntFeedUrl = cursor.getString(cursor.getColumnIndex(Events.EVENT_FM_FNT_FEED_URL));
        final Integer mainEventFighter1Id = cursor.getInt(cursor.getColumnIndex(Events.EVENT_MAIN_EVENT_FIGHTER1_ID));
        final Integer mainEventFighter2Id = cursor.getInt(cursor.getColumnIndex(Events.EVENT_MAIN_EVENT_FIGHTER2_ID));

        event.setId(id);
        event.setEventDate(eventDate);
        event.setSecondaryFeatureImage(secondaryFeatureImage);
        event.setTicketImage(ticketImage);
        event.setEventTimeZoneText(eventTimeZoneText);
        event.setShortDescription(shortDescription);
        event.setEventDategmt(eventDategmt);
        event.setEndEventDategmt(endEventDategmt);
        event.setTicketurl(ticketurl);
        event.setBaseTitle(baseTitle);
        event.setTitleTagLine(titleTagLine);
        event.setTwitterHashtag(twitterHashtag);
        event.setFeatureImage(featureImage);
        event.setEventTimeText(eventTimeText);
        event.setTicketGeneralSaleText(ticketGeneralSaleText);
        event.setSubtitle(subtitle);
        event.setEventStatus(eventStatus);
        event.setIsppvevent(isppvevent);
        event.setCornerAudioAvailable(cornerAudioAvailable);
        event.setLastModified(lastModified);
        event.setUrlName(urlName);
        event.setCreated(created);
        event.setArena(arena);
        event.setLocation(location);
        event.setFmFntFeedUrl(fmFntFeedUrl);
        event.setMainEventFighter1Id(mainEventFighter1Id);
        event.setMainEventFighter2Id(mainEventFighter2Id);
        return event;
    }
}
