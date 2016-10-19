package uiswappingkernel;

import java.io.IOException;
import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import algoTimeLine.AlgoTimeFade;
import animation.TransitionAnim;

/**
 *
 * @author GINEL ; JBFLO Cette classe hérite de la classe ScreenControllerAbstract qui
 * implémente les opérations sur la pile d'interfaces. au besoin, la présente
 * classe peux reécrire la méthode setScreen(String name).
 *
 */
public class ScreensController extends StackPane {

    private ControlledScreen myScreenControler;
    /* Déclaration et instanciation du hashMap contenant les différents écrans*/
    private HashMap<String, Node> screens = new HashMap<>();

    public ControlledScreen getMyScreenControler() {
        return myScreenControler;
    }

    public void setMyScreenControler(ControlledScreen myScreenControler) {
        this.myScreenControler = myScreenControler;
    }

    /*Déclaration et instanciation du logger qui permettra de signaler tous les évènements pouvant surgir dans cette classe*/
    private static Logger logger = Logger.getLogger(ScreensController.class);

    public ScreensController() {

    }

    public static Logger getLogger() {
        return logger;
    }

    public HashMap<String, Node> getScreens() {
        return screens;
    }

    public void setScreens(HashMap<String, Node> screens) {
        this.screens = screens;
    }

    /**
     *
     * @param name représente la clé de l'interface qui va être ajoutée au
     * hashmap d'interfaces graphiques
     * @param screen représente le noeud graphique voulant être ajouté enl
     * screens c'est l'ensemble de toutes les interfaces graphiques chargées
     * dans le hashmap question
     */
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    /**
     *
     * @param name représente la clé de l'interface devant être chargée sous
     * forme d'interface graphique.
     * @param ressource représente le nom en question ou le chemin fxml de
     * l'interface devant être chargée.
     * @return un booleen si true, le chargement à réussi. Sinon il ya eu un
     * problème lors du chargement.
     */
    public boolean loadScreen(String name, String ressource) {
        System.out.print("\nloaded" + this.toString());
        /*Ici j'obtient l'interface graphique en utilisant le nom du fichier fxml(xxxx.fxml par exemple) comme paramètre*/
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(ressource));
        StackPane loadScreen;
        try {
            /*Charge la structure XML du fichier FXML*/
            myLoader.load();

            /*J'obtient l'écran à charger en le convertisant en StackPane. Le loadScreen obtenu sera ensuite
             ajouté au hashMap en utilisant la méthode addScreen*/
            loadScreen = (StackPane) FXMLLoader.load(getClass().getResource(ressource));
        } catch (IOException e) {
            logger.error("Erreur d'ouverture du fichier fxml de l'interface peux être le nom du fichier n'est pas correct", e);
            return false;
        }
        myScreenControler = ((ControlledScreen) myLoader.getController());
        myScreenControler.setScreenParent(this);
        /* J'ajoute l'interface correctement chargée à partir du fichier FXML au hashMap */
        addScreen(name, loadScreen);
        return true;
    }

    /**
     * Cette méthode permet d'enlever un écran dans le map
     *
     * @param name représente la clé de l'interface qu'on veut enlever
     * @return retourne un booleen si true alors l'interface a été enlevée sinon
     * l'interface n'existait même pas.
     *
     */
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

    public boolean setScreen(String name) {
        if (screens.get(name) != null) {
            /*Ici on peux faire des manipulation préable à chaque interface particulière avant de lancer la transition, du genre chargement des données,...*/
            TransitionAnim transition = new TransitionAnim();
            /*Je crée l'algorithme, puis je l'attribut a la transition,je fournie les paramètres d'animation*/ transition.setAlgoTime(new AlgoTimeFade());
            AlgoTimeFade alf = new AlgoTimeFade();
            transition.setAlgoTime(alf);
            alf.createAlgoTime(this, screens.get(name));
            /*Enfin je démmarre l'algorithme*/
            alf.startAlgoTime();

            /*Ces lignes réprésentent la transition liée au changement d'interface graphique. En passant d'une interface A
             à une interface  B, cette animation jouant sur l'opacité de la */
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {
                Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)), new KeyFrame(new Duration(400), new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        //J'enlève l'écran actuel, en première position du stack
                        getChildren().remove(0);
                        //Je met l'écran actuel
                        getChildren().add(0, screens.get(name));
                        //je lance une animation pour rendre la transition agréable
                        Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
                        fadeIn.play();
                    }
                }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                //Si le stackPane ne contenait rien, alors je fixe son opacité à 0, j'ajoute l'écran et je lance une animation sans toute fois faire remove(0) sous peine de lever une exception
                setOpacity(0.0);
                getChildren().add(screens.get(name));
                Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }

        } else {
            logger.warn("Aucune interface ne correspond à la clé" + name);
            return false;
        }
        return true;
    }
}
