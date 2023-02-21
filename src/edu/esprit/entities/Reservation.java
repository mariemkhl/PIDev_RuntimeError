/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import edu.esprit.services.ServiceEvents;
import java.util.Objects;



/**
 *
 * @author ASUS
 */
public class Reservation {

    private int id_res;
    private Events event ;
    private int id_user;
    private String name;
   private int nbplace ;
    ServiceEvents se = new ServiceEvents();
   

    public Reservation() {
    }

    public Reservation(Events event,int id_event, int id_user, String name) {
        this.event = event;
        this.id_user = id_user;
        this.name = name;
    }
    

    public Reservation(int id_res, Events event, int id_user, String name) {
        this.id_res = id_res;
        this.event = event;
        this.id_user = id_user;
        this.name = name;
    }

   

 
    

    

    public int getId_res() {
        return id_res;
    }

    public void setId_res(int id_res) {
        this.id_res = id_res;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    

    

    public int getId_user() {
        return id_user;
    }

    public void setUser_id(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     public int getNbplace() {
        return nbplace;
    }

    public void setNbplace(int nbplace) {
        this.nbplace = nbplace;}

    @Override
    public String toString() {
        return "Reservation{" + "event=" + event + ", id_user=" + id_user + ", name=" + name + ", nbplace=" + nbplace + '}';
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

   
   
   
   }


