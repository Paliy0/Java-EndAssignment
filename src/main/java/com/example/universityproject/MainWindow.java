package com.example.universityproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;

public class MainWindow {

    private Database db = new Database();

    public MainWindow(User currentUser) {
        Stage stage = new Stage();
        stage.setTitle("Cinema App");
        stage.setWidth(1024);
        stage.setHeight(800);

        GridPane myGrid = new GridPane();
        myGrid.setPadding(new Insets(10, 10, 10, 10));
        myGrid.setVgap(10);
        myGrid.setHgap(8);

        MenuBar menuBar = new MenuBar();
        Menu menuAdmin = new Menu("Admin");
        Menu menuHelp = new Menu("Help");
        Menu menuLogout = new Menu("Logout");
        menuBar.getMenus().addAll(menuAdmin, menuHelp, menuLogout);

        String userType = "user";
        if (currentUser.isAdmin()){
            userType = "admin";
        }

        Text purchaseTxt = new Text("Purchase Tickets");
        purchaseTxt.setStyle("-fx-font-size: 18px;");

        Label userLbl = new Label(String.format("Logged in as: %s (%s)  ", currentUser.getUserName(), userType));

        ObservableList<Movie> movies = FXCollections.observableArrayList(db.getMovies());

        TableView<Movie> room1 = new TableView<>();
        room1.setPlaceholder(new Label("No movies to display for room 1"));
        TableView<Movie> room2 = new TableView<>();
        room2.setPlaceholder(new Label("No movies to display for room 2"));

        TableColumn<Movie, String> startColumn = new TableColumn<>("Start Date");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        room1.getColumns().add(startColumn);
        TableColumn<Movie, String> endColumn = new TableColumn<>("End Date");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        room1.getColumns().add(endColumn);
        TableColumn<Movie, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        room1.getColumns().add(titleColumn);
        TableColumn<Movie, String> seatsColumn = new TableColumn<>("Seats");
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        room1.getColumns().add(seatsColumn);
        TableColumn<Movie, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        room1.getColumns().add(priceColumn);

        // Load objects into table
        for (Movie mov : movies){
            room1.getItems().add(mov);
        }

        VBox leftPanel = new VBox();
        leftPanel.getChildren().add(room1);
        myGrid.add(leftPanel, 0,4);
        leftPanel.setPrefWidth(500);

        VBox rightPanel = new VBox();
        rightPanel.getChildren().add(room2);
        myGrid.add(rightPanel, 8,4);

        HBox topBar = new HBox();
        topBar.getChildren().add(menuBar);
        myGrid.add(topBar, 0,0);

        myGrid.add(userLbl, 20,0);
        myGrid.add(purchaseTxt, 0, 2);


        stage.setScene(new Scene(myGrid));
        stage.show();
    }

}
