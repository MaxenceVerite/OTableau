
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.multijoueur;

import Model.content.Difficulte;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GameModelPendu extends GameModelMultijoueur {

	private StringProperty mot;
	private StringProperty proposition;
	private String[] imagePendu;

	public GameModelPendu(Difficulte dif) {
		super(dif);
		this.setNom("Le Pendu");
		mot = new SimpleStringProperty();
		proposition = new SimpleStringProperty();
		initialise();

	}

	public boolean gameOver() {

		return ((mot.get().equals(proposition.get().replaceAll("\\s", "").trim()) || getVie() == 0));
	}

	public void initialise() {
		setVie(7);
		setMot("");

		imagePendu = new String[] { "septvie.jpg", "sixvie.jpg", "cinqvie.jpg", "quatrevie.jpg", "troisvie.jpg",
				"deuxvie.jpg", "unevie.jpg" };
	}

	public void loadMot() {
		/*
		 * ResultSet rslt=null; int nb; try { Connection con=
		 * ConnectH2DB.getConnection(); Statement st = con.createStatement();
		 * System.out.println(con.getCatalog()); rslt=
		 * st.executeQuery("Select * from Mot"); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		this.mot.set("Courgette".toUpperCase());
		toGuessForm();
	}

	public void toGuessForm() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < this.mot.length().get() - 1; i++) {
			str.append("_");
			str.append(" ");
		}
		str.append("_");

		proposition.set(str.toString());
	}

	public StringProperty getMotProperty() {
		return mot;
	}

	public StringProperty getPropositionProperty() {
		return proposition;
	}

	public boolean tryProposition(char proposition) {
		boolean isCorrectAnswer = false;

		StringBuilder str = new StringBuilder(this.proposition.get());
		for (int i = 0; i < getMot().length(); i++) {

			if (proposition == getChar(i)) {
				isCorrectAnswer = true;

				str.setCharAt(2 * i, proposition);

			}
			this.proposition.set(str.toString());
		}
		return isCorrectAnswer;
	}

	public String getMot() {
		return mot.get();
	}

	public void setMot(String nouveau) {
		mot.set(nouveau.toUpperCase());
		toGuessForm();
	}

	public char[] getMotToCharArray() {
		return mot.get().toCharArray();
	}

	public char getChar(int i) {
		return mot.get().charAt(i);
	}

	public String getCurrentImagePendu() {
		return "-fx-background-image: url(\"/images/Pendu/animation/" + imagePendu[imagePendu.length - (getVie() + 1)]
				+ "\");";
	}

	@Override
	public double calculerNote() {
		return 100;
	}

}
