/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overlay;


import javafx.scene.layout.StackPane;

import algoTimeLine.AlgoFadeOverlay;

/**
 *
 * @author JBFLO ;  GINEL.
 * Cette classe permet de définir les boites de dialogue génériques. Il suffit de passer
 * la vue qu'on veut voir apparaitre en overlay dans le constructeur. ensuite sa disparition est gérée par les méthodes
 * de la classe AlgoFadeOverlay.
 */
public class Dialog extends StackPane {

    /**
     *
     * @param width représente la largeur du popup
     * @param heigth représente la hauteur du popup
     * @param popup représente le stackPane personnalisé contenant les
     * informations à afficher en overlay
     * @param interfaceMere ceci est l'interface mère qui contiendra tout le
     * reste.
     */
    public Dialog(int width, int heigth, StackPane popup, StackPane interfaceMere) {
        /*Je met le dialogue personalisé dans la zone prévue à cet effet*/
        this.getChildren().add(popup);
        /*Je défini le style de la boite de dialog le rayon du bord et la couleur du background son définies */
        this.setStyle("-fx-background-radius: 15; -fx-background-image: url(\"/fxml/background.jpg\");");
        /*La taille de la boite de dialogue est définie*/
        this.setPrefSize(width, heigth);
        this.setMinSize(width, heigth);
        this.setMaxSize(width, heigth);
        /*Je lance les deux animations*/
        AlgoFadeOverlay afo = new AlgoFadeOverlay(interfaceMere, this);
        afo.getTimelineAppearBox().play();
        afo.getTimelineFadeFond().play();

    }
}
