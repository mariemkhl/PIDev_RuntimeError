/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS
 */
public class SendEmail {
    
    String user = "chaima.geurchi@esprit.tn ";
    String email ="exemple@gmail.com";
    String password="223AFT2861";
     String host = "smtp.gmail.com";
    
    
      public SendEmail() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      
     // private String username = "";
 

     public void envoyer(){

// Etape 1 : Création de la session
Properties props = new Properties();
props.put("mail.smtp.auth", "true");
props.put("mail.smtp.starttls.enable","true");
props.put("mail.smtp.host","smtp.gmail.com");
props.put("mail.smtp.port","587");
Session session = Session.getInstance(props,
new javax.mail.Authenticator() {
protected PasswordAuthentication getPasswordAuthentication( ) {
return new PasswordAuthentication(user, password);
}
});
try {
// Etape 2 : Création de l'objet Message
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("chaima.guerchi@esprit.tn"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("guerchishayma603@gmail.com"));
message.setSubject("Test email");
message.setText("saluuuuuuuut ...");
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
throw new RuntimeException(e);
} }


    
}


