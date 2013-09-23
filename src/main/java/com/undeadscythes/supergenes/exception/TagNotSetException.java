package com.undeadscythes.supergenes.exception;

import com.undeadscythes.genebase.gedcom.*;

/**
 * Thrown when the value of a tag is requested that does not exist.
 *
 * @author UndeadScythes
 */
public class TagNotSetException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    /**
     * Pass the tag which has no value set.
     */
    public TagNotSetException(final GEDTag tag) {
        super("No value set for tag " + tag.getString() + ".");
    }
}
