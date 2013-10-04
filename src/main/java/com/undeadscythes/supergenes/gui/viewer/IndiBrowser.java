package com.undeadscythes.supergenes.gui.viewer;

import com.undeadscythes.genebase.GeneBase;
import com.undeadscythes.genebase.comparator.SortByName;
import com.undeadscythes.genebase.gedcom.GEDTag;
import com.undeadscythes.genebase.gedcom.GEDTag.TagType;
import com.undeadscythes.genebase.gedcom.RecordType;
import com.undeadscythes.genebase.record.Family;
import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.genebase.structure.Event;
import com.undeadscythes.metaturtle.exception.NoUniqueMetaException;
import com.undeadscythes.metaturtle.metadata.Metadata;
import com.undeadscythes.metaturtle.unique.UniqueMeta;
import com.undeadscythes.supergenes.gui.*;
import com.undeadscythes.supergenes.gui.editor.IndiSheet;
import com.undeadscythes.supergenes.gui.listener.FamilyListener;
import com.undeadscythes.supergenes.gui.listener.IndiListListener;
import com.undeadscythes.swinglow.CloseableTabPane;
import com.undeadscythes.swinglow.HoverButton;
import java.util.*;
import javax.swing.*;

/**
 * Display details of {@link Individual Individuals} and a directory of all
 * loaded {@link Individual Individuals}.
 *
 * @author UndeadScythes
 */
public class IndiBrowser extends JPanel {
    private static final long serialVersionUID = 1L;
    private final GeneBase geneBase;
    private final IndiListListener indiListListener = new IndiListListener(this);
    private final FamilyListener familyListener;
    private final SuperGenesGUI gui2;

    /**
     * Creates a new IndiBrowser containing data on all the
     * {@link Individual Individuals} in the given {@link GeneBase}.
     */
    public IndiBrowser(final GeneBase geneBase, final SuperGenesGUI gui2, final GeneBaseControl gui) {
        this.gui2 = gui2;
        this.geneBase = geneBase;
        familyListener = new FamilyListener(gui);
        initComponents();
        final List<Individual> indis = new ArrayList<Individual>(0);
        gui2.footer.pulse();
        for (UniqueMeta meta : geneBase.getUniqueMeta(RecordType.INDI)) {
            indis.add((Individual)meta);
        }
        Collections.sort(indis, SortByName.ASCENDING);
        dirTabs.removeAll(); //TODO: See if this can be made cleaner with better loops or something

        final DefaultListModel master = new DefaultListModel();
        JScrollPane scroll = new JScrollPane(dirAll);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dirTabs.addTab("*", scroll);
        String currentChar = "#";
        DefaultListModel model = new DefaultListModel();
        for (Individual indi : indis) {
            if (!indi.getFamilyName().isEmpty()) {
                final String nextChar = indi.getFamilyName().substring(0, 1).toUpperCase();
                if (nextChar.compareTo(currentChar) > 0) {
                    if (!model.isEmpty()) {
                        final JList list = new JList(model);
                        list.addListSelectionListener(indiListListener);
                        scroll = new JScrollPane(list);
                        scroll.setBorder(null);
                        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                        dirTabs.addTab(currentChar, scroll);
                    }
                    model = new DefaultListModel();
                    currentChar = nextChar;
                }
            }
            final DirectoryItem listItem = new DirectoryItem(indi);
            model.addElement(listItem);
            master.addElement(listItem);
            gui2.footer.increment(1);
        }
        final JList list = new JList(model);
        list.addListSelectionListener(indiListListener);
        scroll = new JScrollPane(list);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        dirTabs.addTab(currentChar, scroll);
        dirAll.setModel(master);
        dirAll.addListSelectionListener(indiListListener);
        gui2.footer.done();
        gui2.footer.setText("Opened GeneBase " + geneBase.getUID() + ".");
    }

