/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author khoul
 */

   public class Utilisateur {
    private String username;
    private String password;
    private String email;
    private String num_tel;

    public Utilisateur( String username, String password, String email, String num_tel) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.num_tel = num_tel;
    }
    

    public Utilisateur() {
    }

    public Utilisateur(String username, String email, String num_tel) {
        this.username = username;
        this.email = email;
        this.num_tel = num_tel;
    }

   




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    @Override
   // public String toString() {
       // return "Utilisateur{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", num_tel=" + num_tel + '}';
   // }

   
    public String toString() {
        return "Utilisateur{" + "username=" + username + ", password=" + password + ", email=" + email + ", num_tel=" + num_tel + '}';
    }
    
}