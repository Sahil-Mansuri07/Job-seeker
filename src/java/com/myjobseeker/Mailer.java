package com.myjobseeker;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class Mailer {
static String from="sahilmansuri0021@gmail.com";
static String password="vvoy fnnf royu jyzb";
public static void send(String to,String sub,String msg)
{
//setup email server
    
    Properties props=new Properties();
     props.put("mail.smtp.ssl.protocols","TLSv1.2");
     props.put("mail.smtp.host","smtp.gmail.com");
     props.put("mail.smtp.port","587");
     props.put("mail.smtp.auth","true");
     props.put("mail.smtp.starttls.enable","true");
     
     // create session object
     Session ss=Session.getInstance(props,new Authenticator(){
     protected PasswordAuthentication getPasswordAuthentication()
      {
       return new PasswordAuthentication(from,password);
     }
     
     });
    // compose message
    try{
    
    MimeMessage mm=new MimeMessage(ss);
     mm.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
     mm.setSubject(sub);
     mm.setText(msg);
    
    //send message
    
    Transport.send(mm);
        System.out.println("Message sent successfully");
   
    }
     
    catch (MessagingException e) {
            throw new RuntimeException(e);
        }
}


    
}
