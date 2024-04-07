package demo3.fitnesstracker.objects;

import java.util.ArrayList;

/**
 * This class represents a class that holds all the workouts .i.e collection of workouts
 */
public class Workouts {
    //Arraylist to store all the workout objects
    private ArrayList<Workout> workouts;

    /**
     * Constructor that creates a new workouts object with an empty arraylist of workouts
     */
    public Workouts() {
        //initialize the list of workout objects as empty
        this.workouts = new ArrayList<>();
    }

    /**
     * Adds a workout object to our list of workouts .i.e workouts arraylist
     * @param workout - the workout being passed
     */
    public void addWorkout(Workout workout) {
        //adds the workout object passed in as an argument to the workouts arraylist
        workouts.add(workout);
    }

    /**
     * Replaces the current list of workouts with the workouts that were read from the workouts.csv file
     * first the method clears all the workouts it currently has, and then it adds all the elements from the arraylist passed in as an argument
     * @param workoutsFromFile - workouts read from the file to replace our current arraylist of workouts
     */
    public void setAllWorkouts(ArrayList<Workout> workoutsFromFile){
        //clear the current arraylist of workouts
        workouts.clear();
        //add all the workouts from the provided arraylist in the parameter to the current one
        workouts.addAll(workoutsFromFile);
    }

    /**
     * Returns the current arraylist that holds all our workouts
     * @return - an arraylist of workout objects
     */
    public ArrayList<Workout> getWorkouts() {
        //return the current arraylist holding all workouts
        return workouts;
    }
}
