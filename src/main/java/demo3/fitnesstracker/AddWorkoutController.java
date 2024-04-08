package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
import demo3.fitnesstracker.objects.Workout;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddWorkoutController {
    @FXML private TextField dateField;
    @FXML private TextField nameField;
    @FXML private TextField setsField;
    @FXML private TextField repsField;
    @FXML private TextField weightField;
    @FXML private TextField timeField;

    private Data data;
    private String currentWorkoutDate; // Store the date for the current workout session

    public void setData(Data data) {
        this.data = data;
    }

    public interface StatsUpdateCallback {
        void updateStats();
    }

    private StatsUpdateCallback statsUpdateCallback;

    public void setStatsUpdateCallback(StatsUpdateCallback callback) {
        this.statsUpdateCallback = callback;
    }


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
            int sets = Integer.parseInt(setsField.getText());
            int reps = Integer.parseInt(repsField.getText());
            float weight = Float.parseFloat(weightField.getText());
            int time = Integer.parseInt(timeField.getText());

            Exercise exercise = new Exercise(name, sets, reps, weight, time);
            data.addExerciseToWorkout(currentWorkoutDate, exercise);

            showAlert("Success", "Exercise added to the workout!");

            // Optionally clear the fields except for the date
            nameField.clear();
            setsField.clear();
            repsField.clear();
            weightField.clear();
            timeField.clear();

        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please enter valid numbers for sets, reps, weight, and time.");
        }
    }

    // Method to show alerts
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) setsField.getScene().getWindow();
        // Inside your AddWorkoutController after an exercise is successfully added


        stage.close();
    }
}

