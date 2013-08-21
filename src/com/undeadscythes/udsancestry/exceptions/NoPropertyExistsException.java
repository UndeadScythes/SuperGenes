package com.undeadscythes.udsancestry.exceptions;

/**
 * @author UndeadScythes
 */
@SuppressWarnings("serial")
public class NoPropertyExistsException extends Exception {
    public NoPropertyExistsException(String tag) {
        super("No property exists with the tag " + tag + ".");
    }
}
