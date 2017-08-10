package fr.tnducrocq.ufc.data.source.local.utils.fighter;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;

import static android.provider.BaseColumns._ID;

/**
 * Created by tony on 04/08/2017.
 */
public class FighterContact {

    private static final String TAG = "FighterContact";

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


    public static ContentValues convertToValues(Fighter item) {
        if (item == null) return null;

        ContentValues values = new ContentValues();
        values.put(_ID, item.id());
        values.put(Fighters.FIGHTER_ID, item.id());
        values.put(Fighters.FIGHTER_NICKNAME, item.getNickname());
        values.put(Fighters.FIGHTER_WINS, item.getWins());
        values.put(Fighters.FIGHTER_STATID, item.getStatid());
        values.put(Fighters.FIGHTER_LOSSES, item.getLosses());
        values.put(Fighters.FIGHTER_LAST_NAME, item.getLastName());
        if (item.getWeightClass() != null) {
            values.put(Fighters.FIGHTER_WEIGHT_CLASS, item.getWeightClass().toString());
        }
        values.put(Fighters.FIGHTER_TITLE_HOLDER, item.getTitleHolder());
        values.put(Fighters.FIGHTER_DRAWS, item.getDraws());
        values.put(Fighters.FIGHTER_FIRST_NAME, item.getFirstName());
        values.put(Fighters.FIGHTER_FIGHTER_STATUS, item.getFighterStatus());
        values.put(Fighters.FIGHTER_RANK, item.getRank());
        values.put(Fighters.FIGHTER_POUND_FOR_POUND_RANK, item.getPoundForPoundRank());
        values.put(Fighters.FIGHTER_THUMBNAIL, item.getThumbnail());
        values.put(Fighters.FIGHTER_BELT_THUMBNAIL, item.getBeltThumbnail());
        values.put(Fighters.FIGHTER_LEFT_FULL_BODY_IMAGE, item.getLeftFullBodyImage());
        values.put(Fighters.FIGHTER_RIGHT_FULL_BODY_IMAGE, item.getRightFullBodyImage());
        values.put(Fighters.FIGHTER_PROFILE_IMAGE, item.getProfileImage());
        values.put(Fighters.FIGHTER_LINK, item.getLink());
        return values;
    }


    public static Fighter convertToObject(Cursor cursor) {
        if (cursor == null) return null;
        Fighter fighter = new Fighter();

        final String id = cursor.getString(cursor.getColumnIndex(_ID));
        final String nickname = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_NICKNAME));
        final Integer wins = cursor.getInt(cursor.getColumnIndex(Fighters.FIGHTER_WINS));
        final Integer statid = cursor.getInt(cursor.getColumnIndex(Fighters.FIGHTER_STATID));
        final Integer losses = cursor.getInt(cursor.getColumnIndex(Fighters.FIGHTER_LOSSES));
        final String lastName = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_LAST_NAME));
        final String weightClass = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_WEIGHT_CLASS));
        final boolean title_holder = cursor.getInt(cursor.getColumnIndex(Fighters.FIGHTER_TITLE_HOLDER)) > 0 ? true : false;
        final Integer draws = cursor.getInt(cursor.getColumnIndex(Fighters.FIGHTER_DRAWS));
        final String first_name = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_FIRST_NAME));
        final String fighter_status = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_FIGHTER_STATUS));
        final String rank = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_RANK));
        final String pound_for_pound_rank = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_POUND_FOR_POUND_RANK));
        final String thumbnail = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_THUMBNAIL));
        final String belt_thumbnail = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_BELT_THUMBNAIL));
        final String left_full_body_image = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_LEFT_FULL_BODY_IMAGE));
        final String right_full_body_image = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_RIGHT_FULL_BODY_IMAGE));
        final String profile_image = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_PROFILE_IMAGE));
        final String link = cursor.getString(cursor.getColumnIndex(Fighters.FIGHTER_LINK));

        fighter.setId(id);
        fighter.setNickname(nickname);
        fighter.setWins(wins);
        fighter.setStatid(statid);
        fighter.setLosses(losses);
        fighter.setLastName(lastName);
        try {
            fighter.setWeightClass(WeightCategory.valueOf(weightClass));
        } catch (IllegalArgumentException e) {
            Log.wtf(TAG, "weightClass: " + weightClass + " not found");
        }
        fighter.setTitleHolder(title_holder);
        fighter.setDraws(draws);
        fighter.setFirstName(first_name);
        fighter.setFighterStatus(fighter_status);
        fighter.setRank(rank);
        fighter.setPoundForPoundRank(pound_for_pound_rank);
        fighter.setThumbnail(thumbnail);
        fighter.setBeltThumbnail(belt_thumbnail);
        fighter.setLeftFullBodyImage(left_full_body_image);
        fighter.setRightFullBodyImage(right_full_body_image);
        fighter.setProfileImage(profile_image);
        fighter.setLink(link);
        return fighter;
    }
}
