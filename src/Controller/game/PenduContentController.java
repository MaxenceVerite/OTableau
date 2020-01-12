package Controller.game;

import java.net.URISyntaxException;

import Controller.AbstractGameController;
import Model.multijoueur.GameModelPendu;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import tool.SoundBox;

/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

public class PenduContentController extends AbstractGameController {

	@FXML
	private Label labelMot; // le mot à trou
	@FXML
	private Button buttonValider; // le bouton au centre pour valider
	@FXML
	private HBox boxChoixOption; // la "boite de choix" (humain/IA + mot)
	@FXML
	private PasswordField labelChoixMot; // le label permettant au joueur proposant de taper un mot

	private Button selection; // stockage du bouton choisi sur le clavier virtuel

	private GameModelPendu content;

	public void createContent() {
		content = new GameModelPendu(getDifficulty());
		// on lie au gameDataController //
		gameDataController.getLabelNote().setText("X");
		gameDataController.getNameLabelProperty().bind(content.getNomProperty());
		gameDataController.getScoreTFProperty().bind(content.getScoreProperty().asString("---"));
		gameDataController.getVieTFProperty().bind(content.getVieProperty().asString("---"));

		// on lie la visibilité de l'écran de jeu à gameOver //

		rootVbox.visibleProperty().bind(content.isGameOverProperty().not());

		labelMot.textProperty().bind(content.getPropositionProperty()); // on lie le label à la proposition
		try {
			SoundBox.playSound(getClass().getResource("/sound/voix/choix_amis_ordi.wav").toURI().toString());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void submit(ActionEvent e) {

		if (selection != null) { // si on a choisi une lettre avant de valider

			char proposition = selection.getText().charAt(0); // on recupère ce caractère

			if (!content.tryProposition(proposition)) { // on affiche les occurences de cette lettre (cette fonction
														// renvoit faux s'il n'y en a pas//
				wrongAnswer(); // on effectue le traitement de mauvaise réponse s'il n'y avait pas d'occurence
								// de la lettre proposée
			}

			selection.setVisible(false); // on cache la lettre pour ne pas pouvoir la rejouer inutilement

			if (content.gameOver()) {
				PauseTransition wait = new PauseTransition(Duration.seconds(1)); // pause de une seconde sans blocage

				wait.setOnFinished((a) -> {

					content.setGameOver(true);
					jouerSonGameOver();

				});

				wait.play();

			}
		}
	}

	private void wrongAnswer() {
		content.wrongAnswer(); // on perd une vie
		buttonValider.setStyle(content.getCurrentImagePendu()); // on actualise le dessin du pendu

	}

	@FXML
	private void getLettre(ActionEvent e) throws URISyntaxException {
		if (!boxChoixOption.isVisible()) {
			Button source = (Button) e.getSource();
			SoundBox.playSound(getClass().getResource("/sound/bruitage/touche_clavier_pendu.wav").toURI().toString());
			if (selection != null) { // si on a déjà selectionné une lettre auparavant
				if (selection != source) { // si cette lettre est différente de celle d'avant
					selection.setStyle(""); // on deselectionne là precedente
					selection = source; // le nouveau bouton devient la selection
					selection.setStyle("-fx-background-color: #8cdb62;"); // on anime cette selection
				}

				else { // sinon, si on a recliqué sur le même bouton //

					selection.setStyle(""); // on deselectionne//
					selection = null;

				}
			} else { // si on avait pas de selection
				selection = source; // le nouveau bouton devient la selection
				selection.setStyle("-fx-background-color: #8cdb62;");
			}
		}
	}

	@FXML
	private void loadMot() { // Si on veut que "l'ordinateur" charge un mot
		content.loadMot(); // on charge un mot dans la BDD (mot unique pour le moment)
		boxChoixOption.setVisible(false); // on enlève la "boite de choix"
	}

	@FXML
	private void validerChoixMot() {
		String mot = labelChoixMot.getText();
		if (!mot.equals("") & mot.matches("[a-zA-Z]+")) { // Si on a bien un mot qui a été entré (ajouter verification
															// chiffres etc) //
			content.setMot(mot); // on le saisi dans notre modèle
			boxChoixOption.setVisible(false); // on enlève la "boite de choix"
		}

		else {
			labelChoixMot.setText("");
		}

	}

}
