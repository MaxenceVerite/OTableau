
/*
 *************************************
 ***  � Maxence V�rit� 2018 **********
 *************************************
 */

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.AbstractGameController;
import Model.francais.GameModelConfuson;
import View.OTableau;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.util.Duration;
import tool.SoundBox;

public class ConfusonContentController extends AbstractGameController implements Initializable {

	@FXML
	private Button bd, bch, bss, bz, bg, bk, bt, bye, bl, bv, bb, bp; // touches du xylophone
	private Button[] buzzers; // tableau de touches du xylophone

	@FXML
	private Button buttonModele;

	private GameModelConfuson content;

	public void createContent() {
		content = new GameModelConfuson(getDifficulty());

		// on lie les donn�es du jeu au menu de droite //

		gameDataController.getNameLabelProperty().bind(content.getNomProperty());
		gameDataController.getScoreTFProperty().bind(content.getScoreProperty().asString("%.0f"));
		gameDataController.getVieTFProperty().bind(content.getVieProperty().asString("%d"));

		// on lie la visibilit� de l'�cran de jeu � gameOver //

		rootVbox.visibleProperty().bind(content.isGameOverProperty().not());

		for (int i = 0; i < buzzers.length; i++) {
			if (i < content.getSyllabes().length) {

				buzzers[i].textProperty().bind(content.getSyllabes()[i].getOrthoProperty());
			} else {
				buzzers[i].setVisible(false);
			}

		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		buzzers = new Button[] { bt, bd, bb, bp, bv, bss, bz, bch, bk, bg, bye, bl };

	}

	// PROCEDURE LORSQU'ON APPUIE SUR UNE TOUCHE DU XYLOPHONE //

	@FXML
	private void jouerTouche(ActionEvent e) throws URISyntaxException {

		if (!content.modeleIsPlaying()) { // si le mod�le (son) n'est pas encore en cours

			Button source = ((Button) e.getSource());
			String proposition = source.getText();

			// on joue le son associ� � cette proposition //
			SoundBox.playSound(OTableau.class
					.getResource("/sound/confuson/" + content.getSyllabeFromOrtho(proposition).getPathSon()).toURI()
					.toString());
			if (content.correctProposition(proposition)) { // Si la proposition est celle attendue

				if (!content.isLastProposition()) { // si ce n'est pas le dernier son de la s�rie attendue
					content.nextProposition(); // on avance dans le mod�le
				} else { // sinon on a effectu� une s�rie compl�te correcte
					content.setScore(content.getScore() + 2);
					SoundBox.playSound(
							OTableau.class.getResource("/sound/confuson/applaudissement.wav").toURI().toString()); // on
																													// applaudit
					content.addSyllabeModele(); // on ajoute une syllabe � la s�rie (au mod�le)
					content.resetProposition(); // on remet le compteur � 0
					buttonModele.setVisible(true); // on remet le bouton pour �couter le mod�le � disposition
				}

			} else { // si on a faux

				content.wrongAnswer(); // on perd une vie
				content.resetProposition(); // on remet le compteur � 0
				buttonModele.setVisible(true); // on rend visible le bouton pour �couter le mod�le
			}
		}

		if (content.gameOver()) { // si c'est fini, on affiche l'�cran de gameOver et la note

			PauseTransition wait = new PauseTransition(Duration.seconds(1)); // pause de une seconde sans blocage

			wait.setOnFinished((a) -> {

				content.setGameOver(true); // sinon on termine
				jouerSonGameOver();

			});
			gameDataController.getLabelNote().setText(String.valueOf(content.getLetterNote(content.calculerNote())));
			wait.play();
		}
	}

	// FONCTION POUR JOUER LE MODELE //

	@FXML
	private void jouerModele() {
		if (!content.modeleIsPlaying()) { // Si il n'est pas d�j� lanc� (plus tr�s utile maintenant que le bouton
											// disparait certes)
			content.jouerModele(); // on joue la suite de syllabe
			buttonModele.setVisible(false); // on rend le bouton invisible jusqu'� l'erreur ou que la s�rie soit
											// compl�t�e
		}
	}

}
