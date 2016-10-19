
package animation;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import algoTimeLine.AlgoTimeAbstract;

/**
 * Cette classe permet d'implémenter un algorithme d'animation. . L'animation
 * sera ainsi passée ne paramètre à n'importe quel noeud. Et lancant juste la
 * méthode play(), on peut lancer l'animation en question. Pour le cas
 * particulier de cette animation, la vue se voit se fondre après un temps et se
 * restituer après avoir complètement fondu
 *
 * @author JBFLO ; GINEL
 */
public class TransitionAnim {

    /**
     * Ce constructeur permet de construire un objet 
     *
     *
     */
    private AlgoTimeAbstract algoTime;

    public TransitionAnim() {

    }

    public AlgoTimeAbstract getAlgoTime() {
        return algoTime;
    }
    /*Permet de modifier l'algorithme de transition à chaud*/

    public void setAlgoTime(AlgoTimeAbstract algoTime) {
        this.algoTime = algoTime;
    }

}
