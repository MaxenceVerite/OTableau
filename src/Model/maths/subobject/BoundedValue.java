
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package Model.maths.subobject;

public interface BoundedValue {

	public int getMinValue();

	public int getMaxValue();

	public void setMax(int nouveau);

	public void setMin(int nouveau);
}
