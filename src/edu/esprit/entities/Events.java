/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import edu.esprit.gui.GetData;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Events {

    private int id_event;
    private String name;
    private Date date_event;
    private String location;
    private int id_user;
    private String categorie;
    private int nbplacetotal ;
    private String  img ; 
   
    

    private enum categorie {
        online, cinematic, literature, theatre, salle_exposition_des_tableaux, salle_exposition_des_sculpture
    };

    public Events() {
    }

    public Events(String name, Date date_event, String location, int id_user, String categorie, int nbplacetotal , String img) {
        this.name = name;
        this.date_event = date_event;
        this.location = location;
        this.id_user = id_user;
        this.categorie = categorie;
        this.nbplacetotal = nbplacetotal;
        this.img=img; 

    }

    public Events(int id_event, String nameEv, Date date_event, String location, int user_id, String categorie, int nbplacetotal, String img) {
        this.id_event = id_event;
        this.nameEv = nameEv;
        this.date_event = date_event;
        this.location = location;
        this.id_user = id_user;
        this.categorie = categorie;
        this.nbplacetotal = nbplacetotal;
          this.img=img; 
        
    }
    
    public Events( String tfName, int tfuser,Date dpDate,String tfLocation , int nbplace,String myCategory ){
    
    this.name=tfName;
    this.id_user= tfuser;
    this.date_event= dpDate ;
    this.location=tfLocation; 
    this.nbplacetotal=nbplace;
   this.categorie = myCategory;
     this.img=img; 
    
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }
    public String getName() {
        return name;
    }

    public void setName(String name ) {
        this.name = name;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNbplacetotal() {
        return nbplacetotal;
    }

    public void setNbplacetotal(int nbplacetotal) {
        this.nbplacetotal = nbplacetotal;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

   

    
    
    
    @Override
    public String toString() {
        return "Events{" + "name=" + name + ", date_event=" + date_event + ", location=" + location + ", id_user=" + id_user + ", categorie=" + categorie + ", nbplacetotal=" + nbplacetotal + '}';
    }

   

 
    
    
 
    

//    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id_event;
        hash = 13 * hash + this.id_user;
        return hash;
    }

    @Override

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Events other = (Events) obj;
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.date_event, other.date_event)) {
            return false;
        }
        return true;
    }

}
