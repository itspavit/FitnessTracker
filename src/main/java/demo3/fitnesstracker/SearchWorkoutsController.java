package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
import demo3.fitnesstracker.objects.Workout;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SearchWorkoutsController {
    @FXML
    private TextField dateField;
    @FXML
    private TextArea workoutDetailsArea; // Added TextArea to display workout details

    private Data data; // Assuming Data class is accessible and contains all workouts

    // Method to set Data object
    public void setData(Data data) {
        this.data = data;
    }

    @FXML
    private void handleFindWorkout() {
        String date = dateField.getText();
        searchWorkoutByDate(date);
    }

    private void searchWorkoutByDate(String date) {
        Workout workout = data.findWorkoutByDate(date);
        if (workout != null) {
            StringBuilder workoutDetails = new StringBuilder("Workout Date: " + workout.getDate() + "\n");
            for (Exercise exercise : workout.getExercises()) {
                workoutDetails.append("\tExercise Name: ").append(exercise.getName())
                        .append(", Sets: ").append(exercise.getSets())
                        .append(", Reps: ").append(exercise.getReps())
                        .append(", Weight: ").append(exercise.getWeight()).append(" kg")
                        .append(", Time: ").append(exercise.getTime()).append(" minutes\n");
            }
            workoutDetails.append("--------------------------------------------------\n");

            // Set the workout details in the TextArea
            workoutDetailsArea.setText(workoutDetails.toString());
        } else {
            workoutDetailsArea.setText("No workout found for the date: " + date);
        }
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) dateField.getScene().getWindow();
        stage.close();
    }
}
