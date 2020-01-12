
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.content;

import javafx.beans.property.StringProperty;

public interface StringAffichable { // INTERFACE LEGEREMENT OBSOLETE APRES LA DECOUVERTE DES CONVERSIONS DE
									// PROPERTIES

	public void setAsStringProperty(String nouveau);

	public StringProperty asStringProperty();

}
