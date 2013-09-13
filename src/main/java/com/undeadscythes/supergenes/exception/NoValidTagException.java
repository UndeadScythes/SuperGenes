package com.undeadscythes.supergenes.exception;

/**
 * @author UndeadScythes
 */
@SuppressWarnings("serial")
public class NoValidTagException extends RuntimeException {
    /**
     *
     */
    public final String tag;
    /**
     *
     * @param tag
     */
    public NoValidTagException(String tag) {
        super("No valid type found with the tag " + tag + ".");
        this.tag = tag;
    }
}
