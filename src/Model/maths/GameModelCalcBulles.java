
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.maths;

import java.util.concurrent.ThreadLocalRandom;

import Model.GameModel;
import Model.content.Difficulte;
import Model.maths.subobject.Bulle;
import Model.maths.subobject.Operation;

public class GameModelCalcBulles extends GameModel {

	private long t_debut, t_fin;
	private Bulle[] bulles;
	private Operation operation;
	private int nbBulles;

	public GameModelCalcBulles(Difficulte dif) {

		super(dif);
		this.setNom("Calc'Bulles");
		initialise();
	}

	// GETTERS //

	public Bulle[] getBulles() {
		return bulles;
	}

	public Operation getOperation() {
		return operation;
	}

	public int getNbBulles() {
		return nbBulles;
	}

	public Bulle getBulle(int i) {
		return bulles[i];
	}

	// SETTERS //

	public void newWave() {
		int i;

		int wrongValue = 0;

		int correctBulle = (int) ThreadLocalRandom.current().nextInt(nbBulles);

		operation.setOperation(getDifficulte());

		for (i = 0; i < nbBulles; i++) { // pour chaque bulle concerné par la difficulté (nbBulles)
			if (!bulles[i].isVisible()) {
				bulles[i].setVisible(true);
			} // si elle n'est plus visible (éclatée), on l'affiche à nouveau
			if (i == correctBulle) { // bulle choisit aléatoirement qui aura la bonne valeur

				bulles[i].setValeur(operation.getResultat()); // on lui affecte le résultat de l'opération
			} else { // pour les autres bulles
				wrongValue = (int) ThreadLocalRandom.current()
						.nextInt(operation.getResultat() + operation.getMaxValue()); // on génére une valeur incorrecte
																						// allant de 0 à resultat * 2
				if (!(wrongValue == operation.getResultat())) { // si la valeur généré est différente du résultat
																// correct
					bulles[i].setValeur(wrongValue); // on l'affecte à la bulle
				} else {
					bulles[i].setValeur((int) wrongValue / 3); // sinon on divise par trois pour avoir un mauvais
																// resultat.
				}

			}
		}
	}

	public boolean gameOver() {

		return getVie() == 0 || getScore() == getScoreMax() || isGameOver();

	}

	public void initialise() {

		switch (getDifficulte()) {
		case facile:
			setVie(4);
			nbBulles = 6;

			bulles = new Bulle[nbBulles];

			break;
		case moyen:
			setVie(3);
			nbBulles = 7;

			bulles = new Bulle[nbBulles];

			break;
		case difficile:
			setVie(2);
			nbBulles = 8;
			bulles = new Bulle[nbBulles];

			break;
		}

		setScoreMax(2 * nbBulles);

		// initialiser les bulles //

		operation = new Operation(getDifficulte());
		for (int i = 0; i < nbBulles; i++) {
			bulles[i] = new Bulle();
		}

		newWave();

		t_debut = System.nanoTime();
	}

	public double calculerNote() {

		if (getVie() == 0) {
			return 0;
		}

		double penaliteTemps = 0;
		t_fin = System.nanoTime();
		long t_total = (t_fin - t_debut) / 1000000000; // nanosecond to second

		if (t_total < 15 * nbBulles) {
			if (t_total < 6 * nbBulles) {
				penaliteTemps = 1; // aucune pénalité
			}
			if (t_total >= 6 * nbBulles && t_total < 12 * nbBulles) {
				penaliteTemps = 1.5;
			}

			if (t_total >= 12 * nbBulles && t_total < 15 * nbBulles) {
				penaliteTemps = 2;
			}
		} else {
			penaliteTemps = 3;
		}

		return (getScore() + getVie() / penaliteTemps);
	}

}
