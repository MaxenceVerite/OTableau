
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.francais.subobject;

import java.util.concurrent.ThreadLocalRandom;

import com.google.common.collect.HashBiMap;

import Model.content.Difficulte;

public class GrilleColoriage {

	private MotClasse[][] grille; // grille de mot ayant une classe grammaticale
	private HashBiMap<ClasseGrammaticale, Palette> tableAssociation; // table d'association entre les classes
																		// grammaticales, l'ID et la couleur;
	private int[][] matriceConcordance; // matrice chargée dans un fichier pour savoir où placer les mots pour respecter
										// un dessin.
	private MotClasse determinant, nom, verbe, adjectif, adverbe; // à remplacer par des pool de mot au moment venu
	private Palette[] palettes;

	public GrilleColoriage(Difficulte dif) {
		initialise(dif);
	}

	private void initialise(Difficulte dif) {

		grille = new MotClasse[10][10]; // On crée une matrice 10x10 rempli de mot ayant une classe grammaticale
		tableAssociation = HashBiMap.create(); // on initialise la HashBiMap, une HashMap bi dictionnelle
		construireGrille(dif);

	}

	// GETTERS //

	public MotClasse[][] getGrille() {
		return grille;
	}

	public MotClasse getMot(int y, int x) {
		return grille[y][x];
	}

	public int getNbCouleur() {
		return palettes.length;
	}

