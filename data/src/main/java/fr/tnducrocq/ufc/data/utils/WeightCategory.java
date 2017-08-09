package fr.tnducrocq.ufc.data.utils;


import com.google.gson.annotations.SerializedName;

public enum WeightCategory {

    // MEN
    @SerializedName("Flyweight")
    FLYWEIGHT("Flyweight"), //

    @SerializedName("Bantamweight")
    BANTAMWEIGHT("Bantamweight"), //

    @SerializedName("Featherweight")
    FEATHERWEIGHT("Featherweight"), //

    @SerializedName("Lightweight")
    LIGHTWEIGHT("Lightweight"), //

    @SerializedName("Welterweight")
    WELTERWEIGHT("Welterweight"), //

    @SerializedName("Middleweight")
    MIDDLEWEIGHT("Middleweight"), //

    @SerializedName("Light_Heavyweight")
    LIGHT_HEAVYWEIGHT("Light_Heavyweight"), //

    @SerializedName("Heavyweight")
    HEAVYWEIGHT("Heavyweight"), //

    // WOMEN
    @SerializedName("Women_Strawweight")
    WOMEN_STRAWWEIGHT("Women_Strawweight"), //

    @SerializedName("Women_Bantamweight")
    WOMEN_BANTAMWEIGHT("Women_Bantamweight"), //

    @SerializedName("Women_Featherweight")
    WOMEN_FEATHERWEIGHT("Women_Featherweight");

    private String name;

    private WeightCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
