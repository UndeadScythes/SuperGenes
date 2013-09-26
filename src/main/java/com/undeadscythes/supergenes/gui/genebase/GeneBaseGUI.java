package com.undeadscythes.supergenes.gui.genebase;

import com.undeadscythes.genebase.GeneBase;
import com.undeadscythes.genebase.comparator.SortByName;
import com.undeadscythes.genebase.gedcom.RecordType;
import com.undeadscythes.genebase.record.Header;
import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.metaturtle.exception.NoMetadataSetException;
import com.undeadscythes.metaturtle.unique.UniqueMeta;
import com.undeadscythes.supergenes.gui.DumpSheet;
import com.undeadscythes.supergenes.gui.individual.IndividualListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Allows browsing and editing of {@link GeneBase GeneBases}.
 *
 * @author Dave
 */
public class GeneBaseGUI extends JPanel {
    private static final long serialVersionUID = 1L;

    private final GeneBase geneBase;

    /**
     * Creates new form GeneBaseGUI
     */
    public GeneBaseGUI(final GeneBase geneBase) {
        this.geneBase = geneBase;
        initComponents();
        labelTitle.setText(geneBase.getUID().toString());
        final DumpSheet textHeader;
        try {
            textHeader = new DumpSheet((Header)geneBase.getFirst("HEAD"));
            tabMain.addTab("Header", textHeader);
        } catch (NoMetadataSetException ex) {}
        final List<Individual> indis = new ArrayList<Individual>(0);
        for (UniqueMeta meta : geneBase.getUniqueMeta(RecordType.INDI)) {
            indis.add((Individual)meta);
        }
        Collections.sort(indis, SortByName.ASCENDING);
        String currentChar = "#";
        JPanel page = new JPanel();
        page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
        for (Individual indi : indis) {
            if (!indi.getFamilyName().isEmpty()) {
                final String nextChar = indi.getFamilyName().substring(0, 1).toUpperCase();
                if (nextChar.compareTo(currentChar) > 0) {
                    page = new JPanel();
                    page.setLayout(new BoxLayout(page, BoxLayout.Y_AXIS));
                    currentChar = nextChar;
                    tabIndividuals.addTab(currentChar, new JScrollPane(page));
                }
            }
            final JButton button = new JButton((indi.getFamilyName().isEmpty() ? "" : indi.getFamilyName() + ", ") + indi.getGivenName());
            button.addActionListener(new IndividualListener(tabMain, indi));
            page.add(button);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        tabMain = new javax.swing.JTabbedPane();
        panelDirectory = new javax.swing.JPanel();
        tabIndividuals = new javax.swing.JTabbedPane();

        setPreferredSize(new java.awt.Dimension(630, 445));

        labelTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("<GeneBase name>");

        javax.swing.GroupLayout panelDirectoryLayout = new javax.swing.GroupLayout(panelDirectory);
        panelDirectory.setLayout(panelDirectoryLayout);
        panelDirectoryLayout.setHorizontalGroup(
            panelDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDirectoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabIndividuals, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelDirectoryLayout.setVerticalGroup(
            panelDirectoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDirectoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabIndividuals, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabMain.addTab("Individuals", panelDirectory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabMain)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabMain))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel panelDirectory;
    private javax.swing.JTabbedPane tabIndividuals;
    private javax.swing.JTabbedPane tabMain;
    // End of variables declaration//GEN-END:variables
}
