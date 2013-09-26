package com.undeadscythes.supergenes.gui.individual;

import com.undeadscythes.genebase.record.*;
import com.undeadscythes.supergenes.gui.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Handles the user clicking on and individual in the directory.
 *
 * @author UndeadScythes
 */
public class IndividualListener implements ActionListener {
    private final JTabbedPane pane;
    private final Individual indi;

    /**
     * Pass the parent tabbed pane and individual UID.
     */
    public IndividualListener(final JTabbedPane pane, final Individual indi) {
        this.pane = pane;
        this.indi = indi;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final IndividualGUI gui = new IndividualGUI(indi);
        pane.addTab(indi.getFullName(), gui);
        pane.setTabComponentAt(pane.indexOfComponent(gui), new CloseableTab(pane, indi.getFullName()));
        pane.setSelectedComponent(gui);
    }
}
