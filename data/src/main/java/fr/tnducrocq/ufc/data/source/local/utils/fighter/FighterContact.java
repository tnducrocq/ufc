package fr.tnducrocq.ufc.data.source.local.utils.fighter;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by tony on 04/08/2017.
 */
public class FighterContact {

    interface Tables {
        String FIGHTERS = "fighters";
    }

    interface FighterColumns extends BaseColumns {
        String FIGHTER_ID = "fighter_id";
        String FIGHTER_NICKNAME = "nickname";
        String FIGHTER_WINS = "wins";
        String FIGHTER_STATID = "statid";
        String FIGHTER_LOSSES = "losses";
        String FIGHTER_LAST_NAME = "last_name";
        String FIGHTER_WEIGHT_CLASS = "weight_class";
        String FIGHTER_TITLE_HOLDER = "title_holder";
        String FIGHTER_DRAWS = "draws";
        String FIGHTER_FIRST_NAME = "first_name";
        String FIGHTER_FIGHTER_STATUS = "fighter_status";
        String FIGHTER_RANK = "rank";
        String FIGHTER_POUND_FOR_POUND_RANK = "pound_for_pound_rank";
        String FIGHTER_THUMBNAIL = "thumbnail";
        String FIGHTER_BELT_THUMBNAIL = "belt_thumbnail";
        String FIGHTER_LEFT_FULL_BODY_IMAGE = "left_full_body_image";
        String FIGHTER_RIGHT_FULL_BODY_IMAGE = "right_full_body_image";
        String FIGHTER_PROFILE_IMAGE = "profile_image";
        String FIGHTER_LINK = "link";
    }

    public static final String CONTENT_AUTHORITY = "fr.tnducrocq.ufc";
    public static final String PREFIX = "content://";

    public static final Uri BASE_CONTENT_URI = Uri.parse(PREFIX + CONTENT_AUTHORITY);
    public static final String PATH_FIGHTER = "fighter";

    public static class Fighters implements FighterColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FIGHTER).build();

        public static final String CONTENT_DIR_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FIGHTER;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FIGHTER;

        /**
         * Build {@link Uri} for requested {@link #FIGHTER_ID}.
         */
        public static Uri buildFighterUri(String fighterId) {
            return CONTENT_URI.buildUpon().appendPath(fighterId).build();
        }

        /**
         * Read {@link #FIGHTER_ID} from {@link Sessions} {@link Uri}.
         */
        public static String getFighterId(Uri uri) {
            return Long.toString(ContentUris.parseId(uri));
        }

    }
}
