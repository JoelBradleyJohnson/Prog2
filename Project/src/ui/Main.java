/**
 * This package contains the code for the front line operations.
 */
package ui;

import java.util.Scanner;

import bp.Circuit;
import bp.ICircuit;

/**
 * @author 216280 This class holds the interaction with the user.
 */
public class Main {
	/**
	 * This constant holds the value of the user choice.
	 */
	static final int USERCHOSEVOLTAGE = 1;
	/**
	 * This constant holds the value of the user choice.
	 */
	static final int USERCHOSEAMPERAGE = 2;
	/**
	 * This constant holds the value of the user choice.
	 */
	static final int USERCHOSERESISTANCE = 3;

	/**
	 * This method holds user interface interactions.
	 * 
	 * @param args This parameter sends command line arguments to execution.
	 */
	public static void main(final String[] args) {
		Scanner keyboard = new Scanner(System.in);
		ICircuit myCircuit = new Circuit();
		int userChoice;

		System.out.println("The purpose of this program is to calculate a variable based on the user's choice.");

		System.out.println("Would you like to calculate:\n" + "1) Voltage,\n" + "2) Amperage,\n" + "or 3) Resistance?");

		userChoice = keyboard.nextInt();

		if (userChoice == USERCHOSEVOLTAGE) {
			System.out.println("Please enter amperage:");
			myCircuit.setAmperage(keyboard.nextDouble());

			System.out.println("Please enter resistance:");
			myCircuit.setResistance(keyboard.nextDouble());

			myCircuit.calculateVoltage();

			System.out.println("The voltage is " + myCircuit.getVoltage() + " volts.");

		} else if (userChoice == USERCHOSEAMPERAGE) {
			System.out.println("Please enter voltage:");
			myCircuit.setVoltage(keyboard.nextDouble());

			System.out.println("Please enter resistance:");
			myCircuit.setResistance(keyboard.nextDouble());

			myCircuit.calculateAmperage();

			System.out.println("The amperage is " + myCircuit.getAmperage() + " amps.");

		} else if (userChoice == USERCHOSERESISTANCE) {

			System.out.println("Please enter voltage:");
			myCircuit.setVoltage(keyboard.nextDouble());

			System.out.println("Please enter amperage:");
			myCircuit.setAmperage(keyboard.nextDouble());

			myCircuit.calculateResistance();

			System.out.println("The resistance is " + myCircuit.getResistance() + ".");

		} else {
			System.out.println("That input was invalid.");
		}

		keyboard.close();
	}

}
