/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import com.alee.laf.table.WebTableUI;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultCellEditor;
import javax.swing.text.NumberFormatter;
import javax.swing.text.DefaultFormatterFactory;
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
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    private Locale locale;
    public NewJFrame() throws SQLException {
        initComponents();
        SimpleDateFormat format = new SimpleDateFormat("MMM d, yyyy");
        Date date = new Date();
        String now = format.format(date);
        String sql = "select REFERENCENUMBER,DATEOFINCIDENT,DateOfReportingIncident from Incident where DateOfReportingIncident = '"+now+"' ";
       try(Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
           PreparedStatement pst = con.prepareStatement(sql);) {
           try(ResultSet rs = pst.executeQuery();){
           if(rs.next()){
               Date DateOfIncident = rs.getDate("DateOfReportingIncident");
           jXTable1.setUI(new WebTableUI());
           jXTable1.setModel(DbUtils.resultSetToTableModel(rs));
           
        String dateOf  = format.format(DateOfIncident);
        org.jdesktop.swingx.decorator.Highlighter simpleStriping = HighlighterFactory.createSimpleStriping();
        PatternPredicate patternPredicate = new PatternPredicate(now = dateOf,2,2);
        ColorHighlighter magenta = new ColorHighlighter(patternPredicate, Color.red, Color.black, Color.LIGHT_GRAY, Color.black);
        ShadingColorHighlighter shading = new ShadingColorHighlighter(new HighlightPredicate.ColumnHighlightPredicate(2));
        
        jXTable1.setHighlighters(simpleStriping,magenta,shading);
           }
       }
       catch (Exception ex) {
           JOptionPane.showMessageDialog(NewJFrame.this, ex);
            }
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

        jComboBox1 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        txtTest = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtCorrect = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        String emailPattern = "^[0-9]";
        Pattern pattern;
        Matcher matcher;
        this.locale = Locale.US;
        NumberFormat format;
        format = NumberFormat.getCurrencyInstance(locale);
        txtTest.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                //format.format(Integer.parseInt(txtTest.getText();
                    System.out.println(format.format(Integer.parseInt(txtTest.getText())));
                    jFormattedTextField1.setValue(Double.parseDouble((txtTest.getText())));
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    //format.format(Integer.parseInt(txtTest.getText();
                        //System.out.println(format.format(Integer.parseInt(txtTest.getText())));
                        //jFormattedTextField1.setValue(Double.parseDouble(txtTest.getText()));

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        //format.format(Integer.parseInt(txtTest.getText();
                            System.out.println(format.format(Integer.parseInt(txtTest.getText())));
                            jFormattedTextField1.setValue(Double.parseDouble(txtTest.getText()));
                        }
                    });
                    txtTest.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusGained(java.awt.event.FocusEvent evt) {
                            txtTestFocusGained(evt);
                        }
                        public void focusLost(java.awt.event.FocusEvent evt) {
                            txtTestFocusLost(evt);
                        }
                    });
                    txtTest.addInputMethodListener(new java.awt.event.InputMethodListener() {
                        public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                            txtTestInputMethodTextChanged(evt);
                        }
                        public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                        }
                    });
                    txtTest.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            txtTestActionPerformed(evt);
                        }
                    });
                    txtTest.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                        public void propertyChange(java.beans.PropertyChangeEvent evt) {
                            txtTestPropertyChange(evt);
                        }
                    });
                    txtTest.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            txtTestKeyTyped(evt);
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            txtTestKeyReleased(evt);
                        }
                    });

                    jButton1.setText("jButton1");
                    jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            jButton1ActionPerformed(evt);
                        }
                    });

                    //private static final String emailPattern = "^[_A-Za-z0-9-\\+](\\.[_A-Za-z0-9-]+)*@"
                    //"[_A-Za-z0-9-\\]+(\\.[_A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    //private Pattern pattern;
                    //private Matcher matcher;
                    /**public emailValidator(){
                        pattern = Pattern.compile(emailPattern);
                        public boolen validate(final String hex){
                            matcher = pattern.matcher(hex);
                            return matcher matches();
                        }
                    }
                    txtCorrect.getDocument().addDocumentListener(new DocumentListener() {

                        @Override
                        public void insertUpdate(DocumentEvent e) {
                            //format.format(Integer.parseInt(txtTest.getText();
                                emailValidator(txtCorrect.getText());
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                //format.format(Integer.parseInt(txtTest.getText();
                                    SemailValidator(txtCorrect.getText());
                                }

                                @Override
                                public void changedUpdate(DocumentEvent e) {
                                    //format.format(Integer.parseInt(txtTest.getText();
                                        emailValidator(txtCorrect.getText());
                                    }
                                });**/
                                txtCorrect.setForeground(new java.awt.Color(204, 204, 204));
                                txtCorrect.setText("enter text to search here");
                                txtCorrect.addFocusListener(new java.awt.event.FocusAdapter() {
                                    public void focusGained(java.awt.event.FocusEvent evt) {
                                        txtCorrectFocusGained(evt);
                                    }
                                });

                                //jXTable1.getColumn("Title 2").setCellEditor(new DefaultCellEditor(jComboBox1));
                                jScrollPane1.setViewportView(jXTable1);

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
                                ) {
                                    Class[] types = new Class [] {
                                        java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
                                    };

                                    public Class getColumnClass(int columnIndex) {
                                        return types [columnIndex];
                                    }
                                });
                                jTable1.getColumn("Title 2").setCellEditor(new DefaultCellEditor(jComboBox1));
                                jTable1.getColumn("Title 3").setCellEditor(new DefaultCellEditor(jCheckBox1));
                                //jTable1.getColumn("Title 4").setCellEditor(new DefaultCellEditor(jButton1));
                                jScrollPane2.setViewportView(jTable1);

                                jButton3.setText("jButton3");
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
                                        .addGap(15, 15, 15)
                                        .addComponent(jFormattedTextField1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)
                                        .addGap(144, 144, 144))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1)
                                        .addContainerGap())
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton3)
                                        .addGap(42, 42, 42)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTest, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                            .addComponent(txtCorrect))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                                        .addContainerGap())
                                );
                                layout.setVerticalGroup(
                                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(txtTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jButton3)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton1)
                                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCorrect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                                );

                                pack();
                                setLocationRelativeTo(null);
                            }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Double sum = (Double) jFormattedTextField1.getValue();
        JOptionPane.showMessageDialog(NewJFrame.this, sum);
        
        
    
        //JOptionPane.showMessageDialog(NewJFrame.this, format.format(dollar));
    }//GEN-LAST:event_jButton1ActionPerformed
    public void currencyUpdate(DocumentEvent e) throws BadLocationException{
        int dollar ;
        this.locale = Locale.US;
        NumberFormat format;
        format = NumberFormat.getCurrencyInstance(locale);
        
        Document doc = e.getDocument();
        dollar = Integer.parseInt(doc.getText(0, doc.getLength())); 
        format.format(dollar);
    }
    private void txtTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTestActionPerformed
        
        
    }//GEN-LAST:event_txtTestActionPerformed

    private void txtTestKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTestKeyTyped
        
    }//GEN-LAST:event_txtTestKeyTyped

    private void txtCorrectFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCorrectFocusGained
        txtCorrect.setText("");
    }//GEN-LAST:event_txtCorrectFocusGained

    private void txtTestFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTestFocusLost
        
    }//GEN-LAST:event_txtTestFocusLost

    private void txtTestFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTestFocusGained

    }//GEN-LAST:event_txtTestFocusGained

    private void txtTestKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTestKeyReleased
        
    }//GEN-LAST:event_txtTestKeyReleased

    private void txtTestInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTestInputMethodTextChanged

    }//GEN-LAST:event_txtTestInputMethodTextChanged

    private void txtTestPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTestPropertyChange

    }//GEN-LAST:event_txtTestPropertyChange

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
       Locale RSA = new Locale("en", "ZA");
       NumberFormat format = NumberFormat.getCurrencyInstance(RSA);
       format.setMaximumFractionDigits(2);
       
       NumberFormatter formatter = new NumberFormatter(format);
       formatter.setMinimum(0.00);
       formatter.setMaximum(1000000000.00);
       formatter.setAllowsInvalid(false);
       formatter.setOverwriteMode(true);
       
       DefaultFormatterFactory dff = new DefaultFormatterFactory(formatter);
       jFormattedTextField1.setFormatterFactory(dff);
       jFormattedTextField1.setValue(0.00);
       
    }//GEN-LAST:event_formWindowActivated

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            NewJFrame1 frame = NewJFrame1.getObj();
            frame.jLabel1.setText("New Test");
            frame.revalidate();
            frame.repaint();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new NewJFrame().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private org.jdesktop.swingx.JXTable jXTable1;
    private javax.swing.JTextField txtCorrect;
    private javax.swing.JTextField txtTest;
    // End of variables declaration//GEN-END:variables
}
