package com.undeadscythes.supergenes.event;

import com.undeadscythes.supergenes.exception.*;
import com.undeadscythes.supergenes.holder.*;
import java.util.*;

/**
 * @author UndeadScythes
 */
public class SortByDate implements Comparator<Holder> {
    private final boolean increasing;

    /**
     *
     * @param increasing
     */
    public SortByDate(boolean increasing) {
        this.increasing = increasing;
    }

    /**
     *
     * @param e1
     * @param e2
     * @return
     */
    public int compare(Event e1, Event e2) {
        int y1 = 0;
        int y2 = 0;
        try {
            y1 = e1.getDate().year;
        } catch (TagNotSetException ex) {}
        try {
            y2 = e2.getDate().year;
        } catch (TagNotSetException ex) {}
        return (y1 - y2) * (increasing ? 1 : -1);
    }

    public int compare(Holder h1, Holder h2) {
        return compare((Event)h1, (Event)h2);
    }
}
