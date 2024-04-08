package demo3.fitnesstracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class responsible for launching the JavaFX application.
 */
public class HelloApplication extends Application {

    /**
     * Sets up the stage and loads the FXML file for the main view.
     * @param stage The primary stage of the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Create a new FXMLLoader instance and load the FXML file for the main view
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // Create a new Scene using the loaded FXML root node, set the scene dimensions
        Scene scene = new Scene(fxmlLoader.load(), 1080, 1080);

        // Set the title and scene of the primary stage, then display it
        stage.setTitle("Fitness Tracker");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to launch the JavaFX application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}