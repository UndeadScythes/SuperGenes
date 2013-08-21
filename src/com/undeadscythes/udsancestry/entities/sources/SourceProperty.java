package com.undeadscythes.udsancestry.entities.sources;

import com.undeadscythes.udsancestry.entities.EntityProperty;
import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;

/**
 * @author UndeadScythes
 */
public enum SourceProperty implements EntityProperty {
    ID("ID"),
    _APID("_APID"),
    TITLE("TITL"),
    AUTHORITY("AUTH"),
    REPOSITORY("REPO"),
    PULISHER("PUBL");

    protected String tag;

    SourceProperty(String tag) {
        this.tag = tag;
    }

    public static SourceProperty getByTag(String name) throws NoPropertyExistsException {
        for(SourceProperty property : values()) {
            if(name.equals(property.tag)) {
                return property;
            }
        }
        throw new NoPropertyExistsException(name);
    }
}
