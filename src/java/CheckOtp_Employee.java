/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.myjobseeker.MyConnection;
import com.myjobseeker.Mailer;
import jakarta.servlet.RequestDispatcher;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
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
@WebServlet(urlPatterns = {"/CheckOtp_Employee"})
public class CheckOtp_Employee extends HttpServlet {

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
       String otp=request.getParameter("otp");
       String email=request.getParameter("email");
       
       Connection cn=MyConnection.getConnection();
       
       //make preparedStatement object
       PreparedStatement ps=cn.prepareStatement("select * from random");
     
       //execute the query  
       ResultSet rs=ps.executeQuery();
       if(rs.next())
       {
       String otp2=rs.getString(1);
       String email2=rs.getString(2);
       if(otp.equals(otp2))
       {
        
           out.println("<h2>We have sent a link to your gmail for password reset<br>Please check your gmail</h2>");
           
       String msg="Please click this link to reset your password"+" http://localhost:8080/JProject_by_Sahil/editPassword_employee.jsp?mail="+email2;    
         try{  
       Mailer.send(email2,"for password reset",msg); 
         }  
       catch(Exception e)
       {
       out.println(e);
       
       }  
         
       }
       else
       {   
       out.println("Invalid OTP");
        RequestDispatcher rd=request.getRequestDispatcher("employee_otp.html");
        rd.include(request, response);
       
       }
       
       }
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
