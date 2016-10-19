/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author GINEL ; JBFLO
 */
public class Versement {

    private final SimpleStringProperty id;
    private final SimpleStringProperty nom_user;
    private final SimpleStringProperty montant_total;
    private final SimpleStringProperty montant_restant;
    private final SimpleStringProperty quart;
    private final SimpleStringProperty ilot;
    private final SimpleStringProperty heure_versement;

    public Versement(String id, String nom_user, String montant_total, String montant_restant, String quart, String ilot, String heure_versement) {
        this.id = new SimpleStringProperty(id);
        this.nom_user = new SimpleStringProperty(nom_user);
        this.montant_total = new SimpleStringProperty(montant_total);
        this.montant_restant = new SimpleStringProperty(montant_restant);
        this.quart = new SimpleStringProperty(quart);
        this.ilot = new SimpleStringProperty(ilot);
        this.heure_versement = new SimpleStringProperty(heure_versement);
    }

    public String getId() {
        return id.get();
    }

    public String getNom_user() {
        return nom_user.get();
    }

    public String getMontant_total() {
        return montant_total.get();
    }

    public String getMontant_restant() {
        return montant_restant.get();
    }

    public String getQuart() {
        return quart.get();
    }

    public String getIlot() {
        return ilot.get();
    }

    public String getHeure_versement() {
        return heure_versement.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setNom_user(String nom_user) {
        this.nom_user.set(nom_user);
    }

    public void setMontant_total(String montant_total) {
        this.montant_total.set(montant_total);
    }

    public void setMontant_restant(String montant_restant) {
        this.montant_restant.set(montant_restant);
    }

    public void setQuart(String quart) {
        this.quart.set(quart);
    }

    public void setIlot(String ilot) {
        this.ilot.set(ilot);
    }

    public void setHeure_versement(String telephone) {
        this.heure_versement.set(telephone);
    }

}
