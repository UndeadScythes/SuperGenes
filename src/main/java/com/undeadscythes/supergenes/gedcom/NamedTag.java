package com.undeadscythes.supergenes.gedcom;

import com.undeadscythes.gedform.*;

/**
 * @author UndeadScythes
 */
public class NamedTag implements Tag {
    private final String tag;

    /**
     *
     * @param tag
     */
    public NamedTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public String getFriendly() {
        return tag.substring(0, 1).toUpperCase().concat(tag.substring(1).toLowerCase());
    }

    public TagType getType() {
        return TagType.CUSTOM;
    }
}
