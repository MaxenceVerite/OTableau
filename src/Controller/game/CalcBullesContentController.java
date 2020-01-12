
/*
 *************************************
 ***  � Maxence V�rit� 2018 **********
 *************************************
 */

package Controller.game;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.AbstractGameController;
import Model.maths.GameModelCalcBulles;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tool.SoundBox;

public class CalcBullesContentController extends AbstractGameController implements Initializable {

	// INJECTED COMPONENT //

	@FXML
	private Button bulle1, bulle2, bulle3, bulle4, bulle5, bulle6, bulle7, bulle8; // bulles
	@FXML
	private Button[] bulles; // tableau pour parcourir les buttons
	@FXML
	private TextField operationTF; // TextField affichant l'op�ration

	// MODELS //

	private GameModelCalcBulles gameContent;

	// INITIALIZING METHODS //

	public void initialize(URL location, ResourceBundle resources) {
		bulles = new Button[] { bulle1, bulle2, bulle3, bulle4, bulle5, bulle6, bulle7, bulle8 }; // on regroupe les
																									// bulles remplient
																									// par injection
																									// dans un tableau

	}

	public void createContent() {
		// on instancie le mod�le � la difficult� //

		gameContent = new GameModelCalcBulles(getDifficulty());

		// on lie les donn�es de GameModel au GameDataMenu //

		gameDataController.getNameLabelProperty().bind(gameContent.getNomProperty());
		gameDataController.getScoreTFProperty().bind(gameContent.getScoreProperty().asString("%.0f"));
		gameDataController.getVieTFProperty().bind(gameContent.getVieProperty().asString("%d"));

		// on lie la visibilit� de l'�cran de jeu � gameOver //

		rootVbox.visibleProperty().bind(gameContent.isGameOverProperty().not());

		// on lie les �lements graphiques aux donn�es du mod�le //

		operationTF.textProperty().bindBidirectional(gameContent.getOperation().asStringProperty()); // binding du
																										// TextField et
																										// de
																										// l'op�ration
																										// courrante

		for (int i = 0; i < bulles.length; i++) {

			if (i < gameContent.getNbBulles()) {
				bulles[i].visibleProperty().bindBidirectional(gameContent.getBulle(i).isVisibleProperty());
				bulles[i].textProperty().bindBidirectional(gameContent.getBulle(i).asStringProperty()); // binding des
																										// bulles
																										// (mod�le) et
																										// de leur
																										// bouton
																										// correspondant

			} else {
				bulles[i].setVisible(false); // on affiche pas les bulles en trop (par rapport au niveau de difficult�)
			}

		}
	}

	// GESTION DES ANIMATIONS //

	// animation des bulles //
	public void animateBulles() {

	}

	// EVENT METHODS //

	@FXML
	private void buttonClicked(ActionEvent e) { // Quand on clique sur une bulle
		Button source = ((Button) e.getSource());
		int proposition = Integer.valueOf(source.getText()); // on stocke la valeur de cette bulle

		// BRUITAGE //
		try {
			SoundBox.playSound(getClass().getResource("/sound/bruitage/br_bulle.wav").toURI().toString()); // on joue un
																											// son
																											// d'�clatement
																											// de la
																											// bulle
		} catch (URISyntaxException e1) {

			e1.printStackTrace();
		}

		if (proposition == gameContent.getOperation().getResultat()) { // Si la valeur stock�e est �gale au r�sultat de
																		// l'op�ration
			gameContent.setScore(gameContent.getScore() + 2); // on effectue le traitement pour une bonne r�ponse
			gameContent.newWave(); // on relance une vague de bulle
		}

		else {
			gameContent.wrongAnswer();// sinon on effectue le traitement pour une r�ponse fausse
			source.setVisible(false); // et on rend la bulle invisible pour ce round.
		}

		// GAME OVER ?//

		if (gameContent.gameOver()) { // PROCEDURE DE GAME OVER
			PauseTransition wait = new PauseTransition(Duration.seconds(1)); // pause de une seconde sans blocage

			wait.setOnFinished((a) -> {

				gameContent.setGameOver(true);
				jouerSonGameOver();
			});

			wait.play();
			gameDataController.getLabelNote()
					.setText(String.valueOf(gameContent.getLetterNote(gameContent.calculerNote())));
			// calcul de la note));
		}

	}

}
