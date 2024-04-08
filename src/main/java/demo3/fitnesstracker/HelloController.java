package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
import demo3.fitnesstracker.objects.FileIO;
import demo3.fitnesstracker.objects.Workout;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.ArrayList;
public class HelloController {


    @FXML
    private Button addWorkoutButton;  // Button to add a new workout

    @FXML
    private Button displayWorkoutsButton;  // Button to display all workouts

    @FXML
    private MenuItem loadMenuItem;  // Menu item to load data

    @FXML
    private MenuItem saveMenuItem;  // Menu item to save data

    @FXML
    private MenuItem aboutMenuItem;  // Menu item to display information

    @FXML
    private Button searchWorkoutButton;  // Button to search for a workout

    @FXML
    private VBox statsBox;  // Vertical box to display statistics

    @FXML
    private Label totalExercisesLabel;  // Label to display total exercises

    @FXML
    private Label totalTimeSpentLabel;  // Label to display total time spent working out

    @FXML
    private Label totalWorkoutsLabel;  // Label to display workouts

    private Data data;  // Data object to manage workout data

    private FileIO file;  // FileIO object to handle file operations


    /**
     * Empty constructor
     */
    public HelloController() {

    }


    /**
     * Initializes the controller.
     * This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        data = new Data();  // Initialize the data object
        file = new FileIO();  // Initialize the FileIO object
        updateStats();  // Update the statistics
        // Load the custom font
        Font.loadFont(getClass().getResourceAsStream("/demo3/fitnesstracker/Satisfy-Regular.ttf"), 20);
    }


    /**
     * Handles the action when the 'Load' menu item is clicked.
     */
    @FXML
    private void handleLoadMenuItemAction() {
        try {
            // Load workouts from the file
            ArrayList<Workout> loadedWorkouts = file.loadWorkouts();
            // Set the loaded workouts to the data object
            data.setAllWorkouts(loadedWorkouts);
            // Update the statistics
            updateStats();
            // Display a success message
            showAlert("Successful:", "loaded data from workouts.csv file");
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error message
            showAlert("Load Error", "Failed to load workouts from file.");
        }
    }


    /**
     * Handles the action when the 'Save' menu item is clicked.
     */
    @FXML
    private void handleSaveMenuItemAction() {
        try {
            // Save the workouts to the file
            file.saveWorkouts(data);
            // Display a success message
            showAlert("Successful:", "saved data to workouts.csv file");
        } catch (Exception e) {
            e.printStackTrace();
            // Display an error message
            showAlert("Save Error", "Failed to save workouts to file.");
        }
    }


    /**
     * Updates the statistics displayed in the UI with the latest data
     */
    private void updateStats() {
        // Set the text of the total ___ labels to the current number of workouts, exercises, and time spent
        totalWorkoutsLabel.setText("Total Workouts: " + getTotalWorkouts());
        totalExercisesLabel.setText("Total Exercises: " + getTotalExercises());
        totalTimeSpentLabel.setText("Total Time Spent: " + getTotalTimeSpent() + " minutes");
    }


    /**
     * Calculate and return the total number of workouts.
     * @return The total number of workouts.
     */
    private int getTotalWorkouts() {
        // Return the size of the workouts list, indicating the total number of workouts
        return data.workouts().getWorkouts().size();
    }


    /**
     * Retrieve the total number of exercises across all workouts
     * @return The total number of exercises.
     */
    private int getTotalExercises() {
        // Initialize a counter for the total number of exercises
        int totalExercises = 0;
        // Iterate through each workout
        for (Workout workout : data.workouts().getWorkouts()) {
            // Add the number of exercises in the current workout to the total
            totalExercises += workout.getExercises().size();
        }
        // Return the total number of exercises
        return totalExercises;
    }

    /**
     * Calculates and returns the total time spent on exercises across all workouts
     * @return The total time spent on exercises.
     */
    private int getTotalTimeSpent() {
        // Initialize a counter for the total time spent
        int totalTime = 0;
        // Iterate through each workout
        for (Workout workout : data.workouts().getWorkouts()) {
            // Iterate through each exercise in the current workout
            for (Exercise exercise : workout.getExercises()) {
                // Add the time spent on the current exercise to the total time
                totalTime += exercise.getTime();
            }
        }
        // Return the total time spent on exercises
        return totalTime;
    }



    @FXML
    private void handleAddWorkoutButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addWorkout.fxml"));
            Parent root = fxmlLoader.load();

            AddWorkoutController addWorkoutController = fxmlLoader.getController();
            addWorkoutController.setData(data);

            addWorkoutController.setStatsUpdateCallback(new AddWorkoutController.StatsUpdateCallback() {
                @Override
                public void updateStats() {
                    HelloController.this.updateStats();
                }
            });

            Stage stage = new Stage();
            stage.setTitle("Add Workout");
            stage.setMinHeight(1080);
            stage.setMinWidth(900);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            updateStats();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleDisplayWorkoutsButton() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("displayWorkouts.fxml"));
            Parent root = fxmlLoader.load();

            DisplayWorkoutsController displayWorkoutsController = fxmlLoader.getController();
            displayWorkoutsController.setData(data);

            Stage stage = new Stage();
            stage.setTitle("Display Workouts");
            stage.setMinHeight(900);
            stage.setMinWidth(900);
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

            SearchWorkoutsController searchWorkoutsController = fxmlLoader.getController();
            searchWorkoutsController.setData(data);

            Stage stage = new Stage();
            stage.setTitle("Search Workout");
            stage.setMinHeight(900);
            stage.setMinWidth(900);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAbout() {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About Fitness Tracker");
        aboutAlert.setHeaderText("Fitness Tracker Application");
        aboutAlert.setContentText("Developed by:\n\n" +
                "Daphne Choong - 30171153\n" +
                "Pavitpal Singh Bhagat - 30177873\n" +
                "Sourav Singh - 30230496\n\n" +
                "Fitness Tracker helps you keep track of your workouts and exercises.\n\n" +
                "Version: 1.0");

        aboutAlert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
