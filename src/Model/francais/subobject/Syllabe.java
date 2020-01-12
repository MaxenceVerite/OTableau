
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.francais.subobject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Syllabe {

	private StringProperty ortho;
	private String pathSon;

	public Syllabe(String ort, String son) {
		this.ortho = new SimpleStringProperty(ort);
		this.pathSon = son;
	}

	public String getOrtho() {
		return ortho.get();
	}

	public StringProperty getOrthoProperty() {
		return ortho;
	}

	public String getPathSon() {
		return pathSon;
	}

}
