package controller;

import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Circuit;
import model.Toy;

public class Controller {
	
	@FXML
    private ChoiceBox<String> choiceBox1;

    @FXML
    private ChoiceBox<String> choiceBox2;
    
    @FXML
	private TextField txtToyID, txtInspector, txtVoltage1, txtVoltage2, txtResistance1, txtResistance2;
    
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
    void handleSave(ActionEvent event) {
    	Toy myToy = new Toy();
    	
    	// Set toy-only properties
    	myToy.setToyID(Integer.parseInt(txtToyID.getText()));
    	myToy.setInspector(txtInspector.getText());
    	myToy.setInspectionDateTime(LocalDateTime.now());
    	
    	//Set circuit1 properties
    	myToy.getCircuit1().setVoltage(Double.parseDouble(txtVoltage1.getText()));
    	myToy.getCircuit1().setResistance(Double.parseDouble(txtResistance1.getText()));
    	myToy.getCircuit1().setManufactureLocation(choiceBox1.getSelectionModel().getSelectedItem());
    	    	
    	//Set circuit2 properties
    	myToy.getCircuit2().setVoltage(Double.parseDouble(txtVoltage2.getText()));
    	myToy.getCircuit2().setResistance(Double.parseDouble(txtResistance2.getText()));
    	myToy.getCircuit2().setManufactureLocation(choiceBox2.getSelectionModel().getSelectedItem());
    	
    	//Save everything
    	//myToy.save();
    	
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
