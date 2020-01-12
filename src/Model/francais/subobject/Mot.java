
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.francais.subobject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mot {

	private StringProperty orthographe;

	public Mot(String s) {
		orthographe = new SimpleStringProperty(s);
	}

	public StringProperty getOrthographeProperty() {
		return orthographe;
	}

	public String getOrthographe() {
		return orthographe.get();
	}

}
