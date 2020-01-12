
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.francais.subobject;

public class MotClasse extends Mot {

	private ClasseGrammaticale cg;

	public MotClasse(String s, ClasseGrammaticale classeGra) {
		super(s);
		cg = classeGra;
	}

	public ClasseGrammaticale getClasseGrammaticale() {
		return cg;
	}

}
