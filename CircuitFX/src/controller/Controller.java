package controller;

import java.io.IOException;
import java.util.InputMismatchException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.Circuit;

public class Controller {

	/**
	 * This is my global instance
	 */
	Circuit myCircuit = new Circuit();

	@FXML
	private TextField txtVoltage;

	@FXML
	private TextField txtResistance;

	@FXML
	private TextField txtAmperage;

	@FXML
	void handleClick(ActionEvent event) {
		
		if (counter() != 2) {
			
		}
		
		if (txtVoltage.getText().isBlank()) { // User must want to calculate voltage.	
			getResistance();
			getAmperage();
			myCircuit.calculateVoltage();
			txtVoltage.setText(String.valueOf(myCircuit.getVoltage()));
		}

		if (txtResistance.getText().isBlank()) { // User must want to calculate resistance.
			myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
			myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
			myCircuit.calculateResistance();
			txtResistance.setText(String.valueOf(myCircuit.getResistance()));
		}

		if (txtAmperage.getText().isBlank()) { // User must want to calculate amperage.
			myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
			myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
			myCircuit.calculateAmperage();
			txtAmperage.setText(String.valueOf(myCircuit.getAmperage()));
		}

	}

	@FXML
	int counter() {
		int counter = 0;
		if (!txtResistance.getText().isBlank())
			++counter;
		if (!txtVoltage.getText().isBlank())
			++counter;
		if (!txtAmperage.getText().isBlank())
			++counter;
		return counter;
	}

	@FXML
	void getAmperage() {
		try {
			myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
		} catch (NumberFormatException e) {
			errorInput();
		}
	}
	
	@FXML
	void getVoltage() {
		try {
			myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
		} catch (NumberFormatException e) {
			errorInput();
		}
	}
	
	@FXML
	void getResistance() {
		try {
			myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
		} catch (NumberFormatException e) {
			errorInput();
		}
	}

	@FXML
	void handleClear(ActionEvent event) {
		txtVoltage.clear();
		txtAmperage.clear();
		txtResistance.clear();
		txtVoltage.requestFocus();
	}

	@FXML
	void errorInput() {
		Alert myAlert = new Alert(AlertType.ERROR);
		myAlert.setTitle("Error");
		myAlert.setContentText("Invalid input");
		myAlert.showAndWait();
	}

	@FXML
	void errorMissingInput() {
		Alert myAlert = new Alert(AlertType.ERROR);
		myAlert.setTitle("Error");
		myAlert.setContentText("Please Enter Values in two of the three boxes.");
		myAlert.showAndWait();
	}

}
