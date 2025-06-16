/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sahil
 */
@WebServlet(urlPatterns = {"/Employee_UpdatePassword"})
public class Employee_UpdatePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       
       String email=request.getParameter("email");
       String pwd=request.getParameter("pwd");
       
         
        out.println("<head>\n" +
"        \n" +
"         <style>\n" +
"        body {\n" +
"            background-image: url('bg_image/blur.jpg');\n" +
"            background-size: cover;\n" +
"            background-repeat: repeat;\n" +
"            background-position: center;\n" +
"            height: 100vh;\n" +
"            margin: 0;\n" +
"            text-align: center;\n" +
"        }\n" +
"    </style>   </head>");
      
       
       try
       {
      //load the driver
       Class.forName("com.mysql.cj.jdbc.Driver");
       
       //make the connection
       Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jproject", "root", "root");
         
       //make PrepareStatement object
       
       PreparedStatement ps=cn.prepareStatement("update employee set password=? where email=?");
       ps.setString(1,pwd);
       ps.setString(2,email);
       
       //execute the query
       boolean b=ps.execute();
       if(b==false)
       {
       out.println("Password Updated Successfully");
     
       RequestDispatcher rd=request.getRequestDispatcher("employee_login.html");
       rd.include(request, response);
       } 
       else
       {
       out.println("Password not updated");
       out.println("Please try again");
       }
       
       cn.close();
       
       }
       
       catch(Exception e)
       {
        out.println(e);
       }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
