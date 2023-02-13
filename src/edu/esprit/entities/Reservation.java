/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

/**
 *
 * @author ASUS
 */
public class Reservation {
     private int id_res ; 
     private int id_event ; 
     private int user_id ; 
     private String name; 

    public Reservation() {
    }
    
     public Reservation( int id_event, int user_id, String name) {
        
        this.id_event = id_event;
        this.user_id = user_id;
        this.name = name;
    }
    
    public Reservation(int id_res, int id_event, int user_id, String name) {
        this.id_res = id_res;
        this.id_event = id_event;
        this.user_id = user_id;
        this.name = name;
    }

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_event=" + id_event + ", user_id=" + user_id + ", name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Reservation other = (Reservation) obj;
        if (this.id_res != other.id_res) {
            return false;
        }
        return true;
    }

    
    }
     
     

