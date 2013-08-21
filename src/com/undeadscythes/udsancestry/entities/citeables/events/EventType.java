package com.undeadscythes.udsancestry.entities.citeables.events;

import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;

/**
 * @author UndeadScythes
 */
public enum EventType {
    BIRTH("BIRT"),
    DEATH("DEAT"),
    MARRIAGE("MARR"),
    RESIDENCE("RESI"),
    BURIED("BURI"),
    BAPTISM("BAPM"),
    CUSTOM("EVEN");

    protected String tag;

    EventType(String tag) {
        this.tag = tag;
    }

    public static EventType getByTag(String tag) throws NoPropertyExistsException {
        for(EventType type : values()) {
            if(tag.equals(type.tag)) {
                return type;
            }
        }
        throw new NoPropertyExistsException(tag);
    }
}
