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
    private Button addWorkoutButton;

    @FXML
    private Button displayWorkoutsButton;

    @FXML
    private MenuItem loadMenuItem;

    @FXML
    private MenuItem saveMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Button searchWorkoutButton;

    @FXML
    private VBox statsBox;

    @FXML
    private Label totalExercisesLabel;

    @FXML
    private Label totalTimeSpentLabel;

    @FXML
    private Label totalWorkoutsLabel;

    private Data data;

    private FileIO file;

    public HelloController() {

    }

    @FXML
    private void initialize() {
        data = new Data();
        file = new FileIO();
        updateStats();
        Font.loadFont(getClass().getResourceAsStream("/demo3/fitnesstracker/Satisfy-Regular.ttf"), 20);
    }

    @FXML
    private void handleLoadMenuItemAction() {
        try {
            ArrayList<Workout> loadedWorkouts = file.loadWorkouts();
            data.setAllWorkouts(loadedWorkouts);
            updateStats();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Load Error", "Failed to load workouts from file.");
        }
    }

    @FXML
    private void handleSaveMenuItemAction() {
        try {
            file.saveWorkouts(data);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Save Error", "Failed to save workouts to file.");
        }
    }


    private void updateStats() {
        totalWorkoutsLabel.setText("Total Workouts: " + getTotalWorkouts());
        totalExercisesLabel.setText("Total Exercises: " + getTotalExercises());
        totalTimeSpentLabel.setText("Total Time Spent: " + getTotalTimeSpent() + " minutes");
    }

    private int getTotalWorkouts() {
        return data.workouts().getWorkouts().size();
    }

    private int getTotalExercises() {
        int totalExercises = 0;
        for (Workout workout : data.workouts().getWorkouts()) {
            totalExercises += workout.getExercises().size();
        }
        return totalExercises;
    }

    private int getTotalTimeSpent() {
        int totalTime = 0;
        for (Workout workout : data.workouts().getWorkouts()) {
            for (Exercise exercise : workout.getExercises()) {
                totalTime += exercise.getTime();
            }
        }
        return totalTime;
    }



    @FXML
    private void handleAddWorkoutButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addWorkout.fxml"));
            Parent root = fxmlLoader.load();

            AddWorkoutController addWorkoutController = fxmlLoader.getController();
            addWorkoutController.setData(data);

            addWorkoutController.setStatsUpdateCallback(new AddWorkoutController.StatsUpdateCallback() {
                @Override
                public void updateStats() {
                    HelloController.this.updateStats();
                }
            });

            Stage stage = new Stage();
            stage.setTitle("Add Workout");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            updateStats();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleDisplayWorkoutsButton() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("displayWorkouts.fxml"));
            Parent root = fxmlLoader.load();

            DisplayWorkoutsController displayWorkoutsController = fxmlLoader.getController();
            displayWorkoutsController.setData(data);

            Stage stage = new Stage();
            stage.setTitle("Display Workouts");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchWorkoutButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchWorkout.fxml"));
            Parent root = fxmlLoader.load();

            SearchWorkoutsController searchWorkoutsController = fxmlLoader.getController();
            searchWorkoutsController.setData(data);

            Stage stage = new Stage();
            stage.setTitle("Search Workout");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAbout() {
        Alert aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("About Fitness Tracker");
        aboutAlert.setHeaderText("Fitness Tracker Application");
        aboutAlert.setContentText("Developed by:\n\n" +
                "Daphne Choong - 30171153\n" +
                "Pavitpal Singh Bhagat - 30177873\n" +
                "Sourav Singh - 30230496\n\n" +
                "Fitness Tracker helps you keep track of your workouts and exercises.\n\n" +
                "Version: 1.0");

        aboutAlert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
