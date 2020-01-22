/**
 * This package contains business process logic.
 */
package model;

/**
 * @author jjohnson This class holds the necessary stuff.
 */
public class Circuit implements ICircuit {

	// *******Primitive Variables*******
	/**
	 * Primitive variable that holds voltage.
	 */
	private double voltage;
	/**
	 * Primitive variable that holds amperage.
	 */
	private double amperage;
	/**
	 * Primitive variable that holds resistance.
	 */
	private double resistance;

	// *******Accessors********
	@Override
	public final double getVoltage() {
		return voltage;
	}

	@Override
	public final double getAmperage() {
		return amperage;
	}

	@Override
	public final double getResistance() {
		return resistance;
	}

	// *******Mutators*******
	@Override
	public final void setVoltage(final double pVoltage) {
		voltage = pVoltage;
	}

	@Override
	public final void setAmperage(final double pAmperage) {
		amperage = pAmperage;
	}

	@Override
	public final void setResistance(final double pResistance) {
		resistance = pResistance;
	}

	// *******Calculations*******
	@Override
	public final void calculateVoltage() {
		voltage = amperage * resistance;
	}

	@Override
	public final void calculateAmperage() {
		amperage = voltage / resistance;
	}

	@Override
	public final void calculateResistance() {
		resistance = voltage / amperage;
	}

}
