
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.maths;

import java.util.concurrent.ThreadLocalRandom;

import Model.GameModel;
import Model.content.Difficulte;
import Model.maths.subobject.BoundedValue;
import Model.maths.subobject.PaireMarches;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModelEscalier extends GameModel implements BoundedValue {

	private PaireMarches[] marches;

	private IntegerProperty currentMarche;
	private int min, max;
	private String[] imageEscalier;

	public GameModelEscalier(Difficulte dif) {
		super(dif);
		this.setNom("L'Escalier");
		this.currentMarche = new SimpleIntegerProperty(1);

		initialise();

	}

	// GETTERS & SETTERS //

	public PaireMarches[] getEscalier() {
		return marches;
	}

	public void setMarches(PaireMarches[] marches) {
		this.marches = marches;
	}

	public IntegerProperty getCurrentMarcheProperty() {
		return currentMarche;
	}

	public int getCurrentMarchePosition() {
		return currentMarche.get();
	}

	public PaireMarches getCurrentMarche() {
		return marches[getCurrentMarchePosition()];
	}

	public PaireMarches getPaireMarches(int i) {
		return marches[i];
	}

	public void setCurrentMarche(int currentMarche) {
		this.currentMarche.set(currentMarche);

		getCurrentMarche().getMarche1().setCurrentMarche(true);

	}

	public PaireMarches getNextPaireMarches() {
		return marches[getCurrentMarchePosition() + 1];
	}

	public void setNextCurrentMarche() {

		getCurrentMarche().getMarche1().setCurrentMarche(false);

		setCurrentMarche(getCurrentMarchePosition() + 1);
		getCurrentMarche().getMarche1().setVisible(true);
		getCurrentMarche().getMarche2().setVisible(true);
	}

	public PaireMarches previousMarche() {
		return marches[getCurrentMarchePosition() - 1];
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

	public String getCurrentImageEscalier() {
		return "-fx-background-image: url(\"" + imageEscalier[currentMarche.get()] + "\");";
	}

	// GameModel methods

	public boolean gameOver() {

		return (getCurrentMarchePosition() == getEscalier().length) || getVie() == 0 || getScore() == getScoreMax();

	}

	public void initialise() {

		switch (getDifficulte()) {

		case facile:
			imageEscalier = new String[] { "/images/Escalier/animation/1.png", "/images/Escalier/animation/2.png",
					"/images/Escalier/animation/3.png", "/images/Escalier/animation/4.png",
					"/images/Escalier/animation/5.png", "/images/Escalier/animation/6.png" };

			setMarches(new PaireMarches[6]);
			setScoreMax(10);
			setVie(3);
			setMin(0);
			setMax(10);
			break;

		case moyen:
			imageEscalier = new String[] { "/images/Escalier/animation/1.png", "/images/Escalier/animation/2.png",
					"/images/Escalier/animation/3.png", "/images/Escalier/animation/4.png",
					"/images/Escalier/animation/5.png", "/images/Escalier/animation/6.png",
					"/images/Escalier/animation/7.png", "/images/Escalier/animation/8.png", };

			setMarches(new PaireMarches[8]);
			setScoreMax(14);
			setMin(5);
			setMax(20);
			setVie(2);
			break;

		case difficile:
			imageEscalier = new String[] { "/images/Escalier/animation/1.png", "/images/Escalier/animation/2.png",
					"/images/Escalier/animation/3.png", "/images/Escalier/animation/4.png",
					"/images/Escalier/animation/5.png", "/images/Escalier/animation/6.png",
					"/images/Escalier/animation/7.png", "/images/Escalier/animation/8.png",
					"/images/Escalier/animation/9.png", "/images/Escalier/animation/10.png" };

			setMarches(new PaireMarches[10]);
			setScoreMax(18);
			setMin(10);
			setMax(30);
			setVie(2);
			break;
		}

		marches[0] = new PaireMarches(getMinValue(), getMaxValue() + 1);

		for (int i = 1; i < getEscalier().length; i++) {

			marches[i] = new PaireMarches(getMinValue(), getMaxValue() + 1);

		}

		marches[0].getMarche1().setValue((ThreadLocalRandom.current().nextInt(min, max + 1)));
		marches[0].getMarche1().setVisible(true);
		marches[0].getMarche2().setVisible(true);
		marches[1].getMarche1().setVisible(true);
		marches[1].getMarche2().setVisible(true);

		this.setCurrentMarche(1);

	}

	public double calculerNote() {
		if (getVie() == 0) {
			return getScore() / 3;
		}

		return getScore() - 2 * (getVieMax() - getVie());

	}

}
