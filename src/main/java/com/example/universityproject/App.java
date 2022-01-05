package com.example.universityproject;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) {
        Database newDatabase = new Database();
        LoginScreen loginScreen = new LoginScreen(newDatabase);
        //stage.close();
    }
}