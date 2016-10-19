/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Chantier;
import data.DataBase;
import data.Probleme;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.dialog.Dialogs;
import uiswappingkernel.ControlledScreen;
import uiswappingkernel.ScreensController;

/**
 *
 * @author JBFLO & Ginel Cette classe implémente l'interface ControlledScreen. Cette
 * interface contient une seule méthode setScreenParent(ScreensController
 * screenPage). Cette méthode prend en paramètre le stackpanne de base de
 * l'interface graphique. Ceci est utile car lors du chargement d'une interface
 * loadScreen dans la classe ScreensController, il est important que l'interface
 * qu'on veux charger ai une instance de screenController. Le stackpane de base
 * de l'application. Cette classe joue aussi le rôle de contrôleur. Elle
 * contrôle l'interface FXMLDocument.fxml
 */
public class FXMLDocumentControllerProbleme implements Initializable, ControlledScreen {

    public static final String THIRD_SCREEN_CHANTIER = "chantieredit";
    public static final String THIRD_SCREEN_CHANTIER_FXML = "/fxml/FXMLChantier.fxml";
    private ScreensController thirdContainer;
    DataBase database;
    Probleme probleme;
    ScreensController myController;

    @FXML
    private Label nbreResult;
    @FXML
    private TableView<Probleme> tableView;
    @FXML
    private TextField textFieldProbleme;
    @FXML
    private TextArea textAreaProbleme;

