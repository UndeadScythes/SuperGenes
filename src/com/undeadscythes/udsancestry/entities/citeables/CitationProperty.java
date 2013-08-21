package com.undeadscythes.udsancestry.entities.citeables;

import com.undeadscythes.udsancestry.entities.EntityProperty;
import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;

/**
 * @author UndeadScythes
 */
public enum CitationProperty implements EntityProperty {
    ID("ID"),
    _APID("_APID"),
    PAGE("PAGE"),
    CONC("CONC"),
    TEXT("TEXT"),
    DATE("DATE"),
    NOTE("NOTE");

    protected String tag;

    CitationProperty(String tag) {
        this.tag = tag;
    }

    public static CitationProperty getByTag(String name) throws NoPropertyExistsException {
        for(CitationProperty property : values()) {
            if(name.equals(property.tag)) {
                return property;
            }
        }
        throw new NoPropertyExistsException(name);
    }
}
