package com.myjobseeker;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
   public static Connection getConnection()
   {
       Connection cn=null;
       
       try{
      //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
           
            //make the connection object
             cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/jproject", "root", "root");
       
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
       
       return cn;
   }
}
