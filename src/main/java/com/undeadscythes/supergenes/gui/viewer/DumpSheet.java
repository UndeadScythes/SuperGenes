package com.undeadscythes.supergenes.gui.viewer;

import com.undeadscythes.metaturtle.Metadatable;
import com.undeadscythes.metaturtle.metadata.Metadata;
import javax.swing.JPanel;

/**
 * A text area for debugging output along with a word wrap check button.
 *
 * @author UndeadScythes
 */
public class DumpSheet extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a blank DumpSheet.
     */
    public DumpSheet() {
        initComponents();
    }

    /**
     * Creates a DumpSheet containing a data dump of the given
     * {@link Metadatable} object.
     */
    public DumpSheet(final Metadatable meta) {
        this();
        dump(meta);
    }

    /**
     * Fill the text area of this DumpSheet with a data dump of the given
     * {@link Metadatable} object.
     */
    public final void dump(final Metadatable meta) {
        final StringBuilder output = new StringBuilder("");
        for (Metadata data : meta) {
            output.append(getData(data, "- "));
        }
        text.setText(output.toString());
        text.setCaretPosition(0);
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

        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane();
        text = new javax.swing.JTextArea();
        wordWrap = new javax.swing.JCheckBox();

        text.setEditable(false);
        text.setColumns(20);
        text.setRows(5);
        text.setWrapStyleWord(true);
        scroll.setViewportView(text);

        wordWrap.setText("Word Wrap");
        wordWrap.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                wordWrapStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scroll)
            .addGroup(layout.createSequentialGroup()
                .addComponent(wordWrap)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scroll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wordWrap))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void wordWrapStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_wordWrapStateChanged
        text.setLineWrap(wordWrap.isSelected());
    }//GEN-LAST:event_wordWrapStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea text;
    private javax.swing.JCheckBox wordWrap;
    // End of variables declaration//GEN-END:variables
}
