package com.example.universityproject;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {

    public MainWindow() {
        Stage stage = new Stage();
        stage.setWidth(1024);
        stage.setHeight(800);

        VBox box = new VBox();
        Label welcomeLabel = new Label("Welcome ");
        box.getChildren().add(welcomeLabel);

        Scene scene = new Scene(box);
        stage.setScene(scene);
        stage.show();
    }

}
