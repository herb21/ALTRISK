/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import com.alee.laf.WebLookAndFeel;
import java.sql.*;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author MathomeTD
 */
public class IncidentDetails extends javax.swing.JFrame {

    /**
     * Creates new form IncidentDetails
     */
    
    private static IncidentDetails obj = null;
    private IncidentDetails() {
        setUndecorated(true);
        setResizable(true);
        initComponents();
        fillId();
        reference();
        //txtDate.setEnabled(false);
        //dueDate();
    }
    
    
public static IncidentDetails getObj(){
            if (obj == null){
                obj = new IncidentDetails();
            }
            return obj;
        }
   private void fillId(){
        String sql ="Select * from Persons";
            try {
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String ID = rs.getString("EmployeeNumber");
                    txtEmployeeNumber.setText(ID.trim());
                    String name = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    cboSupervisorOnDuty.addItem(name.trim()+" "+surname.trim());
                }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
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
     /**private void dueDate(){
        String search = cboReference.getSelectedItem().toString();
        String sql ="Select * from Incidendz where referenceNumber = ?";
            try {
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, search);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    Date dateDue = rs.getDate("DateOfReportingIncident");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd",Locale.ENGLISH);
                Calendar c = Calendar.getInstance();
                //c.setTime(new Date());
                c.setTime(dateDue);
                //c.setTime(sdf.parse(dateChooserCombo1.getSelectedDate().toString()));
                //int x = Integer.parseInt(txtDays.getText());
                c.add(Calendar.DATE, 1);
                //SimpleDateFormat print = new SimpleDateFormat("yyyy/MM/dd");
                txtDate.setText(sdf.format(c.getTime()));
                txtDate.setEnabled(false);
                }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }**/
      private void incidentDetails(){
        String search = cboReference.getSelectedItem().toString();
        String sql ="Select * from IncidentDetails where Reference = ?";
            try {
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, search);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    String description = rs.getString("Description");
                    txtDescription.setText(description.trim());
                    String details = rs.getString("Details");
                    txtDetails.setText(details);
                    String incident = rs.getString("Incident");
                    txtIncident.setText(incident);
                    String supervisor = rs.getString("Supervisor");
                    cboSupervisorOnDuty.setSelectedItem(supervisor);
                    String employment = rs.getString("EmployeeNumber");
                    txtEmployeeNumber.setText(employment);
                    if(rs.getString("Description").isEmpty()){
                        JOptionPane.showMessageDialog(IncidentDetails.this, "No record found");
                    }
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
        jPanel4 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDetails = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtIncident = new javax.swing.JTextArea();
        cboSupervisorOnDuty = new javax.swing.JComboBox();
        jLabel30 = new javax.swing.JLabel();
        txtEmployeeNumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        cboReference = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel24.setText("Description of the HSECQ Incident:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        jLabel25.setText("Who, when, what, where and how");

        jLabel26.setText("Details of Injury / Damage / Impact:");

        jLabel27.setText("(Immediate Action taken by Line) ");

        jLabel28.setText("Supervisor on Duty:");

        jLabel29.setText("Management following incident:");

        txtDetails.setColumns(20);
        txtDetails.setRows(5);
        jScrollPane3.setViewportView(txtDetails);

        txtIncident.setColumns(20);
        txtIncident.setRows(5);
        jScrollPane4.setViewportView(txtIncident);

        cboSupervisorOnDuty.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSupervisorOnDutyItemStateChanged(evt);
            }
        });

        jLabel30.setText("Employee Number:");

