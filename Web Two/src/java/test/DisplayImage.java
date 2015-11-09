/*author : Zaid Mahdi 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.sql.*;
import java.io.*;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class DisplayImage extends HttpServlet {
    private static final long serialVersionUID = 4593558495041379082L;
    private static final String connection_url = "jdbc:mysql://localhost:3306/web2";

    @Override
        public void doGet(HttpServletRequest request,
                HttpServletResponse response)
                throws ServletException, IOException {
            Statement stmt = null;
            ResultSet rs;
            InputStream sImage;
        try {
 
            String id = request.getParameter("id");
            System.out.println("inside servlet–>" + id);
 
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties user = new Properties();
            user.put("user", "root");
            user.put("password", "test");
            Connection con = DriverManager.getConnection(connection_url, user);
            
            stmt = con.createStatement();
            String strSql = "select image from imagedb where id='" + id + "' ";
            rs = stmt.executeQuery(strSql);
            if (rs.next()) {
                byte[] bytearray = new byte[1048576];
                int size = 0;
                sImage = rs.getBinaryStream(1);
                response.reset();
                response.setContentType("image/png");
                while ((size = sImage.read(bytearray)) != -1) {
                    response.getOutputStream().
                            write(bytearray, 0, size);
                }
            }
 
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}