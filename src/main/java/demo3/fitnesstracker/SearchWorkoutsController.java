package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
import demo3.fitnesstracker.objects.Workout;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


/**
 * Controller class for searching workouts by date.
 * This class is responsible for handling user interactions in the search workouts screen, including
 * searching for workouts by a specified date and displaying the workout details.
 */
public class SearchWorkoutsController {
    @FXML
    private TextField dateField;  // TextField for user to input the date for search.
    @FXML
    private TextArea workoutDetailsArea; // Added TextArea to display workout details

    private Data data; // Assuming Data class is accessible and contains all workouts

    /**
     * Sets the Data object. This method provides the controller with access to the application's data.
     * @param data The Data object containing all workout information.
     */
    public void setData(Data data) {
        this.data = data;
    }


    /**
     * Event handler for the Find Workout button.
     * When invoked, it retrieves the date from the dateField, searches for a workout on that date,
     * and displays the details in the workoutDetailsArea TextArea.
     */
    @FXML
    private void handleFindWorkout() {
        String date = dateField.getText();  // Get the date from the input field.
        searchWorkoutByDate(date);  // Search for the workout by the provided date.
    }


    /**
     * Searches for a workout by date and displays its details.
     * If a workout is found for the given date, its details are formatted and displayed in the workoutDetailsArea.
     * If no workout is found, a message indicating so is displayed instead.
     * @param date The date for which to search the workout.
     */
    private void searchWorkoutByDate(String date) {
        // Attempt to find a workout by the given date
        Workout workout = data.findWorkoutByDate(date);
        if (workout != null) {
            // Build a string with workout details if found.
            StringBuilder workoutDetails = new StringBuilder("Workout Date: " + workout.getDate() + "\n");
            for (Exercise exercise : workout.getExercises()) {
                workoutDetails.append("\tExercise Name: ").append(exercise.getName())
                        .append(", Sets: ").append(exercise.getSets())
                        .append(", Reps: ").append(exercise.getReps())
                        .append(", Weight: ").append(exercise.getWeight()).append(" kg")
                        .append(", Time: ").append(exercise.getTime()).append(" minutes\n");
            }
            workoutDetails.append("--------------------------------------------------\n");

            // Set the formatted workout details in the TextArea for display.
            workoutDetailsArea.setText(workoutDetails.toString());
        } else {
            // Display a message if no workout is found for the given date.
            workoutDetailsArea.setText("No workout found for the date: " + date);
        }
    }


    /**
     * Closes the search workout window.
     * This method is invoked when the Close button is pressed, closing the current window.
     */
    @FXML
    private void handleClose() {
        // Get the current stage from the dateField.
        Stage stage = (Stage) dateField.getScene().getWindow();
        // Close the stage.
        stage.close();
    }
}
