/**
 * This package contains business process logic.
 */
package bp;

/**
 * @author 216280 This class holds the necessary stuff
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
	public double getVoltage() {
		return voltage;
	}

	@Override
	public double getAmperage() {
		return amperage;
	}

	@Override
	public double getResistance() {
		return resistance;
	}

	// *******Mutators*******
	@Override
	public void setVoltage(double pVoltage) {
		voltage = pVoltage;
	}

	@Override
	public void setAmperage(double pAmperage) {
		amperage = pAmperage;
	}

	@Override
	public void setResistance(double pResistance) {
		resistance = pResistance;
	}

	// *******Calculations*******
	@Override
	public void calculateVoltage() {
		voltage = amperage * resistance;
	}

	@Override
	public void calculateAmperage() {
		amperage = voltage / resistance;
	}

	@Override
	public void calculateResistance() {
		resistance = voltage / amperage;
	}

}
