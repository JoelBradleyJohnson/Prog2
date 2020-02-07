package model;

/**
 * This following class stores circuit data.
 * @author 216280
 */
public class Circuit implements ICircuit, IPermanentStorage {

	/**
	 * Primitive variable for toy ID.
	 * We need to have this variable to track the circuits in the database.
	 */
	private int toyID;
	/**
	 * Primitive variable for circuit ID.
	 */
	private int circuitID;
	/**
	 * Primitive variable for voltage.
	 */
	private double voltage;
	/**
	 * Primitive variable for resistance.
	 */
	private double resistance;
	/**
	 * Primitive variable for manufacture location.
	 */
	private String manufactureLocation;
	
	/**
	 * This method is a constructor.
	 * @param pCircuitID This parameter does some stuff.
	 */
	public Circuit (int pCircuitID) {
		circuitID = pCircuitID;
	}
	
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
		return voltage;
	}

	/**
	 * Accessor for amperage.
	 */
	@Override
	public double getAmperage() {
		return voltage / resistance;
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
	 * Mutator for toy ID.
	 */
	@Override
	public void setToyID(final int pToyID) {
		toyID = pToyID;
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void setCircuitID(final int pCircuitID) {
		circuitID = pCircuitID;		
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void setVoltage(final double pVoltage) {
		voltage = pVoltage;		
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void setResistance(final double pResistance) {
		resistance = pResistance;
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void setManufactureLocation(final String pManufactureLocation) {
		manufactureLocation = pManufactureLocation;
	}
	
	/**
	 * This method does stuff.
	 */
	@Override
	public void save() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void delete() {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	/**
	 * This method does stuff.
	 */
	@Override
	public void load(int... id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
}
