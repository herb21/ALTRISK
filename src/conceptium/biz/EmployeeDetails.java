/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;


import com.alee.laf.table.WebTableUI;
import static conceptium.biz.Incident.cboReportedTo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author MathomeTD
 */
public class EmployeeDetails extends javax.swing.JFrame {

    private static EmployeeDetails obj = null;
    /**
     * Creates new form PersonDetails
     */
    DbConnection connect = new DbConnection();
    private EmployeeDetails() {
        try {
            setUndecorated(true);
            setResizable(false);
            initComponents();
            updateTable();
            supervisor();
            fillName();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
public static EmployeeDetails getObj(){
            if (obj == null){
                obj = new EmployeeDetails();
            }
            return obj;
        }
private void updateTable(){
       Statement pst ;
       ResultSet rs ;
       try {
           Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
           pst = con.createStatement();
           rs = pst.executeQuery("Select * from Persons ");
           jXTable1.setModel(DbUtils.resultSetToTableModel(rs));
           jXTable1.setUI(new WebTableUI());
           JTableHeader th = jXTable1.getTableHeader();
           TableColumnModel tcm = th.getColumnModel();
           TableColumn tc = tcm.getColumn(0);
           tc.setHeaderValue("Name");
           tc = tcm.getColumn(1);
           tc.setHeaderValue("Surname");
           tc = tcm.getColumn(2);
           tc.setHeaderValue("ID Number");
           tc = tcm.getColumn(3);
           tc.setHeaderValue("Employee ID");
           tc = tcm.getColumn(4);
           tc.setHeaderValue("Role");
           tc = tcm.getColumn(5);
           tc.setHeaderValue("Department");
           tc = tcm.getColumn(6);
           tc.setHeaderValue("Years Of Service");
           tc = tcm.getColumn(7);
           tc.setHeaderValue("Site");
           tc = tcm.getColumn(8);
           tc.setHeaderValue("Telephone");
           tc = tcm.getColumn(9);
           tc.setHeaderValue("Email");
       }
       catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
       }
}
private void fillName() throws SQLException{
        String sql ="Select * from Persons";
            try {
                Connection con = connect.dbConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String employeeName = rs.getString("Name");
                    cboName.addItem(employeeName.trim());
                }
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
            
    }
        private void supervisor() throws SQLException{
        String sql ="Select * from Persons";
            try {
                Connection con = DbConnection.dbConnection();
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String ID = rs.getString("Name");
                    String surname = rs.getString("Surname");
                    cboReportTo.addItem(ID.trim()+" "+ surname);
                }
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
        public void Names(){
    String select = cboName.getSelectedItem().toString();
        try{
            Connection con = DbConnection.dbConnection();
            PreparedStatement pst = con.prepareStatement("Select * from Persons where Name = ?");
            pst.setString(1, select);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                String employeeNumber = rs.getString("Name");
                txtEmployeeNumber.setText(employeeNumber.trim());
                String surname = rs.getString("Surname");
                txtSurname.setText(surname.trim());
                String idNumber = rs.getString("IDNumber");
                txtIdNumber.setText(idNumber);
            }
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(EmployeeDetails.this, e);
        }
}

    /**public void setYearsOfService(int YOS){
        intYearsOfService.setText(Integer.parseInt(YOS));
    }
    public void setContactNumber(int EContact){
         intContactNumber.setText(EContact);
    }**/
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        editEmployee = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        deleteEmployee = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        siteSupervisor = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        captureIncident = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        intYearsOfService = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboJobTitle = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cboSite = new javax.swing.JComboBox();
        cboDepartment = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cboReportTo = new javax.swing.JComboBox();
        dcTodayDate = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSurname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmployeeNumber = new javax.swing.JTextField();
        txtIdNumber = new javax.swing.JTextField();
        cboName = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPopupMenu1.add(jSeparator1);

        editEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/edit.png"))); // NOI18N
        editEmployee.setText("Edit Employee");
        editEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editEmployeeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(editEmployee);
        jPopupMenu1.add(jSeparator5);

        deleteEmployee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/delete.png"))); // NOI18N
        deleteEmployee.setText("Delete Employee");
        deleteEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEmployeeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(deleteEmployee);
        jPopupMenu1.add(jSeparator2);

        siteSupervisor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/customer.png"))); // NOI18N
        siteSupervisor.setText("Site Supervisor");
        siteSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siteSupervisorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(siteSupervisor);
        jPopupMenu1.add(jSeparator4);

        captureIncident.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/save.png"))); // NOI18N
        captureIncident.setText("Capture Incident");
        captureIncident.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                captureIncidentActionPerformed(evt);
            }
        });
        jPopupMenu1.add(captureIncident);
        jPopupMenu1.add(jSeparator3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Job Title:");

        jLabel8.setText("Site:");

        jLabel7.setText("Years in Service:");

        cboJobTitle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SHE Coordinator", "SHE Manager", "Transport Manager", "Project SHE Officer", "Production Manager", "Eletrical Supervisor", "Mechanical Supervisor", " ", " " }));

        jLabel5.setText("Department:");

        cboSite.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VaalKop", "B", "C", "D" }));

        cboDepartment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IT & Communication", "Human Resources", "Accounts", "Administration", "Technical Support", "Sales Support", " " }));

        jLabel9.setText("Reports To:");

        jLabel10.setText("Date:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(4, 4, 4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboDepartment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboJobTitle, 0, 1, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(4, 4, 4)
                        .addComponent(cboSite, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(4, 4, 4)
                        .addComponent(cboReportTo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(intYearsOfService, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(4, 4, 4)
                        .addComponent(dcTodayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboJobTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cboSite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(intYearsOfService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cboDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(cboReportTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(dcTodayDate, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Name:");

        jLabel2.setText("Surname:");

        jLabel3.setText("ID Number:");

        jLabel4.setText("Employee Number:");

        cboName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNameItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSurname)
                    .addComponent(cboName, 0, 132, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(txtEmployeeNumber))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txtIdNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmployeeNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/save.png"))); // NOI18N
        jButton1.setText("save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Update Employee");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/close.png"))); // NOI18N
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        name = cboName.getSelectedItem().toString();
        surname = txtSurname.getText();
        idNumber = txtIdNumber.getText();
        employeeNumber = txtEmployeeNumber.getText();
        jobTitle = cboJobTitle.getSelectedItem().toString();
        department = cboDepartment.getSelectedItem().toString();
        yearsOfService = intYearsOfService.getText();
        site = cboSite.getSelectedItem().toString();
        //contactNumber =intContactNumber.getText();
        //emailAddress = txtEmailAddress.getText();
        DbaseOperation Db = new DbaseOperation();
        String[] arr = {name,surname,idNumber,employeeNumber,jobTitle,department,yearsOfService,site,contactNumber,emailAddress};
        Db.insertToDb("Persons","Name,Surname,IDNumber,EmployeeNumber,JobTitle,Department," +
        "YearsOfService,Site,ContactNumber,EmailAddress" , "?,?,?,?,?,?,?,?,?,?", arr);
        updateTable();
        JOptionPane.showMessageDialog(EmployeeDetails.this, "Employee" +" "+ name +" "+ surname + " " + "sucessfully saved");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void captureIncidentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_captureIncidentActionPerformed
        try{                                                
            int row = jXTable1.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel)jXTable1.getModel();
            String selected = model.getValueAt(row, 3).toString();
            Incident iI = new Incident();
            //Incident.cboEmployeeNumber.removeAllItems();
            String sql = "Select * from Persons where EmployeeNumber = ?";
            try{
                Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
                //Connection con = DriverManager.getConnection("jdbc:sqlite:Incident");
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, selected);
                ResultSet rs = pst.executeQuery();
                if (rs.next()){
                    employeeNumber = rs.getString("EmployeeNumber");
                    Incident.cboEmployeeNumber.addItem(employeeNumber);
                    name = rs.getString("Name");
                    Incident.txtName.setText(name.trim());
                    surname = rs.getString("Surname");
                    Incident.txtSurname.setText(surname.trim());
                    idNumber = rs.getString("IDNumber");
                    Incident.txtIdNumber.setText(idNumber);
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(EmployeeDetails.this, e);
            }
            
            iI.setVisible(true);
            //Incident.getObj().setVisible(true);
        }
        catch(SQLException | ClassNotFoundException ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_captureIncidentActionPerformed

    private void editEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editEmployeeActionPerformed
        int row = jXTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jXTable1.getModel();
        String selected = model.getValueAt(row, 2).toString();
        String sql = "select * from Persons where IDNumber = ?";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, selected);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            name = rs.getString("Name");
            cboName.setSelectedItem(name);
            surname = rs.getString("Surname");
            txtSurname.setText(surname);
            idNumber = rs.getString("IDNumber");
            txtIdNumber.setText(idNumber);
            employeeNumber = rs.getString("EmployeeNumber");
            txtEmployeeNumber.setText(employeeNumber);
            jobTitle = rs.getString("JobTitle");
            cboJobTitle.setSelectedItem(jobTitle);
            department = rs.getString("Department");
            cboDepartment.setSelectedItem(department);
            yearsOfService = rs.getString("yearsOfService");
            intYearsOfService.setText(yearsOfService);
            site = rs.getString("Site");
            cboSite.setSelectedItem(site);
            contactNumber = rs.getString("ContactNumber");
            //intContactNumber.setText(contactNumber);
            emailAddress = rs.getString("EmailAddress");
            //txtEmailAddress.setText(emailAddress);
            }
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(EmployeeDetails.this, e);
        }
        jButton1.setEnabled(false);
    }//GEN-LAST:event_editEmployeeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        updateTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void deleteEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEmployeeActionPerformed
        int p = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this employee", "Delete",JOptionPane.YES_NO_OPTION );
        if (p == 0){
        int row = jXTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jXTable1.getModel();
        String selected = model.getValueAt(row, 2).toString();
        name = model.getValueAt(row, 0).toString();
        surname = model.getValueAt(row, 1).toString();
        String sql = "delete from Persons where IDNumber = ?";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, selected);
            pst.executeUpdate();
            updateTable();
            JOptionPane.showMessageDialog(this, name+ " "+surname.toUpperCase()+ "successfully deleted.");
        }
        catch(Exception e){
            
            JOptionPane.showMessageDialog(this, e);
           
        }
    }                   
    }//GEN-LAST:event_deleteEmployeeActionPerformed

    private void siteSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siteSupervisorActionPerformed
        int row = jXTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jXTable1.getModel();
        String selected = model.getValueAt(row, 3).toString();
        //SiteManagement sM = new SiteManagement();
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement("Select * from Persons where EmployeeNumber = ?");
            pst.setString(1, selected);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                employeeNumber = rs.getString("EmployeeNumber");
                SiteManagement.cboEmployeeNumber.setSelectedItem(employeeNumber);
                site = rs.getString("Site");
                SiteManagement.txtName.setText(site);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(EmployeeDetails.this, e);
        }

        
        SiteManagement.getObj().setVisible(true);
    }//GEN-LAST:event_siteSupervisorActionPerformed

    private void jXTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable1MouseReleased
        jXTable1.setComponentPopupMenu(jPopupMenu1);
        if (evt.isPopupTrigger())
        {
            jPopupMenu1.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jXTable1MouseReleased

    private void cboNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNameItemStateChanged
        Names();
    }//GEN-LAST:event_cboNameItemStateChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        java.util.Date date = new java.util.Date();
        dcTodayDate.setDate(date);
        dcTodayDate.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(EmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem captureIncident;
    private javax.swing.JComboBox cboDepartment;
    private javax.swing.JComboBox cboJobTitle;
    private javax.swing.JComboBox cboName;
    private javax.swing.JComboBox cboReportTo;
    private javax.swing.JComboBox cboSite;
    private com.toedter.calendar.JDateChooser dcTodayDate;
    private javax.swing.JMenuItem deleteEmployee;
    private javax.swing.JMenuItem editEmployee;
    private javax.swing.JTextField intYearsOfService;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private org.jdesktop.swingx.JXTable jXTable1;
    private javax.swing.JMenuItem siteSupervisor;
    private javax.swing.JTextField txtEmployeeNumber;
    private javax.swing.JTextField txtIdNumber;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables
private String Ename;
private String name ;
private  String surname ;
private String idNumber ;
private String employeeNumber ;
private String jobTitle ;
private String department ;
private String yearsOfService ;
private  String site ;
private String contactNumber ;
private String emailAddress ;

}
