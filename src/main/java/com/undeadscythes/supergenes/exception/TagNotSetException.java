package com.undeadscythes.supergenes.exception;

import com.undeadscythes.gedform.*;

/**
 * @author UndeadScythes
 */
@SuppressWarnings("serial")
public class TagNotSetException extends RuntimeException {
    /**
     *
     * @param tag
     */
    public TagNotSetException(Tag tag) {
        super("No value set for tag " + tag.getTag() + ".");
    }
}
