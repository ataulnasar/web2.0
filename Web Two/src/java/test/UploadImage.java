/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
/**
 * @author Zaid
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
 
@ManagedBean(name = "uploadImage")
@SessionScoped
public class UploadImage implements Serializable {

    private static final String connection_url = "jdbc:mysql://localhost:3306/web2";
    private static final long serialVersionUID = 1L;
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
 
    public void upload () {
        if (file != null) {
            try {
            System.out.println(file.getFileName());
            InputStream fin2 = file.getInputstream();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties user = new Properties();
            user.put("user", "root");
            user.put("password", "test");
            Connection con = DriverManager.getConnection(connection_url, user);

            PreparedStatement pre = con.prepareStatement("insert into imagedb (name,image) values(?,?)");
            pre.setString(1, file.getFileName());
            pre.setBinaryStream(2, fin2, file.getSize());
            pre.executeUpdate();
            System.out.println("Inserting Successfully!");
            pre.close();
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        }
        else{
            FacesMessage msg = new FacesMessage("Please select image!!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}