package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
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

    public HelloController() {

    }

    @FXML
    private void initialize() {
        data = new Data();
        updateStats();
        Font.loadFont(getClass().getResourceAsStream("/demo3.fitnesstracker/Satisfy-Regular.ttf"), 20);
    }


    private void updateStats() {
        totalWorkoutsLabel.setText("Total Workouts: " + getTotalWorkouts());
        totalExercisesLabel.setText("Total Exercises: " + getTotalExercises());
        totalTimeSpentLabel.setText("Total Time Spent: " + getTotalTimeSpent() + " minutes");
    }

    private int getTotalWorkouts() {
        // Assuming data.workouts().getWorkouts() returns an ArrayList of all workouts
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

            // Set the callback for updating stats
            addWorkoutController.setStatsUpdateCallback(new AddWorkoutController.StatsUpdateCallback() {
                @Override
                public void updateStats() {
                    HelloController.this.updateStats(); // Call the updateStats method in HelloController
                }
            });

            Stage stage = new Stage();
            stage.setTitle("Add Workout");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            updateStats(); // Update stats after the Add Workout window is closed

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




}
