package com.undeadscythes.supergenes.gui.menu;

import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.gui.genebase.GeneBaseGUI;
import java.awt.event.*;
import javax.swing.*;

/**
 * Provides context sensitive menu operations.
 *
 * @author UndeadScythes
 */
public class MenuListener implements ActionListener {
    private static GeneBaseGUI geneBase;
    private final JFrame container;
    private final SuperGenes program;

    /**
     * Pass the parent {@link JFrame}.
     */
    public MenuListener(final JFrame container, final SuperGenes program) {
        this.container = container;
        this.program = program;
    }

    public void actionPerformed(final ActionEvent event) {
        final JRadioButtonMenuItem item = (JRadioButtonMenuItem)event.getSource();
        item.setSelected(true);
        openGeneBaseGUI(item.getText());
    }

    /**
     * Open a given GeneBase into the main window.
     */
    public void openGeneBaseGUI(final String name) {
        if (geneBase != null) {
            container.remove(geneBase);
        }
        geneBase = new GeneBaseGUI(program.getGeneBase(name));
        container.add(geneBase);
        container.validate();
    }
}
