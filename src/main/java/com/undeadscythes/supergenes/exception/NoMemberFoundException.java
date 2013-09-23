package com.undeadscythes.supergenes.exception;

/**
 * Thrown when a member is requested with a given ID and none is found.
 *
 * @author UndeadScythes
 */
public class NoMemberFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Pass the ID of the missing member.
     */
    public NoMemberFoundException(final int IDNo) {
        super("No member with unique ID " + IDNo + " exists.");
    }
}
