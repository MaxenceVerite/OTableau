
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model;

import Model.content.Difficulte;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class GameModel {

	// -- Donnée générales communes à tous les jeux -- //

	private StringProperty nom;
	private DoubleProperty score, scoreMax;
	private Difficulte difficulte;
	private BooleanProperty isGameOver;
	private IntegerProperty vie;
	private IntegerProperty vieMax;

	public GameModel(Difficulte dif) {
		nom = new SimpleStringProperty();
		score = new SimpleDoubleProperty();
		scoreMax = new SimpleDoubleProperty();
		difficulte = dif;
		isGameOver = new SimpleBooleanProperty(false);
		vie = new SimpleIntegerProperty();
		vieMax = new SimpleIntegerProperty();

	}

	// GETTERS //

	public String getNom() {
		return nom.get();
	}

	public double getScore() {
		return score.get();
	}

	public double getScoreMax() {
		return scoreMax.get();
	}

	public Difficulte getDifficulte() {
		return difficulte;
	}

	public boolean isGameOver() {
		return isGameOver.get();
	}

	public int getVie() {
		return vie.get();
	}

	public int getVieMax() {
		return vieMax.get();
	}
	// PROPERTY GETTER //

	public DoubleProperty getScoreProperty() {
		return score;
	}

	public DoubleProperty getScoreMaxProperty() {
		return scoreMax;
	}

	public BooleanProperty isGameOverProperty() {
		return isGameOver;
	}

	public IntegerProperty getVieProperty() {
		return vie;
	}

	public StringProperty getNomProperty() {
		return nom;
	}
	// SETTERS //

	public void setNom(String nouveau) {
		nom.set(nouveau);
	}

	public void setScore(double d) {
		score.set(d);
	}

	public void setDifficulte(Difficulte nouveau) {
		difficulte = nouveau;
	}

	public void setGameOver(boolean nouveau) {
		isGameOver.set(nouveau);
	}

	public void setScoreMax(int nouveau) {
		scoreMax.set(nouveau);
	}

	public void setVie(int nouveau) {
		vie.set(nouveau);
		vieMax.set(nouveau);
	}

	public void wrongAnswer() {
		vie.set(vie.get() - 1);
	}

	public char getLetterNote(double noteNumerique) {
		double max = getScoreMax();
		System.out.println(noteNumerique);
		if (noteNumerique <= 0) {
			return 'G';
		}
		if (noteNumerique > 0 && noteNumerique <= max / 4.5) {
			return 'F';
		}
		if (noteNumerique > max / 4.5 && noteNumerique <= max / 3.5) {
			return 'E';
		}
		if (noteNumerique > max / 3.5 && noteNumerique <= max / 2.75) {
			return 'D';
		}
		if (noteNumerique > max / 2.75 && noteNumerique <= max / 1.75) {
			return 'C';
		}
		if (noteNumerique > max / 1.75 && noteNumerique <= max / 1.25) {
			return 'B';
		}
		if (noteNumerique > max / 1.25) {
			return 'A';
		} else {
			return '?';
		}
	}

	// ABSTRACT METHODS //

	public abstract boolean gameOver();

	public abstract void initialise();

	public abstract double calculerNote();

}
