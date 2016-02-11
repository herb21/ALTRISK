/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.table.WebTableUI;
import com.javadocking.DockingManager;
import com.javadocking.dock.Position;
import com.javadocking.dock.SplitDock;
import com.javadocking.dock.TabDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.dockable.DockingMode;
import com.javadocking.model.FloatDockModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import joxy.JoxyLookAndFeel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author MathomeTD
 */
public class Dash extends javax.swing.JFrame {

    /**
     * Creates new form Dash
     */
    public Dash() {
        try {
            UIManager.setLookAndFeel(new JoxyLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUndecorated(true);
        initComponents();
        updateTraining();
        fillmodules();
        Dockable dock = new DefaultDockable("JTree",jTree1,"SHEQ Documents",null,DockingMode.ALL);
        Dockable dock1 = new DefaultDockable("JTree1",jTree2,"Key Documents",null,DockingMode.ALL);
        Dockable dock2 = new DefaultDockable("JList",jList1,"Legal Copliance",null,DockingMode.ALL);
        Dockable dock5 = new DefaultDockable("JTable",jTable1,"Training Bookings",null,DockingMode.ALL);
        Dockable dock4 = new DefaultDockable("JPanel",jPanel1,"Incident Management",null,DockingMode.ALL);
        Dockable dock3 = new DefaultDockable("JPanel",jPanel2,"Audit",null,DockingMode.ALL);
        
        
        TabDock topDockRight = new TabDock();
        TabDock topDockRight1 = new TabDock();
        TabDock topDockRight2 = new TabDock();
        TabDock bottomDockRight = new TabDock();
        TabDock bottomDockRight1 = new TabDock();
        TabDock bottomDockRight2 = new TabDock();
        
        
        
        topDockRight.addDockable(dock, new Position(0));
        topDockRight1.addDockable(dock1, new Position(0));
        bottomDockRight.addDockable(dock2, new Position(0));
        bottomDockRight1.addDockable(dock3, new Position(0));
        bottomDockRight2.addDockable(dock4, new Position(0));
        topDockRight2.addDockable(dock5, new Position(0));
        
        
        SplitDock topSplitRight = new SplitDock();
        topSplitRight.addChildDock(topDockRight, new Position(Position.CENTER));
        
        SplitDock topSplitRight1 = new SplitDock();
        topSplitRight1.addChildDock(topDockRight1, new Position(Position.CENTER));
        
        SplitDock bottomSplitRight = new SplitDock();
        
        SplitDock topSplitRight4 = new SplitDock();
        topSplitRight4.addChildDock(topDockRight2, new Position(Position.CENTER));
        
        
        
        bottomSplitRight.addChildDock(bottomDockRight, new Position(Position.CENTER));
        SplitDock bottomSplitRight1 = new SplitDock();
        bottomSplitRight1.addChildDock(bottomDockRight1, new Position(Position.CENTER));
        SplitDock bottomSplitRight2 = new SplitDock();
        bottomSplitRight2.addChildDock(bottomDockRight2, new Position(Position.CENTER));
        
        
        
        
        
        
        FloatDockModel dockModel = new FloatDockModel();
        dockModel.addOwner("frame0", Dash.this);
        DockingManager.setDockModel(dockModel);
        
        
        dockModel.addRootDock("topDockRight",topSplitRight,Dash.this);
        jSplitPane1.setRightComponent(topSplitRight);
        
        dockModel.addRootDock("topDockRight1",topSplitRight1,Dash.this);
        jSplitPane3.setRightComponent(topSplitRight1);
        
        dockModel.addRootDock("bottomDockRight",bottomSplitRight,Dash.this);
        jSplitPane4.setRightComponent(bottomSplitRight);
        
        dockModel.addRootDock("bottomDockRight1",bottomSplitRight1,Dash.this);
        jSplitPane5.setLeftComponent(bottomSplitRight1);
        
        dockModel.addRootDock("bottomDockRight1",bottomSplitRight2,Dash.this);
        jSplitPane5.setRightComponent(bottomSplitRight2);
        
        dockModel.addRootDock("topDockRight",topSplitRight4,Dash.this);
        jSplitPane2.setRightComponent(topSplitRight4);
        
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
                //Incident.getObj().setVisible(true);
            }
        });
        jXTaskPane5.add(new AbstractAction() {
            {
                putValue(Action.NAME, "Incident Details : ");
                putValue(Action.SHORT_DESCRIPTION, "AN overview of learners status in the system");
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                IncidentDetails.getObj().setVisible(true);
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
                putValue(Action.NAME, "Incident Investigations : ");
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
                putValue(Action.NAME, "Corrective Actions : ");
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
                EmployeeDetails.getObj().setVisible(true);
            }
        });
    }

    
    
     private void updateTable(){
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
           JOptionPane.showMessageDialog(Dash.this, ex);
       }
} 

    
    private void updateTraining(){
       Statement pst = null;
       ResultSet rs = null;
       try {
           //Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           Connection con = DriverManager.getConnection("jdbc:derby:MTD","herbert","elsie1*#");
           //con = DriverManager.getConnection(dbUrl, user, password);
           pst = con.createStatement();
           rs = pst.executeQuery("Select LEARNERID,NAME,SURNAME,COURSEBOOKED,STARTDATE,ENDDATE,SITE from APP.BOOKCOURSE ");
           
           jTable1.setModel(DbUtils.resultSetToTableModel(rs)); 
           /*jTable2.removeColumn(jTable2.getColumnModel().getColumn(5));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(6));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(7));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(8));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(4));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(3));
           //jXTable1.removeColumn(jXTable1.getColumnModel().getColumn(9));*/
       }
       
       catch (Exception ex) {
           
           JOptionPane.showMessageDialog(Dash.this, ex);
           
           
       }
    
}
    private void fillmodules(){
        
        DefaultListModel m = new DefaultListModel();
        //String search = (String)cboTransaction.getSelectedItem();
        String sql = "Select * from APP.COURSEMODULES";
        try(Connection con = DriverManager.getConnection("jdbc:derby:MTD","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);) {
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    String ModuleName = rs.getString("DESCRIPTION");
                    m.addElement(ModuleName);
                }
            
                jList1.setModel(m);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
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

        desktopPane = new javax.swing.JDesktopPane();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane3 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane4 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane5 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane6 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane7 = new org.jdesktop.swingx.JXTaskPane();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree2 = new javax.swing.JTree();
        jPanel5 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSplitPane5 = new javax.swing.JSplitPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu1 = new javax.swing.JMenu();
        jMenu36 = new javax.swing.JMenu();
        jSeparator133 = new javax.swing.JPopupMenu.Separator();
        jMenuItem64 = new javax.swing.JMenuItem();
        jSeparator130 = new javax.swing.JPopupMenu.Separator();
        jMenuItem18 = new javax.swing.JMenuItem();
        jSeparator131 = new javax.swing.JPopupMenu.Separator();
        jMenuItem65 = new javax.swing.JMenuItem();
        jSeparator132 = new javax.swing.JPopupMenu.Separator();
        jSeparator136 = new javax.swing.JPopupMenu.Separator();
        jMenu35 = new javax.swing.JMenu();
        jSeparator124 = new javax.swing.JPopupMenu.Separator();
        jMenu33 = new javax.swing.JMenu();
        jSeparator122 = new javax.swing.JPopupMenu.Separator();
        jMenuItem60 = new javax.swing.JMenuItem();
        jSeparator134 = new javax.swing.JPopupMenu.Separator();
        jMenuItem58 = new javax.swing.JMenuItem();
        jSeparator125 = new javax.swing.JPopupMenu.Separator();
        jMenuItem63 = new javax.swing.JMenuItem();
        jSeparator123 = new javax.swing.JPopupMenu.Separator();
        jSeparator126 = new javax.swing.JPopupMenu.Separator();
        jMenu34 = new javax.swing.JMenu();
        jSeparator129 = new javax.swing.JPopupMenu.Separator();
        jMenuItem61 = new javax.swing.JMenuItem();
        jSeparator135 = new javax.swing.JPopupMenu.Separator();
        jMenuItem59 = new javax.swing.JMenuItem();
        jSeparator128 = new javax.swing.JPopupMenu.Separator();
        jMenuItem62 = new javax.swing.JMenuItem();
        jSeparator127 = new javax.swing.JPopupMenu.Separator();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jSeparator37 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator84 = new javax.swing.JPopupMenu.Separator();
        jSeparator88 = new javax.swing.JPopupMenu.Separator();
        jSeparator36 = new javax.swing.JPopupMenu.Separator();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();
        jSeparator43 = new javax.swing.JPopupMenu.Separator();
        jMenu19 = new javax.swing.JMenu();
        jSeparator71 = new javax.swing.JPopupMenu.Separator();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator70 = new javax.swing.JPopupMenu.Separator();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator69 = new javax.swing.JPopupMenu.Separator();
        jMenu25 = new javax.swing.JMenu();
        jSeparator68 = new javax.swing.JPopupMenu.Separator();
        jSeparator42 = new javax.swing.JPopupMenu.Separator();
        jMenu20 = new javax.swing.JMenu();
        jSeparator74 = new javax.swing.JPopupMenu.Separator();
        jMenuItem25 = new javax.swing.JMenuItem();
        jSeparator73 = new javax.swing.JPopupMenu.Separator();
        jMenuItem26 = new javax.swing.JMenuItem();
        jSeparator72 = new javax.swing.JPopupMenu.Separator();
        jSeparator41 = new javax.swing.JPopupMenu.Separator();
        jMenu21 = new javax.swing.JMenu();
        jSeparator77 = new javax.swing.JPopupMenu.Separator();
        jMenuItem27 = new javax.swing.JMenuItem();
        jSeparator76 = new javax.swing.JPopupMenu.Separator();
        jMenuItem28 = new javax.swing.JMenuItem();
        jSeparator75 = new javax.swing.JPopupMenu.Separator();
        jSeparator40 = new javax.swing.JPopupMenu.Separator();
        jMenu22 = new javax.swing.JMenu();
        jSeparator81 = new javax.swing.JPopupMenu.Separator();
        jMenuItem29 = new javax.swing.JMenuItem();
        jSeparator80 = new javax.swing.JPopupMenu.Separator();
        jMenuItem30 = new javax.swing.JMenuItem();
        jSeparator79 = new javax.swing.JPopupMenu.Separator();
        jMenuItem32 = new javax.swing.JMenuItem();
        jSeparator78 = new javax.swing.JPopupMenu.Separator();
        jSeparator39 = new javax.swing.JPopupMenu.Separator();
        jMenu23 = new javax.swing.JMenu();
        jSeparator38 = new javax.swing.JPopupMenu.Separator();
        jMenuItem31 = new javax.swing.JMenuItem();
        jSeparator83 = new javax.swing.JPopupMenu.Separator();
        jMenu24 = new javax.swing.JMenu();
        jSeparator96 = new javax.swing.JPopupMenu.Separator();
        jMenuItem56 = new javax.swing.JMenuItem();
        jSeparator95 = new javax.swing.JPopupMenu.Separator();
        jMenuItem57 = new javax.swing.JMenuItem();
        jSeparator94 = new javax.swing.JPopupMenu.Separator();
        jSeparator82 = new javax.swing.JPopupMenu.Separator();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        jSeparator63 = new javax.swing.JPopupMenu.Separator();
        jMenu13 = new javax.swing.JMenu();
        jSeparator62 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator61 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator60 = new javax.swing.JPopupMenu.Separator();
        jSeparator67 = new javax.swing.JPopupMenu.Separator();
        jMenu14 = new javax.swing.JMenu();
        jSeparator52 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator51 = new javax.swing.JPopupMenu.Separator();
        jSeparator66 = new javax.swing.JPopupMenu.Separator();
        jMenu15 = new javax.swing.JMenu();
        jSeparator50 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jSeparator49 = new javax.swing.JPopupMenu.Separator();
        jMenu17 = new javax.swing.JMenu();
        jSeparator48 = new javax.swing.JPopupMenu.Separator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator47 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator46 = new javax.swing.JPopupMenu.Separator();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator45 = new javax.swing.JPopupMenu.Separator();
        jSeparator65 = new javax.swing.JPopupMenu.Separator();
        jMenu16 = new javax.swing.JMenu();
        jSeparator54 = new javax.swing.JPopupMenu.Separator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator53 = new javax.swing.JPopupMenu.Separator();
        jMenu18 = new javax.swing.JMenu();
        jSeparator55 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jSeparator58 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator57 = new javax.swing.JPopupMenu.Separator();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator56 = new javax.swing.JPopupMenu.Separator();
        jSeparator64 = new javax.swing.JPopupMenu.Separator();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenu6 = new javax.swing.JMenu();
        jSeparator32 = new javax.swing.JPopupMenu.Separator();
        jMenu11 = new javax.swing.JMenu();
        jSeparator35 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator85 = new javax.swing.JPopupMenu.Separator();
        jMenuItem42 = new javax.swing.JMenuItem();
        jSeparator34 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator89 = new javax.swing.JPopupMenu.Separator();
        jMenuItem45 = new javax.swing.JMenuItem();
        jSeparator86 = new javax.swing.JPopupMenu.Separator();
        jMenuItem43 = new javax.swing.JMenuItem();
        jSeparator33 = new javax.swing.JPopupMenu.Separator();
        jSeparator31 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator30 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator29 = new javax.swing.JPopupMenu.Separator();
        jMenu12 = new javax.swing.JMenu();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        jSeparator25 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu7 = new javax.swing.JMenu();
        jSeparator141 = new javax.swing.JPopupMenu.Separator();
        jMenuItem69 = new javax.swing.JMenuItem();
        jSeparator140 = new javax.swing.JPopupMenu.Separator();
        jMenuItem44 = new javax.swing.JMenuItem();
        jSeparator87 = new javax.swing.JPopupMenu.Separator();
        jMenuItem66 = new javax.swing.JMenuItem();
        jSeparator138 = new javax.swing.JPopupMenu.Separator();
        jMenuItem67 = new javax.swing.JMenuItem();
        jSeparator137 = new javax.swing.JPopupMenu.Separator();
        jMenuItem68 = new javax.swing.JMenuItem();
        jSeparator139 = new javax.swing.JPopupMenu.Separator();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenu27 = new javax.swing.JMenu();
        jSeparator142 = new javax.swing.JPopupMenu.Separator();
        jMenu37 = new javax.swing.JMenu();
        jMenu38 = new javax.swing.JMenu();
        jSeparator143 = new javax.swing.JPopupMenu.Separator();
        jMenuItem70 = new javax.swing.JMenuItem();
        jSeparator144 = new javax.swing.JPopupMenu.Separator();
        jMenuItem71 = new javax.swing.JMenuItem();
        jSeparator145 = new javax.swing.JPopupMenu.Separator();
        jMenuItem72 = new javax.swing.JMenuItem();
        jSeparator146 = new javax.swing.JPopupMenu.Separator();
        jSeparator147 = new javax.swing.JPopupMenu.Separator();
        jMenuItem73 = new javax.swing.JMenuItem();
        jSeparator148 = new javax.swing.JPopupMenu.Separator();
        jMenuItem74 = new javax.swing.JMenuItem();
        jSeparator149 = new javax.swing.JPopupMenu.Separator();
        jSeparator150 = new javax.swing.JPopupMenu.Separator();
        jMenu39 = new javax.swing.JMenu();
        jSeparator151 = new javax.swing.JPopupMenu.Separator();
        jMenuItem75 = new javax.swing.JMenuItem();
        jSeparator152 = new javax.swing.JPopupMenu.Separator();
        jMenuItem76 = new javax.swing.JMenuItem();
        jSeparator153 = new javax.swing.JPopupMenu.Separator();
        jSeparator154 = new javax.swing.JPopupMenu.Separator();
        jMenu40 = new javax.swing.JMenu();
        jSeparator155 = new javax.swing.JPopupMenu.Separator();
        jMenuItem77 = new javax.swing.JMenuItem();
        jSeparator156 = new javax.swing.JPopupMenu.Separator();
        jMenuItem78 = new javax.swing.JMenuItem();
        jSeparator157 = new javax.swing.JPopupMenu.Separator();
        jSeparator158 = new javax.swing.JPopupMenu.Separator();
        jSeparator159 = new javax.swing.JPopupMenu.Separator();
        editMenu = new javax.swing.JMenu();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenu28 = new javax.swing.JMenu();
        jSeparator99 = new javax.swing.JPopupMenu.Separator();
        jMenuItem46 = new javax.swing.JMenuItem();
        jSeparator98 = new javax.swing.JPopupMenu.Separator();
        jMenuItem47 = new javax.swing.JMenuItem();
        jSeparator97 = new javax.swing.JPopupMenu.Separator();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        copyMenuItem = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        pasteMenuItem = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenu41 = new javax.swing.JMenu();
        jSeparator162 = new javax.swing.JPopupMenu.Separator();
        deleteMenuItem = new javax.swing.JMenuItem();
        jSeparator160 = new javax.swing.JPopupMenu.Separator();
        jMenuItem79 = new javax.swing.JMenuItem();
        jSeparator161 = new javax.swing.JPopupMenu.Separator();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        systemMenu = new javax.swing.JMenu();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        jMenu8 = new javax.swing.JMenu();
        jSeparator118 = new javax.swing.JPopupMenu.Separator();
        jMenu30 = new javax.swing.JMenu();
        jSeparator116 = new javax.swing.JPopupMenu.Separator();
        jMenuItem34 = new javax.swing.JMenuItem();
        jSeparator115 = new javax.swing.JPopupMenu.Separator();
        jMenu31 = new javax.swing.JMenu();
        jSeparator114 = new javax.swing.JPopupMenu.Separator();
        jMenuItem50 = new javax.swing.JMenuItem();
        jSeparator113 = new javax.swing.JPopupMenu.Separator();
        jMenuItem52 = new javax.swing.JMenuItem();
        jSeparator112 = new javax.swing.JPopupMenu.Separator();
        jMenuItem53 = new javax.swing.JMenuItem();
        jSeparator111 = new javax.swing.JPopupMenu.Separator();
        jSeparator121 = new javax.swing.JPopupMenu.Separator();
        jMenu32 = new javax.swing.JMenu();
        jSeparator109 = new javax.swing.JPopupMenu.Separator();
        jMenuItem51 = new javax.swing.JMenuItem();
        jSeparator110 = new javax.swing.JPopupMenu.Separator();
        jMenuItem54 = new javax.swing.JMenuItem();
        jSeparator107 = new javax.swing.JPopupMenu.Separator();
        jMenuItem55 = new javax.swing.JMenuItem();
        jSeparator108 = new javax.swing.JPopupMenu.Separator();
        jSeparator120 = new javax.swing.JPopupMenu.Separator();
        jSeparator119 = new javax.swing.JPopupMenu.Separator();
        jMenu29 = new javax.swing.JMenu();
        jSeparator92 = new javax.swing.JPopupMenu.Separator();
        jMenuItem33 = new javax.swing.JMenuItem();
        jSeparator91 = new javax.swing.JPopupMenu.Separator();
        jMenuItem48 = new javax.swing.JMenuItem();
        jSeparator93 = new javax.swing.JPopupMenu.Separator();
        jMenuItem49 = new javax.swing.JMenuItem();
        jSeparator90 = new javax.swing.JPopupMenu.Separator();
        jSeparator117 = new javax.swing.JPopupMenu.Separator();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        jMenu9 = new javax.swing.JMenu();
        jSeparator101 = new javax.swing.JPopupMenu.Separator();
        jMenuItem37 = new javax.swing.JMenuItem();
        jSeparator103 = new javax.swing.JPopupMenu.Separator();
        jMenuItem38 = new javax.swing.JMenuItem();
        jSeparator102 = new javax.swing.JPopupMenu.Separator();
        jMenu26 = new javax.swing.JMenu();
        jSeparator105 = new javax.swing.JPopupMenu.Separator();
        jMenuItem39 = new javax.swing.JMenuItem();
        jSeparator44 = new javax.swing.JPopupMenu.Separator();
        jMenuItem40 = new javax.swing.JMenuItem();
        jSeparator100 = new javax.swing.JPopupMenu.Separator();
        jMenuItem41 = new javax.swing.JMenuItem();
        jSeparator104 = new javax.swing.JPopupMenu.Separator();
        jSeparator106 = new javax.swing.JPopupMenu.Separator();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        helpMenu1 = new javax.swing.JMenu();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        contentMenuItem1 = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        aboutMenuItem1 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jSeparator59 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 255));

        jXTaskPaneContainer1.setBackground(new java.awt.Color(255, 255, 255));
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

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("System Documents");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("SHEQ Documents");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("SHEQ Policies");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("SHEQ Procedures");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("SOPS's");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Work Instructions");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Records");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Planning");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("basketball");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("soccer");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("football");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hockey");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Implementation");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hot dogs");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("pizza");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Actions");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("ravioli");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("bananas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Management Reviews");
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jTree1);

        jSplitPane1.setRightComponent(jScrollPane1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 251, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jPanel3);

        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane2.setViewportView(jTree2);

        jSplitPane3.setRightComponent(jScrollPane2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 269, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jSplitPane3.setLeftComponent(jPanel5);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jSplitPane4.setRightComponent(jScrollPane3);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("A");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("L");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 255));
        jLabel3.setText("T");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 255));
        jLabel4.setText("R");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 255));
        jLabel5.setText("I");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setText("S");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 255));
        jLabel7.setText("K");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jSplitPane4.setLeftComponent(jPanel6);

        jSplitPane5.setDividerLocation(400);
        jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

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
        jScrollPane4.setViewportView(jTable1);

        jSplitPane5.setLeftComponent(jScrollPane4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jSplitPane5.setRightComponent(jPanel1);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSplitPane3)
                    .addComponent(jSplitPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jSplitPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSplitPane4))
                    .addComponent(jSplitPane2))
                .addContainerGap())
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSplitPane4)
                            .addComponent(jSplitPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addComponent(jSplitPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jDesktopPane1.setLayer(jXTaskPaneContainer1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jSplitPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jSplitPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jSplitPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jSplitPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jSplitPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        desktopPane.add(jDesktopPane1);
        jDesktopPane1.setBounds(10, 10, 1420, 790);

        fileMenu.setForeground(new java.awt.Color(51, 51, 51));
        fileMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/file98.png"))); // NOI18N
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");
        fileMenu.add(jSeparator1);

        jMenu1.setForeground(new java.awt.Color(51, 51, 51));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/tie21.png"))); // NOI18N
        jMenu1.setText("Audit");

        jMenu36.setText("Inspections");
        jMenu36.add(jSeparator133);

        jMenuItem64.setText("Create Inspection");
        jMenu36.add(jMenuItem64);
        jMenu36.add(jSeparator130);

        jMenuItem18.setText("Inspection List");
        jMenu36.add(jMenuItem18);
        jMenu36.add(jSeparator131);

        jMenuItem65.setText("Corrective Action");
        jMenu36.add(jMenuItem65);
        jMenu36.add(jSeparator132);

        jMenu1.add(jMenu36);
        jMenu1.add(jSeparator136);

        jMenu35.setText("Audits");
        jMenu35.add(jSeparator124);

        jMenu33.setText("Internal Audits");
        jMenu33.add(jSeparator122);

        jMenuItem60.setText("Create Internal Audit");
        jMenu33.add(jMenuItem60);
        jMenu33.add(jSeparator134);

        jMenuItem58.setText("Audit List");
        jMenu33.add(jMenuItem58);
        jMenu33.add(jSeparator125);

        jMenuItem63.setText("Corrective Action");
        jMenu33.add(jMenuItem63);
        jMenu33.add(jSeparator123);

        jMenu35.add(jMenu33);
        jMenu35.add(jSeparator126);

        jMenu34.setText("External Audits");
        jMenu34.add(jSeparator129);

        jMenuItem61.setText("Create External Audit");
        jMenu34.add(jMenuItem61);
        jMenu34.add(jSeparator135);

        jMenuItem59.setText("Audit List");
        jMenu34.add(jMenuItem59);
        jMenu34.add(jSeparator128);

        jMenuItem62.setText("Corrective Action");
        jMenu34.add(jMenuItem62);
        jMenu34.add(jSeparator127);

        jMenu35.add(jMenu34);

        jMenu1.add(jMenu35);

        fileMenu.add(jMenu1);
        fileMenu.add(jSeparator9);

        jMenu2.setForeground(new java.awt.Color(51, 51, 51));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/magistrate.png"))); // NOI18N
        jMenu2.setText("Legal Compliance");
        jMenu2.add(jSeparator37);

        jMenuItem10.setText("Legal Register");
        jMenu2.add(jMenuItem10);
        jMenu2.add(jSeparator84);
        jMenu2.add(jSeparator88);
        jMenu2.add(jSeparator36);

        fileMenu.add(jMenu2);
        fileMenu.add(jSeparator8);

        jMenu3.setForeground(new java.awt.Color(51, 51, 51));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/class6.png"))); // NOI18N
        jMenu3.setText("Training Management");
        jMenu3.add(jSeparator43);

        jMenu19.setText("Skills Management");
        jMenu19.add(jSeparator71);

        jMenuItem23.setText("Employee Evaluation");
        jMenu19.add(jMenuItem23);
        jMenu19.add(jSeparator70);

        jMenuItem24.setText("Skills Definations");
        jMenu19.add(jMenuItem24);
        jMenu19.add(jSeparator69);

        jMenu25.setText("jMenu25");
        jMenu19.add(jMenu25);
        jMenu19.add(jSeparator68);

        jMenu3.add(jMenu19);
        jMenu3.add(jSeparator42);

        jMenu20.setText("Training Courses");
        jMenu20.add(jSeparator74);

        jMenuItem25.setText("Create Course");
        jMenu20.add(jMenuItem25);
        jMenu20.add(jSeparator73);

        jMenuItem26.setText("Course List");
        jMenu20.add(jMenuItem26);
        jMenu20.add(jSeparator72);

        jMenu3.add(jMenu20);
        jMenu3.add(jSeparator41);

        jMenu21.setText("Views");
        jMenu21.add(jSeparator77);

        jMenuItem27.setText("Booked Training");
        jMenu21.add(jMenuItem27);
        jMenu21.add(jSeparator76);

        jMenuItem28.setText("Employee Recommended Training");
        jMenu21.add(jMenuItem28);
        jMenu21.add(jSeparator75);

        jMenu3.add(jMenu21);
        jMenu3.add(jSeparator40);

        jMenu22.setText("Training Reports");
        jMenu22.add(jSeparator81);

        jMenuItem29.setText("Achieved Training ");
        jMenu22.add(jMenuItem29);
        jMenu22.add(jSeparator80);

        jMenuItem30.setText("Booked Training ");
        jMenu22.add(jMenuItem30);
        jMenu22.add(jSeparator79);

        jMenuItem32.setText("Training Pending");
        jMenu22.add(jMenuItem32);
        jMenu22.add(jSeparator78);

        jMenu3.add(jMenu22);
        jMenu3.add(jSeparator39);

        jMenu23.setText("Training Management");
        jMenu23.add(jSeparator38);

        jMenuItem31.setText("Course Management");
        jMenu23.add(jMenuItem31);
        jMenu23.add(jSeparator83);

        jMenu24.setText("Book Course");
        jMenu24.add(jSeparator96);

        jMenuItem56.setText("Course Reservation");
        jMenu24.add(jMenuItem56);
        jMenu24.add(jSeparator95);

        jMenuItem57.setText("jMenuItem57");
        jMenu24.add(jMenuItem57);
        jMenu24.add(jSeparator94);

        jMenu23.add(jMenu24);
        jMenu23.add(jSeparator82);

        jMenu3.add(jMenu23);

        fileMenu.add(jMenu3);
        fileMenu.add(jSeparator7);

        jMenu4.setForeground(new java.awt.Color(51, 51, 51));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/mine4.png"))); // NOI18N
        jMenu4.setText("Risk Assessment");
        fileMenu.add(jMenu4);
        fileMenu.add(jSeparator6);

        jMenu5.setForeground(new java.awt.Color(51, 51, 51));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/accident3.png"))); // NOI18N
        jMenu5.setText("Incident Management");
        jMenu5.add(jSeparator63);

        jMenu13.setText("Incident");
        jMenu13.add(jSeparator62);

        jMenuItem11.setText("Create Incident");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem11);
        jMenu13.add(jSeparator61);

        jMenuItem12.setText("Additional Information");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu13.add(jMenuItem12);
        jMenu13.add(jSeparator60);

        jMenu5.add(jMenu13);
        jMenu5.add(jSeparator67);

        jMenu14.setText("Risk Assessment");
        jMenu14.add(jSeparator52);

        jMenuItem13.setText("Rate Incident");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem13);
        jMenu14.add(jSeparator51);

        jMenu5.add(jMenu14);
        jMenu5.add(jSeparator66);

        jMenu15.setText("Analysis");
        jMenu15.add(jSeparator50);

        jMenuItem14.setText("Analysie Incident");
        jMenu15.add(jMenuItem14);
        jMenu15.add(jSeparator49);

        jMenu17.setText("Manage Incidents");
        jMenu17.add(jSeparator48);

        jMenuItem15.setText("High priority");
        jMenu17.add(jMenuItem15);
        jMenu17.add(jSeparator47);

        jMenuItem16.setText("Medium Priority");
        jMenu17.add(jMenuItem16);
        jMenu17.add(jSeparator46);

        jMenuItem17.setText("Low Priority");
        jMenu17.add(jMenuItem17);
        jMenu17.add(jSeparator45);

        jMenu15.add(jMenu17);

        jMenu5.add(jMenu15);
        jMenu5.add(jSeparator65);

        jMenu16.setText("Corrective Actions");
        jMenu16.add(jSeparator54);

        jMenuItem19.setText("Add Corrective Actions");
        jMenu16.add(jMenuItem19);
        jMenu16.add(jSeparator53);

        jMenu18.setText("Action Analysis");
        jMenu18.add(jSeparator55);

        jMenuItem20.setText("Overdues Action");
        jMenu18.add(jMenuItem20);
        jMenu18.add(jSeparator58);

        jMenuItem21.setText("Failed Actions");
        jMenu18.add(jMenuItem21);
        jMenu18.add(jSeparator57);

        jMenuItem22.setText("Actions pending Approval");
        jMenu18.add(jMenuItem22);
        jMenu18.add(jSeparator56);

        jMenu16.add(jMenu18);

        jMenu5.add(jMenu16);
        jMenu5.add(jSeparator64);

        fileMenu.add(jMenu5);
        fileMenu.add(jSeparator5);

        jMenu6.setForeground(new java.awt.Color(51, 51, 51));
        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/search99.png"))); // NOI18N
        jMenu6.setText("System Documents");
        jMenu6.add(jSeparator32);

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/books8.png"))); // NOI18N
        jMenu11.setText("SHEQ Documents");
        jMenu11.add(jSeparator35);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/newspaper18.png"))); // NOI18N
        jMenuItem8.setText("Policies");
        jMenu11.add(jMenuItem8);
        jMenu11.add(jSeparator85);

        jMenuItem42.setText("Coperate Standard");
        jMenu11.add(jMenuItem42);
        jMenu11.add(jSeparator34);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/sign20.png"))); // NOI18N
        jMenuItem9.setText("SOPs / Procedures");
        jMenu11.add(jMenuItem9);
        jMenu11.add(jSeparator89);

        jMenuItem45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/bill.png"))); // NOI18N
        jMenuItem45.setText("Work Instructions / Forms");
        jMenu11.add(jMenuItem45);
        jMenu11.add(jSeparator86);

        jMenuItem43.setText("Records");
        jMenu11.add(jMenuItem43);
        jMenu11.add(jSeparator33);

        jMenu6.add(jMenu11);
        jMenu6.add(jSeparator31);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/management3.png"))); // NOI18N
        jMenuItem3.setText("Planning");
        jMenu6.add(jMenuItem3);
        jMenu6.add(jSeparator30);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/businessman270.png"))); // NOI18N
        jMenuItem4.setText("Implementation");
        jMenu6.add(jMenuItem4);
        jMenu6.add(jSeparator29);

        jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/report1.png"))); // NOI18N
        jMenu12.setText("Actions");
        jMenu12.add(jSeparator28);

        jMenuItem6.setText("Checkings");
        jMenu12.add(jMenuItem6);
        jMenu12.add(jSeparator26);

        jMenuItem7.setText("Corrective Actions");
        jMenu12.add(jMenuItem7);
        jMenu12.add(jSeparator27);

        jMenu6.add(jMenu12);
        jMenu6.add(jSeparator25);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/wall clock.png"))); // NOI18N
        jMenuItem5.setText("Management Reviews");
        jMenu6.add(jMenuItem5);
        jMenu6.add(jSeparator24);

        fileMenu.add(jMenu6);
        fileMenu.add(jSeparator4);

        jMenu7.setForeground(new java.awt.Color(51, 51, 51));
        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/doc.png"))); // NOI18N
        jMenu7.setText("Key Coperate Document");
        jMenu7.add(jSeparator141);

        jMenuItem69.setText("Create Document");
        jMenu7.add(jMenuItem69);
        jMenu7.add(jSeparator140);

        jMenuItem44.setText("Management");
        jMenu7.add(jMenuItem44);
        jMenu7.add(jSeparator87);

        jMenuItem66.setText("Supervisors");
        jMenu7.add(jMenuItem66);
        jMenu7.add(jSeparator138);

        jMenuItem67.setText("Floor");
        jMenu7.add(jMenuItem67);
        jMenu7.add(jSeparator137);

        jMenuItem68.setText("General / Memos");
        jMenu7.add(jMenuItem68);
        jMenu7.add(jSeparator139);

        fileMenu.add(jMenu7);
        fileMenu.add(jSeparator3);

        exitMenuItem.setForeground(new java.awt.Color(51, 51, 51));
        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/power-16.png"))); // NOI18N
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);
        fileMenu.add(jSeparator2);

        menuBar.add(fileMenu);

        jMenu27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/men16.png"))); // NOI18N
        jMenu27.setText("Environmental Management");
        jMenu27.add(jSeparator142);

        jMenu37.setText("Water");

        jMenu38.setText("Surface Water Quality");
        jMenu38.add(jSeparator143);

        jMenuItem70.setText("Daily");
        jMenuItem70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem70ActionPerformed(evt);
            }
        });
        jMenu38.add(jMenuItem70);
        jMenu38.add(jSeparator144);

        jMenuItem71.setText("Weekly");
        jMenu38.add(jMenuItem71);
        jMenu38.add(jSeparator145);

        jMenuItem72.setText("Monthly");
        jMenu38.add(jMenuItem72);
        jMenu38.add(jSeparator146);

        jMenu37.add(jMenu38);
        jMenu37.add(jSeparator147);

        jMenuItem73.setText("Ground Water Quality");
        jMenu37.add(jMenuItem73);
        jMenu37.add(jSeparator148);

        jMenuItem74.setText("Ground Water Level");
        jMenu37.add(jMenuItem74);
        jMenu37.add(jSeparator149);

        jMenu27.add(jMenu37);
        jMenu27.add(jSeparator150);

        jMenu39.setText("Air");
        jMenu39.add(jSeparator151);

        jMenuItem75.setText("Ambient Air Quality");
        jMenu39.add(jMenuItem75);
        jMenu39.add(jSeparator152);

        jMenuItem76.setText("Stake Emmision");
        jMenu39.add(jMenuItem76);
        jMenu39.add(jSeparator153);

        jMenu27.add(jMenu39);
        jMenu27.add(jSeparator154);

        jMenu40.setText("Occupation Health");
        jMenu40.add(jSeparator155);

        jMenuItem77.setText("Work Place Monitoring");
        jMenu40.add(jMenuItem77);
        jMenu40.add(jSeparator156);

        jMenuItem78.setText("Personnel Monitoring");
        jMenu40.add(jMenuItem78);
        jMenu40.add(jSeparator157);

        jMenu27.add(jMenu40);
        jMenu27.add(jSeparator158);
        jMenu27.add(jSeparator159);

        menuBar.add(jMenu27);

        editMenu.setForeground(new java.awt.Color(51, 51, 51));
        editMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/men16.png"))); // NOI18N
        editMenu.setMnemonic('e');
        editMenu.setText("People");
        editMenu.add(jSeparator10);

        jMenu28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/worker25.png"))); // NOI18N
        jMenu28.setText("Employees");
        jMenu28.add(jSeparator99);

        jMenuItem46.setText("Create employee");
        jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem46ActionPerformed(evt);
            }
        });
        jMenu28.add(jMenuItem46);
        jMenu28.add(jSeparator98);

        jMenuItem47.setText("Assign Responsibilities");
        jMenu28.add(jMenuItem47);
        jMenu28.add(jSeparator97);

        editMenu.add(jMenu28);
        editMenu.add(jSeparator14);

        copyMenuItem.setForeground(new java.awt.Color(51, 51, 51));
        copyMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/class6.png"))); // NOI18N
        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Learners");
        copyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(copyMenuItem);
        editMenu.add(jSeparator13);

        pasteMenuItem.setForeground(new java.awt.Color(51, 51, 51));
        pasteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/user2.png"))); // NOI18N
        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Users");
        pasteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(pasteMenuItem);
        editMenu.add(jSeparator12);

        jMenu41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/users6.png"))); // NOI18N
        jMenu41.setText("Groups");
        jMenu41.add(jSeparator162);

        deleteMenuItem.setForeground(new java.awt.Color(51, 51, 51));
        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Audit Teams");
        jMenu41.add(deleteMenuItem);
        jMenu41.add(jSeparator160);

        jMenuItem79.setText("Investigation Teams");
        jMenu41.add(jMenuItem79);
        jMenu41.add(jSeparator161);

        editMenu.add(jMenu41);
        editMenu.add(jSeparator11);

        menuBar.add(editMenu);

        systemMenu.setForeground(new java.awt.Color(51, 51, 51));
        systemMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/cogwheel28.png"))); // NOI18N
        systemMenu.setMnemonic('h');
        systemMenu.setText("System Configuration");
        systemMenu.add(jSeparator22);

        jMenu8.setForeground(new java.awt.Color(51, 51, 51));
        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/data90.png"))); // NOI18N
        jMenu8.setText("Import / Export ");
        jMenu8.add(jSeparator118);

        jMenu30.setText("Batch Export");
        jMenu30.add(jSeparator116);

        jMenuItem34.setText("Employee Master File");
        jMenu30.add(jMenuItem34);
        jMenu30.add(jSeparator115);

        jMenu31.setText("Training");
        jMenu31.add(jSeparator114);

        jMenuItem50.setText("jMenuItem50");
        jMenu31.add(jMenuItem50);
        jMenu31.add(jSeparator113);

        jMenuItem52.setText("jMenuItem52");
        jMenu31.add(jMenuItem52);
        jMenu31.add(jSeparator112);

        jMenuItem53.setText("jMenuItem53");
        jMenu31.add(jMenuItem53);
        jMenu31.add(jSeparator111);

        jMenu30.add(jMenu31);
        jMenu30.add(jSeparator121);

        jMenu32.setText("Incidents");
        jMenu32.add(jSeparator109);

        jMenuItem51.setText("jMenuItem51");
        jMenu32.add(jMenuItem51);
        jMenu32.add(jSeparator110);

        jMenuItem54.setText("jMenuItem54");
        jMenu32.add(jMenuItem54);
        jMenu32.add(jSeparator107);

        jMenuItem55.setText("jMenuItem55");
        jMenu32.add(jMenuItem55);
        jMenu32.add(jSeparator108);

        jMenu30.add(jMenu32);
        jMenu30.add(jSeparator120);

        jMenu8.add(jMenu30);
        jMenu8.add(jSeparator119);

        jMenu29.setText("Batch Import");
        jMenu29.add(jSeparator92);

        jMenuItem33.setText("System Users");
        jMenuItem33.setActionCommand("BatchImport");
        jMenu29.add(jMenuItem33);
        jMenu29.add(jSeparator91);

        jMenuItem48.setText("Modules");
        jMenu29.add(jMenuItem48);
        jMenu29.add(jSeparator93);

        jMenuItem49.setText("SHEQ Documents");
        jMenu29.add(jMenuItem49);
        jMenu29.add(jSeparator90);

        jMenu8.add(jMenu29);
        jMenu8.add(jSeparator117);

        systemMenu.add(jMenu8);
        systemMenu.add(jSeparator21);

        jMenu9.setForeground(new java.awt.Color(51, 51, 51));
        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/wireless29.png"))); // NOI18N
        jMenu9.setText("System Configurations");
        jMenu9.add(jSeparator101);

        jMenuItem37.setText("People");
        jMenu9.add(jMenuItem37);
        jMenu9.add(jSeparator103);

        jMenuItem38.setText("Addon Modules");
        jMenu9.add(jMenuItem38);
        jMenu9.add(jSeparator102);

        jMenu26.setText("ALTRISK Configuration");
        jMenu26.add(jSeparator105);

        jMenuItem39.setText("Assign Investigation Teams");
        jMenu26.add(jMenuItem39);
        jMenu26.add(jSeparator44);

        jMenuItem40.setText("Create Learner");
        jMenu26.add(jMenuItem40);
        jMenu26.add(jSeparator100);

        jMenuItem41.setText("Site Management");
        jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem41ActionPerformed(evt);
            }
        });
        jMenu26.add(jMenuItem41);
        jMenu26.add(jSeparator104);

        jMenu9.add(jMenu26);
        jMenu9.add(jSeparator106);

        systemMenu.add(jMenu9);
        systemMenu.add(jSeparator19);

        jMenuItem2.setForeground(new java.awt.Color(51, 51, 51));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/data112.png"))); // NOI18N
        jMenuItem2.setText("Company Setup");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        systemMenu.add(jMenuItem2);
        systemMenu.add(jSeparator23);

        jMenu10.setForeground(new java.awt.Color(51, 51, 51));
        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/database.png"))); // NOI18N
        jMenu10.setText("System Backup / Restore");

        jMenuItem35.setText("Backup");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem35);

        jMenuItem36.setText("Restore");
        jMenu10.add(jMenuItem36);

        systemMenu.add(jMenu10);
        systemMenu.add(jSeparator20);

        menuBar.add(systemMenu);

        helpMenu1.setForeground(new java.awt.Color(51, 51, 51));
        helpMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/call center.png"))); // NOI18N
        helpMenu1.setMnemonic('h');
        helpMenu1.setText("Help");
        helpMenu1.add(jSeparator18);

        contentMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/book83.png"))); // NOI18N
        contentMenuItem1.setMnemonic('c');
        contentMenuItem1.setText("Contents");
        helpMenu1.add(contentMenuItem1);
        helpMenu1.add(jSeparator17);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/speech bubble133.png"))); // NOI18N
        jMenuItem1.setText("Support");
        helpMenu1.add(jMenuItem1);
        helpMenu1.add(jSeparator16);

        aboutMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/travelling12.png"))); // NOI18N
        aboutMenuItem1.setMnemonic('a');
        aboutMenuItem1.setText("About");
        helpMenu1.add(aboutMenuItem1);
        helpMenu1.add(jSeparator15);
        helpMenu1.add(jSeparator59);

        menuBar.add(helpMenu1);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1459, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
        EmployeeDetails.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem46ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        //Incident.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        IncidentDetails.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        CompanySetup.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem41ActionPerformed
        SiteManagement.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem41ActionPerformed

    private void pasteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuItemActionPerformed
        SystemUsers.getObj().setVisible(true);
    }//GEN-LAST:event_pasteMenuItemActionPerformed

    private void copyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuItemActionPerformed
        Learners.getObj().setVisible(true);
    }//GEN-LAST:event_copyMenuItemActionPerformed

    private void jMenuItem70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem70ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem70ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        RiskRating.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

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
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem1;
    private javax.swing.JMenuItem contentMenuItem1;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
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
    private javax.swing.JMenu jMenu22;
    private javax.swing.JMenu jMenu23;
    private javax.swing.JMenu jMenu24;
    private javax.swing.JMenu jMenu25;
    private javax.swing.JMenu jMenu26;
    private javax.swing.JMenu jMenu27;
    private javax.swing.JMenu jMenu28;
    private javax.swing.JMenu jMenu29;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu30;
    private javax.swing.JMenu jMenu31;
    private javax.swing.JMenu jMenu32;
    private javax.swing.JMenu jMenu33;
    private javax.swing.JMenu jMenu34;
    private javax.swing.JMenu jMenu35;
    private javax.swing.JMenu jMenu36;
    private javax.swing.JMenu jMenu37;
    private javax.swing.JMenu jMenu38;
    private javax.swing.JMenu jMenu39;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu40;
    private javax.swing.JMenu jMenu41;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
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
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem37;
    private javax.swing.JMenuItem jMenuItem38;
    private javax.swing.JMenuItem jMenuItem39;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem40;
    private javax.swing.JMenuItem jMenuItem41;
    private javax.swing.JMenuItem jMenuItem42;
    private javax.swing.JMenuItem jMenuItem43;
    private javax.swing.JMenuItem jMenuItem44;
    private javax.swing.JMenuItem jMenuItem45;
    private javax.swing.JMenuItem jMenuItem46;
    private javax.swing.JMenuItem jMenuItem47;
    private javax.swing.JMenuItem jMenuItem48;
    private javax.swing.JMenuItem jMenuItem49;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem50;
    private javax.swing.JMenuItem jMenuItem51;
    private javax.swing.JMenuItem jMenuItem52;
    private javax.swing.JMenuItem jMenuItem53;
    private javax.swing.JMenuItem jMenuItem54;
    private javax.swing.JMenuItem jMenuItem55;
    private javax.swing.JMenuItem jMenuItem56;
    private javax.swing.JMenuItem jMenuItem57;
    private javax.swing.JMenuItem jMenuItem58;
    private javax.swing.JMenuItem jMenuItem59;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem60;
    private javax.swing.JMenuItem jMenuItem61;
    private javax.swing.JMenuItem jMenuItem62;
    private javax.swing.JMenuItem jMenuItem63;
    private javax.swing.JMenuItem jMenuItem64;
    private javax.swing.JMenuItem jMenuItem65;
    private javax.swing.JMenuItem jMenuItem66;
    private javax.swing.JMenuItem jMenuItem67;
    private javax.swing.JMenuItem jMenuItem68;
    private javax.swing.JMenuItem jMenuItem69;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem70;
    private javax.swing.JMenuItem jMenuItem71;
    private javax.swing.JMenuItem jMenuItem72;
    private javax.swing.JMenuItem jMenuItem73;
    private javax.swing.JMenuItem jMenuItem74;
    private javax.swing.JMenuItem jMenuItem75;
    private javax.swing.JMenuItem jMenuItem76;
    private javax.swing.JMenuItem jMenuItem77;
    private javax.swing.JMenuItem jMenuItem78;
    private javax.swing.JMenuItem jMenuItem79;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator100;
    private javax.swing.JPopupMenu.Separator jSeparator101;
    private javax.swing.JPopupMenu.Separator jSeparator102;
    private javax.swing.JPopupMenu.Separator jSeparator103;
    private javax.swing.JPopupMenu.Separator jSeparator104;
    private javax.swing.JPopupMenu.Separator jSeparator105;
    private javax.swing.JPopupMenu.Separator jSeparator106;
    private javax.swing.JPopupMenu.Separator jSeparator107;
    private javax.swing.JPopupMenu.Separator jSeparator108;
    private javax.swing.JPopupMenu.Separator jSeparator109;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator110;
    private javax.swing.JPopupMenu.Separator jSeparator111;
    private javax.swing.JPopupMenu.Separator jSeparator112;
    private javax.swing.JPopupMenu.Separator jSeparator113;
    private javax.swing.JPopupMenu.Separator jSeparator114;
    private javax.swing.JPopupMenu.Separator jSeparator115;
    private javax.swing.JPopupMenu.Separator jSeparator116;
    private javax.swing.JPopupMenu.Separator jSeparator117;
    private javax.swing.JPopupMenu.Separator jSeparator118;
    private javax.swing.JPopupMenu.Separator jSeparator119;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator120;
    private javax.swing.JPopupMenu.Separator jSeparator121;
    private javax.swing.JPopupMenu.Separator jSeparator122;
    private javax.swing.JPopupMenu.Separator jSeparator123;
    private javax.swing.JPopupMenu.Separator jSeparator124;
    private javax.swing.JPopupMenu.Separator jSeparator125;
    private javax.swing.JPopupMenu.Separator jSeparator126;
    private javax.swing.JPopupMenu.Separator jSeparator127;
    private javax.swing.JPopupMenu.Separator jSeparator128;
    private javax.swing.JPopupMenu.Separator jSeparator129;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator130;
    private javax.swing.JPopupMenu.Separator jSeparator131;
    private javax.swing.JPopupMenu.Separator jSeparator132;
    private javax.swing.JPopupMenu.Separator jSeparator133;
    private javax.swing.JPopupMenu.Separator jSeparator134;
    private javax.swing.JPopupMenu.Separator jSeparator135;
    private javax.swing.JPopupMenu.Separator jSeparator136;
    private javax.swing.JPopupMenu.Separator jSeparator137;
    private javax.swing.JPopupMenu.Separator jSeparator138;
    private javax.swing.JPopupMenu.Separator jSeparator139;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator140;
    private javax.swing.JPopupMenu.Separator jSeparator141;
    private javax.swing.JPopupMenu.Separator jSeparator142;
    private javax.swing.JPopupMenu.Separator jSeparator143;
    private javax.swing.JPopupMenu.Separator jSeparator144;
    private javax.swing.JPopupMenu.Separator jSeparator145;
    private javax.swing.JPopupMenu.Separator jSeparator146;
    private javax.swing.JPopupMenu.Separator jSeparator147;
    private javax.swing.JPopupMenu.Separator jSeparator148;
    private javax.swing.JPopupMenu.Separator jSeparator149;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator150;
    private javax.swing.JPopupMenu.Separator jSeparator151;
    private javax.swing.JPopupMenu.Separator jSeparator152;
    private javax.swing.JPopupMenu.Separator jSeparator153;
    private javax.swing.JPopupMenu.Separator jSeparator154;
    private javax.swing.JPopupMenu.Separator jSeparator155;
    private javax.swing.JPopupMenu.Separator jSeparator156;
    private javax.swing.JPopupMenu.Separator jSeparator157;
    private javax.swing.JPopupMenu.Separator jSeparator158;
    private javax.swing.JPopupMenu.Separator jSeparator159;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator160;
    private javax.swing.JPopupMenu.Separator jSeparator161;
    private javax.swing.JPopupMenu.Separator jSeparator162;
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
    private javax.swing.JPopupMenu.Separator jSeparator52;
    private javax.swing.JPopupMenu.Separator jSeparator53;
    private javax.swing.JPopupMenu.Separator jSeparator54;
    private javax.swing.JPopupMenu.Separator jSeparator55;
    private javax.swing.JPopupMenu.Separator jSeparator56;
    private javax.swing.JPopupMenu.Separator jSeparator57;
    private javax.swing.JPopupMenu.Separator jSeparator58;
    private javax.swing.JPopupMenu.Separator jSeparator59;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator60;
    private javax.swing.JPopupMenu.Separator jSeparator61;
    private javax.swing.JPopupMenu.Separator jSeparator62;
    private javax.swing.JPopupMenu.Separator jSeparator63;
    private javax.swing.JPopupMenu.Separator jSeparator64;
    private javax.swing.JPopupMenu.Separator jSeparator65;
    private javax.swing.JPopupMenu.Separator jSeparator66;
    private javax.swing.JPopupMenu.Separator jSeparator67;
    private javax.swing.JPopupMenu.Separator jSeparator68;
    private javax.swing.JPopupMenu.Separator jSeparator69;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator70;
    private javax.swing.JPopupMenu.Separator jSeparator71;
    private javax.swing.JPopupMenu.Separator jSeparator72;
    private javax.swing.JPopupMenu.Separator jSeparator73;
    private javax.swing.JPopupMenu.Separator jSeparator74;
    private javax.swing.JPopupMenu.Separator jSeparator75;
    private javax.swing.JPopupMenu.Separator jSeparator76;
    private javax.swing.JPopupMenu.Separator jSeparator77;
    private javax.swing.JPopupMenu.Separator jSeparator78;
    private javax.swing.JPopupMenu.Separator jSeparator79;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator80;
    private javax.swing.JPopupMenu.Separator jSeparator81;
    private javax.swing.JPopupMenu.Separator jSeparator82;
    private javax.swing.JPopupMenu.Separator jSeparator83;
    private javax.swing.JPopupMenu.Separator jSeparator84;
    private javax.swing.JPopupMenu.Separator jSeparator85;
    private javax.swing.JPopupMenu.Separator jSeparator86;
    private javax.swing.JPopupMenu.Separator jSeparator87;
    private javax.swing.JPopupMenu.Separator jSeparator88;
    private javax.swing.JPopupMenu.Separator jSeparator89;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JPopupMenu.Separator jSeparator90;
    private javax.swing.JPopupMenu.Separator jSeparator91;
    private javax.swing.JPopupMenu.Separator jSeparator92;
    private javax.swing.JPopupMenu.Separator jSeparator93;
    private javax.swing.JPopupMenu.Separator jSeparator94;
    private javax.swing.JPopupMenu.Separator jSeparator95;
    private javax.swing.JPopupMenu.Separator jSeparator96;
    private javax.swing.JPopupMenu.Separator jSeparator97;
    private javax.swing.JPopupMenu.Separator jSeparator98;
    private javax.swing.JPopupMenu.Separator jSeparator99;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JTree jTree2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane3;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane4;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane5;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane6;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane7;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenu systemMenu;
    // End of variables declaration//GEN-END:variables

}
