/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import algoTimeLine.AlgoFadeOverlay;
import data.Annexe;
import data.Chantier;
import data.DataBase;
import data.Probleme;
import data.Procedure;
import data.Solution;
import intfield.IntField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import jfxtras.scene.control.LocalTimePicker;
import org.controlsfx.dialog.Dialogs;
import stationmanager.StationManager;
import uiswappingkernel.ControlledScreen;
import uiswappingkernel.ScreenLoader;
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

public class FXMLDocumentControllerCreateChantier implements Initializable, ControlledScreen {

    //Permet de charger une interface connaissant son chemin fxml
    ScreenLoader sl = new ScreenLoader();
    //Implémente l'algo d'animation nécessaire à la apparition de la boite de dialogue
    private AlgoFadeOverlay afo;
    StackPane dialog;
    List<File> selectedFiles;
    public static final String SOLUTION_FXML = "/fxml/FXMLSolution.fxml";
    public static final String THIRD_SCREEN_CHANTIER = "chantieredit";
    public static final String THIRD_SCREEN_CHANTIER_FXML = "/fxml/FXMLChantier.fxml";
    private ScreensController thirdContainer = new ScreensController();
    DataBase database;
    public static Chantier chantier;
    public static Probleme probleme;
    ScreensController myController;
    @FXML
    private StackPane stackPanepb;
    @FXML
    private StackPane stackPaneChantier;
    @FXML
    private StackPane stackPanePetit;
    @FXML
    private Label nbreResult;
    @FXML
    private Label nbreResultProbleme;
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
    private TextArea textAreaDescription;
    @FXML
    private ComboBox textFieldProbleme;
    @FXML
    private TableView tableViewProbleme;
    @FXML
    private TextField textFieldID;
    @FXML
    private Label nbreResultSolution;
    @FXML
    private TableView tableViewSolution;
    @FXML
    private TableView tableViewProcedure;
    @FXML
    private TextField textFieldSolution;
    @FXML
    private IntField textFieldNumero;
    @FXML
    private TextArea textAreaProcedure;
    @FXML
    private TextArea textAreaSolution;
    @FXML
    private TextField textFieldFileName;
    @FXML
    private TableView tableViewAnnexe;
@FXML
void handleFileSave(ActionEvent ev){
    if(textFieldFileName.getText().length()==0)
        Dialogs.create().title("Information").message("Veuillez sélectionner le(s) archive(s) à enregistrer").showInformation();
    else{
        if (selectedFiles != null) {
            for (File selected : selectedFiles) {
                try {
                    FileInputStream inputStream=new FileInputStream(selected);
                   //J'enregistre les fichiers
                    database.insertAnnexe(selected.getName(), selected.getPath().split(".")[1], inputStream, Integer.parseInt(chantier.getIdentifiant()));
                
             //Je configure la tableView à la reception de données
            ObservableList listeColonnesAnnexe = tableViewAnnexe.getColumns();
            String[] tablePropertiesAnnexe = new String[3];
            tablePropertiesAnnexe[0] = "identifiant";
            tablePropertiesAnnexe[1] = "nom";
            tablePropertiesAnnexe[2] = "format";
            
            for (int i = 0; i < listeColonnesAnnexe.size(); i++) {
                TableColumn column = (TableColumn) listeColonnesAnnexe.get(i);
                column.setCellValueFactory(new PropertyValueFactory<>(tablePropertiesAnnexe[i]));
            }
            tableViewAnnexe.getItems().clear();
            String resultatsAnnexe = null;
            resultatsAnnexe = database.getDataAnnexe(Integer.parseInt(chantier.getIdentifiant()));
            if ((resultatsAnnexe != null) && (resultatsAnnexe.length() > 0)) {
                String[] tableResult = resultatsAnnexe.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    
                    String[] elt = tableResult[i].split(":::");
                    if (elt != null) {
                        if (elt[i] == null) {
                            elt[i] = "";
                        }
                        tableViewAnnexe.getItems().add(new Annexe(elt[0], elt[1], elt[2]));
                    }
                }
            }
            
                } catch (FileNotFoundException ex) {
                    Dialogs.create().title("Erreur Chargement").message("Erreur lors du chargement des fichiers veuillez vérifier que les chemins n'ont pas changé").showWarning();
                    Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } 
    }
}
    @FXML
    void handleFileChoose(ActionEvent ev) {
        textFieldFileName.setText("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF files");
        fileChooser.setInitialDirectory(new File("C:"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"), new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        fileChooser.setTitle("Ouvrir les ressources");
     selectedFiles = fileChooser.showOpenMultipleDialog(StationManager.primaryStage);
        
        if (selectedFiles != null) {
            for (File selected : selectedFiles) {
                if(textFieldFileName.getText().length()>0)
                textFieldFileName.setText(textFieldFileName.getText() + "," + selected.getName());
                else
                  textFieldFileName.setText(textFieldFileName.getText()  + selected.getName());   
            }
            
        } else {
            
        }
    }
    
    @FXML
    public void handleClicSupprimerSolution(ActionEvent ev) {
        //Je recupère les informations
        List<Solution> solutions = tableViewSolution.getSelectionModel().getSelectedItems();
        if ((solutions == null) || (solutions.size() == 0)) {
            Dialogs.create().title("Warning").message("veuillez selectionner un problème").showInformation();
        } else {
            for (Solution solution : solutions) {
                try {
                    database.deleteSolution(Integer.parseInt(solution.getIdentifiant()));
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerSolution.class.getName()).log(Level.SEVERE, null, ex);
                    Dialogs.create().title("Warning").message("la solution n'a pas été supprimé car elle est liée à certaines procédures, veuillez supprimer ces procédures au préalable").showInformation();
                }
            }
            //Je met la table à jour
            tableViewSolution.getItems().clear();
            String resultats = database.getDataProbleme();
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    String[] elt = tableResult[i].split(":::");
                    tableViewSolution.getItems().add(new Solution(elt[0], elt[1], elt[2]));
                }
            }
            //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
            nbreResultSolution.setText("" + tableViewSolution.getItems().size());
            
        }
    }
    
