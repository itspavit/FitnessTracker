package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
import demo3.fitnesstracker.objects.Workout;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DisplayWorkoutsController {
    @FXML
    private ListView<String> workoutsList;

    private Data data; // Assuming Data class is accessible and contains all workouts

    public void setData(Data data) {
        this.data = data;
        populateWorkoutsList();
    }

    private void populateWorkoutsList() {
        List<String> workoutDescriptions = new ArrayList<>();
        for (Workout workout : data.workouts().getWorkouts()) {
            StringBuilder workoutInfo = new StringBuilder("Workout Date: " + workout.getDate() + "\n");
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

        if (workoutDescriptions.isEmpty()) {
            workoutsList.getItems().add("No workouts have been added yet.");
        } else {
            workoutsList.getItems().addAll(workoutDescriptions);
        }
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) workoutsList.getScene().getWindow();
        stage.close();
    }
}
