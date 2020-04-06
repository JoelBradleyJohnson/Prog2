package controller;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ExerciseAerobic;
import model.ExerciseStrength;
import model.Gender;
import model.Person;

public class Controller {

	// Global Instances
	private Person myPerson = new Person();

	private ExerciseAerobic myAerobic = new ExerciseAerobic();

	private ExerciseStrength myStrength = new ExerciseStrength();

	@FXML
	private TextField txtStudent, txtFirst, txtLast, txtBirthdate, txtExerciseSeconds, txtHeight, txtWeight, txtName,
			txtMHRSets, txtAHRReps, txtDistanceWeight;

	@FXML
	private Label labelMHR, labelAHR, labelDistance, labelSets, labelReps, labelWeightLifted;

	@FXML
	private TextArea txtInfo;

	@FXML
	private ChoiceBox<Gender> choiceGender;

	@FXML
	private Button btnDelete, btnAddExercise, btnRemoveExercise;

	@FXML
	private RadioButton rbtnAerobic, rbtnStrength;

	@FXML
	private DatePicker dpExerciseDate, dpBirthdate;

	@FXML
	private TableView<ExerciseAerobic> tblAerobic;

	@FXML
	private TableView<ExerciseStrength> tblStrength;

	@FXML
	private TableColumn<Duration, ExerciseAerobic> clmEXSA;

	@FXML
	private TableColumn<Duration, ExerciseStrength> clmEXSS;

	@FXML
	private TableColumn<String, ExerciseAerobic> clmSIDA, clmNameA, clmDateA, clmMHR, clmAHR, clmDistance;

	@FXML
	private TableColumn<String, ExerciseStrength> clmSIDS, clmNameS, clmDateS, clmReps, clmSets, clmWeight;

	@FXML
	private void initialize() throws SQLException {
		// Populate Gender Choice box
		choiceGender.getItems().setAll(Gender.values());

		clmSIDA.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
		clmDateA.setCellValueFactory(new PropertyValueFactory<>("ExerciseDate"));
		clmNameA.setCellValueFactory(new PropertyValueFactory<>("ExerciseName"));
		clmEXSA.setCellValueFactory(new PropertyValueFactory<>("ExerciseDuration"));
		clmMHR.setCellValueFactory(new PropertyValueFactory<>("MaxHeartRate"));
		clmAHR.setCellValueFactory(new PropertyValueFactory<>("AverageHeartRate"));
		clmDistance.setCellValueFactory(new PropertyValueFactory<>("Distance"));

		clmSIDS.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
		clmDateS.setCellValueFactory(new PropertyValueFactory<>("ExerciseDate"));
		clmNameS.setCellValueFactory(new PropertyValueFactory<>("ExerciseName"));
		clmEXSS.setCellValueFactory(new PropertyValueFactory<>("ExerciseDuration"));
		clmSets.setCellValueFactory(new PropertyValueFactory<>("Sets"));
		clmReps.setCellValueFactory(new PropertyValueFactory<>("Reps"));
		clmWeight.setCellValueFactory(new PropertyValueFactory<>("WeightLifted"));
	}

	@FXML
	private void tblStrengthClicked() {
		ExerciseStrength tempPerson = tblStrength.getSelectionModel().getSelectedItem();
		txtName.setText(tempPerson.getExerciseName());
		txtExerciseSeconds.setText(String.valueOf(tempPerson.getExerciseDuration()));
		txtMHRSets.setText(String.valueOf(tempPerson.getSets()));
		txtAHRReps.setText(String.valueOf(tempPerson.getReps()));
		dpExerciseDate.setValue(tempPerson.getExerciseDate());
		txtDistanceWeight.setText(String.valueOf(tempPerson.getWeightLifted()));
		myStrength.setStudentID(Integer.parseInt(txtStudent.getText()));
		myStrength.setExerciseDate(dpExerciseDate.getValue());
		myStrength.setExerciseName(txtName.getText());
	}

	@FXML
	private void tblAerobicClicked() {
		ExerciseAerobic tempPerson = tblAerobic.getSelectionModel().getSelectedItem();
		txtName.setText(tempPerson.getExerciseName());
		txtExerciseSeconds.setText(String.valueOf(tempPerson.getExerciseDuration()));
		txtMHRSets.setText(String.valueOf(tempPerson.getMaxHeartRate()));
		txtAHRReps.setText(String.valueOf(tempPerson.getAverageHeartRate()));
		dpExerciseDate.setValue(tempPerson.getExerciseDate());
		txtDistanceWeight.setText(String.valueOf(tempPerson.getDistance()));
		myAerobic.setStudentID(Integer.parseInt(txtStudent.getText()));
		myAerobic.setExerciseDate(dpExerciseDate.getValue());
		myAerobic.setExerciseName(txtName.getText());
	}