    @FXML
    public void handleclicTableView(MouseEvent ev) {
        
        tableViewProcedure.getItems().clear();
        String resultats = database.getDataProcedure(Integer.parseInt(((Solution) tableViewSolution.getSelectionModel().getSelectedItem()).getIdentifiant()));
        if ((resultats != null) && (resultats.length() > 0)) {
            String[] tableResult = resultats.split(";;;");
            for (int i = 0; i < tableResult.length; i++) {
                String[] elt = tableResult[i].split(":::");
                tableViewProcedure.getItems().add(new Procedure(elt[0], elt[1], elt[2]));
            }
        }
        textFieldNumero.setText("" + tableViewProcedure.getItems().size());
    }
    
    @FXML
    public void handleClicEnregistrerProcedure(ActionEvent ev) {
        //Je recupère les informations
        if (((Solution) tableViewSolution.getSelectionModel().getSelectedItem()) != null) {
            String numero = textFieldNumero.getText();
            String libelle = textAreaProcedure.getText();
            if ((numero == null) || (libelle == null) || (numero.length() == 0) || (libelle.length() == 0)) {
                Dialogs.create().title("Avertissement").message("Veuillez remplir tous vos champs obligatoires").showWarning();
                
            } else {
                
                try {
                    database.insertProcedure(Integer.parseInt(numero), libelle, Integer.parseInt(((Solution) tableViewSolution.getSelectionModel().getSelectedItem()).getIdentifiant()));
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                }
                tableViewProcedure.getItems().clear();
                String resultats = database.getDataProcedure(Integer.parseInt(((Solution) tableViewSolution.getSelectionModel().getSelectedItem()).getIdentifiant()));
                if ((resultats != null) && (resultats.length() > 0)) {
                    String[] tableResult = resultats.split(";;;");
                    for (int i = 0; i < tableResult.length; i++) {
                        String[] elt = tableResult[i].split(":::");
                        tableViewProcedure.getItems().add(new Procedure(elt[0], elt[1], elt[2]));
                    }
                }
                Dialogs.create().title("Information").message("Procédure enregistrée avec success").showInformation();
                textFieldNumero.setText("" + tableViewProcedure.getItems().size());
                textAreaProcedure.setText("");
                // nbreResultSolution.setText("" + tableViewProcedure.getItems().size());

            }
        } else {
            Dialogs.create().title("Avertissement").message("Veuillez sélectionner une solution").showWarning();
        }
    }
    
