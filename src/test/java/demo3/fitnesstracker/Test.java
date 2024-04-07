package demo3.fitnesstracker;

import demo3.fitnesstracker.objects.Data;
import demo3.fitnesstracker.objects.Exercise;
import demo3.fitnesstracker.objects.Workout;

import static org.junit.jupiter.api.Assertions.*;

class Test {

    @org.junit.jupiter.api.Test
    void addExerciseToWorkout1() {
        Data data = new Data();
        Exercise exercise = new Exercise("Push-up", 3, 10, 60, 120);
        String date = "2024-03-17";

        // Adding exercise to work out
        data.addExerciseToWorkout(date, exercise);

        // Check if the exercise was added to the correct workout
        Workout workout = data.findWorkoutByDate(date);
        assertNotNull(workout);
        assertEquals(1, workout.getExercises().size());
        assertEquals(exercise, workout.getExercises().get(0));
    }
    @org.junit.jupiter.api.Test
    public void ddExerciseToWorkout2() {
        Data data = new Data();
        Exercise exercise1 = new Exercise("Push-up", 3, 10, 45, 120);
        Exercise exercise2 = new Exercise("Sit-up", 3, 15, 25, 60);
        String date = "2024-03-17";

        // Adding exercise to existing workout
        data.addExerciseToWorkout(date, exercise1);
        data.addExerciseToWorkout(date, exercise2);

        // Retrieve the workout and verify exercises
        Workout workout = data.findWorkoutByDate(date);
        assertNotNull(workout);
        assertEquals(2, workout.getExercises().size());
        assertTrue(workout.getExercises().contains(exercise1));
        assertTrue(workout.getExercises().contains(exercise2));
    }
    @org.junit.jupiter.api.Test
    public void ddExerciseToWorkout3() {
        Data data = new Data();
        Exercise exercise = new Exercise("Push-up", 3, 10, 55, 30);
        String date = "2024-03-17";

        // Adding exercise to nonexistent workout
        data.addExerciseToWorkout(date, exercise);

        // Retrieve the workout and verify exercise
        Workout workout = data.findWorkoutByDate(date);
        assertNotNull(workout);
        assertEquals(1, workout.getExercises().size());
        assertEquals(exercise, workout.getExercises().get(0));
    }


    @org.junit.jupiter.api.Test
    void findWorkoutByDate1() {
        Data data = new Data();
        String date = "2024-03-18";

        // No workout for this date initially
        assertNull(data.findWorkoutByDate(date));

        // Add a workout for the date
        Workout workout = new Workout(date);
        data.workouts().addWorkout(workout);

        // Verify that the workout can be found
        assertEquals(workout, data.findWorkoutByDate(date));
    }

    @org.junit.jupiter.api.Test
    void getAllWorkouts1() {
        Data data = new Data();
        assertNotNull(data.workouts());
        System.out.println("All workouts retrieved successfully!");

        // Ensure initially there are no workouts
        assertEquals(0, data.workouts().getWorkouts().size());
        System.out.println("No workouts initially.");

        // Add some workouts
        data.addExerciseToWorkout("2024-03-17", new Exercise("Push-up", 3, 10, 45, 55));
        data.addExerciseToWorkout("2024-03-17", new Exercise("Sit-up", 3, 15, 45, 60));

        // Verify that all added workouts are retrieved
        assertEquals(1, data.workouts().getWorkouts().size());
        System.out.println("Workouts added successfully.");

        // Print details of workouts and exercises
        System.out.println("Workout details:");
        for (Workout workout : data.workouts().getWorkouts()) {
            System.out.println("Date: " + workout.getDate());
            for (Exercise exercise : workout.getExercises()) {
                System.out.println("Exercise: " + exercise.getName());
                System.out.println("Sets: " + exercise.getSets());
                System.out.println("Reps: " + exercise.getReps());
                System.out.println("Time: " + exercise.getTime());
                System.out.println("Weight: " + exercise.getWeight());
                System.out.println();
            }
        }
    }
}