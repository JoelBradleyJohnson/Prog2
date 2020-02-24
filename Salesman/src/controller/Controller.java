package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private TextField txtToyID, txtInspector, txtIDT, txtVoltage1, txtVoltage2, txtResistance1, txtResistance2;

	@FXML
	private Button btnDelete;

	@FXML
	private Label txtToyIDA, txtInspectorA, txtVoltage1A, txtVoltage2A, txtResistance1A, txtResistance2A, txtMan1A,
			txtMan2A;

	/**
	 * Like a constructor. Whatever you want in the program on startup.
	 */
	@FXML
	private void initialize() {
		// Initialize Choice Box 1.
		choiceBox1.getItems().add("United States");
		choiceBox1.getItems().add("Germany");
		choiceBox1.getItems().add("China");

		// Initialize Choice Box 2.
		choiceBox2.getItems().add("United States");
		choiceBox2.getItems().add("Germany");
		choiceBox2.getItems().add("China");
		
		handleSetLanguageSpanish(null);
	}

	@FXML
	void handleSave(ActionEvent event) throws SQLException {

		if (textIsEmpty() || choiceIsEmpty()) {
			choiceIsEmpty();
			errorEmpty();
		} else {
			if (calculationFail()) {
				errorInvalidInput();
			} else {
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
		}
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
				deleted.setHeaderText("Deleted");
				deleted.setContentText("ToyID " + myToy.getToyID() + " was deleted.");
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
	void handleLoad(ActionEvent e) throws NumberFormatException, SQLException {
		try {
			myToy.load(Integer.parseInt(txtToyID.getText()));
			txtInspector.setText(myToy.getInspector());
			txtIDT.setText(myToy.getInspectionDateTime().toString());
			txtVoltage1.setText(String.valueOf(myToy.getCircuit1().getVoltage()));
			txtVoltage2.setText(String.valueOf(myToy.getCircuit2().getVoltage()));
			txtResistance1.setText(String.valueOf(myToy.getCircuit1().getResistance()));
			txtResistance2.setText(String.valueOf(myToy.getCircuit2().getResistance()));
			choiceBox1.getSelectionModel().select(myToy.getCircuit1().getManufactureLocation());
			choiceBox2.getSelectionModel().select(myToy.getCircuit2().getManufactureLocation());
			btnDelete.setDisable(false);
		} catch (RuntimeException a) {
			errorNoEntry();
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
		txtToyID.requestFocus();
	}

	/**
	 * This method scans for negative text fields.
	 * 
	 * @return returns boolean value.
	 */
	@FXML
	private boolean calculationFail() {
		boolean calculationFail = false;
		try {
			myToy.setToyID(Integer.parseInt(txtToyID.getText()));
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}

		try {
			myToy.setInspector(txtInspector.getText());
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}

		try {
			myToy.getCircuit1().setVoltage(Double.parseDouble(txtVoltage1.getText()));
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}

		try {
			myToy.getCircuit1().setResistance(Double.parseDouble(txtResistance1.getText()));
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}

		try {
			myToy.getCircuit1().setManufactureLocation(choiceBox1.getSelectionModel().getSelectedItem());
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}

		try {
			myToy.getCircuit2().setVoltage(Double.parseDouble(txtVoltage2.getText()));
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}

		try {
			myToy.getCircuit2().setResistance(Double.parseDouble(txtResistance2.getText()));
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}

		try {
			myToy.getCircuit2().setManufactureLocation(choiceBox2.getSelectionModel().getSelectedItem());
		} catch (NumberFormatException e) {
			calculationFail = true;
		} catch (InputMismatchException e) {
			calculationFail = true;
		}
		return calculationFail;
	}

	/**
	 * This method scans choice boxes for empty values.
	 * 
	 * @return returns boolean value.
	 */
	@FXML
	private boolean choiceIsEmpty() {
		boolean choiceIsEmpty = false;
		txtMan1A.setVisible(false);
		txtMan2A.setVisible(false);

		if (choiceBox1.getSelectionModel().isEmpty()) {
			choiceIsEmpty = true;
			txtMan1A.setVisible(true);
		}
		if (choiceBox2.getSelectionModel().isEmpty()) {
			choiceIsEmpty = true;
			txtMan2A.setVisible(true);
		}
		return choiceIsEmpty;
	}

	/**
	 * This method scans for empty text fields.
	 * 
	 * @return returns the boolean value.
	 */
	@FXML
	private boolean textIsEmpty() {
		boolean textIsEmpty = false;
		txtInspectorA.setVisible(false);
		txtToyIDA.setVisible(false);
		txtVoltage1A.setVisible(false);
		txtVoltage2A.setVisible(false);
		txtResistance1A.setVisible(false);
		txtResistance2A.setVisible(false);

		if (txtInspector.getText().isBlank()) {
			textIsEmpty = true;
			txtInspectorA.setVisible(true);
		}
		if (txtToyID.getText().isBlank()) {
			textIsEmpty = true;
			txtToyIDA.setVisible(true);
		}
		if (txtVoltage1.getText().isBlank()) {
			textIsEmpty = true;
			txtVoltage1A.setVisible(true);
		}
		if (txtVoltage2.getText().isBlank()) {
			textIsEmpty = true;
			txtVoltage2A.setVisible(true);
		}
		if (txtResistance1.getText().isBlank()) {
			textIsEmpty = true;
			txtResistance1A.setVisible(true);
		}
		if (txtResistance2.getText().isBlank()) {
			textIsEmpty = true;
			txtResistance2A.setVisible(true);
		}
		return textIsEmpty;
	}

	@FXML
	void errorEmpty() {
		Alert empty = new Alert(AlertType.ERROR);
		empty.setTitle("Error");
		empty.setHeaderText("Empty Field");
		empty.setContentText("One or more fields are empty. Please fill in all marked fields");
		empty.showAndWait();
	}

	@FXML
	void errorInvalidInput() {
		Alert invalid = new Alert(AlertType.ERROR);
		invalid.setTitle("Error");
		invalid.setHeaderText("Invalid Entry");
		invalid.setContentText(
				"Text and special characters are only allowed in Inspector field. Please enter numbers only.");
		invalid.showAndWait();
	}

	@FXML
	void errorNoEntry() {
		Alert invalid = new Alert(AlertType.ERROR);
		invalid.setTitle("Error");
		invalid.setHeaderText("Need Entry");
		invalid.setContentText("There must be a valid input in the load box to load an application.");
		invalid.showAndWait();
	}

	@FXML
	void handleSetLanguageEnglish(ActionEvent event) {
		setFieldsWithTranslations("en", "US");
	}

	@FXML
	void handleSetLanguageSpanish(ActionEvent event) {
		setFieldsWithTranslations("es", "MX");
	}

	private void setFieldsWithTranslations(String language, String region) {
		Locale myLocale = new Locale(language, region);

		ResourceBundle rb = ResourceBundle.getBundle("Translation", myLocale);
		//Set all fields that need translations
		btnDelete.setText(rb.getString("delete"));
		//TODO: ADD THE OTHERS LATER
	}
}
