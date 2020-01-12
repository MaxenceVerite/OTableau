
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.subscreen;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class GameDataMenuController implements Initializable {

	@FXML
	private JFXButton buttonRejouer;
	@FXML
	private Label noteLabel, gameIssueLabel, gameNameLabel;
	@FXML
	private JFXTextField vieTF, scoreTF;
	private BooleanProperty rejouer;

	public void initialize(URL location, ResourceBundle resources) {
		rejouer = new SimpleBooleanProperty();
		buttonRejouer.setDisable(true);
	}

	// ACCES AUX COMPOSANTS ET AUX PROPERTIES DU GAME DATA MENU //

	public Label getLabelNote() {
		return noteLabel;
	}

	public StringProperty getVieTFProperty() {
		return vieTF.textProperty();
	}

	public StringProperty getScoreTFProperty() {
		return scoreTF.textProperty();
	}

	public StringProperty getNameLabelProperty() {
		return gameNameLabel.textProperty();
	}

	public Label getGameIssueLabel() {
		return gameIssueLabel;
	}

	public BooleanProperty getRejouerProperty() {
		return rejouer;
	}

	@FXML
	private void rejouer() {
		buttonRejouer.setDisable(true);
		rejouer.set(true);
	}
}
