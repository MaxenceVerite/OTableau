
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.maths.subobject;

import java.util.concurrent.ThreadLocalRandom;

public class PaireMarches {

	private Marche marche1, marche2;

	public PaireMarches(int min, int max) {
		marche1 = new Marche(0);
		marche2 = new Marche(ThreadLocalRandom.current().nextInt(min, max + 1));

	}

	public Marche getMarche1() {
		return marche1;
	}

	public Marche getMarche2() {
		return marche2;
	}

	public int getResultat() {
		return marche1.getValue() + marche2.getValue();
	}

	public void setPaireCase(int min, int max) {
		marche1.setValue(0);
		marche2.setValue(ThreadLocalRandom.current().nextInt(min, max + 1));
	}

}
