
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.langues.subobject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CarteVocabulaire {

	private StringProperty mot;
	private StringProperty pathImage;
	private StringProperty pathSon;

	public CarteVocabulaire() {
		this.mot = new SimpleStringProperty(" ");
		this.pathImage = new SimpleStringProperty(" ");
		this.pathSon = new SimpleStringProperty(" ");
	}

	public CarteVocabulaire(String nom, String pathImage, String pathSon) {
		this.mot = new SimpleStringProperty(nom);
		this.pathImage = new SimpleStringProperty();
		this.setPathImage(pathImage);
		this.pathSon = new SimpleStringProperty(pathSon);
	}

	// MOT //

	public String getMot() {
		return mot.get();
	}

	public StringProperty getMotProperty() {
		return mot;
	}

	public void setMot(String nouveau) {
		mot.set(nouveau);
	}

	// pathImage //

	public String getPathImage() {
		return "-fx-background-image:url(\"/images/vocabulary/cartes/" + pathImage.get() + "\");"
				+ "-fx-background-repeat: stretch; \n" + "-fx-background-size: 100% 100%; \n"
				+ "-fx-background-position: center center;";
	}

	public StringProperty getPathImageProperty() {
		return pathImage;
	}

	public void setPathImage(String newpath) {
		pathImage.set("-fx-background-image:url(\"/images/vocabulary/cartes/" + newpath + "\");");
	}

	// pathSon //

	public String getPathSon() {
		return "/sound/vocabulary/" + pathSon.get();
	}

	public StringProperty getPathSonProperty() {
		return pathSon;
	}

	public void setPathSon(String newpath) {
		pathSon.set(newpath);
	}

}
