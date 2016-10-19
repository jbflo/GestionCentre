/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import configuration.Configurations;
import intfield.IntField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import uiswappingkernel.ControlledScreen;
import uiswappingkernel.ScreensController;

/**
 *
 * @author GINEL;  JBFLO  Cette classe implémente l'interface ControlledScreen. Cette
 * interface contient une seule méthode setScreenParent(ScreensController
 * screenPage). Cette méthode prend en paramètre le stackpanne de base de
 * l'interface graphique. Ceci est utile car lors du chargement d'une interface
 * loadScreen dans la classe ScreensController, il est important que l'interface
 * qu'on veux charger ai une instance de screenController. Le stackpane de base
 * de l'application. Cette classe joue aussi le rôle de contrôleur. Elle
 * contrôle l'interface FXMLDocument.fxml
 */
public class FXMLDocumentControllerConfiguration implements Initializable, ControlledScreen {

    private Configurations configuration;
    ScreensController myController;
    @FXML
    private TableView tableView;
    @FXML
    private TextField ipField;
    @FXML
    private TextField bdField;
    @FXML
    private IntField portField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    /*Cette méthode permet de sauvegarder les paramètres de connexion à la base de données*/

    @FXML
    public void handleSaveConfig(ActionEvent e) {
        configuration.setURL(ipField.getText());
        configuration.setDatabaseName(bdField.getText());
        configuration.setPort(portField.getValue());
        configuration.setLogin(loginField.getText());
        configuration.setPassword(passwordField.getText());
        configuration.save();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configuration = new Configurations();
        ipField.setText(configuration.getURL());
        bdField.setText(configuration.getDatabaseName());
        portField.setValue(configuration.getPort());
        loginField.setText(configuration.getLogin());
        passwordField.setText(configuration.getPassword());
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
}
