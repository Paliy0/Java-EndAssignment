package com.example.universityproject;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreen extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage){

        GridPane myGrid = new GridPane();
        myGrid.setPadding(new Insets(10, 10, 10, 10));
        myGrid.setVgap(10); // Vertical spacing between grid items
        myGrid.setHgap(8);

        Label userLbl = new Label("Username");
        myGrid.add(userLbl, 1, 0);

        TextField userFld = new TextField();
        myGrid.add(userFld, 2 ,0);

        Label passLbl = new Label("Password");
        myGrid.add(passLbl, 1, 1);

        PasswordField passFld = new PasswordField();
        myGrid.add(passFld, 2, 1);

        Button loginBtn = new Button("Login");
        myGrid.add(loginBtn, 2,2);
        loginBtn.setVisible(false);

        StringProperty passwordFieldProperty = passFld.textProperty();

        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String password = passFld.getText();
                passwordValidation(password);
            }
        });

        stage.setHeight(250);
        stage.setWidth(400);
        stage.setTitle("Login Form");

        Scene scene = new Scene(myGrid);
        stage.setScene(scene);
        stage.show();
    }

    private Boolean passwordValidation(String password) {
        boolean charPresent = false;
        char[] specialChars = {'+', '-', '&', '|', '!', '(', ')', '{', '}', '[', ']', '^', '~', '*', '?', ':'};

        for(char c : password.toCharArray()) {
            if(Character.isDigit(c)){
                return true;
            }
            //else if (){

            //}
        }
        return true;
    }
}