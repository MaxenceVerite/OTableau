
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
import Model.UserModel;
import Model.multijoueur.GameModelMemory;
//import Model.multijoueur.ia.IAMemory;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.util.Duration;
import tool.SoundBox;

public class MemoryContentController extends AbstractGameController implements Initializable {

	@FXML
	private Button c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21,
			c22, c23, c24, c25, c26, c27; // les cartes
	private Button[] buttoncartes; // le tableau de cartes

	@FXML
	private ProgressBar pbJoueur1, pbJoueur2; // La barre de score de chaque joueur
	@FXML
	private ToggleButton tbJoueur1, tbJoueur2; // Les togglebuttons servant à savoir quel joueur doit jouer.
	@FXML
	private Button buttonJ2; // bouton servant à choisir un adversaire ordinateur ou humain
	@FXML
	private Label labelJ1;

	public GameModelMemory content;
	private Button precedent; // stockage du choix de carte précédent
	private boolean workingAnimation; // boolean servant à attendre la fin de l'animation avant qu'une action est un
										// impact sur le déroulement

	// IA DATA //

	// public IAMemory bot;

	//

	public void initialize(URL arg0, ResourceBundle arg1) {
		buttoncartes = new Button[] { c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17,
				c18, c19, c20, c21, c22, c23, c24, c25, c26, c27 };
		if (!UserModel.getName().equals("")) {
			labelJ1.setText(UserModel.getName());
		} else {
			labelJ1.setText("Joueur 1");
		}

	}

	public void createContent() {
		content = new GameModelMemory(getDifficulty());

		// binding to gameDataController //
		gameDataController.getLabelNote().setText("X");
		gameDataController.getNameLabelProperty().bind(content.getNomProperty());
		gameDataController.getScoreTFProperty().bind(content.getScoreProperty().asString("---"));
		gameDataController.getVieTFProperty().bind(content.getVieProperty().asString("---"));

		// on lie la visibilité de l'écran de jeu à gameOver //

		rootVbox.visibleProperty().bind(content.isGameOverProperty().not());

		// binding player turn to toggle button //

		tbJoueur1.selectedProperty().bind(content.getIsPlayerOneTurnProperty());
		tbJoueur2.selectedProperty().bind(content.getIsPlayerOneTurnProperty().not());

		// binding score to progressBar//

		pbJoueur1.progressProperty().bind(content.getScoreProperty().divide(content.getScoreMax()));
		pbJoueur2.progressProperty().bind(content.getScoreJ2Property().divide(content.getScoreMax()));

		// on affiche seulement les composants utiles
		for (int i = 0; i < buttoncartes.length; i++) {
			if (i < content.getCartes().length) {

			} else {
				buttoncartes[i].setVisible(false);
			}
		}

		workingAnimation = false; // aucune animation est en cours (initialisation)
	}

	@FXML
	private void switchPlayer2Humanity(ActionEvent e) { // fonction pour passé d'un joueur humain à une IA et
														// vice-versa.
		if (content.isPlayer2Human()) {
			content.setIsPlayer2Human(false);
			buttonJ2.setText("Ordinateur");
		} else {
			content.setIsPlayer2Human(true);
			buttonJ2.setText("Joueur 2");
		}
	}

	@FXML
	private void clickOnCard(ActionEvent e) throws InterruptedException, URISyntaxException {
		Button source = ((Button) e.getSource());
		if (!workingAnimation) { // s'il n'y a pas d'animation en cours

			SoundBox.playSound(getClass().getResource("/sound/bruitage/retourner-carte.wav").toURI().toString()); // bruitage
																													// de
																													// retournement
																													// de
																													// carte

			if (precedent == null) { // si c'est la première carte //
				precedent = source; // on stock la référence du bouton pour pouvoir le dé-animer
				precedent
						.setStyle(content.getCarte(Integer.valueOf(precedent.getText())).getImageStyleProperty().get());

			} else { // sinon
				if (!precedent.equals(source)) { // si on a pas recliqué sur la même carte

					if (content.getCarte(Integer.valueOf(precedent.getText())).getID()
							.equals(content.getCarte(Integer.valueOf(source.getText())).getID())) { // si on a une
																									// paire//
						// (Le texte de la carte sert d'identifiant pour savoir à quelle carte dans le
						// modèle elle est associé)
						precedent.setVisible(false); // on rend invisible la paire
						source.setVisible(false);
						precedent = null; // on deselectionne la carte précédente

						if (content.isPlayerOneTurn()) { // si c'était au tour du joueur 1
							content.setScore(content.getScore() + 1); // le joueur 1 gagne un point
						} else { // si c'était au tour du joueur 2
							content.setScoreJ2(content.getScoreJ2() + 1); // le joueur 2 gagne un point
						}
					} else { // si on a pas de paire
						workingAnimation = true; // une animation va se dérouler : on empeche le joueur de jouer pendant

						PauseTransition wait = new PauseTransition(Duration.seconds(1)); // pause de une seconde sans
																							// blocage

						source.setStyle(
								content.getCarte(Integer.valueOf(source.getText())).getImageStyleProperty().get());

						wait.setOnFinished((a) -> { // quand la pause est terminé, on effectue le traitement
							precedent.setStyle(""); // on remet les cartes de dos
							source.setStyle("");
							precedent = null; // on deselectionne le précédent
							content.switchPlayerTurn(); // tour du joueur suivant
							workingAnimation = false; // l'animation est terminée

						});

						wait.play();

					}
				}

			}
		}

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
