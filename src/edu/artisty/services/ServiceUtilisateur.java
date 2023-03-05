/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import edu.artisty.entities.Artist;
import edu.artisty.entities.Customer;
import edu.artisty.entities.Utilisateur;
import edu.artisty.utils.Datasource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author khoul
 */
public class ServiceUtilisateur{  

   Connection cnx = Datasource.getInstance().getCnx();

 

   public void ajouter(Utilisateur u) {
       String role;
       if (u instanceof Customer){
        role="Customer";
           try {
        String req = "INSERT INTO `utilisateur`(`Username`, `Password`, `Email`, `Address`, `domaine`, `Num_tel`, `role`) VALUES  (?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getEmail());
        ps.setString(4,((Customer) u).getAdresse());
        ps.setString(5,null);
        ps.setString(6, u.getNum_tel());
        ps.setString(7,role);
        ps.executeUpdate();
        System.out.println("Customer added!");
       }
                  
            catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
      
   }
       
       else if (u instanceof Artist){
       role = "Artist";
        try {
        String req = "INSERT INTO `utilisateur`(`Username`, `Password`, `Email`, `Address`, `domaine`, `Num_tel`, `role`) VALUES  (?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getEmail());
        ps.setString(4,null);
        ps.setString(5,((Artist) u).getDomaine());
        ps.setString(6, u.getNum_tel());
        ps.setString(7,role);
        ps.executeUpdate();
        System.out.println("Artist added!");
        System.out.println(((Artist) u).getDomaine());
       }
            catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
   }
   else{
       role="utilisateur";
      try {
        String req = "INSERT INTO `utilisateur`(`Username`, `Password`, `Email`, `Address`, `domaine`, `Num_tel`, `role`) VALUES  (?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getPassword());
        ps.setString(3, u.getEmail());
         ps.setString(4,null);
          ps.setString(5,null);
        ps.setString(6, u.getNum_tel());
        ps.setString(7,role);
        ps.executeUpdate();
        System.out.println("user added!");
       }
            catch (SQLException ex) {
        System.out.println(ex.getMessage());
    } 
   }
       
}


  
      public void supprimer(Utilisateur a) {
          String ch=a.getEmail();
        try {
            String req = "DELETE FROM `utilisateur` WHERE email ='"+ch+"'";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println(" deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
      public void modifier(Utilisateur u,String email) {
    String role = "";
    String req = "";
    PreparedStatement ps = null;
    
    // Determine the user's role and construct the appropriate SQL query
    if (u instanceof Customer) {
        role = "Customer";
        req = "UPDATE `utilisateur` SET `Username`=?, `Email`=?,`Address`=?,`Num_tel`=?,`role`=? WHERE `email`='"+email+"'";
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, ((Customer) u).getAdresse());
            ps.setString(4, u.getNum_tel());
            ps.setString(5, role);
            ps.executeUpdate();
            System.out.println("Customer updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else if (u instanceof Artist) {
        role = "Artist";
        req = "UPDATE `utilisateur` SET `Username`=?,`Email`=?,`domaine`=?,`Num_tel`=?,`role`=? WHERE `email`='"+email+"'";
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, ((Artist) u).getDomaine());
            ps.setString(4, u.getNum_tel());
            ps.setString(5, role);
            ps.executeUpdate();
            System.out.println("Artist updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        role = "utilisateur";
        req = "UPDATE `utilisateur` SET `Username`=?,`Email`=?,`Num_tel`=?,`role`=? WHERE `email`='"+email+"'";
        try {
            ps = cnx.prepareStatement(req);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getNum_tel());
            ps.setString(4, role);
            ps.executeUpdate();
            System.out.println("User updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
public List<Utilisateur> getAll() {
    List<Utilisateur> utilisateurs = new ArrayList();

    try {
        String req = "SELECT * FROM utilisateur";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String role = rs.getString("role");

            if (role.equals("Customer")) {
                Customer customer = new Customer(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("Num_tel")
                );
                utilisateurs.add(customer);
            } else if (role.equals("Artist")) {
                Artist artist = new Artist(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Num_tel"),
                        rs.getString("domaine")
                );
                utilisateurs.add(artist);
            } else {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                       //null,
                       //null,
                   
                        rs.getString("Num_tel")
                );
                utilisateurs.add(utilisateur);
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return utilisateurs;
}
public Utilisateur getAllById(String email) {
    Utilisateur utilisateurs = new Utilisateur();

    try {
        String req = "SELECT * FROM utilisateur WHERE email = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,email);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String role = rs.getString("role");

            if (role.equals("Customer")) {
                Customer customer = new Customer(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("Num_tel")
                );
                return customer;
            } else if (role.equals("Artist")) {
                Artist artist = new Artist(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getString("Num_tel"),
                        rs.getString("domaine")
                );
                return artist;
            } else {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        //null,
                        rs.getString("Num_tel")
                );
                return utilisateur;
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return utilisateurs;
}
   
      public boolean  RechercherUtilisateur(int Id_user) {
        String req = null;
        boolean x = false;

       try {
     req = "SELECT * FROM utilisateur WHERE Id_user = ?";
    PreparedStatement ps = this.cnx.prepareStatement(req);
    ps.setInt(1, Id_user); // set the value of the first parameter to Id_user
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        System.out.println("User Exist");
        x = true;
    } else {
        System.out.println("does not exist");
        x = false;
    }
} catch (SQLException var6) {
    System.err.println(var6.getMessage());
}
  return x;
  
   
}
public boolean  login(String email,String password) {
        String req = null;
        boolean x = false;

       try {
     req = "SELECT * FROM utilisateur WHERE Email ='"+email+"' and password='"+password+"'";
    Statement ps = this.cnx.createStatement();
    ResultSet rs = ps.executeQuery(req);
    if (rs.next()) {
       x= true;
    } else {
        System.out.println("does not exist");
        x = false;
    }
} catch (SQLException var6) {
    
    System.err.println(var6.getMessage());
}
       return x;
     
}
  private String hashPassword(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hash = md.digest(password.getBytes());
    StringBuilder sb = new StringBuilder();
    for (byte b : hash) {
        sb.append(String.format("%02x", b));
    }
    return sb.toString();
}
 public void modifierpwd(Utilisateur u) throws NoSuchAlgorithmException {
    try {
        String req = "UPDATE user SET pwd = ? WHERE email = ?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, hashPassword(u.getPassword())); 
        st.setString(2, u.getEmail());
        st.executeUpdate();
        System.out.println("client updated !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
 public String  RechercherUtilisateur(String nameuser) {
        String req = null;
        

       try {
     req = "SELECT * FROM utilisateur WHERE Ussername = ?";
    PreparedStatement ps = this.cnx.prepareStatement(req);
    ps.setString(1, nameuser); // set the value of the first parameter to Id_user
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        return rs.getNString("Email");
    } else {
       return "error";
    }
} catch (SQLException var6) {
    System.err.println(var6.getMessage());
}
  return "error";
  
   
}
/* public boolean verifierEmailBd(String email) {
PreparedStatement ps = null;
ResultSet rs = null;
try {
   String sql = "SELECT * FROM utilisateur WHERE email = ?";
   ps = cnx.prepareStatement(sql);
   ps.setString(1, email);
   rs = ps.executeQuery();
   if (rs.next()) {
return true;
   }
} catch (SQLException ex) {
   System.out.println(ex.getMessage());
}
return false;

    }
     
    public boolean verifierpassword(String Password) {
PreparedStatement ps = null;
ResultSet rs = null;
try {
   String sql = "SELECT * FROM utilisateur WHERE Password = ?"; 
   ps = cnx.prepareStatement(sql);
   ps.setString(1, Password);
   rs = ps.executeQuery();
   if (rs.next()) {
return true;
   }
} catch (SQLException ex) {
   System.out.println(ex.getMessage());
}
return false;

    }

    
   
     
     
     public void Authentification(String email , String Password)  {
    String s= "";
    PreparedStatement ps = null;
ResultSet rs = null;
    if (verifierEmailBd(email)==false) {
        System.out.println("Email n'est pas reconnu. Veuillez vous inscrire!!!");
    }
    else {
       if (verifierpassword(Password)==false) {
        System.out.println("Votre Mdp est erroné . Un Email de verification vous sera envoyé pour changer de mot de passe!");
        try {
   String sql = "SELECT * FROM utilisateur WHERE email = ?";
   ps = cnx.prepareStatement(sql);
   ps.setString(1, email);
   rs = ps.executeQuery();
            if (rs.next()) {
 s=rs.getString("username");
Envoyeremail e = new Envoyeremail();
            try {
                e.envoyer(email,rs.getString("Password"),s);
            } catch (MessagingException ex) {
              
            }}
   
} catch (SQLException ex) {
   System.out.println(ex.getMessage());
}}
        else {
           try {
               String sq1 ="SELECT * FROM utilisateur WHERE email = ?";
               ps = cnx.prepareStatement(sq1);
               ps.setString(1, email);
               rs = ps.executeQuery();
               if (rs.next()) {
               if ("Artist".equals(rs.getString("role"))){
                   System.out.println("Bonjour Mr "+rs.getString("username")+"Vous etes  Artist de domaine "+ rs.getString("domaine") );
               }
               if ("Costumer".equals(rs.getString("role"))){
                   System.out.println("Bonjour Mr "+rs.getString("username")+" voici votre adresse "+ rs.getString("adresse") );
               }
               if ("Artist".equals(rs.getString("role"))){
                   System.out.println("Bonjour Mr "+rs.getString("username")+" vous etres simple utilisateur" );
               }}
           } catch (SQLException ex) {
             
           }
     
       }}
   
     }*/
 public static List<Utilisateur> trier(List<Utilisateur> listc) {
        return listc.stream()
                .sorted(Comparator.comparing(Utilisateur::getUsername))
                .collect(Collectors.toList());
    }
    
    
     
     public static List<Utilisateur> rechercher(List<Utilisateur> listc,String getUsername)
   {
       return (List<Utilisateur>) listc.stream()
        .filter(a -> a.getUsername().equalsIgnoreCase(getUsername)).collect(Collectors.toList());
       
       
   }
        public boolean emailExists(String email) {
        try {
            String req = "SELECT * FROM utilisateur WHERE email=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
    

  
    
    

   

   

    

   

  
   

 

    
    
    

