/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;


import ch.fhnw.filecopier.CopyJob;
import ch.fhnw.filecopier.FileCopier;
import ch.fhnw.filecopier.FileCopierPanel;
import ch.fhnw.filecopier.Source;
import com.alee.laf.table.WebTableUI;
import com.javadocking.DockingManager;
import com.javadocking.dock.Position;
import com.javadocking.dock.SplitDock;
import com.javadocking.dock.TabDock;
import com.javadocking.dockable.DefaultDockable;
import com.javadocking.dockable.Dockable;
import com.javadocking.dockable.DockableState;
import com.javadocking.dockable.DockingMode;
import com.javadocking.dockable.DraggableContent;
import com.javadocking.dockable.StateActionDockable;
import com.javadocking.dockable.action.DefaultDockableStateAction;
import com.javadocking.dockable.action.DefaultDockableStateActionFactory;
import com.javadocking.drag.DragListener;
import com.javadocking.event.DockingEvent;
import com.javadocking.event.DockingListener;
import com.javadocking.model.FloatDockModel;
import com.javadocking.visualizer.FloatExternalizer;
import com.javadocking.visualizer.LineMinimizer;
import com.javadocking.visualizer.SingleMaximizer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import static java.awt.Component.BOTTOM_ALIGNMENT;
import java.awt.ComponentOrientation;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import net.infonode.gui.laf.InfoNodeLookAndFeel;
import net.proteanit.sql.DbUtils;
import org.jdesktop.swingx.decorator.BorderHighlighter;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.jdesktop.swingx.decorator.PatternPredicate;
import org.jdesktop.swingx.decorator.ShadingColorHighlighter;
import org.jdesktop.swingx.rollover.RolloverProducer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author MathomeTD
 */


public class DashBoards extends javax.swing.JFrame implements DraggableContent,ItemListener, DockingListener{

 
    /**
     * Creates new form DashBoards
     */

    DbConnection connect = new DbConnection();
    public DashBoards() throws SQLException {
        try {
            UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        //setUndecorated(true);
        initComponents();
         jTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
         jTree2.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
         jTree3.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
         jTree4.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
         jTree5.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
         jTree6.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        updateTable();
        updateTraining();
        fillmodules();
        overDue();
        //jMenu28.setText("7 Messages");
        //HighlightPredicate predicate = new PatternPredicate(".*R50*.", 2, 2);
        //ColorHighlighter highlighter =  new ColorHighlighter(predicate,null,Color.red,null,null);
        //jXTable1.setHighlighters(highlighter);
        jXTable1.setSortable(false);
        jXTable1.setToolTipText("hit cmd + f on mac to search data or Ctl + f on windows");
        org.jdesktop.swingx.decorator.Highlighter simpleStriping = HighlighterFactory.createSimpleStriping();
        PatternPredicate patternPredicate = new PatternPredicate("Above R50 000",2,2);
        ColorHighlighter magenta = new ColorHighlighter(patternPredicate, Color.red, Color.black, Color.LIGHT_GRAY, Color.black);
        ShadingColorHighlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(2));
        
        jXTable1.setHighlighters(simpleStriping,magenta,shading);
        
       HighlightPredicate rolloverPredicate = new HighlightPredicate(){

            @Override
            public boolean isHighlighted(Component renderer, ComponentAdapter adapter) {
                if(!adapter.getComponent().isEnabled()) {return false;}
                Point p = (Point)adapter.getComponent().getClientProperty(RolloverProducer.ROLLOVER_KEY);
                return p != null && p.x == adapter.column;
            }
       };
       BorderHighlighter hlRow =  new BorderHighlighter(HighlightPredicate.ROLLOVER_ROW, null,false,false);
       ColorHighlighter hlColumn =  new ColorHighlighter(rolloverPredicate, Color.blue, Color.blue, Color.blue, Color.blue);
       //jXTable1.setHighlighters(hlRow, hlColumn);
        
        
        //jTable1.setUI(new ITunesTableUI());
        Dockable dock = new DefaultDockable("JTree",jTree1,"System Documents",null,DockingMode.ALL);
        Dockable dock1 = new DefaultDockable("JPanel2",jPanel1,"Audits",null,DockingMode.ALL);
        Dockable dock2 = new DefaultDockable("JPanel1",jPanel2,"Inspection",null,DockingMode.ALL);
        Dockable dock3 = new DefaultDockable("JTable1",jPanel9,"Course Bookings",null,DockingMode.ALL);
        Dockable dock21 = new DefaultDockable("JTable3",jPanel16,"Employee Training Recomendation",null,DockingMode.ALL);
        Dockable dock4 = new DefaultDockable("JPanel3",jPanel3,"Training Statistics",null,DockingMode.ALL);
        Dockable dock14 = new DefaultDockable("JPanel114",jPanel14,"Reports",null,DockingMode.ALL);
        Dockable dock5 = new DefaultDockable("JTable2",jPanel10,"Incident Management",null,DockingMode.ALL);
        Dockable dock6 = new DefaultDockable("JPanel4",jPanel4,"Incident Statistics",null,DockingMode.ALL);
        Dockable dock12 = new DefaultDockable("JPanel12",jPanel12,"Outstanding Incidents",null,DockingMode.ALL);
        Dockable dock13 = new DefaultDockable("JPanel13",jPanel13,"Reports",null,DockingMode.ALL);
        Dockable dock7 = new DefaultDockable("JPanel5",jPanel5,"Risk Statistics",null,DockingMode.ALL);
        Dockable dock8 = new DefaultDockable("JPanel6",jPanel6,"Legal Documents List",null,DockingMode.ALL);
        Dockable dock9 = new DefaultDockable("JPanel7",jPanel7,"Risk Listing",null,DockingMode.ALL);
        Dockable dock10 = new DefaultDockable("JList1",jPanel11,"Legal Compliance",null,DockingMode.ALL);
        dock1011 = new DefaultDockable("JPanel8",jPanel8,"Main Menu",null,DockingMode.ALL);
        dock1011 = new StateActionDockable(dock1011, new DefaultDockableStateActionFactory(), DockableState.statesClosed());
        Dockable dock1012 = new DefaultDockable("JTree2",jTree2,"Key Corperate Standards",null,DockingMode.ALL);
        
        
        
        
        
        
        TabDock topDockRight = new TabDock();
        TabDock topDockLeft = new TabDock();
        TabDock topDockRight1 = new TabDock();
        TabDock topDockRight2 = new TabDock();
        TabDock topDockLeft1 = new TabDock();
        TabDock topDockRight3 = new TabDock();
        TabDock bottomSplitDock = new TabDock();
        TabDock topDockRight12 = new TabDock();
        
        topDockRight.addDockable(dock, new Position(0));
        topDockLeft.addDockable(dock1, new Position(0));
        topDockLeft.addDockable(dock2, new Position(1));
        
        
        topDockRight1.addDockable(dock3, new Position(1));
        topDockRight1.addDockable(dock21, new Position(2));
        topDockRight1.addDockable(dock14, new Position(3));
        topDockRight1.addDockable(dock4, new Position(0));
        
        //topDockRight1.addDockable(dock4, new Position(1));
        topDockRight2.addDockable(dock12, new Position(2));
        topDockRight2.addDockable(dock5, new Position(1));
        topDockRight2.addDockable(dock6, new Position(0));
        
         
        topDockLeft1.addDockable(dock9, new Position(1));
        topDockLeft1.addDockable(dock13, new Position(2));
        topDockLeft1.addDockable(dock7, new Position(0));
        
        topDockRight3.addDockable(dock10, new Position(2));
        topDockRight3.addDockable(dock8, new Position(0));
        topDockRight12.addDockable(dock1012, new Position(0));
        
        SplitDock topSplitRight = new SplitDock();
        topSplitRight.addChildDock(topDockRight, new Position(Position.CENTER));
        SplitDock topSplitLeft = new SplitDock();
        topSplitLeft.addChildDock(topDockLeft, new Position(Position.CENTER));
        SplitDock topSplitRight1 = new SplitDock();
        topSplitRight1.addChildDock(topDockRight1, new Position(Position.CENTER));
        SplitDock topSplitRight2 = new SplitDock();
        topSplitRight2.addChildDock(topDockRight2, new Position(Position.CENTER));
        SplitDock topSplitLeft1 = new SplitDock();
        topSplitLeft1.addChildDock(topDockLeft1, new Position(Position.CENTER));
        SplitDock topSplitRight3 = new SplitDock();
        topSplitRight3.addChildDock(topDockRight3, new Position(Position.CENTER));
        SplitDock topSplitRight12 = new SplitDock();
        topSplitRight12.addChildDock(topDockRight12, new Position(Position.CENTER));
        
        FloatDockModel dockModel = new FloatDockModel();
        dockModel.addOwner("frame0", DashBoards.this);
        DockingManager.setDockModel(dockModel);
        
        
        
        FloatExternalizer externalizer = new FloatExternalizer(DashBoards.this);
        dockModel.addVisualizer("externalizer", externalizer, DashBoards.this);

        LineMinimizer minimizePanel = new LineMinimizer(bottomSplitDock);
        dockModel.addVisualizer("minimizer", minimizePanel, DashBoards.this);

        SingleMaximizer maximizePanel = new SingleMaximizer(minimizePanel);
        dockModel.addVisualizer("maximizer", maximizePanel, DashBoards.this);

        DashBoards.this.add(maximizePanel, BorderLayout.CENTER);

        maximizePanel.visualizeDockable(dock1011);
        
        
        
        dockModel.addRootDock("topDockRight",topSplitRight,DashBoards.this);
        jSplitPane5.setRightComponent(topSplitRight);
        dockModel.addRootDock("topDockLeft",topSplitLeft,DashBoards.this);
        //jSplitPane1.setLeftComponent(topSplitLeft);
        dockModel.addRootDock("topDockRight1",topSplitRight1,DashBoards.this);
        jSplitPane1.setRightComponent(topSplitRight1);
        dockModel.addRootDock("topDockRight2",topSplitRight2,DashBoards.this);
        jSplitPane2.setRightComponent(topSplitRight2);
        dockModel.addRootDock("topDockLeft",topSplitLeft1,DashBoards.this);
        jSplitPane3.setRightComponent(topSplitLeft1);
        dockModel.addRootDock("topDockRight",topSplitRight3,DashBoards.this);
        jSplitPane4.setRightComponent(topSplitRight3);
        //dockModel.addRootDock("topDockRight",topSplitRight12,DashBoards.this);
        //jSplitPane6.setRightComponent(topSplitRight12);
        
        
        //FloatDockModel dockModel1 = new FloatDockModel();
        //dockModel.addRootDock("splitDock", SplitDock, DashBoards.this);
            //dockModel1.addOwner("frame0", DashBoards.this);
            //DockingManager.setDockModel(dockModel);
        
        
        
        
        //FloatDock floatDock = dockModel.getFloatDock(DashBoards.this);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //floatDock.addDockable(dock1011, new Point(screenSize.width / 2, screenSize.height / 2), new Point());
        //externalizer.externalizeDockable(dock1011, new Point(screenSize.width / 2, screenSize.height / 2));
        
        
        
        
        
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
        jXTaskPane1.setForeground(Color.BLACK);
        jXTaskPane2.setForeground(Color.BLACK);
        jXTaskPane3.setForeground(Color.BLACK);
        jXTaskPane4.setForeground(Color.BLACK);
        jXTaskPane6.setForeground(Color.BLACK);
        jXTaskPane2.setTitle("Legal Compliance");
        jXTaskPane3.setTitle("Training");
        jXTaskPane4.setTitle("Risk Assessment");
        jXTaskPane5.setTitle("Incident Management & Reporting");
        jXTaskPane5.setForeground(Color.BLACK);
        jXTaskPane6.setTitle("SHEQ System Documentation");
        jXTaskPane7.setTitle("Key Corporate Standards ");
    }

    
    
