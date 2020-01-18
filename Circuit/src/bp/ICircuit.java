package bp;

/**
 * This interface provides the basic Ohm's law structure of a circuit.
 * 
 * @author cberkstresser
 *
 */
public interface ICircuit {
	// **********Assessors************
	/**
	 * 
	 * @return the voltage of the circuit
	 */
	double getVoltage();

	/**
	 * 
	 * @return the amperage of the circuit
	 */
	double getAmperage();

	/**
	 * 
	 * @return the resistance of the circuit
	 */
	double getResistance();

	//// **********Mutators************
	/**
	 * 
	 * @param pVoltage the voltage to set
	 */
	void setVoltage(double pVoltage);

	/**
	 * 
	 * @param pAmperage the amperage to set
	 */
	void setAmperage(double pAmperage);

	/**
	 * 
	 * @param pResistance the resistance to set
	 */
	void setResistance(double pResistance);

	//// **********Methods************
	/**
	 * Sets the voltage of the circuit using the amperage and resistance properties.
	 */
	void calculateVoltage();

	/**
	 * Sets the amperage of the circuit using the voltage and resistance properties.
	 */
	void calculateAmperage();

	/**
	 * Sets the resistance of the circuit using the voltage and amperage properties.
	 */
	void calculateResistance();
}
