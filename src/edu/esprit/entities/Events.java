/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Events {
    private int id_event  ;
    private String name ; 
    private Date date_event ; 
    private String location ;
    private int user_id ; 
    private String categorie ;
    
    

    private enum categorie {
       online,cinematic,literature,theatre,salle_exposition_des_tableaux, salle_exposition_des_sculpture 
    };

    public Events() {
    }

    

    public Events(String name, Date date_event, String location, int user_id, String categorie) {
        this.name = name;
        this.date_event = date_event;
        this.location = location;
        this.user_id = user_id;
        this.categorie = categorie;
    }

    public Events(int id_event, String name, Date date_event, String location, int user_id, String categorie) {
        this.id_event = id_event;
        this.name = name;
        this.date_event = date_event;
        this.location = location;
        this.user_id = user_id;
        this.categorie = categorie;
    }
   

    public int getId_event() {
        return id_event;
    }

//    public void setId_event(int id_event) {
//        this.id_event = id_event;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public int getUser_id() {
        return user_id;
    }

//    public void setUser_id(int user_id) {
//        this.user_id = user_id;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Events{" + "name=" + name + ", date_event=" + date_event + ", location=" + location + ", user_id=" + user_id + ", categorie=" + categorie + '}';
    }

    

//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id_event;
        hash = 13 * hash + this.user_id;
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
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }
    
    
     
     
    
    
}
