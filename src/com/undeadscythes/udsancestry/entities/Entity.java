package com.undeadscythes.udsancestry.entities;

import com.undeadscythes.udsancestry.exceptions.PropertyNotSetException;
import com.undeadscythes.udsancestry.entities.citeables.properties.Property;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class Entity {
    protected ArrayList<Property> properties = new ArrayList<Property>(0);

    public void addProperty(EntityProperty propertyType, String value) {
        properties.add(new Property(propertyType, value));
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public String getPropertyValue(EntityProperty propertyType) throws PropertyNotSetException {
        for(Property property : properties) {
            if(property.getType().equals(propertyType)) {
                return property.getValue();
            }
        }
        throw new PropertyNotSetException(propertyType);
    }

    public Property getProperty(EntityProperty propertyType) throws PropertyNotSetException {
        for(Property property : properties) {
            if(property.getType().equals(propertyType)) {
                return property;
            }
        }
        throw new PropertyNotSetException(propertyType);
    }

    public void debug(String prefix) {
        for(Property property : properties) {
            property.debug(prefix);
        }
    }
}