	@FXML
	void handleAddExercise(ActionEvent event) throws SQLException {

		if (rbtnStrength.isSelected()) {
			myStrength.setStudentID(Integer.parseInt(txtStudent.getText()));
			myStrength.setExerciseDate(dpExerciseDate.getValue());
			myStrength.setExerciseName(txtName.getText());
			myStrength.setExerciseDuration(Duration.ofSeconds(Long.parseLong(txtExerciseSeconds.getText())));
			myStrength.setSets(Integer.parseInt(txtMHRSets.getText()));
			myStrength.setReps(Integer.parseInt(txtAHRReps.getText()));
			myStrength.setWeightLifted(Double.parseDouble(txtDistanceWeight.getText()));
			myStrength.save();
		} else if (rbtnAerobic.isSelected()) {
			myAerobic.setStudentID(Integer.parseInt(txtStudent.getText()));
			myAerobic.setExerciseDate(dpExerciseDate.getValue());
			myAerobic.setExerciseName(txtName.getText());
			myAerobic.setExerciseDuration(Duration.ofSeconds(Long.parseLong(txtExerciseSeconds.getText())));
			myAerobic.setMaxHeartRate(Integer.parseInt(txtMHRSets.getText()));
			myAerobic.setAverageHeartRate(Integer.parseInt(txtAHRReps.getText()));
			myAerobic.setDistance(Double.parseDouble(txtDistanceWeight.getText()));
			myAerobic.save();
		}
		loadTables();
	}

	@FXML
	void handleRemoveExercise(ActionEvent event) throws SQLException {
		if (rbtnStrength.isSelected()) {
			myStrength.delete(myStrength.getStudentID(), myStrength.getExerciseDate(), myStrength.getExerciseName());
			System.out.println(myStrength.getStudentID() + "blah " + myStrength.getExerciseDate() + "booie " + myStrength.getExerciseName());
		} else if (!rbtnStrength.isSelected()) {
			myAerobic.delete(myAerobic.getStudentID(), myAerobic.getExerciseDate(), myAerobic.getExerciseName());
			System.out.println(myAerobic.getStudentID() + "blach " + myAerobic.getExerciseDate() + "ha " + myAerobic.getExerciseName());
		}
		loadTables();
		txtInfo.setText("Exercise Deleted");
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
			txtInfo.clear();
			btnDelete.setDisable(false);
			loadTables();
			txtInfo.setText("Student " + myPerson.getStudentID() + " loaded.");
		} catch (IllegalArgumentException e) {
			txtInfo.setText("Student " + txtStudent.getText() + " could not be found.");
		} catch (RuntimeException e) {
			txtInfo.setText("What have you done?");
		} catch (SQLException e) {
			txtInfo.setText("Student " + txtStudent.getText() + " could not be found.");
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
		loadTables();
		txtInfo.setText("Student " + myPerson.getStudentID() + " was saved.");
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
				clearStudent();
				txtInfo.clear();
			} else {
				Alert cancelled = new Alert(AlertType.INFORMATION);
				cancelled.setTitle("Delete Cancelled");
				cancelled.setHeaderText("Fool");
				cancelled.setContentText("COWARD!");
				cancelled.showAndWait();
			}
		} catch (NumberFormatException e) {
			txtInfo.setText("You must have a student to delete them. Idiot...");
		} catch (SQLException e) {
			txtInfo.setText("You did something weird");
		} catch (RuntimeException e) {
			txtInfo.setText("That student dosen't exist.");
		}
	}

	@FXML
	void handleAerobic(ActionEvent event) {
		tblAerobic.setDisable(false);
		tblStrength.setDisable(true);
		rbtnStrength.setSelected(false);
		enableExercise();
		labelSets.setVisible(false);
		labelReps.setVisible(false);
		labelWeightLifted.setVisible(false);
		labelMHR.setVisible(true);
		labelAHR.setVisible(true);
		labelDistance.setVisible(true);
	}

	@FXML
	void handleStrength(ActionEvent event) {
		tblAerobic.setDisable(true);
		tblStrength.setDisable(false);
		rbtnAerobic.setSelected(false);
		enableExercise();
		labelMHR.setVisible(false);
		labelAHR.setVisible(false);
		labelDistance.setVisible(false);
		labelSets.setVisible(true);
		labelReps.setVisible(true);
		labelWeightLifted.setVisible(true);
	}

	@FXML
	void handleClearStudent(ActionEvent event) throws SQLException {
		clearStudent();
	}

	@FXML
	private void enableExercise() {
		txtName.setDisable(false);
		dpExerciseDate.setDisable(false);
		txtExerciseSeconds.setDisable(false);
		txtMHRSets.setDisable(false);
		txtAHRReps.setDisable(false);
		txtDistanceWeight.setDisable(false);
		btnAddExercise.setDisable(false);
		btnRemoveExercise.setDisable(false);
	}

	@FXML
	private void loadTables() throws SQLException {
		myPerson.clearAllExercises();
		myPerson.refreshTables();
		tblAerobic.getItems().setAll(myPerson.getAerobicsExercises());
		tblStrength.getItems().setAll(myPerson.getStrengthExercises());
	}

	@FXML
	private void clearStudent() throws SQLException {
		myPerson.setBirthdate(null);
		myPerson.setFirstName(null);
		myPerson.setLastName(null);
		myPerson.setGender(null);
		myPerson.setHeight(0);
		myPerson.setWeight(0);
		myPerson.setStudentID(0);
		txtStudent.clear();
		txtFirst.clear();
		txtLast.clear();
		txtHeight.clear();
		txtWeight.clear();
		dpBirthdate.setValue(null);
		choiceGender.getSelectionModel().clearSelection();
		loadTables();
	}
}