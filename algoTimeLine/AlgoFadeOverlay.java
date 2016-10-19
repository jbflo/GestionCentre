
package algoTimeLine;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author GINEL ; JBFLO Cette classe permet d'attribuer une animation de disparition
 * à une boite de dialogue déjà affichée.
 */
public class AlgoFadeOverlay {

    //Interface mère qui acceuille le popup
    StackPane interfaceMere;
    //Boite de dialogue qui contient les informations personnalisées
    StackPane dialog;
    //Timeline pour la disparition de la boite
    public static TimelineBuilder timelineDisapear;
    //Timeline pour l'apparition de la boite de dialogue
    Timeline timelineAppearBox;
    //Timeline pour la disparition de la boite de dialogue
    Timeline timelineFadeFond;

    public AlgoFadeOverlay(StackPane interfaceMere, StackPane dialog) {
        this.interfaceMere = interfaceMere;
        this.dialog = dialog;
        //Ici j'ajoute la boite de dialogue à l'interface principale
        StackPane pile = new StackPane();

        interfaceMere.getChildren().add(pile);
        interfaceMere.getChildren().add(dialog);
        timelineDisapear
                = TimelineBuilder.create().keyFrames(
                        new KeyFrame(Duration.millis(100),
                                new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent t) {
                                        dialog.setVisible(false);
                                        interfaceMere.getChildren().remove(1);
                                    }
                                },
                                new KeyValue(interfaceMere.opacityProperty(), 1, Interpolator.EASE_BOTH)
                        ), new KeyFrame(Duration.millis(300),
                                new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent t) {
                                        dialog.setVisible(false);
                                        interfaceMere.getChildren().remove(1);
                                    }
                                },
                                new KeyValue(dialog.opacityProperty(), 0, Interpolator.EASE_BOTH)
                        ));

        timelineAppearBox = new Timeline();
        pile.setOpacity(0);
        final KeyValue kvv = new KeyValue(dialog.opacityProperty(), 1, Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(100), kvv);
        timelineAppearBox.getKeyFrames().add(kf);
        timelineFadeFond = new Timeline();
        pile.setStyle("-fx-background-color: #333333;");
        final KeyValue kv = new KeyValue(pile.opacityProperty(), 0.7, Interpolator.EASE_BOTH);
        final KeyFrame kff = new KeyFrame(Duration.millis(300), kv);
        timelineFadeFond.getKeyFrames().add(kff);

    }

    public TimelineBuilder getTimelineDisapear() {
        return timelineDisapear;
    }

    public Timeline getTimelineAppearBox() {
        return timelineAppearBox;
    }

    public Timeline getTimelineFadeFond() {
        return timelineFadeFond;
    }

}
