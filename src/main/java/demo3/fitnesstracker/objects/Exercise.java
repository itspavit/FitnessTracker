package demo3.fitnesstracker.objects;

/**
 * Exercise class represents an exercise with its attributes such as name, sets, reps, weight, and time.
 */

public class Exercise {

    // Attributes of an exercise
    private String name;  // Name of the exercise
    private int sets, reps, time;  // Number of sets, repetitions per reps, and time spent (minutes) completing the exercise
    private float weight; // Weights used during the exercise


    /**
     * Constructs an Exercise object with the given attributes.
     *
     * @param name   The name of the exercise.
     * @param sets   The number of sets.
     * @param reps   The number of repetitions per set.
     * @param weight The weight used during the exercise.
     * @param time   The time spent on the exercise (in minutes).
     */
    public Exercise(String name, int sets, int reps, float weight, int time) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.time = time;
    }


    /**
     * Retrieves the name of the exercise.
     * @return The name of the exercise.
     */
    public String getName() {
        return name;
    }


    /**
     * Retrieves the number of sets for the exercise.
     * @return The number of sets.
     */
    public int getSets() {
        return sets;
    }


    /**
     * Retrieves the number of repetitions per set for the exercise.
     * @return The number of repetitions per set.
     */
    public int getReps() {
        return reps;
    }


    /**
     * Retrieves the weight used during the exercise.
     * @return The weight used during the exercise.
     */
    public float getWeight() {
        return weight;
    }


    /**
     * Retrieves the time spent on the exercise (in minutes).
     * @return The time spent on the exercise (in minutes).
     */
    public int getTime() {
        return time;
    }
}