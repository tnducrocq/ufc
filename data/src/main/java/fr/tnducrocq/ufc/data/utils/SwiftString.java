package fr.tnducrocq.ufc.data.utils;

import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 25/07/2017.
 */

public class SwiftString {

    private Map<String, String> map;
    private String message;

    private SwiftString() {
    }

    public static SwiftString format(String message) {
        SwiftString format = new SwiftString();
        format.message = message;
        return format;
    }

    public SwiftString put(String key, String value) {
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(key, value);
        return this;
    }

    public String toString() {
        if (map == null) {
            return message;
        }
        return StrSubstitutor.replace(message, map);
    }
}
