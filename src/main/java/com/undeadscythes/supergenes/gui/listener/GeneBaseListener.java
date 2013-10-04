package com.undeadscythes.supergenes.gui.listener;

import com.undeadscythes.supergenes.gui.SuperGenesGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;

/**
 * Provides context sensitive menu operations.
 *
 * @author UndeadScythes
 */
public class GeneBaseListener implements ActionListener {
    private final SuperGenesGUI container;

    /**
     * Pass the parent {@link JFrame}.
     */
    public GeneBaseListener(final SuperGenesGUI container) {
        this.container = container;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final JRadioButtonMenuItem item = (JRadioButtonMenuItem)event.getSource();
        item.setSelected(true);
        container.switchGeneBase(item.getText());
    }
}
