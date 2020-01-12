
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.game;

import java.net.URISyntaxException;

import Controller.AbstractGameController;
import Model.francais.GameModelPixelMagique;
import Model.francais.subobject.ClasseGrammaticale;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import tool.SoundBox;

public class PixelMagiqueContentController extends AbstractGameController {

	private GameModelPixelMagique content;
	@FXML
	private Button b00, b01, b02, b03, b04, b05, b06, b07, b08, b09, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19,
			b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32, b33, b34, b35, b36, b37, b38, b39;
	@FXML
	private Button b40, b41, b42, b43, b44, b45, b46, b47, b48, b49, b50, b51, b52, b53, b54, b55, b56, b57, b58, b59,
			b60, b61, b62, b63, b64, b65, b66, b67, b68, b69, b70, b71, b72, b73, b74, b75, b76, b77, b78, b79;
	@FXML
	private Button b80, b81, b82, b83, b84, b85, b86, b87, b88, b89, b90, b91, b92, b93, b94, b95, b96, b97, b98, b99; // injection
																														// dans
																														// tous
																														// les
																														// buttons
																														// (énorme)
	@FXML
	private Button[][] cases; // matrice de cases
	@FXML
	private Button buttonNom, buttonVerbe, buttonDeterminant, buttonAdjectif, buttonAdverbe; // bouton de choix des
																								// couleurs
	@FXML
	private Button[] buttonPalette; // tableau des boutons de choix des couleurs
	@FXML
	private Label labelCurrentColor; // pinceau

	private StringProperty currentColorStyle; // couleur selectionnée

	public void createContent() {
		currentColorStyle = new SimpleStringProperty();
		content = new GameModelPixelMagique(getDifficulty());

		// on lie au gameDataController
		gameDataController.getNameLabelProperty().bind(content.getNomProperty());
		gameDataController.getScoreTFProperty().bind(content.getScoreProperty().asString("%.0f"));
		gameDataController.getVieTFProperty().bind(content.getVieProperty().asString("%d"));
		// ---

		cases = new Button[][] { { b00, b01, b02, b03, b04, b05, b06, b07, b08, b09 },
				{ b10, b11, b12, b13, b14, b15, b16, b17, b18, b19 },
				{ b20, b21, b22, b23, b24, b25, b26, b27, b28, b29 },
				{ b30, b31, b32, b33, b34, b35, b36, b37, b38, b39 },
				{ b40, b41, b42, b43, b44, b45, b46, b47, b48, b49 },
				{ b50, b51, b52, b53, b54, b55, b56, b57, b58, b59 },
				{ b60, b61, b62, b63, b64, b65, b66, b67, b68, b69 },
				{ b70, b71, b72, b73, b74, b75, b76, b77, b78, b79 },
				{ b80, b81, b82, b83, b84, b85, b86, b87, b88, b89 },
				{ b90, b91, b92, b93, b94, b95, b96, b97, b98, b99 } };

		binding(); // lier les composants

		fillButtonWithColor(); // associé les couleurs à leur classe grammaticale respectives

	}

	private void fillButtonWithColor() {
		buttonPalette = new Button[] { buttonNom, buttonDeterminant, buttonVerbe, buttonAdjectif, buttonAdverbe };

		for (int i = 0; i < buttonPalette.length; i++) {
			if (i < content.getColoriage().getNbCouleur()) {
				buttonPalette[i]
						.setStyle(content.getColoriage().getColorFromCG(buttonPalette[i].getText().toLowerCase()));
				// met le bouton de la couleur correspondant à sa classe grammaticale (texte du
				// bouton)
			} else {
				buttonPalette[i].setVisible(false); // rendre invisible les boutons non-concernés
			}
		}
	}

	private void binding() {
		int length = content.getColoriage().getGrille().length;
		for (int y = 0; y < length; y++) {
			for (int x = 0; x < length; x++) {

				cases[y][x].textProperty().bind(content.getColoriage().getGrille()[y][x].getOrthographeProperty());
			}
		}

		// on lie la visibilité de l'écran de jeu à gameOver //

		rootVbox.visibleProperty().bind(content.isGameOverProperty().not());

	}

	// FXML CONTROLLER METHODS //

	@FXML
	private void onClick(ActionEvent e) throws URISyntaxException { // Lorsque l'on clique sur une case du coloriage
		Button source = ((Button) e.getSource());

		if (!(currentColorStyle.get() == null)) { // si on a choisi une couleur auparavant
			source.setStyle(currentColorStyle.get()); // on peint le bouton de cette couleur
			SoundBox.playSound(getClass().getResource("/sound/bruitage/colorier.wav").toURI().toString()); // on joue le
																											// son de ce
																											// coloriage
		} else {
			SoundBox.playSound(getClass().getResource("/sound/voix/choisi_une_couleur.wav").toURI().toString()); // sinon
																													// on
																													// prévient
																													// oralement
																													// le
																													// joueur
			// qu'il n'a pas choisi de couleur
		}
	}

	@FXML
	private void selectColor(ActionEvent e) {
		Button source = ((Button) e.getSource());
		currentColorStyle.set(source.getStyle()); // on stock la couleur du bouton cliqué
		if (!labelCurrentColor.styleProperty().isBound()) { // si le pinceau n'était pas encore lié à currentColorStyle
															// (première selection de couleur)
			labelCurrentColor.styleProperty().bind(currentColorStyle); // on le fait
		}
	}

	@FXML
	private void resetColors() {
		for (int y = 0; y < cases.length; y++) {
			for (int x = 0; x < cases.length; x++) {

				cases[y][x].setStyle(""); // on vide toute les cases de leur couleur
			}
		}
	}

	@FXML
	private void submit() {

		ClasseGrammaticale cg;
		boolean noMistakes = true;
		for (int y = 0; y < cases.length; y++) { // pour chaque case du coloriage
			for (int x = 0; x < cases.length; x++) {
				cg = content.getColoriage().getMot(y, x).getClasseGrammaticale(); // on regarde la classe grammaticale
																					// du mot à cette case dans le
																					// modele
				if (!content.getColoriage().getColorFromCG(cg.toString()).equals(cases[y][x].getStyle())) { // si la cg
																											// ne
																											// correspond
																											// pas à la
																											// couleur
																											// espéré
					content.setScore(content.getScore() - 1); // mauvaise réponse
					content.wrongAnswer();
					noMistakes = false;
					cases[y][x].setStyle(""); // on vide la case de sa couleur
				}

			}
		}
		if (noMistakes = true) {

			content.setGameOver(true);

		}

		if (content.gameOver()) {
			gameDataController.getLabelNote().setText(String.valueOf(content.getLetterNote(content.calculerNote())));
			PauseTransition wait = new PauseTransition(Duration.seconds(2)); // pause de une seconde sans blocage

			wait.setOnFinished((a) -> {

				content.setGameOver(true);
				jouerSonGameOver();

			});

			wait.play();
		}
	}

}
