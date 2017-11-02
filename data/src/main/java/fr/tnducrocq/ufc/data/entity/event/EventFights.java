package fr.tnducrocq.ufc.data.entity.event;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.HasId;

/**
 * Created by tony on 02/11/2017.
 */

public class EventFights implements HasId {
    private String id;
    private List<EventFight> fightList;

    @Override
    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EventFight> getFightList() {
        return fightList;
    }

    public void setFightList(List<EventFight> fightList) {
        this.fightList = fightList;
    }
}
