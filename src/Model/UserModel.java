
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserModel {

	private static StringProperty name;

	static {
		name = new SimpleStringProperty();
	}

	public static String getName() {

		return name.get();

	}

	public static StringProperty getNameProperty() {
		return name;
	}

	public static void setName(String nouv) {
		name.set(nouv);
	}

}
