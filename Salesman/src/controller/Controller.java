package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Toy;

public class Controller {

	/**
	 * My Global Instance of toy.
	 */
	@FXML
	private Toy myToy = new Toy();

	@FXML
	private ChoiceBox<String> choiceBox1;

	@FXML
	private ChoiceBox<String> choiceBox2;

	@FXML
	private TextField txtToyID, txtInspector, txtVoltage1, txtVoltage2, txtResistance1, txtResistance2;

	@FXML
	private Button btnDelete;

	@FXML
	private Label txtSaved;

	/**
	 * Like a constructor. Whatever you want in the program on startup.
	 */
	@FXML
	void initialize() {
		// Initialize Choice Box 1.
		choiceBox1.getItems().add("United States");
		choiceBox1.getItems().add("Germany");
		choiceBox1.getItems().add("China");

		// Initialize Choice Box 2.
		choiceBox2.getItems().add("United States");
		choiceBox2.getItems().add("Germany");
		choiceBox2.getItems().add("China");
	}
	
	@FXML
	void txtSavedFade() {
		FadeTransition fader = new FadeTransition(Duration.seconds(2), txtSaved);		
		fader.setFromValue(0.0);
		fader.setToValue(1.0);
		fader.setCycleCount(1);
		fader.setAutoReverse(true);
		fader.play();
	}


	@FXML
	void handleSave(ActionEvent event) throws SQLException {

		// Set toy-only properties
		myToy.setToyID(Integer.parseInt(txtToyID.getText()));
		myToy.setInspector(txtInspector.getText());
		myToy.setInspectionDateTime(LocalDateTime.now());

		// Set circuit1 properties
		myToy.getCircuit1().setVoltage(Double.parseDouble(txtVoltage1.getText()));
		myToy.getCircuit1().setResistance(Double.parseDouble(txtResistance1.getText()));
		myToy.getCircuit1().setManufactureLocation(choiceBox1.getSelectionModel().getSelectedItem());

		// Set circuit2 properties
		myToy.getCircuit2().setVoltage(Double.parseDouble(txtVoltage2.getText()));
		myToy.getCircuit2().setResistance(Double.parseDouble(txtResistance2.getText()));
		myToy.getCircuit2().setManufactureLocation(choiceBox2.getSelectionModel().getSelectedItem());

		// Save everything
		myToy.save();
		txtSavedFade();
		btnDelete.setDisable(false);

		StringBuilder results = new StringBuilder();
		results.append("\n----------------------");
		results.append("\nToy Information");
		results.append("\nInspector Name: " + myToy.getInspector());
		results.append("\nInspection Date/Time: " + myToy.getInspectionDateTime());
		results.append("\nToyID: " + myToy.getToyID());
		results.append("\n----------------------");
		results.append("\nCircuit 1 Information");
		results.append("\nCircuit ID: " + myToy.getCircuit1().getCircuitID());
		results.append("\nVoltage: " + myToy.getCircuit1().getVoltage());
		results.append("\nAmperage: " + myToy.getCircuit1().getAmperage());
		results.append("\nResistance: " + myToy.getCircuit1().getResistance());
		results.append("\nLocation: " + myToy.getCircuit1().getManufactureLocation());
		results.append("\n----------------------");
		results.append("\nCircuit 2 Information");
		results.append("\nCircuit ID: " + myToy.getCircuit2().getCircuitID());
		results.append("\nVoltage: " + myToy.getCircuit2().getVoltage());
		results.append("\nAmperage: " + myToy.getCircuit2().getAmperage());
		results.append("\nResistance: " + myToy.getCircuit2().getResistance());
		results.append("\nLocation: " + myToy.getCircuit2().getManufactureLocation());
		System.out.println(results);
	}

	@FXML
	void handleDelete(ActionEvent event) {
		try {
			Alert myAlert = new Alert(AlertType.CONFIRMATION);
			myAlert.setTitle("Confirm Delete");
			myAlert.setHeaderText("Are you sure?");
			myAlert.setContentText("Are your sure you want to delete ToyID: " + myToy.getToyID());
			Optional<ButtonType> answer = myAlert.showAndWait();
			if (answer.isPresent() && answer.get().equals(ButtonType.OK)) {
				myToy.delete();
				Alert deleted = new Alert(AlertType.INFORMATION);
				deleted.setTitle("Deleted");
				deleted.setContentText("ToyID " + myToy.getToyID() + " deleted.");
				deleted.showAndWait();
				handleClear(null);
				btnDelete.setDisable(true);
			} else {
				Alert cancelled = new Alert(AlertType.INFORMATION);
				cancelled.setTitle("Delete Cancelled");
				cancelled.setContentText("ToyID " + myToy.getToyID() + " not deleted!");
				cancelled.showAndWait();
			}

		} catch (SQLException e) {
			// TODO Some message about an error to a user.
			e.printStackTrace();
		}
	}
	
	@FXML
	void handleClear(ActionEvent event) {
		txtVoltage1.clear();
		txtVoltage2.clear();
		txtResistance1.clear();
		txtResistance2.clear();
		txtToyID.clear();
		choiceBox1.getSelectionModel().clearSelection();
		choiceBox2.getSelectionModel().clearSelection();
	}

}
