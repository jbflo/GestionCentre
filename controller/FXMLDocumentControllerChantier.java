/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import uiswappingkernel.ControlledScreen;
import uiswappingkernel.ScreensController;

/**
 * @author GINEL;  JBFLO Cette classe implémente l'interface ControlledScreen. Cette
 * interface contient une seule méthode setScreenParent(ScreensController
 * screenPage). Cette méthode prend en paramètre le stackpanne de base de
 * l'interface graphique. Ceci est utile car lors du chargement d'une interface
 * loadScreen dans la classe ScreensController, il est important que l'interface
 * qu'on veux charger ai une instance de screenController. Le stackpane de base
 * de l'application. Cette classe joue aussi le rôle de contrôleur. Elle
 * contrôle l'interface FXMLDocument.fxml
 */

public class FXMLDocumentControllerChantier implements Initializable, ControlledScreen {
    public static final String SECONDARY_SCREEN_PROBLEME = "probleme";
    public static final String SECONDARY_SCREEN_PROBLEME_FXML = "/fxml/FXMLProbleme.fxml";   
    public static final String SECONDARY_SCREEN_CHANTIER = "chantier";
    public static final String SECONDARY_SCREEN_CHANTIER_FXML = "/fxml/FXMLCreateChantier.fxml";
    public static final String SECONDARY_SCREEN_UTILISATEUR = "utilisateur";
    public static final String SECONDARY_SCREEN_UTILISATEUR_FXML = "/fxml/FXMLUtilisateur.fxml";
    public static final String SECONDARY_SCREEN_CONFIGURATION = "configuration";
    public static final String SECONDARY_SCREEN_CONFIGURATION_FXML = "/fxml/FXMLConfiguration.fxml";
    
    public static final String SECONDARY_SCREEN_ABOUT = "about";
    public static final String SECONDARY_SCREEN_ABOUT_FXML = "/fxml/FXMLConfiguration.fxml";

    private ScreensController secondaryContainer;
    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // J'initialise le chargement de toutes les interfaces disponibles lorsqu'on cliquera sur un menu
        secondaryContainer = new ScreensController();
        secondaryContainer.loadScreen(SECONDARY_SCREEN_PROBLEME, SECONDARY_SCREEN_PROBLEME_FXML);
        secondaryContainer.loadScreen(SECONDARY_SCREEN_CHANTIER, SECONDARY_SCREEN_CHANTIER_FXML);
        
        secondaryContainer.loadScreen(SECONDARY_SCREEN_UTILISATEUR, SECONDARY_SCREEN_UTILISATEUR_FXML);
        
        secondaryContainer.loadScreen(SECONDARY_SCREEN_CONFIGURATION, SECONDARY_SCREEN_CONFIGURATION_FXML);
        secondaryContainer.loadScreen(SECONDARY_SCREEN_ABOUT, SECONDARY_SCREEN_ABOUT_FXML);        
        stackPane.getChildren().add(secondaryContainer);
        
    }

    //Je gère le clique sur le sous menus configuration  
    @FXML
    public void handleConfiguration(ActionEvent event) {
        secondaryContainer.setScreen(FXMLDocumentControllerChantier.SECONDARY_SCREEN_CONFIGURATION);
    }

    //Je gère le clique sur le sous menus gestion de chantier
    @FXML
    public void handleChantier(ActionEvent event) {
        secondaryContainer.getScreens().get(SECONDARY_SCREEN_CHANTIER);
        secondaryContainer.loadScreen(SECONDARY_SCREEN_CHANTIER, SECONDARY_SCREEN_CHANTIER_FXML);
        secondaryContainer.setScreen(FXMLDocumentControllerChantier.SECONDARY_SCREEN_CHANTIER);     
    }

    //Je gère le clique sur le sous menus gestion de chantier
    @FXML
    public void handleUtilisateur(ActionEvent event) { 
        secondaryContainer.setScreen(FXMLDocumentControllerChantier.SECONDARY_SCREEN_UTILISATEUR);      
    }
    
    //Je gère le clique sur le sous menus About /  Afficher les info apropos
  /*  @FXML
    public void handleAbout(ActionEvent event) {
        secondaryContainer.setScreen(FXMLDocumentControllerChantier.SECONDARY_SCREEN_ABOUT);
        
    } 
    */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Gestion Centre");
        alert.setHeaderText("About");
        alert.setContentText("Author: Ginel ' JBFLO \n Projet Applicatif Genie Logiciel \n"
                + " Notre logiciel est conçu pour être un module d’une suite plus complète comprenant la gestion des Centre, des membres, "
                + " de la facturation, du planning des activités"
                + "....");

        alert.showAndWait();
    } 
    
    @FXML
    private void handleAides(){
    
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Gestion Centre");
        alert.setHeaderText("Aide");
        alert.setContentText("Projet Applicatif Genie Logiciel\n"
                + "1) Creation de centre\n"
                + "2)Creation d'utilisateur\n"
                + "2)Ajouter une Fiche\n"
                + "3)Configuration"
                + "....");
        alert.showAndWait();   
    }
   
    /*Cette méthode gère l'action générée en cliquant sur le sous menu Quitter*/
    @FXML
    public void handleQuit(ActionEvent event) {
        /*Je  quitte de l'application*/
        stationmanager.StationManager.primaryStage.close();
    }
    /*Cette méthode gère l'action générée en cliquant sur le sous menu Déconnexion*/

    @FXML
    public void handleDeconnexion(ActionEvent event) {
        /*Je me déconnecte de l'application*/
        stationmanager.StationManager.mainContainer.setScreen(stationmanager.StationManager.MAIN_SCREEN);

    }
@FXML
public void handleProblemes(ActionEvent event){
    /*Je gère les problèmes globaux ici*/
   secondaryContainer.setScreen(FXMLDocumentControllerChantier.SECONDARY_SCREEN_PROBLEME);
}
    @Override
    public void setScreenParent(ScreensController screenPage) {
        secondaryContainer = screenPage;
    }

}
