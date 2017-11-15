package fr.tnducrocq.ufc.data.entity.fighter;

import android.text.TextUtils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

/**
 * Created by tony on 03/11/2017.
 */

public class FighterDeserializer implements JsonDeserializer<Fighter> {

    @Override
    public Fighter deserialize(JsonElement json, Type type, JsonDeserializationContext context) {
        Fighter fighter = context.deserialize(json, _Fighter.class);
        JsonElement elt = json.getAsJsonObject().get("rank");
        if (elt != null && !elt.isJsonNull()) {
            String rank = elt.getAsString();
            if ("C".equals(rank)) {
                fighter.setRank(0);
            } else if (TextUtils.isDigitsOnly(rank)) {
                fighter.setRank(Integer.parseInt(rank));
            } else {
                fighter.setRank(Integer.MAX_VALUE);
            }
        } else {
            fighter.setRank(Integer.MAX_VALUE);
        }
        return fighter;
    }

    private static class _Fighter extends Fighter {
    }

}
