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
     private int id_user ; 
     private String name; 

    public Reservation() {
    }

    public Reservation(int id_res, int id_event, int id_user, String name) {
        this.id_res = id_res;
        this.id_event = id_event;
        this.id_user = id_user;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Reservation{" + "name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (this.id_event != other.id_event) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        return true;
    }
     
     
}
