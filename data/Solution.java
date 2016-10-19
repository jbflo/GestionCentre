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
public class Solution {

    private final SimpleStringProperty identifiant;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty libelle;
   
    public Solution(String identifiant, String nom, String libelle) {
        this.identifiant = new SimpleStringProperty(identifiant);
        this.nom = new SimpleStringProperty(nom);
        this.libelle = new SimpleStringProperty(libelle);
       
    }

    public String getIdentifiant() {
        return identifiant.getValue();
    }

    public String getNom() {
        return nom.getValue();
    }

    public String getLibelle() {
        return libelle.getValue();
    }

   
    public void setIdentifiant(String identifiant) {
        this.identifiant.setValue(identifiant);
    }

    public void setNom(String nom) {
        this.nom.setValue(nom);
    }

    public void setLibelle(String libelle) {
        this.libelle.setValue(libelle);
    }

   
}
