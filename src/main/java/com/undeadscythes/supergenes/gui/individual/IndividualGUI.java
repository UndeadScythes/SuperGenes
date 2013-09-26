package com.undeadscythes.supergenes.gui.individual;

import com.undeadscythes.genebase.comparator.SortByBirth;
import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.genebase.specific.Gender;
import com.undeadscythes.supergenes.gui.DumpSheet;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Displays information and tools for editing an {@link Individual}.
 *
 * @author Dave
 */
public class IndividualGUI extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Link the {@link JPanel} to the given {@link Individual}.
     */
    public IndividualGUI(final Individual indi) {
        initComponents();
        labelTitle.setText(indi.getFullName());
        final JPanel tabFamily = new JPanel();
        tabFamily.setLayout(new BorderLayout());
        final JPanel children = new JPanel();
        children.setLayout(new BoxLayout(children, BoxLayout.PAGE_AXIS));
        addDescendants(children, indi);
        final JPanel parents = new JPanel();
        parents.setLayout(new FlowLayout());
        parents.add(ancestors(indi));
        tabFamily.add(parents, BorderLayout.PAGE_START);
        tabFamily.add(couple(indi), BorderLayout.CENTER);
        tabFamily.add(children, BorderLayout.PAGE_END);
        tabbedMain.addTab("Family", tabFamily);
        final DumpSheet tabDump = new DumpSheet(indi);
        tabbedMain.addTab("Dump", tabDump);
    }

    private JPanel couple(final Individual indi) {
        final JPanel couple = new JPanel();
        couple.setLayout(new FlowLayout());
        final List<Individual> spouses = indi.getSpouses();
        if (spouses.isEmpty()) spouses.add(Individual.UNKNOWN);
        if (indi.getGender().equals(Gender.MALE)) {
            couple.add(new IndividualCard(indi));
            couple.add(new IndividualCard(spouses.get(0)));
        } else {
            couple.add(new IndividualCard(spouses.get(0)));
            couple.add(new IndividualCard(indi));
        }
        return couple;
    }

    private JPanel ancestors(final Individual indi) {
        final JPanel couple = new JPanel();
        couple.setLayout(new FlowLayout());
        final List<Individual> spouses = indi.getSpouses();
        if (spouses.isEmpty()) spouses.add(Individual.UNKNOWN);
        if (indi.getGender().equals(Gender.MALE)) {
            couple.add(new IndividualCard(indi.getFather()));
            couple.add(new IndividualCard(indi.getMother()));
            couple.add(new IndividualCard(spouses.get(0).getFather()));
            couple.add(new IndividualCard(spouses.get(0).getMother()));
        } else {
            couple.add(new IndividualCard(spouses.get(0).getFather()));
            couple.add(new IndividualCard(spouses.get(0).getMother()));
            couple.add(new IndividualCard(indi.getFather()));
            couple.add(new IndividualCard(indi.getMother()));
        }
        return couple;
    }

    private void addDescendants(final JPanel panel, final Individual indi) {
        final List<Individual> children = indi.getChildren();
        SortByBirth.ASCENDING.sort(children);
        for (Individual child : children) {
            panel.add(new IndividualCard(child));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        tabbedMain = new javax.swing.JTabbedPane();

        labelTitle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("<Individual name>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
            .addComponent(tabbedMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbedMain, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTabbedPane tabbedMain;
    // End of variables declaration//GEN-END:variables
}