    /**
     * Open the details of the given {@link Individual}.
     */
    public void openIndividual(final DirectoryItem item) {
        final Individual indi;
        try {
            indi = (Individual)geneBase.getUniqueMeta(RecordType.INDI, item.getKey());
        } catch (NoUniqueMetaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "SuperGenes", JOptionPane.ERROR_MESSAGE);
            return;
        }
        fact.removeAll();
        for (Metadata data : indi.getListByType(TagType.FACT)) {
            fact.add(new JLabel(GEDTag.getByName(data.getProperty()).getFormal() + ": " + data.getValue()));
        }
        even.removeAll();
        for (Metadata data : indi.getListByType(TagType.EVENT)) {
            even.add(new JLabel(GEDTag.getByName(data.getProperty()).getFormal() + ": " + ((Event)data).toString()));
        }
        custom.removeAll();
        for (Metadata data : indi.getListByType(TagType.CUSTOM)) {
            custom.add(new JLabel(GEDTag.getByName(data.getProperty()).getFormal() + ": " + data.toString()));
        }
        for (Metadata data : indi.getListByType(TagType.OTHER)) {
            custom.add(new JLabel(GEDTag.getByName(data.getProperty()).getFormal() + ": " + data.toString()));
        }
        parents.removeAll();
        for (Metadata data : indi.getList(GEDTag.FAMC)) {
            final Family family;
            try {
                family = (Family)geneBase.getUniqueMeta(RecordType.FAM, data.getValue());
            } catch (NoUniqueMetaException ex) {
                return;
            }
            final HoverButton button = new HoverButton(family.getFather().getFullName() + " = " + family.getMother().getFullName());
            button.setAlignmentX(.5F);
            button.addActionListener(familyListener);
            button.setName(family.getUID().toString());
            parents.add(button);
        }
        spouses.removeAll();
        for (Metadata data : indi.getList(GEDTag.FAMS)) {
            final Family family;
            try {
                family = (Family)geneBase.getUniqueMeta(RecordType.FAM, data.getValue());
            } catch (NoUniqueMetaException ex) {
                return;
            }
            final HoverButton button = new HoverButton(family.getFather().getFullName() + " = " + family.getMother().getFullName());
            button.setAlignmentX(.5F);
            button.addActionListener(familyListener);
            button.setName(family.getUID().toString());
            spouses.add(button);
        }
        gedcPanel.dump(indi);
        right.revalidate();
        right.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        right = new javax.swing.JTabbedPane();
        detailScroll = new javax.swing.JScrollPane();
        details = new javax.swing.JPanel();
        fact = new javax.swing.JPanel();
        even = new javax.swing.JPanel();
        custom = new javax.swing.JPanel();
        parents = new javax.swing.JPanel();
        spouses = new javax.swing.JPanel();
        sour = new javax.swing.JPanel();
        gedcPanel = new com.undeadscythes.supergenes.gui.viewer.DumpSheet();
        left = new javax.swing.JPanel();
        hidden = new javax.swing.JPanel();
        show = new com.undeadscythes.swinglow.HoverButton();
        dirTabs = new javax.swing.JTabbedPane();
        javax.swing.JScrollPane dirScroll = new javax.swing.JScrollPane();
        dirAll = new javax.swing.JList();
        search = new javax.swing.JTextField();
        javax.swing.JLabel searchLabel = new javax.swing.JLabel();
        newIndi = new com.undeadscythes.swinglow.HoverButton();
        edit = new com.undeadscythes.swinglow.HoverButton();
        delete = new com.undeadscythes.swinglow.HoverButton();
        hide = new com.undeadscythes.swinglow.HoverButton();

        split.setDividerLocation(170);
        split.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        right.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fact.setBorder(javax.swing.BorderFactory.createTitledBorder("Facts"));
        fact.setLayout(new javax.swing.BoxLayout(fact, javax.swing.BoxLayout.PAGE_AXIS));

        even.setBorder(javax.swing.BorderFactory.createTitledBorder("Events"));
        even.setLayout(new javax.swing.BoxLayout(even, javax.swing.BoxLayout.PAGE_AXIS));

        custom.setBorder(javax.swing.BorderFactory.createTitledBorder("Other"));
        custom.setLayout(new javax.swing.BoxLayout(custom, javax.swing.BoxLayout.PAGE_AXIS));

        parents.setBorder(javax.swing.BorderFactory.createTitledBorder("Parents"));
        parents.setLayout(new javax.swing.BoxLayout(parents, javax.swing.BoxLayout.PAGE_AXIS));

        spouses.setBorder(javax.swing.BorderFactory.createTitledBorder("Spouses"));
        spouses.setLayout(new javax.swing.BoxLayout(spouses, javax.swing.BoxLayout.PAGE_AXIS));

