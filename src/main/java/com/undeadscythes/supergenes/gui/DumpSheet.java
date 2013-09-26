package com.undeadscythes.supergenes.gui;

import com.undeadscythes.metaturtle.*;
import com.undeadscythes.metaturtle.metadata.*;

/**
 * Will display the {@link Holder#dump(String)} data of a given
 * {@link Metadatable}.
 *
 * @author Dave
 */
public class DumpSheet extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form DumpSheet.
     */
    public DumpSheet(final Metadatable meta) {
        initComponents();
        final StringBuilder output = new StringBuilder("");
        for (Metadata data : meta) {
            output.append(getData(data, "- "));
        }
        textMain.setText(output.toString());
        textMain.setCaretPosition(0);
    }

    private String getData(final Metadata meta, final String prefix) {
        final StringBuilder output = new StringBuilder(prefix);
        output.append(meta.getProperty());
        output.append(": ");
        output.append(meta.getValue());
        output.append("\n");
        for (Metadata data : meta) {
            output.append(getData(data, "  " + prefix));
        }
        return output.toString();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollMain = new javax.swing.JScrollPane();
        textMain = new javax.swing.JTextArea();
        checkWordWrap = new javax.swing.JCheckBox();

        textMain.setEditable(false);
        textMain.setColumns(20);
        textMain.setRows(5);
        textMain.setWrapStyleWord(true);
        scrollMain.setViewportView(textMain);

        checkWordWrap.setText("Word wrap");
        checkWordWrap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkWordWrapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollMain, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(checkWordWrap))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollMain, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkWordWrap)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkWordWrapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkWordWrapActionPerformed
        textMain.setLineWrap(checkWordWrap.isSelected());
    }//GEN-LAST:event_checkWordWrapActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkWordWrap;
    private javax.swing.JScrollPane scrollMain;
    private javax.swing.JTextArea textMain;
    // End of variables declaration//GEN-END:variables
}
