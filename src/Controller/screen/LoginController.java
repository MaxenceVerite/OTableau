
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import Model.UserModel;
import View.OTableau;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private JFXButton buttonQuit;
	@FXML
	private JFXToggleButton buttonStart;
	@FXML
	private JFXTextField TFLogin;

	// FONCTION POUR FERMER L'APPLICATION //

	@FXML
	private void quitter(ActionEvent e) {
		Platform.exit();
	}

	// FONCTION LIEN VERS L'ECRAN PRINCIPAL

	@FXML
	private void toMainScene(ActionEvent e) throws IOException {
		UserModel.setName(TFLogin.getText().trim()); // on enregistre le nom
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/MainScreen.fxml"));
		Parent root = loader.load(); // on charge l'entiereté de la vue écrite en FXML dans un panel container
		Stage window = (Stage) buttonStart.getScene().getWindow(); // on crée une nouvelle fenêtre
		window.setScene(new Scene(root)); // on y ajoute la scène avec l'ecran principal à l'intérieur
		window.setResizable(true);
		window.setFullScreenExitHint("");
		window.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); // on met cette fenêtre en plein-ecran
		window.setFullScreen(true);

	}

	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
