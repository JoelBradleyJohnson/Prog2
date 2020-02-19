package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.Database;
import db.Parameter;

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
	public void save() throws SQLException {
		Database db = new Database("db.cberkstresser.name");
		List<Parameter<?>> params = new ArrayList<>();
		
		// ToyID, CircuitID, ManufactureLocation, Voltage, Amperage, Resistance
		params.add(new Parameter<Integer>(toyID));
		params.add(new Parameter<Integer>(circuitID));
		params.add(new Parameter<String>(manufactureLocation));
		params.add(new Parameter<Double>(voltage));
		params.add(new Parameter<Double>(getAmperage()));
		params.add(new Parameter<Double>(resistance));
		
		db.executeSql("usp_SaveCircuit", params);		
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
	    public void load(int... id) throws SQLException {
	        Database db = new Database("db.cberkstresser.name");
	        List<Parameter<?>> params = new ArrayList<>();

	        params.add(new Parameter<Integer>(id[0]));
	        params.add(new Parameter<Integer>(id[1]));

	        ResultSet rsCircuit = db.getResultSet("usp_LoadCircuit", params);
	        if (rsCircuit.next()) {
	            toyID = rsCircuit.getInt("ToyID");
	            circuitID = rsCircuit.getInt("CircuitID");
	            voltage = rsCircuit.getDouble("Voltage");
	            resistance = rsCircuit.getDouble("Resistance");
	            manufactureLocation = rsCircuit.getString("ManufactureLocation");
	        }
	    }
}
