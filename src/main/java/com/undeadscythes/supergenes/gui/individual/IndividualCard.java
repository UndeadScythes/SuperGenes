/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.undeadscythes.supergenes.gui.individual;

import com.undeadscythes.genebase.record.*;
import com.undeadscythes.genebase.specific.*;
import java.awt.*;
import javax.swing.*;

/**
 * Displays basic information about an individual.
 *
 * @author Dave
 */
public class IndividualCard extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates new form IndividualCard
     */
    public IndividualCard(final Individual indi) {
        initComponents();
        jButton1.setText(indi.getFullName());
        jLabel1.setText(indi.getBirth().year + " - " + indi.getDeath().year);
        if (indi.getGender().equals(Gender.MALE)) {
            setBackground(Color.getHSBColor(.95F, .2F, 1F));
        } else {
            setBackground(Color.getHSBColor(0F, .2F, 1F));
        }
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jButton1.setText("<Individual full name>");
        jButton1.setAlignmentX(0.5F);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<Birth - Death>");
        jLabel1.setAlignmentX(0.5F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
