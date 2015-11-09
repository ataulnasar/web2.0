package test;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile; 
import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tableBean")
@SessionScoped 
public class TableBean {
    
    private String imageID;
    private String imageName;
    private static final String connection_url = "jdbc:mysql://localhost:3306/web2";
    
    public String getImageName() {
        return imageName;
    }
 
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
 
    public String getImageLength() {
        return imageLength;
    }
 
    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    
    private String imageLength;
 
    public String getImageID() {
        return imageID;
    }
 
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
 
    public List<TableBean> getAllImage() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<TableBean> imageInfo = new ArrayList<TableBean>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties user = new Properties();
            user.put("user", "root");
            user.put("password", "test");
            Connection con = DriverManager.getConnection(connection_url, user);
            stmt = con.createStatement();
            String strSql = "select id , name from imagedb";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBean tbl = new TableBean();
                tbl.setImageID(rs.getString("id"));
                tbl.setImageName(rs.getString("name"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
    
    public String deleteAction(String order) {
        try {
            String connection_url = "jdbc:mysql://localhost:3306/web2";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties user = new Properties();
            user.put("user", "root");
            user.put("password", "test");
            Connection con = DriverManager.getConnection(connection_url, user);
            Statement stmt = con.createStatement();

            String strSql = "delete from  imagedb where name = '"+order+"'";
            PreparedStatement preparedStmt = con.prepareStatement(strSql);
            preparedStmt.execute();            
            //ResultSet rs = stmt.executeQuery(strSql);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        } 
        return null;
    }

    
           
    public List<ImagesDAO> getImages() {
        
        List<ImagesDAO> imageInfo = new ArrayList<>();
        ImagesDAO imagesDao = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties user = new Properties();
            user.put("user", "root");
            user.put("password", "test");
            Connection con = DriverManager.getConnection(connection_url, user);
            stmt = con.createStatement();
            String strSql = "select path  from images";
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                imagesDao = new ImagesDAO();
                imagesDao.setPath(rs.getString("path"));
                imageInfo.add(imagesDao);
            }
        } catch (Exception e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        
        return imageInfo;   
    }

}   