package fr.tnducrocq.ufc.data.entity.event;

import java.util.List;

import fr.tnducrocq.ufc.data.entity.HasId;

/**
 * Created by tony on 02/11/2017.
 */

public class EventMedias implements HasId {
    private String id;
    private List<EventMedia> mediaList;

    @Override
    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<EventMedia> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<EventMedia> mediaList) {
        this.mediaList = mediaList;
    }
}
