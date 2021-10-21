package com.example.universityproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {

    private Database db = new Database();

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


        TableView<Movie> leftPanel = new TableView<>();
        leftPanel.setPlaceholder(new Label("No movies to display for room 1"));
        TableView<Movie> rightPanel = new TableView<>();
        rightPanel.setPlaceholder(new Label("No movies to display for room 2"));

        ObservableList<Movie> movies = FXCollections.observableArrayList(db.getMovies());

        TableColumn<Movie, String> startDateColumn = new TableColumn<>("Start");
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        TableColumn<Movie, String> endDateColumn = new TableColumn<>("End");
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        TableColumn<Movie, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Movie, String> seatsColumn = new TableColumn<>("Seats");
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));

        TableColumn<Movie, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        leftPanel.getColumns().addAll(startDateColumn, endDateColumn, titleColumn, seatsColumn, priceColumn);
        leftPanel.getItems().addAll(movies);

        VBox room1 = new VBox(leftPanel);
        VBox room2 = new VBox(rightPanel);

        room1.getChildren().addAll(leftPanel);
        room2.getChildren().add(rightPanel);

        container.setTop(menuBar);
        container.setRight(userLbl);
        container.setTop(purchaseLbl);
        BorderPane.setAlignment(purchaseLbl, Pos.TOP_LEFT);
        container.setCenter(room1);
        BorderPane.setAlignment(room1, Pos.CENTER_LEFT);
        //container.setCenter(room2);
        //BorderPane.setAlignment(room2, Pos.CENTER_RIGHT);

        stage.setScene(new Scene(container));
        stage.show();
    }

}
