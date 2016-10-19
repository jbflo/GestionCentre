
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
 * @author GINEL ; JBFLO Algorithme permettant de fabriquer une animation de type
 * fade(fondre). Pour celà, la méthode getTimeline est reécrite.
 */
public class AlgoTimeFade extends AlgoTimeAbstract {
  

    @Override
    public void createAlgoTime(StackPane pile, Node nodeAdded) {
      
            //Création du Timeline personnalisé pour le fade
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
                //pile.getChildren().add(nodeAdded);
                setTimeline (new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)), new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0))));
               // timeline.play();
            }
        

    }

}
