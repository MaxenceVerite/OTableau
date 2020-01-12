
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.multijoueur;

import java.util.concurrent.ThreadLocalRandom;

import Model.content.Difficulte;
import Model.multijoueur.subobject.CarteMemory;

public class GameModelMemory extends GameModelMultijoueur {

	CarteMemory[] cartes;
	String[] images;

	public GameModelMemory(Difficulte dif) {
		super(dif);
		this.setNom("Memory");
		initialise();

	}

	public CarteMemory getCarte(int i) {
		return cartes[i];
	}

	public CarteMemory[] getCartes() {
		return cartes;
	}

	public boolean gameOver() {

		return ((getScoreJ2() + getScore()) >= getScoreMax() || isGameOver());
	}

	public void initialise() {

		switch (getDifficulte()) {
		case facile:
			images = new String[] { "/images/Memory/cartes/abeille.png", "/images/Memory/cartes/coccinelle.png",
					"/images/Memory/cartes/elephant.png", "/images/Memory/cartes/lion.png", };
			cartes = new CarteMemory[8];
			setScoreMax(4);
			break;
		case moyen:
			images = new String[] { "/images/Memory/cartes/cygne.png", "/images/Memory/cartes/escargot.png",
					"/images/Memory/cartes/elephant.png", "/images/Memory/cartes/crocodile.png",
					"/images/Memory/cartes/panda.png", "/images/Memory/cartes/koala.png",
					"/images/Memory/cartes/sanglier.png", "/images/Memory/cartes/serpent.png",
					"/images/Memory/cartes/rhinoceros.png", "/images/Memory/cartes/hippopotame.png"

			};

			cartes = new CarteMemory[20];
			setScoreMax(10);
			break;
		case difficile:
			images = new String[] { "/images/Memory/cartes/cygne.png", "/images/Memory/cartes/escargot.png",
					"/images/Memory/cartes/ratonlaveur.png", "/images/Memory/cartes/crocodile.png",
					"/images/Memory/cartes/panda.png", "/images/Memory/cartes/koala.png",
					"/images/Memory/cartes/girafe.png", "/images/Memory/cartes/gorille.png",
					"/images/Memory/cartes/rhinoceros.png", "/images/Memory/cartes/hippopotame.png",
					"/images/Memory/cartes/mouflon.png", "/images/Memory/cartes/oursblanc.png",
					"/images/Memory/cartes/oursbrun.png", "/images/Memory/cartes/requin.png" };
			cartes = new CarteMemory[28];
			setScoreMax(14);
			break;
		default:
			break;
		}

		int j = 0;

		for (int i = 0; i < cartes.length; i++) {
			cartes[i] = new CarteMemory(String.valueOf(j), images[j]);
			if (i % 2 == 1) {
				j = j + 1;
			}
		}

		shuffle();

		setScoreMax(cartes.length / 2);

	}

	public void shuffle() {
		int index; // index et stock de la valeur précedente
		CarteMemory prec;
		for (int j = 0; j < 6; j++) { // on mélange six fois
			for (int i = 0; i < cartes.length - 1; i++) {
				index = (int) ThreadLocalRandom.current().nextInt(cartes.length);
				prec = cartes[index];
				cartes[index] = cartes[i];
				cartes[i] = prec;

			}
		}
	}

	@Override
	public double calculerNote() {
		return 100;
	}

}
