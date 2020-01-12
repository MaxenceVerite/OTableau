
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.maths.subobject;

import Model.content.StringAffichable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bulle implements StringAffichable {

	private IntegerProperty valeur;
	private DoubleProperty x, y;
	private BooleanProperty isVisible;
	private StringProperty asString;

	public Bulle(int value, double x, double y) {
		valeur = new SimpleIntegerProperty(value);
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
		this.isVisible = new SimpleBooleanProperty(false);

	}

	public Bulle() {
		valeur = new SimpleIntegerProperty();
		x = new SimpleDoubleProperty();
		y = new SimpleDoubleProperty();
		this.isVisible = new SimpleBooleanProperty(false);
		this.asString = new SimpleStringProperty();
	}

	// GETTERS //

	// PROPERTY //

	public IntegerProperty getValeurProperty() {
		return valeur;
	}

	public StringProperty asStringProperty() {
		return asString;
	}

	public DoubleProperty getXProperty() {
		return x;
	}

	public DoubleProperty getYProperty() {
		return y;
	}

	public BooleanProperty isVisibleProperty() {
		return isVisible;
	}

	// VALUE //

	public int getValeur() {
		return valeur.get();
	}

	public boolean isVisible() {
		return isVisible.get();
	}

	// SETTERS //

	public void setValeur(int nouveau) {
		this.valeur.set(nouveau);
		setAsStringProperty(String.valueOf(getValeur()));
	}

	public void setVisible(boolean isVisible) {
		this.isVisible.set(isVisible);
	}

	public void setAsStringProperty(String nouveau) {
		asString.set(nouveau);

	}
}
