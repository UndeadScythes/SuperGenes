package com.undeadscythes.supergenes.event;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.exception.*;


/**
 * @author UndeadScythes
 */
public enum EventTag implements Tag {
    /**
     *
     */
    DATE("DATE", "Date"),
    /**
     *
     */
    PLACE("PLAC", "Place"),
    /**
     *
     */
    TYPE("TYPE", "Type");

    private final String tag;
    private final String friendly;
    private final TagType type;

    private EventTag(final String tag, final String friendly, final TagType type) {
        this.tag = tag;
        this.friendly = friendly;
        this.type = type;
    }

    private EventTag(final String tag, final String friendly) {
        this(tag, friendly, TagType.FACT);
    }

    public String getTag() {
        return tag;
    }

    /**
     *
     * @param tag
     * @return
     */
    public static EventTag getByTag(final String tag) {
        for(EventTag test : values()) {
            if(tag.equals(test.getTag())) {
                return test;
            }
        }
        throw new NoValidTagException(tag);
    }

    public String getFriendly() {
        return friendly;
    }

    public TagType getType() {
        return type;
    }
}
