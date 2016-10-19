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
public class Procedure {

    private final SimpleStringProperty identifiant;
    private final SimpleStringProperty numero;
    private final SimpleStringProperty libelle;
   
    public Procedure(String identifiant, String numero, String libelle) {
        this.identifiant = new SimpleStringProperty(identifiant);
        this.numero = new SimpleStringProperty(numero);
        this.libelle = new SimpleStringProperty(libelle);
       
    }

    public String getIdentifiant() {
        return identifiant.getValue();
    }

    public String getNumero() {
        return numero.getValue();
    }

    public String getLibelle() {
        return libelle.getValue();
    }

   
    public void setIdentifiant(String identifiant) {
        this.identifiant.setValue(identifiant);
    }

    public void setNumero(String numero) {
        this.numero.setValue(numero);
    }

    public void setLibelle(String libelle) {
        this.libelle.setValue(libelle);
    }

   
}
