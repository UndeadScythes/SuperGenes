package com.undeadscythes.supergenes.gui;

import com.undeadscythes.genebase.GeneBase;
import com.undeadscythes.genebase.gedcom.RecordType;
import com.undeadscythes.genebase.record.Family;
import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.genebase.specific.Relation;
import com.undeadscythes.metaturtle.exception.NoUniqueMetaException;
import com.undeadscythes.supergenes.gui.editor.HeadSheet;
import com.undeadscythes.supergenes.gui.viewer.*;
import com.undeadscythes.swinglow.CloseableTab;
import com.undeadscythes.swinglow.CloseableTabPane;
import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Main control panel for manipulating GeneBases.
 *
 * @author UndeadScythes
 */
public class GeneBaseControl extends JPanel {
    private static final long serialVersionUID = 1L;
    private final GeneBase geneBase;
    private final SuperGenesGUI gui;
    private CloseableTabPane parent;

    /**
     * Creates new form GeneBaseControl
     */
    public GeneBaseControl(final GeneBase geneBase, final SuperGenesGUI gui) {
        initComponents();
        this.geneBase = geneBase;
        this.gui = gui;
        title.setText(geneBase.getUID().toString());
    }

    /**
     * Open the details of the given {@link Family}.
     */
    public void openFamily(final String uid) {
        final Family family;
        try {
            family = (Family)geneBase.getUniqueMeta(RecordType.FAM, uid);
        } catch (NoUniqueMetaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "SuperGenes", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (Component panel : parent.getComponents()) {
            if (panel instanceof FamPanel && ((FamPanel)panel).family.getUID().equals(uid)) {
                parent.setSelectedComponent(panel);
                return;
            }
        }
        final FamPanel panel = new FamPanel(this, family);
        parent.addTab("", panel);
        parent.setSelectedComponent(panel);
        final int index = parent.indexOfComponent(panel);
        parent.setTabComponentAt(index, new CloseableTab(parent, family.getFather().getFamilyName() + " = " + family.getMother().getFamilyName()));
    }

    /**
     * Rename the GeneBase being held by this controller.
     */
    public void rename(final String newName) {
        title.setText(newName);
        parent.setTitleAt(parent.indexOfComponent(this), newName);
    }

    /**
     * Open a family with this {@link Individual} as a parent.
     */
    public void openFamilyFromIndividual(final String uid) {
        final List<Family> families;
        try {
            families = ((Individual)geneBase.getUniqueMeta(RecordType.INDI, uid)).getFamilies(Relation.SPOUSE);
        } catch (NoUniqueMetaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "SuperGenes", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (families.isEmpty()) return;
        openFamily(families.get(0).getUID().toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        javax.swing.JPanel main = new javax.swing.JPanel();
        javax.swing.Box.Filler left = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        javax.swing.JPanel buttons = new javax.swing.JPanel();
        fileButtons = new javax.swing.JPanel();
        rename = new com.undeadscythes.swinglow.HoverButton();
        close = new com.undeadscythes.swinglow.HoverButton();
        save = new com.undeadscythes.swinglow.HoverButton();
        indi = new com.undeadscythes.swinglow.HoverButton();
        fam = new com.undeadscythes.swinglow.HoverButton();
        sour = new com.undeadscythes.swinglow.HoverButton();
        repo = new com.undeadscythes.swinglow.HoverButton();
        note = new com.undeadscythes.swinglow.HoverButton();
        obje = new com.undeadscythes.swinglow.HoverButton();
        head = new com.undeadscythes.swinglow.HoverButton();
        subm = new com.undeadscythes.swinglow.HoverButton();
        subn = new com.undeadscythes.swinglow.HoverButton();
        javax.swing.Box.Filler right = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                addedToParent(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        title.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("<GeneBase Name>");

        main.setLayout(new java.awt.GridLayout(1, 3));
        main.add(left);

        buttons.setLayout(new java.awt.GridLayout(0, 1));

        fileButtons.setLayout(new java.awt.GridLayout(1, 0));

        rename.setText("Rename");
        fileButtons.add(rename);

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        fileButtons.add(close);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        fileButtons.add(save);

        buttons.add(fileButtons);

        indi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/indi.png"))); // NOI18N
        indi.setText("Individuals");
        indi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indiActionPerformed(evt);
            }
        });
        buttons.add(indi);

        fam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/fam.png"))); // NOI18N
        fam.setText("Families");
        buttons.add(fam);

        sour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/sour.png"))); // NOI18N
        sour.setText("Sources");
        buttons.add(sour);

        repo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/repo.png"))); // NOI18N
        repo.setText("Repositories");
        buttons.add(repo);

        note.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/note.png"))); // NOI18N
        note.setText("Notes");
        buttons.add(note);

        obje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/obje.png"))); // NOI18N
        obje.setText("Multimedia");
        buttons.add(obje);

        head.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/head.png"))); // NOI18N
        head.setText("GEDCOM Header");
        head.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headActionPerformed(evt);
            }
        });
        buttons.add(head);

        subm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/subm.png"))); // NOI18N
        subm.setText("Submitters");
        buttons.add(subm);

        subn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/subn.png"))); // NOI18N
        subn.setText("Submission");
        buttons.add(subn);

        main.add(buttons);
        main.add(right);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void headActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headActionPerformed
        parent.addCloseableTab("Header", new HeadViewer(geneBase, gui));
    }//GEN-LAST:event_headActionPerformed

    private void indiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indiActionPerformed
        parent.addCloseableTab("Individuals", new IndiBrowser(geneBase, gui, this));
    }//GEN-LAST:event_indiActionPerformed

    private void addedToParent(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_addedToParent
        parent = (CloseableTabPane)getParent();
    }//GEN-LAST:event_addedToParent

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        gui.save("");
    }//GEN-LAST:event_saveActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        gui.closeGeneBase();
    }//GEN-LAST:event_closeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.undeadscythes.swinglow.HoverButton close;
    private com.undeadscythes.swinglow.HoverButton fam;
    private javax.swing.JPanel fileButtons;
    private com.undeadscythes.swinglow.HoverButton head;
    private com.undeadscythes.swinglow.HoverButton indi;
    private com.undeadscythes.swinglow.HoverButton note;
    private com.undeadscythes.swinglow.HoverButton obje;
    private com.undeadscythes.swinglow.HoverButton rename;
    private com.undeadscythes.swinglow.HoverButton repo;
    private com.undeadscythes.swinglow.HoverButton save;
    private com.undeadscythes.swinglow.HoverButton sour;
    private com.undeadscythes.swinglow.HoverButton subm;
    private com.undeadscythes.swinglow.HoverButton subn;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
