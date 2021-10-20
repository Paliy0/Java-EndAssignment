package com.example.universityproject;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {

    public MainWindow(User currentUser) {
        Stage stage = new Stage();
        stage.setTitle("Cinema App");
        stage.setWidth(1024);
        stage.setHeight(800);

        VBox layout = new VBox();
        layout.setPadding(new Insets(10));

        MenuBar menuBar = new MenuBar();
        Menu menuAdmin = new Menu("Admin");
        Menu menuHelp = new Menu("Help");
        Menu menuLogout = new Menu("Logout");
        menuBar.getMenus().addAll(menuAdmin, menuHelp, menuLogout);


        //((VBox) scene.getRoot()).getChildren().addAll(menuBar);
        TableView<User> tableView = new TableView<>();



        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

}
