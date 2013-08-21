package com.undeadscythes.udsancestry.entities.gedcom;

import com.undeadscythes.udsancestry.entities.EntityProperty;
import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;

/**
 * @author UndeadScythes
 */
public enum GEDCOMProperty implements EntityProperty {
    CREATOR("SOUR"),
    ENCODING("CHAR"),
    VERSION("GEDC");

    protected String tag;

    GEDCOMProperty(String tag) {
        this.tag = tag;
    }

    public static GEDCOMProperty getByTag(String tag) throws NoPropertyExistsException {
        for(GEDCOMProperty type : values()) {
            if(tag.equals(type.tag)) {
                return type;
            }
        }
        throw new NoPropertyExistsException(tag);
    }
}
