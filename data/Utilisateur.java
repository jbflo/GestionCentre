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
public class Utilisateur {
    
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty grade;
   
    private final SimpleStringProperty login;
     
    public Utilisateur(String nom, String prenom, String grade, String login) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.grade = new SimpleStringProperty(grade);
        this.login = new SimpleStringProperty(login);
        
       
    }
    
    public String getNom() {
        return nom.get();
    }
    
    public String getPrenom() {
        return prenom.get();
    }
    
    public String getGrade() {
        return grade.get();
    }
    
    public String getLogin() {
        return login.get();
    }
    
   
    
    public void setNom(String nom) {
        this.nom.set(nom);
    }
    
    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }
    
    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

   
   
    
}
