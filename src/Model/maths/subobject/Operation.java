
/*
 *************************************
 ***  � Maxence V�rit� 2018 **********
 *************************************
 */

package Model.maths.subobject;

import java.util.concurrent.ThreadLocalRandom;

import Model.content.Difficulte;
import Model.content.StringAffichable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Operation implements BoundedValue, StringAffichable {

	private int a, b;
	private StringProperty operator, asString;
	private int min, max;

	public Operation(Difficulte dif) {
		this.asString = new SimpleStringProperty();
		this.operator = new SimpleStringProperty();
		setOperation(dif);

	}

	// GETTERS //

	// PROPERTY //
	public StringProperty getOperatorProperty() {
		return operator;
	}
	// VALUE //

	public String getOperator() {
		return operator.get();
	}

	public int getMinValue() {
		return min;
	}

	public int getMaxValue() {
		return max;
	}

	public void setMax(int nouveau) {
		max = nouveau;
	}

	public void setMin(int nouveau) {
		min = nouveau;
	}

	public int getResultat() {
		switch (getOperator()) {
		case "+":
			return getFirstOperand() + getSecondOperand();
		case "-":
			return getFirstOperand() - getSecondOperand();
		case "x":
			return getFirstOperand() * getSecondOperand();
		default:
			return 0;
		}
	}

	public int getFirstOperand() {

		return a;
	}

	public int getSecondOperand() {

		return b;
	}

	public void setFirstOperand(int nouveau) {
		a = nouveau;

	}

	public void setSecondOperand(int nouveau) {
		b = nouveau;

	}

	public IntegerProperty getResultatProperty() {
		return new SimpleIntegerProperty(getResultat());
	}

	// SETTERS//

	public void setOperation(Difficulte f) {

		switch (f) {
		case facile:

			setOperator("+");
			setMin(0);
			setMax(15);
			break;
		case moyen:

			setOperator("-");
			setMin(0);
			setMax(20);
			break;
		case difficile:

			setOperator("x");
			setMin(0);
			setMax(10);
			break;
		}

		if (getOperator().equals("-")) { // Si l'op�rateur est un "-"
			// on d�clare le second op�rand en premier
			setSecondOperand((int) ThreadLocalRandom.current().nextInt(min, max + 1));
			setFirstOperand((int) ThreadLocalRandom.current().nextInt(getSecondOperand(), max + 1)); // et le deuxi�me a
																										// la borne
																										// inf�rieur
																										// sup�rieur au
																										// premier
																										// op�rand
		} else { // sinon aucun probl�me pour les autres op�rations
			setFirstOperand((int) ThreadLocalRandom.current().nextInt(min, max + 1));
			setSecondOperand((int) ThreadLocalRandom.current().nextInt(min, max + 1));
		}

		setAsStringProperty(getFirstOperand() + " " + getOperator() + " " + getSecondOperand());

	}

	public void setAsStringProperty(String nouveau) {
		asString.set(nouveau);
	}

	public StringProperty asStringProperty() {
		return asString;
	}

	public void setOperator(String op) {
		this.operator.set(op);
	}

}
