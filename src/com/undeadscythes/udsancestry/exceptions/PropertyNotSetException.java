package com.undeadscythes.udsancestry.exceptions;

import com.undeadscythes.udsancestry.entities.EntityProperty;

/**
 * @author UndeadScythes
 */
@SuppressWarnings("serial")
public class PropertyNotSetException extends Exception {
    public PropertyNotSetException(EntityProperty type) {
        super("No property of type " + type.name() + " is set.");
    }
}
