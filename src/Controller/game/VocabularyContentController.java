
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.game;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.AbstractGameController;
import Model.langues.GameModelVocabulary;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import tool.SoundBox;

public class VocabularyContentController extends AbstractGameController implements Initializable {

	// components //

	@FXML
	private Button c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15;
	private Button[] cartes; // les cartes
	@FXML
	private Label e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15;
	private Label[] etiquettes; // les etiquettes de la "Word Bank"

	// game model //

	private GameModelVocabulary content;

	public void initialize(URL arg0, ResourceBundle arg1) {
		cartes = new Button[] { c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15 };
		etiquettes = new Label[] { e0, e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15 };

	}

	public void createContent() {
		content = new GameModelVocabulary(getDifficulty());

		// on lie au gameDataController //

		gameDataController.getNameLabelProperty().bind(content.getNomProperty());
		gameDataController.getScoreTFProperty().bind(content.getScoreProperty().asString("%.0f"));
		gameDataController.getVieTFProperty().bind(content.getVieProperty().asString("%d"));

		// on lie la visibilité de l'écran de jeu à gameOver //

		rootVbox.visibleProperty().bind(content.isGameOverProperty().not());

		// -----

		for (int i = 0; i < cartes.length; i++) {
			etiquettes[i].setVisible(false);
			if (i < content.getCartesVocabulaire().length) { // on bind les string du modèle aux boutons.

				etiquettes[i].textProperty().bind(content.getCarte(i).getMotProperty()); // pour les étiquettes, dans
																							// l'ordre des mots
				// pour les cartes, dans un ordre choisi par un "ordonnanceur" pour garder une
				// référence de la nouvelle position des cartes après mélange//
				cartes[i].textProperty().bind(content.getCarteOrdonnanceur(i).getMotProperty());
				cartes[i].styleProperty().bind(content.getCarteOrdonnanceur(i).getPathImageProperty());

			} else {

				cartes[i].setVisible(false); // les cartes non-concernées -> invisible
			}
		}
	}

	@FXML
	private void clickOnCarte(ActionEvent e) { // quand on clique sur une "carte"
		Button source = ((Button) e.getSource());

		String proposition = source.getText();
		if (proposition.equals(content.getCurrentCarte().getMot())) { // si le mot est égal à celui de la carte
																		// considéré comme "courante"
			content.setScore(content.getScore() + 2);
			etiquettes[content.getCurrent()].setVisible(true); // on dévoile ce mot dans la Word Bank
			content.nextCurrent(); // on passe à la carte suivante
		}

		else {

			content.setScore(content.getScore() - 1);
			content.wrongAnswer();

		}
		if (content.gameOver()) {
			gameDataController.getLabelNote().setText(String.valueOf(content.getLetterNote(content.calculerNote())));
			PauseTransition wait = new PauseTransition(Duration.seconds(1)); // pause de une seconde sans blocage

			wait.setOnFinished((a) -> {

				content.setGameOver(true); // sinon on termine
				jouerSonGameOver();

			});

			wait.play();
		}
	}

	@FXML
	private void playSound(ActionEvent e) { // Si on appuie sur le bouton "Sound"
		try {
			SoundBox.playSound(getClass().getResource(content.getCurrentCarte().getPathSon()).toURI().toString()); // on
																													// joue
																													// le
																													// son
																													// correspondant
																													// à
																													// la
																													// carte
																													// courrante
		} catch (URISyntaxException e1) {

			e1.printStackTrace();
		}
	}

}
