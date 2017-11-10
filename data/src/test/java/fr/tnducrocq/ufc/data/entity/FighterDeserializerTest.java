package fr.tnducrocq.ufc.data.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Assert;
import org.junit.Test;

import fr.tnducrocq.ufc.data.entity.fighter.Fighter;
import fr.tnducrocq.ufc.data.entity.fighter.FighterDeserializer;

/**
 * Created by tony on 02/10/2017.
 */

public class FighterDeserializerTest {

    @Test
    public void parseFighterDetails() throws Exception {
        String json = "{\"id\":241895,\"nickname\":null,\"wins\":18,\"statid\":1194,\"losses\":1,\"last_name\":\"Cyborg\",\"weight_class\":\"Women_Featherweight\",\"title_holder\":true,\"draws\":0,\"first_name\":\"Cris\",\"fighter_status\":\"Active\",\"rank\":\"C\",\"pound_for_pound_rank\":\"11\",\"thumbnail\":\"http://imagec.ufc.com/http%253A%252F%252Fmedia.ufc.tv%252Fgenerated_images_sorted%252FFighter%252FCris-Cyborg%252FCris-Cyborg_241895_medium_thumbnail.jpg?mw300-mh300-tc1\",\"belt_thumbnail\":\"http://imagec.ufc.com/http%253A%252F%252Fmedia.ufc.tv%252Ffighter_images%252FCris_Cyborg%252FCYBORG_CRIS_BELT.png?mw300-mh300-tc1\",\"left_full_body_image\":\"http://imagec.ufc.com/http%253A%252F%252Fmedia.ufc.tv%252Ffighter_images%252FCris_Cyborg%252FCYBORG_CRIS_L2.png?mh530\",\"right_full_body_image\":\"http://imagec.ufc.com/http%253A%252F%252Fmedia.ufc.tv%252Ffighter_images%252FCris_Cyborg%252FCYBORG_CRIS_L2.png?mh530\",\"profile_image\":\"http://imagec.ufc.com/http%253A%252F%252Fmedia.ufc.tv%252Ffighter_images%252FCris_Cyborg%252FCYBORG_CRIS2.png?mw300-mh300-tc1\",\"link\":\"http://www.ufc.com/fighter/Cris-Cyborg\"}";

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Fighter.class, new FighterDeserializer());
        builder.excludeFieldsWithoutExposeAnnotation();

        Gson gson = builder.create();
        Fighter fighter = gson.fromJson(json, Fighter.class);
        Assert.assertNotNull(fighter);
        Assert.assertEquals(fighter.getId(), "241895");
        Assert.assertTrue(0 == fighter.getRank());

        System.out.println(fighter);
    }

}
