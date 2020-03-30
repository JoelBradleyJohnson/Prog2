package controller;

import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.ExerciseAerobic;
import model.ExerciseStrength;
import model.Gender;
import model.Person;

public class Controller {

	@FXML
	private TextField txtStudent, txtFirst, txtLast, txtBirthdate, txtHeight, txtWeight;

	@FXML
	private ChoiceBox<Gender> choiceGender;

	@FXML
	private Button btnAddExercise, btnRemoveExercise;

	@FXML
	private RadioButton rbtnAerobic, rbtnStrength;

	@FXML
	private DatePicker dpExerciseDate, dpBirthdate;

	@FXML
	private TableView<ExerciseAerobic> tblAerobic;

	@FXML
	private TableView<ExerciseStrength> tblStrength;

	@FXML
	private TableColumn<String, ExerciseAerobic> clmNameA, clmDateA, clmMHR, clmAHR, clmDistance;

	@FXML
	private TableColumn<String, ExerciseStrength> clmNameS, clmDateS, clmReps, clmSets, clmWeight;

	Person myPerson = new Person();

	@FXML
	private void initialize() throws SQLException {
		// Populate Gender Choice box
		choiceGender.getItems().setAll(Gender.values());
	}

	@FXML
	void handleLoad(ActionEvent event) {
		try {
			myPerson.load(Integer.parseInt((txtStudent.getText())));
			txtStudent.setText(String.valueOf(myPerson.getStudentID()));
			txtFirst.setText((myPerson.getFirstName()));
			txtLast.setText((myPerson.getLastName()));
			txtHeight.setText(String.valueOf(myPerson.getHeight()));
			txtWeight.setText(String.valueOf(myPerson.getWeight()));
			dpBirthdate.setValue(myPerson.getBirthdate());
			choiceGender.getSelectionModel().select(myPerson.getGender());
		} catch (NumberFormatException e) {
			errorFormat();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleSave(ActionEvent event) throws SQLException {
		myPerson.setStudentID(Integer.parseInt(txtStudent.getText()));
		myPerson.setFirstName(txtFirst.getText());
		myPerson.setLastName(txtLast.getText());
		myPerson.setHeight(Double.parseDouble(txtHeight.getText()));
		myPerson.setWeight(Double.parseDouble(txtWeight.getText()));
		myPerson.setBirthdate(dpBirthdate.getValue());
		myPerson.setGender(choiceGender.getSelectionModel().getSelectedItem());
		myPerson.save();
		
		Alert myAlert = new Alert(AlertType.INFORMATION);
		myAlert.setTitle("Student Saved");
		myAlert.setHeaderText("You Have Saved " + myPerson.getStudentID());
		myAlert.setContentText("You have saved the life of " + myPerson.getFirstName() + myPerson.getLastName());

	}

	@FXML
	void handleDelete(ActionEvent event) {
		try {
			Alert empty = new Alert(AlertType.CONFIRMATION);
			empty.setTitle("Are You Sure?");
			empty.setHeaderText("Kill " + txtStudent.getText());
			empty.setContentText("You are about to do something very grave to student " + txtStudent.getText());
			Optional<ButtonType> answer = empty.showAndWait();
			if (answer.isPresent() && answer.get().equals(ButtonType.OK)) {
				myPerson.setStudentID(Integer.parseInt(txtStudent.getText()));
				myPerson.delete();
			} else {
				Alert cancelled = new Alert(AlertType.INFORMATION);
				cancelled.setTitle("Delete Cancelled");
				cancelled.setHeaderText("Fool");
				cancelled.setContentText("COWARD!");
				cancelled.showAndWait();
			}

		} catch (NumberFormatException e) {
			errorFormat();
		} catch (SQLException e) {
			errorNotFound();
		} catch (RuntimeException e) {
			errorNotFound();
		}
	}

	@FXML
	private void errorFormat() {
		Alert empty = new Alert(AlertType.ERROR);
		empty.setTitle("Error");
		empty.setHeaderText("Invalid Format");
		empty.setContentText("The format used in text fields is invalid. Please retry.");
		empty.showAndWait();
	}

	@FXML
	private void errorNotFound() {
		Alert empty = new Alert(AlertType.ERROR);
		empty.setTitle("Error");
		empty.setHeaderText("Item Not Found");
		empty.setContentText("You are searching for something that cannot (or shouldn't) be found.");
		empty.showAndWait();
	}
}