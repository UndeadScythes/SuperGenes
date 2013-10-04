package com.undeadscythes.supergenes.gui.viewer;

import com.undeadscythes.genebase.GeneBase;
import com.undeadscythes.metaturtle.metadata.Metadata;
import com.undeadscythes.supergenes.gui.SuperGenesGUI;
import com.undeadscythes.supergenes.gui.editor.HeadSheet;
import com.undeadscythes.swinglow.CloseableTabPane;

/**
 * View GEDCOM header information.
 *
 * @author UndeadScythes
 */
public class HeadViewer extends javax.swing.JPanel {
    private static final long serialVersionUID = 1L;

    private final SuperGenesGUI gui;
    private final GeneBase geneBase;

    /**
     * Creates new form HeadViewer
     */
    public HeadViewer(final GeneBase geneBase, final SuperGenesGUI gui) {
        initComponents();
        final StringBuilder output = new StringBuilder("");
        for (Metadata data : geneBase) {
            output.append(getData(data, "- "));
        }
        text.setText(output.toString());
        text.setCaretPosition(0);
        this.gui = gui;
        this.geneBase = geneBase;
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        final javax.swing.JScrollPane scroll = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        hoverToggle1 = new com.undeadscythes.swinglow.HoverToggle();
        hoverButton1 = new com.undeadscythes.swinglow.HoverButton();

        text.setEditable(false);
        text.setColumns(20);
        text.setRows(5);
        text.setWrapStyleWord(true);
        scroll.setViewportView(text);

        hoverToggle1.setText("Word Wrap");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, text, org.jdesktop.beansbinding.ELProperty.create("${lineWrap}"), hoverToggle1, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        hoverButton1.setText("Edit");
        hoverButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoverButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(hoverToggle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hoverButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hoverToggle1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hoverButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void hoverButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoverButton1ActionPerformed
        ((CloseableTabPane)getParent()).addCloseableTab("Header*", new HeadSheet(geneBase, gui));
    }//GEN-LAST:event_hoverButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.undeadscythes.swinglow.HoverButton hoverButton1;
    private com.undeadscythes.swinglow.HoverToggle hoverToggle1;
    private javax.swing.JTextArea text;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
