/*author : Zaid Mahdi
*/
 
package test;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name="user")
@SessionScoped
public class User implements Serializable{

	String name;
	String password;

        public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	Connection con=null;
	ResultSet rs=null;
        Statement stmt=null;
	String uName,pwd;
	public String login()
	{
            if(getName()!=null && getPassword()!=null){
            try
            {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/web2","root","test");
		stmt=con.createStatement();
		String query="select * from web2.login";
		rs=stmt.executeQuery(query);
		while(rs.next())
		{    
                    String user = rs.getString(1);
   		   if((name.equals(user)) && (password.equals(rs.getString(2))))
		   {
                       clearData();
                            return "beforeDB";
		   }
                }
            }
	    catch(Exception e) { e.printStackTrace(); }		
            
            }else{
                return "5login";
            }
          return "5login";
      }

    

      public String logout(){
        SecurityContextHolder.clearContext();
        return "index";
    }
      
       private void clearData() {
        this.name = null;
        this.password = null;       
    }
}
