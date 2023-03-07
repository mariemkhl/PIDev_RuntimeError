/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import  services.ServiceEvents;
import java.sql.Date;
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
   private Date dateRE ;
   

    public Reservation() {
    }

    public Reservation(Events event,String nameEv, int id_user, String name,Date dateRE) {
        this.event = event;
        this.id_user = id_user;
        this.name = name;
          this.dateRE = dateRE;
    }
    

    public Reservation(int id_res, Events event, int id_user, String name,Date dateRE) {
        this.id_res = id_res;
        this.event = event;
        this.id_user = id_user;
        this.name = name;
          this.dateRE = dateRE;
    }

   
 public Reservation( String tfName, int tfuserid , Events tfevent , Date datepc){
    
    this.name=tfName;
    this.id_user= tfuserid;
    
     this.event = tfevent;
     this.dateRE=datepc;
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
//     public int getNbplace() {
//        return nbplace;
//    }
//
//    public void setNbplace(int nbplace) {
//        this.nbplace = nbplace;}

    public Date getDateRE() {
        return dateRE;
    }

    public void setDateRE(Date dateRE) {
        this.dateRE = dateRE;
    }

    @Override
    public String toString() {
        return "Reservation{" + "event=" + event + ", id_user=" + id_user + ", name=" + name + ", dateRE=" + dateRE + '}';
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


