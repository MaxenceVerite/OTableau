
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.subscreen;

import java.io.IOException;
import java.net.URISyntaxException;

import com.jfoenix.controls.JFXButton;

import Controller.AbstractSubcontentController;
import Controller.screen.GameScreenController;
import View.OTableau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import tool.SoundBox;

public class GameChooserMathsController extends AbstractSubcontentController {

	@FXML
	private JFXButton buttonCalc;
	@FXML
	private JFXButton buttonEscalier;

	// LIEN CALC BULLES //

	@FXML
	private void linkCalc(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/calcbulle.wav").toURI().toString());

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/GameScreen.fxml"));
		BorderPane root = (BorderPane) loader.load();
		GameScreenController controller = loader.getController();
		FXMLLoader subloader = new FXMLLoader();
		subloader.setLocation(OTableau.class.getResource("/View/CalcBullesContent.fxml"));

		controller.setCurrentContent(subloader, getDifficulty());

		Scene scene = buttonCalc.getScene();
		scene.setRoot(root);

	}

	// LIEN ESCALIER //

	@FXML
	private void linkEscalier(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/lescalier.wav").toURI().toString());

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/GameScreen.fxml"));
		BorderPane root = (BorderPane) loader.load();
		GameScreenController controller = loader.getController();
		FXMLLoader subloader = new FXMLLoader();
		subloader.setLocation(OTableau.class.getResource("/View/EscalierContent.fxml"));

		controller.setCurrentContent(subloader, getDifficulty());
		Scene scene = buttonEscalier.getScene();
		scene.setRoot(root);

	}

}