	private void construireGrille(Difficulte dif) {
		// temporaire (à charger dans un fichier)//

		switch (dif) {
		case facile:

			// MATRICE //

			matriceConcordance = new int[][] { { 0, 0, 0, 0, 0, 1, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 }, { 2, 0, 2, 0, 2, 2, 2, 2, 2, 0 },
					{ 2, 2, 2, 0, 2, 2, 2, 2, 2, 0 }, { 0, 2, 0, 0, 2, 2, 2, 1, 2, 0 },
					{ 0, 2, 0, 0, 2, 2, 2, 2, 2, 0 }, { 0, 2, 2, 2, 2, 2, 2, 0, 0, 0 },
					{ 0, 2, 2, 2, 2, 2, 2, 2, 2, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

			// TABLE ASSOCIATION DES COULEURS ET CLASSE GRAMMATICALE.

			palettes = new Palette[3];

			palettes[0] = new Palette(0, "#56e4f7");
			palettes[1] = new Palette(1, "#2f1eaa");
			palettes[2] = new Palette(2, "#96937c");
			shufflePalette();

			/*
			 * ON DEFINIT UNE PALETTE ENTRE CHAQUE NB ET LA COULEUR PAR LEQUEL IL FAUT
			 * REMPLIR LA CASE DANS LEQUEL CE NUMERO FIGURE(plus tard il faudra la charger
			 * aussi)
			 */

			tableAssociation.put(ClasseGrammaticale.determinant, palettes[0]);
			tableAssociation.put(ClasseGrammaticale.nom, palettes[1]);
			tableAssociation.put(ClasseGrammaticale.verbe, palettes[2]);

			// mots à charger //

			determinant = new MotClasse("le", ClasseGrammaticale.determinant);
			nom = new MotClasse("chien", ClasseGrammaticale.nom);
			verbe = new MotClasse("manger", ClasseGrammaticale.verbe);

			for (int y = 0; y < grille.length; y++) {
				for (int x = 0; x < grille.length; x++) {

					switch (matriceConcordance[y][x]) {

					case 0:
						setMot(0, y, x);
						break;
					case 1:

						setMot(1, y, x);
						break;

					case 2:

						setMot(2, y, x);
						break;
					default:

						break;
					}
				}
			}
			break;
		case moyen:

			matriceConcordance = new int[][] { { 0, 3, 3, 3, 3, 3, 0, 0, 0, 0 }, { 3, 3, 1, 1, 1, 3, 3, 0, 0, 0 },
					{ 0, 2, 1, 3, 1, 1, 0, 0, 0, 0 }, { 2, 2, 1, 1, 1, 1, 0, 0, 0, 0 },
					{ 2, 2, 2, 1, 1, 0, 0, 0, 0, 3 }, { 0, 0, 1, 1, 1, 0, 1, 1, 1, 3 },
					{ 0, 1, 1, 2, 1, 1, 1, 1, 1, 2 }, { 0, 1, 1, 1, 2, 1, 1, 1, 2, 1 },
					{ 0, 1, 1, 1, 1, 2, 2, 2, 1, 1 }, { 0, 0, 1, 1, 1, 1, 1, 1, 1, 0 } };

			palettes = new Palette[4];
			/*
			 * ON DEFINIT UNE PALETTE ENTRE CHAQUE NB ET LA COULEUR PAR LEQUEL IL FAUT
			 * REMPLIR LA CASE DANS LEQUEL CE NUMERO FIGURE(plus tard il faudra la charger
			 * aussi)
			 */
			palettes[0] = new Palette(0, "#47aeb7");
			palettes[1] = new Palette(1, "#f9f348");
			palettes[2] = new Palette(2, "#c18b2c");
			palettes[3] = new Palette(3, "#000000");
			shufflePalette();

			// ON MELANGE POUR QUE CHAQUE NUMERO/COULEUR SOIT ASSOCIE A UNE CLASSE
			// GRAMMATICALE ALEATOIRE (rejouabilité)

			tableAssociation.put(ClasseGrammaticale.determinant, palettes[0]);
			tableAssociation.put(ClasseGrammaticale.nom, palettes[1]);
			tableAssociation.put(ClasseGrammaticale.verbe, palettes[2]);
			tableAssociation.put(ClasseGrammaticale.adjectif, palettes[3]);

			// MOT CHOISI ARBITRAITEMENT POUR L'EXEMPLE LE TEMPS DE JOINDRE LA BASE DE
			// DONNEE //

			determinant = new MotClasse("le", ClasseGrammaticale.determinant);
			nom = new MotClasse("chien", ClasseGrammaticale.nom);
			verbe = new MotClasse("manger", ClasseGrammaticale.verbe);
			adjectif = new MotClasse("beau", ClasseGrammaticale.adjectif);

			for (int y = 0; y < grille.length; y++) {
				for (int x = 0; x < grille.length; x++) {

					switch (matriceConcordance[y][x]) {

					case 0:
						setMot(0, y, x);
						break;
					case 1:

						setMot(1, y, x);
						break;

					case 2:

						setMot(2, y, x);
						break;

					case 3:

						setMot(3, y, x);
						break;
					default:
						break;
					}
				}
			}
			break;
		case difficile:

			// MATRICE //

			matriceConcordance = new int[][] { { 0, 0, 1, 1, 1, 1, 1, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 0, 2, 2, 2, 3, 3, 2, 3, 0, 0 }, { 2, 3, 2, 3, 3, 3, 2, 3, 3, 3 },
					{ 2, 3, 2, 2, 3, 3, 3, 2, 3, 3 }, { 2, 2, 3, 3, 3, 3, 2, 2, 2, 2 },
					{ 0, 0, 3, 3, 3, 3, 3, 3, 3, 0 }, { 0, 1, 1, 4, 1, 1, 1, 1, 0, 0 },
					{ 1, 1, 1, 4, 1, 1, 4, 1, 1, 1 }, { 1, 1, 1, 4, 4, 4, 4, 1, 1, 1 } };

			// TABLE ASSOCIATION DES COULEURS ET CLASSE GRAMMATICALE.

			palettes = new Palette[5];
			/*
			 * ON DEFINIT UNE PALETTE ENTRE CHAQUE NB ET LA COULEUR PAR LEQUEL IL FAUT
			 * REMPLIR LA CASE DANS LEQUEL CE NUMERO FIGURE(plus tard il faudra la charger
			 * aussi)
			 */
			palettes[0] = new Palette(0, "#88beea");
			palettes[1] = new Palette(1, "#ed1410");
			palettes[2] = new Palette(2, "#542f09");
			palettes[3] = new Palette(3, "#f7d3af");
			palettes[4] = new Palette(4, "#133cf2");
			shufflePalette();
			// ON MELANGE POUR QUE CHAQUE NUMERO/COULEUR SOIT ASSOCIE A UNE CLASSE
			// GRAMMATICALE ALEATOIRE (rejouabilité)

			tableAssociation.put(ClasseGrammaticale.determinant, palettes[0]);
			tableAssociation.put(ClasseGrammaticale.nom, palettes[1]);
			tableAssociation.put(ClasseGrammaticale.verbe, palettes[2]);
			tableAssociation.put(ClasseGrammaticale.adjectif, palettes[3]);
			tableAssociation.put(ClasseGrammaticale.adverbe, palettes[4]);

			// mots à charger //
			determinant = new MotClasse("le", ClasseGrammaticale.determinant);
			nom = new MotClasse("chien", ClasseGrammaticale.nom);
			verbe = new MotClasse("manger", ClasseGrammaticale.verbe);
			adjectif = new MotClasse("beau", ClasseGrammaticale.adjectif);
			adverbe = new MotClasse("lentement", ClasseGrammaticale.adverbe);

			for (int y = 0; y < grille.length; y++) {
				for (int x = 0; x < grille.length; x++) {

					switch (matriceConcordance[y][x]) {

					case 0:
						setMot(0, y, x);
						break;
					case 1:

						setMot(1, y, x);
						break;

					case 2:

						setMot(2, y, x);
						break;

					case 3:

						setMot(3, y, x);
						break;

					case 4:
						setMot(4, y, x);
						break;

					default:
						break;

					}// charger dans un fichier (ou bdd)
				}
			}
		}

	}

	// FONCTION POUR MELANGER LA PALETTE DE COULEUR //

	public void shufflePalette() {

		int index; // index et stock de la valeur précedente
		Palette prec;
		for (int j = 0; j < 6; j++) { // on mélange six fois
			for (int i = 0; i < palettes.length - 1; i++) {
				index = (int) ThreadLocalRandom.current().nextInt(palettes.length);
				prec = palettes[index];
				palettes[index] = palettes[i];
				palettes[i] = prec;

			}

		}

	}

	// FONCTION LA PALETTE ASSOCIE A L'ID

	private Palette getPaletteFromID(int ID) {
		Palette retour = null;
		for (int i = 0; i < palettes.length; i++) {
			if (ID == palettes[i].getID()) {
				retour = palettes[i];
			}
		}
		return retour;
	}

	// FONCTION QUI RETOURNE LA COULEUR ASSOCIE A LA CLASSE GRAMMATICALE //
	public String getColorFromCG(String cg) {

		return tableAssociation.get(ClasseGrammaticale.valueOf(cg)).getColorStyle();
	}

	private void setMot(int ID, int y, int x) { // rempli la case x,y d'un mot ayant la classe grammaticale associé à
												// l'ID

		switch (tableAssociation.inverse().get(getPaletteFromID(ID))) {
		case determinant:
			grille[y][x] = determinant;
			break;
		case nom:
			grille[y][x] = nom;
			break;
		case verbe:
			grille[y][x] = verbe;
			break;
		case adjectif:
			grille[y][x] = adjectif;
			break;
		case adverbe:
			grille[y][x] = adverbe;
			break;
		default:
			break;
		}
	}
}
