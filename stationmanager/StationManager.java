/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stationmanager;

import data.Md5;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import uiswappingkernel.ScreensController;

/**
 *
 * @author JBFLO ; GINEL
 */
public class StationManager extends Application {
    /*J'instancie le stackPane qui contiendra une par une les différentes interfaces de l'application selon les choix faits par
     les utilisateurs*/

    public static Stage primaryStage;
    public static ScreensController mainContainer = new ScreensController();
    /*Déclaration et instanciation du logger qui permettra de signaler tous les évènements pouvant surgir dans cette classe*/

    private static Logger logger = Logger.getLogger(StationManager.class);
    /*La structure de données utilisée ici pour stocker les interfaces utilisateurs est celle de pile. Les élements graphiques
     sont donc empilés les uns sur les autres. Chaque clic de souris équivaut dans ce cas au retrait d'une interface au dessus de la pile et à 
     empilage d'une nouvelle interface en tête de pile.
     Les lignes suivantes donnent la clé de chaque interface et leur nom de sorte qu'il puissent etre stocké dans le hashtable.
     exemple: 
     -----cle----------->login
     -----password------------>password
     */
    public static final String MAIN_SCREEN = "login";
    public static final String MAIN_SCREEN_FXML = "/fxml/FXMLDocumentLogin.fxml";
    public static final String MAIN_SCREEN_WORK = "main";
    public static final String MAIN_SCREEN_WORK_FXML = "/fxml/FXMLMain.fxml";
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        //Je centre l'interface
        primaryStage.centerOnScreen();
        //Je charge les interfaces dans le hashMap en utilisant la méthode loadScreen
        mainContainer.loadScreen(MAIN_SCREEN, MAIN_SCREEN_FXML);
        mainContainer.loadScreen(MAIN_SCREEN_WORK, MAIN_SCREEN_WORK_FXML);
        //Je fixe l'écran du mainContainer comme étant celui du login.
        //mainContainer.setScreen(MAIN_SCREEN);
        mainContainer.setScreen(MAIN_SCREEN);
        StackPane root = new StackPane();
        //Je met le noeud mainContainer dans mon noeud racine root
        root.getChildren().add(mainContainer);
        //Le noeud le plus dominant dans ma scène est root
        Scene scene = new Scene(root);
        //J'attribut la scene au stage de mon application
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(StationManager.class.getResourceAsStream("/images/track.png"))); // Set the application icon.
        System.out.println(Md5.encode("0000"));
        //Affichage du stage
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
