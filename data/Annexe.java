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
public class Annexe {

    private final SimpleStringProperty identifiant;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty format;
   
    public Annexe(String identifiant, String nom, String format) {
        this.identifiant = new SimpleStringProperty(identifiant);
        this.nom = new SimpleStringProperty(nom);
        this.format = new SimpleStringProperty(format);
       
    }

    public String getIdentifiant() {
        return identifiant.getValue();
    }

    public String getNom() {
        return nom.getValue();
    }

    public String getFormat() {
        return format.getValue();
    }

   
    public void setIdentifiant(String identifiant) {
        this.identifiant.setValue(identifiant);
    }

    public void setNom(String nom) {
        this.nom.setValue(nom);
    }

    public void setFormat(String format) {
        this.format.setValue(format);
    }

   
}
