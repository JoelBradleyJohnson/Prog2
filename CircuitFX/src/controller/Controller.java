package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller {

	@FXML
	void handleClick(ActionEvent event) {
		Alert myAlert = new Alert(AlertType.INFORMATION);
		myAlert.setTitle("Christian");
		myAlert.setContentText("Christian is a cookie boi with a lot of cookies.");
		myAlert.showAndWait();
	}

}
