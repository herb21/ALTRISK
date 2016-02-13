/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import com.alee.laf.WebLookAndFeel;
import static conceptium.biz.IncidentDetails.cboReference;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MathomeTD
 */
public class IncidentCorrectiveActionStatusUpdate extends javax.swing.JFrame {

    /**
     * Creates new form IncidentCorrectiveActionStatusUpdate
     */
    private static IncidentCorrectiveActionStatusUpdate obj = null;
    private IncidentCorrectiveActionStatusUpdate() {;
        setResizable(false);
        setUndecorated(true);
        initComponents();
        reference();
        
    }
public static IncidentCorrectiveActionStatusUpdate getObj(){
            if (obj == null){
                obj = new IncidentCorrectiveActionStatusUpdate();
            }
            return obj;
        }

   private void reference(){
        String sql ="Select * from Incident";
            try {
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String ID = rs.getString("ReferenceNumber");
                    cboReference.addItem(ID.trim());
                }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
   
      private void hierachy(){
        String search = cboReference.getSelectedItem().toString();
          String sql ="Select Hierachy,Hierachy1,Hierachy2,Hierachy3,Hierachy4,Hierachy5 "+
                  "from CorrectiveAction where ReferenceNumber = ?";
            try {
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, search);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String ID = rs.getString("Hierachy");
                    cboHierachy.addItem(ID.trim());
                }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboReference = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtFeedback = new javax.swing.JTextArea();
        txtAllocatedTask = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        statusCompleted = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        txtUpload = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        statusPending = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        cboHierachy = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        txtReason2 = new javax.swing.JTextField();
        txtReason4 = new javax.swing.JTextField();
        txtReason1 = new javax.swing.JTextField();
        txtReason3 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Reference Number:");

        jLabel2.setText("Allocated Task:");

        txtFeedback.setColumns(20);
        txtFeedback.setRows(5);
        jScrollPane1.setViewportView(txtFeedback);

        txtAllocatedTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAllocatedTaskActionPerformed(evt);
            }
        });

        jLabel3.setText("FeedBack:");

        jLabel4.setText("Status:");

        statusCompleted.setText("Completed and waiting authorisation");
        statusCompleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusCompletedActionPerformed(evt);
            }
        });

        jLabel5.setText("Supporting Docs:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/conceptium/biz/upload58.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Responsible Person:");

        statusPending.setText("Pending (work in progress)");
        statusPending.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                statusPendingItemStateChanged(evt);
            }
        });
        statusPending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusPendingActionPerformed(evt);
            }
        });

        jLabel7.setText("Hierachy Type:");

        cboHierachy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Eliminate", "Substistute", "Seperate", "Redesign", "Administrate", "PPE", " ", " " }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Reason for Pending"));

        txtReason2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReason2ActionPerformed(evt);
            }
        });

        txtReason4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReason4ActionPerformed(evt);
            }
        });

        txtReason3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReason3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtReason4, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(txtReason3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReason2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReason1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtReason1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReason2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReason3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReason4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel8.setText("Due Date:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(statusCompleted)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statusPending))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(txtAllocatedTask)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboHierachy, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboReference, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cboHierachy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAllocatedTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUpload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusPending)
                    .addComponent(statusCompleted)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/save.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/close.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /**String sql = "insert into StatusUpdate()values()";
        String reference = cboReference.getSelectedItem().toString();
        String name = txtName.getText();
        String task = txtAllocatedTask.getText();
        String feedback = txtFeedback.getText();
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, reference);
            pst.setString(2, name);
            pst.setString(3, task);
            pst.setString(4, feedback);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(StatusUpdate.this, e);
        }
        String SHE = "herbert@conceptium.biz";
        Properties props = new Properties();
        //props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.host", "mail.conceptium.biz");
        //props.put("mail.smtp.socketFactory.port", "465");
        //props.put("mail.smtp.SocketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
             return new PasswordAuthentication("herbert@conceptium.biz", "Kaylad1*#");
                    }
              });
        try {
            
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("herbert@conceptium.biz"));
            //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(GroupA));
            msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse("herbertd@mathometd.com,molokgobi@yahoo.com,herbert@conceptium.biz"));
            msg.setSubject("Learnings from Incident" + task);
            msg.setText("Dear" +" "+SHE+" "+ "Please fing update for"+" "+reference+" " +"Awaiting you authorisation."+
                    " "+ "  " + "\n"+"\n"+"Regards" +"\n"+" "+" Kaylida SHE Management System");
            Transport.send(msg);
            
            JOptionPane.showMessageDialog(StatusUpdate.this, "Record successfully saved and update sent"+" " + SHE + "." );
        }
        catch(MessagingException | HeadlessException e){
            JOptionPane.showMessageDialog(StatusUpdate.this, e);
        }**/
        if(statusCompleted.isSelected()){
            Corrective corrective = Corrective.getObj();
            String reference = cboReference.getSelectedItem().toString();
            String hierachy = cboHierachy.getSelectedItem().toString();
            String responsiblePerson = txtName.getText();
            Date dueDate = new java.sql.Date(jDateChooser1.getDate().getTime());
            String allocatedTask = txtAllocatedTask.getText();
            /**String sql = "select * from CorrectiveAction where ReferenceNumber = ? and Hierachy = ?";
            try{
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, reference);
                pst.setString(2, hierachy);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                String action = rs.getString("Action");
                String resonsiblePerson = rs.getString("ResponsiblePerson");
                Date dueDate = rs.getDate("DueDate");
                corrective.txtAction.setText(action);
                corrective.cboChoice.setSelectedItem(hierachy);
                corrective.cboName.setSelectedItem(resonsiblePerson);
                corrective.jDateChooser2.setDate(dueDate);
                
                }
                
            }
            catch(Exception e){
            JOptionPane.showMessageDialog(StatusUpdate.this, e);
            }**/


            SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formatter = myFormat.format(jDateChooser1.getDate());
            DefaultTableModel model = (DefaultTableModel) Corrective.jTable1.getModel();
            model.setValueAt(txtAllocatedTask.getText(), Corrective.jTable1.getSelectedRow(),0);
            model.setValueAt(cboHierachy.getSelectedItem().toString(), Corrective.jTable1.getSelectedRow(),1);
            model.setValueAt(txtName.getText(), Corrective.jTable1.getSelectedRow(),2);
            model.setValueAt(formatter, Corrective.jTable1.getSelectedRow(),3);
            model.setValueAt(choice, Corrective.jTable1.getSelectedRow(),4);
            
            this.dispose();
        }
        
        else{
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void txtAllocatedTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAllocatedTaskActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAllocatedTaskActionPerformed

    private void statusCompletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusCompletedActionPerformed
        choice = "Closed";
        if(statusCompleted.isSelected()){
            statusPending.setEnabled(false);
        }
        else{
            statusPending.setEnabled(true);
        }
    }//GEN-LAST:event_statusCompletedActionPerformed

    private void statusPendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusPendingActionPerformed
        choice = "pending";
        if(!statusPending.isSelected()){
        txtReason1.setEnabled(false);
        txtReason2.setEnabled(false);
        txtReason3.setEnabled(false);
        txtReason4.setEnabled(false);
        txtReason1.setText("");
        txtReason2.setText("");
        txtReason3.setText("");
        txtReason4.setText("");
        }
    }//GEN-LAST:event_statusPendingActionPerformed

    private void txtReason3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReason3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReason3ActionPerformed

    private void statusPendingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_statusPendingItemStateChanged
        if(statusPending.isSelected()){
        txtReason1.setEnabled(true);
        txtReason2.setEnabled(true);
        txtReason3.setEnabled(true);
        txtReason4.setEnabled(true);
        }
        
    }//GEN-LAST:event_statusPendingItemStateChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        txtReason1.setEnabled(false);
        txtReason2.setEnabled(false);
        txtReason3.setEnabled(false);
        txtReason4.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtReason4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReason4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReason4ActionPerformed

    private void txtReason2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReason2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReason2ActionPerformed

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
            java.util.logging.Logger.getLogger(IncidentCorrectiveActionStatusUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IncidentCorrectiveActionStatusUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IncidentCorrectiveActionStatusUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IncidentCorrectiveActionStatusUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IncidentCorrectiveActionStatusUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cboHierachy;
    public static javax.swing.JComboBox cboReference;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public static com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox statusCompleted;
    private javax.swing.JCheckBox statusPending;
    public static javax.swing.JTextField txtAllocatedTask;
    private javax.swing.JTextArea txtFeedback;
    public static javax.swing.JTextField txtName;
    private javax.swing.JTextField txtReason1;
    private javax.swing.JTextField txtReason2;
    private javax.swing.JTextField txtReason3;
    private javax.swing.JTextField txtReason4;
    private javax.swing.JTextField txtUpload;
    // End of variables declaration//GEN-END:variables
private String choice;
}
