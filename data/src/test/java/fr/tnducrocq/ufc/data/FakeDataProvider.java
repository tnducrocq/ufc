package fr.tnducrocq.ufc.data;


import java.util.Arrays;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.utils.WeightCategory;

@SuppressWarnings("WeakerAccess")
public class FakeDataProvider {

    public static final String FAKE_FIGHTER_ID = "fighter_id";
    public static final String FAKE_FIGHTER_NICKNAME = "nickname";
    public static final int FAKE_FIGHTER_WINS = 46;
    public static final int FAKE_FIGHTER_STATID = 1;
    public static final int FAKE_FIGHTER_LOSSES = 4;
    public static final String FAKE_FIGHTER_LAST_NAME = "last_name";
    public static final boolean FAKE_FIGHTER_TITLE_HOLDER = false;
    public static final int FAKE_FIGHTER_DRAWS = 0;
    public static final String FAKE_FIGHTER_FIRST_NAME = "first_name";
    public static final String FAKE_FIGHTER_FAKE_FIGHTER_STATUS = "fighter_status";
    public static final String FAKE_FIGHTER_RANK = "rank";
    public static final String FAKE_FIGHTER_POUND_FOR_POUND_RANK = "pound_for_pound_rank";
    public static final String FAKE_FIGHTER_THUMBNAIL = "thumbnail";
    public static final String FAKE_FIGHTER_BELT_THUMBNAIL = "belt_thumbnail";
    public static final String FAKE_FIGHTER_LEFT_FULL_BODY_IMAGE = "left_full_body_image";
    public static final String FAKE_FIGHTER_RIGHT_FULL_BODY_IMAGE = "right_full_body_image";
    public static final String FAKE_FIGHTER_PROFILE_IMAGE = "profile_image";
    public static final String FAKE_FIGHTER_LINK = "link";

    public static Fighter provideFighter() {
        Fighter fighter = new Fighter();

        fighter.setId(FAKE_FIGHTER_ID);
        fighter.setNickname(FAKE_FIGHTER_NICKNAME);
        fighter.setWins(FAKE_FIGHTER_WINS);
        fighter.setStatid(FAKE_FIGHTER_STATID);
        fighter.setLosses(FAKE_FIGHTER_LOSSES);
        fighter.setLastName(FAKE_FIGHTER_LAST_NAME);
        fighter.setWeightClass(WeightCategory.HEAVYWEIGHT);
        fighter.setTitleHolder(FAKE_FIGHTER_TITLE_HOLDER);
        fighter.setDraws(FAKE_FIGHTER_DRAWS);
        fighter.setFirstName(FAKE_FIGHTER_FIRST_NAME);
        fighter.setFighterStatus(FAKE_FIGHTER_FAKE_FIGHTER_STATUS);
        fighter.setRank(FAKE_FIGHTER_RANK);
        fighter.setPoundForPoundRank(FAKE_FIGHTER_POUND_FOR_POUND_RANK);
        fighter.setThumbnail(FAKE_FIGHTER_THUMBNAIL);
        fighter.setBeltThumbnail(FAKE_FIGHTER_BELT_THUMBNAIL);
        fighter.setLeftFullBodyImage(FAKE_FIGHTER_LEFT_FULL_BODY_IMAGE);
        fighter.setRightFullBodyImage(FAKE_FIGHTER_RIGHT_FULL_BODY_IMAGE);
        fighter.setProfileImage(FAKE_FIGHTER_PROFILE_IMAGE);
        fighter.setLink(FAKE_FIGHTER_LINK);
        return fighter;
    }

    public static List<Fighter> provideFighterList() {
        return Arrays.asList(provideFighter(), provideFighter(), provideFighter(), provideFighter(), provideFighter());
    }
}
