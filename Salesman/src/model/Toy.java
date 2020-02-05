package model;

import java.time.LocalDateTime;

/**
 * This contains the model for the toy field.
 * @author 216280
 */
public class Toy implements IToy {

	/**
	 * Primitive data point for toy ID.
	 */
	private int toyID;

	/**
	 * Primitive data point for inspector.
	 */
	private String inspector;

	/**
	 * Primitive data point for time stamp.
	 */
	private LocalDateTime inspectionDateTime;

	/**
	 * Primitive data point for circuit 1.
	 */
	private Circuit circuit1;

	/**
	 * Primitive data point for circuit 2.
	 */
	private Circuit circuit2;

	/**
	 * Accessor for toy ID.
	 */
	@Override
	public int getToyID() {
		return toyID;
	}

	/**
	 * Accessor method for inspector.
	 */
	@Override
	public String getInspector() {
		return inspector;
	}

	/**
	 * Accessor for time stamp.
	 */
	@Override
	public LocalDateTime getInspectionDateTime() {
		return inspectionDateTime;
	}

	/**
	 *  Accessor for circuit 1.
	 */
	@Override
	public Circuit getCircuit1() {
		return circuit1;
	}

	/**
	 * Accessor for circuit 2.
	 */
	@Override
	public Circuit getCircuit2() {
		return circuit2;
	}

	/**
	 * Mutator for toy ID.
	 */
	@Override
	public void setToyID(final int pToyID) {
		toyID = pToyID;

	}

	/**
	 * Mutator for inspector.
	 */
	@Override
	public void setInspector(final String pInspector) {
		inspector = pInspector;
	}

	/**
	 * Mutator for time stamp.
	 */
	@Override
	public void setInspectionDateTime(final LocalDateTime pInspectionDateTime) {
		inspectionDateTime = pInspectionDateTime;

	}

	/**
	 * Mutator for circuit 1.
	 */
	@Override
	public void setCircuit1(final Circuit pCircuit1) {
		circuit1 = pCircuit1;
	}

	/**
	 * Mutator for circuit 2.
	 */
	@Override
	public void setCircuit2(final Circuit pCircuit2) {
		circuit2 = pCircuit2;
	}

}