    @FXML
    public void handleClicSupprimerProcedure(ActionEvent ev) {
//Je recupère les informations
        Procedure procedure = ((Procedure) tableViewProcedure.getSelectionModel().getSelectedItem());
        if (procedure != null) {
            
            try {
                database.deleteProcedure(Integer.parseInt(((Procedure) tableViewProcedure.getSelectionModel().getSelectedItem()).getIdentifiant()));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Je met à jour la numérotation
            for (int i = Integer.parseInt(procedure.getNumero()) + 1; i < tableViewProcedure.getItems().size(); i++) {
                Procedure procedurei = (Procedure) tableViewProcedure.getItems().get(i);
                try {
                    database.updateProcedure(Integer.parseInt(procedurei.getNumero()) - 1, Integer.parseInt(procedurei.getIdentifiant()));
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            tableViewProcedure.getItems().clear();
            String resultats = database.getDataProcedure(Integer.parseInt(((Solution) tableViewSolution.getSelectionModel().getSelectedItem()).getIdentifiant()));
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    String[] elt = tableResult[i].split(":::");
                    tableViewProcedure.getItems().add(new Procedure(elt[0], elt[1], elt[2]));
                }
            }
            // Dialogs.create().title("Information").message("Solution enregistrée avec success").showInformation();
            textFieldNumero.setText("" + tableViewProcedure.getItems().size());
            textAreaSolution.setText("");
                // nbreResultSolution.setText("" + tableViewProcedure.getItems().size());

        } else {
            Dialogs.create().title("Avertissement").message("Veuillez sélectionner une procédure").showWarning();
        }
    }
    
    @FXML
    public void handleClicEnregistreSolution(ActionEvent ev) {
        //Je recupère les informations

        String solution = textFieldSolution.getText();
        String libelle = textAreaSolution.getText();
        if ((solution == null) || (libelle == null) || (solution.length() == 0) || (libelle.length() == 0)) {
            Dialogs.create().title("Avertissement").message("Veuillez remplir tous vos champs obligatoires").showWarning();
            
        } else {
            try {
                if (!database.getIfSolutionExists(solution)) {
                    try {
                        database.insertSolution(solution, libelle, Integer.parseInt(probleme.getIdentifiant()));
                        tableViewSolution.getItems().clear();
                        String resultats = database.getDataSolution(Integer.parseInt(probleme.getIdentifiant()));
                        if ((resultats != null) && (resultats.length() > 0)) {
                            String[] tableResult = resultats.split(";;;");
                            for (int i = 0; i < tableResult.length; i++) {
                                String[] elt = tableResult[i].split(":::");
                                tableViewSolution.getItems().add(new Solution(elt[0], elt[1], elt[2]));
                            }
                        }
                        Dialogs.create().title("Information").message("Solution enregistrée avec success").showInformation();
                        textFieldSolution.setText("");
                        textAreaSolution.setText("");
                        //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
                        nbreResultSolution.setText("" + tableViewSolution.getItems().size());
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerSolution.class.getName()).log(Level.SEVERE, null, ex);
                        Dialogs.create().title("Erreur").message("Solution non enregistrée").showError();
                        
                    }
                    
                } else {
                    Dialogs.create().title("Warning").message("Solution déjà existant, vérifiez vos informations").showInformation();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerSolution.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    @FXML
    public void handleCharger(ActionEvent ev) {
        
        chantier = (Chantier) tableView.getSelectionModel().getSelectedItem();
        
        if (chantier == null) {
            Dialogs.create().title("Warning").message("veuillez selectionner un chantier").showInformation();
        } else {
            textFieldID.setText(chantier.getIdentifiant());
            textFieldName.setText(chantier.getNom());
            textFieldLocalisation.setText(chantier.getLocalisation());
            comboTypologie.getSelectionModel().select(chantier.getTypologie());
            comboUsage.getSelectionModel().select(chantier.getUsage());
            textFieldEmprise.setText(chantier.getEmprise());
            dateCreate.setValue(LocalDate.parse(chantier.getDate_creation().split(" ")[0]));
            localTime.setLocalTime(LocalTime.parse(chantier.getDate_creation().split(" ")[1]));
            if (chantier.getActif().compareTo("oui") == 0) {
                check.setSelected(true);
            } else {
                check.setSelected(false);
            }
            
            thirdContainer.loadScreen(THIRD_SCREEN_CHANTIER, THIRD_SCREEN_CHANTIER_FXML);
            stackPanePetit.getChildren().clear();
            stackPanePetit.getChildren().add(thirdContainer);
            
            thirdContainer.setScreen(THIRD_SCREEN_CHANTIER);
            
        }
    }
    
    @FXML
    void handleAnnulerSolution(ActionEvent ev) {
        /*Je fait disparaitre la boite de dialogue*/
        AlgoFadeOverlay.timelineDisapear.build().play();
    }
    
    @FXML
    void handleClicSolution(ActionEvent ev) {
        //Je recupère les informations
        probleme = (Probleme) tableViewProbleme.getSelectionModel().getSelectedItem();
        if ((probleme == null)) {
            Dialogs.create().title("Warning").message("veuillez selectionner un problème").showInformation();
        } else {
    //Je fait apparaitre la boite de dialogue présentant les différents choix

            //Tout d'abord je charge l'interface de la dialog box du fxml vers l'objet
            StackPane root = sl.getScreenStack(FXMLDocumentControllerCreateChantier.SOLUTION_FXML);
            dialog = new StackPane();
            dialog.setAlignment(Pos.CENTER);
            dialog.getChildren().add(root);
            // sl.getScreenStack(StationManag.STATION_SCREENFXML
            //stackPanepb.getParent().getParent().getParent().getParent().getParent()
            afo = new AlgoFadeOverlay((StackPane) stackPanepb.getParent().getParent().getParent().getParent(), dialog);

            //Je lance la page d'apparition du dialog box
            afo.getTimelineFadeFond().play();
            afo.getTimelineAppearBox().play();
        }
        
    }
    
    @FXML
    public void handleClicSupprimer(ActionEvent ev) {
        //Je recupère les informations
        List<Chantier> chantiers = tableView.getSelectionModel().getSelectedItems();
        if (chantiers == null) {
            Dialogs.create().title("Warning").message("veuillez selectionner un chantier").showInformation();
        } else {
            for (Chantier chantie : chantiers) {
                try {
                    database.deleteChantier(chantie.getNom());
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
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
                    tableView.getItems().add(new Chantier(elt[0], elt[1], elt[2], elt[3], elt[4], elt[5], elt[6], elt[7], elt[8]));
                }
            }
            //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
            nbreResult.setText("" + tableView.getItems().size());
            
        }
    }
    
    @FXML
    public void handleClicSupprimerProbleme(ActionEvent ev) {
        //Je recupère les informations
        List<Probleme> problemes = tableViewProbleme.getSelectionModel().getSelectedItems();
        if ((problemes == null) || (problemes.size() == 0)) {
            Dialogs.create().title("Warning").message("veuillez selectionner un problème").showInformation();
        } else {
            for (Probleme probleme : problemes) {
                try {
                    database.deleteProblemeRencontre(Integer.parseInt(probleme.getIdentifiant()), Integer.parseInt(chantier.getIdentifiant()));
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                    Dialogs.create().title("Warning").message("le problème n'a pas été supprimé").showInformation();
                }
            }
            //Je met la table à jour
            tableViewProbleme.getItems().clear();
            String resultats = null;
            try {
                resultats = database.getIDProbleme(Integer.parseInt(chantier.getIdentifiant()));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(":::");
                for (int i = 0; i < tableResult.length; i++) {
                    
                    String[] elt = null;
                    try {
                        elt = database.getProbleme(Integer.parseInt(tableResult[i])).split(":::");
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (elt != null) {
                        
                        tableViewProbleme.getItems().add(new Probleme(elt[0], elt[1], elt[2]));
                    }
                }
            }
            //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
            nbreResultProbleme.setText("" + tableViewProbleme.getItems().size());
            
        }
    }
    
    @FXML
    public void handleClicEnregistrer(ActionEvent ev) {
        //Je recupère les informations

        String nom = textFieldName.getText();
        String localisation = textFieldLocalisation.getText();
        String typologie = comboTypologie.getSelectionModel().getSelectedItem().toString();
        String usage = comboUsage.getSelectionModel().getSelectedItem().toString();
        String emprise = textFieldEmprise.getText();
        LocalDate date = dateCreate.getValue();
        String actif;
        if (check.isSelected()) {
            actif = "oui";
        } else {
            actif = "non";
        }
        if ((nom == null) || (localisation == null) || (usage == null) || (emprise == null) || (typologie == null) || (date == null) || (localisation.length() == 0) || (nom.length() == 0) || (typologie.length() == 0) || (usage.length() == 0) || (emprise.length() == 0)) {
            Dialogs.create().title("Avertissement").message("Veuillez remplir tous vos champs obligatoires").showWarning();
            
        } else {
            //Je transforme la date
            String dateString = date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
            String timeString = localTime.getLocalTime().format(DateTimeFormatter.ISO_TIME);
            String dateCompletString = dateString + " " + timeString;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parsed = null;
            try {
                parsed = format.parse(dateCompletString);
            } catch (ParseException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }
            java.sql.Timestamp datesql = new java.sql.Timestamp(parsed.getTime());
            try {
                if (!database.getIfChantierExists(nom)) {
                    try {
                        database.insertChantier(nom, localisation, typologie, usage, emprise, datesql, actif);
                        tableView.getItems().clear();
                        String resultats = database.getDataChantier();
                        if ((resultats != null) && (resultats.length() > 0)) {
                            String[] tableResult = resultats.split(";;;");
                            for (int i = 0; i < tableResult.length; i++) {
                                String[] elt = tableResult[i].split(":::");
                                tableView.getItems().add(new Chantier(elt[0], elt[1], elt[2], elt[3], elt[4], elt[5], elt[6], elt[7], elt[8]));
                            }
                        }
                        //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
                        nbreResult.setText("" + tableView.getItems().size());
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                        Dialogs.create().title("Erreur").message("Chantier non enregistrée").showError();
                        
                    }
                    
                    Dialogs.create().title("Information").message("Chantier enregistrée avec success").showInformation();
                } else {
                    Dialogs.create().title("Warning").message("Chantier déjà existant").showInformation();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }
            Dialogs.create().title("Warning").message("le chantier n'a pas été crée").showInformation();
            
        }
    }
    
    @FXML
    public void handleChoice(ActionEvent ev) {
        try {
            String probleme = database.getProbleme(textFieldProbleme.getSelectionModel().getSelectedItem().toString());
            if (probleme.length() > 0) {
                String[] tableResult = probleme.split(":::");
                textAreaDescription.setText(tableResult[2]);
                
            } else {
                textAreaDescription.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void handleClicEnregistrerProbleme(ActionEvent ev) {
        //Je recupère les informations

        String description = textAreaDescription.getText();
        String probleme = null;
        if ((textFieldProbleme.getSelectionModel().getSelectedItem() == null) || (description == null) || (description.length() == 0) || (textFieldProbleme.getSelectionModel().getSelectedItem().toString().length() == 0)) {
            Dialogs.create().title("Avertissement").message("Veuillez remplir tous vos champs obligatoires pour ajouter un problème").showWarning();
            
        } else {
            probleme = textFieldProbleme.getSelectionModel().getSelectedItem().toString();
            try {
                if (!database.getIfProblemeExists(database.getProblemeId(probleme), Integer.parseInt(chantier.getIdentifiant()))) {
                    //je fait tout simplement correspondre le problème au chantier
                    try {
                        database.insertProblemeChantier(database.getProblemeId(probleme), Integer.parseInt(chantier.getIdentifiant()));
                        tableViewProbleme.getItems().clear();
                        String resultats = null;
                        try {
                            resultats = database.getIDProbleme(Integer.parseInt(chantier.getIdentifiant()));
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if ((resultats != null) && (resultats.length() > 0)) {
                            String[] tableResult = resultats.split(":::");
                            for (int i = 0; i < tableResult.length; i++) {
                                
                                String[] elt = null;
                                try {
                                    elt = database.getProbleme(Integer.parseInt(tableResult[i])).split(":::");
                                } catch (SQLException ex) {
                                    Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                if (elt != null) {
                                    
                                    tableViewProbleme.getItems().add(new Probleme(elt[0], elt[1], elt[2]));
                                }
                            }
                        }
                        //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
                        nbreResultProbleme.setText("" + tableViewProbleme.getItems().size());
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                        Dialogs.create().title("Erreur").message("Problème non enregistrée").showError();
                        
                    }
                    
                    Dialogs.create().title("Information").message("Problème enregistrée avec success").showInformation();
                } else {
                    Dialogs.create().title("Warning").message("Problème déjà existant").showInformation();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        if (url.getFile().contains("FXMLCreateChantier.fxml")) {
            try {
                // TODO
                comboTypologie.getItems().add(new String("R+1"));
                comboTypologie.getItems().add(new String("R+2"));
                comboTypologie.getItems().add(new String("R+3"));
                comboTypologie.getItems().add(new String("R+4"));
                comboTypologie.getItems().add(new String("R+5"));
                comboTypologie.getItems().add(new String("R+6"));
                comboTypologie.getItems().add(new String("R+7"));
                comboTypologie.getItems().add(new String("R+8"));
                comboTypologie.getItems().add(new String("R+9"));
                comboTypologie.getItems().add(new String("R+10"));
                comboTypologie.getSelectionModel().selectFirst();
                comboUsage.getItems().add(new String("Habitation"));
                comboUsage.getItems().add(new String("Bureaux"));
                comboUsage.getItems().add(new String("Usine"));
                comboUsage.getItems().add(new String("Autres"));
                comboUsage.getSelectionModel().selectFirst();
                dateCreate.setValue(LocalDate.now());
                database = new DataBase("com.mysql.jdbc.Driver");
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }
            SimpleStringProperty nom;

            //Je configure la tableView à la reception de données
            ObservableList listeColonnes = tableView.getColumns();
            String[] tableProperties = new String[9];
            tableProperties[0] = "identifiant";
            tableProperties[1] = "nom";
            tableProperties[2] = "localisation";
            tableProperties[3] = "typologie";
            tableProperties[4] = "usage";
            tableProperties[5] = "emprise";
            tableProperties[6] = "etape";
            tableProperties[7] = "date_creation";
            tableProperties[8] = "actif";
            
            for (int i = 0; i < listeColonnes.size(); i++) {
                TableColumn column = (TableColumn) listeColonnes.get(i);
                column.setCellValueFactory(new PropertyValueFactory<>(tableProperties[i]));
            }
            tableView.getItems().clear();
            String resultats = database.getDataChantier();
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    
                    String[] elt = tableResult[i].split(":::");
                    if (elt != null) {
                        if (elt[i] == null) {
                            elt[i] = "";
                        }
                        tableView.getItems().add(new Chantier(elt[0], elt[1], elt[2], elt[3], elt[4], elt[5], elt[6], elt[7], elt[8]));
                    }
                }
            }
            //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
            nbreResult.setText("" + tableView.getItems().size());
        } else if (url.getFile().contains("FXMLChantier.fxml")) {
            
            try {
                database = new DataBase("com.mysql.jdbc.Driver");
                
                String problemes = database.getDataProbleme();
                if ((problemes != null) && (problemes.length() > 0)) {
                    String[] tableResult = problemes.split(";;;");
                    for (int i = 0; i < tableResult.length; i++) {
                        String[] elt = tableResult[i].split(":::");
                        textFieldProbleme.getItems().add(elt[1]);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Je configure la tableView à la reception de données
            ObservableList listeColonnes = tableViewProbleme.getColumns();
            String[] tableProperties = new String[3];
            tableProperties[0] = "identifiant";
            tableProperties[1] = "nom";
            tableProperties[2] = "libelle";
            
            for (int i = 0; i < listeColonnes.size(); i++) {
                TableColumn column = (TableColumn) listeColonnes.get(i);
                column.setCellValueFactory(new PropertyValueFactory<>(tableProperties[i]));
            }
            tableViewProbleme.getItems().clear();
            String resultats = null;
            try {
                resultats = database.getIDProbleme(Integer.parseInt(chantier.getIdentifiant()));
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(":::");
                for (int i = 0; i < tableResult.length; i++) {
                    
                    String[] elt = null;
                    try {
                        elt = database.getProbleme(Integer.parseInt(tableResult[i])).split(":::");
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (elt != null) {
                        
                        tableViewProbleme.getItems().add(new Probleme(elt[0], elt[1], elt[2]));
                    }
                }
            }
            
             //Je configure la tableView à la reception de données
            ObservableList listeColonnesAnnexe = tableViewAnnexe.getColumns();
            String[] tablePropertiesAnnexe = new String[3];
            tablePropertiesAnnexe[0] = "identifiant";
            tablePropertiesAnnexe[1] = "nom";
            tablePropertiesAnnexe[2] = "format";
            
            for (int i = 0; i < listeColonnesAnnexe.size(); i++) {
                TableColumn column = (TableColumn) listeColonnesAnnexe.get(i);
                column.setCellValueFactory(new PropertyValueFactory<>(tablePropertiesAnnexe[i]));
            }
            tableViewAnnexe.getItems().clear();
            String resultatsAnnexe = null;
            resultatsAnnexe = database.getDataAnnexe(Integer.parseInt(chantier.getIdentifiant()));
            if ((resultatsAnnexe != null) && (resultatsAnnexe.length() > 0)) {
                String[] tableResult = resultatsAnnexe.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    
                    String[] elt = tableResult[i].split(":::");
                    if (elt != null) {
                        if (elt[i] == null) {
                            elt[i] = "";
                        }
                        tableViewAnnexe.getItems().add(new Annexe(elt[0], elt[1], elt[2]));
                    }
                }
            }
            
            
        } else if (url.getFile().contains("FXMLSolution.fxml")) {
            try {
                database = new DataBase("com.mysql.jdbc.Driver");
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentControllerCreateChantier.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Je configure la tableView à la reception de données
            ObservableList listeColonnes = tableViewSolution.getColumns();
            
            String[] tableProperties = new String[3];
            tableProperties[0] = "identifiant";
            tableProperties[1] = "nom";
            tableProperties[2] = "libelle";
            
            for (int i = 0; i < listeColonnes.size(); i++) {
                TableColumn column = (TableColumn) listeColonnes.get(i);
                column.setCellValueFactory(new PropertyValueFactory<>(tableProperties[i]));
            }
            //Je configure la tableView à la reception de données
            ObservableList listeColonnesProcedure = tableViewProcedure.getColumns();
            
            String[] tablePropertiesProcedure = new String[3];
            tablePropertiesProcedure[0] = "identifiant";
            tablePropertiesProcedure[1] = "numero";
            tablePropertiesProcedure[2] = "libelle";
            
            for (int i = 0; i < listeColonnesProcedure.size(); i++) {
                TableColumn column = (TableColumn) listeColonnesProcedure.get(i);
                column.setCellValueFactory(new PropertyValueFactory<>(tablePropertiesProcedure[i]));
            }
            
            tableViewSolution.getItems().clear();
            String resultats = database.getDataSolution(Integer.parseInt(probleme.getIdentifiant()));
            if ((resultats != null) && (resultats.length() > 0)) {
                String[] tableResult = resultats.split(";;;");
                for (int i = 0; i < tableResult.length; i++) {
                    
                    String[] elt = tableResult[i].split(":::");
                    if (elt != null) {
                        if (elt[i] == null) {
                            elt[i] = "";
                        }
                        tableViewSolution.getItems().add(new Solution(elt[0], elt[1], elt[2]));
                    }
                }
            }
            //Je bind la propriété du label de nombre d'enregistrement dans le tableView au nbre
            nbreResultSolution.setText("" + tableViewSolution.getItems().size());
        }
    }
    
    @Override
    public void setScreenParent(ScreensController screenPage) {
        myController = screenPage;
    }
    
}
