package controller;

import java.io.File;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;

public class Controller {

	@FXML
	private TextField txtAuthor, txtTitle, txtGenre, txtLocation, txtRowID, txtSearch;

	@FXML
	private Button btnSave, btnDelete, btnUpdate, btnClear;

	@FXML
	private TableView<Book> tblMain;

	@FXML
	private TableColumn<String, Book> clmAuthor, clmTitle, clmGenre, clmLocation;

	Book myBook = new Book();

	@FXML
	private void initialize() throws SQLException {

		// you still need the column factory to attach the columns you want to display
		clmAuthor.setCellValueFactory(new PropertyValueFactory<String, Book>("author"));
		clmTitle.setCellValueFactory(new PropertyValueFactory<String, Book>("title"));
		clmGenre.setCellValueFactory(new PropertyValueFactory<String, Book>("genre"));
		clmLocation.setCellValueFactory(new PropertyValueFactory<String, Book>("location"));

		// Initialize Table with rows
		tblMain.getItems().setAll(Book.getAll());
	}

	@FXML
	private void tblClicked() {
		Book tempBook = tblMain.getSelectionModel().getSelectedItem();
		// tblMain.getSelectionModel().getSelectedItem().getRowID();
		if (tempBook.getAuthor() != null) {
			txtAuthor.setText(tempBook.getAuthor());
		} else {
			txtAuthor.setText("");
		}
		txtTitle.setText(tempBook.getTitle());
		txtGenre.setText(tempBook.getGenre());
		txtLocation.setText(tempBook.getLocation());
		txtRowID.setText(String.valueOf(tempBook.getRowID()));
		btnSave.setDisable(true);
	}

	@FXML
	void handleDelete(ActionEvent event) {
		if (tblMain.getSelectionModel().isEmpty() || txtRowID.getText().isBlank()) {
			Alert empty = new Alert(AlertType.ERROR);
			empty.setTitle("Error");
			empty.setHeaderText("No Row Selected");
			empty.setContentText("YOU HAVE TO SELECT SOMETHING TO DELETE IT YOU IMBECILE!!!!");
			empty.showAndWait();
		} else {
			try {
				Alert myAlert = new Alert(AlertType.CONFIRMATION);
				myAlert.setTitle("Confirm Delete");
				myAlert.setHeaderText("Are you sure?");
				myAlert.setContentText("Are your sure you wish to do this?");
				Optional<ButtonType> answer = myAlert.showAndWait();
				if (answer.isPresent() && answer.get().equals(ButtonType.OK)) {
					myBook.setRowID(tblMain.getSelectionModel().getSelectedItem().getRowID());
					myBook.delete();
					tableRfsh();
					handleClear();
				} else {
					Alert cancelled = new Alert(AlertType.INFORMATION);
					cancelled.setTitle("Delete Cancelled");
					cancelled.setHeaderText("Fool");
					cancelled.setContentText("COWARD!");
					cancelled.showAndWait();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void handleUpdate(ActionEvent event) {
		try {
			myBook.setAuthor(txtAuthor.getText());
			myBook.setTitle(txtTitle.getText());
			myBook.setGenre(txtGenre.getText());
			myBook.setLocation(txtLocation.getText());
			myBook.setRowID(tblMain.getSelectionModel().getSelectedItem().getRowID());
			myBook.update();
			tableRfsh();
			handleClear();
		} catch (NullPointerException e) {
			Alert empty = new Alert(AlertType.ERROR);
			empty.setTitle("Error");
			empty.setHeaderText("No Row Selected");
			empty.setContentText("Update what?");
			empty.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleSave(ActionEvent event) {
		try {
			myBook.setAuthor(txtAuthor.getText());
			myBook.setTitle(txtTitle.getText());
			myBook.setGenre(txtGenre.getText());
			myBook.setLocation(txtLocation.getText());
			myBook.create();
			tableRfsh();
			handleClear();
		} catch (SQLIntegrityConstraintViolationException e) {
			Alert empty = new Alert(AlertType.ERROR);
			empty.setTitle("Error");
			empty.setHeaderText("Nothing To Save");
			empty.setContentText("Are you braindead? Enter something to save.");
			empty.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleSearch() {
		try {
			tblMain.getItems().setAll(Book.search(txtSearch.getText()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handleRickey() {
		// This gets the path to the project and the audio file for the sound effect
        String path = new File("").getAbsolutePath() + "\\Villager_idle1.wav";
        // Make a File object with a path to the audio file.
        File sound = new File(path);
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(sound);
            Clip c = AudioSystem.getClip();
            c.open(ais); // Clip opens AudioInputStream
            c.start(); // Start playing audio
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	@FXML
	void tableRfsh() throws SQLException {
		tblMain.getItems().setAll(Book.getAll());
	}

	@FXML
	void handleClear() {
		txtAuthor.setText("");
		txtTitle.setText("");
		txtGenre.setText("");
		txtLocation.setText("");
		txtSearch.setText("");
		txtRowID.setText("");
		btnSave.setDisable(false);
	}

}