        sour.setBorder(javax.swing.BorderFactory.createTitledBorder("Sources"));
        sour.setLayout(new javax.swing.BoxLayout(sour, javax.swing.BoxLayout.PAGE_AXIS));

        javax.swing.GroupLayout detailsLayout = new javax.swing.GroupLayout(details);
        details.setLayout(detailsLayout);
        detailsLayout.setHorizontalGroup(
            detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fact, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
            .addComponent(even, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(custom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(parents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(spouses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(sour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        detailsLayout.setVerticalGroup(
            detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsLayout.createSequentialGroup()
                .addComponent(fact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(even, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(custom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(parents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(spouses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(sour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        detailScroll.setViewportView(details);

        right.addTab("Details", detailScroll);
        right.addTab("GEDCOM", gedcPanel);

        split.setRightComponent(right);

        left.setLayout(new javax.swing.BoxLayout(left, javax.swing.BoxLayout.LINE_AXIS));

        show.setText(">");
        show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout hiddenLayout = new javax.swing.GroupLayout(hidden);
        hidden.setLayout(hiddenLayout);
        hiddenLayout.setHorizontalGroup(
            hiddenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hiddenLayout.createSequentialGroup()
                .addComponent(show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        hiddenLayout.setVerticalGroup(
            hiddenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hiddenLayout.createSequentialGroup()
                .addGap(0, 388, Short.MAX_VALUE)
                .addComponent(show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        left.add(hidden);
        hidden.setVisible(false);

        dirTabs.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        dirTabs.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        dirTabs.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        dirScroll.setBorder(null);
        dirScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        dirAll.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        dirAll.setValueIsAdjusting(true);
        dirScroll.setViewportView(dirAll);

        dirTabs.addTab("*", dirScroll);

        searchLabel.setText("Search:");

        newIndi.setText("New");
        newIndi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newIndiActionPerformed(evt);
            }
        });

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        delete.setText("Delete");

        hide.setText("<");
        hide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dirLayout = new javax.swing.GroupLayout(dir);
        dir.setLayout(dirLayout);
        dirLayout.setHorizontalGroup(
            dirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dirTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(dirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(dirLayout.createSequentialGroup()
                        .addComponent(searchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search))
                    .addGroup(dirLayout.createSequentialGroup()
                        .addComponent(newIndi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        dirLayout.setVerticalGroup(
            dirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dirLayout.createSequentialGroup()
                .addComponent(dirTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newIndi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLabel))
                .addContainerGap())
        );

        left.add(dir);

        split.setLeftComponent(left);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 531, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(split, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(split))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideActionPerformed
        hidden.setVisible(true);
        dir.setVisible(false);
        split.setDividerLocation(split.getMinimumDividerLocation());
    }//GEN-LAST:event_hideActionPerformed

    private void showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showActionPerformed
        hidden.setVisible(false);
        dir.setVisible(true);
        split.setDividerLocation(split.getMinimumDividerLocation());
    }//GEN-LAST:event_showActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed

    }//GEN-LAST:event_editActionPerformed

    private void newIndiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newIndiActionPerformed
        ((CloseableTabPane)getParent()).addCloseableTab("Individual*", new IndiSheet(geneBase, gui2));
    }//GEN-LAST:event_newIndiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel custom;
    private com.undeadscythes.swinglow.HoverButton delete;
    private javax.swing.JScrollPane detailScroll;
    private javax.swing.JPanel details;
    private final javax.swing.JPanel dir = new javax.swing.JPanel();
    private javax.swing.JList dirAll;
    private javax.swing.JTabbedPane dirTabs;
    private com.undeadscythes.swinglow.HoverButton edit;
    private javax.swing.JPanel even;
    private javax.swing.JPanel fact;
    private com.undeadscythes.supergenes.gui.viewer.DumpSheet gedcPanel;
    private javax.swing.JPanel hidden;
    private com.undeadscythes.swinglow.HoverButton hide;
    private javax.swing.JPanel left;
    private com.undeadscythes.swinglow.HoverButton newIndi;
    private javax.swing.JPanel parents;
    private javax.swing.JTabbedPane right;
    private javax.swing.JTextField search;
    private com.undeadscythes.swinglow.HoverButton show;
    private javax.swing.JPanel sour;
    private final javax.swing.JSplitPane split = new javax.swing.JSplitPane();
    private javax.swing.JPanel spouses;
    // End of variables declaration//GEN-END:variables
}
