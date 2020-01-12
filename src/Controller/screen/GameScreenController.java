
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Controller.AbstractGameController;
import Controller.subscreen.GameDataMenuController;
import Controller.subscreen.SideMenuController;
import Model.content.Difficulte;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class GameScreenController implements Initializable {

	// subcontroller //

	@FXML
	private GameDataMenuController gameDataMenuController;
	@FXML
	private SideMenuController sideMenuController;
	@FXML
	private AbstractGameController gameController;

	// subContent//
	@FXML
	private VBox gameContentBox;

	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void setCurrentContent(FXMLLoader loader, Difficulte diff) throws IOException {
		VBox subcontent = (VBox) loader.load();
		gameController = loader.getController();
		gameController.setDifficulty(diff);
		gameController.injectGameDataController(gameDataMenuController);
		gameController.createContent();
		gameContentBox.getChildren().setAll(subcontent);

	}

}
