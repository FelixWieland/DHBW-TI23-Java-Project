module org.dhbw.advancewars {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens org.dhbw.advancewars to javafx.fxml;
    exports org.dhbw.advancewars;
    exports org.dhbw.advancewars.level;
    opens org.dhbw.advancewars.level to javafx.fxml;
    exports org.dhbw.advancewars.entity;
    opens org.dhbw.advancewars.entity to javafx.fxml;
    exports org.dhbw.advancewars.util;
    opens org.dhbw.advancewars.util to javafx.fxml;
    exports org.dhbw.advancewars.controller;
    opens org.dhbw.advancewars.controller to javafx.fxml;
}