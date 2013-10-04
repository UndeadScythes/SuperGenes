package com.undeadscythes.supergenes.gui.listener;

import com.undeadscythes.supergenes.gui.DirectoryItem;
import com.undeadscythes.supergenes.gui.viewer.IndiBrowser;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Listens for clicks on the Individual directory list.
 *
 * @author UndeadScythes
 */
public class IndiListListener implements ListSelectionListener {
    private final IndiBrowser container;

    /**
     * Pass the container of this Listener.
     */
    public IndiListListener(final IndiBrowser container) {
        this.container = container;
    }

    @Override
    public void valueChanged(final ListSelectionEvent event) {
        container.openIndividual((DirectoryItem)((JList)event.getSource()).getSelectedValue());
    }
}
