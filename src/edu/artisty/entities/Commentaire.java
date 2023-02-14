/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;

import java.sql.Date;

/**
 *
 * @author MariemKhlifi
 */
public class Commentaire {

    private int idCommentaire;
    private Article article;
    private String contentCommentaire;
    private Date dateCommentaire;
    private int nbLikesCommentaire;
    private boolean etatCommentaire;
    // TODO
    private int idUser;

    public Commentaire() {
    }

    public Commentaire(int idCommentaire, Article article, String contentCommentaire, Date dateCommentaire, int nbLikesCommentaire, boolean etatCommentaire, int idUser) {
        this.idCommentaire = idCommentaire;
        this.article = article;
        this.contentCommentaire = contentCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.nbLikesCommentaire = nbLikesCommentaire;
        this.etatCommentaire = etatCommentaire;
        this.idUser = idUser;
    }

    public Commentaire(Article article, String contentCommentaire, Date dateCommentaire, int nbLikesCommentaire, boolean etatCommentaire, int idUser) {
        this.article = article;
        this.contentCommentaire = contentCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.nbLikesCommentaire = nbLikesCommentaire;
        this.etatCommentaire = etatCommentaire;
        this.idUser = idUser;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getContentCommentaire() {
        return contentCommentaire;
    }

    public void setContentCommentaire(String contentCommentaire) {
        this.contentCommentaire = contentCommentaire;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public int getNbLikesCommentaire() {
        return nbLikesCommentaire;
    }

    public void setNbLikesCommentaire(int nbLikesCommentaire) {
        this.nbLikesCommentaire = nbLikesCommentaire;
    }

    public boolean isEtatCommentaire() {
        return etatCommentaire;
    }

    public void setEtatCommentaire(boolean etatCommentaire) {
        this.etatCommentaire = etatCommentaire;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", article=" + article + ", contentCommentaire=" + contentCommentaire + ", dateCommentaire=" + dateCommentaire + ", nbLikesCommentaire=" + nbLikesCommentaire + ", etatCommentaire=" + etatCommentaire + ", idUser=" + idUser + '}';
    }

}
