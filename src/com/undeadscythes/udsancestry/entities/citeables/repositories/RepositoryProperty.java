package com.undeadscythes.udsancestry.entities.citeables.repositories;

import com.undeadscythes.udsancestry.entities.EntityProperty;
import com.undeadscythes.udsancestry.exceptions.NoPropertyExistsException;

/**
 * @author UndeadScythes
 */
public enum RepositoryProperty implements EntityProperty {
    ID("ID"),
    NAME("NAME");

    protected String tag;

    RepositoryProperty(String tag) {
        this.tag = tag;
    }

    public static RepositoryProperty getByTag(String tag) throws NoPropertyExistsException {
        for(RepositoryProperty type : values()) {
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
