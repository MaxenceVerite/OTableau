
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.multijoueur.subobject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CarteMemory {

	private StringProperty id;
	private StringProperty imageStyle;

	public CarteMemory(String id, String pathImage) {
		this.id = new SimpleStringProperty(id);
		this.imageStyle = new SimpleStringProperty();
		this.setImageStyle(pathImage);
	}

	public StringProperty getImageStyleProperty() {
		return imageStyle;
	}

	public void setImageStyle(String pathImage) {
		imageStyle.set("-fx-background-image:url(\"" + pathImage + "\");");
	}

	public String getID() {
		return id.get();
	}

	public void setID(String i) {
		id.set(i);
	}
}
