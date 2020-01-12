
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.langues;

import java.util.concurrent.ThreadLocalRandom;

import Model.GameModel;
import Model.content.Difficulte;
import Model.langues.subobject.CarteVocabulaire;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameModelVocabulary extends GameModel {

	private CarteVocabulaire[] cartes;
	private IntegerProperty current;
	private int[] ordonnanceur;

	public GameModelVocabulary(Difficulte dif) {
		super(dif);
		this.setNom("Vocabulary");
		initialise();
	}

	public boolean gameOver() {

		return (current.get() >= cartes.length || getVie() == 0 || getScore() == getScoreMax());
	}

	public void initialise() {

		current = new SimpleIntegerProperty();

		switch (getDifficulte()) {
		case facile:
			ordonnanceur = new int[8];
			cartes = new CarteVocabulaire[8];
			setVie(3);
			setScoreMax(16);
			break;
		case moyen:
			ordonnanceur = new int[12];
			cartes = new CarteVocabulaire[12];
			setVie(2);
			setScoreMax(24);
			break;
		case difficile:
			ordonnanceur = new int[16];
			cartes = new CarteVocabulaire[16];
			setVie(2);
			setScoreMax(32);
			break;
		default:

			break;

		}
		loadVocabulaire();
		for (int i = 0; i < ordonnanceur.length; i++) {
			ordonnanceur[i] = i;
		}

		shuffle();
		current.set(0);

	}

	public int getCurrent() {
		return current.get();
	}

	// retourne l'ID de la carte courrante en passant par l'ordonnanceur
	public int getOrdonnanceurCurrent() {
		return ordonnanceur[current.get()];
	}

	// retourne la carte courrante
	public CarteVocabulaire getCurrentCarte() {
		return cartes[current.get()];
	}

	public CarteVocabulaire[] getCartesVocabulaire() {
		return cartes;
	}

	public CarteVocabulaire getCarte(int indice) {
		return cartes[indice];
	}

	// retourne la carte à l'indice i de l'ordonnanceur
	public CarteVocabulaire getCarteOrdonnanceur(int indice) {
		return cartes[ordonnanceur[indice]];
	}

	public void nextCurrent() {
		current.set(current.get() + 1);
	}

	// Mélange l'ordonnanceur et les cartes avec le ménage de Fisher–Yates//

	public void shuffle() {
		int index, prec; // index et stock de la valeur précedente
		CarteVocabulaire precedent;
		for (int j = 0; j < 3; j++) { // on mélange trois fois
			for (int i = 0; i < ordonnanceur.length - 1; i++) {
				index = (int) ThreadLocalRandom.current().nextInt(ordonnanceur.length);
				prec = ordonnanceur[index];
				ordonnanceur[index] = ordonnanceur[i];
				ordonnanceur[i] = prec;

			}

			for (int x = 0; x < cartes.length - 1; x++) {
				index = (int) ThreadLocalRandom.current().nextInt(cartes.length);
				precedent = cartes[index];
				cartes[index] = cartes[x];
				cartes[x] = precedent;
			}
		}
	}

	// BASE DE DONNEE //

	public void loadVocabulaire() { // fonction à remplacer lorsque la base de donnée sera crée //
		CarteVocabulaire[] pool = new CarteVocabulaire[] { new CarteVocabulaire("Blue", "blue.png", "v_blue.wav"),
				new CarteVocabulaire("Black", "black.png", "v_black.wav"),
				new CarteVocabulaire("Red", "red.png", "v_red.wav"),
				new CarteVocabulaire("Yellow", "yellow.png", "v_yellow.wav"),
				new CarteVocabulaire("Green", "green.png", "v_green.wav"),
				new CarteVocabulaire("Orange", "orange.png", "v_orange.wav"),
				new CarteVocabulaire("Brown", "brown.png", "v_brown.wav"),
				new CarteVocabulaire("Dog", "dog.png", "v_dog.wav"),
				new CarteVocabulaire("Cat", "cat.png", "v_cat.wav"),
				new CarteVocabulaire("Horse", "horse.png", "v_horse.wav"),
				new CarteVocabulaire("Fish", "fish.png", "v_fish.wav"),
				new CarteVocabulaire("Pink", "pink.png", "v_pink.wav"),
				new CarteVocabulaire("White", "white.png", "v_white.wav"),
				new CarteVocabulaire("Rabbit", "rabbit.png", "v_rabbit.wav"),
				new CarteVocabulaire("Pen", "pen.png", "v_pen.wav"),
				new CarteVocabulaire("Cow", "cow.png", "v_cow.wav"),

		};

		for (int i = 0; i < cartes.length; i++) {
			cartes[i] = pool[i];
		}
	}

	public double calculerNote() {
		if (getVie() == 0) {
			return 0;
		}
		return getScore() - 3 * (getVieMax() - getVie());
	}

}
