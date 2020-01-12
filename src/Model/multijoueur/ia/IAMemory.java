
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.multijoueur.ia;

import Model.content.Difficulte;

public class IAMemory {
	private int coefMemo;
	private int[] memoire;
	// ETAT QUE PEUT PRENDRE CHAQUE CASE DE LA MEMOIRE //
	// -2 : la carte n'existe plus //
	// -1 : la carte est inconnue //
	// autres : ID de la carte //
	private int[] tourMemoire; // combien de temps il lui reste à retenir la carte à cet endroit
	private int precedementJoue;
	private int prochaineProposition;
	private boolean isFirstProposition;

	public IAMemory(int tailleMemory, Difficulte dif) {
		memoire = new int[tailleMemory];
		tourMemoire = new int[tailleMemory];

		switch (dif) {
		case facile:
			coefMemo = 1;
			break;
		case moyen:
			coefMemo = 2;
			break;
		case difficile:
			coefMemo = 3;
			break;
		default:
			break;

		}
	}

	public void retenir(int position, int valeur) {
		memoire[position] = valeur;
		if (valeur >= 0) { // si c'est un ID de carte
			tourMemoire[position] = coefMemo;
		}
	}

	public void passerTempsMemoire() {
		for (int x = 0; x < tourMemoire.length; x++) {
			if (tourMemoire[x] > 0) {
				if (tourMemoire[x] == 1) { // si c'est le tour auquel on va oublier la carte
					oublier(x);
				}

				tourMemoire[x]--;
			}
		}
	}

	private void oublier(int x) {
		memoire[x] = -1;

	}

	public int getProposition() { // Fonction pour savoir où l'IA veut jouer dans MemoryContentController

		if (isFirstProposition) { // SI C'EST LA PREMIERE FOIS QU'IL JOUE
			prochaineProposition = -1; // il n'a pas encore de prochaine proposition

			for (int x = 0; x < memoire.length; x++) { // on regarde si on a déja une proposition viable en mémoire
				for (int y = 0; x < memoire.length; y++) {
					if (memoire[x] == memoire[y] && memoire[x] != -1 && memoire[x] != -2) { // si il a trouvé deux
																							// cartes qui correspondent
																							// dans sa mémoire,

						prochaineProposition = y; // il retient sa prochaine proposition
						isFirstProposition = false;
						return x; // il propose celle-ci
					}

				}
			}

			if (prochaineProposition == -1) { // SI IL N'A PAS TROUVE DE SOLUTION
				precedementJoue = propositionAleatoire(); // il retient ce qu'il a joué au premier tour pour après
				isFirstProposition = false;
				return precedementJoue;
			} // il propose cette carte jamais dévoilé pour ce tour ci.

		} else { // si c'est sa seconde proposition

			if (prochaineProposition != -1) { // si il avait une seconde proposition à proposer enregistré précedement
				int a = prochaineProposition;
				prochaineProposition = -1;
				isFirstProposition = true;
				return a; // il la propose
			} else { // sinon (s'il avait proposé une carte qu'il ne connaissait pas)

				for (int x = 0; x < memoire.length; x++) { // il verifie que la carte découverte précedemment n'a pas
															// crée de nouvelles combinaisons en mémoire
					if (memoire[x] == memoire[precedementJoue]) { // si il avait une carte correspondant à cette
																	// nouvelle carte découverte
						isFirstProposition = true;
						return x; // il propose celle-ci
					}

				}

			}
			isFirstProposition = true;
			return propositionAleatoire(); // si malgré tout ça il ne trouve pas de correspondance, il propose au second
											// tour une carte qu'il ne connait pas
		}
		return -1;
	}

	public int propositionAleatoire() {
		int retour = -1;
		for (int x = 0; x < memoire.length; x++) {
			if (memoire[x] == -1) {
				retour = x;
			}
		}
		return retour;
	}

}
