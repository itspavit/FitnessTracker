package demo3.fitnesstracker;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddWorkoutController {
    @FXML
    private TextField setsField;
    @FXML private TextField repsField;
    @FXML private TextField weightField;
    @FXML private TextField timeField;

    @FXML
    private void handleAddExercise() {

    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) setsField.getScene().getWindow();
        stage.close();
    }
}

