package model;

/**
 * This class contains the business logic for the program.
 * @author jjohnson
 */
public class Circuit {
	/**
	 * private field.
	 */
    private double resistance;
    
    /**
     * private field.
     */
    private double voltage;

    /**
     * This method retrieves value of amperage.
     * @return returns voltage/resistance.
     */
    public double getAmperage() {
        return voltage / resistance;
    }

    /**
     * This method retrieves value of resistance.
     * @return returns field data.
     */
    public double getResistance() {
        return resistance;
    }

    /**
     * This method retrieves value of voltage.
     * @return returns field data.
     */
    public double getVoltage() {
        return voltage;
    }

    /**
     * This method sets the value of resistance.
     * @param resistance holds input value.
     */
    public void setResistance(final double resistance) {
        this.resistance = resistance;
    }

    /**
     * This methods sets the value of voltage.
     * @param voltage holds input value. 
     */
    public void setVoltage(final double voltage) {
        this.voltage = voltage;
    }
}