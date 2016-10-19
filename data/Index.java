/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JBFLO; GINEL
 */
public class Index {
    
    private final SimpleStringProperty id;
    private final SimpleStringProperty distributeur;
    private final SimpleStringProperty heure_releve;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty index_avant;
    private final SimpleStringProperty index_apres;
    
    public Index(String id, String distributeur, String heure_releve, String nom, String index_avant, String index_apres) {
        this.id = new SimpleStringProperty(id);
        this.distributeur = new SimpleStringProperty(distributeur);
        this.heure_releve = new SimpleStringProperty(heure_releve);
        this.nom = new SimpleStringProperty(nom);
        this.index_avant = new SimpleStringProperty(index_avant);
        this.index_apres = new SimpleStringProperty(index_apres);
        
    }
    
    public String getId() {
        return id.get();
    }
    
    public String getDistributeur() {
        return distributeur.get();
    }
    
    public String getHeure_releve() {
        return heure_releve.get();
    }
    
    public String getNom() {
        return nom.get();
    }
    
    public String getIndex_avant() {
        return index_avant.get();
    }

    public String getIndex_apres() {
        return index_apres.get();
    }
    
    public void setId(String id) {
        this.id.set(id);
    }
    
    public void setDistributeur(String distributeur) {
        this.distributeur.set(distributeur);
    }
    
    public void setHeure_releve(String heure_releve) {
        this.heure_releve.set(heure_releve);
    }
    
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    
    public void setIndex_avant(String index_avant) {
        this.index_avant.set(index_avant);
    }

    public void setIndex_apres(String idex_apres) {
        this.index_apres.set(idex_apres);
    }
    
}
