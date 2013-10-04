package com.undeadscythes.supergenes.gui;

import java.io.IOException;
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

        final javax.swing.JPanel lower = new javax.swing.JPanel();
        final javax.swing.Box.Filler left = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        final javax.swing.JPanel buttons = new javax.swing.JPanel();
        final com.undeadscythes.swinglow.HoverButton newGeneBase = new com.undeadscythes.swinglow.HoverButton();
        final com.undeadscythes.swinglow.HoverButton loadGeneBase = new com.undeadscythes.swinglow.HoverButton();
        final javax.swing.Box.Filler right = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        final javax.swing.JScrollPane scroll = new javax.swing.JScrollPane();
        final javax.swing.JTextPane text = new javax.swing.JTextPane();
        final javax.swing.JCheckBox hide = new javax.swing.JCheckBox();

        lower.setLayout(new javax.swing.BoxLayout(lower, javax.swing.BoxLayout.LINE_AXIS));
        lower.add(left);

        buttons.setLayout(new java.awt.GridLayout(0, 1));

        newGeneBase.setText("Start a New GeneBase");
        newGeneBase.setAlignmentX(0.5F);
        newGeneBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGeneBaseActionPerformed(evt);
            }
        });
        buttons.add(newGeneBase);

        loadGeneBase.setText("Load a GEDCOM");
        loadGeneBase.setAlignmentX(0.5F);
        loadGeneBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGeneBaseActionPerformed(evt);
            }
        });
        buttons.add(loadGeneBase);

        lower.add(buttons);
        lower.add(right);

        scroll.setBorder(null);

        text.setEditable(false);
        text.setOpaque(false);
        scroll.setViewportView(text);
        try {     text.setPage(getClass().getResource("/gui/welcome.html")); } catch (IOException ex) {}

        hide.setText("Hide this screen on startup");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(hide)
                .addGap(0, 461, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scroll)
                        .addComponent(lower, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 388, Short.MAX_VALUE)
                .addComponent(hide))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(68, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadGeneBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGeneBaseActionPerformed
        gui.openGeneBase();
    }//GEN-LAST:event_loadGeneBaseActionPerformed

    private void newGeneBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGeneBaseActionPerformed
        gui.newGeneBase();
    }//GEN-LAST:event_newGeneBaseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
