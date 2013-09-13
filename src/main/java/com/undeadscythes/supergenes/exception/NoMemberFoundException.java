package com.undeadscythes.supergenes.exception;

/**
 * @author UndeadScythes
 */
@SuppressWarnings("serial")
public class NoMemberFoundException extends Exception {
    /**
     *
     * @param ID
     */
    public NoMemberFoundException(int ID) {
        super("No member with unique ID " + ID + " exists.");
    }
}
