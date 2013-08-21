package com.undeadscythes.udsancestry.entities.citeables.properties;

import com.undeadscythes.udsancestry.entities.citeables.Citeable;
import com.undeadscythes.udsancestry.entities.EntityProperty;

/**
 * @author UndeadScythes
 */
public class Property extends Citeable {
    private EntityProperty property;
    private String value;

    public Property(EntityProperty property, String value) {
        this.property = property;
        this.value = value;
    }

    public EntityProperty getType() {
        return property;
    }

    public String getValue() {
        return value;
    }

    public String setValue(String value) {
        String oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public void debug(String prefix) {
        System.out.println(prefix + "- " + property.name() + ": " + value);
        super.debug(prefix + "   ");
    }
}
