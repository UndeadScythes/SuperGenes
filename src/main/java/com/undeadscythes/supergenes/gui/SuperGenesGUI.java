package com.undeadscythes.supergenes.gui;

import com.undeadscythes.authenticmd.exception.TerminationRequest;
import com.undeadscythes.genebase.GeneBase;
import com.undeadscythes.supergenes.SuperGenes;
import com.undeadscythes.supergenes.SuperGenesHeader;
import com.undeadscythes.supergenes.gui.listener.GeneBaseListener;
import com.undeadscythes.supergenes.service.*;
import com.undeadscythes.swinglow.CloseableTab;
import com.undeadscythes.tipscript.TipRedirect;
import java.awt.EventQueue;
import java.util.Enumeration;
import javax.swing.*;

/**
 * The main GUI window to serve the {@link SuperGenes} services.
 *
 * @author UndeadScythes
 */
public class SuperGenesGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private final SuperGenes program;
    private final TipRedirect out;
    private final GeneBaseListener geneBaseListener = new GeneBaseListener(this);
    private GeneBase currentGeneBase = GeneBase.NULL_GENEBASE;
    private GeneBaseControl control;

    /**
     * Creates a new SuperGenesGUI form.
     */
    public SuperGenesGUI(final SuperGenes program, final TipRedirect out) {
        this.program = program;
        this.out = out;
        setLookAndFeel("Windows");
        initComponents();
        for (final GeneBase geneBase : program.getGeneBases()) {
            addGeneBase(geneBase);
        }
        if (program.getDefault() != null) {
            switchGeneBase(program.getDefault().getUID().toString());
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    private void setLookAndFeel(final String name) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (name.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            footer.setText(ex.getMessage());
        } catch (InstantiationException ex) {
            footer.setText(ex.getMessage());
        } catch (IllegalAccessException ex) {
            footer.setText(ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
            footer.setText(ex.getMessage());
        }
    }

    /**
     * Add and select a new component in the main tabbed section of the form.
     */
    public final void addTab(final String title, final JPanel panel) {
        main.addTab(title, panel);
        main.setSelectedComponent(panel);
    }

    /**
     * Remove a component from the main tabbed section of the form.
     */
    public void removeTab(final JPanel panel) {
        main.remove(panel);
    }

    private void clearTitles() {
        setTitle("SuperGenes");
        footer.setText("");
    }

    private JRadioButtonMenuItem getSelectedMenuItem() {
        final Enumeration<AbstractButton> buttons = groupGeneBases.getElements();
        while (buttons.hasMoreElements()) {
            final JRadioButtonMenuItem button = (JRadioButtonMenuItem)buttons.nextElement();
            if (button.isSelected()) {
                return button;
            }
        }
        return null;
    }

    /**
     * Close the current GeneBase.
     */
    public void closeGeneBase() {
        main.removeAll();
        geneBases.remove(getSelectedMenuItem());
        if (geneBases.getMenuComponents().length == 1) {
            noGeneBases.setVisible(true);
        }
        currentGeneBase = GeneBase.NULL_GENEBASE;
        clearTitles();
    }

    private JRadioButtonMenuItem addGeneBase(final GeneBase geneBase) {
        final JRadioButtonMenuItem item = new JRadioButtonMenuItem(geneBase.getUID().toString());
        item.addActionListener(geneBaseListener);
        groupGeneBases.add(item);
        geneBases.add(item);
        noGeneBases.setVisible(false);
        return item;
    }

    /**
     * Open a given {@link GeneBase} into the main window.
     */
    public final void switchGeneBase(final String name) {
        try {
            new Auto().run(program, name);
        } catch (TerminationRequest ex) {}
        if (program.getDefault().isNull()) {
            footer.setText(out.getOutput());
            return;
        }
        out.flush();
        currentGeneBase = program.getDefault();
        footer.setText("Opening GeneBase '" + currentGeneBase.getUID() + "'...");
        footer.pulse();
        setTitle("SuperGenes - " + currentGeneBase.getUID().toString());
        main.removeAll();
        control = new GeneBaseControl(currentGeneBase, this);
        main.addTab(currentGeneBase.getUID().toString(), control);
        footer.setText("Opened GeneBase '" + currentGeneBase.getUID() + "'.");
        footer.done();
    }

    /**
     * Load a {@link GeneBase} from a
     * {@link com.undeadscythes.genebase.gedcom.GEDCOM} specified by the user.
     */
    public void openGeneBase() {
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(GEDFilter.FILTER);
        if (chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) return;
        footer.setText("Loading GEDCOM...");
        footer.pulse();
        final Load load = new Load();
        try {
            load.run(program, chooser.getSelectedFile().getPath());
        } catch (TerminationRequest ex) {}
        final String message = out.getOutput();
        final GeneBase loaded = load.getGeneBase();
        if (loaded != null) {
            addGeneBase(loaded).setSelected(true);
            switchGeneBase(loaded.getUID().toString());
        }
        footer.done();
        footer.setText(message);
    }

    /**
     * Create a new blank GeneBase.
     */
    public void newGeneBase() {
        final GeneBase geneBase = new GeneBase();
        geneBase.add(SuperGenesHeader.getStandard());
        program.addGeneBase(geneBase);
        addGeneBase(geneBase).setSelected(true);
        switchGeneBase(geneBase.getUID().toString());
    }

    /**
     * Save the current GeneBase to file with a user chosen name.
     */
    public void saveAs() {
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(GEDFilter.FILTER);
        if (chooser.showSaveDialog(this) == JFileChooser.CANCEL_OPTION) return;
        if (chooser.getSelectedFile().exists() && JOptionPane.showConfirmDialog(this, "Overwrite file?") != JOptionPane.YES_OPTION) return;
        save(chooser.getSelectedFile().getPath());
        final String newName = chooser.getSelectedFile().getName().replace(".ged", "");
        getSelectedMenuItem().setText(newName);
        try {
            new Rename().run(program, newName);
        } catch (TerminationRequest ex) {}
        setTitle("SuperGenes - " + newName);
        control.rename(newName);
    }

    /**
     * Save the current GeneBase to file.
     *
     * @param path If the given path is empty, "", then the default file path is
     * used.
     */
    public void save(final String path) {
        footer.setText("Saving GEDCOM...");
        footer.pulse();
        try {
            new Save().run(program, path);
        } catch (TerminationRequest ex) {}
        footer.done();
        footer.setText(out.getOutput());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupGeneBases = new javax.swing.ButtonGroup();
        main = new com.undeadscythes.swinglow.CloseableTabPane();
        final com.undeadscythes.supergenes.gui.Welcome welcome = new com.undeadscythes.supergenes.gui.Welcome(this);
        footer = new com.undeadscythes.swinglow.DetailPane();
        javax.swing.JMenuBar menu = new javax.swing.JMenuBar();
        javax.swing.JMenu file = new javax.swing.JMenu();
        javax.swing.JMenuItem newFile = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator sep1 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem open = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator sep2 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem save = new javax.swing.JMenuItem();
        javax.swing.JMenuItem saveAs = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator sep3 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem close = new javax.swing.JMenuItem();
        javax.swing.JPopupMenu.Separator sep4 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem quit = new javax.swing.JMenuItem();
        javax.swing.JMenu tools = new javax.swing.JMenu();
        javax.swing.JMenuItem fixMarr = new javax.swing.JMenuItem();
        javax.swing.JMenuItem ResToCen = new javax.swing.JMenuItem();
        geneBases = new javax.swing.JMenu();
        noGeneBases = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SuperGenes");
        setMinimumSize(new java.awt.Dimension(640, 480));

        main.setFocusable(false);
        main.addTab("Welcome", welcome);
        main.setTabComponentAt(main.indexOfComponent(welcome), new CloseableTab(main, "Welcome"));

        getContentPane().add(main, java.awt.BorderLayout.CENTER);
        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        file.setText("File");

        newFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/new.png"))); // NOI18N
        newFile.setText("New...");
        newFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAction(evt);
            }
        });
        file.add(newFile);
        file.add(sep1);

        open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        open.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/load.png"))); // NOI18N
        open.setText("Open...");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openAction(evt);
            }
        });
        file.add(open);
        file.add(sep2);

        save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/save.png"))); // NOI18N
        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAction(evt);
            }
        });
        file.add(save);

        saveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAs.setText("Save As...");
        saveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsAction(evt);
            }
        });
        file.add(saveAs);
        file.add(sep3);

        close.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/close.png"))); // NOI18N
        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeAction(evt);
            }
        });
        file.add(close);
        file.add(sep4);

        quit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/quit.png"))); // NOI18N
        quit.setText("Quit");
        quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitAction(evt);
            }
        });
        file.add(quit);

        menu.add(file);

        tools.setText("Tools");

        fixMarr.setText("Link Marriages");
        fixMarr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runFixMarr(evt);
            }
        });
        tools.add(fixMarr);

        ResToCen.setText("Convert Census");
        ResToCen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runResToCen(evt);
            }
        });
        tools.add(ResToCen);

        menu.add(tools);

        geneBases.setText("GeneBases");

        noGeneBases.setText("No GeneBases Loaded");
        noGeneBases.setEnabled(false);
        geneBases.add(noGeneBases);

        menu.add(geneBases);

        setJMenuBar(menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitAction
        dispose();
    }//GEN-LAST:event_quitAction

    private void openAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openAction
        openGeneBase();
    }//GEN-LAST:event_openAction

    private void newAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAction
        newGeneBase();
    }//GEN-LAST:event_newAction

    private void saveAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAction
        if (currentGeneBase.getGEDCOM().getFileName().isEmpty()) {
            saveAs();
        } else {
            save("");
        }
    }//GEN-LAST:event_saveAction

    private void saveAsAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsAction
        saveAs();
    }//GEN-LAST:event_saveAsAction

    private void closeAction(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeAction
        closeGeneBase();
    }//GEN-LAST:event_closeAction

    private void runFixMarr(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runFixMarr
        footer.setText("Fixing marriages...");
        footer.pulse();
        try {
            new FixMarr().run(program, "");
        } catch (TerminationRequest ex) {}
        footer.done();
        footer.setText(out.getOutput());
    }//GEN-LAST:event_runFixMarr

    private void runResToCen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runResToCen
        footer.setText("Converting residences to censuses...");
        footer.pulse();
        try {
            new ResToCen().run(program, "");
        } catch (TerminationRequest ex) {}
        footer.done();
        footer.setText(out.getOutput());
    }//GEN-LAST:event_runResToCen

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.undeadscythes.swinglow.DetailPane footer;
    private javax.swing.JMenu geneBases;
    private javax.swing.ButtonGroup groupGeneBases;
    private com.undeadscythes.swinglow.CloseableTabPane main;
    private javax.swing.JMenuItem noGeneBases;
    // End of variables declaration//GEN-END:variables
}
