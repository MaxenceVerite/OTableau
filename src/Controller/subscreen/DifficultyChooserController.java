
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller.subscreen;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSlider;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class DifficultyChooserController implements Initializable {

	@FXML
	private JFXSlider difficultySlider;

	private DoubleProperty currentDifficulty;

	public void initialize(URL arg0, ResourceBundle arg1) {

		currentDifficulty = difficultySlider.valueProperty();

	}

	public DoubleProperty getCurrentDifficultyProperty() {
		return currentDifficulty;
	}

}
