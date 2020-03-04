package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This class opens the dialog box that the user interacts with.
 * @author 216280
 */
public class Main extends Application {

	/**
	 * This method handles the dialog box.
	 * @param primaryStage This parameter sends command line arguments execution.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/Liew.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

/**
 * This method handles the dialog box.
 * @param args This parameter does stuff.
 */
	public static void main(String[] args) {
		launch(args);
	}
}

