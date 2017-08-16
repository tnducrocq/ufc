package fr.tnducrocq.ufc.data.source.remote;

import java.util.List;

import fr.tnducrocq.ufc.data.utils.SwiftString;
import rx.Observable;

/**
 * Created by tony on 04/08/2017.
 */

public interface IRepository<T> {

    static final String API_URL = "http://ufc-data-api.ufc.com/api/v3";
    static final SwiftString API_EVENTS = SwiftString.format(API_URL + "/events");
    static final SwiftString API_FIGHTERS = SwiftString.format(API_URL + "/fighters");
    static final SwiftString API_EVENTS_ID = SwiftString.format(API_URL + "/events/${id}");
    static final SwiftString API_EVENT_FIGHTS = SwiftString.format(API_URL + "/events/${id}/fights");
    static final SwiftString API_EVENT_MEDIAS = SwiftString.format(API_URL + "/events/${id}/media");

    Observable<List<T>> get();
}
