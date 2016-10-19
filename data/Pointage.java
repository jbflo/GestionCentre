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
public class Pointage {

    private final SimpleStringProperty id;
    private final SimpleStringProperty nom_user;
    private final SimpleStringProperty heure_pointage;
    private final SimpleStringProperty motif;
    private final SimpleStringProperty heure_arrivee;

    public Pointage(String id, String nom_user, String heure_pointage, String motif, String heure_arrivee) {
        this.id = new SimpleStringProperty(id);
        this.nom_user = new SimpleStringProperty(nom_user);
        this.heure_pointage = new SimpleStringProperty(heure_pointage);
        this.motif = new SimpleStringProperty(motif);
        this.heure_arrivee = new SimpleStringProperty(heure_arrivee);
       

    }

    public String getId() {
        return id.get();
    }

    public String getNom_user() {
        return nom_user.get();
    }

    public String getHeure_pointage() {
        return heure_pointage.get();
    }

    public String getMotif() {
        return motif.get();
    }

    public String getHeure_arrivee() {
        return heure_arrivee.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setNom_user(String nom_user) {
        this.nom_user.set(nom_user);
    }

    public void setHeure_pointage(String heure_pointage) {
        this.heure_pointage.set(heure_pointage);
    }

    public void setMotif(String motif) {
        this.motif.set(motif);
    }

    public void setHeure_arrivee(String heure_arrivee) {
         this.heure_arrivee.set(heure_arrivee);
    }

}
