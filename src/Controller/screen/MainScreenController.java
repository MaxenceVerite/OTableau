
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.screen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import Controller.subscreen.SideMenuController;
import View.OTableau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import tool.SoundBox;

public class MainScreenController implements Initializable {

	// Injections //

	@FXML
	private JFXButton buttonFrancais;
	@FXML
	private JFXButton buttonMaths;
	@FXML
	private JFXButton buttonLangues;
	@FXML
	private JFXButton buttonMulti;

	// NESTED CONTROLER //

	@FXML
	private SideMenuController sideMenuController;

	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	private void linkFrancais(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/voix_francais.wav").toURI().toString());
		goTo("/View/GameChooserFrancais.fxml");

	}

	@FXML
	private void linkMaths(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/voix_maths.wav").toURI().toString());
		goTo("/View/GameChooserMaths.fxml");

	}

	@FXML
	private void linkLangues(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/voix_langues.wav").toURI().toString());
		goTo("/View/GameChooserLangues.fxml");
	}

	@FXML
	private void linkMulti(ActionEvent e) throws IOException, URISyntaxException {
		SoundBox.playSound(getClass().getResource("/sound/voix/voix_multi.wav").toURI().toString());
		goTo("/View/GameChooserMulti.fxml");
	}

	// FONCTION LIEN POUR CHARGER LE CONTENU DE L'ECRAN PRECISE EN PARAMETRES

	private void goTo(String ressource) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(OTableau.class.getResource("/View/Selection.fxml")); // on charge l'ecran de selection global
																				// des jeux
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SelectionController controller = loader.getController();
		Scene scene = (Scene) buttonMaths.getScene();
		scene.setRoot(root);
		try {
			controller.setCurrentContent(ressource); // on transmet à cet écran le contenu qu'il doit charger
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
