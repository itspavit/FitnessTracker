package demo3.fitnesstracker.objects;
/*
 * Fitness Tracker
 *
 * Tutorial 09
 *
 * Daphne Choong - 30171153
 * Pavitpal Singh Bhagat - 30177873
 * Sourav Singh - 30230496
 */


/**
 * The Main class contains the main method to start the fitness tracker application.
 * It initializes the necessary objects and starts the menu loop.
 */
public class Main {
    public static void main(String[] args) {
        // Create an instance of Data
        Data data = new Data();

        // Create an instance of FileIO and pass the Data instance to it
        FileIO file = new FileIO();

            // Create an instance of Menu and pass the Data and FileIO instances to it
        Menu menu = new Menu(data,file);

        // Call the instance method menuLoop on the Menu instance
        menu.menuLoop();
    }
}