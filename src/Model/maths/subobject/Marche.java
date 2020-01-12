
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.maths.subobject;

import Model.content.StringAffichable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marche implements StringAffichable {

	private int valeur;
	private StringProperty asString;
	private BooleanProperty isCurrentMarche, isVisible;

	public Marche(int value) {
		asString = new SimpleStringProperty();
		isCurrentMarche = new SimpleBooleanProperty(false);
		isVisible = new SimpleBooleanProperty(false);
		setValue(value);
	}

	public void setAsStringProperty(String nouveau) {
		asString.set(nouveau);
		;

	}

	public BooleanProperty isCurrentMarcheProperty() {
		return isCurrentMarche;
	}

	public void setCurrentMarche(boolean nouveau) {
		isCurrentMarche.set(nouveau);
	}

	public BooleanProperty isVisibleProperty() {
		return isVisible;
	}

	public void setVisible(boolean nouveau) {
		isVisible.set(nouveau);
	}

	public int getValue() {
		return valeur;
	}

	public void setValue(int nouveau) {
		valeur = nouveau;
		setAsStringProperty(String.valueOf(nouveau));
	}

	public StringProperty asStringProperty() {
		return asString;
	}

}
