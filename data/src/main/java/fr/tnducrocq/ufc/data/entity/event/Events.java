package fr.tnducrocq.ufc.data.entity.event;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by tony on 07/08/2017.
 */

public class Events implements Parcelable {

    public List<Event> pastEvents;
    public List<Event> scheduleEvents;

    public Events(List<Event> eventList) {
        Collections.sort(eventList);
        Date now = GregorianCalendar.getInstance().getTime();
        int i = 0;
        for (; i < eventList.size(); i++) {
            if (eventList.get(i).getEventDate().compareTo(now) > 0) {
                break;
            }
        }
        pastEvents = eventList.subList(0, i);
        Collections.reverse(pastEvents);
        scheduleEvents = eventList.subList(i, eventList.size());
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Events{");
        sb.append("pastEvents=").append(pastEvents);
        sb.append(", scheduleEvents=").append(scheduleEvents);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.pastEvents);
        dest.writeTypedList(this.scheduleEvents);
    }

    protected Events(Parcel in) {
        this.pastEvents = in.createTypedArrayList(Event.CREATOR);
        this.scheduleEvents = in.createTypedArrayList(Event.CREATOR);
    }

    public static final Parcelable.Creator<Events> CREATOR = new Parcelable.Creator<Events>() {
        @Override
        public Events createFromParcel(Parcel source) {
            return new Events(source);
        }

        @Override
        public Events[] newArray(int size) {
            return new Events[size];
        }
    };
}
