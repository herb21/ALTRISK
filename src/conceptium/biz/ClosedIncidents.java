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
public class ClosedIncidents extends javax.swing.JFrame {

    /**
     * Creates new form ClosedIncidents
     */
    private static ClosedIncidents obj = null;
    private ClosedIncidents() {
        setUndecorated(true);
        setResizable(false);
        initComponents();
        updateTable();
        jXTable1.setSortable(false);
        jXTable1.setToolTipText("hit cmd + f on mac to search data or Ctl + f on windows");
        org.jdesktop.swingx.decorator.Highlighter simpleStriping = HighlighterFactory.createSimpleStriping();
        PatternPredicate patternPredicate = new PatternPredicate("Above R50 000",4,4);
        //PatternPredicate patternPredicate1 = new PatternPredicate("Fatality",4,4);
        ColorHighlighter magenta = new ColorHighlighter(patternPredicate, Color.red, Color.black, Color.LIGHT_GRAY, Color.black);
        //ColorHighlighter magenta1 = new ColorHighlighter(patternPredicate1, Color.red, Color.black, Color.LIGHT_GRAY, Color.black);
        ShadingColorHighlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(4));
        
        jXTable1.setHighlighters(simpleStriping,magenta,shading);
        //jXTable1.setHighlighters(simpleStriping,magenta1,shading);
    }
private void updateTable(){
       String status = "Closed";
       String sql = "Select Name,Surname,ReferenceNumber,IncidentType,NatureOfIncident,DateOfIncident,"+
               "DateOfReportingIncident,ReportedTo,Department,Site,ClosingDate from Incident where status = ? ";
       try {
           Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
           PreparedStatement pst = con.prepareStatement(sql);
           pst.setString(1, status);
           ResultSet rs = pst.executeQuery();
           jXTable1.setModel(DbUtils.resultSetToTableModel(rs)); 
       }
       catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
       }
}
public static ClosedIncidents getObj(){
            if (obj == null){
                obj = new ClosedIncidents();
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
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        printReport = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        refreshData = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable(){public boolean isCellEditable(int row, int column){
            return false;}};
    jPanel2 = new javax.swing.JPanel();
    jPanel3 = new javax.swing.JPanel();
    jButton1 = new javax.swing.JButton();

    jPopupMenu1.add(jSeparator3);

    printReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/print.png"))); // NOI18N
    printReport.setText("Print Report");
    jPopupMenu1.add(printReport);
    jPopupMenu1.add(jSeparator1);

    refreshData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/load9.png"))); // NOI18N
    refreshData.setText("Refresh Data");
    refreshData.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            refreshDataActionPerformed(evt);
        }
    });
    jPopupMenu1.add(refreshData);
    jPopupMenu1.add(jSeparator2);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setAlwaysOnTop(true);

    jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jXTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jXTable1MouseReleased(evt);
        }
    });
    jScrollPane2.setViewportView(jXTable1);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2)
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
    );

    jPanel2.setBackground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 53, Short.MAX_VALUE)
    );

    jPanel3.setBackground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 26, Short.MAX_VALUE)
    );

    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/close.png"))); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 1090, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jButton1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0))
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

    private void refreshDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshDataActionPerformed
        updateTable();
    }//GEN-LAST:event_refreshDataActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(ClosedIncidents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClosedIncidents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClosedIncidents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClosedIncidents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClosedIncidents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private org.jdesktop.swingx.JXTable jXTable1;
    private javax.swing.JMenuItem printReport;
    private javax.swing.JMenuItem refreshData;
    // End of variables declaration//GEN-END:variables
}
