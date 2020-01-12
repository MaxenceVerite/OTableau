
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.game;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.AbstractGameController;
import Model.maths.GameModelEscalier;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class EscalierContentController extends AbstractGameController implements Initializable {

	@FXML
	private TextField tf0, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9;
	private TextField[] marchesgauche;
	@FXML
	private Label label0, label1, label2, label3, label4, label5, label6, label7, label8, label9;
	private Label[] marchesdroite;

	@FXML
	private GridPane background;

	// MODEL //

	private GameModelEscalier gameContent;

	public void createContent() {

		// on initialise le modèle //

		gameContent = new GameModelEscalier(getDifficulty());

		// on lie au gameDataController //

		gameDataController.getNameLabelProperty().bind(gameContent.getNomProperty());
		gameDataController.getScoreTFProperty().bind(gameContent.getScoreProperty().asString("%.0f"));
		gameDataController.getVieTFProperty().bind(gameContent.getVieProperty().asString("%d"));

		// on lie la visibilité de l'écran de jeu à gameOver //

		rootVbox.visibleProperty().bind(gameContent.isGameOverProperty().not());

		background.setStyle(gameContent.getCurrentImageEscalier()); // on met l'image de fond //

		// on initialise la première marche qui ne suit pas la même règle que les autres
		// (elle est pré-remplie)
		marchesgauche[0].setText(gameContent.getPaireMarches(0).getMarche1().asStringProperty().get());
		for (int i = 0; i < marchesgauche.length; i++) { // pour les autres marches, on les lie aux properties du
															// modèle.
			if (i < gameContent.getEscalier().length) {
				marchesdroite[i].textProperty().bind(gameContent.getPaireMarches(i).getMarche2().asStringProperty());
				marchesgauche[i].visibleProperty()
						.bind(gameContent.getPaireMarches(i).getMarche1().isVisibleProperty());
				marchesdroite[i].visibleProperty()
						.bind(gameContent.getPaireMarches(i).getMarche2().isVisibleProperty());
				marchesgauche[i].editableProperty()
						.bind(gameContent.getPaireMarches(i).getMarche1().isCurrentMarcheProperty());

			} else { // et on affiche pas les composants en trop pour la difficulté donnée
				marchesgauche[i].setVisible(false);
				marchesdroite[i].setVisible(false);

			}

		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		marchesgauche = new TextField[] { tf0, tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9 };
		marchesdroite = new Label[] { label0, label1, label2, label3, label4, label5, label6, label7, label8, label9 };

	}

	// event methods //

	@FXML
	private void onEnter(ActionEvent ae) { // quand on valide le contenu d'un TextField
		int proposition;
		TextField source = ((TextField) ae.getSource());
		try {
			proposition = Integer.valueOf(source.getText()); // on stocke la valeur entrée
		} catch (NumberFormatException nfe) {
			proposition = -1;
		} // Si aucune valeur n'a été entrée, on affecte une valeur arbitraitement fausse

		if (proposition == gameContent.previousMarche().getResultat()) { // Si la valeur entrée est égale au résultat de
																			// la marche précédente

			gameContent.setScore(gameContent.getScore() + 2); // on effectue le traitement pour une bonne réponse
			gameContent.getCurrentMarche().getMarche1().setValue(proposition); // et on met la valeur de cette marche
																				// dans le model

			if (gameContent.getCurrentMarchePosition() + 1 < gameContent.getEscalier().length) { // Si l'escalier n'est
																									// pas fini
				gameContent.setNextCurrentMarche(); // on passe à la marche suivante
				greatAnswerAnimation(); // on anime ce passage à la marche suivante
			} else { // sinon gameOver
				PauseTransition wait = new PauseTransition(Duration.seconds(1)); // pause de une seconde sans blocage

				wait.setOnFinished((a) -> {

					gameContent.setGameOver(true); // sinon on termine

				});

				wait.play();
				gameDataController.getLabelNote()
						.setText(String.valueOf(gameContent.getLetterNote(gameContent.calculerNote()))); // on met la
																											// note
				finalAnimation();
				jouerSonGameOver();
			}

		}

		else { // Si la valeur entrée n'est pas égale au résultat de la marche précédente

			gameContent.setScore(gameContent.getScore() - 1);
			gameContent.wrongAnswer();
			wrongAnswerAnimation(); // on effectue le traitement pour une mauvaise réponse.
		}

		if (gameContent.gameOver()) {
			PauseTransition wait = new PauseTransition(Duration.seconds(1)); // pause de une seconde sans blocage

			wait.setOnFinished((a) -> {

				gameContent.setGameOver(true); // sinon on termine
				jouerSonGameOver();

			});
			gameDataController.getLabelNote()
					.setText(String.valueOf(gameContent.getLetterNote(gameContent.calculerNote())));
			wait.play();
			finalAnimation();
		}
	}

	private void finalAnimation() {
		marchesgauche[gameContent.getCurrentMarchePosition()].setStyle("-fx-border-color:green;");
		gameContent.getCurrentMarche().getMarche2().setVisible(false);

	}

	private void greatAnswerAnimation() {

		background.setStyle(gameContent.getCurrentImageEscalier());
		marchesgauche[gameContent.getCurrentMarchePosition() - 1].setStyle("-fx-background-color: green;");
		marchesgauche[gameContent.getCurrentMarchePosition()].requestFocus();

	}

	private void wrongAnswerAnimation() {
		marchesgauche[gameContent.getCurrentMarchePosition()].setStyle("-fx-background-color: red;");
	}

}
