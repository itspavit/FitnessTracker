package demo3.fitnesstracker.objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Menu class provides a menu-driven interface for interacting with workout data.
 */

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private final Data data;
    private final FileIO file;
    /**
     * Constructs a Menu object with the specified Data and FileIO instances.
     * This constructor initializes the Menu object with the provided Data and FileIO objects,
     * which are used for managing workout data and file I/O operations, respectively.
     * @param data The Data object containing workout data.
     * @param file The FileIO object used for file input/output operations.
     */
    public Menu(Data data, FileIO file) {
        this.data = data;
        this.file = file;
    }


    /**
     * Displays the menu options and handles user input until the user chooses to exit.
     * Provides options for adding exercises to a workout, displaying all workouts,
     * finding a workout on a specific date, and exiting the menu loop.
     */
    public void menuLoop() {
        // Menu loop Implementation
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Menu Options:");
            System.out.println("1. Add Exercise to Workout");
            System.out.println("2. Display All Workouts");
            System.out.println("3. Find workout on a specific date");
            System.out.println("4. Save all data to csv file");
            System.out.println("5. Load data from csv file");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            //Displays the menu and handles user input until the user chooses to exit.

            switch (choice) {
                case 1:
                    menuAddExercise();
                    break;
                case 2:
                    menuDisplayWorkout();
                    break;
                case 3:
                    menuDisplayWorkoutByDate();
                    break;
                case 4:
                    file.saveWorkouts(data);
                    break;
                case 5:
                    ArrayList<Workout> loadedWorkoutsFromFile = file.loadWorkouts();
                    data.setAllWorkouts(loadedWorkoutsFromFile);
                    break;
                case 0:
                    continueLoop = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }


    /**
     * Prompts the user to enter a date in the format "YYYY-MM-DD", validates the input,
     * and returns the entered date.
     * @return A string representing a valid date in the format "YYYY-MM-DD" entered by the user.
     */
    private String getDate(){
        // Define the format expected for the date input
        DateTimeFormatter checkFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Initialize the variable to store the entered date
        String workoutDate;

        System.out.println("\nEnter workout date (YYYY-MM-DD):");
        // Loop indefinitely until a valid date is entered
        while(true){
            // Read the user input and remove leading/trailing whitespace
            workoutDate = scanner.nextLine().trim();
            try{
                // Attempt to parse date with specific format.  If parsing succeeds, return the valid date
                LocalDate.parse(workoutDate,checkFormat);
                return workoutDate;
            }catch (DateTimeParseException e){
                // If parsing fails, ask user to enter in date again
                System.out.println("Invalid date format. Please make sure to enter the date as (YYYY-MM-DD)");
            }
        }
    }


    /**
     * Prompts the user to enter an exercise name, validates the input, and returns the entered exercise name.
     * @return A string representing the exercise name entered by the user.
     */
    private String getName(){

        // Initialize the variable to store the entered exercise name
        String exerciseName;

        // Repeat the following block until a non-empty exercise name is entered
        do{
            System.out.println("Enter exercise name:");
            // Read the user input and remove leading/trailing whitespace
            exerciseName = scanner.nextLine().trim();
            // Check if input is empty and notify the user if so, and then prompt for a valid exercise name
            if(exerciseName.isEmpty()){
                System.out.println("Your input was empty please enter a valid exercise name");
            }
        }while (exerciseName.isEmpty());  // Repeat loop until a non-empty exercise name is entered
        // Return the validated exercise name
        return exerciseName;
    }


    /**
     * Prompts the user to enter the number of sets done for the exercise,
     * validates the input, and returns the entered number of sets.
     * @return An integer representing the number of sets entered by the user.
     */
    private int getSets(){

        // Loop the following block until a valid input is received
        while(true){
            System.out.println("Please enter the number of sets done for the exercise: ");
            String userInput = scanner.nextLine().trim();

            try{
                // Attempt to parse the user input as an integer
                int exerciseSets = Integer.parseInt(userInput);

                // Check if the entered number of sets is greater than 0, if so, return the value
                if(exerciseSets>0){
                    return exerciseSets;
                }else {
                    // Notify user that input is invalid (must be > 0)
                    System.out.println("You must enter sets more than 0.");
                }
            }catch (NumberFormatException e){
                // Notify the user if the input is not valid (non-integer input)
                System.out.println("Invalid input please enter number of sets greater than 0. Do not add letters or symbols.");
            }
        }
    }


    /**
     * Prompts the user to enter the number of reps done for the exercise,
     * validates the input, and returns the entered number of reps.
     * @return An integer representing the number of reps entered by the user.
     */
    private int getReps(){

        // Loop the following block until a valid input is received
        while(true){
            System.out.println("Please enter the number of reps done for the exercise: ");
            String userInput = scanner.nextLine().trim();

            try{
                // Attempt to parse the user input as an integer
                int exerciseReps = Integer.parseInt(userInput);

                // Check if the entered number of reps is greater than 0, if so, return the value
                if(exerciseReps>0){
                    return exerciseReps;
                }else {
                    // Notify user that input is invalid (must be > 0)
                    System.out.println("You must enter reps more than 0.");
                }
            }catch (NumberFormatException e){
                // Notify the user if the input is not valid (non-integer input)
                System.out.println("Invalid input please enter number of reps greater than 0. Do not add letters or symbols.");
            }
        }

    }


    /**
     * Prompts the user to enter the weights used for the exercise, validates the input, and returns the entered weight.
     * @return An integer representing the weight entered by the user.
     */
    private float getWeight(){
        // Loop the following block until a valid input is received
        while(true){
            System.out.println("Please enter the weight used for the exercise: ");
            String userInput = scanner.nextLine().trim();

            try{
                // Attempt to parse the user input as a float
                float exerciseWeight = Float.parseFloat(userInput);

                // Check if the entered number of reps is equal to or greater than 0, if so, return the value
                if(exerciseWeight>=0){
                    return exerciseWeight;
                }else {
                    // Notify user that input is invalid (must be >= 0)
                    System.out.println("You must enter weight greater than or equal to 0.");
                }
            }catch (NumberFormatException e){
                // Notify the user if the input is not valid (non-float input)
                System.out.println("Invalid input please enter weight more than or equal to 0. Do not add letters or symbols.");
            }
        }
    }


    /**
     * Prompts the user to enter the time spent (in minutes) doing the exercise, validates the input,
     * and returns the entered time.
     * @return An integer representing the time spent (in minutes) entered by the user.
     */

    private int getTime(){
        // Loop the following block until a valid input is received
        while(true){
            System.out.println("Please enter the time spent on the exercise as minutes: ");
            String userInput = scanner.nextLine().trim();

            try{
                // Attempt to parse the user input as an int
                int exerciseTime = Integer.parseInt(userInput);

                // Check if the entered time is greater than 0, if so, return the value
                if(exerciseTime>0){
                    return exerciseTime;
                }else {
                    // Notify user that input is invalid (must be > 0)
                    System.out.println("You must enter time more than 0 minutes.");
                }
            }catch (NumberFormatException e){
                // Notify the user if the input is not valid (non-integer input)
                System.out.println("Invalid input please enter time as minutes greater than 0. Do not add letters or symbols.");
            }
        }
    }


    /**
     * Prompts the user to add exercises to a workout on a specified date.
     * The user can add multiple exercises to the same workout.
     * This method interacts with the user to input exercise details such as name, sets, reps, weight, and time.
     * The entered exercises are then added to the workout for the specified date using the Data class.
     */
    private void menuAddExercise() {
        // Get the date of the workout
        String date = getDate();

        // Variable to control the loop for adding more exercises
        boolean addMoreExercises = true;
        // Continue adding exercises until the user chooses to stop
        while(addMoreExercises){

            // Prompt the user to enter exercise details
            String name = getName();
            int sets = getSets();
            int reps = getReps();
            float weight = getWeight();
            int time = getTime();

            System.out.println("Press enter to continue \n");
            scanner.nextLine();

            // Create an Exercise object with the entered details
            Exercise exercise = new Exercise(name, sets, reps, weight, time);
            // Add the exercise to the workout for the specified date
            data.addExerciseToWorkout(date, exercise);
            System.out.println("Exercise added to workout. \n");

            // Ask the user if they want to add more exercises
            System.out.println("Would you like to add more exercises to the workout? type (yes/no) \n");
            String userAnswer = scanner.nextLine().trim().toLowerCase();

            // Check if the user wants to add more exercises or stop
            // If not "yes" the boolean variable addMoreExercises will be set to false, ending the loop
            if(!userAnswer.equalsIgnoreCase("yes")){
                addMoreExercises = false;
            }
        }
    }


    /**
     * Displays all the workouts performed till now along with their exercises.
     * Retrieves all workouts from the data and iterates through them to display each workout's details.
     * If no workouts have been added yet, it notifies the user accordingly.
     */
    private void menuDisplayWorkout() {
        // Method implementation
        System.out.println("These are all the workouts performed till now:");

        // Retrieve all workouts from the Data class
        ArrayList<Workout> allWorkouts = data.workouts().getWorkouts();

        // Iterate through each workout to display its details
        for (Workout workout : allWorkouts) {
            // Print the workout date
            System.out.println("\nWorkout Date: " + workout.getDate() + "\n");
            // Get the list of exercises for the current workout
            ArrayList<Exercise> exercises = workout.getExercises();

            // Iterate through each exercise in the workout to display its details
            for (Exercise exercise : exercises) {
                System.out.println("\tExercise Name: " + exercise.getName());
                System.out.println("\tSets: " + exercise.getSets());
                System.out.println("\tReps: " + exercise.getReps());
                System.out.println("\tWeight: " + exercise.getWeight() + " kg");
                System.out.println("\tTime: " + exercise.getTime() + " minutes \n");
            }
            // Print a line to act as a separator
            System.out.println("--------------------------------------------------");
        }

        // Notify the user if no workouts have been added yet
        if (allWorkouts.isEmpty()) {
            System.out.println("No workouts have been added yet.");
        }
    }


    /**
     * Displays the workout details for a specific date entered by the user.
     * Retrieves the workout for the specified date from the data and displays its details.
     * If no workout is found for the given date, it notifies the user accordingly.
     */
    private void menuDisplayWorkoutByDate() {
        // Prompt user to enter a date
        String date = getDate();

        // Find the workout for the specified date
        Workout workout = data.findWorkoutByDate(date);

        // If a workout is found for the specified date, display its details
        if (workout != null) {
            System.out.println("Workout Date: " + workout.getDate() + "\n");
            // Get the list of exercises for the workout
            ArrayList<Exercise> exercises = workout.getExercises();

            // Iterate through each exercise in the workout to display its details
            for (Exercise exercise : exercises) {
                System.out.println("\tExercise Name: " + exercise.getName());
                System.out.println("\tSets: " + exercise.getSets());
                System.out.println("\tReps: " + exercise.getReps());
                System.out.println("\tWeight: " + exercise.getWeight() + "kg");
                System.out.println("\tTime: " + exercise.getTime() + " minutes \n");
            }
            // Print a line to act as a separator
            System.out.println("--------------------------------------------------");
        }else{
            // Notify the user if no workout is found for the specified date
            System.out.println("There are no workouts to show. Please add some workouts first.");
        }
        //menuLoop();
    }
}