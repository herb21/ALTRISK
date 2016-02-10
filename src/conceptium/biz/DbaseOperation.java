/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conceptium.biz;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MathomeTD
 */
public class DbaseOperation {
    public void insertToDb(String table, String columns,String args, String[] inputQuery){
    String sql = "insert into "+table+"("+columns+") values("+args+")"; 
    try{
        Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
        PreparedStatement pst = con.prepareStatement(sql);
        String [] k = args.split(",");
        int x = 0;
        while(x < k.length){
        pst.setString(x+1, inputQuery[x]);
        x += 1;
        }
        pst.executeUpdate();
    }catch(SQLException | HeadlessException e){
        e.printStackTrace();
    }
}
    
    public void updateData(String table, String columns,String args, String[] inputQuery){
        String sql = "Update into "+table+"("+columns+") values("+args+")"; 
        try{
        Connection con = DriverManager.getConnection("jdbc:derby:Incident","herbert","elsie1*#");
        PreparedStatement pst = con.prepareStatement(sql);
        String [] k = args.split(",");
        int x = 0;
        while(x < k.length){
        pst.setString(x+1, inputQuery[x]);
        x += 1;
        }
        pst.executeUpdate();
            }catch(SQLException | HeadlessException e){
        e.printStackTrace();
    }
    }
    


    public void insertInDB(int bookingID,String name, String surname, String employeeID, String jobTitle, String email ,
            Date startDate, String courseBooked, int days, Date endDate, Date dateOfBooking, String serviceProvider,
            String venue, String costCenter) throws Exception{
        String sql = "Insert into Bookings (BOOKINGID, NAME, SURNAME, EMPLOYEEID, JOBTITLE, EMAIL, STARTDATE " +
        " COURSEBOOKED, DAYS, ENDATE, DATEOFBOOKING, SERVICEPROVIDER,VENUE, COSTCENTER) values (?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?)";
        try{
            Connection con = DriverManager.getConnection(sql);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookingID);
            pst.setString(2, name);
            pst.setString(3, surname);
            pst.setString(4, employeeID);
            pst.setString(5, jobTitle);
            pst.setString(6, email);
            pst.setDate(7, startDate);
            pst.setString(8, courseBooked);
            pst.setInt(9, days);
            pst.setDate(10, endDate);
            pst.setDate(11, dateOfBooking);
            pst.setString(12, serviceProvider);
            pst.setString(13, venue);
            pst.setString(14, costCenter);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(Book.getObj(), "Record successfully save");
        }
        catch(SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(Book.getObj(), e);
        }
    }
}
