/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MathomeTD
 */
public class DbConnection {

    public static Connection dbConnection() throws ClassNotFoundException {
        Connection conn = null;
        try{
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        conn = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
        //JOptionPane.showMessageDialog(null, "Connection done");
        return conn;
        }catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
        
    }
}
