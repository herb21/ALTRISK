/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.tabbedpane.WebTabbedPaneUI;
import com.alee.laf.table.WebTableUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.SystemTray;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TrayIcon;

/**
 *
 * @author MathomeTD
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private SystemTray sysTray;
    public Main() {
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUndecorated(true);
        initComponents();
        if(SystemTray.isSupported()){
        JOptionPane.showMessageDialog(Main.this, "SystemTray is not supported");
        return;
        }
        SystemTray tray = SystemTray.getSystemTray();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = toolkit.getImage("trayIcon.jpg");

    PopupMenu menu = new PopupMenu();

    MenuItem messageItem = new MenuItem("Show Message");
    messageItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "www.java2s.com");
      }
    });
    menu.add(messageItem);

    MenuItem closeItem = new MenuItem("Close");
    closeItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    //menu.add(closeItem);
    //TrayIcon icon = new TrayIcon(image, "SystemTray Demo", menu);
    //icon.setImageAutoSize(true);

    //tray.add(icon);
    menu.add(closeItem);
        jXTaskPaneContainer1.setBackground(Color.white);
        jXTaskPane1.setBackground(Color.red);
        jXTaskPane1.setCollapsed(true);
        jXTaskPane2.setCollapsed(true);
        jXTaskPane3.setCollapsed(true);
        jXTaskPane4.setCollapsed(true);
        jXTaskPane5.setCollapsed(false);
        jXTaskPane6.setCollapsed(true);
        jXTaskPane7.setCollapsed(true);
        jXTaskPane1.setTitle("Auditing");
        jXTaskPane1.setForeground(Color.BLUE);
        jXTaskPane2.setForeground(Color.BLUE);
        jXTaskPane3.setForeground(Color.BLUE);
        jXTaskPane4.setForeground(Color.BLUE);
        jXTaskPane6.setForeground(Color.BLUE);
        jXTaskPane7.setForeground(Color.BLUE);
        jXTaskPane1.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Create Employees : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //PersonDetails pD = new EmployeeDetails();
                EmployeeDetails.getObj().setVisible(true);
            }
        });
        jXTaskPane5.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Capture Incident : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //Incident iI = new Incident();
                Incident.getObj().setVisible(true);
            }
        });
        jXTaskPane5.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Incident Details : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //IncidentDetails iD = new IncidentDetails();
                //iD.setVisible(true);
            }
        });
         jXTaskPane5.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Incident Risk Rating: ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                RiskRating.getObj().setVisible(true);
            }
        });
                  jXTaskPane5.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Corrective Actions : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //Corrective iR = new Corrective();
                //iR.setVisible(true);
            }
        });
          jXTaskPane5.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Incident Analysis : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //IncidentAnalysis iR = new IncidentAnalysis();
                //iR.setVisible(true);
            }
        });
             
          
             jXTaskPane2.setTitle("Legal Compliance");
        jXTaskPane2.add(new AbstractAction() {
            {
                putValue(Action.NAME, "SOPs : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
        jXTaskPane2.add(new AbstractAction() {
            {
                putValue(Action.NAME, "QMS roles and responsibilities : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
         jXTaskPane2.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Legal Register : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
          jXTaskPane2.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Risk Assessment : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
           jXTaskPane2.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Training and Awareness : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
            jXTaskPane2.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Incident and NC reporting : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
             jXTaskPane2.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Monitoring and Review. : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
             
             
jXTaskPane3.setTitle("Training");
        jXTaskPane3.add(new AbstractAction() {
            {
                putValue(Action.NAME, "SOPs : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
        jXTaskPane3.add(new AbstractAction() {
            {
                putValue(Action.NAME, "QMS roles and responsibilities : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
         jXTaskPane3.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Legal Register : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
          jXTaskPane3.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Risk Assessment : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
           jXTaskPane3.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Training and Awareness : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
            jXTaskPane3.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Incident and NC reporting : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
             jXTaskPane3.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Monitoring and Review. : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
             
             
jXTaskPane4.setTitle("Risk Assessment");
        jXTaskPane4.add(new AbstractAction() {
            {
                putValue(Action.NAME, "SOPs : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
        jXTaskPane4.add(new AbstractAction() {
            {
                putValue(Action.NAME, "QMS roles and responsibilities : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
         jXTaskPane4.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Legal Register : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
          jXTaskPane4.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Risk Assessment : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
           jXTaskPane4.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Training and Awareness : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
            jXTaskPane4.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Incident and NC reporting : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
             jXTaskPane4.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Monitoring and Review. : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
             
             
jXTaskPane5.setTitle("Incident Management & Reporting");
jXTaskPane5.setForeground(Color.BLUE);
        jXTaskPane1.add(new AbstractAction() {
            {
                putValue(Action.NAME, "SHE Procedures : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
        jXTaskPane1.add(new AbstractAction() {
            {
                putValue(Action.NAME, "QMS roles and responsibilities : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
         jXTaskPane1.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Legal Register : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
          jXTaskPane1.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Risk Assessment : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
           jXTaskPane1.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Training and Awareness : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
            jXTaskPane1.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Incident and NC reporting : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
             jXTaskPane1.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Monitoring and Review. : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
             
             
jXTaskPane6.setTitle("SHEQ System Documentation");
        jXTaskPane6.add(new AbstractAction() {
            {
                putValue(Action.NAME, "SHEQ Policies : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
        jXTaskPane6.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Planning : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
         jXTaskPane6.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Implementation & Operation : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
          jXTaskPane6.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Checking & Corrective Actions : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
           jXTaskPane6.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Management Review : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentRegistry iR = new IncidentRegistry();
                iR.setVisible(true);
            }
        });
           
             jXTaskPane7.setTitle("Key Corporate Standards ");
        jXTaskPane7.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Capture an Audit : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                //PersonDetails pD = new EmployeeDetails();
                EmployeeDetails.getObj().setVisible(true);
            }
        });
    }
    public void populateNumber(){
        String sql = "select COUNT(ReferenceNumber) as count from Incident";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            sum = rs.getString("count");
            //jLabel2.setText("("+sum+")");
            //jLabel2.setFont(new Font("Arial", Font.BOLD, 14));
            //jLabel2.setForeground(Color.red);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(Main.this, e);
        }
    }
    public void highPriority(){
        String priority1 = "Fatality";
        String priority = "Above R50 000";
        String sql = "select COUNT(NatureOfIncident) as count from Incident where NatureOfIncident = '"+priority+"' or NatureOfIncident = '"+priority1+"'";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            String total = rs.getString("count");
            //jLabel10.setText("("+total+")");
            //jLabel10.setFont(new Font("Arial", Font.BOLD, 14));
            //jLabel10.setForeground(Color.red);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(Main.this, e);
        }
    }
    
    
    /*private void updateTable(){
       String sql = "Select ReferenceNumber,IncidentType,NatureOfIncident, "+
                   "Description, DateOfIncident,Status,Site from Incident inner join IncidentDetail on ReferenceNumber = Reference";
       try {
           Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
           PreparedStatement pst = con.prepareStatement(sql);
           ResultSet rs = pst.executeQuery();
           if(rs.next()){
           jTable1.setUI(new WebTableUI());
           jTable1.setModel(DbUtils.resultSetToTableModel(rs)); 
           JTableHeader th = jTable1.getTableHeader();
           TableColumnModel tcm = th.getColumnModel();
           TableColumn tc = tcm.getColumn(0);
           tc.setHeaderValue("Reference Number");
           tc = tcm.getColumn(1);
           tc.setHeaderValue("incident Type");
           tc = tcm.getColumn(2);
           tc.setHeaderValue("Nature Of Incident");
           tc = tcm.getColumn(3);
           tc.setHeaderValue("Description");
           tc = tcm.getColumn(4);
           tc.setHeaderValue("Date of Incident");
           tc = tcm.getColumn(5);
           tc.setHeaderValue("Status");
           tc = tcm.getColumn(6);
           tc.setHeaderValue("Site");
           //jTable1.addColumn(tc);
           //th.repaint();
           }
       }
       catch (Exception ex) {
           JOptionPane.showMessageDialog(Main.this, ex);
       }
} */
        public void Priority(){
        String priority1 = "Fatality";
        String priority = "Above R50 000";
        String sql = "select (NatureOfIncident),(IncidentType),(DateOfIncident),(DateOfReportingIncident),(Site),"+
                "(ReferenceNumber) from Incident where NatureOfIncident = '"+priority+"' or NatureOfIncident = '"+priority1+"'";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
            //jTable2.setModel(DbUtils.resultSetToTableModel(rs)); 
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(Main.this, e);
        }
    }
        public void overDue(){
        SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
        Date date = new Date();
        String now = format.format(date);
        //txtNew.setText(format.format(date));
        String sql = "select COUNT(DateOfReportingIncident) as count from Incident where DateOfReportingIncident < '"+now+"' ";
        try{
            Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            String totals =  rs.getString("count");
            //jLabel9.setText("("+totals+")");
            //jLabel9.setFont(new Font("Arial", Font.BOLD, 14));
            //jLabel9.setForeground(Color.red);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(Main.this, e);
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

        jPopupMenu2 = new javax.swing.JPopupMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane3 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane4 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane5 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane6 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane7 = new org.jdesktop.swingx.JXTaskPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        jMenu16 = new javax.swing.JMenu();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        jMenu17 = new javax.swing.JMenu();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenu18 = new javax.swing.JMenu();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenu19 = new javax.swing.JMenu();
        jSeparator31 = new javax.swing.JPopupMenu.Separator();
        jMenu20 = new javax.swing.JMenu();
        jSeparator30 = new javax.swing.JPopupMenu.Separator();
        jMenu15 = new javax.swing.JMenu();
        jMenu7 = new javax.swing.JMenu();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        jMenu8 = new javax.swing.JMenu();
        jSeparator25 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        jMenu13 = new javax.swing.JMenu();
        jSeparator39 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jSeparator37 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator38 = new javax.swing.JPopupMenu.Separator();
        jMenu9 = new javax.swing.JMenu();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jMenu10 = new javax.swing.JMenu();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenu6 = new javax.swing.JMenu();
        jSeparator34 = new javax.swing.JPopupMenu.Separator();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator32 = new javax.swing.JPopupMenu.Separator();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jSeparator33 = new javax.swing.JPopupMenu.Separator();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jSeparator29 = new javax.swing.JPopupMenu.Separator();
        jMenu21 = new javax.swing.JMenu();
        jSeparator40 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jSeparator35 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        jSeparator36 = new javax.swing.JPopupMenu.Separator();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator41 = new javax.swing.JPopupMenu.Separator();
        jMenuItem25 = new javax.swing.JMenuItem();
        jSeparator42 = new javax.swing.JPopupMenu.Separator();
        jMenuItem26 = new javax.swing.JMenuItem();
        jSeparator43 = new javax.swing.JPopupMenu.Separator();
        jSeparator45 = new javax.swing.JPopupMenu.Separator();
        jMenu14 = new javax.swing.JMenu();
        jSeparator44 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator48 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator47 = new javax.swing.JPopupMenu.Separator();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator46 = new javax.swing.JPopupMenu.Separator();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator49 = new javax.swing.JPopupMenu.Separator();
        jSeparator50 = new javax.swing.JPopupMenu.Separator();
        jSeparator51 = new javax.swing.JPopupMenu.Separator();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();

        jPopupMenu2.add(jSeparator1);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/delete.png"))); // NOI18N
        jMenuItem3.setText("Delete");
        jPopupMenu2.add(jMenuItem3);
        jPopupMenu2.add(jSeparator3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/category.png"))); // NOI18N
        jMenu2.setText("View");
        jMenu2.add(jSeparator2);

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/preview.png"))); // NOI18N
        jMenuItem2.setText("Incident");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenu2.add(jSeparator5);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/preview.png"))); // NOI18N
        jMenuItem4.setText("Incident Details");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jPopupMenu2.add(jMenu2);
        jPopupMenu2.add(jSeparator4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/load9.png"))); // NOI18N
        jMenuItem5.setText("Refresh Data");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem5);
        jPopupMenu2.add(jSeparator6);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jXTaskPaneContainer1.setBackground(new java.awt.Color(204, 204, 255));
        org.jdesktop.swingx.VerticalLayout verticalLayout1 = new org.jdesktop.swingx.VerticalLayout();
        verticalLayout1.setGap(14);
        jXTaskPaneContainer1.setLayout(verticalLayout1);
        jXTaskPaneContainer1.add(jXTaskPane1);
        jXTaskPaneContainer1.add(jXTaskPane2);
        jXTaskPaneContainer1.add(jXTaskPane3);
        jXTaskPaneContainer1.add(jXTaskPane4);
        jXTaskPaneContainer1.add(jXTaskPane5);
        jXTaskPaneContainer1.add(jXTaskPane6);
        jXTaskPaneContainer1.add(jXTaskPane7);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Compliance Audits", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N

        jButton2.setText("Graphs");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("System Compliance", jPanel19);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Inspection", jPanel21);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Audits", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("Total Audits Planned");

        jLabel3.setForeground(new java.awt.Color(0, 102, 255));
        jLabel3.setText("Audits Completed");

        jLabel4.setForeground(new java.awt.Color(0, 102, 255));
        jLabel4.setText("Audits Outstanding");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setText("(0)");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("(0)");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 51, 102));
        jLabel7.setText("(0)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel5)))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(127, 127, 127)
                        .addComponent(jLabel7)
                        .addGap(58, 58, 58))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Legal Compliance", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N
        jPanel16.setForeground(new java.awt.Color(255, 51, 102));

        jLabel12.setForeground(new java.awt.Color(0, 102, 255));
        jLabel12.setText("Pending");

        jLabel13.setForeground(new java.awt.Color(0, 102, 255));
        jLabel13.setText("Gazetted Legislation");

        jLabel16.setForeground(new java.awt.Color(0, 102, 255));
        jLabel16.setText("Outstanding Legal Update");

        jLabel17.setForeground(new java.awt.Color(0, 102, 255));
        jLabel17.setText("Registry Count");

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 102));
        jLabel18.setText("(0)");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 102));
        jLabel19.setText("(0)");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 102));
        jLabel20.setText("(0)");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 51, 102));
        jLabel21.setText("(0)");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 51, 102));
        jLabel22.setText("(0)");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel18)))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel19)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel20)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel21)
                        .addGap(95, 95, 95)
                        .addComponent(jLabel22)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "News Feed", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N

        jLabel24.setText("News Feed1");

        jLabel25.setText("News Feed 2");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setText("Graph");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Training and Development", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Training Summary", jPanel6);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Skills Matrix Summary", jPanel8);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Graphs and Reports", jPanel9);

        jLabel26.setForeground(new java.awt.Color(0, 51, 255));
        jLabel26.setText("Intervention Complete");

        jLabel27.setForeground(new java.awt.Color(0, 51, 255));
        jLabel27.setText("Outstanding Training");

        jLabel38.setForeground(new java.awt.Color(0, 51, 255));
        jLabel38.setText("Total Planned Training");

        jLabel39.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 51, 102));
        jLabel39.setText("(0)");

        jLabel40.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 51, 102));
        jLabel40.setText("(0)");

        jLabel41.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 51, 102));
        jLabel41.setText("(0)");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel39)
                .addGap(144, 144, 144)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(71, 71, 71))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(204, 204, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Incident Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Incident Statistics", jPanel13);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Incident Summary", jPanel14);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 255));
        jLabel9.setText("Incidents");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jLabel10.setText("(0)");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 255));
        jLabel11.setText("Overdue");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 102, 255));
        jLabel15.setText("Priority Incidents");

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 51));
        jLabel28.setText("(0)");

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 102, 255));
        jLabel29.setText("Pending Analysis");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel10)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(115, 115, 115)
                                .addComponent(jLabel28))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel15)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel29)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabel9)
                        .addComponent(jLabel29))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Risk Assessment", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N

        jLabel30.setForeground(new java.awt.Color(0, 102, 255));
        jLabel30.setText("Extreme Risk");

        jLabel31.setForeground(new java.awt.Color(0, 102, 255));
        jLabel31.setText("High Risk");

        jLabel32.setForeground(new java.awt.Color(0, 102, 255));
        jLabel32.setText("Moderate Risk");

        jLabel33.setForeground(new java.awt.Color(0, 102, 255));
        jLabel33.setText("Low Risk");

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 51, 102));
        jLabel34.setText("(0)");

        jLabel35.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 51, 102));
        jLabel35.setText("(0)");

        jLabel36.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 51, 102));
        jLabel36.setText("(0)");

        jLabel37.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 51, 102));
        jLabel37.setText("(0)");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 125, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Graphs and Reports", jPanel18);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jTabbedPane1.addTab("Risk Summary", jScrollPane4);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel34)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel35)))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(49, 49, 49)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel37)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addGap(1, 1, 1)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(476, 476, 476))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jMenu1.setText("Application");
        jMenu1.add(jSeparator28);

        jMenu16.setText("Auditing");
        jMenu1.add(jMenu16);
        jMenu1.add(jSeparator22);

        jMenu17.setText("Legal Complaince");
        jMenu1.add(jMenu17);
        jMenu1.add(jSeparator11);

        jMenu18.setText("Training ");
        jMenu1.add(jMenu18);
        jMenu1.add(jSeparator9);

        jMenu19.setText("Risk Assessment");
        jMenu1.add(jMenu19);
        jMenu1.add(jSeparator31);

        jMenu20.setText("System Documentation");
        jMenu1.add(jMenu20);
        jMenu1.add(jSeparator30);

        jMenu15.setText("Incident Management");

        jMenu7.setText("Employees");
        jMenu7.add(jSeparator21);

        jMenuItem6.setText("Manage Employees");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem6);
        jMenu7.add(jSeparator20);
        jMenu7.add(jSeparator18);

        jMenuItem9.setText("Assign Responsibilities");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem9);
        jMenu7.add(jSeparator19);

        jMenu15.add(jMenu7);

        jMenu8.setText("Incidents");
        jMenu8.add(jSeparator25);

        jMenuItem10.setText("Capture Incidents");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem10);
        jMenu8.add(jSeparator23);

        jMenuItem11.setText("Capture Additional Incident Details");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem11);
        jMenu8.add(jSeparator24);

        jMenu15.add(jMenu8);

        jMenu13.setText("Incident Risk Assessment");
        jMenu13.add(jSeparator39);

        jMenuItem20.setText("Incident Risk Rating");
        jMenu13.add(jMenuItem20);
        jMenu13.add(jSeparator37);

        jMenuItem21.setText("Define Invesigation Teams");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem21);
        jMenu13.add(jSeparator38);

        jMenu15.add(jMenu13);

        jMenu9.setText("Incident Analysis");
        jMenu9.add(jSeparator27);

        jMenuItem7.setText("Analysis an Incident");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem7);
        jMenu9.add(jSeparator26);

        jMenu5.setText("Manage Incident Analysis");
        jMenu5.add(jSeparator17);

        jMenuItem12.setText("High Priority Incidents");
        jMenu5.add(jMenuItem12);
        jMenu5.add(jSeparator15);

        jMenuItem13.setText("Outstanding / Pending Incident Analysis");
        jMenu5.add(jMenuItem13);
        jMenu5.add(jSeparator16);

        jMenu9.add(jMenu5);

        jMenu15.add(jMenu9);

        jMenu10.setText("Incident Corrective Action");
        jMenu10.add(jSeparator14);

        jMenuItem14.setText("Capture a corrective action");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem14);
        jMenu10.add(jSeparator12);

        jMenu6.setText("Analysis actions");
        jMenu6.add(jSeparator34);

        jMenuItem17.setText("Overdue Actions");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem17);
        jMenu6.add(jSeparator32);

        jMenuItem18.setText("Failed actions");
        jMenu6.add(jMenuItem18);

        jMenuItem27.setText("Incidents awaiting approvals");
        jMenu6.add(jMenuItem27);
        jMenu6.add(jSeparator33);

        jMenu10.add(jMenu6);
        jMenu10.add(jSeparator13);

        jMenu15.add(jMenu10);

        jMenu1.add(jMenu15);
        jMenu1.add(jSeparator29);

        jMenu21.setText("Key Corperate Documemys");
        jMenu1.add(jMenu21);
        jMenu1.add(jSeparator40);

        jMenu3.setText("System Configurations");
        jMenu3.add(jSeparator7);

        jMenuItem16.setText("jMenuItem16");
        jMenu3.add(jMenuItem16);
        jMenu3.add(jSeparator10);

        jMenu1.add(jMenu3);
        jMenu1.add(jSeparator8);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/exit.png"))); // NOI18N
        jMenuItem1.setText("Close");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu11.setText("View");
        jMenu11.add(jSeparator35);

        jMenu4.setText("Reports");
        jMenu4.add(jSeparator36);

        jMenuItem24.setText("jMenuItem24");
        jMenu4.add(jMenuItem24);
        jMenu4.add(jSeparator41);

        jMenuItem25.setText("jMenuItem25");
        jMenu4.add(jMenuItem25);
        jMenu4.add(jSeparator42);

        jMenuItem26.setText("jMenuItem26");
        jMenu4.add(jMenuItem26);
        jMenu4.add(jSeparator43);

        jMenu11.add(jMenu4);
        jMenu11.add(jSeparator45);

        jMenu14.setText("Incident");
        jMenu14.add(jSeparator44);

        jMenuItem15.setText("Overdue Incidents");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem15);
        jMenu14.add(jSeparator48);

        jMenuItem8.setText("High Priority Incidents");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem8);
        jMenu14.add(jSeparator47);

        jMenuItem22.setText("Pending Analysis");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem22);
        jMenu14.add(jSeparator46);

        jMenuItem23.setText("Signed Off Incidents");
        jMenu14.add(jMenuItem23);
        jMenu14.add(jSeparator49);

        jMenu11.add(jMenu14);
        jMenu11.add(jSeparator50);
        jMenu11.add(jSeparator51);

        jMenuBar1.add(jMenu11);

        jMenu12.setText("Help");

        jMenuItem19.setText("About");
        jMenu12.add(jMenuItem19);

        jMenuBar1.add(jMenu12);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private String sum;
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //jTabbedPane1.setUI(new WebTabbedPaneUI());
        //jLabel3.setForeground(Color.blue);
        Calendar expireDate = Calendar.getInstance();
        expireDate.set(2015, 11, 26);
        if(Calendar.getInstance().after(expireDate)){
            JOptionPane.showMessageDialog(Main.this, "Your trial has expired please contact developer");
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        //updateTable();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        /*int row = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        String selected = model.getValueAt(row, 0).toString();
        Incident iI = new Incident();
        iI.jButton1.setEnabled(false);
        //Incident.cboEmployeeNumber.setSelectedItem(selected);
        //Incident.cboIncidentType.removeAll();
        try{
        Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
        //Connection con = DriverManager.getConnection("jdbc:sqlite:Incident");
            PreparedStatement pst = con.prepareStatement("Select * from Incident where ReferenceNumber = ?");
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
                Incident.cboNature.setSelectedItem(natureOfIncident);
                //String ID = rs.getString("ContactNumber");
                //Incident.txtContactNumber.setText(ID.trim());
                //String email = rs.getString("EmailAddress");
                //Incident.txtEmail.setText(email.trim());
                //String department = rs.getString("Department");
                //Incident.txtDepartment.setText(department.trim());
                //String site = rs.getString("Site");
                //Incident.txtSite.setText(site.trim());
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(Main.this, e);
        }
        iI.setVisible(true);*/
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        /*int row = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        String selected = model.getValueAt(row, 0).toString();
        IncidentDetails iI = new IncidentDetails();
        try{
        Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement("Select * from IncidentDetail where Reference = ?");
            pst.setString(1, selected);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                String reference = rs.getString("Reference");
                IncidentDetails.cboReference.setSelectedItem(reference);
                String description = rs.getString("Description");
                IncidentDetails.txtDescription.setText(description.trim());
                String details = rs.getString("Details");
                IncidentDetails.txtDetails.setText(details.trim());
                String supervisor = rs.getString("Supervisor");
                IncidentDetails.cboSupervisorOnDuty.addItem(supervisor);
                String incident = rs.getString("Incident");
                IncidentDetails.txtIncident.setText(incident);
                String employeeNumber = rs.getString("EmployeeNumber");
                //Incident.cboNature.removeAllItems();
                IncidentDetails.txtEmployeeNumber.setText(employeeNumber);
                Date date = rs.getDate("DueDate");
                //java.util.Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateValue);
                IncidentDetails.jDateChooser1.setDate(date);
                //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                //Date date = new Date();
                //txtNew.setText(format.format(date));
                //IncidentDetails.txtDate.setText(format.format(date));
                //String email = rs.getString("EmailAddress");
                //Incident.txtEmail.setText(email.trim());
                //String department = rs.getString("Department");
                //Incident.txtDepartment.setText(department.trim());
                //String site = rs.getString("Site");
                //Incident.txtSite.setText(site.trim());
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(Main.this, e);
        }
        IncidentDetails.jButton1.setEnabled(false);
        IncidentDetails.jButton3.setEnabled(false);
        IncidentDetails.cboReference.setEnabled(false);
        IncidentDetails.cboSupervisorOnDuty.setEnabled(false);
        IncidentDetails.txtEmployeeNumber.setEditable(false);
        iI.setVisible(true);*/
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        //IncidentDetails iD = new IncidentDetails();
        //iD.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        //PersonDetails pD = new EmployeeDetails();
        EmployeeDetails.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        //Incident incident = new Incident();
        Incident.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        //IncidentAnalysis iA = new IncidentAnalysis();
        //iA.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        //Corrective corrective = new Corrective();
        //corrective.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
       //SiteManagement sM = new SiteManagement();
        SiteManagement.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        OverIncidents oI =  new OverIncidents();
        oI.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        InvestigationTeams iT = InvestigationTeams.getObj();
        iT.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem22ActionPerformed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu13;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu15;
    private javax.swing.JMenu jMenu16;
    private javax.swing.JMenu jMenu17;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenu jMenu19;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu20;
    private javax.swing.JMenu jMenu21;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator22;
    private javax.swing.JPopupMenu.Separator jSeparator23;
    private javax.swing.JPopupMenu.Separator jSeparator24;
    private javax.swing.JPopupMenu.Separator jSeparator25;
    private javax.swing.JPopupMenu.Separator jSeparator26;
    private javax.swing.JPopupMenu.Separator jSeparator27;
    private javax.swing.JPopupMenu.Separator jSeparator28;
    private javax.swing.JPopupMenu.Separator jSeparator29;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator30;
    private javax.swing.JPopupMenu.Separator jSeparator31;
    private javax.swing.JPopupMenu.Separator jSeparator32;
    private javax.swing.JPopupMenu.Separator jSeparator33;
    private javax.swing.JPopupMenu.Separator jSeparator34;
    private javax.swing.JPopupMenu.Separator jSeparator35;
    private javax.swing.JPopupMenu.Separator jSeparator36;
    private javax.swing.JPopupMenu.Separator jSeparator37;
    private javax.swing.JPopupMenu.Separator jSeparator38;
    private javax.swing.JPopupMenu.Separator jSeparator39;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator40;
    private javax.swing.JPopupMenu.Separator jSeparator41;
    private javax.swing.JPopupMenu.Separator jSeparator42;
    private javax.swing.JPopupMenu.Separator jSeparator43;
    private javax.swing.JPopupMenu.Separator jSeparator44;
    private javax.swing.JPopupMenu.Separator jSeparator45;
    private javax.swing.JPopupMenu.Separator jSeparator46;
    private javax.swing.JPopupMenu.Separator jSeparator47;
    private javax.swing.JPopupMenu.Separator jSeparator48;
    private javax.swing.JPopupMenu.Separator jSeparator49;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator50;
    private javax.swing.JPopupMenu.Separator jSeparator51;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane3;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane4;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane5;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane6;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane7;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    // End of variables declaration//GEN-END:variables

}
