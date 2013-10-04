package com.undeadscythes.supergenes.gui.viewer;

import com.undeadscythes.supergenes.gui.viewer.IndiCard;
import com.undeadscythes.supergenes.gui.viewer.ParentsPanel;
import com.undeadscythes.genebase.comparator.SortByBirth;
import com.undeadscythes.genebase.record.Family;
import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.supergenes.gui.GeneBaseControl;
import java.util.List;
import javax.swing.JSpinner.NumberEditor;

/**
 * Display navigable family trees.
 *
 * @author UndeadScythes
 */
public class FamPanel extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    private final GeneBaseControl gui;
    /**
     * The {@link Family} this FamPanel contains data about.
     */
    public final Family family;

    /**
     * Creates a FamPanel displaying a tree of the given {@link Family}.
     */
    public FamPanel(final GeneBaseControl gui, final Family family) {
        this.gui = gui;
        this.family = family;
        initComponents();
        ((NumberEditor)spinnerAnce.getEditor()).getTextField().setEditable(false);
        drawTree();
    }

    private void drawTree() {
        panelAnce.removeAll();
        panelAnce.add(ParentsPanel.newPanel(gui, family.getFather(), (Integer)spinnerAnce.getValue()));
        panelAnce.add(ParentsPanel.newPanel(gui, family.getMother(), (Integer)spinnerAnce.getValue()));
        final Individual indi = family.getParent();
        final List<Individual> children = indi.getChildren(getSpouses(indi).get(0));
        SortByBirth.ASCENDING.sort(children);
        panelDesc.removeAll();
        for (Individual child : children) {
            panelDesc.add(new IndiCard(gui, child));
        }
        revalidate();
    }

    private List<Individual> getSpouses(final Individual indi) {
        final List<Individual> spouses = indi.getSpouses();
        if (spouses.isEmpty()) spouses.add(Individual.UNKNOWN);
        return spouses;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel footer = new javax.swing.JPanel();
        javax.swing.JLabel labelAnce = new javax.swing.JLabel();
        spinnerAnce = new javax.swing.JSpinner();
        javax.swing.JPanel panelTrees = new javax.swing.JPanel();
        javax.swing.JPanel titleAnce = new javax.swing.JPanel();
        javax.swing.JScrollPane scrollAnce = new javax.swing.JScrollPane();
        panelAnce = new javax.swing.JPanel();
        javax.swing.JPanel titleDesc = new javax.swing.JPanel();
        javax.swing.JScrollPane scrollDesc = new javax.swing.JScrollPane();
        panelDesc = new javax.swing.JPanel();

        labelAnce.setText("Ancestors:");

        spinnerAnce.setModel(new javax.swing.SpinnerNumberModel(1, 0, 5, 1));
        spinnerAnce.setFocusable(false);
        spinnerAnce.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerAnceStateChanged(evt);
            }
        });

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addComponent(labelAnce)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerAnce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAnce)
                    .addComponent(spinnerAnce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelTrees.setLayout(new javax.swing.BoxLayout(panelTrees, javax.swing.BoxLayout.PAGE_AXIS));

        titleAnce.setBorder(javax.swing.BorderFactory.createTitledBorder("Ancestors"));
        titleAnce.setLayout(new java.awt.GridLayout(1, 1));

        scrollAnce.setBorder(null);

        panelAnce.setLayout(new java.awt.GridLayout(1, 2));
        scrollAnce.setViewportView(panelAnce);

        titleAnce.add(scrollAnce);

        panelTrees.add(titleAnce);

        titleDesc.setBorder(javax.swing.BorderFactory.createTitledBorder("Descendants"));
        titleDesc.setLayout(new java.awt.GridLayout(1, 1));

        scrollDesc.setBorder(null);
        scrollDesc.setViewportView(panelDesc);

        titleDesc.add(scrollDesc);

        panelTrees.add(titleDesc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTrees, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTrees, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void spinnerAnceStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerAnceStateChanged
        drawTree();
    }//GEN-LAST:event_spinnerAnceStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panelAnce;
    private javax.swing.JPanel panelDesc;
    private javax.swing.JSpinner spinnerAnce;
    // End of variables declaration//GEN-END:variables
}