    @FXML
    public void handleClicSupprimerProbleme(ActionEvent ev) {
        //Je recupère les informations
        List<Probleme> problemes = tableView.getSelectionModel().getSelectedItems();
        if ((problemes == null) || (problemes.size() == 0)) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Gestion Centre");
                    alert.setHeaderText("Alert !!!");
                    alert.setContentText(" veuillez selectionner un problème\n");
                    alert.showAndWait(); 
           
        } else {
            for (Probleme probleme : problemes) {
                try {
                    database.deleteProbleme(probleme.getNom());

                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerProbleme.class.getName()).log(Level.SEVERE, null, ex);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Gestion Centre");
                    alert.setHeaderText("Alert !!!");
                    alert.setContentText(" le problème n'a pas été supprimé car il est lié à certains chantiers, veuillez supprimer ces liaisons au préalable\n");
                    alert.showAndWait(); 
                   }
            }
            //Je met la table à jour
            tableView.getItems().clear();
            String resultats = database.getDataProbleme();
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    String[] elt = tableResult[i].split(":::");
                    tableView.getItems().add(new Probleme(elt[0], elt[1], elt[2]));
                }
            }
            //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
            nbreResult.setText("" + tableView.getItems().size());

        }
    }
    
    @FXML
    public void handleClicModifierProbleme(ActionEvent ev) {
        //Je recupère les informations
        String probleme = textFieldProbleme.getText();
        String libelle = textAreaProbleme.getText();
        List<Probleme> problemes = tableView.getSelectionModel().getSelectedItems();
    
        if ((probleme == null) || (libelle == null) || (probleme.length() == 0) || (libelle.length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Gestion Centre");
                    alert.setHeaderText("Alert !!!");
                    alert.setContentText("Veuillez remplir tous vos champs obligatoires\n");
                    alert.showAndWait();   
         
        } else {
            try {
                if (database.getIfProblemeExists(probleme)) {
                    try {
                           for (Probleme problem : problemes) {
      
                                String IDF = problem.getIdentifiant();
                                database.updateProbleme(probleme, libelle, IDF );
                         }                          
                        tableView.getItems().clear();
                        String resultats = database.getDataProbleme();
                        if ((resultats != null) && (resultats.length() > 0)) {
                            String[] tableResult = resultats.split(";;;");
                            for (int i = 0; i < tableResult.length; i++) {
                                String[] elt = tableResult[i].split(":::");
                                tableView.getItems().add(new Probleme(elt[0], elt[1], elt[2]));
                            }
                        }
                        Dialogs.create().title("Information").message("Problème enregistrée avec success").showInformation();
                        textFieldProbleme.setText("");
                        textAreaProbleme.setText("");
                        //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
                        nbreResult.setText("" + tableView.getItems().size());
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerProbleme.class.getName()).log(Level.SEVERE, null, ex);
                        Dialogs.create().title("Erreur").message("Problème non enregistrée").showError();

                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Gestion Centre");
                    alert.setHeaderText("Alert !!!");
                    alert.setContentText("Problème déjà existant, vérifiez vos informations\n");
                    alert.showAndWait();   
                 //   Dialogs.create().title("Warning").message("Problème déjà existant, vérifiez vos informations").showInformation();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerProbleme.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
     }
      
        
 

    @FXML
    public void handleClicEnregistrer(ActionEvent ev) {
        //Je recupère les informations

        String probleme = textFieldProbleme.getText();
        String libelle = textAreaProbleme.getText();
        if ((probleme == null) || (libelle == null) || (probleme.length() == 0) || (libelle.length() == 0)) {
            Dialogs.create().title("Avertissement").message("Veuillez remplir tous vos champs obligatoires").showWarning();

        } else {
            try {
                if (!database.getIfProblemeExists(probleme)) {
                    try {
                        database.insertProbleme(probleme, libelle);
                        tableView.getItems().clear();
                        String resultats = database.getDataProbleme();
                        if ((resultats != null) && (resultats.length() > 0)) {
                            String[] tableResult = resultats.split(";;;");
                            for (int i = 0; i < tableResult.length; i++) {
                                String[] elt = tableResult[i].split(":::");
                                tableView.getItems().add(new Probleme(elt[0], elt[1], elt[2]));
                            }
                        }
                        Dialogs.create().title("Information").message("Problème enregistrée avec success").showInformation();
                        textFieldProbleme.setText("");
                        textAreaProbleme.setText("");
                        //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
                        nbreResult.setText("" + tableView.getItems().size());
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerProbleme.class.getName()).log(Level.SEVERE, null, ex);
                        Dialogs.create().title("Erreur").message("Problème non enregistrée").showError();

                    }

                } else {
                    Dialogs.create().title("Warning").message("Problème déjà existant, vérifiez vos informations").showInformation();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerProbleme.class.getName()).log(Level.SEVERE, null, ex);
            }
         

        }
    }

    private void showDetails(Probleme p) {
        if (p != null) {
            // Fill the labels with info from the person object.
             textFieldProbleme.setText(p.getNom());
             textAreaProbleme.setText(p.getLibelle());
     
          
        } else {
            // Person is null, remove all the text.
             textFieldProbleme.setText("");
             textAreaProbleme.setText("");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tableView.getSelectionModel().selectedItemProperty().addListener(
           (observable, oldValue, newValue) -> showDetails(newValue));
             
        
        
        try {
            database = new DataBase("com.mysql.jdbc.Driver");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerProbleme.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Je configure la tableView à la reception de données
        ObservableList listeColonnes = tableView.getColumns();
        String[] tableProperties = new String[3];
        tableProperties[0] = "identifiant";
        tableProperties[1] = "nom";
        tableProperties[2] = "libelle";

        for (int i = 0; i < listeColonnes.size(); i++) {
            TableColumn column = (TableColumn) listeColonnes.get(i);
            column.setCellValueFactory(new PropertyValueFactory<>(tableProperties[i]));
        }
        tableView.getItems().clear();
        String resultats = database.getDataProbleme();
        if ((resultats != null) && (resultats.length() > 0)) {
            String[] tableResult = resultats.split(";;;");
            for (int i = 0; i < tableResult.length; i++) {

                String[] elt = tableResult[i].split(":::");
                if (elt != null) {
                   
                    tableView.getItems().add(new Probleme(elt[0], elt[1], elt[2]));
                }
            }
        }
        //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
        nbreResult.setText("" + tableView.getItems().size());

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

}
