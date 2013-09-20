package com.undeadscythes.supergenes.exception;

/**
 * @author UndeadScythes
 */
public class GEDCOMLoadException extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     *
     * @param line
     * @param lineNo
     * @param exception
     */
    public GEDCOMLoadException(final String line, final int lineNo, final Exception exception) {
        super("Could not parse '" + line + "' on line " + lineNo + ".", exception);
    }
}
