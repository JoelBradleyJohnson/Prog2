module circuitfx {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	exports application to javafx.graphics;
	opens controller to javafx.fxml;
	
}