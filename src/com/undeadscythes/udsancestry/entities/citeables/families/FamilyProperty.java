package com.undeadscythes.udsancestry.entities.citeables.families;

import com.undeadscythes.udsancestry.entities.EntityProperty;
import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;

/**
 * @author UndeadScythes
 */
public enum FamilyProperty implements EntityProperty {
    ID("ID"),
    HUSBAND("HUSB"),
    WIFE("WIFE"),
    CHILD("CHIL"),
    MARRIAGE("MARR"),
    FATHER_RELATIONSHIP("_FREL"),
    MOTHER_RELATIONSHIP("_MREL");

    protected String tag;

    FamilyProperty(String tag) {
        this.tag = tag;
    }

    public static FamilyProperty getByTag(String tag) throws NoPropertyExistsException {
        for(FamilyProperty type : values()) {
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
