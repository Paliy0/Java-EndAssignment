package com.example.universityproject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {

    public MainWindow(User currentUser) {
        Stage stage = new Stage();
        stage.setTitle("Cinema App");
        stage.setWidth(1024);
        stage.setHeight(800);
        BorderPane container = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu menuAdmin = new Menu("Admin");
        Menu menuHelp = new Menu("Help");
        Menu menuLogout = new Menu("Logout");
        menuBar.getMenus().addAll(menuAdmin, menuHelp, menuLogout);

        String userType = "user";
        if (currentUser.isAdmin()){
            userType = "admin";
        }

        Label userLbl = new Label(String.format("Logged in as: %s (%s)  ", currentUser.getUserName(), userType));
        Label purchaseLbl = new Label("Purchase tickets");

        container.setTop(menuBar);
        container.setRight(userLbl);
        container.setTop(purchaseLbl);
        BorderPane.setAlignment(purchaseLbl, Pos.TOP_LEFT);

        TableView<Movie> room1 = new TableView<>();
        TableView<User> room2 = new TableView<>();

        VBox leftSide = new VBox(room1);
        VBox rightSide = new VBox(room2);


        stage.setScene(new Scene(container));
        stage.show();
    }

}
