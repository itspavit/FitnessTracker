package demo3.fitnesstracker.objects;

import java.io.*;
import java.util.ArrayList;

/**
 * Class will handle input and output functionality. Which is saving data to a csv file and loading data from csv file.
 */
public class FileIO{
    //data object which holds all the data related to workouts
    private Data data;

    private static final int INDEX_OF_DATE = 0;

    //file path where data will be saved/loaded
    private static final String fileName = "/home/itspavit/Desktop/CPSC 233/FitnessTracker-GUI/src/main/resources/demo3/fitnesstracker/workouts.csv";

    /**
     * FileIO constructor
     */
    public FileIO() {

    }

    /**
     * Uses a buffered writer and filterer to write workout data into a csv file as a comma separated value which is in the following format:
     * example of format - date,name,sets, reps, weight, time.... keeps on writing until there is no more workout
     * return type - void so returns nothing.
     */
    public void saveWorkouts(Data data){
        try {
            //creating a BufferedWriter to write data into the workouts.csv file
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            //getting all the workouts from the data object
            ArrayList<Workout> allWorkouts = data.workouts().getWorkouts();

            //looping through all the workouts
            for(Workout eachWorkout: allWorkouts){
                //writing the date of the workout into the file and adding a comma at the ending so we can separate the exercise details
                writer.write(eachWorkout.getDate()+",");
                //getting all the exercises we did for the current workout we are looping over
                ArrayList<Exercise> allExercises = eachWorkout.getExercises();
                //looping over all the exercises
                for(Exercise exercise: allExercises){
                    //writing data for each exercise to the csv file separated by commas
                    writer.write(exercise.getName()+","+exercise.getSets()+","+exercise.getReps()+","+exercise.getWeight()+","+exercise.getTime()+",");
                }
                //line break
                writer.write("\n");
            }
            System.out.println("All workouts saved to the csv file \n");
            //closing writer
            writer.close();
        } catch (IOException e) {
            //if we get an ioexception it will print the stackTrace i.e a report of the sequence of method calls that happened before the exception was thrown
            e.printStackTrace();
        }
    }

    /**
     * Loads the workouts from the workouts.csv file to an arraylist of workouts and returns the arraylist containing all the workout objects
     * Every line represents a workout - which contains the date,name,sets,reps,weight,time
     * @return - returns an arraylist of workout objects containing the loaded data from the csv file
     */
    public ArrayList<Workout> loadWorkouts(){
        //initializing a line which while hold a line that was read by the buffered reader
        String line;
        //creating new empty arraylist that will hold workout objects
        ArrayList<Workout> allWorkouts = new ArrayList<>();
        try {
            //buffered reader to read from the workouts.csv file
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            //loops until there is no more content in the file
            while((line=reader.readLine()) !=null){
                //stored workout details read from the file into a Workout object using the getWorkout method
                Workout newWorkout = getWorkout(line);
                //stores the workout we just got into the allWorkouts arraylist
                allWorkouts.add(newWorkout);
            }
        } catch (FileNotFoundException e) {
            throw new UncheckedIOException("File "+ fileName + " not found",e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Loaded all workouts from the csv file \n");
        //return the arraylist of all workouts
        return allWorkouts;
    }

    /**
     * This method is to read the lines and convert each comma separated values into an exercise object and also passing
     * the date read from file to the Workout as a parameter and then finally adding the date and exercise to the Workout object
     * @param line - the line that was read by the BufferedReader
     * @return - returns a Workout object
     */
    private static Workout getWorkout(String line) {
        //making a String array to store the line read and splitting them whenever there is a comma
        String[] readItems = line.split(",");
        //as the first value is always the date of the workout its stored before the for loop starts
        String date = readItems[INDEX_OF_DATE];

        //making a newWorkout object using the date we just got from file
        Workout newWorkout = new Workout(date);
        //looping over the rest of the items in the readItems array. because the rest of the items are just the exercises which have name,sets,reps,weight,time
        //Its incremented by 5 times to reach the next exercise in the array
        for(int i=1; i<readItems.length; i+=5){
            //storing the name which is at index i
            String exerciseName = readItems[i].trim();

            //storing the sets and reps and parsing them into an integer value
            int exerciseSets = Integer.parseInt(readItems[i+1]);
            int exerciseReps = Integer.parseInt(readItems[i+2]);

            //storing the weight as a floating point number and convert the string to a float
            float exerciseWeight = Float.parseFloat(readItems[i+3]);

            //storing time and parsing the time to an integer as we store the exercise as minutes
            int exerciseTime = Integer.parseInt(readItems[i+4]);

            //creating a new exercise object with the above details as parameters
            Exercise newExercise = new Exercise(exerciseName,exerciseSets,exerciseReps,exerciseWeight,exerciseTime);
            //storing this exercise into the Workout object
            newWorkout.addExercise(newExercise);
        }
        //return the Workout object
        return newWorkout;
    }
}
