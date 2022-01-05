package com.example.universityproject;

import javafx.animation.PauseTransition;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LoginScreen {

    public LoginScreen(Database db) {
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setHeight(250);
        stage.setWidth(400);

        GridPane myGrid = new GridPane();
        myGrid.setPadding(new Insets(10, 10, 10, 10));
        myGrid.setVgap(10);
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

        Label errorLbl = new Label("The user name or password is incorrect.");
        myGrid.add(errorLbl, 2, 3);
        errorLbl.setTextFill(Color.RED);
        errorLbl.setVisible(false);
        PauseTransition visiblePause = new PauseTransition( //hides label after 1 sec
                Duration.seconds(1)
        );
        visiblePause.setOnFinished(
                event -> errorLbl.setVisible(false)
        );

        StringProperty passwordFieldProperty = passFld.textProperty();

        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                loginBtn.setVisible(db.PasswordValidation(passFld.getText()));
            }
        });

        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!userFld.getText().isEmpty() && !passFld.getText().isEmpty()){
                    User user = db.validateUser(userFld.getText(), passFld.getText());
                    if (user != null){
                        MainWindow mainWindow = new MainWindow(user, db);
                        stage.close();
                    }
                }
                errorLbl.setVisible(true);
                visiblePause.play();
            }
        });

        stage.setHeight(250);
        stage.setWidth(400);
        stage.setTitle("Login Form");

        Scene scene = new Scene(myGrid);
        stage.setScene(scene);
        stage.show();
    }


}
