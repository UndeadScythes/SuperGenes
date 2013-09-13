package com.undeadscythes.supergenes.header;

import com.undeadscythes.gedform.*;

/**
 * @author UndeadScythes
 */
public enum HeaderTag implements Tag {
    /**
     *
     */
    GEDCOM_DATA("GEDC", "GEDCOM data"),
    /**
     *
     */
    VERSION("VERS", "Version"),
    /**
     *
     */
    DESTINATION("DEST", "Destination"),
    /**
     *
     */
    CHARACTER_SET("CHAR", "Character set"),
    /**
     *
     */
    BUSINESS("CORP", "Business"),
    /**
     *
     */
    FILE_NAME("FILE", "File name"),
    /**
     *
     */
    LAST_CHANGE("CHAN", "Last change"),
    /**
     *
     */
    FORM("FORM", "Form");

    private final String tag;
    private final String friendly;
    private final TagType type;

    private HeaderTag(final String tag, final String friendly, final TagType type) {
        this.tag = tag;
        this.friendly = friendly;
        this.type = type;
    }

    private HeaderTag(final String tag, final String friendly) {
        this(tag, friendly, TagType.FACT);
    }

    public String getTag() {
        return tag;
    }

    public String getFriendly() {
        return friendly;
    }

    public TagType getType() {
        return type;
    }
}
