/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiswappingkernel;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import org.apache.log4j.Logger;

/**
 *
 * @author Sikadie Cette classe permet de charger les interface stackpane en
 * passant en paramètres le chemin du fichier fxml.
 */
public class ScreenLoader {

    /*Déclaration et instanciation du logger qui permettra de signaler tous les évènements pouvant surgir dans cette classe*/
    private static Logger logger = Logger.getLogger(ScreensController.class);

    public ScreenLoader() {
    }

   public StackPane getScreenStack(String fileName) {
        /*Ici j'obtient l'interface graphique en utilisant le nom du fichier fxml(xxxx.fxml par exemple) comme paramètre*/
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(fileName));
        StackPane loadScreen;
        try {
            /*Charge la structure XML du fichier FXML*/
            myLoader.load();

            /*J'obtient l'écran à charger en le convertisant en StackPane. Le loadScreen obtenu sera ensuite
             ajouté au hashMap en utilisant la méthode addScreen*/
            loadScreen = (StackPane) FXMLLoader.load(getClass().getResource(fileName));
        } catch (IOException e) {
            logger.error("Erreur d'ouverture du fichier fxml de l'interface peux être le nom du fichier n'est pas correct", e);
            return null;
        }
        return loadScreen;
    }
}
