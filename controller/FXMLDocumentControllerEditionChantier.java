/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import data.Chantier;
import data.DataBase;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jfxtras.scene.control.LocalTimePicker;
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
public class FXMLDocumentControllerEditionChantier implements Initializable, ControlledScreen {

    DataBase database;
    ScreensController myController;
    @FXML
    private Label nbreResult;
    @FXML
    private TableView tableView;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldLocalisation;
    @FXML
    private ComboBox comboTypologie;
    @FXML
    private ComboBox comboUsage;
    @FXML
    private TextField textFieldEmprise;
    @FXML
    private DatePicker dateCreate;
    @FXML
    private LocalTimePicker localTime;
    @FXML
    private CheckBox check;
    @FXML
    private TextField textFieldtitre;
    @FXML
    private TextArea textAreaDescription;

    @FXML
    public void handleClicSupprimer(ActionEvent ev) {
        //Je recupère les informations
        List<Chantier> chantiers = tableView.getSelectionModel().getSelectedItems();
        if (chantiers == null) {
            Dialogs.create().title("Warning").message("veuillez selectionner un chantier").showInformation();
        } else {
            for (Chantier chantier : chantiers) {
                try {
                    database.deleteChantier(chantier.getNom());

                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerEditionChantier.class.getName()).log(Level.SEVERE, null, ex);
                    Dialogs.create().title("Warning").message("le chantier n'a pas été supprimé").showInformation();
                }
            }
            //Je met la table à jour
            tableView.getItems().clear();
            String resultats = database.getDataChantier();
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    String[] elt = tableResult[i].split(":::");
                    tableView.getItems().add(new Chantier(elt[0], elt[1], elt[2], elt[3], elt[4], elt[5], elt[6], elt[7],elt[8]));
                }
            }
            //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
            nbreResult.setText("" + tableView.getItems().size());

        }
    }

    @FXML
    public void handleClicEnregistrer(ActionEvent ev) {
        //Je recupère les informations

        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        }
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }

}
