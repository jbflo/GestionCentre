/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.DataBase;
import data.Md5;
import data.Utilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.dialog.Dialogs;
import uiswappingkernel.ControlledScreen;
import uiswappingkernel.ScreensController;

/**
 *
 * @author Sikadie Cette classe implémente l'interface ControlledScreen. Cette
 * interface contient une seule méthode setScreenParent(ScreensController
 * screenPage). Cette méthode prend en paramètre le stackpanne de base de
 * l'interface graphique. Ceci est utile car lors du chargement d'une interface
 * loadScreen dans la classe ScreensController, il est important que l'interface
 * qu'on veux charger ai une instance de screenController. Le stackpane de base
 * de l'application. Cette classe joue aussi le rôle de contrôleur. Elle
 * contrôle l'interface FXMLDocument.fxml
 */
public class FXMLDocumentControllerUtilisateur implements Initializable, ControlledScreen {

    DataBase database;
    Md5 md5;
    ScreensController myController;
    @FXML
    Label nbreResult;

    @FXML
    private TableView tableView;

    @FXML
    private ComboBox comboBoxGrade;

    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldLogin;
    @FXML
    private PasswordField textFieldPass;
    @FXML
    private PasswordField textFieldPassConfirm;

    @FXML
    public void handleClicSupprimer(ActionEvent ev) {
  //Je recupère les informations
        List<Utilisateur> utilisateurs = tableView.getSelectionModel().getSelectedItems();
        if ((utilisateurs == null) || (utilisateurs.size() == 0)) {
            Dialogs.create().title("Warning").message("veuillez selectionner un utilisateur").showInformation();
        } else {
            for (Utilisateur utilisateur : utilisateurs) {
                try {
                    //Pour ne jamais supprimer le compte passe partout admin
                    if(utilisateur.getLogin().compareTo("admin")!=0){
                       
                    database.deleteUtilisateur(utilisateur.getLogin());
                    }else{
                         Dialogs.create().title("Avertissement").message("Impossible de supprimer l'utilisateur par défaut").showWarning();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //Je met la table à jour
            tableView.getItems().clear();
            String resultats = database.getUser();
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    String[] elt = tableResult[i].split(":::");
                    tableView.getItems().add(new Utilisateur(elt[0], elt[1], elt[2],elt[3]));
                }
            }
            //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
            nbreResult.setText("" + tableView.getItems().size());

        }
    }

    @FXML
    public void handleClicEnregistrer(ActionEvent ev) {
        String grade = comboBoxGrade.getSelectionModel().getSelectedItem().toString();

        String nom = textFieldNom.getText();
        String prenom = textFieldPrenom.getText();
        String login = textFieldLogin.getText();
        String password = textFieldPass.getText();
        String passwordConfirm = textFieldPassConfirm.getText();
        if ((grade.length() > 0) && (nom.length() > 0) && (prenom.length() > 0) && (login.length() > 0) && (password.length() > 0) && (passwordConfirm.length() > 0)) {
            if ((password.compareTo(passwordConfirm) == 0)) {
                //Je retrouve d'abord le code de la station nessecaire à l'enregistrement de la personne

//J'encode le mot de passe 
                password = Md5.encode(password);

                try {
                    if (database.getIfUserExists(login)) {    
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Gestion Centre");
                        alert.setHeaderText("Alert !!!");
                        alert.setContentText("Cet utilisateur exite déjà\n");
                        alert.showAndWait();  
                        
                    } else {
                        //J'enregistre l'utilisateur
                        database.insertUser(nom, prenom, login, password, grade);
                        tableView.getItems().clear();
                        String resultats = database.getUser();
                        if (resultats != null) {
                            String[] tableResult = resultats.split(";;;");
                            for (int i = 0; i < tableResult.length; i++) {
                                String[] elt = tableResult[i].split(":::");
                                //if (elt[i]==null){ elt[i]="";}
                                tableView.getItems().add(new Utilisateur(elt[0], elt[1], elt[2], elt[3]));
                            }
                        }
                        //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
                        nbreResult.setText("" + tableView.getItems().size());
                        textFieldLogin.setText("");
                        textFieldNom.setText("");
                        textFieldPass.setText("");
                        textFieldPassConfirm.setText("");
                        textFieldPrenom.setText("");
                        //Message d'information
                        Dialogs.create().title("Information").message("Utilisateur crée avec succes").showInformation();

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
                    Dialogs.create().title("Erreur").message(ex.getMessage()).showError();
                }
            } else {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Gestion Centre");
                 alert.setHeaderText("Alert !!!");
                 alert.setContentText("mots de passe pas identiques\n");
                 alert.showAndWait();  

            }
        } else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Gestion Centre");
             alert.setHeaderText("Alert !!!");
             alert.setContentText("Veuillez remplir tous vos champs obligatoires\n");
             alert.showAndWait(); 
          
        }
    }
    
    @FXML
    public void handleClicModifier(ActionEvent ev) {
        String grade = comboBoxGrade.getSelectionModel().getSelectedItem().toString();

        String nom = textFieldNom.getText();
        String prenom = textFieldPrenom.getText();
        String login = textFieldLogin.getText();
        String password = textFieldPass.getText();
        String passwordConfirm = textFieldPassConfirm.getText(); 
        List<Utilisateur> utilisateur = tableView.getSelectionModel().getSelectedItems();
        
        if ((grade.length() > 0) && (nom.length() > 0) && (prenom.length() > 0) && (login.length() > 0) && (password.length() > 0) && (passwordConfirm.length() > 0)) {
            if ((password.compareTo(passwordConfirm) == 0)) {
                //Je retrouve d'abord le code de la station nessecaire à l'enregistrement de la personne

        //J'encode le mot de passe 
                password = Md5.encode(password);

                try {                                   
                    //J'enregistre le neveau l'utilisateur
               
                        database.updateUser(nom, prenom, login, password, grade , utilisateur.get(0).getLogin());
                                           
                        tableView.getItems().clear();
                        String resultats = database.getUser();
                        if (resultats != null) {
                            String[] tableResult = resultats.split(";;;");
                            for (int i = 0; i < tableResult.length; i++) {
                                String[] elt = tableResult[i].split(":::");
                                //if (elt[i]==null){ elt[i]="";}
                                tableView.getItems().add(new Utilisateur(elt[0], elt[1], elt[2], elt[3]));
                            } 
                        }
                        //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
                        nbreResult.setText("" + tableView.getItems().size());
                        textFieldLogin.setText("");
                        textFieldNom.setText("");
                        textFieldPass.setText("");
                        textFieldPassConfirm.setText("");
                        textFieldPrenom.setText("");
                        //Message d'information
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Gestion Centre");
                        alert.setHeaderText("Alert !!!");
                        alert.setContentText("Utilisateur crée avec succes\n");
                        alert.showAndWait(); 
                       
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
                    Dialogs.create().title("Erreur").message(ex.getMessage()).showError();
                }
            } else {
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Gestion Centre");
                alert.setHeaderText("Alert !!!");
                alert.setContentText("mots de passe pas identiques\n");
                alert.showAndWait(); 

               
            }
        } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Gestion Centre");
                alert.setHeaderText("Alert !!!");
                alert.setContentText("Veuillez remplir tous vos champs obligatoires\n");
                alert.showAndWait(); 
            
        }
    }
    
    
       private void showDetails(Utilisateur u) {
        if (u != null) {
            // Fill the labels with info from the person object.
           // comboBoxGrade.setSelectionModel(value);
            textFieldNom.setText(u.getNom());
            textFieldPrenom.setText(u.getPrenom());
            textFieldLogin.setText(u.getLogin());
            //textFieldPass.setText(u.);
            // String passwordConfirm = textFieldPassConfirm.getText();
               
        } else {
            // Utilisateur is null, remove all the text.
            textFieldNom.setText("");
            textFieldPrenom.setText("");
            textFieldLogin.setText("");
             
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           tableView.getSelectionModel().selectedItemProperty().addListener(
           (observable, oldValue, newValue) -> showDetails((Utilisateur) newValue));
             
        

        try {
            database = new DataBase("com.mysql.jdbc.Driver");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Je met les éléments dans le combobox du choix de poste

        List list = new ArrayList();
        list.add("Administrateur");
        list.add("Chef de travaux");
        //Je construit le observable list à partir de la liste classique
        ObservableList observableList = FXCollections.observableList(list);
        comboBoxGrade.setItems(observableList);
        comboBoxGrade.setValue(observableList.get(0));

        //Je configure la tableView à la reception de données
        ObservableList listeColonnes = tableView.getColumns();
        String[] tableProperties = new String[4];
        tableProperties[0] = "nom";
        tableProperties[1] = "prenom";
        tableProperties[2] = "login";
        tableProperties[3] = "grade";

        for (int i = 0; i < listeColonnes.size(); i++) {
            TableColumn column = (TableColumn) listeColonnes.get(i);
            column.setCellValueFactory(new PropertyValueFactory<>(tableProperties[i]));
        }
        tableView.getItems().clear();
        String resultats = database.getUser();
        if (resultats != null) {
            String[] tableResult = resultats.split(";;;");
            for (int i = 0; i < tableResult.length; i++) {
                String[] elt = tableResult[i].split(":::");
                //if (elt[i]==null){ elt[i]="";}
                tableView.getItems().add(new Utilisateur(elt[0], elt[1], elt[2], elt[3]));
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
