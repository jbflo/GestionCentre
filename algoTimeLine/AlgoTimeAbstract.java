
package algoTimeLine;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author JBFLO ; GINEL Cette classe générique permet de proposer une méthode
 * abstraite pour l'implémentatin d'un algorithme.en plus une méthod permettant
 * de démarrer l'algorithme est aussi utilisée.
 */
public abstract class AlgoTimeAbstract {
    /*Variable représentant le timeLine en question*/

    static private Timeline timeline;

    /**
     *
     * @param pile la pile qui est l'interface mère
     * @param nodeAdded le noeud devant être ajouté
     *
     */
    public void createAlgoTime(StackPane pile, Node nodeAdded) {
//Création du Timeline personnalisé pour le fade ceci est le TimeLine par défaut
        final DoubleProperty opacity = pile.opacityProperty();

        if (pile.getChildren().isEmpty()) {
            setTimeline(new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)), new KeyFrame(new Duration(400), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    //J'enlève l'écran actuel, en première position du stack
                    pile.getChildren().remove(0);
                    //Je met l'écran actuel
                    pile.getChildren().add(0, nodeAdded);
                    //je lance une animation pour rendre la transition agréable
                    Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
                    fadeIn.play();
                }
            }, new KeyValue(opacity, 0.0))));
            // timeline.play();

        } else {
            //Si le stackPane ne contenait rien, alors je fixe son opacité à 0, j'ajoute l'écran et je lance une animation sans toute fois faire remove(0) sous peine de lever une exception
            pile.setOpacity(0.0);
            pile.getChildren().add(nodeAdded);
            setTimeline(new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0))));
            // timeline.play();
        }
    }

    /**
     * Permet de démarrer
     */
    public void startAlgoTime() {
        //On lance le Timeline
        getTimeline().play();
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        AlgoTimeAbstract.timeline = timeline;
    }

}