    public final static void overDue(){
        SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
        Date date = new Date();
        String now = format.format(date);
        //txtNew.setText(format.format(date));
        String sql = "select COUNT(ReferenceNumber) as count from Incident where DateOfReportingIncident = '"+now+"' ";
        try{
            Connection con = DbConnection.dbConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
            int totals =  rs.getInt("count");
            if(totals == 0){
            jMenu28.setText(totals+" "+"Messages");
            jMenu28.setText("0 Messages");
            jMenuItem84.setText("0 New Incident Reported");
            }else{
            jMenu28.setText(totals+" "+"Messages");
            jMenuItem84.setText(totals+" "+"New Incident Reported");
            jMenuItem84.setForeground(Color.red);}
            //jLabel9.setText("("+totals+")");
            //jLabel9.setFont(new Font("Arial", Font.BOLD, 14));
            //jLabel9.setForeground(Color.red);
            }
        }
        catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    
    private void updateTable() throws SQLException{
       String sql = "Select ReferenceNumber,IncidentType,NatureOfIncident, "+
                   "DateOfIncident,Status from Incident inner join IncidentDetail on ReferenceNumber = Reference";
       try(Connection con = connect.dbConnection();
           PreparedStatement pst = con.prepareStatement(sql);) {
           try(ResultSet rs = pst.executeQuery();){
           if(rs.next()){
           jXTable1.setUI(new WebTableUI());
           jXTable1.setModel(DbUtils.resultSetToTableModel(rs));
           /**JXTableHeader th = (JXTableHeader) jXTable1.getTableHeader();
           TableColumnModel tcm = th.getColumnModel();
           TableColumn tc = tcm.getColumn(0);
           tc.setHeaderValue("Reference Number");
           tc = tcm.getColumn(1);
           tc.setHeaderValue("incident Type");
           tc = tcm.getColumn(2);
           tc.setHeaderValue("Nature Of Incident");
           tc = tcm.getColumn(3);
           tc.setHeaderValue("Date of Incident");
           tc = tcm.getColumn(4);
           tc.setHeaderValue("Status");
           //tc = tcm.getColumn(5);
           //tc.setHeaderValue("Site");
           jXTable1.addColumn(tc);
           th.repaint();
           **/
           }
       }
       catch (Exception ex) {
           JOptionPane.showMessageDialog(DashBoards.this, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DashBoards.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }


    private void updateTraining() throws SQLException{
       String sql ="Select LEARNERID,NAME,SURNAME,COURSEBOOKED,STARTDATE,ENDDATE,SITE from APP.BOOKCOURSE ";
        try (Connection con = DriverManager.getConnection("jdbc:derby:MTD","herbert","elsie1*#");
           PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();){
           jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }
       catch (SQLException ex) {
           JOptionPane.showMessageDialog(DashBoards.this, ex + "Unable to located Database ");
             }
        }

    private void fillmodules() throws SQLException{
        DefaultListModel m = new DefaultListModel();
        //String search = (String)cboTransaction.getSelectedItem();
        String sql = "Select * from APP.COURSEMODULES";
        try(Connection con = DriverManager.getConnection("jdbc:derby:MTD","herbert","elsie1*#");
            PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();) {
                while(rs.next()){
                    String ModuleName = rs.getString("DESCRIPTION");
                    m.addElement(ModuleName);
                
                }
                jList1.setModel(m);
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(DashBoards.this, e);
        }
}

    
    private void updateTree(){
        DefaultMutableTreeNode rootNode = null;
        String sql = "Select * from Docs";
       try {
           Connection con = DriverManager.getConnection("jdbc:derby:MTD","herbert","elsie1*#");
           PreparedStatement pst = con.prepareStatement(sql);
           ResultSet rs = pst.executeQuery();
           rs.last();
           int rowCount = rs.getRow();
           rs.beforeFirst();
           
           int i = 0;
           int j = 0;
           
           String [] nodeID = new String [rowCount];
           String [] parentID = new String[rowCount];
           DefaultMutableTreeNode [] node = new DefaultMutableTreeNode[rowCount];
           
           while (rs.next()){
               String docCat = rs.getString("DocumentCategory");
               String docName = rs.getString("DocumentName");
               
               node[i] = new DefaultMutableTreeNode(docCat);
               parentID[i] = rs.getString("DocumentCategory");
               nodeID[i] = rs.getString("node_id") + "";
               
                if (parentID.equals("null")){
                   rootNode = node[i];
                }
                i++;
           }
           for(i = 0; i <rowCount; i++){
                for(j = 0; j < rowCount; j ++){
                    if(parentID[j].equals(nodeID[i]) && node[i] != null){
                        node[i].add(node[j]);
                    }
                    node[i] = null;
                }
           }
           JTree tree = new JTree(rootNode);
       }
       
       catch (Exception ex) {
           JOptionPane.showMessageDialog(DashBoards.this, ex);
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
        jSeparator129 = new javax.swing.JPopupMenu.Separator();
        jMenuItem68 = new javax.swing.JMenuItem();
        jSeparator128 = new javax.swing.JPopupMenu.Separator();
        jMenuItem71 = new javax.swing.JMenuItem();
        jSeparator127 = new javax.swing.JPopupMenu.Separator();
        jMenuItem72 = new javax.swing.JMenuItem();
        jSeparator126 = new javax.swing.JPopupMenu.Separator();
        jMenuItem73 = new javax.swing.JMenuItem();
        jSeparator130 = new javax.swing.JPopupMenu.Separator();
        jMenuItem74 = new javax.swing.JMenuItem();
        jSeparator131 = new javax.swing.JPopupMenu.Separator();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jSeparator120 = new javax.swing.JPopupMenu.Separator();
        jMenuItem65 = new javax.swing.JMenuItem();
        jSeparator25 = new javax.swing.JPopupMenu.Separator();
        jMenuItem64 = new javax.swing.JMenuItem();
        jSeparator118 = new javax.swing.JPopupMenu.Separator();
        jMenuItem69 = new javax.swing.JMenuItem();
        jSeparator119 = new javax.swing.JPopupMenu.Separator();
        jMenuItem66 = new javax.swing.JMenuItem();
        jSeparator121 = new javax.swing.JPopupMenu.Separator();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jSplitPane4 = new javax.swing.JSplitPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable(){public boolean isCellEditable(int row, int column){
            return false;}};
    jPanel3 = new javax.swing.JPanel();
    jPanel9 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable(){public boolean isCellEditable(int row, int column){
        return false;}};
jSplitPane5 = new javax.swing.JSplitPane();
jPanel2 = new javax.swing.JPanel();
jScrollPane1 = new javax.swing.JScrollPane();
jTree1 = new javax.swing.JTree();
jPanel17 = new javax.swing.JPanel();
jXTaskPaneContainer1 = new org.jdesktop.swingx.JXTaskPaneContainer();
jXTaskPane1 = new org.jdesktop.swingx.JXTaskPane();
jScrollPane8 = new javax.swing.JScrollPane();
jTree4 = new javax.swing.JTree();
jXTaskPane2 = new org.jdesktop.swingx.JXTaskPane();
jButton1 = new javax.swing.JButton();
jButton2 = new javax.swing.JButton();
jXTaskPane3 = new org.jdesktop.swingx.JXTaskPane();
jScrollPane10 = new javax.swing.JScrollPane();
jTree5 = new javax.swing.JTree();
jXTaskPane4 = new org.jdesktop.swingx.JXTaskPane();
jScrollPane11 = new javax.swing.JScrollPane();
jTree6 = new javax.swing.JTree();
jXTaskPane5 = new org.jdesktop.swingx.JXTaskPane();
jScrollPane7 = new javax.swing.JScrollPane();
jTree3 = new javax.swing.JTree();
jXTaskPane6 = new org.jdesktop.swingx.JXTaskPane();
jScrollPane15 = new javax.swing.JScrollPane();
jTree9 = new javax.swing.JTree();
jXTaskPane7 = new org.jdesktop.swingx.JXTaskPane();
jButton12 = new javax.swing.JButton();
jButton13 = new javax.swing.JButton();
jButton14 = new javax.swing.JButton();
jButton15 = new javax.swing.JButton();
jSplitPane3 = new javax.swing.JSplitPane();
jPanel5 = new javax.swing.JPanel();
jPanel11 = new javax.swing.JPanel();
jPanel13 = new javax.swing.JPanel();
jPanel16 = new javax.swing.JPanel();
jScrollPane6 = new javax.swing.JScrollPane();
jTable3 = new javax.swing.JTable();
jSplitPane2 = new javax.swing.JSplitPane();
jPanel4 = new javax.swing.JPanel();
jPanel8 = new javax.swing.JPanel();
jScrollPane5 = new javax.swing.JScrollPane();
jTree2 = new javax.swing.JTree();
jPanel12 = new javax.swing.JPanel();
jPanel14 = new javax.swing.JPanel();
jPanel15 = new javax.swing.JPanel();
jButton3 = new javax.swing.JButton();
jButton4 = new javax.swing.JButton();
jButton5 = new javax.swing.JButton();
jButton6 = new javax.swing.JButton();
jButton7 = new javax.swing.JButton();
jButton8 = new javax.swing.JButton();
jButton9 = new javax.swing.JButton();
jButton10 = new javax.swing.JButton();
jButton11 = new javax.swing.JButton();
jMenuBar1 = new javax.swing.JMenuBar();
jMenu1 = new javax.swing.JMenu();
jSeparator1 = new javax.swing.JPopupMenu.Separator();
jMenu3 = new javax.swing.JMenu();
jSeparator71 = new javax.swing.JPopupMenu.Separator();
jMenuItem47 = new javax.swing.JMenuItem();
jSeparator78 = new javax.swing.JPopupMenu.Separator();
jMenuItem48 = new javax.swing.JMenuItem();
jSeparator137 = new javax.swing.JPopupMenu.Separator();
jMenuItem78 = new javax.swing.JMenuItem();
jSeparator79 = new javax.swing.JPopupMenu.Separator();
jMenu36 = new javax.swing.JMenu();
jSeparator142 = new javax.swing.JPopupMenu.Separator();
jMenuItem35 = new javax.swing.JMenuItem();
jSeparator70 = new javax.swing.JPopupMenu.Separator();
jMenuItem46 = new javax.swing.JMenuItem();
jSeparator144 = new javax.swing.JPopupMenu.Separator();
jMenuItem82 = new javax.swing.JMenuItem();
jSeparator145 = new javax.swing.JPopupMenu.Separator();
jMenuItem83 = new javax.swing.JMenuItem();
jSeparator143 = new javax.swing.JPopupMenu.Separator();
jSeparator67 = new javax.swing.JPopupMenu.Separator();
jMenu37 = new javax.swing.JMenu();
jSeparator147 = new javax.swing.JPopupMenu.Separator();
jMenuItem34 = new javax.swing.JMenuItem();
jSeparator146 = new javax.swing.JPopupMenu.Separator();
jSeparator69 = new javax.swing.JPopupMenu.Separator();
jSeparator5 = new javax.swing.JPopupMenu.Separator();
jMenu4 = new javax.swing.JMenu();
jSeparator106 = new javax.swing.JPopupMenu.Separator();
jMenuItem58 = new javax.swing.JMenuItem();
jSeparator105 = new javax.swing.JPopupMenu.Separator();
jMenuItem59 = new javax.swing.JMenuItem();
jSeparator104 = new javax.swing.JPopupMenu.Separator();
jSeparator4 = new javax.swing.JPopupMenu.Separator();
jMenu5 = new javax.swing.JMenu();
jSeparator41 = new javax.swing.JPopupMenu.Separator();
jMenu18 = new javax.swing.JMenu();
jSeparator40 = new javax.swing.JPopupMenu.Separator();
jMenuItem20 = new javax.swing.JMenuItem();
jSeparator39 = new javax.swing.JPopupMenu.Separator();
jMenuItem19 = new javax.swing.JMenuItem();
jSeparator38 = new javax.swing.JPopupMenu.Separator();
jSeparator44 = new javax.swing.JPopupMenu.Separator();
jMenu20 = new javax.swing.JMenu();
jSeparator51 = new javax.swing.JPopupMenu.Separator();
jMenuItem17 = new javax.swing.JMenuItem();
jSeparator42 = new javax.swing.JPopupMenu.Separator();
jMenuItem18 = new javax.swing.JMenuItem();
jSeparator50 = new javax.swing.JPopupMenu.Separator();
jSeparator46 = new javax.swing.JPopupMenu.Separator();
jMenu21 = new javax.swing.JMenu();
jSeparator141 = new javax.swing.JPopupMenu.Separator();
jMenuItem80 = new javax.swing.JMenuItem();
jSeparator140 = new javax.swing.JPopupMenu.Separator();
jMenuItem81 = new javax.swing.JMenuItem();
jSeparator139 = new javax.swing.JPopupMenu.Separator();
jSeparator45 = new javax.swing.JPopupMenu.Separator();
jMenu22 = new javax.swing.JMenu();
jSeparator49 = new javax.swing.JPopupMenu.Separator();
jMenuItem21 = new javax.swing.JMenuItem();
jSeparator47 = new javax.swing.JPopupMenu.Separator();
jMenuItem22 = new javax.swing.JMenuItem();
jSeparator48 = new javax.swing.JPopupMenu.Separator();
jSeparator43 = new javax.swing.JPopupMenu.Separator();
jMenu19 = new javax.swing.JMenu();
jSeparator81 = new javax.swing.JPopupMenu.Separator();
jMenuItem86 = new javax.swing.JMenuItem();
jSeparator80 = new javax.swing.JPopupMenu.Separator();
jSeparator3 = new javax.swing.JPopupMenu.Separator();
jMenu6 = new javax.swing.JMenu();
jSeparator10 = new javax.swing.JPopupMenu.Separator();
jMenuItem53 = new javax.swing.JMenuItem();
jSeparator74 = new javax.swing.JPopupMenu.Separator();
jMenuItem56 = new javax.swing.JMenuItem();
jSeparator77 = new javax.swing.JPopupMenu.Separator();
jMenuItem54 = new javax.swing.JMenuItem();
jSeparator122 = new javax.swing.JPopupMenu.Separator();
jMenu34 = new javax.swing.JMenu();
jSeparator125 = new javax.swing.JPopupMenu.Separator();
jMenuItem55 = new javax.swing.JMenuItem();
jSeparator124 = new javax.swing.JPopupMenu.Separator();
jMenuItem67 = new javax.swing.JMenuItem();
jSeparator76 = new javax.swing.JPopupMenu.Separator();
jMenuItem70 = new javax.swing.JMenuItem();
jSeparator123 = new javax.swing.JPopupMenu.Separator();
jSeparator75 = new javax.swing.JPopupMenu.Separator();
jSeparator6 = new javax.swing.JPopupMenu.Separator();
jMenu10 = new javax.swing.JMenu();
jSeparator16 = new javax.swing.JPopupMenu.Separator();
jSeparator15 = new javax.swing.JPopupMenu.Separator();
jMenu12 = new javax.swing.JMenu();
jSeparator28 = new javax.swing.JPopupMenu.Separator();
jMenuItem4 = new javax.swing.JMenuItem();
jSeparator27 = new javax.swing.JPopupMenu.Separator();
jMenuItem5 = new javax.swing.JMenuItem();
jSeparator26 = new javax.swing.JPopupMenu.Separator();
jSeparator14 = new javax.swing.JPopupMenu.Separator();
jMenu13 = new javax.swing.JMenu();
jSeparator31 = new javax.swing.JPopupMenu.Separator();
jSeparator30 = new javax.swing.JPopupMenu.Separator();
jMenuItem7 = new javax.swing.JMenuItem();
jSeparator29 = new javax.swing.JPopupMenu.Separator();
jSeparator13 = new javax.swing.JPopupMenu.Separator();
jMenu14 = new javax.swing.JMenu();
jSeparator37 = new javax.swing.JPopupMenu.Separator();
jMenuItem8 = new javax.swing.JMenuItem();
jSeparator36 = new javax.swing.JPopupMenu.Separator();
jMenu16 = new javax.swing.JMenu();
jSeparator32 = new javax.swing.JPopupMenu.Separator();
jMenuItem10 = new javax.swing.JMenuItem();
jSeparator34 = new javax.swing.JPopupMenu.Separator();
jMenuItem11 = new javax.swing.JMenuItem();
jSeparator35 = new javax.swing.JPopupMenu.Separator();
jMenuItem12 = new javax.swing.JMenuItem();
jSeparator33 = new javax.swing.JPopupMenu.Separator();
jSeparator11 = new javax.swing.JPopupMenu.Separator();
jMenu15 = new javax.swing.JMenu();
jSeparator21 = new javax.swing.JPopupMenu.Separator();
jMenuItem9 = new javax.swing.JMenuItem();
jSeparator22 = new javax.swing.JPopupMenu.Separator();
jMenu17 = new javax.swing.JMenu();
jSeparator20 = new javax.swing.JPopupMenu.Separator();
jMenuItem13 = new javax.swing.JMenuItem();
jSeparator19 = new javax.swing.JPopupMenu.Separator();
jMenuItem14 = new javax.swing.JMenuItem();
jSeparator18 = new javax.swing.JPopupMenu.Separator();
jMenuItem15 = new javax.swing.JMenuItem();
jSeparator17 = new javax.swing.JPopupMenu.Separator();
jSeparator12 = new javax.swing.JPopupMenu.Separator();
jSeparator7 = new javax.swing.JPopupMenu.Separator();
jMenu9 = new javax.swing.JMenu();
jSeparator91 = new javax.swing.JPopupMenu.Separator();
jMenu26 = new javax.swing.JMenu();
jSeparator90 = new javax.swing.JPopupMenu.Separator();
jMenuItem36 = new javax.swing.JMenuItem();
jSeparator89 = new javax.swing.JPopupMenu.Separator();
jMenuItem37 = new javax.swing.JMenuItem();
jSeparator101 = new javax.swing.JPopupMenu.Separator();
jMenuItem38 = new javax.swing.JMenuItem();
jSeparator96 = new javax.swing.JPopupMenu.Separator();
jSeparator87 = new javax.swing.JPopupMenu.Separator();
jMenu29 = new javax.swing.JMenu();
jSeparator95 = new javax.swing.JPopupMenu.Separator();
jMenuItem50 = new javax.swing.JMenuItem();
jSeparator94 = new javax.swing.JPopupMenu.Separator();
jMenuItem51 = new javax.swing.JMenuItem();
jSeparator93 = new javax.swing.JPopupMenu.Separator();
jSeparator92 = new javax.swing.JPopupMenu.Separator();
jMenuItem43 = new javax.swing.JMenuItem();
jSeparator115 = new javax.swing.JPopupMenu.Separator();
jMenu27 = new javax.swing.JMenu();
jSeparator114 = new javax.swing.JPopupMenu.Separator();
jMenuItem45 = new javax.swing.JMenuItem();
jSeparator113 = new javax.swing.JPopupMenu.Separator();
jMenuItem39 = new javax.swing.JMenuItem();
jSeparator88 = new javax.swing.JPopupMenu.Separator();
jSeparator8 = new javax.swing.JPopupMenu.Separator();
jMenu7 = new javax.swing.JMenu();
jSeparator109 = new javax.swing.JPopupMenu.Separator();
jMenu31 = new javax.swing.JMenu();
jSeparator108 = new javax.swing.JPopupMenu.Separator();
jMenuItem57 = new javax.swing.JMenuItem();
jSeparator107 = new javax.swing.JPopupMenu.Separator();
jMenu32 = new javax.swing.JMenu();
jSeparator110 = new javax.swing.JPopupMenu.Separator();
jMenuItem60 = new javax.swing.JMenuItem();
jSeparator103 = new javax.swing.JPopupMenu.Separator();
jMenuItem61 = new javax.swing.JMenuItem();
jSeparator86 = new javax.swing.JPopupMenu.Separator();
jMenuItem62 = new javax.swing.JMenuItem();
jSeparator111 = new javax.swing.JPopupMenu.Separator();
jSeparator102 = new javax.swing.JPopupMenu.Separator();
jMenuItem63 = new javax.swing.JMenuItem();
jSeparator2 = new javax.swing.JPopupMenu.Separator();
jMenu11 = new javax.swing.JMenu();
jSeparator116 = new javax.swing.JPopupMenu.Separator();
jMenuItem2 = new javax.swing.JMenuItem();
jSeparator66 = new javax.swing.JPopupMenu.Separator();
jMenuItem30 = new javax.swing.JMenuItem();
jSeparator65 = new javax.swing.JPopupMenu.Separator();
jMenuItem31 = new javax.swing.JMenuItem();
jSeparator64 = new javax.swing.JPopupMenu.Separator();
jMenu35 = new javax.swing.JMenu();
jSeparator135 = new javax.swing.JPopupMenu.Separator();
jMenuItem29 = new javax.swing.JMenuItem();
jSeparator132 = new javax.swing.JPopupMenu.Separator();
jMenuItem75 = new javax.swing.JMenuItem();
jSeparator134 = new javax.swing.JPopupMenu.Separator();
jMenuItem76 = new javax.swing.JMenuItem();
jSeparator133 = new javax.swing.JPopupMenu.Separator();
jMenuItem77 = new javax.swing.JMenuItem();
jSeparator136 = new javax.swing.JPopupMenu.Separator();
jSeparator24 = new javax.swing.JPopupMenu.Separator();
jMenuItem3 = new javax.swing.JMenuItem();
jSeparator23 = new javax.swing.JPopupMenu.Separator();
jMenu8 = new javax.swing.JMenu();
jSeparator62 = new javax.swing.JPopupMenu.Separator();
jMenu23 = new javax.swing.JMenu();
jSeparator60 = new javax.swing.JPopupMenu.Separator();
jMenuItem26 = new javax.swing.JMenuItem();
jSeparator73 = new javax.swing.JPopupMenu.Separator();
jMenuItem23 = new javax.swing.JMenuItem();
jSeparator72 = new javax.swing.JPopupMenu.Separator();
jMenuItem40 = new javax.swing.JMenuItem();
jSeparator63 = new javax.swing.JPopupMenu.Separator();
jMenuItem41 = new javax.swing.JMenuItem();
jSeparator61 = new javax.swing.JPopupMenu.Separator();
jSeparator52 = new javax.swing.JPopupMenu.Separator();
jMenuItem24 = new javax.swing.JMenuItem();
jSeparator59 = new javax.swing.JPopupMenu.Separator();
jMenu24 = new javax.swing.JMenu();
jSeparator57 = new javax.swing.JPopupMenu.Separator();
jSeparator117 = new javax.swing.JPopupMenu.Separator();
jSeparator68 = new javax.swing.JPopupMenu.Separator();
jMenuItem32 = new javax.swing.JMenuItem();
jSeparator58 = new javax.swing.JPopupMenu.Separator();
jSeparator53 = new javax.swing.JPopupMenu.Separator();
jMenu25 = new javax.swing.JMenu();
jSeparator56 = new javax.swing.JPopupMenu.Separator();
jMenuItem27 = new javax.swing.JMenuItem();
jSeparator54 = new javax.swing.JPopupMenu.Separator();
jMenuItem28 = new javax.swing.JMenuItem();
jSeparator55 = new javax.swing.JPopupMenu.Separator();
jMenu28 = new javax.swing.JMenu();
jSeparator150 = new javax.swing.JPopupMenu.Separator();
jMenu30 = new javax.swing.JMenu();
jSeparator112 = new javax.swing.JPopupMenu.Separator();
jMenuItem6 = new javax.swing.JMenuItem();
jSeparator156 = new javax.swing.JPopupMenu.Separator();
jMenuItem33 = new javax.swing.JMenuItem();
jSeparator155 = new javax.swing.JPopupMenu.Separator();
jMenuItem49 = new javax.swing.JMenuItem();
jSeparator154 = new javax.swing.JPopupMenu.Separator();
jMenuItem52 = new javax.swing.JMenuItem();
jSeparator153 = new javax.swing.JPopupMenu.Separator();
jSeparator151 = new javax.swing.JPopupMenu.Separator();
jMenu38 = new javax.swing.JMenu();
jSeparator149 = new javax.swing.JPopupMenu.Separator();
jMenuItem84 = new javax.swing.JMenuItem();
jSeparator158 = new javax.swing.JPopupMenu.Separator();
jMenuItem85 = new javax.swing.JMenuItem();
jSeparator157 = new javax.swing.JPopupMenu.Separator();
jSeparator152 = new javax.swing.JPopupMenu.Separator();
jMenu39 = new javax.swing.JMenu();
jSeparator148 = new javax.swing.JPopupMenu.Separator();
jMenu2 = new javax.swing.JMenu();
jSeparator99 = new javax.swing.JPopupMenu.Separator();
jMenuItem16 = new javax.swing.JMenuItem();
jSeparator97 = new javax.swing.JPopupMenu.Separator();
jMenuItem42 = new javax.swing.JMenuItem();
jSeparator98 = new javax.swing.JPopupMenu.Separator();
jMenuItem44 = new javax.swing.JMenuItem();
jSeparator100 = new javax.swing.JPopupMenu.Separator();
jMenu33 = new javax.swing.JMenu();

jPopupMenu2.add(jSeparator129);

jMenuItem68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/preview.png"))); // NOI18N
jMenuItem68.setText("View Incident");
jMenuItem68.setToolTipText("");
jPopupMenu2.add(jMenuItem68);
jPopupMenu2.add(jSeparator128);

jMenuItem71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/edit.png"))); // NOI18N
jMenuItem71.setText("Update Incident Status");
jPopupMenu2.add(jMenuItem71);
jPopupMenu2.add(jSeparator127);

jMenuItem72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/print.png"))); // NOI18N
jMenuItem72.setText("Print Incident");
jPopupMenu2.add(jMenuItem72);
jPopupMenu2.add(jSeparator126);

jMenuItem73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/delete.png"))); // NOI18N
jMenuItem73.setText("Delete Incident");
jPopupMenu2.add(jMenuItem73);
jPopupMenu2.add(jSeparator130);

jMenuItem74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/load9.png"))); // NOI18N
jMenuItem74.setText("Refresh Incident Data");
jPopupMenu2.add(jMenuItem74);
jPopupMenu2.add(jSeparator131);

jPopupMenu1.add(jSeparator120);

jMenuItem65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/preview.png"))); // NOI18N
jMenuItem65.setText("View Booking");
jPopupMenu1.add(jMenuItem65);
jPopupMenu1.add(jSeparator25);

jMenuItem64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/delete.png"))); // NOI18N
jMenuItem64.setText("Delete Booking");
jPopupMenu1.add(jMenuItem64);
jPopupMenu1.add(jSeparator118);

jMenuItem69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/print.png"))); // NOI18N
jMenuItem69.setText("Print Booking");
jPopupMenu1.add(jMenuItem69);
jPopupMenu1.add(jSeparator119);

jMenuItem66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/load9.png"))); // NOI18N
jMenuItem66.setText("Refresh Data");
jPopupMenu1.add(jMenuItem66);
jPopupMenu1.add(jSeparator121);

setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
addWindowListener(new java.awt.event.WindowAdapter() {
    public void windowActivated(java.awt.event.WindowEvent evt) {
        formWindowActivated(evt);
    }
    });

    jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

    jList1.setModel(new javax.swing.AbstractListModel() {
        String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
        public int getSize() { return strings.length; }
        public Object getElementAt(int i) { return strings[i]; }
    });
    jScrollPane4.setViewportView(jList1);

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
    );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 2106, Short.MAX_VALUE)
    );

    jSplitPane4.setRightComponent(jPanel6);

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
    jScrollPane12.setViewportView(jTable4);

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
    );
    jPanel7Layout.setVerticalGroup(
        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
    );

    jSplitPane4.setLeftComponent(jPanel7);

    jSplitPane1.setDividerLocation(250);

    jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

    jScrollPane9.setViewportView(jXTable1);

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel10Layout.createSequentialGroup()
            .addGap(0, 201, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 202, Short.MAX_VALUE))
        .addComponent(jScrollPane9)
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(20, 20, 20)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(15, 15, 15)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jSplitPane1.setLeftComponent(jPanel1);

    jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jTable1MouseReleased(evt);
        }
    });
    jScrollPane2.setViewportView(jTable1);

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
    );
    jPanel9Layout.setVerticalGroup(
        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel9Layout.createSequentialGroup()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    jSplitPane1.setRightComponent(jPanel3);

    jSplitPane5.setDividerLocation(500);
    jSplitPane5.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

    javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Documents");
    javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Checking & Corrective Action");
    javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Inspection Pan");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Audit Plan");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Corrective Actions");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Reports");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Implementation & Operations");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Ongoing Concerns");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Strategic");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Administrative");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Management");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Management Review");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2010");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2011");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2012");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2013");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2014");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("2015");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Planning");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Plans");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("SHE Policies");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Environmental Policy");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Health and Safety Policy");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Standards");
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Work Instruction / Procedures");
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Forms");
    treeNode1.add(treeNode2);
    jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
    for (int i = 0; i < jTree1.getRowCount(); i++) {
        jTree1.expandRow(i);
    }
    jScrollPane1.setViewportView(jTree1);

    javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
    jPanel17.setLayout(jPanel17Layout);
    jPanel17Layout.setHorizontalGroup(
        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 207, Short.MAX_VALUE)
    );
    jPanel17Layout.setVerticalGroup(
        jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(290, 290, 290))
    );

    jSplitPane5.setLeftComponent(jPanel2);

    jXTaskPaneContainer1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jXTaskPaneContainer1MouseReleased(evt);
        }
    });
    jXTaskPaneContainer1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
            jXTaskPaneContainer1PropertyChange(evt);
        }
    });
    org.jdesktop.swingx.VerticalLayout verticalLayout1 = new org.jdesktop.swingx.VerticalLayout();
    verticalLayout1.setGap(14);
    jXTaskPaneContainer1.setLayout(verticalLayout1);

    jXTaskPane1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jXTaskPane1MouseClicked(evt);
        }
    });

    treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Audting");
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Inspections");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Findings");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Corrective Actions");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Scheduling");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Achieved");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Audits");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Findings");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Corrective Actions");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Scheduling");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Achieved");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Statistics");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Woirk in Progress");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    jTree4.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
    for (int i = 0; i < jTree4.getRowCount(); i++) {
        jTree4.expandRow(i);}
    jScrollPane8.setViewportView(jTree4);

    jXTaskPane1.getContentPane().add(jScrollPane8);

    jXTaskPaneContainer1.add(jXTaskPane1);

    jButton1.setText("Legal Register");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });
    jXTaskPane2.getContentPane().add(jButton1);

    jButton2.setText("Legal rester List");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });
    jXTaskPane2.getContentPane().add(jButton2);

    jXTaskPaneContainer1.add(jXTaskPane2);

    treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Training");
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Skills Matrixs");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Job Criteria");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Skills Audit");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Training Management");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Bookings");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Training Recomendation");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Courses");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hot dogs");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("pizza");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Views");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("bananas");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Reports");
    treeNode1.add(treeNode2);
    jTree5.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
    for (int i = 0; i < jTree5.getRowCount(); i++) {
        jTree5.expandRow(i);}
    jScrollPane10.setViewportView(jTree5);

    jXTaskPane3.getContentPane().add(jScrollPane10);

    jXTaskPaneContainer1.add(jXTaskPane3);

    treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Risk Assessment");
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Identify Risk");
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Revaluate Risk");
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Actions");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Corrective");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Outstandings");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Risk Listing");
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Statistics");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("High Level Risk");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Medium Level Risk");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Low Level Risk");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Graphs");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Outstanding Risks");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Incidents By Person");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Incidents By Site");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Open Cases");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Closed Cases");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    jTree6.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
    for (int i = 0; i < jTree6.getRowCount(); i++) {
        jTree6.expandRow(i);}
    jScrollPane11.setViewportView(jTree6);

    jXTaskPane4.getContentPane().add(jScrollPane11);

    jXTaskPaneContainer1.add(jXTaskPane4);

    treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Incident Management");
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Incidents");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Create Incident");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Additional Information");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Risk Assessment");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Rate Incident Risk");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Manage Incident");
    javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("High Priority");
    treeNode3.add(treeNode4);
    treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Medium Priority");
    treeNode3.add(treeNode4);
    treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Low Priority");
    treeNode3.add(treeNode4);
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Analysis");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Incident Analysis");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Corrective Action");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Capture Corrective Action");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Analyse Action Taken");
    treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Overdue");
    treeNode3.add(treeNode4);
    treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Failed");
    treeNode3.add(treeNode4);
    treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Pending");
    treeNode3.add(treeNode4);
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    jTree3.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
    jTree3.setPreferredSize(new java.awt.Dimension(30, 72));
    jTree3.setSize(new java.awt.Dimension(50, 360));
    jTree3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            jTree3MouseReleased(evt);
        }
    });
    jTree3.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
        public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
            jTree3ValueChanged(evt);
        }
    });
    for (int i = 0; i < jTree3.getRowCount(); i++) {
        jTree3.expandRow(i);}
    jScrollPane7.setViewportView(jTree3);

    jXTaskPane5.getContentPane().add(jScrollPane7);

    jXTaskPaneContainer1.add(jXTaskPane5);

    treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("System Documentation");
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("SHEQ Documentation");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Policies");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Procedures");
    treeNode2.add(treeNode3);
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Forms");
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
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Actions");
    treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("ravioli");
    treeNode2.add(treeNode3);
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Management Reviews");
    treeNode1.add(treeNode2);
    treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Amendment List");
    treeNode1.add(treeNode2);
    jTree9.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
    for (int i = 0; i < jTree9.getRowCount(); i++) {
        jTree9.expandRow(i);}
    jScrollPane15.setViewportView(jTree9);

    jXTaskPane6.getContentPane().add(jScrollPane15);

    jXTaskPaneContainer1.add(jXTaskPane6);

    jButton12.setText("Create Document");
    jXTaskPane7.getContentPane().add(jButton12);

    jButton13.setText("Document Management");
    jButton13.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton13ActionPerformed(evt);
        }
    });
    jXTaskPane7.getContentPane().add(jButton13);

    jButton14.setText("Document List");
    jXTaskPane7.getContentPane().add(jButton14);

    jButton15.setText("Memos");
    jXTaskPane7.getContentPane().add(jButton15);

    jXTaskPaneContainer1.add(jXTaskPane7);

    jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );

    jSplitPane3.setTopComponent(jPanel5);

    jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
    jPanel13.setLayout(jPanel13Layout);
    jPanel13Layout.setHorizontalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanel13Layout.setVerticalGroup(
        jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 95, Short.MAX_VALUE)
    );

    jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
    jScrollPane6.setViewportView(jTable3);

    javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
    jPanel16.setLayout(jPanel16Layout);
    jPanel16Layout.setHorizontalGroup(
        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    jPanel16Layout.setVerticalGroup(
        jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
    jPanel11.setLayout(jPanel11Layout);
    jPanel11Layout.setHorizontalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    jPanel11Layout.setVerticalGroup(
        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel11Layout.createSequentialGroup()
            .addGap(57, 57, 57)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(152, 152, 152)
            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jSplitPane3.setRightComponent(jPanel11);

    jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );

    jSplitPane2.setLeftComponent(jPanel4);

    jScrollPane5.setViewportView(jTree2);

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel8Layout.createSequentialGroup()
            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel8Layout.setVerticalGroup(
        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
    );

    jSplitPane2.setTopComponent(jPanel8);

    jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    jButton3.setText("jButton3");

    jButton4.setText("jButton4");

    jButton5.setText("jButton5");

    jButton6.setText("jButton6");

    jButton7.setText("jButton7");

    jButton8.setText("jButton8");

    jButton9.setText("jButton9");

    jButton10.setText("jButton10");

    jButton11.setText("jButton11");

    javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
    jPanel15.setLayout(jPanel15Layout);
    jPanel15Layout.setHorizontalGroup(
        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    jPanel15Layout.setVerticalGroup(
        jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel15Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, 0)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(0, 0, 0)
            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
    jPanel14.setLayout(jPanel14Layout);
    jPanel14Layout.setHorizontalGroup(
        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel14Layout.setVerticalGroup(
        jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
    jPanel12.setLayout(jPanel12Layout);
    jPanel12Layout.setHorizontalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel12Layout.setVerticalGroup(
        jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel12Layout.createSequentialGroup()
            .addGap(36, 36, 36)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jSplitPane2.setRightComponent(jPanel12);

    javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
    jDesktopPane1.setLayout(jDesktopPane1Layout);
    jDesktopPane1Layout.setHorizontalGroup(
        jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jDesktopPane1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSplitPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSplitPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addContainerGap())
    );
    jDesktopPane1Layout.setVerticalGroup(
        jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jDesktopPane1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createSequentialGroup()
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDesktopPane1Layout.createSequentialGroup()
                            .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSplitPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addComponent(jSplitPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addComponent(jXTaskPaneContainer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
    );
    jDesktopPane1.setLayer(jSplitPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jDesktopPane1.setLayer(jSplitPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jDesktopPane1.setLayer(jSplitPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jDesktopPane1.setLayer(jXTaskPaneContainer1, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jDesktopPane1.setLayer(jSplitPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
    jDesktopPane1.setLayer(jSplitPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

    jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/file98.png"))); // NOI18N
    jMenu1.setMnemonic('f');
    jMenu1.setText("File");
    jMenu1.add(jSeparator1);

    jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/tie21.png"))); // NOI18N
    jMenu3.setText("Auditing");
    jMenu3.add(jSeparator71);

    jMenuItem47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/search102.png"))); // NOI18N
    jMenuItem47.setText("Findings");
    jMenuItem47.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem47ActionPerformed(evt);
        }
    });
    jMenu3.add(jMenuItem47);
    jMenu3.add(jSeparator78);

    jMenuItem48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/checked2.png"))); // NOI18N
    jMenuItem48.setText("Corrective Actions");
    jMenuItem48.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem48ActionPerformed(evt);
        }
    });
    jMenu3.add(jMenuItem48);
    jMenu3.add(jSeparator137);

    jMenuItem78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/add98.png"))); // NOI18N
    jMenuItem78.setText("Scheduling");
    jMenuItem78.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem78ActionPerformed(evt);
        }
    });
    jMenu3.add(jMenuItem78);
    jMenu3.add(jSeparator79);

    jMenu36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/panel.png"))); // NOI18N
    jMenu36.setText("Statistics");
    jMenu36.add(jSeparator142);

    jMenuItem35.setText("Scheduled Audits");
    jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem35ActionPerformed(evt);
        }
    });
    jMenu36.add(jMenuItem35);
    jMenu36.add(jSeparator70);

    jMenuItem46.setText("Achieved Audits");
    jMenuItem46.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem46ActionPerformed(evt);
        }
    });
    jMenu36.add(jMenuItem46);
    jMenu36.add(jSeparator144);

    jMenuItem82.setText("Pending Audits");
    jMenuItem82.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem82ActionPerformed(evt);
        }
    });
    jMenu36.add(jMenuItem82);
    jMenu36.add(jSeparator145);

    jMenuItem83.setText("Outstanding Audits");
    jMenu36.add(jMenuItem83);
    jMenu36.add(jSeparator143);

    jMenu3.add(jMenu36);
    jMenu3.add(jSeparator67);

    jMenu37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/report1.png"))); // NOI18N
    jMenu37.setText("Reports");
    jMenu37.add(jSeparator147);

    jMenuItem34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/panel.png"))); // NOI18N
    jMenuItem34.setText("Statistics");
    jMenu37.add(jMenuItem34);
    jMenu37.add(jSeparator146);

    jMenu3.add(jMenu37);
    jMenu3.add(jSeparator69);

    jMenu1.add(jMenu3);
    jMenu1.add(jSeparator5);

    jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/magistrate.png"))); // NOI18N
    jMenu4.setText("Legal Compliance");
    jMenu4.add(jSeparator106);

    jMenuItem58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/books8.png"))); // NOI18N
    jMenuItem58.setText("Legal Register");
    jMenuItem58.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem58ActionPerformed(evt);
        }
    });
    jMenu4.add(jMenuItem58);
    jMenu4.add(jSeparator105);

    jMenuItem59.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/list4.png"))); // NOI18N
    jMenuItem59.setText("Legal Documents List");
    jMenuItem59.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem59ActionPerformed(evt);
        }
    });
    jMenu4.add(jMenuItem59);
    jMenu4.add(jSeparator104);

    jMenu1.add(jMenu4);
    jMenu1.add(jSeparator4);

    jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/seo40-2.png"))); // NOI18N
    jMenu5.setText("Training Management");
    jMenu5.add(jSeparator41);

    jMenu18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/tool240.png"))); // NOI18N
    jMenu18.setText("Skills Matrixs");
    jMenu18.add(jSeparator40);

    jMenuItem20.setText("Job Criteria Defination");
    jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem20ActionPerformed(evt);
        }
    });
    jMenu18.add(jMenuItem20);
    jMenu18.add(jSeparator39);

    jMenuItem19.setText("Employee Skills Audit");
    jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem19ActionPerformed(evt);
        }
    });
    jMenu18.add(jMenuItem19);
    jMenu18.add(jSeparator38);

    jMenu5.add(jMenu18);
    jMenu5.add(jSeparator44);

    jMenu20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/businessman270.png"))); // NOI18N
    jMenu20.setText("Training Management");
    jMenu20.add(jSeparator51);

    jMenuItem17.setText("Book Course");
    jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem17ActionPerformed(evt);
        }
    });
    jMenu20.add(jMenuItem17);
    jMenu20.add(jSeparator42);

    jMenuItem18.setText("Employee Traing Recommendations");
    jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem18ActionPerformed(evt);
        }
    });
    jMenu20.add(jMenuItem18);
    jMenu20.add(jSeparator50);

    jMenu5.add(jMenu20);
    jMenu5.add(jSeparator46);

    jMenu21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/books8.png"))); // NOI18N
    jMenu21.setText("Courses");
    jMenu21.add(jSeparator141);

    jMenuItem80.setText("Create Course");
    jMenuItem80.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem80ActionPerformed(evt);
        }
    });
    jMenu21.add(jMenuItem80);
    jMenu21.add(jSeparator140);

    jMenuItem81.setText("Assign Default Provide");
    jMenuItem81.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem81ActionPerformed(evt);
        }
    });
    jMenu21.add(jMenuItem81);
    jMenu21.add(jSeparator139);

    jMenu5.add(jMenu21);
    jMenu5.add(jSeparator45);

    jMenu22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/list40.png"))); // NOI18N
    jMenu22.setText("Views");
    jMenu22.add(jSeparator49);

    jMenuItem21.setText("Booked Training");
    jMenu22.add(jMenuItem21);
    jMenu22.add(jSeparator47);

    jMenuItem22.setText("Employee Recommended Training");
    jMenu22.add(jMenuItem22);
    jMenu22.add(jSeparator48);

    jMenu5.add(jMenu22);
    jMenu5.add(jSeparator43);

    jMenu19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/report1.png"))); // NOI18N
    jMenu19.setText("Training Report");
    jMenu5.add(jMenu19);
    jMenu5.add(jSeparator81);

    jMenuItem86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/add98.png"))); // NOI18N
    jMenuItem86.setText("Training Planning");
    jMenu5.add(jMenuItem86);
    jMenu5.add(jSeparator80);

    jMenu1.add(jMenu5);
    jMenu1.add(jSeparator3);

    jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/mine4.png"))); // NOI18N
    jMenu6.setText("Risk Assessment");
    jMenu6.add(jSeparator10);

    jMenuItem53.setText("Identify Risk");
    jMenuItem53.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem53ActionPerformed(evt);
        }
    });
    jMenu6.add(jMenuItem53);
    jMenu6.add(jSeparator74);

    jMenuItem56.setText("Revaluate Risk");
    jMenuItem56.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem56ActionPerformed(evt);
        }
    });
    jMenu6.add(jMenuItem56);
    jMenu6.add(jSeparator77);

    jMenuItem54.setText("List Risk");
    jMenuItem54.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem54ActionPerformed(evt);
        }
    });
    jMenu6.add(jMenuItem54);
    jMenu6.add(jSeparator122);

    jMenu34.setText("Statistics");
    jMenu34.add(jSeparator125);

    jMenuItem55.setText("High Level Risk");
    jMenu34.add(jMenuItem55);
    jMenu34.add(jSeparator124);

    jMenuItem67.setText("Medium Level Risk");
    jMenu34.add(jMenuItem67);
    jMenu34.add(jSeparator76);

    jMenuItem70.setText("Low Level Risk");
    jMenu34.add(jMenuItem70);
    jMenu34.add(jSeparator123);

    jMenu6.add(jMenu34);
    jMenu6.add(jSeparator75);

    jMenu1.add(jMenu6);
    jMenu1.add(jSeparator6);

    jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/accident3.png"))); // NOI18N
    jMenu10.setText("Incident Management");
    jMenu10.add(jSeparator16);
    jMenu10.add(jSeparator15);

    jMenu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/exclamation mark1.png"))); // NOI18N
    jMenu12.setText("Incidents");
    jMenu12.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenu12ActionPerformed(evt);
        }
    });
    jMenu12.add(jSeparator28);

    jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/add118.png"))); // NOI18N
    jMenuItem4.setText("Capture Incident");
    jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem4ActionPerformed(evt);
        }
    });
    jMenu12.add(jMenuItem4);
    jMenu12.add(jSeparator27);

    jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/add139-4.png"))); // NOI18N
    jMenuItem5.setText("Capture Additional Incident Information");
    jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem5ActionPerformed(evt);
        }
    });
    jMenu12.add(jMenuItem5);
    jMenu12.add(jSeparator26);

    jMenu10.add(jMenu12);
    jMenu10.add(jSeparator14);

    jMenu13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/dangerous3.png"))); // NOI18N
    jMenu13.setText("Risk Assessment     ");
    jMenu13.add(jSeparator31);
    jMenu13.add(jSeparator30);

    jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/symbol147.png"))); // NOI18N
    jMenuItem7.setText("Rate Incident Risk");
    jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem7ActionPerformed(evt);
        }
    });
    jMenu13.add(jMenuItem7);
    jMenu13.add(jSeparator29);

    jMenu10.add(jMenu13);
    jMenu10.add(jSeparator13);

    jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/graphic49.png"))); // NOI18N
    jMenu14.setText("Analysis");
    jMenu14.add(jSeparator37);

    jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/search109.png"))); // NOI18N
    jMenuItem8.setText("Analysize Incident");
    jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem8ActionPerformed(evt);
        }
    });
    jMenu14.add(jMenuItem8);
    jMenu14.add(jSeparator36);

    jMenu16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/management3.png"))); // NOI18N
    jMenu16.setText("Manage Incidents");
    jMenu16.add(jSeparator32);

    jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/alarm.png"))); // NOI18N
    jMenuItem10.setText("High Priority Incident");
    jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem10ActionPerformed(evt);
        }
    });
    jMenu16.add(jMenuItem10);
    jMenu16.add(jSeparator34);

    jMenuItem11.setText("Medium Priority Incidents");
    jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem11ActionPerformed(evt);
        }
    });
    jMenu16.add(jMenuItem11);
    jMenu16.add(jSeparator35);

    jMenuItem12.setText("Low Priority Incidents");
    jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem12ActionPerformed(evt);
        }
    });
    jMenu16.add(jMenuItem12);
    jMenu16.add(jSeparator33);

    jMenu14.add(jMenu16);

    jMenu10.add(jMenu14);
    jMenu10.add(jSeparator11);

    jMenu15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/checked2.png"))); // NOI18N
    jMenu15.setText("Corrective Action");
    jMenu15.add(jSeparator21);

    jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/add139-4.png"))); // NOI18N
    jMenuItem9.setText("Capture Corrective Action");
    jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem9ActionPerformed(evt);
        }
    });
    jMenu15.add(jMenuItem9);
    jMenu15.add(jSeparator22);

    jMenu17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/graphic49.png"))); // NOI18N
    jMenu17.setText("Analyse Actions Taken");
    jMenu17.add(jSeparator20);

    jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/wall clock.png"))); // NOI18N
    jMenuItem13.setText("Overdue Action");
    jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem13ActionPerformed(evt);
        }
    });
    jMenu17.add(jMenuItem13);
    jMenu17.add(jSeparator19);

    jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/dangerous3.png"))); // NOI18N
    jMenuItem14.setText("Failed Actions");
    jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem14ActionPerformed(evt);
        }
    });
    jMenu17.add(jMenuItem14);
    jMenu17.add(jSeparator18);

    jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/disabled.png"))); // NOI18N
    jMenuItem15.setText("Actions awating Approval");
    jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem15ActionPerformed(evt);
        }
    });
    jMenu17.add(jMenuItem15);
    jMenu17.add(jSeparator17);

    jMenu15.add(jMenu17);

    jMenu10.add(jMenu15);
    jMenu10.add(jSeparator12);

    jMenu1.add(jMenu10);
    jMenu1.add(jSeparator7);

    jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/search99.png"))); // NOI18N
    jMenu9.setText("System Documentation");
    jMenu9.add(jSeparator91);

    jMenu26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/books8.png"))); // NOI18N
    jMenu26.setText("SHEQ Documents");
    jMenu26.add(jSeparator90);

    jMenuItem36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/sign20.png"))); // NOI18N
    jMenuItem36.setText("SHEQ Policies");
    jMenu26.add(jMenuItem36);
    jMenu26.add(jSeparator89);

    jMenuItem37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/list40.png"))); // NOI18N
    jMenuItem37.setText("SHEQ Procedures");
    jMenu26.add(jMenuItem37);
    jMenu26.add(jSeparator101);

    jMenuItem38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/bill.png"))); // NOI18N
    jMenuItem38.setText("SHEQ Forms");
    jMenu26.add(jMenuItem38);
    jMenu26.add(jSeparator96);

    jMenu9.add(jMenu26);
    jMenu9.add(jSeparator87);

    jMenu29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/wall clock.png"))); // NOI18N
    jMenu29.setText("Planning");
    jMenu29.add(jSeparator95);

    jMenuItem50.setText("jMenuItem50");
    jMenu29.add(jMenuItem50);
    jMenu29.add(jSeparator94);

    jMenuItem51.setText("jMenuItem51");
    jMenu29.add(jMenuItem51);
    jMenu29.add(jSeparator93);

    jMenu9.add(jMenu29);
    jMenu9.add(jSeparator92);

    jMenuItem43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/businessman270.png"))); // NOI18N
    jMenuItem43.setText("Implementation");
    jMenu9.add(jMenuItem43);
    jMenu9.add(jSeparator115);

    jMenu27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/report1.png"))); // NOI18N
    jMenu27.setText("Actions");
    jMenu9.add(jMenu27);
    jMenu9.add(jSeparator114);

    jMenuItem45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/wall clock.png"))); // NOI18N
    jMenuItem45.setText("Management");
    jMenu9.add(jMenuItem45);
    jMenu9.add(jSeparator113);

    jMenuItem39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/list4.png"))); // NOI18N
    jMenuItem39.setText("Ammendments / Recommenddation List");
    jMenu9.add(jMenuItem39);
    jMenu9.add(jSeparator88);

    jMenu1.add(jMenu9);
    jMenu1.add(jSeparator8);

    jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/doc.png"))); // NOI18N
    jMenu7.setText("Key Corporate Documents");
    jMenu7.add(jSeparator109);

    jMenu31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/books8.png"))); // NOI18N
    jMenu31.setText("Document Management");
    jMenu7.add(jMenu31);
    jMenu7.add(jSeparator108);

    jMenuItem57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/add118.png"))); // NOI18N
    jMenuItem57.setText("Create Document");
    jMenu7.add(jMenuItem57);
    jMenu7.add(jSeparator107);

    jMenu32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/list4.png"))); // NOI18N
    jMenu32.setText("Document List");
    jMenu32.add(jSeparator110);

    jMenuItem60.setText("Management");
    jMenu32.add(jMenuItem60);
    jMenu32.add(jSeparator103);

    jMenuItem61.setText("Supervisors");
    jMenu32.add(jMenuItem61);
    jMenu32.add(jSeparator86);

    jMenuItem62.setText("General");
    jMenu32.add(jMenuItem62);
    jMenu32.add(jSeparator111);

    jMenu7.add(jMenu32);
    jMenu7.add(jSeparator102);

    jMenuItem63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/newspaper18.png"))); // NOI18N
    jMenuItem63.setText("Memos");
    jMenu7.add(jMenuItem63);

    jMenu1.add(jMenu7);
    jMenu1.add(jSeparator2);

    jMenuBar1.add(jMenu1);

    jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/men16.png"))); // NOI18N
    jMenu11.setMnemonic('p');
    jMenu11.setText("Peoples");
    jMenu11.add(jSeparator116);

    jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/worker25.png"))); // NOI18N
    jMenuItem2.setText("Employees");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem2ActionPerformed(evt);
        }
    });
    jMenu11.add(jMenuItem2);
    jMenu11.add(jSeparator66);

    jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/class6.png"))); // NOI18N
    jMenuItem30.setText("Learners");
    jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem30ActionPerformed(evt);
        }
    });
    jMenu11.add(jMenuItem30);
    jMenu11.add(jSeparator65);

    jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/user2.png"))); // NOI18N
    jMenuItem31.setText("Users");
    jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem31ActionPerformed(evt);
        }
    });
    jMenu11.add(jMenuItem31);
    jMenu11.add(jSeparator64);

    jMenu35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/users6.png"))); // NOI18N
    jMenu35.setText("Groups");
    jMenu35.add(jSeparator135);

    jMenuItem29.setText("Audit Teams");
    jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem29ActionPerformed(evt);
        }
    });
    jMenu35.add(jMenuItem29);
    jMenu35.add(jSeparator132);

    jMenuItem75.setText("Investigation Teams");
    jMenuItem75.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem75ActionPerformed(evt);
        }
    });
    jMenu35.add(jMenuItem75);
    jMenu35.add(jSeparator134);

    jMenuItem76.setText("Inspection Teams");
    jMenuItem76.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem76ActionPerformed(evt);
        }
    });
    jMenu35.add(jMenuItem76);
    jMenu35.add(jSeparator133);

    jMenuItem77.setText("System Administrators");
    jMenu35.add(jMenuItem77);
    jMenu35.add(jSeparator136);

    jMenu11.add(jMenu35);
    jMenu11.add(jSeparator24);

    jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/settings19.png"))); // NOI18N
    jMenuItem3.setText("Responsibilities");
    jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem3ActionPerformed(evt);
        }
    });
    jMenu11.add(jMenuItem3);
    jMenu11.add(jSeparator23);

    jMenuBar1.add(jMenu11);

    jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/cogwheel28.png"))); // NOI18N
    jMenu8.setMnemonic('s');
    jMenu8.setText("System Configuration");
    jMenu8.add(jSeparator62);

    jMenu23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/data90.png"))); // NOI18N
    jMenu23.setText("Import / Export Data");
    jMenu23.add(jSeparator60);

    jMenuItem26.setText("Import Employees");
    jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem26ActionPerformed(evt);
        }
    });
    jMenu23.add(jMenuItem26);
    jMenu23.add(jSeparator73);

    jMenuItem23.setText("Import Courses");
    jMenu23.add(jMenuItem23);
    jMenu23.add(jSeparator72);

    jMenuItem40.setText("Export  Employee Masters File");
    jMenu23.add(jMenuItem40);
    jMenu23.add(jSeparator63);

    jMenuItem41.setText("Export Document List");
    jMenu23.add(jMenuItem41);
    jMenu23.add(jSeparator61);

    jMenu8.add(jMenu23);
    jMenu8.add(jSeparator52);

    jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/data112.png"))); // NOI18N
    jMenuItem24.setText("Company Setup");
    jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem24ActionPerformed(evt);
        }
    });
    jMenu8.add(jMenuItem24);
    jMenu8.add(jSeparator59);

    jMenu24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/wireless29.png"))); // NOI18N
    jMenu24.setText("System Paramenter Configuration");
    jMenu24.add(jSeparator57);
    jMenu24.add(jSeparator117);
    jMenu24.add(jSeparator68);

    jMenuItem32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/plus sign1.png"))); // NOI18N
    jMenuItem32.setText("Create Person");
    jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem32ActionPerformed(evt);
        }
    });
    jMenu24.add(jMenuItem32);
    jMenu24.add(jSeparator58);

    jMenu8.add(jMenu24);
    jMenu8.add(jSeparator53);

    jMenu25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/database.png"))); // NOI18N
    jMenu25.setText("System Backup / Restore");
    jMenu25.add(jSeparator56);

    jMenuItem27.setText("Backup");
    jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem27ActionPerformed(evt);
        }
    });
    jMenu25.add(jMenuItem27);
    jMenu25.add(jSeparator54);

    jMenuItem28.setText("Restore");
    jMenu25.add(jMenuItem28);
    jMenu25.add(jSeparator55);

    jMenu8.add(jMenu25);

    jMenuBar1.add(jMenu8);

    jMenu28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/email5-2.png"))); // NOI18N
    jMenu28.setText("Messages");
    jMenu28.add(jSeparator150);

    jMenu30.setText("Risk Assessment");
    jMenu30.add(jSeparator112);

    jMenuItem6.setText("New Risk Reporting");
    jMenu30.add(jMenuItem6);
    jMenu30.add(jSeparator156);

    jMenuItem33.setText("Completed Risk Assessment");
    jMenu30.add(jMenuItem33);
    jMenu30.add(jSeparator155);

    jMenuItem49.setText("Risk Assessment");
    jMenu30.add(jMenuItem49);
    jMenu30.add(jSeparator154);

    jMenuItem52.setText("High Risk Reported");
    jMenu30.add(jMenuItem52);
    jMenu30.add(jSeparator153);

    jMenu28.add(jMenu30);
    jMenu28.add(jSeparator151);

    jMenu38.setText("Incident Reporting");
    jMenu38.add(jSeparator149);

    jMenuItem84.setText("New Incident");
    jMenuItem84.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem84ActionPerformed(evt);
        }
    });
    jMenu38.add(jMenuItem84);
    jMenu38.add(jSeparator158);

    jMenuItem85.setText("Incidents Closed");
    jMenuItem85.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem85ActionPerformed(evt);
        }
    });
    jMenu38.add(jMenuItem85);
    jMenu38.add(jSeparator157);

    jMenu28.add(jMenu38);
    jMenu28.add(jSeparator152);

    jMenu39.setText("Training Management");
    jMenu28.add(jMenu39);
    jMenu28.add(jSeparator148);

    jMenuBar1.add(jMenu28);

    jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/call center.png"))); // NOI18N
    jMenu2.setMnemonic('e');
    jMenu2.setText("Help");
    jMenu2.add(jSeparator99);

    jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/book83.png"))); // NOI18N
    jMenuItem16.setText("User Manual");
    jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem16ActionPerformed(evt);
        }
    });
    jMenu2.add(jMenuItem16);
    jMenu2.add(jSeparator97);

    jMenuItem42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/travelling12.png"))); // NOI18N
    jMenuItem42.setText("About");
    jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jMenuItem42ActionPerformed(evt);
        }
    });
    jMenu2.add(jMenuItem42);
    jMenu2.add(jSeparator98);

    jMenuItem44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/speech bubble133.png"))); // NOI18N
    jMenuItem44.setText("Support");
    jMenu2.add(jMenuItem44);
    jMenu2.add(jSeparator100);

    //jMenu2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    //jMenuBar1.add(Box.createHorizontalGlue());

    jMenuBar1.add(jMenu2);

    jMenu33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dbase/Resources/power28.png"))); // NOI18N
    jMenu33.addMenuListener(new javax.swing.event.MenuListener() {
        public void menuSelected(javax.swing.event.MenuEvent evt) {
            jMenu33MenuSelected(evt);
        }
        public void menuDeselected(javax.swing.event.MenuEvent evt) {
        }
        public void menuCanceled(javax.swing.event.MenuEvent evt) {
        }
    });

    jMenu33.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    jMenuBar1.add(Box.createHorizontalGlue());

    jMenuBar1.add(jMenu33);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jDesktopPane1)
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jDesktopPane1)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //jMenu33.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        
        
        
        
        DefaultPieDataset pieDataSet = new DefaultPieDataset(); 
        pieDataSet.setValue("Completed", new Integer(10));
        pieDataSet.setValue("Awaiting Actions", new Integer(20));
        pieDataSet.setValue("Pending", new Integer(30));
        pieDataSet.setValue("Sheduled", new Integer(40));
        JFreeChart chart = ChartFactory.createPieChart3D("Autit Schedule Report", pieDataSet, true, false, false);
        PiePlot p = (PiePlot)chart.getPlot();
        //p.setForegroundAlpha(BOTTOM_ALIGNMENT);
        ChartPanel CP = new ChartPanel(chart);
        CP.setVisible(true);
        CP.setSize(350,250);
        jPanel2.add(CP, BOTTOM_ALIGNMENT);
        jPanel2.validate();
        
        
        DefaultCategoryDataset barDataSet = new DefaultCategoryDataset();
        barDataSet.setValue(1, "Medium Risk", "Medium");
        barDataSet.setValue(4, "OutStanding", "Medium");
        
        barDataSet.setValue(3, "Low Risk", "Low");
        barDataSet.setValue(1, "Outstanding", "Low");
        
        barDataSet.setValue(2, "High Risk", "High");
        barDataSet.setValue(2, "Low Risk", "High");
        
        JFreeChart chart1 = ChartFactory.createBarChart(null, "Trained", "Number", barDataSet,PlotOrientation.VERTICAL,true, true, true);
        CategoryPlot p1;
        p1 = chart1.getCategoryPlot();
        p1.setRangeGridlinePaint(Color.black);
        ChartPanel CP1 = new ChartPanel(chart1);
        CP1.setVisible(true);
        CP1.setSize(485,250);
        jPanel4.add(CP1, BOTTOM_ALIGNMENT);
        jPanel4.validate();
        
        DefaultCategoryDataset plotDataSet = new DefaultCategoryDataset();
        plotDataSet.setValue(10, "Incidents", "1st Quarter");
        plotDataSet.setValue(5, "Incidents", "2nd Quarter");
        
        plotDataSet.setValue(7, "Incidents", "3rd Quarter");
        plotDataSet.setValue(2, "Incidents", "4th Quarter");
        
        JFreeChart chart2 = ChartFactory.createLineChart("Incidents Vs Quaters", " Quarters" ,"Number of Incidents", plotDataSet,PlotOrientation.HORIZONTAL,true, true, false);
        
        ChartPanel CP2 = new ChartPanel(chart2);
        CP2.setVisible(true);
        CP2.setSize(485,120);
        jPanel5.add(CP2, BOTTOM_ALIGNMENT);
        jPanel5.validate();
        
        
        
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(1, "trained", "Male");
        dataSet.setValue(3, "trained", "Female");
        
        dataSet.setValue(2, "awaitingtraining", "Male");
        dataSet.setValue(4, "awaitingtraining", "Female");
        
        dataSet.setValue(1, "toBetrained", "Male");
        dataSet.setValue(2, "toBetrained", "Female");
        
        JFreeChart chart3 = ChartFactory.createStackedAreaChart(null, "Incidents", "Number", dataSet,PlotOrientation.VERTICAL,true, true, true);
        CategoryPlot p3;
        p3 = chart3.getCategoryPlot();
        p3.setRangeGridlinePaint(Color.black);
        ChartPanel CP3 = new ChartPanel(chart3);
        CP3.setVisible(true);
        CP3.setSize(905,255);
        jPanel3.add(CP3, BOTTOM_ALIGNMENT);
        jPanel3.validate();
        
        
        Calendar expireDate = Calendar.getInstance();
        expireDate.set(2016, 02, 14);
        System.out.println(expireDate.getTime());
        if(Calendar.getInstance().getTime().after(expireDate.getTime())){
            JOptionPane.showMessageDialog(DashBoards.this, "Your trial has expired please contact developer");
            System.exit(0);
        }
        //Splash splash = new Splash();
        //splash.setVisible(true);
        //for(int i = jTree3.getRowCount() - 1; i >= 0; i ++){
            //jTree3.collapseRow(i);
        //}

    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        EmployeeDetails.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        try {
            Incident incident = new Incident();
            //Incident.getObj().setVisible(true);
            incident.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(DashBoards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        //IncidentDetails iD = new IncidentDetails();
        IncidentDetails.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
      RiskRating.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        IncidentAnalysis.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        Corrective.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem26ActionPerformed
    public static void backUpDatabase(Connection conn)throws SQLException
    {
        String backupdirectory ="/Users/MathomeTD/Desktop/";
        CallableStatement cs = conn.prepareCall("CALL SYSCS_UTIL.SYSCS_BACKUP_DATABASE(?)"); 
        cs.setString(1, backupdirectory);
        cs.execute(); 
        cs.close();
        System.out.println("backed up database to "+backupdirectory);
        }
    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        
        try {
            backUpDatabase(DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#"));
        } catch (SQLException ex) {
            Logger.getLogger(DashBoards.class.getName()).log(Level.SEVERE, null, ex);
        }
        Source[] sources = new Source[]{
            new Source("/Users/MathomeTD/Desktop/Incident")};
        String [] destinations = new String[]{
            "/Users/MathomeTD/Desktop/untitled folder"
        };
        CopyJob copyJob = new CopyJob(sources, destinations);
        FileCopier fileCopier = new FileCopier();
        FileCopierPanel fileCopierPanel = new FileCopierPanel();
        try {
            fileCopier.copy(copyJob);
            fileCopierPanel.setFileCopier(fileCopier);
        } catch (IOException ex) {
            Logger.getLogger(DashBoards.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /**try {
        final Path targetPath = null;
        final Path sourcePath = null ;
                Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
                    public FileVisitResult preVisitDirectory(final Path dir,
                            final BasicFileAttributes attrs) throws IOException {
                        Files.createDirectories(targetPath.resolve(sourcePath
                                .relativize(dir)));
                        return FileVisitResult.CONTINUE;
                    }
                    
                    public FileVisitResult visitFile(final Path file,
                            final BasicFileAttributes attrs) throws IOException {
                        Files.copy(file,
                                targetPath.resolve(sourcePath.relativize(file)));
                        return FileVisitResult.CONTINUE;
                    }
                }); } catch (IOException ex) {
            Logger.getLogger(DashBoards.class.getName()).log(Level.SEVERE, null, ex);
        }
        * **/
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenu12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu12ActionPerformed

    private void jMenuItem58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem58ActionPerformed
        LegalRegister.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem58ActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        jTable1.setComponentPopupMenu(jPopupMenu1);
        if (evt.isPopupTrigger())
        {
            jPopupMenu1.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void jTree3ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree3ValueChanged
        try {
            String node = evt.getNewLeadSelectionPath().getLastPathComponent().toString();
            switch (node) {
                case "Create Incident":
                    Incident incident = new Incident();
                    incident.setVisible(true);
                    break;
                case "Additional Information":
                    IncidentDetails.getObj().setVisible(true);
                    break;
                case "Rate Incident Risk":
                    RiskRating.getObj().setVisible(true);
                    break;
                case "Incident Analysis":
                    IncidentAnalysis.getObj().setVisible(true);
                    break;
                case "Capture Corrective Action":
                    Corrective.getObj().setVisible(true);
                    break;
                case "Root Cause":
                    IncidentRootCause.getObj().setVisible(true);
                    break;
                case "High Priority":
                    HighPriority.getObj().setVisible(true);
                    break;
                case "E":
                    
                    break;
                case "H":
                    
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashBoards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTree3ValueChanged

    private void jMenuItem59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem59ActionPerformed
        LegalRegisterList.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem59ActionPerformed

    private void jMenuItem47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem47ActionPerformed
        InternalAudits.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem47ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        LegalRegisterList.getObj().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LegalRegister.getObj().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseReleased
        jTable2.setComponentPopupMenu(jPopupMenu1);
        if (evt.isPopupTrigger())
        {
            jPopupMenu1.show(this, evt.getX(), evt.getY());
        } 
    }//GEN-LAST:event_jTable2MouseReleased

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem35ActionPerformed
        ScheduledAudits.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem49ActionPerformed
        InspectionScheduling.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem49ActionPerformed

    private void jMenuItem53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem53ActionPerformed
        CreateRisk.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem53ActionPerformed

    private void jMenuItem56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem56ActionPerformed
        RiskRevaluation.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem56ActionPerformed

    private void jMenuItem55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem55ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem55ActionPerformed

    private void jMenuItem54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem54ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem54ActionPerformed

    private void jTable3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseReleased
        jTable3.setComponentPopupMenu(jPopupMenu1);
        if (evt.isPopupTrigger())
        {
            jPopupMenu1.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTable3MouseReleased

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        SkillsMatrix.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        JoRequirements.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
       Book.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem67ActionPerformed
        CreateCourse.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem67ActionPerformed

    private void jMenuItem70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem70ActionPerformed
        PreferedServideProvider.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem70ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CreateRisk.getObj().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        RiskRevaluation.getObj().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem71ActionPerformed
        //ProgressMonitor.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem71ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        CompanySetup.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        SystemUsers.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem36ActionPerformed
        SHEQPolicies.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenu33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu33ActionPerformed

    }//GEN-LAST:event_jMenu33ActionPerformed

    private void jMenu33MenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_jMenu33MenuSelected
        System.exit(0);
    }//GEN-LAST:event_jMenu33MenuSelected

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        AuditTeam.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jXTaskPaneContainer1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTaskPaneContainer1MouseReleased

    }//GEN-LAST:event_jXTaskPaneContainer1MouseReleased

    private void jXTaskPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTaskPane1MouseClicked

    }//GEN-LAST:event_jXTaskPane1MouseClicked

    private void jXTaskPaneContainer1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jXTaskPaneContainer1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jXTaskPaneContainer1PropertyChange

    private void jMenuItem78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem78ActionPerformed
        AuditScheduling.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem78ActionPerformed

    private void jTree3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTree3MouseReleased

    private void jMenuItem81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem81ActionPerformed
        PreferedServideProvider.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem81ActionPerformed

    private void jMenuItem80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem80ActionPerformed
        CreateCourse.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem80ActionPerformed

    private void jMenuItem48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem48ActionPerformed
        AuditCorrective.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem48ActionPerformed

    private void jMenuItem46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem46ActionPerformed
      AchievedAudits.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem46ActionPerformed

    private void jMenuItem82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem82ActionPerformed
        PendingAudits.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem82ActionPerformed

    private void jMenuItem75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem75ActionPerformed
        InvestigationTeams.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem75ActionPerformed

    private void jMenuItem76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem76ActionPerformed
        InspectionTeams.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem76ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        Person.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem84ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem84ActionPerformed
        CreatedIncident.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem84ActionPerformed

    private void jMenuItem42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem42ActionPerformed
        About.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem42ActionPerformed

    private void jMenuItem85ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem85ActionPerformed
        ClosedIncidents.getObj().setVisible(true);
    }//GEN-LAST:event_jMenuItem85ActionPerformed

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
            java.util.logging.Logger.getLogger(DashBoards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashBoards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashBoards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashBoards.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DashBoards().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(DashBoards.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane1;
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
    public static javax.swing.JMenu jMenu28;
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
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
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
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem80;
    private javax.swing.JMenuItem jMenuItem81;
    private javax.swing.JMenuItem jMenuItem82;
    private javax.swing.JMenuItem jMenuItem83;
    public static javax.swing.JMenuItem jMenuItem84;
    private javax.swing.JMenuItem jMenuItem85;
    private javax.swing.JMenuItem jMenuItem86;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
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
    private javax.swing.JPopupMenu.Separator jSeparator86;
    private javax.swing.JPopupMenu.Separator jSeparator87;
    private javax.swing.JPopupMenu.Separator jSeparator88;
    private javax.swing.JPopupMenu.Separator jSeparator89;
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
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    public static javax.swing.JTree jTree1;
    private javax.swing.JTree jTree2;
    private javax.swing.JTree jTree3;
    private javax.swing.JTree jTree4;
    private javax.swing.JTree jTree5;
    private javax.swing.JTree jTree6;
    private javax.swing.JTree jTree9;
    private org.jdesktop.swingx.JXTable jXTable1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane1;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane2;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane3;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane4;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane5;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane6;
    private org.jdesktop.swingx.JXTaskPane jXTaskPane7;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void addDragListener(DragListener dl) {
         addMouseListener(dl);
         addMouseMotionListener(dl);
         jPanel8.addMouseListener(dl);
         jPanel8.addMouseMotionListener(dl);
    }
    @Override
    public void itemStateChanged(ItemEvent itemEvent){
        Action closeAction = new DefaultDockableStateAction(dock1011, DockableState.CLOSED);
        Action  restoreAction = new DefaultDockableStateAction(dock1011, DockableState.NORMAL);
        dock1011.removeDockingListener((DockingListener) this);
        if(itemEvent.getStateChange() == ItemEvent.DESELECTED){
            closeAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Close"));
        }
        else{
        restoreAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Restore"));
        }
        dock1011.addDockingListener((DockingListener) this);
    }
    public Dockable dock1011;

    @Override
    public void dockingWillChange(DockingEvent de) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dockingChanged(DockingEvent de) {
         
    }
}
