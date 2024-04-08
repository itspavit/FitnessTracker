package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
import demo3.fitnesstracker.objects.Workout;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for the DisplayWorkouts.fxml file.
 * Handles the display of all workouts in a ListView.
 */
public class DisplayWorkoutsController {
    @FXML
    private ListView<String> workoutsList;  // ListView to display workouts

    private Data data;  // Assuming Data class is accessible and contains all workouts

    /**
     * Sets the Data object and populates the workouts list.
     * @param data The Data object containing the workouts.
     */
    public void setData(Data data) {
        this.data = data;
        populateWorkoutsList();
    }

    /**
     * Populates the workoutsList with the descriptions of all workouts.
     * If no workouts are present, displays a message indicating so.
     */
    private void populateWorkoutsList() {
        List<String> workoutDescriptions = new ArrayList<>();

        // Iterate through each workout to create its description
        for (Workout workout : data.workouts().getWorkouts()) {
            StringBuilder workoutInfo = new StringBuilder("Workout Date: " + workout.getDate() + "\n");

            // Iterate through each exercise of the workout to add its details
            for (Exercise exercise : workout.getExercises()) {
                workoutInfo.append("\tExercise Name: ").append(exercise.getName())
                        .append(", Sets: ").append(exercise.getSets())
                        .append(", Reps: ").append(exercise.getReps())
                        .append(", Weight: ").append(exercise.getWeight()).append(" kg")
                        .append(", Time: ").append(exercise.getTime()).append(" minutes\n");
            }
            workoutInfo.append("--------------------------------------------------\n");
            workoutDescriptions.add(workoutInfo.toString());
        }

        // Check if the list is empty and add appropriate message
        if (workoutDescriptions.isEmpty()) {
            workoutsList.getItems().add("No workouts have been added yet.");
        } else {
            workoutsList.getItems().addAll(workoutDescriptions);
        }
    }

    /**
     * Closes the current JavaFX stage.
     */
    @FXML
    private void handleClose() {
        // Retrieve the current stage from the workoutsList
        Stage stage = (Stage) workoutsList.getScene().getWindow();

        // Close the stage
        stage.close();
    }
}
