/*author : Zaid Mahdi 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.sql.DataSource;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@Named
@SessionScoped
public class CharacterData implements Serializable{
private static final String connection_url = "jdbc:mysql://localhost:3306/web2";

public List<Form> getCharacters() {
List<Form> result = new ArrayList<>();
    try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Properties user = new Properties();
        user.put("user", "root");
        user.put("password", "test");
        Connection conn = DriverManager.getConnection(connection_url, user);
        try {
            String sql = "select * from web2.form";
            PreparedStatement stat = conn.prepareStatement(sql);
            ResultSet rs = null;
            stat.execute();
            rs = stat.getResultSet();
            while(rs.next()) {
                Form form = new Form();
                form.setName(rs.getString(1));
                form.setEmail(rs.getString(2));
                form.setPhoneNumber(rs.getString(3));
                form.setSubject(rs.getString(4));
                form.setMessage(rs.getString(5));
                result.add(form);
            }
        }
        finally {
        conn.close();
        }
        } catch (SQLException e) {
        e.printStackTrace();
        } catch (InstantiationException e) {
        e.printStackTrace();
        } catch (IllegalAccessException e) {
        e.printStackTrace();
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        }
    return result;
}
//-----------------------------------------
  public String insert(Form form) {//examSolveData1 comp
      if(form.getName().length()!=0 && form.getEmail().length()!=0 && form.getPhoneNumber().length()!=0 && 
         form.getSubject().length()!=0 && form.getMessage().length()!=0)
      {
      try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Properties user = new Properties();
        user.put("user", "root");
        user.put("password","test");
        Connection conn = DriverManager.getConnection(connection_url, user);
        
        PreparedStatement pstmt = conn.prepareStatement(""
        + "insert into web2.form(name, email, phoneNumber, subject, message) values(?, ?, ?, ?, ?)");    
        { 
            pstmt.setString(1,form.getName()); 
            pstmt.setString(2,form.getEmail()); 
            pstmt.setString(3,form.getPhoneNumber()); 
            pstmt.setString(4,form.getSubject()); 
            pstmt.setString(5,form.getMessage()); 
            
            pstmt.executeUpdate(); 
        } 
            conn.close();
                      System.out.println("index"+form.toString());

         form.clearData();
        return "6contact";
        }  catch (SQLException | InstantiationException 
        | IllegalAccessException | ClassNotFoundException e) {}
      }else{
          return "6contact";
      }
                System.out.println("6Contact");
        return "6contact";
    }
}
