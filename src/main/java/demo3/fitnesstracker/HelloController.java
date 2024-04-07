package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.Optional;

public class HelloController {

    @FXML
    private Button addWorkoutButton;

    @FXML
    private Button displayWorkoutsButton;

    @FXML
    private MenuItem loadMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private Button searchWorkoutButton;

    @FXML
    private VBox statsBox;

    @FXML
    private Label totalExercisesLabel;

    @FXML
    private Label totalTimeSpentLabel;

    @FXML
    private Label totalWorkoutsLabel;

    private Data data;

    public HelloController() {
        data = new Data();
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleAddWorkoutButton() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addWorkout.fxml"));
            Parent root = fxmlLoader.load();

            AddWorkoutController addWorkoutController = fxmlLoader.getController();
            addWorkoutController.setData(data);

            Stage stage = new Stage();
            stage.setTitle("Add Workout");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDisplayWorkoutsButton() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("displayWorkouts.fxml"));
            Parent root = fxmlLoader.load();


            Stage stage = new Stage();
            stage.setTitle("Display Workouts");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchWorkoutButton() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchWorkout.fxml"));
            Parent root = fxmlLoader.load();


            Stage stage = new Stage();
            stage.setTitle("Search Workout");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
