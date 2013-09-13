package com.undeadscythes.supergenes.individual;

import com.undeadscythes.supergenes.event.*;
import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.holder.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class SortByBirth implements Comparator<Individual> {
    private final boolean increasing;

    /**
     *
     * @param increasing
     */
    public SortByBirth(boolean increasing) {
        this.increasing = increasing;
    }

    public int compare(Individual i1, Individual i2) {
        int y1 = 0;
        int y2 = 0;
        try {
            y1 = ((Event)i1.getTag(IndividualTag.BIRTH)).getDate().year;
        } catch (TagNotSetException ex) {}
        try {
            y2 = ((Event)i2.getTag(IndividualTag.BIRTH)).getDate().year;
        } catch (TagNotSetException ex) {}
        return (y1 - y2) * (increasing ? 1 : -1);
    }

    /**
     *
     * @param h1
     * @param h2
     * @return
     */
    public int compare(Holder h1, Holder h2) {
        return compare((Event)h1, (Event)h2);
    }
}
