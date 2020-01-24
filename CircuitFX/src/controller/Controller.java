package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.Circuit;

public class Controller {

	@FXML
	private TextField txtVoltage;

	@FXML
	private TextField txtResistance;

	@FXML
	private TextField txtAmperage;

	@FXML
	void handleClick(ActionEvent event) {
		Circuit myCircuit = new Circuit();
	
		if (txtVoltage.getText().isBlank()) { //User must want to calculate voltage.
			myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
			myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
			myCircuit.calculateVoltage();
			Alert myAlert = new Alert(AlertType.INFORMATION);
			myAlert.setTitle("Voltaje");
			myAlert.setContentText(String.valueOf(myCircuit.getVoltage()));
			myAlert.showAndWait();
		}
		
		if (txtResistance.getText().isBlank()) { //User must want to calculate resistance.
			myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
			myCircuit.setAmperage(Double.parseDouble(txtAmperage.getText()));
			myCircuit.calculateResistance();
			txtResistance.setText(String.valueOf(myCircuit.getResistance()));
		}
		
		if (txtAmperage.getText().isBlank()) { //User must want to calculate amperage.
			myCircuit.setVoltage(Double.parseDouble(txtVoltage.getText()));
			myCircuit.setResistance(Double.parseDouble(txtResistance.getText()));
			myCircuit.calculateAmperage();
			txtAmperage.setText(String.valueOf(myCircuit.getAmperage()));
		}
		
	}
	
	@FXML
	void handleClear(ActionEvent event) {
		txtVoltage.clear();
		txtAmperage.clear();
		txtResistance.clear();
		txtVoltage.requestFocus();
	}

}
