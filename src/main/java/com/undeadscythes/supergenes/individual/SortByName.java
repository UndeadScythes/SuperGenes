package com.undeadscythes.supergenes.individual;

import java.util.*;

/**
 * @author UndeadScythes
 */
public class SortByName implements Comparator<Individual> {
    private boolean familyName;

    /**
     *
     * @param familyName
     */
    public SortByName(boolean familyName) {
        this.familyName = familyName;
    }

    public int compare(Individual i1, Individual i2) {
        int c = i1.getFamilyName().toLowerCase().compareTo(i2.getFamilyName().toLowerCase());
        if (familyName && c != 0) return c;
        return i1.getGivenName().toLowerCase().compareTo(i2.getGivenName().toLowerCase());
    }
}
