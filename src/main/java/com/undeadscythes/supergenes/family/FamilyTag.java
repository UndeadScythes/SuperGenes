package com.undeadscythes.supergenes.family;

import com.undeadscythes.gedform.*;

/**
 * @author UndeadScythes
 */
public enum FamilyTag implements Tag {
    /**
     *
     */
    FATHER("HUSB", "Father"),
    /**
     *
     */
    MOTHER("WIFE", "Mother"),
    /**
     *
     */
    CHILD("CHIL", "Child");

    private final String tag;
    private final String friendly;
    private final TagType type;

    private FamilyTag(final String tag, final String friendly, final TagType type) {
        this.tag = tag;
        this.friendly = friendly;
        this.type = type;
    }

    private FamilyTag(final String tag, final String friendly) {
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
