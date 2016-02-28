/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import static conceptium.biz.IncidentCorrectiveActionStatusUpdate.cboHierachy;
import static conceptium.biz.IncidentCorrectiveActionStatusUpdate.cboReference;
import static conceptium.biz.IncidentCorrectiveActionStatusUpdate.jDateChooser1;
import static conceptium.biz.IncidentCorrectiveActionStatusUpdate.txtName;
import static conceptium.biz.Incident.cboEmployeeNumber;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MathomeTD
 */
public class Corrective extends javax.swing.JFrame {

    /**
     * Creates new form Controls
     */
    private static Corrective obj = null;
    private Corrective() {
        setUndecorated(true);
        setResizable(false);
        initComponents();
        names();
        reference();
        txtStatus.setEditable(false);
        
    }
    public static Corrective getObj(){
            if (obj == null){
                obj = new Corrective();
            }
            return obj;
        }
 private void names(){
        String sql ="Select * from Person";
            try {
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String ID = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    cboName.addItem(ID.trim()+" "+surname.trim());
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
                    cboReferenceNumber.addItem(ID.trim());
                }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
private void incident() throws SQLException, ClassNotFoundException{
    String search = (String)cboReferenceNumber.getSelectedItem();
    String sql = "select * from Incident where ReferenceNumber = ?";
    try{
        Connection con = DbConnection.dbConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, search);
            ResultSet rs = pst.executeQuery();
        if(rs.next()){
            String employeeNumber = rs.getString("EmployeeNumber");
            Incident.cboEmployeeNumber.addItem(employeeNumber.trim());
            //String name = rs.getString("Name");
            //Incident.txtName.setText(name);
            //String surname = rs.getString("Surname");
            //Incident.txtSurname.setText(surname);
            //String idNumber = rs.getString("IDNumber");
            //Incident.txtIdNumber.setText(idNumber);
            //String referenceNumber = rs.getString("");
            //incident.txtReferenceNumber.getText();
            String incidentType = rs.getString("IncidentType");
            Incident.cboIncidentType.setSelectedItem(incidentType.trim());
            String natureOfIncident = rs.getString("NatureOfIncident");
            Incident.cboNature.setSelectedItem(natureOfIncident.trim());
            String shift = rs.getString("Shift");
            Incident.cboShift.setSelectedItem(shift);
            String hoursOnShift = rs.getString("HoursOnShift");
            Incident.cboHrs.setSelectedItem(hoursOnShift);
            String reportTo = rs.getString("ReportedTo");
            Incident.cboReportedTo.setSelectedItem(reportTo);
            String numberOfContiniousDaysWorked = rs.getString("NumberOfContinuosDaysWorked");
            Incident.cboNOCDW.setSelectedItem(numberOfContiniousDaysWorked);
            //String contactNumber = rs.getString("ContactNumber");
            //Incident.txtContactNumber.setText(contactNumber);
            //String contactEmail = rs.getString("ContactEmail");
            //Incident.txtEmail.setText(contactEmail);
            //String department = rs.getString("Department");
            //Incident.txtDepartment.setText(department);
            String site = rs.getString("Site");
            Incident.txtSite.setText(site);
            String equipmentUsed = rs.getString("EquipmentUsed");
            Incident.txtEquipmentUsed.setText(equipmentUsed);
            String activityBiengDone = rs.getString("ActivityBiengCarriedOut");
            Incident.txtActivity.setText(activityBiengDone);
            String yearsOfExperience = rs.getString("YearsOfExperienceOnTask");
            Incident.txtYears.setText(yearsOfExperience);
            String agent = rs.getString("Agent");
            Incident.cboAgent.setSelectedItem(agent);
            String timeExposed = rs.getString("TimeOfExpose");
            Incident.txtTimeExposed.setText(timeExposed);
            String equipmentDemaged = rs.getString("EquipmentDemaged");
            Incident.txtEquipmentDemaged.setText(equipmentDemaged);
            String estimatedValue = rs.getString("EstimatedValue");
            Incident.txtEstimatedValue.setText(estimatedValue);
            Date DateOfIncident = rs.getDate("DateOfIncident");
            Incident.jDateChooser1.setDate((Date)DateOfIncident);
            Date DateOfReportingIncident = rs.getDate("DateOfReportingIncident");
            Incident.jDateChooser2.setDate((Date)DateOfReportingIncident);
            Date todayDate = rs.getDate("TodayDate");
            Incident.dcTodayDate.setDate((Date)todayDate);
            /**String head_Neck = choice;
            String eye = choice;
            String truck = choice;
            String finger = choice;
            String hand = choice;
            String arm = choice;
            String foot = choice;
            String toe = choice;**/
            String area = rs.getString("Area");
            Incident.txtArea.setText(area);
            System.out.println(todayDate);
            System.out.println(DateOfIncident);
            System.out.println(DateOfReportingIncident);
        }
    }catch(SQLException e){
    JOptionPane.showMessageDialog(Corrective.this, "Unable to complete task"+e);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        updateStatus = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        deleteAction = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jPanel1 = new javax.swing.JPanel();
        cboReferenceNumber = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtAction = new javax.swing.JTextField();
        cboHierachyOfControl = new javax.swing.JComboBox();
        cboName = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){public boolean isCellEditable(int row, int column){
            return false;}};
    jDateChooser2 = new com.toedter.calendar.JDateChooser();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();
    jButton5 = new javax.swing.JButton();
    jButton6 = new javax.swing.JButton();
    jPanel3 = new javax.swing.JPanel();
    jPanel4 = new javax.swing.JPanel();

    jPopupMenu1.add(jSeparator3);

    updateStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/update-windows.png"))); // NOI18N
    updateStatus.setText("Update Status");
    updateStatus.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            updateStatusActionPerformed(evt);
        }
    });
    jPopupMenu1.add(updateStatus);
    jPopupMenu1.add(jSeparator2);

    deleteAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/delete.png"))); // NOI18N
    deleteAction.setText("Remove action");
    deleteAction.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteActionActionPerformed(evt);
        }
    });
    jPopupMenu1.add(deleteAction);
    jPopupMenu1.add(jSeparator1);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    addWindowListener(new java.awt.event.WindowAdapter() {
        public void windowActivated(java.awt.event.WindowEvent evt) {
            formWindowActivated(evt);
        }
    });

    jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    cboReferenceNumber.addItemListener(new java.awt.event.ItemListener() {
        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            cboReferenceNumberItemStateChanged(evt);
        }
    });
    cboReferenceNumber.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cboReferenceNumberActionPerformed(evt);
        }
    });

    jLabel7.setText("Reference Number:");

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Corrective Action"));

    txtAction.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyTyped(java.awt.event.KeyEvent evt) {
            txtActionKeyTyped(evt);
        }
    });

    cboHierachyOfControl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Eliminate", "Substitute", "Seperate", "Redesign", "Administrate", "PPE", " " }));

    jLabel1.setText("Actions:");

    jLabel2.setText("Hierachy of Control Type");

    jLabel4.setText("Responsible person ");

    jLabel5.setText("Due Date");

    jLabel6.setText("Status");

    jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/add139-4.png"))); // NOI18N
    jButton7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
        }
    });

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
        },
        new String [] {
            "Action", "Hierachy of Control", "Responsible Person", "Due Date","Status"
        }
    ));
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jTable1MouseReleased(evt);
        }
    });
    jScrollPane2.setViewportView(jTable1);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane2)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(txtAction, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cboHierachyOfControl, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(11, 11, 11)
                            .addComponent(jLabel5)))
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboHierachyOfControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAction, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtStatus))
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                    .addGap(720, 720, 720)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cboReferenceNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cboReferenceNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel7))
            .addGap(0, 0, 0)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/save.png"))); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jButton2.setText("Key Learnings");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/newspaper18.png"))); // NOI18N
    jButton3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
        }
    });

    jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/close.png"))); // NOI18N
    jButton4.setText("Close");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    jButton5.setText("Update Incident Status");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });

    jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/edit.png"))); // NOI18N

    jPanel3.setBackground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 51, Short.MAX_VALUE)
    );

    jPanel4.setBackground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 31, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addComponent(jButton4))
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0))
    );

    pack();
    setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CheckList cL = new CheckList();
        CheckList.cboReference.setSelectedItem(cboReferenceNumber.getSelectedItem().toString());
        String sql ="Select * from Person";
            try {
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String ID = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    String idNumber = rs.getString("IDNumber");
                    cL.txtId.setText(idNumber);
                    cL.cboManager.addItem(ID.trim()+" "+surname.trim());
                    String telephone = rs.getString("ContactNumber");
                    cL.txtTelephone.setText(telephone);
                    String email = rs.getString("EmailAddress");
                    cL.txtEmail.setText(email.trim());
                    String job = rs.getString("JobTitle");
                    cL.cboJobTitle.addItem(job.trim());
                }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    
        cL.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        KeyLearning kL = new KeyLearning();
        KeyLearning.cboReferenceNumber.setSelectedItem(cboReferenceNumber.getSelectedItem().toString());
        kL.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cboReferenceNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboReferenceNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboReferenceNumberActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String sql = "insert into CorrectiveAction(ReferenceNumber,Action,"+
                "Hierachy,ResponsiblePerson,"+
                "DuteDate,Status"+
                ")values(?,?,?,?,?,?)";
        String reference = cboReferenceNumber.getSelectedItem().toString();
        String action = txtAction.getText();
        String Hierachy = cboHierachyOfControl.getSelectedItem().toString();;
        String responsibleName = cboName.getSelectedItem().toString();
        Date dueDate = new java.sql.Date(jDateChooser2.getDate().getTime());
        String status = txtStatus.getText();
        try{
        Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, reference);
            pst.setString(2, action);
            pst.setString(3, Hierachy);
            pst.setString(4, responsibleName);
            pst.setDate(5, dueDate);
            pst.setString(6, status);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(Corrective.this, "Corrective action for"+" "+ reference+" "+"has been successjully saved.");
        }
        catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(Corrective.this, e);
        }
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
        String GroupA = "herbert@conceptium.biz";
        String referenceNumber = cboReferenceNumber.getSelectedItem().toString();
       
        //Address[] cc = new Address[] {InternetAddress.parse("abc@abc.com"),
                               //InternetAddress.parse("abc@def.com"), 
                               //InternetAddress.parse("ghi@abc.com")};
        //message.addRecipients(Message.RecipientType.CC, cc);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("herbert@conceptium.biz"));
            //msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(GroupA));
            msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse("herbertd@mathometd.com,molokgobi@yahoo.com,herbert@conceptium.biz"));
            msg.setSubject("Action allocation for"+" " + referenceNumber);
            msg.setText("Dear"+" "+responsibleName+ "\n"+
                    "Incident"+" "+reference+"\n"+
                    "Requires that you"+"\n"+
                    "1."+action+"\n"+
                    "Due date "+ "  " +dueDate+
                    "\n"+"\n"+"Regards");
            Transport.send(msg);
            
            JOptionPane.showMessageDialog(Corrective.this, "Record successfully saved and Learnings distributed to  " + GroupA + "." );
        }
        catch(MessagingException | HeadlessException e){
            JOptionPane.showMessageDialog(Corrective.this, e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated
    public boolean isEmpty(){
        if (jTable1 != null && jTable1.getModel() != null) {
            return jTable1.getModel().getRowCount()<=0;
        }
        return false;
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DefaultTableModel model = (DefaultTableModel) Corrective.jTable1.getModel();
        
        if(isEmpty()){JOptionPane.showMessageDialog(Corrective.this, "Corrective action is empty");
        }else{
        for(int i =0; i < model.getRowCount(); i++){
            String use = model.getValueAt(i, 4).toString();
            if(use.equals("Open")){
                JOptionPane.showMessageDialog(Corrective.this, "please ensure all open cases are closed first");
            System.out.println("Test sustefull");
                    }else{ 
        String search = cboReferenceNumber.getSelectedItem().toString();
        String sql = "select ReferenceNumber from KeyLearnings  where ReferenceNumber = ? ";
        String sql1 = "select * from checklist where referencenumber = ?";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Incidents","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, search);
            //pst.setString(2, search);
                ResultSet rs = pst.executeQuery();
                if(!rs.last()){
                    JOptionPane.showMessageDialog(Corrective.this, "Please ensure that KeyLearnings have been shared");
                }
                else{
        Incident iI = new Incident();
        iI.statusClosed.setSelected(true);
        iI.statusOpen.setSelected(false);
        iI.statusOpen.setEnabled(false);
        //iI.statusPending.setEnabled(false);
        iI.statusClosed.setEnabled(false);
        iI.jButton1.setEnabled(false);
        iI.txtReferenceNumber.setText(cboReferenceNumber.getSelectedItem().toString());
        java.util.Date date = new java.util.Date();
        iI.dcClosingDate.setDate(date);
                try {
                    incident();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Corrective.class.getName()).log(Level.SEVERE, null, ex);
                }
        try{
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Incidents","herbert","elsie1*#");
            pst = con.prepareStatement(sql1,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            pst.setString(1, search);
            //pst.setString(2, search);
                rs = pst.executeQuery();
                if(!rs.last()){
                    JOptionPane.showMessageDialog(Corrective.this, "Please ensure that the CheckList has been completed","Warning",JOptionPane.ERROR_MESSAGE);
                }else{iI.setVisible(true);}
        }catch(SQLException | HeadlessException e){
                JOptionPane.showMessageDialog(Corrective.this, e);}
        
                }
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(Corrective.this, e);
        }}}}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String ref = cboReferenceNumber.getSelectedItem().toString();
        if(!txtAction.getText().equals("")  ){
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        java.util.Date date = new java.util.Date();
        String action = txtAction.getText();
        String hierachyOfControl = (String)cboHierachyOfControl.getSelectedItem();
        String responsiblePerson = (String)cboName.getSelectedItem();
        java.sql.Date dueDate;
        dueDate = new java.sql.Date (jDateChooser2.getDate().getTime());
        String status = txtStatus.getText();
        Object[] newRow = {action,hierachyOfControl,responsiblePerson,dueDate,status};
        if(jDateChooser2.getDate().getTime() <= date.getTime()){
        JOptionPane.showMessageDialog(Corrective.this, "Due date can not be less or equal to today's date");}
        else{
            //if(!txtAction.getText().equals("")  ){
            model.addRow(newRow);
            //}
        }
        }else{
            JOptionPane.showMessageDialog(Corrective.this, "Please enter action required for"+" "+ref);
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        jTable1.setComponentPopupMenu(jPopupMenu1);
        if (evt.isPopupTrigger())
        {
            jPopupMenu1.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTable1MouseReleased
    public int row; 
    private void updateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStatusActionPerformed
        row = jTable1.getSelectedRow();
        IncidentCorrectiveActionStatusUpdate sU = IncidentCorrectiveActionStatusUpdate.getObj();
        DefaultTableModel model = (DefaultTableModel) Corrective.jTable1.getModel();
        IncidentCorrectiveActionStatusUpdate.txtAllocatedTask.setText(model.getValueAt(row, 0).toString().trim());
        IncidentCorrectiveActionStatusUpdate.cboHierachy.setSelectedItem(model.getValueAt(row, 1).toString().trim());
        IncidentCorrectiveActionStatusUpdate.txtName.setText(model.getValueAt(row, 2).toString().trim());
        IncidentCorrectiveActionStatusUpdate.jDateChooser1.setDate((java.util.Date) model.getValueAt(row, 3));
        IncidentCorrectiveActionStatusUpdate.txtAllocatedTask.setEnabled(false);
        jDateChooser1.setEnabled(false);
        txtName.setEnabled(false);
        cboReference.setEnabled(false);
        cboHierachy.setEnabled(false);
        IncidentCorrectiveActionStatusUpdate.getObj().setVisible(true);
    }//GEN-LAST:event_updateStatusActionPerformed

    private void txtActionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtActionKeyTyped
        txtStatus.setText("Open");
        txtStatus.setBackground(Color.GREEN);
        Calendar c = Calendar.getInstance();
        java.util.Date date = new java.util.Date();
        int x = 2;
        c.setFirstDayOfWeek(2);
        c.setTime(date);
        if(c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7){
        x++;
        c.add(Calendar.DATE, x);
        jDateChooser2.setDate(c.getTime());
        jDateChooser2.setEnabled(false);
        }else{
        c.add(Calendar.DATE, x);
        jDateChooser2.setDate(c.getTime());
        jDateChooser2.setEnabled(false);
        }
    }//GEN-LAST:event_txtActionKeyTyped

    private void deleteActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionActionPerformed
        row = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) Corrective.jTable1.getModel();
        model.removeRow(row);
    }//GEN-LAST:event_deleteActionActionPerformed

    private void cboReferenceNumberItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboReferenceNumberItemStateChanged
       DefaultTableModel model = (DefaultTableModel) Corrective.jTable1.getModel();
       model.setRowCount(0);
    }//GEN-LAST:event_cboReferenceNumberItemStateChanged

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
            java.util.logging.Logger.getLogger(Corrective.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Corrective.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Corrective.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Corrective.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Corrective().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox cboHierachyOfControl;
    public static javax.swing.JComboBox cboName;
    public static javax.swing.JComboBox cboReferenceNumber;
    private javax.swing.JMenuItem deleteAction;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    public static com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField txtAction;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JMenuItem updateStatus;
    // End of variables declaration//GEN-END:variables
}
