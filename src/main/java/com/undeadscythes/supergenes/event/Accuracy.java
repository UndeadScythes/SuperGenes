package com.undeadscythes.supergenes.event;

/**
 * @author UndeadScythes
 */
public enum Accuracy {
    /**
     *
     */
    ABOUT("abt"),
    /**
     *
     */
    EXACT(""),
    /**
     *
     */
    BETWEEN("bet");

    /**
     *
     */
    public final String shortHand;

    private Accuracy(String shortHand) {
        this.shortHand = shortHand;
    }
}
