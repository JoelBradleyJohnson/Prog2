package controller;

import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Circuit;
import model.Toy;

public class Controller {

    @FXML
    void handleSave(ActionEvent event) {
    	Toy myToy = new Toy();
    	
    	// Set toy-only properties
    	myToy.setToyID(txtToyID.getText());
    	myToy.setInspector(txtInspector.getText());
    	myToy.setInspectionDateTime(LocalDateTime.now());
    	
    	//Set circuit1 properties
    	myToy.getCircuit1().setVoltage(txtVoltage1.getText());
    	myToy.getCircuit1().setResistance(txtResistance1.getText());
    	
    	//Set circuit2 properties
    	myToy.getCircuit2().setVoltage(txtVoltage2.getText());
    	
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

    }
   
}
