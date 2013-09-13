package com.undeadscythes.supergenes.gedcom;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.exception.*;

/**
 * @author UndeadScythes
 */
public enum GlobalTag implements Tag {
    /**
     *
     */
    CONC("CONC", "Concatenation");

    private final String tag;
    private final String friendly;
    private final TagType type;

    private GlobalTag(final String tag, final String friendly, final TagType type) {
        this.tag = tag;
        this.friendly = friendly;
        this.type = type;
    }

    private GlobalTag(final String tag, final String friendly) {
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
    public static GlobalTag getByTag(final String tag) {
        for(GlobalTag test : values()) {
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
