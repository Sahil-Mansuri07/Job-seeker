/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.myjobseeker.MyConnection;
import com.myjobseeker.Mailer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

/**
 *
 * @author sahil
 */
@WebServlet(urlPatterns = {"/Employee_Forget"})
public class Employee_Forget extends HttpServlet {

    
    
    public static int generateOtp(int length)
{
if(length<=0 || length>9)
{

throw new IllegalArgumentException("OTP length must be between 1-9");

}

int min=(int)Math.pow(10,length-1);
int max=(int)Math.pow(10,length)-1;

Random rd=new Random();
return rd.nextInt(max-min+1)+min;

}
    
    
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
        String to=request.getParameter("email");
        
        int m=generateOtp(6);
        
        String op=String.valueOf(m);
        
        String msg=" your otp is\n"+op;
       
        // make the connection
        Connection cn2=MyConnection.getConnection();
        
        // make preparedStatement object
        PreparedStatement ps2=cn2.prepareStatement("select * from employee where email=?");
        ps2.setString(1,to);
        //execute the query
        ResultSet rs=ps2.executeQuery();
        if(rs.next())
        {
        
            try{
            
        Mailer.send(to,"For password reset",msg);
       
        
        Class.forName("com.mysql.cj.jdbc.Driver");
       
       //make the connection
       Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jproject", "root", "root");
         
       //make PrepareStatement object
       
       PreparedStatement ps=cn.prepareStatement("INSERT INTO random(otp,email) VALUES (?,?)");
       ps.setString(1,op);
       ps.setString(2,to);
       
       //execute the query
       int b=ps.executeUpdate();

       if(b>0) 
       {
       out.println("<h2>We have sent OTP to your gmail</h2>");
       out.println("<input type='email' name='email' value='"+to+"' readonly>");
       RequestDispatcher rd=request.getRequestDispatcher("employee_otp.html");
       rd.include(request, response);
       }
            }
       catch(Exception e)
       {
       out.println(e);
       
       }
       }
        else
        {
        
        out.println("<h2>Invalid email address,please enter a valid email address</h2>");
        
        RequestDispatcher rd=request.getRequestDispatcher("employee_forget.html");
       rd.include(request, response);
        
        }
        
        
        
        
        
        
       cn2.close();
        
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
