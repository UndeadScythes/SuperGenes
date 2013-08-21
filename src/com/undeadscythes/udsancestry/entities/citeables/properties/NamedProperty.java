package com.undeadscythes.udsancestry.entities.citeables.properties;

import com.undeadscythes.udsancestry.entities.EntityProperty;

/**
 * @author UndeadScythes
 */
public class NamedProperty implements EntityProperty {
    private String name;

    public NamedProperty(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }
}
