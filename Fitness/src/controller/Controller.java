package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ExerciseAerobic;
import model.ExerciseStrength;
import model.Gender;

public class Controller {

	@FXML
	private TextField txtStudent, txtFirst, txtLast, txtBirthdate, txtHeight, txtWeight;
	
	@FXML
	private ChoiceBox<Gender> choiceGender;
	
	@FXML
	private Button btnLoad, btnSave, btnDelete, btnAddExercise, btnRemoveExercise;

	@FXML
	private RadioButton rbtnAerobic, rbtnStrength;
	
	@FXML
	private DatePicker dpDateBox;
	
	@FXML
	private TableView<ExerciseAerobic> tblAerobic;

	@FXML
	private TableView<ExerciseStrength> tblStrength;

	@FXML
	private TableColumn<String, ExerciseAerobic> clmNameA, clmDateA, clmMHR, clmAHR, clmDistance;

	@FXML
	private TableColumn<String, ExerciseStrength> clmNameS, clmDateS, clmReps, clmSets, clmWeight;

	@FXML
	private void initialize() throws SQLException {
		//Populate Gender Choice box
		choiceGender.getItems().setAll(Gender.values());
	}
}