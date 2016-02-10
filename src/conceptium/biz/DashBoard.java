/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.table.WebTableUI;
import com.javadocking.DockingManager;
import com.javadocking.dock.FloatDock;
import com.javadocking.dock.Position;
import com.javadocking.dock.SplitDock;
import com.javadocking.dock.TabDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.dockable.DockingMode;
import com.javadocking.model.FloatDockModel;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.javadocking.visualizer.FloatExternalizer;
import com.javadocking.visualizer.LineMinimizer;
import com.javadocking.visualizer.SingleMaximizer;

import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author MathomeTD
 */
public class DashBoard extends javax.swing.JFrame {

    /**
     * Creates new form DashBoard
     */
    public DashBoard() {
        try {
            UIManager.setLookAndFeel(new WebLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
            setUndecorated(true);
            initComponents();
            updateTable();
            updateTraining();
            //NewJFrame1 frame = new 
            
            
            
            Dockable dock1 = new DefaultDockable("Window1",jPanel12,"Audits",null,DockingMode.ALL);
            Dockable dock2 = new DefaultDockable("Window1",jTable2,"Training Management",null,DockingMode.ALL);
            Dockable dock3 = new DefaultDockable("Window1",jTable1,"Incident Management",null,DockingMode.ALL);
            Dockable dock4 = new DefaultDockable("Window1",jTree1,"SHEQ Documents",null,DockingMode.ALL);
            Dockable dock5 = new DefaultDockable("Window1",jPanel3,"Legal Compliance",null,DockingMode.ALL);
            Dockable dock6 = new DefaultDockable("Window1",jTabbedPane3,"Legal Compliance",null,DockingMode.ALL);
            Dockable dock7 = new DefaultDockable("Window1",jTabbedPane3,"Legal Compliance",null,DockingMode.ALL);
            Dockable dock8 = new DefaultDockable("Window1",jPanel2,"Incident Statistics",null,DockingMode.ALL);
            Dockable dock9 = new DefaultDockable("Window1",jPanel16,"Risk Assessment",null,DockingMode.ALL);
            Dockable dock10 = new DefaultDockable("Window1",jPanel20,"Training Statistics",null,DockingMode.ALL);
            Dockable dock11 = new DefaultDockable("Window1",jPanel4,"Issued Licenses",null,DockingMode.ALL);
            Dockable dock12 = new DefaultDockable("Window1",jPanel5,"Inspections",null,DockingMode.ALL);
            
            
        //http://companies.mybroadband.co.za/sabinet/feed/
            //http://www.elasa.co.za/1/feed
            TabDock topDock = new TabDock();
            TabDock bottomTabDock = new TabDock();
            TabDock rightTabDock = new TabDock();
            TabDock topDock1 = new TabDock();
            TabDock bottomTabDock1 = new TabDock();
            TabDock rightTabDock1 = new TabDock();
            TabDock second = new TabDock();


            topDock.addDockable(dock1, new Position(0));
            topDock.addDockable(dock12, new Position(1));
            rightTabDock.addDockable(dock2, new Position(0  ));
            rightTabDock.addDockable(dock10, new Position(1  ));
            rightTabDock.addDockable(dock11, new Position(2  ));
            bottomTabDock.addDockable(dock3, new Position(0));
            bottomTabDock.addDockable(dock8, new Position(1));
            topDock1.addDockable(dock4, new Position(0));
            //topDock1.addDockable(dock9, new Position(1));
            bottomTabDock1.addDockable(dock5, new Position(0));
            bottomTabDock1.addDockable(dock6, new Position(1));
            bottomTabDock1.addDockable(dock7, new Position(3));
            //rightTabDock1.addDockable(dock6, new Position(0  ));
            second.addDockable(dock9, new Position(1));


            SplitDock topSplit = new SplitDock();
            topSplit.addChildDock(topDock, new Position(Position.CENTER));
            SplitDock rightSplitDock = new SplitDock();
            rightSplitDock.addChildDock(rightTabDock, new Position(Position.CENTER));
            SplitDock bottomSplitDock = new SplitDock();
            bottomSplitDock.addChildDock(bottomTabDock, null);
            SplitDock topSplit1 = new SplitDock();
            topSplit1.addChildDock(topDock1, new Position(Position.CENTER));
            SplitDock bottomSplitDock1 = new SplitDock();
            bottomSplitDock1.addChildDock(bottomTabDock1, null);
            SplitDock rightSplitDock1 = new SplitDock();
            rightSplitDock1.addChildDock(rightTabDock1, new Position(Position.CENTER));
            SplitDock secondSplit = new SplitDock();
            secondSplit.addChildDock(second, new Position(Position.CENTER));



            FloatDockModel dockModel = new FloatDockModel();
            dockModel.addOwner("frame0", DashBoard.this);
            DockingManager.setDockModel(dockModel);



            FloatExternalizer externalizer = new FloatExternalizer(DashBoard.this);
            dockModel.addVisualizer("externalizer", externalizer, DashBoard.this);

            LineMinimizer minimizePanel = new LineMinimizer(bottomSplitDock);
            dockModel.addVisualizer("minimizer", minimizePanel, DashBoard.this);

            SingleMaximizer maximizePanel = new SingleMaximizer(minimizePanel);
            dockModel.addVisualizer("maximizer", maximizePanel, DashBoard.this);

            minimizePanel.visualizeDockable(dock3);


            dockModel.addRootDock("topDock",topSplit,DashBoard.this);
            jSplitPane3.setLeftComponent(topSplit);
            dockModel.addRootDock("rightTabdock", rightSplitDock, DashBoard.this);
            jSplitPane3.setRightComponent(rightSplitDock);
            dockModel.addRootDock("bottomTabDock", bottomSplitDock, DashBoard.this);
            jSplitPane1.setLeftComponent(bottomSplitDock);
            dockModel.addRootDock("topDock1",topSplit1,DashBoard.this);
            jSplitPane2.setRightComponent(topSplit1);
            dockModel.addRootDock("topDock1",bottomSplitDock1,DashBoard.this);
            jSplitPane4.setRightComponent(bottomSplitDock1);
            dockModel.addRootDock("bottomTabDock", rightSplitDock1, DashBoard.this);
            jSplitPane1.setRightComponent(rightSplitDock1);
            dockModel.addRootDock("bottomTabDock", secondSplit, DashBoard.this);
            jSplitPane5.setLeftComponent(secondSplit);
        FloatDock floatDock = dockModel.getFloatDock(DashBoard.this);
        //Dimension screenSize = Toolkit.getDefaultkit().getScreenSize();
        //floatDock.addDockable(dock1, null, null);
        
        
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
                //PersonDetails pD = new PersonDetails();
                //pD.setVisible(true);
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
               // IncidentAnalysis iR = new IncidentAnalysis();
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
                //PersonDetails pD = new PersonDetails();
                //pD.setVisible(true);
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
           JOptionPane.showMessageDialog(DashBoard.this, ex);
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
           
           jTable2.setModel(DbUtils.resultSetToTableModel(rs)); 
           /*jTable2.removeColumn(jTable2.getColumnModel().getColumn(5));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(6));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(7));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(8));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(4));
           jTable2.removeColumn(jTable2.getColumnModel().getColumn(3));
           //jXTable1.removeColumn(jXTable1.getColumnModel().getColumn(9));*/
       }
       
       catch (Exception ex) {
           
           JOptionPane.showMessageDialog(null, ex);
           
           
       }
    
}
    
    
    
    /*public void readRSSDocument() throws Exception{
    
    RssParser parser = RssParserFactory.createDefault();
    Rss rss = parser.parse(
            new URL("http://rss.cnn.com/rss/cnn_world.rss"));
    //Get all XML elements in the feed
     Collection items = rss.getChannel().getItems();
        if(items != null && !items.isEmpty())
        {
        //Iterate over our main elements. Should have one for each article
            for(Iterator i = items.iterator();
                i.hasNext();
                System.out.println())
            {
                Item item = (Item)i.next();
                System.out.println("Title: " + item.getTitle());
                System.out.println("Link: " + item.getLink());
                System.out.println("Description: " + item.getDescription());
            }

        }
    //Iterate over categories if we are provided with any
        Collection categories = rss.getChannel().getCategories();
        if(categories != null && !categories.isEmpty())
        {
            Category cat;
            for(Iterator i = categories.iterator();
                i.hasNext();
                System.out.println("Category Domain: " + cat.getDomain()))
            {
                cat = (Category)i.next();
                System.out.println("Category: " + cat);
            }

        }
}*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
        jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane3 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane4 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane5 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane6 = new org.jdesktop.swingx.JXTaskPane();
        jXTaskPane7 = new org.jdesktop.swingx.JXTaskPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel12 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jSplitPane5 = new javax.swing.JSplitPane();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

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

        jSplitPane3.setDividerLocation(400);

        jTree1.setModel(new FileSystemModel(new java.io.File("/Users/MathomeTD/Documents/SHEQ Documents")));
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jSplitPane3.setLeftComponent(jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        jSplitPane3.setRightComponent(jScrollPane3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );

        jSplitPane3.setRightComponent(jPanel4);

        jSplitPane1.setDividerLocation(600);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel20.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );

        jSplitPane1.setTopComponent(jPanel2);

        jSplitPane2.setDividerLocation(400);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Incident Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );

        jSplitPane2.setLeftComponent(jPanel12);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Compliance Audits", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(255, 51, 102))); // NOI18N

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

        jSplitPane4.setLeftComponent(jPanel3);

        jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

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

        jSplitPane5.setRightComponent(jPanel16);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSplitPane5)
                            .addComponent(jSplitPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSplitPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSplitPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSplitPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jSplitPane2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        setExtendedState(JFrame.MAXIMIZED_BOTH);
         
        DefaultPieDataset pieDataSet = new DefaultPieDataset(); 
        pieDataSet.setValue("Completed", new Integer(10));
        pieDataSet.setValue("Awaiting Actions", new Integer(20));
        pieDataSet.setValue("Pending", new Integer(30));
        pieDataSet.setValue("Sheduled", new Integer(40));
        JFreeChart chart = ChartFactory.createPieChart3D("Autit Schedule Report", pieDataSet, false, true, true);
        PiePlot p = (PiePlot)chart.getPlot();
        //p.setForegroundAlpha(BOTTOM_ALIGNMENT);
        ChartPanel CP = new ChartPanel(chart);
        CP.setVisible(true);
        CP.setSize(290,250);
        jPanel12.add(CP, BOTTOM_ALIGNMENT);
        jPanel12.validate();
        //ChartFrame frame = new ChartFrame("Pie Chart", chart);
        //frame.setVisible(true);
        //frame.setSize(200,200);
        //jPanel16.add(frame);
        
        DefaultCategoryDataset barDataSet = new DefaultCategoryDataset();
        barDataSet.setValue(1, "trained", "Medium");
        barDataSet.setValue(3, "trained", "Low");
        
        barDataSet.setValue(2, "awaitingtraining", "Medium");
        barDataSet.setValue(4, "awaitingtraining", "Low");
        
        barDataSet.setValue(1, "toBetrained", "Medium");
        barDataSet.setValue(2, "toBetrained", "Low");
        
        JFreeChart chart1 = ChartFactory.createBarChart(null, "Incidents", "Number", barDataSet,PlotOrientation.VERTICAL,false, true, false);
        CategoryPlot p1;
        p1 = chart1.getCategoryPlot();
        p1.setRangeGridlinePaint(Color.black);
        //ChartFrame frame = new ChartFrame("Pie Chart", chart1);
        //frame.setVisible(true);
        //frame.setSize(500,500);
        //jPanel16.add(frame);
        ChartPanel CP1 = new ChartPanel(chart1);
        CP1.setVisible(true);
        CP1.setSize(580,260);
        jPanel2.add(CP1, BOTTOM_ALIGNMENT);
        jPanel2.validate();
        
        
        DefaultCategoryDataset plotDataSet = new DefaultCategoryDataset();
        plotDataSet.setValue(10, "Incidents", "1st Quarter");
        plotDataSet.setValue(5, "Incidents", "2nd Quarter");
        
        plotDataSet.setValue(7, "Incidents", "3rd Quarter");
        plotDataSet.setValue(2, "Incidents", "4th Quarter");
        
        JFreeChart chart2 = ChartFactory.createLineChart("Incidents Vs Quaters", " Quarters" ,"Number of Incidents", plotDataSet,PlotOrientation.HORIZONTAL,true, true, false);
        
        ChartPanel CP2 = new ChartPanel(chart2);
        CP2.setVisible(true);
        CP2.setSize(610,255);
        jPanel4.add(CP2, BOTTOM_ALIGNMENT);
        jPanel4.validate();
        
    }//GEN-LAST:event_formWindowActivated
String jTreeVar;
    /**id
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
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DashBoard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTree jTree1;
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
