module library {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires mysql.connector.java;
	requires javafx.base;
	exports application to javafx.graphics;
	opens controller to javafx.fxml;
	opens model to javafx.base;
}