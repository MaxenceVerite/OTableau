
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.AbstractSubcontentController;
import Controller.subscreen.DifficultyChooserController;
import Controller.subscreen.SideMenuController;
import Model.content.Difficulte;
import View.OTableau;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SelectionController implements Initializable {

	@FXML
	private SideMenuController sideMenuController;
	@FXML
	private DifficultyChooserController difficultyChooserController;
	@FXML
	private AbstractSubcontentController gameSelectionController;
	@FXML
	private VBox gameChooserBox;

	private int currentDifficulty;

	public void initialize(URL arg0, ResourceBundle arg1) {

		// système fait au début sans que j'y retouche, la mise en place d'une nouvelle
		// solution est périlleuse donc je laisse //

		// On stocke l'entier correspondant à la difficulté, et on le modifie à chaque
		// fois que le slider de difficulté change//

		currentDifficulty = (int) difficultyChooserController.getCurrentDifficultyProperty().get();

		difficultyChooserController.getCurrentDifficultyProperty()
				.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
					currentDifficulty = (int) difficultyChooserController.getCurrentDifficultyProperty().get();
					setDifficulty(currentDifficulty);
				});

	}

	// FONCTION QUI CHARGE A L'ECRAN LA VUE CHOISI A L'ECRAN PRECEDENT

	public void setCurrentContent(String s) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource(s));
		HBox subcontent = (HBox) loader.load();
		AbstractSubcontentController subcontroller = loader.getController(); // on charge le controleur dont on ne
																				// connait pas encore le type
																				// (français,maths,etc)

		gameSelectionController = subcontroller;
		if (gameSelectionController.getDifficulty() == null) {
			setDifficulty(currentDifficulty);
		}
		gameChooserBox.getChildren().setAll(subcontent); // on affiche le gameChooserContent dans la vbox du milieu
		VBox.setVgrow(subcontent, Priority.ALWAYS); // on lui demande de prendre toute la place disponible

	}

	public void setDifficulty(int dif) { // on fait navigué la difficulté
		Difficulte f;

		switch (dif) {
		case 1:
			f = Difficulte.facile;
			break;
		case 2:
			f = Difficulte.moyen;
			break;
		case 3:
			f = Difficulte.difficile;
			break;
		default:
			f = Difficulte.facile;
			break;
		}

		gameSelectionController.setDifficulty(f);

	}

}
