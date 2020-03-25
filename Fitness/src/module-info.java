module fitness {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    exports application to javafx.graphics;
    opens controller to javafx.fxml;
    opens model;
}