
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

public class GameChooserMultiController extends AbstractSubcontentController {

	@FXML
	private JFXButton buttonMemory;
	@FXML
	private JFXButton buttonPendu;

	// LIEN MEMORY //

	@FXML
	private void linkMemory(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/le_memory.wav").toURI().toString());

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/GameScreen.fxml"));
		BorderPane root = (BorderPane) loader.load();
		GameScreenController controller = loader.getController();
		FXMLLoader subloader = new FXMLLoader();
		subloader.setLocation(OTableau.class.getResource("/View/MemoryContent.fxml"));

		controller.setCurrentContent(subloader, getDifficulty());

		Scene scene = buttonMemory.getScene();
		scene.setRoot(root);

	}

	// LIEN PENDU //

	@FXML
	private void linkPendu(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/le_pendu.wav").toURI().toString());

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/GameScreen.fxml"));
		BorderPane root = (BorderPane) loader.load();
		GameScreenController controller = loader.getController();
		FXMLLoader subloader = new FXMLLoader();
		subloader.setLocation(OTableau.class.getResource("/View/PenduContent.fxml"));

		controller.setCurrentContent(subloader, getDifficulty());
		Scene scene = buttonPendu.getScene();
		scene.setRoot(root);

	}

}
