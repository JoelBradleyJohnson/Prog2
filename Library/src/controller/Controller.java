package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.Book;

public class Controller {

	@FXML
	private TextField txtAuthor, txtTitle, txtGenre, txtLocation;
	
	@FXML
	private Button btnSearch, btnSave;
	
	@FXML
	private TableView<Book> tblMain;
	
	@FXML
	private TableColumn clmAuthor, clmTitle, clmGenre, clmLocation;
	
	@FXML
	public void initialize() throws SQLException {
	//Initialize Table with rows
	tblMain.getItems().setAll(Book.getAll());
	//you still need the column factory to attach the columns you want to display.
	
}
