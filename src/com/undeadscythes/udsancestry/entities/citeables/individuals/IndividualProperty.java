package com.undeadscythes.udsancestry.entities.citeables.individuals;

import com.undeadscythes.udsancestry.entities.EntityProperty;
import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;

/**
 * @author UndeadScythes
 */
public enum IndividualProperty implements EntityProperty {
    ID("ID"),
    GENDER("SEX"),
    NAME("NAME"),
    FB("_FACE"),
    CHILD_IN_FAMILY("FAMC"),
    SPOUSE_IN_FAMILY("FAMS"),
    CITATION("SOUR"),
    EMBED("OBJE"),
    MILITARY("_MILT");

    protected String tag;

    IndividualProperty(String tag) {
        this.tag = tag;
    }

    public static IndividualProperty getByTag(String tag) throws NoPropertyExistsException {
        for(IndividualProperty type : values()) {
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
