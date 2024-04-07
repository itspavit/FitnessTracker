package demo3.fitnesstracker;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DisplayWorkoutsController {
    @FXML
    private ListView<String> workoutsList;

    @FXML
    public void initialize() {
        // Populate the ListView with workout data, you might need to call a method
        // that returns the list of workouts and add them to the ListView.
        // For example:
        // workoutsList.getItems().addAll(getWorkouts());
    }

    private List<String> getWorkouts() {
        // Retrieve the list of workout descriptions
        // This method should interact with your data model to retrieve the workouts
        return new ArrayList<>();
    }

    @FXML
    private void handleClose() {
        // Get the current stage and close it
        Stage stage = (Stage) workoutsList.getScene().getWindow();
        stage.close();
    }
}

