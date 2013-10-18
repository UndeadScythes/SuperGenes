package com.undeadscythes.supergenes.gui;

import java.util.logging.Logger;

/**
 * The welcome screen shown on startup and when no
 * {@link com.undeadscythes.genebase.GeneBase GeneBases} are loaded.
 *
 * @author UndeadScythes
 */
public class Welcome extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;
    private final SuperGenesGUI gui;

    /**
     * An empty constructor for NetBeans.
     */
    public Welcome() {
        gui = null;
        Logger.getLogger(getClass().getName()).severe("Incorrect construction of Welcome pane.");
    }

    /**
     * Creates new form Welcome.
     */
    public Welcome(final SuperGenesGUI gui) {
        this.gui = gui;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        columns = new javax.swing.JPanel();
        left = new javax.swing.JPanel();
        final com.undeadscythes.swinglow.HoverButton newGeneBase = new com.undeadscythes.swinglow.HoverButton();
        right = new javax.swing.JPanel();
        final com.undeadscythes.swinglow.HoverButton loadGeneBase = new com.undeadscythes.swinglow.HoverButton();

        title.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        title.setText("SuperGenes " + getClass().getPackage().getImplementationVersion());

        columns.setLayout(new java.awt.GridLayout(1, 2));

        left.setLayout(new javax.swing.BoxLayout(left, javax.swing.BoxLayout.PAGE_AXIS));

        newGeneBase.setText("Start a New GeneBase");
        newGeneBase.setAlignmentX(0.5F);
        newGeneBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGeneBaseActionPerformed(evt);
            }
        });
        left.add(newGeneBase);

        columns.add(left);

        right.setLayout(new javax.swing.BoxLayout(right, javax.swing.BoxLayout.PAGE_AXIS));

        loadGeneBase.setText("Import a GEDCOM");
        loadGeneBase.setAlignmentX(0.5F);
        loadGeneBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGeneBaseActionPerformed(evt);
            }
        });
        right.add(loadGeneBase);

        columns.add(right);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(columns, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(columns, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadGeneBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGeneBaseActionPerformed
        gui.openGeneBase();
    }//GEN-LAST:event_loadGeneBaseActionPerformed

    private void newGeneBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGeneBaseActionPerformed
        gui.newGeneBase();
    }//GEN-LAST:event_newGeneBaseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel columns;
    private javax.swing.JPanel left;
    private javax.swing.JPanel right;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
