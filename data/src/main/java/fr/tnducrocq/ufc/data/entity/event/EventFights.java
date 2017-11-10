package fr.tnducrocq.ufc.data.entity.event;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;

import fr.tnducrocq.ufc.data.entity.HasId;
import fr.tnducrocq.ufc.data.entity.fighter.DaoSession;

/**
 * Created by tony on 02/11/2017.
 */
@Entity(indexes = {
        @Index(value = "eventId ASC", unique = true)
})
public class EventFights implements HasId, Parcelable {

    @Id(autoincrement = true)
    private Long uid;

    private String eventId;

    @ToMany(joinProperties = {
            @JoinProperty(name = "eventId", referencedName = "eventId")
    })
    private List<EventFight> fightList;

    public EventFights() {
    }

    @Override
    public String getId() {
        return eventId;
    }

    public void setId(String id) {
        this.eventId = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EventFights{\n");
        sb.append("uid=").append(uid);
        sb.append(",\n\t eventId='").append(eventId).append('\'');
        sb.append(",\n\t fightList=").append(fightList);
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.uid);
        dest.writeString(this.eventId);
        dest.writeTypedList(this.fightList);
    }

    public void addEventFights(List<EventFight> eventFights) {
        fightList = new ArrayList<>();
        fightList.addAll(eventFights);
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2018379545)
    public List<EventFight> getFightList() {
        if (fightList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EventFightDao targetDao = daoSession.getEventFightDao();
            List<EventFight> fightListNew = targetDao._queryEventFights_FightList(eventId);
            synchronized (this) {
                if (fightList == null) {
                    fightList = fightListNew;
                }
            }
        }
        return fightList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1376257135)
    public synchronized void resetFightList() {
        fightList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 623848711)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEventFightsDao() : null;
    }

    protected EventFights(Parcel in) {
        this.uid = (Long) in.readValue(Long.class.getClassLoader());
        this.eventId = in.readString();
        this.fightList = in.createTypedArrayList(EventFight.CREATOR);
    }

    @Generated(hash = 61238166)
    public EventFights(Long uid, String eventId) {
        this.uid = uid;
        this.eventId = eventId;
    }

    public static final Creator<EventFights> CREATOR = new Creator<EventFights>() {
        @Override
        public EventFights createFromParcel(Parcel source) {
            return new EventFights(source);
        }

        @Override
        public EventFights[] newArray(int size) {
            return new EventFights[size];
        }
    };

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1112456024)
    private transient EventFightsDao myDao;
}
