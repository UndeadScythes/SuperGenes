package com.undeadscythes.udsancestry.entities.citeables.events;

import com.undeadscythes.udsancestry.entities.EntityProperty;
import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;

/**
 * @author UndeadScythes
 */
public enum EventProperty implements EntityProperty {
    DATE("DATE"),
    PLACE("PLAC"),
    SOUR("SOUR"),
    TYPE("TYPE");

    protected String tag;

    EventProperty(String tag) {
        this.tag = tag;
    }

    public static EventProperty getByTag(String tag) throws NoPropertyExistsException {
        for(EventProperty type : values()) {
            if(tag.equals(type.tag)) {
                return type;
            }
        }
        throw new NoPropertyExistsException(tag);
    }

    public String getTag() {
        return tag;
    }
}
