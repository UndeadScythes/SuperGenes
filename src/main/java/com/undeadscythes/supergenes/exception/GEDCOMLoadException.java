package com.undeadscythes.supergenes.exception;

/**
 * Thrown if a GEDCOM cannot be loaded from a file.
 *
 * @author UndeadScythes
 */
public class GEDCOMLoadException extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * Pass the value, line number and exception that caused this error.
     */
    public GEDCOMLoadException(final String line, final int lineNo, final Exception exception) {
        super("Could not parse '" + line + "' on line " + lineNo + ".", exception);
    }
}
