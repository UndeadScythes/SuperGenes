package com.undeadscythes.supergenes.exception;

import com.undeadscythes.genebase.gedcom.*;

/**
 * @author UndeadScythes
 */
@SuppressWarnings("serial")
public class TagNotSetException extends RuntimeException {
    /**
     *
     * @param tag
     */
    public TagNotSetException(GEDTag tag) {
        super("No value set for tag " + tag.getTag() + ".");
    }
}
