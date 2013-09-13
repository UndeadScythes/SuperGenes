package com.undeadscythes.supergenes.gedcom;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.exception.*;

/**
 * @author UndeadScythes
 */
public enum GEDCOMTag implements Tag {
    /**
     *
     */
    INDIVIDUAL("INDI", "Individual"),
    /**
     *
     */
    HEADER("HEAD", "Header"),
    /**
     *
     */
    FAMILY("FAM", "Family"),
    /**
     *
     */
    TRAILER("TRLR", "Trailer"),
    /**
     *
     */
    REPOSITORY("REPO", "Repository");

    private final String tag;
    private final String friendly;
    private final TagType type;

    private GEDCOMTag(final String tag, final String friendly, final TagType type) {
        this.tag = tag;
        this.friendly = friendly;
        this.type = type;
    }

    private GEDCOMTag(final String tag, final String friendly) {
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
    public static GEDCOMTag getByTag(final String tag) {
        for (GEDCOMTag test : values()) {
            if (test.getTag().equals(tag)) return test;
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
