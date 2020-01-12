
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.multijoueur;

import Model.GameModel;
import Model.content.Difficulte;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class GameModelMultijoueur extends GameModel {

	private BooleanProperty isPlayer2Human;
	private BooleanProperty isPlayerOneTurn;
	private SimpleDoubleProperty scoreJ2;

	public GameModelMultijoueur(Difficulte dif) {
		super(dif);
		isPlayer2Human = new SimpleBooleanProperty(true);
		isPlayerOneTurn = new SimpleBooleanProperty(true);
		scoreJ2 = new SimpleDoubleProperty(0);

	}

	public BooleanProperty isPlayer2HumanProperty() {
		return isPlayer2Human;
	}

	public boolean isPlayer2Human() {
		return isPlayer2Human.get();
	}

	public void setIsPlayer2Human(boolean b) {
		isPlayer2Human.set(b);
	}

	public BooleanProperty getIsPlayerOneTurnProperty() {
		return isPlayerOneTurn;
	}

	public void switchPlayerTurn() {
		this.isPlayerOneTurn.set(!isPlayerOneTurn());
	}

	public boolean isPlayerOneTurn() {

		return isPlayerOneTurn.get();
	}

	public void setIsPlayerOneTurn(BooleanProperty isPlayerOneTurn) {
		this.isPlayerOneTurn = isPlayerOneTurn;
	}

	public DoubleProperty getScoreJ2Property() {
		return scoreJ2;
	}

	public double getScoreJ2() {
		return scoreJ2.get();
	}

	public void setScoreJ2(double d) {
		this.scoreJ2.set(d);
	}

}
