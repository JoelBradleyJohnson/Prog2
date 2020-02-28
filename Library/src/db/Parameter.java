package db;

/**
 * This class allows you to set up parameters to send to the data access layer
 * of the database.
 * 
 * @author cberkstresser
 * 
 * @param <T> The parameter to send to the database.
 */
public class Parameter<T> {
	/** Convert default data type value to null in the db? */
	private boolean defaultToNull;
	/** Placeholder for the parameter value. */
	private T parameterValue;

	/**
	 * Constructor. Initialize without a parameter.
	 */
	public Parameter() {

	}

	/**
	 * Constructor. Initialize with a parameter.
	 * 
	 * @param pParameter The parameter to initialize.
	 */
	public Parameter(final T pParameter) {
		parameterValue = pParameter;
		defaultToNull = true;
	}

	/**
	 * Constructor. Initialize with a parameter.
	 * 
	 * @param pParameter     The parameter to initialize.
	 * @param pDefaultToNull Does this parameter convert the default value of the
	 *                       data type to null?
	 */
	public Parameter(final T pParameter, final boolean pDefaultToNull) {
		parameterValue = pParameter;
		defaultToNull = pDefaultToNull;
	}

	/**
	 * @return Default to null value.
	 */
	public final boolean getDefaultToNull() {
		return defaultToNull;
	}

	/**
	 * 
	 * @return The parameter value.
	 */
	public final T getParameter() {
		return parameterValue;
	}

	/**
	 * Sets the default value of data types to null in the db.
	 * 
	 * @param pDefaultToNull True or false to set defaults to null.
	 */
	public final void setDefaultToNull(final boolean pDefaultToNull) {
		defaultToNull = pDefaultToNull;
	}

	/**
	 * Set the parameter value.
	 * 
	 * @param pParameter The parameter value to set.
	 */
	public final void setParameter(final T pParameter) {
		parameterValue = pParameter;
	}
}
