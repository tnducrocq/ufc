package fr.tnducrocq.ufc.presentation.ui.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;

/**
 * Created by tony on 13/10/2017.
 */

public class PaletteColors implements Parcelable {

    public int titleTextColor;
    public int bodyTextColor;
    public int rgb;

    public static PaletteColors fromSwatch(@NonNull Palette.Swatch swatch) {
        PaletteColors palette = new PaletteColors();
        palette.rgb = swatch.getRgb();
        palette.bodyTextColor = swatch.getBodyTextColor();
        palette.titleTextColor = swatch.getTitleTextColor();
        return palette;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.titleTextColor);
        dest.writeInt(this.bodyTextColor);
        dest.writeInt(this.rgb);
    }

    public PaletteColors() {
    }

    protected PaletteColors(Parcel in) {
        this.titleTextColor = in.readInt();
        this.bodyTextColor = in.readInt();
        this.rgb = in.readInt();
    }

    public static final Parcelable.Creator<PaletteColors> CREATOR = new Parcelable.Creator<PaletteColors>() {
        @Override
        public PaletteColors createFromParcel(Parcel source) {
            return new PaletteColors(source);
        }

        @Override
        public PaletteColors[] newArray(int size) {
            return new PaletteColors[size];
        }
    };
}
