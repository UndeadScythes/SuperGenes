package com.undeadscythes.supergenes.gui;

import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.metaturtle.unique.UID;
import java.util.Map;

/**
 * A directory list item.
 *
 * @author UndeadScythes
 */
public class DirectoryItem implements Map.Entry<UID, String> {
    private final UID uid;
    private final String display;

    /**
     * Load an Individual.
     */
    public DirectoryItem(final Individual indi) {
        uid = indi.getUID();
        display = (indi.getFamilyName().isEmpty() ? "" : indi.getFamilyName() + ", ") + indi.getGivenName();
    }

    @Override
    public UID getKey() {
        return uid;
    }

    @Override
    public String toString() {
        return display;

    }

    @Override
    public String getValue() {
        return display;
    }

    @Override
    public String setValue(final String value) {
        return display;
    }
}
