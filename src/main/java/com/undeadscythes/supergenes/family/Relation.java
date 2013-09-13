package com.undeadscythes.supergenes.family;

/**
 * @author UndeadScythes
 */
public enum Relation {
    /**
     *
     */
    FATHER("Father"),
    /**
     *
     */
    MOTHER("Mother"),
    /**
     *
     */
    CHILD("Child"),
    /**
     *
     */
    PARTNER("Partner"),
    /**
     *
     */
    WIFE("Wife"),
    /**
     *
     */
    HUSBAND("Husband"),
    /**
     *
     */
    SIBLING("Sibling"),
    /**
     *
     */
    BROTHER("Brother"),
    /**
     *
     */
    SISTER("Sister"),
    /**
     *
     */
    NONE("");

    /**
     *
     */
    public final String title;

    private Relation(String title) {
        this.title = title;
    }
}
