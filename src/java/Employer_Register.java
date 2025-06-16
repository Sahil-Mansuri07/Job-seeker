/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.myjobseeker.MyConnection;
import com.myjobseeker.Mailer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sahil
 */
@WebServlet(urlPatterns = {"/Employer_Register"})
public class Employer_Register extends HttpServlet {

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
      
        
        
        try{


       System.out.println("Inner try block");
       
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String mob=request.getParameter("mob");
        String comp=request.getParameter("comp");
        String pwd=request.getParameter("pwd");
       
        
        String to=request.getParameter("email");
        String msg="This email is send by jobseeker for confirmation of" +email+ "Please click this link to verify you"+" http://localhost:8084/JProject_by_Sahil/employer_verify.jsp?mail="+email;
        
        //make connection
         Connection cn=MyConnection.getConnection();

        
        // make preparedStatement object
        
        PreparedStatement ps=cn.prepareStatement("insert into employer(name,email,mobile,cp_name,password,status,verify) values(?,?,?,?,?,?,?)");
        
       ps.setString(1,name);
       ps.setString(2, email);
       ps.setString(3, mob);
       ps.setString(4, comp);
       ps.setString(5, pwd);
       ps.setBoolean(6, false);
       ps.setBoolean(7, false);
       
       // execute the query
       boolean b=ps.execute();
       
       if(b==false)
       {
         out.println("<h2>Registration Successfull</h2>");
         
       out.println("<h2> We have sent a link to your gmail for confirmation</h2>");
    
     out.println("<h2>please check your gmail for confirmation link</h2>");
         
     try{
    Mailer.send(to,"For employer confirmation", msg);
     }
     
     catch(Exception e)
     {
          out.println(e);
         
         out.println("Please check your internet connection or may be your email id is invalid");
     
     }
      
        }
        // closse the connection
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
