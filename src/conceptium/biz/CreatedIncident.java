/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.decorator.PatternPredicate;
import org.jdesktop.swingx.decorator.ShadingColorHighlighter;

/**
 *
 * @author MathomeTD
 */
public class CreatedIncident extends javax.swing.JFrame {

    /**
     * Creates new form CreatedIncident
     */
    private static CreatedIncident obj = null;
    public CreatedIncident() {
        setUndecorated(true);
        setResizable(false);
        initComponents();
        updateTable();
        org.jdesktop.swingx.decorator.Highlighter simpleStriping = HighlighterFactory.createSimpleStriping();
        PatternPredicate patternPredicate = new PatternPredicate("Above R50 000",7,7);
        ColorHighlighter magenta = new ColorHighlighter(patternPredicate, Color.red, Color.black, Color.LIGHT_GRAY, Color.black);
        ShadingColorHighlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(2));
        
        jXTable1.setHighlighters(simpleStriping,magenta,shading);
    }
    private void updateTable(){
       SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
       Date date = new Date();
       String now = format.format(date);
       String sql = "Select ReferenceNumber,Name,Surname,Supervisor,Site,Department,IncidentType,NatureOfIncident"+
               ",DateOfIncident,DueDate from Incident inner join IncidentDetail on ReferenceNumber = Reference where DateOfReportingIncident = '"+now+"'";
       try {
           Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
           PreparedStatement pst = con.prepareStatement(sql);
           ResultSet rs = pst.executeQuery();
           jXTable1.setModel(DbUtils.resultSetToTableModel(rs)); 
       }
       catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
       }
}
public static CreatedIncident getObj(){
            if (obj == null){
                obj = new CreatedIncident();
            }
            return obj;
        }
      /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        rateRisk = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        analysizeIncident = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        deleteIncident = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        refreshIncidents = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable(){public boolean isCellEditable(int row, int column){
            return false;}};
    jButton1 = new javax.swing.JButton();

    jPopupMenu1.add(jSeparator4);

    rateRisk.setText("Rate Risk");
    jPopupMenu1.add(rateRisk);
    jPopupMenu1.add(jSeparator3);

    analysizeIncident.setText("Analysize Risk");
    analysizeIncident.setActionCommand("analysizeRisk");
    jPopupMenu1.add(analysizeIncident);
    jPopupMenu1.add(jSeparator2);

    deleteIncident.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/delete.png"))); // NOI18N
    deleteIncident.setText("Delete Incident");
    deleteIncident.setActionCommand("DeleteIncident");
    jPopupMenu1.add(deleteIncident);
    jPopupMenu1.add(jSeparator1);

    refreshIncidents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/load9.png"))); // NOI18N
    refreshIncidents.setText("Refresh");
    refreshIncidents.setActionCommand("refreshIncidents");
    jPopupMenu1.add(refreshIncidents);
    jPopupMenu1.add(jSeparator5);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setAlwaysOnTop(true);

    jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jXTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jXTable1MouseReleased(evt);
        }
    });
    jScrollPane1.setViewportView(jXTable1);

    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/close.png"))); // NOI18N
    jButton1.setText("Close");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
            .addGap(0, 0, 0))
        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jXTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable1MouseReleased
        jXTable1.setComponentPopupMenu(jPopupMenu1);
        if (evt.isPopupTrigger())
        {
            jPopupMenu1.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jXTable1MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus locok and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreatedIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreatedIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreatedIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreatedIncident.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreatedIncident().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem analysizeIncident;
    private javax.swing.JMenuItem deleteIncident;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private org.jdesktop.swingx.JXTable jXTable1;
    private javax.swing.JMenuItem rateRisk;
    private javax.swing.JMenuItem refreshIncidents;
    // End of variables declaration//GEN-END:variables
}
