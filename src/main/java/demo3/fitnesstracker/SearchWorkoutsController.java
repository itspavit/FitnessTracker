package demo3.fitnesstracker;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchWorkoutsController {
    @FXML
    private TextField dateField;

    @FXML
    private void handleFindWorkout() {
        String date = dateField.getText();
        searchWorkoutByDate(date);
    }

    private void searchWorkoutByDate(String date) {

    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) dateField.getScene().getWindow();
        stage.close();
    }
}
