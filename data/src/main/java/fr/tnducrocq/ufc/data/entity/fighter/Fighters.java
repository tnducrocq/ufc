package fr.tnducrocq.ufc.data.entity.fighter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by tony on 07/08/2017.
 */

public class Fighters implements Parcelable {

    public List<Fighter> values;

    public Fighters(List<Fighter> fighterList) {
        values = fighterList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.values);
    }

    protected Fighters(Parcel in) {
        this.values = in.createTypedArrayList(Fighter.CREATOR);
    }

    public static final Parcelable.Creator<Fighters> CREATOR = new Parcelable.Creator<Fighters>() {
        @Override
        public Fighters createFromParcel(Parcel source) {
            return new Fighters(source);
        }

        @Override
        public Fighters[] newArray(int size) {
            return new Fighters[size];
        }
    };
}
