package com.undeadscythes.supergenes.gui.listener;

import com.undeadscythes.supergenes.gui.GeneBaseControl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Provides context sensitive menu operations.
 *
 * @author UndeadScythes
 */
public class FamilyListener implements ActionListener {
    private final GeneBaseControl container;

    /**
     * Pass the parent {@link JFrame}.
     */
    public FamilyListener(final GeneBaseControl container) {
        this.container = container;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final JButton item = (JButton)event.getSource();
        item.setSelected(true);
        container.openFamily(item.getName());
    }
}
