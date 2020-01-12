
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.francais.subobject;

public class Palette {

	private int id;
	private String cssColor;

	public Palette(int id, String csscolor) {
		this.id = id;
		this.cssColor = "-fx-background-color:" + csscolor + ";";

	}

	public int getID() {
		return id;
	}

	public String getColorStyle() {
		return cssColor;
	}

}
