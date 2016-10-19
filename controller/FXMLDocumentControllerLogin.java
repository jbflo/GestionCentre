/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.DataBase;
import data.Md5;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
public class FXMLDocumentControllerLogin implements Initializable, ControlledScreen {

    ScreensController myController;
    public static ScreensController secondaryContainer;
    DataBase database;
//Cette variable représente le texte nécessaire à afficher les informations concernant la connexion lors du login
    @FXML
    private Text actionTarget;
    @FXML
    private TextField textLogin;
    @FXML
    private TextField textPassword;

    public FXMLDocumentControllerLogin() {

    }
    /*Cette méthode gère l'action générée par le bouton Sign In de connexion*/

    @FXML
    public void handleSubmitButtonAction(ActionEvent event) {
        /*Je rend le message visible et je me rassure que le texte qui s'affiche est bien celui qui est attendu*/
        //actionTarget.setVisible(true);
        //actionTarget.setText("Acces denied... ");
        String login = textLogin.getText();
        String password = textPassword.getText();
        if ((login.length() > 0) && (password.length() > 0)) {
            stationmanager.StationManager.mainContainer.setScreen(stationmanager.StationManager.MAIN_SCREEN_WORK);

        }else{
             Dialogs.create().title("Information").message("Veuillez saisir tous les champs").showInformation();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            database = new DataBase("com.mysql.jdbc.Driver");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

}
