module org.dhbw.advancewars {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.dhbw.advancewars to javafx.fxml;
    exports org.dhbw.advancewars;
    exports org.dhbw.advancewars.level;
    opens org.dhbw.advancewars.level to javafx.fxml;
}