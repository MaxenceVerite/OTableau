
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Controller;

import Model.content.Difficulte;

public abstract class AbstractSubcontentController {
	// CLASSE DONT HERITE TOUS LES ECRANS AYANT BESOIN D'UNE DIFFICULTE

	private Difficulte dif;

	public void setDifficulty(Difficulte d) {
		this.dif = d;
	}

	public Difficulte getDifficulty() {
		return dif;
	}

}
