module demo3.fitnesstracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens demo3.fitnesstracker to javafx.fxml;
    exports demo3.fitnesstracker;
}