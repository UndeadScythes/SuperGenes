package com.undeadscythes.supergenes.gui.viewer;

import com.undeadscythes.genebase.record.Individual;
import com.undeadscythes.genebase.specific.Gender;
import com.undeadscythes.supergenes.gui.Extend;
import com.undeadscythes.supergenes.gui.GeneBaseControl;
import java.awt.Color;
import java.awt.Cursor;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Displays basic information about an {@link Individual}.
 *
 * @author UndeadScythes
 */
public class IndiCard extends JPanel {
    private static final Map<Gender, Color> COLORS = new EnumMap<Gender, Color>(Gender.class);
    private static final Map<Gender, Color> HIGHLIGHTS = new EnumMap<Gender, Color>(Gender.class);

    static {
        COLORS.put(Gender.MALE, Color.getHSBColor(.65F, .2F, 1F));
        COLORS.put(Gender.FEMALE, Color.getHSBColor(0F, .2F, 1F));
        COLORS.put(Gender.UNKNOWN, Color.getHSBColor(0F, 0F, .8F));
        HIGHLIGHTS.put(Gender.MALE, Color.getHSBColor(.65F, .4F, 1F));
        HIGHLIGHTS.put(Gender.FEMALE, Color.getHSBColor(0F, .4F, 1F));
        HIGHLIGHTS.put(Gender.UNKNOWN, Color.getHSBColor(0F, 0F, .7F));
    }

    private static final long serialVersionUID = 1L;

    private final boolean linked;

    private final String uid;
    private final GeneBaseControl container;
    private final Gender gender;

    /**
     * Creates a new IndiCard about a given {@link Individual}.
     */
    public IndiCard(final GeneBaseControl container, final Individual indi) {
        uid = indi.getUID().toString();
        this.container = container;
        gender = indi.getGender();
        initComponents();
        linked = indi.equals(Individual.UNKNOWN) ^ true;
        labelGivenName.setText(indi.getGivenName());
        labelFamilyName.setText(indi.getFamilyName());
        labelDates.setText(indi.getBirth().year + " - " + indi.getDeath().year);
        panelHolder.setBackground(COLORS.get(gender));
        buttonAnce.setVisible(false);
        buttonDesc.setVisible(false);
    }

    /**
     * Creates a new IndiCard about a given {@link Individual} with extension
     * buttons.
     */
    public IndiCard(final GeneBaseControl container, final Individual indi, final Extend extend) {
        this(container, indi);
        if (extend.equals(Extend.UP)) {
            buttonAnce.setVisible(false);
        } else if(extend.equals(Extend.DOWN)) {
            buttonDesc.setVisible(false);
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHolder = new javax.swing.JPanel();
        labelGivenName = new javax.swing.JLabel();
        labelFamilyName = new javax.swing.JLabel();
        labelDates = new javax.swing.JLabel();
        javax.swing.JPanel panelAnce = new javax.swing.JPanel();
        buttonAnce = new javax.swing.JButton();
        javax.swing.JPanel panelDesc = new javax.swing.JPanel();
        buttonDesc = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        panelHolder.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panelHolder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelHolderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelHolderMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelHolderMouseReleased(evt);
            }
        });

        labelGivenName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelGivenName.setText("<Given name>");
        labelGivenName.setAlignmentX(0.5F);

        labelFamilyName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFamilyName.setText("<Family Name>");
        labelFamilyName.setAlignmentX(0.5F);
        labelFamilyName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelDates.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDates.setText("<Birth - Death>");
        labelDates.setAlignmentX(0.5F);
        labelDates.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelHolderLayout = new javax.swing.GroupLayout(panelHolder);
        panelHolder.setLayout(panelHolderLayout);
        panelHolderLayout.setHorizontalGroup(
            panelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHolderLayout.createSequentialGroup()
                .addGroup(panelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelFamilyName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDates, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(labelGivenName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        panelHolderLayout.setVerticalGroup(
            panelHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelHolderLayout.createSequentialGroup()
                .addComponent(labelGivenName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelFamilyName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDates)
                .addGap(0, 0, 0))
        );

        add(panelHolder, java.awt.BorderLayout.CENTER);

        buttonAnce.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/up.png"))); // NOI18N
        buttonAnce.setContentAreaFilled(false);
        buttonAnce.setFocusable(false);
        buttonAnce.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/up-press.png"))); // NOI18N
        buttonAnce.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/up-hover.png"))); // NOI18N

        javax.swing.GroupLayout panelAnceLayout = new javax.swing.GroupLayout(panelAnce);
        panelAnce.setLayout(panelAnceLayout);
        panelAnceLayout.setHorizontalGroup(
            panelAnceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonAnce, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );
        panelAnceLayout.setVerticalGroup(
            panelAnceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnceLayout.createSequentialGroup()
                .addComponent(buttonAnce)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(panelAnce, java.awt.BorderLayout.PAGE_START);

        buttonDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/down.png"))); // NOI18N
        buttonDesc.setContentAreaFilled(false);
        buttonDesc.setFocusable(false);
        buttonDesc.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/down-press.png"))); // NOI18N
        buttonDesc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/down-hover.png"))); // NOI18N

        javax.swing.GroupLayout panelDescLayout = new javax.swing.GroupLayout(panelDesc);
        panelDesc.setLayout(panelDescLayout);
        panelDescLayout.setHorizontalGroup(
            panelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonDesc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        );
        panelDescLayout.setVerticalGroup(
            panelDescLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDescLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonDesc))
        );

        add(panelDesc, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void panelHolderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHolderMouseEntered
        if (linked) {
            panelHolder.setBackground(HIGHLIGHTS.get(gender));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_panelHolderMouseEntered

    private void panelHolderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHolderMouseExited
        if (linked) {
            panelHolder.setBackground(COLORS.get(gender));
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_panelHolderMouseExited

    private void panelHolderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHolderMouseReleased
        if (linked) {
            container.openFamilyFromIndividual(uid);
        }
    }//GEN-LAST:event_panelHolderMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnce;
    private javax.swing.JButton buttonDesc;
    private javax.swing.JLabel labelDates;
    private javax.swing.JLabel labelFamilyName;
    private javax.swing.JLabel labelGivenName;
    private javax.swing.JPanel panelHolder;
    // End of variables declaration//GEN-END:variables
}
