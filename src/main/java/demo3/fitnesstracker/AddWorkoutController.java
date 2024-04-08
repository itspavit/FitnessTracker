package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddWorkoutController {

    /**
     * These private text fields are for user input
     */
    @FXML private TextField dateField;
    @FXML private TextField nameField;
    @FXML private TextField setsField;
    @FXML private TextField repsField;
    @FXML private TextField weightField;
    @FXML private TextField timeField;

    private Data data;  // Reference to the data class
    private String currentWorkoutDate;  // Store the date for the current workout session


    /**
     * Setter method to set the Data object
     * @param data The Data object to set.
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Callback to update statistics
     */
    public interface StatsUpdateCallback {
        void updateStats();
    }


    /**
     * Setter method to set the StatsUpdateCallback
     * @param callback The StatsUpdateCallback object to set.
     */
    private StatsUpdateCallback statsUpdateCallback;
    public void setStatsUpdateCallback(StatsUpdateCallback callback) {
        this.statsUpdateCallback = callback;
    }


    /**
     * Handles the addition of a new exercise.
     */
    @FXML
    private void handleAddExercise() {
        String name = nameField.getText();

        // Only prompt for date on the first exercise addition
        if (currentWorkoutDate == null || currentWorkoutDate.isEmpty()) {
            String dateString = dateField.getText(); // Get the date string from the TextField
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                currentWorkoutDate = dateString;
                dateField.setDisable(true); // Optionally disable the date field after a valid date is entered
            } catch (DateTimeParseException e) {
                showAlert("Date Error", "Please enter the date in the format YYYY-MM-DD.");
                return; // Exit the method without adding the exercise
            }
        }

        try {
            // Parsing the values from the TextFields
            int sets = Integer.parseInt(setsField.getText());
            int reps = Integer.parseInt(repsField.getText());
            float weight = Float.parseFloat(weightField.getText());
            int time = Integer.parseInt(timeField.getText());

            // Creating a new Exercise object
            Exercise exercise = new Exercise(name, sets, reps, weight, time);
            // Adding the exercise to the workout
            data.addExerciseToWorkout(currentWorkoutDate, exercise);

            showAlert("Success", "Exercise added to the workout!");

            // Clearing the TextFields after adding the exercise
            nameField.clear();
            setsField.clear();
            repsField.clear();
            weightField.clear();
            timeField.clear();

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numbers for sets, reps, weight, and time.");
        }
    }

    /**
     * Displays an alert with the provided title and message.
     * @param title   The title of the alert.
     * @param message The message content of the alert.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);  // Create a new information alert
        alert.setTitle(title);  // Set the title of the alert
        alert.setHeaderText(null);  // Set the header text to null
        alert.setContentText(message);  // Set the content text of the alert
        alert.showAndWait();  // Display the alert and wait for user interaction
    }


    /**
     * Closes the current JavaFX stage.
     */
    @FXML
    private void handleClose() {
        // Retrieve the current stage from the setsField
        Stage stage = (Stage) setsField.getScene().getWindow();
        // Close the stage
        stage.close();
    }
}

