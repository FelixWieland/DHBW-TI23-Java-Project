package org.dhbw.advancewars.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dhbw.advancewars.Globals;
import org.dhbw.advancewars.MainApplication;

import java.io.IOException;
import java.util.Objects;

public interface IController {
    void init(Stage stage, Scene scene);
}
