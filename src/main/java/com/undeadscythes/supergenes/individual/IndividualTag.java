package com.undeadscythes.supergenes.individual;

import com.undeadscythes.gedform.*;

/**
 * @author UndeadScythes
 */
public enum IndividualTag implements Tag {
    /**
     *
     */
    GENDER("SEX", "Gender"),
    /**
     *
     */
    NAME("NAME", "Full name"),
    /**
     *
     */
    OCCUPATION("OCCU", "Occupation"),
    FAMILY_SPOUSE("FAMS", "Spouse in family", TagType.XREF),
    FAMILY_CHILD("FAMC", "Child in family", TagType.XREF),
    /**
     *
     */
    BIRTH("BIRT", "Birth", TagType.EVENT),
    /**
     *
     */
    EDUCATION("EDUC", "Education"),
    /**
     *
     */
    RELIGION("RELI", "Religion"),
    /**
     *
     */
    MARRIAGE("MARR", "Marriage", TagType.EVENT),
    /**
     *
     */
    BURIED("BURI", "Buried", TagType.EVENT),
    /**
     *
     */
    BAPTISM("BAPM", "Baptism", TagType.EVENT),
    /**
     *
     */
    DEATH("DEAT", "Death", TagType.EVENT),
    /**
     *
     */
    RESIDENCE("RESI", "Residence", TagType.EVENT),
    /**
     *
     */
    EVENT("EVEN", "Event", TagType.EVENT),
    /**
     *
     */
    CENSUS("CENS", "Census", TagType.EVENT);

    /**
     *
     */
    public final String tag;
    /**
     *
     */
    public final String friendly;
    /**
     *
     */
    public final TagType type;

    private IndividualTag(String tag, String friendly, TagType type) {
        this.tag = tag;
        this.friendly = friendly;
        this.type = type;
    }

    private IndividualTag(String tag, String friendly) {
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
