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
            // Load the FXML file for the "Add Workout" UI
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addWorkout.fxml"));
            // Load the UI elements into a Parent object
            Parent root = fxmlLoader.load();

            // Get the controller associated with the "Add Workout" UI
            AddWorkoutController addWorkoutController = fxmlLoader.getController();
            // Pass the Data object to the AddWorkoutController so it can manipulate the data
            addWorkoutController.setData(data);

            // Set a callback in AddWorkoutController to update the statistics in the HelloController
            // after a new workout is added
            addWorkoutController.setStatsUpdateCallback(new AddWorkoutController.StatsUpdateCallback() {
                @Override
                public void updateStats() {
                    // Call the updateStats method in HelloController to refresh the displayed stats
                    HelloController.this.updateStats();
                }
            });

            // Set a new stage for the "Add Workout UI"
            Stage stage = new Stage();
            stage.setTitle("Add Workout");
            stage.setMinHeight(1080);
            stage.setMinWidth(900);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // After the "Add Workout" window is closed, update the statistics on the main window
            updateStats();

        } catch (IOException e) {
            // If an IOException occurs, print the stack trace for debugging
            e.printStackTrace();
        }
    }


    /**
     * Handles the action triggered by the "Display Workouts" button.
     * This method initializes a new window to display all recorded workouts by loading
     * the associated FXML file and setting the necessary controller.
     */
    @FXML
    private void handleDisplayWorkoutsButton() {
        try {
            // Load the FXML file for displaying workouts
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("displayWorkouts.fxml"));
            // Load the view
            Parent root = fxmlLoader.load();

            // Get the controller and pass the current data model to it
            DisplayWorkoutsController displayWorkoutsController = fxmlLoader.getController();
            displayWorkoutsController.setData(data);

            // Create a new stage to display workouts and set its properties
            Stage stage = new Stage();
            stage.setTitle("Display Workouts");
            stage.setMinHeight(900);
            stage.setMinWidth(900);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            // If an IOException occurs, print the stack trace for debugging
            e.printStackTrace();
        }
    }


    /**
     * Handles the action triggered by the "Search Workout" button.
     * This method opens a new window for searching workouts by loading the appropriate
     * FXML file and setting up the controller with access to the current data model.
     */
    @FXML
    private void handleSearchWorkoutButton() {
        try {
            // Load the FXML file for searching workouts
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchWorkout.fxml"));
            // Load the view
            Parent root = fxmlLoader.load();

            // Get the controller and pass the current data model to it
            SearchWorkoutsController searchWorkoutsController = fxmlLoader.getController();
            searchWorkoutsController.setData(data);

            // Create a new stage to search workouts and set its properties
            Stage stage = new Stage();
            stage.setTitle("Search Workout");
            stage.setMinHeight(900);
            stage.setMinWidth(900);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            // If an IOException occurs, print the stack trace for debugging
            e.printStackTrace();
        }
    }


    /**
     * Displays an informational alert about the Fitness Tracker application.
     * This method constructs and shows an alert dialog with details about the Fitness Tracker application,
     * including the developers' names and the application version.
     */
    @FXML
    private void handleAbout() {
        // Create a new alert of type INFORMATION
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        // Set the title of the alert window
        aboutAlert.setTitle("About Fitness Tracker");
        // Set the header text of the alert
        aboutAlert.setHeaderText("Fitness Tracker Application");
        // Set the content text of the alert with developers' information and application version
        aboutAlert.setContentText("Developed by:\n\n" +
                "Daphne Choong - 30171153\n" +
                "Pavitpal Singh Bhagat - 30177873\n" +
                "Sourav Singh - 30230496\n\n" +
                "Fitness Tracker helps you keep track of your workouts and exercises.\n\n" +
                "Version: 1.0");
        // Display the alert and wait for the user to close it
        aboutAlert.showAndWait();
    }

    /**
     * Shows an alert dialog with a custom title and message.
     * This utility method is used to display informational messages to the user.
     * @param title   the title of the alert dialog
     * @param message the message to be displayed inside the alert dialog
     */
    private void showAlert(String title, String message) {
        // Create a new alert of type INFORMATION
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        // Set the title of the alert
        alert.setTitle(title);
        // The header is set to null to not display a header section in the alert dialog
        alert.setHeaderText(null);
        // Set the content of the alert to the message passed as a parameter
        alert.setContentText(message);
        // Display the alert and wait for the user to close it
        alert.showAndWait();
    }

}
