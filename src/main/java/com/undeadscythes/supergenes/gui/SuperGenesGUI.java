package com.undeadscythes.supergenes.gui;

import com.undeadscythes.genebase.*;
import com.undeadscythes.supergenes.*;
import com.undeadscythes.supergenes.gui.menu.*;
import com.undeadscythes.supergenes.service.*;
import com.undeadscythes.tipscript.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

/**
 * The open GUI window to server the {@link SuperGenesGUI} services.
 *
 * @author Dave
 */
public class SuperGenesGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private final SuperGenes program;
    private final TipRedirect out;

    /**
     * Creates new form SuperGenesGUI.
     */
    public SuperGenesGUI(final SuperGenes program, final TipRedirect out) {
        this.program = program;
        this.out = out;
        initComponents();
        setLayout(new BorderLayout());
        for (GeneBase geneBase : program.getGeneBases()) {
            final JRadioButtonMenuItem item = new JRadioButtonMenuItem(geneBase.getUID().toString());
            item.addActionListener(new MenuListener(this, program));
            groupGeneBase.add(item);
            menuGeneBases.add(item);
            itemNoGeneBases.setVisible(false);
        }
        setLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    /**
     * Get the program that is running this GUI.
     */
    public SuperGenes getProgram() {
        return program;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupGeneBase = new javax.swing.ButtonGroup();
        menu = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        itemSave = new javax.swing.JMenuItem();
        itemLoad = new javax.swing.JMenuItem();
        itemClose = new javax.swing.JMenuItem();
        seperator1 = new javax.swing.JPopupMenu.Separator();
        itemQuit = new javax.swing.JMenuItem();
        menuEdit = new javax.swing.JMenu();
        menuGeneBases = new javax.swing.JMenu();
        itemNoGeneBases = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SuperGenes");
        setPreferredSize(new java.awt.Dimension(640, 480));

        menuFile.setText("File");

        itemSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        itemSave.setText("Save");
        menuFile.add(itemSave);

        itemLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        itemLoad.setText("Load");
        itemLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLoadActionPerformed(evt);
            }
        });
        menuFile.add(itemLoad);

        itemClose.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        itemClose.setText("Close");
        menuFile.add(itemClose);
        menuFile.add(seperator1);

        itemQuit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        itemQuit.setText("Quit");
        itemQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemQuitActionPerformed(evt);
            }
        });
        menuFile.add(itemQuit);

        menu.add(menuFile);

        menuEdit.setText("Edit");
        menu.add(menuEdit);

        menuGeneBases.setText("GeneBases");

        itemNoGeneBases.setText("No GeneBases Loaded");
        itemNoGeneBases.setEnabled(false);
        menuGeneBases.add(itemNoGeneBases);

        menu.add(menuGeneBases);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemQuitActionPerformed
        this.dispose();
    }//GEN-LAST:event_itemQuitActionPerformed

    private void itemLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLoadActionPerformed
        final JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()));
        chooser.setFileFilter(new GEDFilter());
        if (chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) return;
        final Load load = new Load();
        load.run(program, chooser.getSelectedFile().getPath());
        final String message = out.getOutput();
        if (load.getGeneBase() != null) {
            final JRadioButtonMenuItem item = new JRadioButtonMenuItem(load.getGeneBase().getUID().toString());
            final MenuListener listener = new MenuListener(this, program);
            item.addActionListener(listener);
            groupGeneBase.add(item);
            menuGeneBases.add(item);
            itemNoGeneBases.setVisible(false);
            item.setSelected(true);
            listener.openGeneBaseGUI(item.getText());
        }
        JOptionPane.showMessageDialog(this, message, "SuperGenes", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_itemLoadActionPerformed

    private void setLookAndFeel() {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SuperGenesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SuperGenesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SuperGenesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SuperGenesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup groupGeneBase;
    private javax.swing.JMenuItem itemClose;
    private javax.swing.JMenuItem itemLoad;
    private javax.swing.JMenuItem itemNoGeneBases;
    private javax.swing.JMenuItem itemQuit;
    private javax.swing.JMenuItem itemSave;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuGeneBases;
    private javax.swing.JPopupMenu.Separator seperator1;
    // End of variables declaration//GEN-END:variables
}
