package com.undeadscythes.supergenes.gui.viewer;

import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.supergenes.gui.GeneBaseControl;
import javax.swing.JPanel;

/**
 * Structure of the navigable tree.
 *
 * @author UndeadScythes
 */
public class ParentsPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Create a new ParentsPanel.
     */
    public static JPanel newPanel(final GeneBaseControl gui, final Individual indi, final int level) {
        if (level == 0) return new IndiCard(gui, indi);
        return new ParentsPanel(gui, indi, level);
    }

    /**
     * Creates a new ParentsPanel.
     */
    public ParentsPanel(final GeneBaseControl gui, final Individual indi, final int level) {
        initComponents();
        panelChild.add(new IndiCard(gui, indi));
        panelFather.add(newPanel(gui, indi.getFather(), level - 1));
        panelMother.add(newPanel(gui, indi.getMother(), level - 1));
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel panelParents = new javax.swing.JPanel();
        panelFather = new javax.swing.JPanel();
        panelMother = new javax.swing.JPanel();
        panelChild = new javax.swing.JPanel();

        setLayout(new java.awt.GridLayout(2, 1));

        panelParents.setLayout(new java.awt.GridLayout(1, 2));

        panelFather.setLayout(new java.awt.GridLayout(1, 1));
        panelParents.add(panelFather);

        panelMother.setLayout(new java.awt.GridLayout(1, 1));
        panelParents.add(panelMother);

        add(panelParents);

        panelChild.setLayout(new java.awt.GridLayout(1, 1));
        add(panelChild);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelChild;
    private javax.swing.JPanel panelFather;
    private javax.swing.JPanel panelMother;
    // End of variables declaration//GEN-END:variables
}
