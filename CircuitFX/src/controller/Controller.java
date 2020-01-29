package controller;

import java.util.InputMismatchException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.Circuit;

/**
 * This class handles the control of the UI and user interaction.
 * @author jjohnson
 *
 */
public class Controller {

	/**
	 * This is my global instance.
	 */
	private Circuit myCircuit = new Circuit();

	/**
	 * This primitive field holds voltage.
	 */
	@FXML
	private TextField txtVoltage;

	/**
	 * This primitive field holds resistance.
	 */
	@FXML
	private TextField txtResistance;

	/**
	 * This primitive field holds amperage.
	 */
	@FXML
	private TextField txtAmperage;

	/**
	 * This primitive field holds the truth value of a failed execution.
	 */
	@FXML
	private boolean calculationFail = false;

	/**
	 * This method handles the click of the calculate button.
	 * @param event This holds place of the event.
	 */
	@FXML
	void handleClick(final ActionEvent event) {

		if (counter() != 2) {
			errorMissingInput();
		} else if (txtVoltage.getText().isBlank()) { // User must want to calculate voltage.
			calculationFail = false;
			setResistance();
			setAmperage();
			myCircuit.calculateVoltage();
			if (calculationFail) {
				errorInput();
			} else {
				txtVoltage.setText(String.valueOf(myCircuit.getVoltage()));
			}
		} else if (txtResistance.getText().isBlank()) { // User must want to calculate resistance.
			calculationFail = false;
			setVoltage();
			setAmperage();
			myCircuit.calculateResistance();
			if (calculationFail) {
				errorInput();
			} else {
				txtResistance.setText(String.valueOf(myCircuit.getResistance()));
			}
		} else if (txtAmperage.getText().isBlank()) { // User must want to calculate amperage.
			calculationFail = false;
			setVoltage();
			setResistance();
			myCircuit.calculateAmperage();
			if (calculationFail) {
				errorInput();
			} else {
				errorInput();
			}
		}

	}

	/**
	 * This method counts how many fields are full.
	 * @return this returns the total count of the counter.
	 */
	@FXML
	int counter() {
		int counter = 0;
		if (!txtResistance.getText().isBlank()) {
			++counter;
		}
		if (!txtVoltage.getText().isBlank()) {
			++counter;
		}
		if (!txtAmperage.getText().isBlank()) {
			++counter;
		}
		return counter;
	}

	/**
	 * This method sets amperage in the class Circuit & will let the system know if it has a mismatch exception.
	 */
	@FXML
	void setAmperage() {
		try {
			myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}
	}

	/**
	 * This method sets voltage in the class Circuit & will let the system know if it has a mismatch exception.
	 */
	@FXML
	void setVoltage() {
		try {
			myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}
	}
	
	/**
	 * This method sets resistance in the class Circuit & will let the system know if it has a mismatch exception.
	 */
	@FXML
	void setResistance() {
		try {
			myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
		} catch (InputMismatchException e) {
			calculationFail = true;
		} catch (NumberFormatException e) {
			calculationFail = true;
		}
	}
	
	/**
	 * This method handles the clear button's functionality.
	 * @param event This parameter holds place of the clear.
	 */
	@FXML
	void handleClear(final ActionEvent event) {
		txtVoltage.clear();
		txtAmperage.clear();
		txtResistance.clear();
		txtVoltage.requestFocus();
	}

	/**
	 * This method sends an error message to the user after an invaid input (in Spanish).
	 */
	@FXML
	void errorInput() {
		Alert myAlert = new Alert(AlertType.ERROR);
		myAlert.setTitle("Error");
		myAlert.setContentText("Entrada inválida");
		myAlert.showAndWait();
		txtVoltage.requestFocus();
	}
	
	/**
	 * This method sends an error message to the user after an incorrect amount of entries (in Spanish).
	 */
	@FXML
	void errorMissingInput() {
		Alert myAlert = new Alert(AlertType.ERROR);
		myAlert.setTitle("Error");
		myAlert.setContentText("Ingrese valores en dos de los tres cuadros.");
		myAlert.showAndWait();
		txtVoltage.requestFocus();
	}

}
