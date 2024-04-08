# Fitness Tracker

Fitness Tracker is a JavaFX application that allows users to record and track their workout routines, including exercises, sets, reps, weights, and time spent.

## Features

- **Add Workouts**: Users can add details about their workouts, specifying the exercises, sets, reps, weights, and duration.
- **Display Workouts**: Users can view a list of all recorded workouts with details.
- **Search Workouts**: Users can search for workouts by date.
- **Save/Load Workouts**: Workouts can be saved to and loaded from a CSV file, making data persistence possible.
- **About**: Provides information about the application and its authors.

## Prerequisites

To run this application, you'll need:

- Java Runtime Environment (JRE) or Java Development Kit (JDK) version 17 or above.
- JavaFX SDK (compatible with your Java version).

## Setup and Installation

1. Make sure you have Java and JavaFX are installed on your system.
2. You can Clone or download this repository to your local machine.
3. Open the project in your IDE of choice, such as IntelliJ IDEA or Eclipse.
4. Make sure you configure the project to use JavaFX by adding the JavaFX SDK as a library to the project.

## Running the Application

To run the application from an IDE, simply execute the `HelloApplication` class. 

You can also open the folder where the jar file is located and use this command `java -jar run.jar`

To run the application from the command line, navigate to the project directory and use the following command:

```shell
java --module-path /home/itspavit/Desktop/javafx-sdk-22/lib --add-modules javafx.controls,javafx.fxml -jar out/artifacts/unnamed/run.jar