        jLabel2.setText("Due Date:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel29)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel25)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cboSupervisorOnDuty, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboSupervisorOnDuty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel28)
                        .addComponent(jLabel30)
                        .addComponent(txtEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cboReference.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboReferenceItemStateChanged(evt);
            }
        });
        cboReference.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboReferenceActionPerformed(evt);
            }
        });

        jLabel1.setText("Reference Number:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboReference, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboReference, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(4, 4, 4)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/save.png"))); // NOI18N
        jButton1.setText("Add incident Details and Notify Management");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/close.png"))); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("<<");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 52, Short.MAX_VALUE)
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
            .addGap(0, 24, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String description = txtDescription.getText();
        String details = txtDetails.getText();
        String incident = txtIncident.getText();
        String reference = cboReference.getSelectedItem().toString();
        String supervisor = cboSupervisorOnDuty.getSelectedItem().toString();
        String employeeNumber = txtEmployeeNumber.getText();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        
        String sql = "insert into IncidentDetail(Description,Details,Incident,Reference,Supervisor,"+
                "EmployeeNumber,DueDate)values(?,?,?,?,?,?,?)";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, description);
            pst.setString(2, details);
            pst.setString(3, incident);
            pst.setString(4, reference);
            pst.setString(5, supervisor);
            pst.setString(6, employeeNumber);
            pst.setDate(7, new java.sql.Date(jDateChooser1.getDate().getTime()));
            pst.executeUpdate();
            //JOptionPane.showMessageDialog(IncidentDetails.this, "Incident details successfully save");
        }
        catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(IncidentDetails.this, e);
        }
        Properties props = new Properties();
        props.put("mail.smtp.host", "mail.conceptium.biz");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication("herbert@conceptium.biz", "Kaylad1*#");
                    }
                });
        String GroupA = "herbert@conceptium.biz";
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("herbert@conceptium.biz"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(GroupA));
            //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("herbertd@mathometd"));
            msg.setSubject("Incident Occurred");
            msg.setText("Dear  Member . Please note that an incident"+" " + reference + " "+description+" "+"has been captured on the system."+
                    " "+ "  " + "\n"+"\n"+"Regards");
            Transport.send(msg);
            
            JOptionPane.showMessageDialog(IncidentDetails.this, "Record successfully saved and message sucessfuly sent to  " + GroupA + "." );
        }
        catch(MessagingException | HeadlessException e){
            JOptionPane.showMessageDialog(IncidentDetails.this, e);}
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboSupervisorOnDutyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSupervisorOnDutyItemStateChanged
        String search = cboSupervisorOnDuty.getSelectedItem().toString();
        String [] args = search.split(" ");
        String sql ="Select * from Persons where Name = ?";
            try {
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                //Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                //PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, args[0]);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String ID = rs.getString("EmployeeNumber");
                    txtEmployeeNumber.setText(ID.trim());
                }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_cboSupervisorOnDutyItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboReferenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboReferenceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboReferenceActionPerformed

    private void cboReferenceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboReferenceItemStateChanged
        //dueDate();
    }//GEN-LAST:event_cboReferenceItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{                                         
            //Incident iI = Incident.getObj();
            Incident iI = new Incident();
            Incident.jButton1.setEnabled(false);
            //Incident.cboEmployeeNumber.setSelectedItem(selected);
            //Incident.cboIncidentType.removeAll();;;
            String selected = cboReference.getSelectedItem().toString();
            try{
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                //Connection con = DriverManager.getConnection("jdbc:sqlite:Incident");
                PreparedStatement pst = con.prepareStatement("Select * from Incidents where ReferenceNumber = ?");
                pst.setString(1, selected);
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    String employeeNumber = rs.getString("EmployeeNumber");
                    Incident.cboEmployeeNumber.setSelectedItem(employeeNumber);
                    String name = rs.getString("Name");
                    Incident.txtName.setText(name.trim());
                    String surname = rs.getString("Surname");
                    Incident.txtSurname.setText(surname.trim());
                    String idNumber = rs.getString("IDNumber");
                    Incident.txtIdNumber.setText(idNumber);
                    String incidentType = rs.getString("IncidentType");
                    Incident.cboIncidentType.setSelectedItem(incidentType);
                    String natureOfIncident = rs.getString("NatureOfIncident");
                    //Incident.cboNature.removeAllItems();
                    Date date = rs.getDate("DateOfIncident");
                    Incident.jDateChooser1.setDate(date);
                    Incident.cboNature.setSelectedItem(natureOfIncident);
                    String equipment = rs.getString("EquipmentUsed");
                    Incident.txtEquipmentUsed.setText(equipment.trim());
                    String activity = rs.getString("ActivityBiengCarriedOut");
                    Incident.txtActivity.setText(activity.trim());
                    String area = rs.getString("Area");
                    Incident.txtArea.setText(area.trim());
                    String experienceOnTask = rs.getString("YearsOfExperienceOnTask");
                    Incident.txtYears.setText(experienceOnTask.trim());
                    Incident.statusOpen.setEnabled(true);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(IncidentDetails.this, e);
            }
            iI.setVisible(true);
        }
        catch(SQLException ex){
            Logger.getLogger(IncidentDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(IncidentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IncidentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IncidentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IncidentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IncidentDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cboReference;
    protected static javax.swing.JComboBox cboSupervisorOnDuty;
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTextArea txtDescription;
    public static javax.swing.JTextArea txtDetails;
    public static javax.swing.JTextField txtEmployeeNumber;
    protected static javax.swing.JTextArea txtIncident;
    // End of variables declaration//GEN-END:variables
java.util.Date dueDate;
}
