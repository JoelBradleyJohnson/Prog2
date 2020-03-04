package controller;

import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;

public class Controller {

	@FXML
	private TextField txtAuthor, txtTitle, txtGenre, txtLocation;

	@FXML
	private Button btnSearch, btnSave, btnDelete, btnUpdate, btnClear;

	@FXML
	private TableView<Book> tblMain;

	@FXML
	private TableColumn<String, Book> clmAuthor, clmTitle, clmGenre, clmLocation;

	Book myBook = new Book();

	@FXML
	private void initialize() throws SQLException {
		// Initialize Table with rows
		tblMain.getItems().setAll(Book.getAll());

		// you still need the column factory to attach the columns you want to display
		clmAuthor.setCellValueFactory(new PropertyValueFactory<String, Book>("author"));
		clmTitle.setCellValueFactory(new PropertyValueFactory<String, Book>("title"));
		clmGenre.setCellValueFactory(new PropertyValueFactory<String, Book>("genre"));
		clmLocation.setCellValueFactory(new PropertyValueFactory<String, Book>("location"));
	}

	@FXML
	private void tblClicked() {
		// tblMain.getSelectionModel().getSelectedItem().getRowID();
		txtAuthor.setText(tblMain.getSelectionModel().getSelectedItem().getAuthor());
		txtTitle.setText(tblMain.getSelectionModel().getSelectedItem().getTitle());
		txtGenre.setText(tblMain.getSelectionModel().getSelectedItem().getGenre());
		txtLocation.setText(tblMain.getSelectionModel().getSelectedItem().getLocation());
		btnSave.setDisable(true);
	}

	@FXML
	void handleDelete(ActionEvent event) {
		try {
			myBook.setRowID(tblMain.getSelectionModel().getSelectedItem().getRowID());
			myBook.delete();
			tableRfsh();
			handleClear();
		} catch (Exception e) {
			e.printStackTrace();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void tableRfsh() throws SQLException {
		// Initialize Table with rows
		tblMain.getItems().setAll(Book.getAll());

		// you still need the column factory to attach the columns you want to display
		clmAuthor.setCellValueFactory(new PropertyValueFactory<String, Book>("author"));
		clmTitle.setCellValueFactory(new PropertyValueFactory<String, Book>("title"));
		clmGenre.setCellValueFactory(new PropertyValueFactory<String, Book>("genre"));
		clmLocation.setCellValueFactory(new PropertyValueFactory<String, Book>("location"));
	}

	@FXML
	void handleClear() {
		txtAuthor.setText("");
		txtTitle.setText("");
		txtGenre.setText("");
		txtLocation.setText("");
		btnSave.setDisable(false);
	}

}