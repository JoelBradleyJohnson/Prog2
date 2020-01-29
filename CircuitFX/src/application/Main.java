package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class holds the initialization for the user interface.
 * @author jjohnson
 */
public class Main extends Application {
	@Override
	public final void start(final Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/View.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Calculadora de la Ley de Oms");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method launches the command line arguments... I guess. 
	 * @param args
	 */
	public static void main(final String[] args) {
		launch(args);
	}
}
