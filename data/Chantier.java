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
public class Chantier {

    private final SimpleStringProperty identifiant;
    private final SimpleStringProperty nom;

    private final SimpleStringProperty localisation;
    private final SimpleStringProperty typologie;
    private final SimpleStringProperty usage;
    private final SimpleStringProperty emprise;
    private final SimpleStringProperty etape;
    private final SimpleStringProperty date_creation;
    private final SimpleStringProperty actif;

    public Chantier(String identifiant, String nom, String localisation, String typologie, String usage, String emprise, String etape, String date_creation, String actif) {
        this.identifiant = new SimpleStringProperty(identifiant);
        this.nom = new SimpleStringProperty(nom);
        this.localisation = new SimpleStringProperty(localisation);
        this.usage = new SimpleStringProperty(usage);
        this.emprise = new SimpleStringProperty(emprise);
        this.date_creation = new SimpleStringProperty(date_creation);
        this.actif = new SimpleStringProperty(actif);
        this.etape = new SimpleStringProperty(etape);
        this.typologie = new SimpleStringProperty(typologie);
    }

    public String getIdentifiant() {
        return identifiant.getValue();
    }

    public String getNom() {
        return nom.getValue();
    }

    public String getDate_creation() {
        return date_creation.getValue();
    }

    public String getActif() {
        return actif.getValue();
    }

    public String getLocalisation() {
        return localisation.getValue();
    }

    public String getUsage() {
        return usage.getValue();
    }

    public String getEmprise() {
        return emprise.getValue();
    }

    public String getEtape() {
        return etape.getValue();
    }

    public String getTypologie() {
        return typologie.getValue();
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant.setValue(identifiant);
    }

    public void setDate_creation(String date_creation) {
        this.date_creation.setValue(date_creation);
    }

    public void setActif(String actif) {
        this.actif.setValue(actif);
    }

    public void setNom(String nom) {
        this.nom.setValue(nom);
    }

    public void setLocalisation(String localisation) {
        this.localisation.setValue(localisation);
    }

    public void setUsage(String usage) {
        this.usage.setValue(usage);
    }

    public void setEmprise(String emprise) {
        this.emprise.setValue(emprise);
    }

    public void setEtape(String etape) {
        this.etape.setValue(etape);
    }

    public void setTypologie(String typologie) {
        this.typologie.setValue(typologie);
    }
}
