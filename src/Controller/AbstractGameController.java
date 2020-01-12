
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller;

import java.net.URISyntaxException;

import Controller.subscreen.GameDataMenuController;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import tool.SoundBox;

public abstract class AbstractGameController extends AbstractSubcontentController {

	@FXML
	protected GameDataMenuController gameDataController;
	@FXML
	protected VBox rootVbox;

	public abstract void createContent(); // fonction pour créer le contenu relatif au jeu

	public void injectGameDataController(GameDataMenuController injected) { // fonction pour donner une référence du
																			// menu d'affichage des données de jeu à
																			// l'écran de jeu
		gameDataController = injected;
	}

	public void jouerSonGameOver() {
		try {
			SoundBox.playSound(getClass().getResource("/sound/voix/cest_fini.wav").toURI().toString());

		} catch (URISyntaxException e) {

			e.printStackTrace();
		}

	}

}
