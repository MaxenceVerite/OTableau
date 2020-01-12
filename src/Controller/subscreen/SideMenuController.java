
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.subscreen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import Model.UserModel;
import View.OTableau;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import tool.SoundBox;

public class SideMenuController implements Initializable {

	@FXML
	private JFXTextField nameLabel;
	@FXML
	private UserModel user;
	@FXML
	private JFXButton buttonMenu;
	@FXML
	private JFXSlider sliderSon;

	public void initialize(URL arg0, ResourceBundle arg1) {
		startBgMusic(); // démarre la musique de background
		sliderSon.valueProperty().bindBidirectional(SoundBox.getVolumeProperty()); // bind du son et du slider son
		nameLabel.textProperty().bind(UserModel.getNameProperty()); // bind du nom du joueur au label nom
	}

	// EVENT METHODS //

	@FXML
	private void quitter(ActionEvent e) {

		Platform.exit();

	}

	// FONCTION POUR RETOURNER AU MENU PRINCIPAL

	@FXML
	private void retourMenu(ActionEvent e) throws IOException {
		Scene scene = buttonMenu.getScene();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/MainScreen.fxml"));

		Parent root = loader.load();
		scene.setRoot(root);
	}

	// FONCTION POUR LANCER LA MUSIQUE DE FOND //

	private void startBgMusic() {
		try {
			if (!SoundBox.isPlayingMusic) {
				SoundBox.playBackgroundMusic(
						getClass().getResource("/sound/musique/backgroundmusic.mp3").toURI().toString());
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}
}
