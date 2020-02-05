package model;

/**
 * This following class stores circuit data.
 * @author 216280
 */
public class Circuit implements ICircuit {

	/**
	 * Primitive variable for toy ID.
	 */
	private int toyID;
	/**
	 * Primitive variable for circuit ID.
	 */
	private int circuitID;
	
	/**
	 * Accessor for toy ID.
	 */
	@Override
	public int getToyID() {
		return toyID;
	}

	/**
	 * Accessor for circuit ID.
	 */
	@Override
	public int getCircuitID() {
		return circuitID;
	}

	/**
	 * Accessor for voltage.
	 */
	@Override
	public double getVoltage() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Accessor for amperage.
	 */
	@Override
	public double getAmperage() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Accessor for resistance.
	 */
	@Override
	public double getResistance() {
		return resistance;
	}

	/**
	 * Accessor manufactureLocation.
	 */
	@Override
	public String getManufactureLocation() {
		return manufactureLocation;
	}

	/**
	 * Mutator for 
	 */
	@Override
	public void setToyID(final int pToyID) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void setCircuitID(final int pCircuitID) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void setVoltage(final double pVoltage) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void setResistance(final double pResistance) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void setManufactureLocation(final String pManufactureLocation) {
		// TODO Auto-generated method stub
		
	}
	
}
