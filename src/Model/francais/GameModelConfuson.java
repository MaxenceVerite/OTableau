
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.francais;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Model.GameModel;
import Model.content.Difficulte;
import Model.francais.subobject.Syllabe;
import View.OTableau;
import tool.SoundBox;

public class GameModelConfuson extends GameModel {

	private Syllabe[] syllabes; // les syllabes jouables
	private ArrayList<Syllabe> modele; // modele à suivre
	private int currentProposition; // à quel element du modèle on veut comparer (iterateur)
	private boolean modeleIsPlaying; // a "true" si le modèle à déjà été écouté ce tour.

	public GameModelConfuson(Difficulte dif) {
		super(dif);
		initialise();
	}

	public Syllabe[] getSyllabes() {
		return syllabes;
	}

	// FONCTION QUI RETOURNE LA SYLLABE ASSOCIE A PARTIR DE SON ORTHOGRAPHE

	public Syllabe getSyllabeFromOrtho(String orth) {

		for (int x = 0; x < syllabes.length; x++) {
			if (syllabes[x].getOrtho().equals(orth)) {
				return syllabes[x];
			}
		}
		return null;
	}

	// FONCTION QUI REMET LE COMPTEUR A 0 //
	public void resetProposition() {
		currentProposition = 0;
	}

	// FONCTION QUI AJOUTE UNE NOUVELLE SYLLABE ALEATOIRE AU MODELE //

	public void addSyllabeModele() {
		int random = ThreadLocalRandom.current().nextInt(syllabes.length);
		modele.add(syllabes[random]);
	}

	public void nextProposition() {

		currentProposition++;

	}

	public boolean correctProposition(String s) {

		return s.equals(modele.get(currentProposition).getOrtho());

	}

	public boolean modeleIsPlaying() {
		return modeleIsPlaying;
	}

	public boolean isLastProposition() {
		return (currentProposition == modele.size() - 1);
	}

	public boolean gameOver() {
		return getVie() <= 0 || getScore() == getScoreMax() || isGameOver();
	}

	public void initialise() {
		currentProposition = 0;
		modeleIsPlaying = false;
		modele = new ArrayList<>();

		switch (getDifficulte()) {
		case facile:
			syllabes = new Syllabe[] { new Syllabe("T", "T.wav"), new Syllabe("D", "D.wav"), new Syllabe("B", "B.wav"),
					new Syllabe("P", "P.wav"), new Syllabe("V", "V.wav") };
			setScoreMax(10);
			break;

		case moyen:
			syllabes = new Syllabe[] { new Syllabe("T", "T.wav"), new Syllabe("D", "D.wav"), new Syllabe("B", "B.wav"),
					new Syllabe("P", "P.wav"), new Syllabe("V", "V.wav"), new Syllabe("SS", "SS.wav"),
					new Syllabe("Z", "Z.wav"), new Syllabe("CH", "CH.wav"), new Syllabe("K", "K.wav") };
			setScoreMax(12);
			break;
		case difficile:
			syllabes = new Syllabe[] { new Syllabe("T", "T.wav"), new Syllabe("D", "D.wav"), new Syllabe("B", "B.wav"),
					new Syllabe("P", "P.wav"), new Syllabe("V", "V.wav"), new Syllabe("SS", "SS.wav"),
					new Syllabe("Z", "Z.wav"), new Syllabe("CH", "CH.wav"), new Syllabe("K", "K.wav"),
					new Syllabe("GU", "GU.wav"), new Syllabe("YE", "YE.wav"), new Syllabe("L", "L.wav") };
			setScoreMax(14);
			break;
		default:
			break;

		}

		addSyllabeModele(); // on met le premier élement de la série à reproduire

		setVie(3);

	}

	// FONCTION POUR JOUER LE SON DU MODELE //

	public void jouerModele() {
		new Thread(new Runnable() {

			public void run() {
				for (Syllabe s : modele) {
					try {
						SoundBox.playSound(
								OTableau.class.getResource("/sound/confuson/" + s.getPathSon()).toURI().toString());
						Thread.sleep(500);
						;
					} catch (URISyntaxException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	public double calculerNote() {

		if (getVie() == 0) {
			return 0;
		}
		return getScore() - 2 * (getVieMax() - getVie());
	}

}
