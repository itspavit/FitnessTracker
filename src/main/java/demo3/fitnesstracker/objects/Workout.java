package demo3.fitnesstracker.objects;

import java.util.ArrayList;

/**
 * This represents our Workout session that contains a date and exercises done on the same date
 */
public class Workout {
    //to store the date of the workout
    private String date;
    //to store the exercise objects as a collection as the user can input multiple exercises on the same date
    private ArrayList<Exercise> exercises;

    /**
     * Workout constructor makes a new workout object for us with the date passed as a parameter
     * @param date - the date bring passed
     */
    public Workout(String date) {
        //storing passed date as parameter to out object date variable
        this.date = date;
        //initializing out exercises arraylist as an empty arraylist
        this.exercises = new ArrayList<>();
    }

    /**
     * Adds an exercise to our arraylist of exercise objects
     * @param exercise - the exercise object which is to be added to the arraylist
     */
    public void addExercise(Exercise exercise) {
        //adds the exercise passed in the parameter to the arraylist of exercise objects
        exercises.add(exercise);
    }

    /**
     * Returns the date of the workout
     * @return - String containing the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns the list arraylist holding all our exercise objects
     * @return - An ArrayList of Exercise objects representing the exercises in this workout.
     */
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }
}
