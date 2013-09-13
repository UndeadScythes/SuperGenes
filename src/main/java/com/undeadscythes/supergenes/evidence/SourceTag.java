package com.undeadscythes.supergenes.evidence;

import com.undeadscythes.gedform.*;
import com.undeadscythes.supergenes.exception.*;

/**
 * @author UndeadScythes
 */
public enum SourceTag implements Tag {
    /**
     *
     */
    SOURCE("SOUR", "Source", TagType.CITATION),
    /**
     *
     */
    PAGE("PAGE", "Page"),
    /**
     *
     */
    DATA("DATA", "Data"),
    /**
     *
     */
    NOTE("NOTE", "Note"),
    /**
     *
     */
    TEXT("TEXT", "Text"),
    /**
     *
     */
    OBJECT("OBJE", "Object"),
    /**
     *
     */
    TITLE("TITL", "Title"),
    /**
     *
     */
    AUTHORITY("AUTH", "Authority"),
    /**
     *
     */
    PUBLISHER("PUBL", "Publisher");

    private final String tag;
    private final String friendly;
    private final TagType type;

    private SourceTag(final String tag, final String friendly, final TagType type) {
        this.tag = tag;
        this.friendly = friendly;
        this.type = type;
    }

    private SourceTag(final String tag, final String friendly) {
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
    public static SourceTag getByTag(final String tag) {
        for(SourceTag test : values()) {
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
