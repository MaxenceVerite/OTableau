
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.francais;

import Model.GameModel;
import Model.content.Difficulte;
import Model.francais.subobject.GrilleColoriage;

public class GameModelPixelMagique extends GameModel {

	private GrilleColoriage coloriage;

	public GameModelPixelMagique(Difficulte dif) {
		super(dif);
		this.setNom("Pixel Magique");
		initialise();

	}

	public GrilleColoriage getColoriage() {
		return coloriage;
	}

	public boolean gameOver() {

		return (getVie() <= 0 || isGameOver() || getScore() == getScoreMax());
	}

	public void initialise() {
		coloriage = new GrilleColoriage(getDifficulte());

		setScoreMax(100);
		setScore(100);

		switch (getDifficulte()) {
		case facile:
			setVie(20);
			break;
		case moyen:
			setVie(15);
			break;
		case difficile:
			setVie(10);
			break;
		default:
			break;

		}

	}

	@Override
	public double calculerNote() {
		if (getVie() == 0) {
			return 0;
		}
		return getScore() - 4 * (getVieMax() - getVie());
	}

}
