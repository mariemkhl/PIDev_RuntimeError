/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

/**
 *
 * @author user
 */


  public class CommandeListCell extends ListCell<Commande> {

    Commande commande = new Commande();
@FXML private Label idcommandeLabel = new Label();
@FXML    private Label prix_totLabel = new Label();
@FXML    private Label paymentLabel = new Label();
@FXML    private Label date_creationLabel = new Label();
@FXML    private VBox vBox = new VBox(idcommandeLabel, prix_totLabel, paymentLabel, date_creationLabel);

    public CommandeListCell() {
        super();
        vBox.getStyleClass().add("cart1");
        idcommandeLabel.getStyleClass().add("idcommande");
        prix_totLabel.getStyleClass().add("prix_tot");
        paymentLabel.getStyleClass().add("payment");
        date_creationLabel.getStyleClass().add("date_creation");
        setGraphic(vBox);
    }
    

    @Override
    protected void updateItem(Commande commande, boolean empty) {
        super.updateItem(commande, empty);

        if (empty || commande == null) {
            setText(null);
            setGraphic(null);
        } else {

            idcommandeLabel.setText(Integer.toString(commande.getId()));;
            idcommandeLabel.setPrefWidth(50); 
            idcommandeLabel.setPrefHeight(50);
         prix_totLabel.setText("$ "+ commande.getPrix_tot());
            paymentLabel.setText(commande.getPayment());
            date_creationLabel.setText(commande.getDate_creation());
 
            setText(null);
            setGraphic(vBox);
        }
    }

 }
