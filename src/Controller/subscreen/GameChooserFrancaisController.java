
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

public class GameChooserFrancaisController extends AbstractSubcontentController {

	@FXML
	private JFXButton buttonPixel;
	@FXML
	private JFXButton buttonConfuson;

	// LIEN VERS PIXEL MAGIQUE //

	@FXML
	private void linkPixel(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/pixel_magic.wav").toURI().toString());

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/GameScreen.fxml"));
		BorderPane root = (BorderPane) loader.load();
		GameScreenController controller = loader.getController();
		FXMLLoader subloader = new FXMLLoader();
		subloader.setLocation(OTableau.class.getResource("/View/PixelMagiqueContent.fxml"));

		controller.setCurrentContent(subloader, getDifficulty());

		Scene scene = buttonPixel.getScene();
		scene.setRoot(root);

	}

	// LIEN VERS CONFUSON //

	@FXML
	private void linkConfuson(ActionEvent e) throws IOException, URISyntaxException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/GameScreen.fxml"));
		BorderPane root = (BorderPane) loader.load();
		GameScreenController controller = loader.getController();
		FXMLLoader subloader = new FXMLLoader();
		subloader.setLocation(OTableau.class.getResource("/View/ConfusonContent.fxml"));

		controller.setCurrentContent(subloader, getDifficulty());
		Scene scene = buttonConfuson.getScene();
		scene.setRoot(root);

	}

}
