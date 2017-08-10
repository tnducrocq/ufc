package fr.tnducrocq.ufc.data.utils;


import com.google.gson.annotations.SerializedName;

import fr.tnducrocq.ufc.data.R;

public enum WeightCategory {

    // MEN
    @SerializedName("Flyweight")
    FLYWEIGHT("Flyweight", R.string.fighter_category_flyweight), //

    @SerializedName("Bantamweight")
    BANTAMWEIGHT("Bantamweight", R.string.fighter_category_bantamweight), //

    @SerializedName("Featherweight")
    FEATHERWEIGHT("Featherweight", R.string.fighter_category_featherweight), //

    @SerializedName("Lightweight")
    LIGHTWEIGHT("Lightweight", R.string.fighter_category_lightweight), //

    @SerializedName("Welterweight")
    WELTERWEIGHT("Welterweight", R.string.fighter_category_welterweight), //

    @SerializedName("Middleweight")
    MIDDLEWEIGHT("Middleweight", R.string.fighter_category_middleweight), //

    @SerializedName("Light_Heavyweight")
    LIGHT_HEAVYWEIGHT("Light_Heavyweight", R.string.fighter_category_light_heavyweight), //

    @SerializedName("Heavyweight")
    HEAVYWEIGHT("Heavyweight", R.string.fighter_category_heavyweight), //

    // WOMEN
    @SerializedName("Women_Strawweight")
    WOMEN_STRAWWEIGHT("Women_Strawweight", R.string.fighter_category_women_strawweight), //

    @SerializedName("Women_Bantamweight")
    WOMEN_BANTAMWEIGHT("Women_Bantamweight", R.string.fighter_category_women_bantamweight), //

    @SerializedName("Women_Featherweight")
    WOMEN_FEATHERWEIGHT("Women_Featherweight", R.string.fighter_category_women_featherweight);

    private String mName;
    private int mResourceId;

    private WeightCategory(String name, int resourceId) {
        this.mName = name;
        this.mResourceId = resourceId;
    }

    public String getName() {
        return mName;
    }

    public int getResourceId() {
        return mResourceId;
    }
}
