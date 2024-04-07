package demo3.fitnesstracker.objects;

import java.util.ArrayList;
/**
 * Data class represents a container for managing workout data.
 */
public class Data {


    /**
     * Represents a collection of all workouts.
     * This field stores an instance of the Workouts class, which manages all workout data.
     */
    private final Workouts allWorkouts;


    /**
     * Constructs a Data object.
     * This constructor initializes a Data object with an empty Workouts instance,
     * which will be used to store all workout data.
     */
    public Data() {
        this.allWorkouts = new Workouts();
    }


    /**
     * Adds an exercise to a workout on the specified date.
     * If a workout for the given date does not exist, a new workout is created.
     * The exercise is then added to the workout.
     *
     * @param date The date of the workout to which the exercise will be added.
     * @param exercise The Exercise object to be added to the workout.
     */
    public void addExerciseToWorkout(String date, Exercise exercise) {
        // Find the workout for the specified date
        Workout workout = findWorkoutByDate(date);

        // If no workout exists for the date, create a new workout
        if (workout == null) {
            workout = new Workout(date);
            allWorkouts.addWorkout(workout);
        }
        // Add the exercise to the workout
        workout.addExercise(exercise);
    }


    /**
     * Finds and retrieves a workout by its date.
     * This method searches for a workout with the specified date in the list of all workouts
     * and returns the first workout found with the matching date. If no workout is found
     * for the specified date, it returns null.
     *
     * equals(). is implemented in this function
     *
     * @param date The date of the workout to find.
     * @return The workout with the specified date, or null if no such workout is found.
     */
    public Workout findWorkoutByDate(String date) {
        for (Workout workout : allWorkouts.getWorkouts()) {
            // Use equals() to see if the dates are the same
            if (workout.getDate().equals(date)) {
                return workout;
            }
        }
        return null;
    }


    /**
     * Retrieves Workouts object containing all the workout data.
     * @return The Workouts object containing all the workout data.
     */
    public Workouts workouts() {
        return allWorkouts;
    }


    /**
     * Sets the list of workouts with the provided list of workouts from file.
     * This method updates the list of workouts in the Data object with the provided list of workouts.
     * @param workoutsFromFile The ArrayList of Workout objects obtained from file.
     */
    public void setAllWorkouts(ArrayList<Workout> workoutsFromFile){
        allWorkouts.setAllWorkouts(workoutsFromFile);
    }


}
