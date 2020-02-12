package model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

/**
 * This contains the model for the toy field.
 * @author 216280
 */
public class Toy implements IToy,IPermanentStorage {

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
	private Circuit circuit1 = new Circuit(1);
	/**
	 * Primitive data point for circuit 2.
	 */
	private Circuit circuit2 = new Circuit(2);

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
		circuit1.setToyID(pToyID);
		circuit2.setToyID(pToyID);
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

	/**
	 * I don't know why this exists.
	 * @throws SQLException This throws an exception to the controller.
	 */
	@Override
	public void save() throws SQLException {
		Database db = new Database("db.cberkstresser.name");
		List<Parameter<?>> params = new ArrayList<>();
		
		// ToyID, Inspector, InspectionDateTime
		params.add(new Parameter<Integer>(toyID));
		params.add(new Parameter<String>(inspector));
		params.add(new Parameter<LocalDateTime>(inspectionDateTime));
		
		db.executeSql("usp_SaveToy", params);
		
		// When the toy is saved, so is circuit 1 & 2.
		circuit1.save();
		circuit2.save();
	}

	/**
	 * I don't know why this exists.
	 */
	@Override
	public void delete() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * I don't know why this exists.
	 * @param id This parameter can accept multiple arguments (ID's) at once.
	 */
	@Override
	public void load(int... id) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
