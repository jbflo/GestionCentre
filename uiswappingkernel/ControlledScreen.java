/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uiswappingkernel;

/**
 *
 * @author GINEL ; JBFLO
 * Cette interface propose d'implémenter la méthode setScreenParent qui prend en paramètre le
 * screensController nécessaire aux diffenrents controller des différentes interface
 */
public interface ControlledScreen {

    public void setScreenParent(ScreensController screenPage);
}
