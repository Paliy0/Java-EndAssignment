package com.example.universityproject;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class MainWindow {

    public MainWindow(User currentUser, Database db) {
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
        MenuItem purchaseTickets = new MenuItem("Purchase tickets");
        purchaseTickets.setVisible(false);
        MenuItem manageShowings = new MenuItem("Manage showings");
        manageShowings.setVisible(currentUser.isAdmin());
        menuAdmin.getItems().addAll(purchaseTickets, manageShowings);
        //add manage movies
        Menu menuHelp = new Menu("Help");
        Menu menuLogout = new Menu("Logout");
        MenuItem Logout = new MenuItem("Log out");
        menuLogout.getItems().add(Logout);
        menuBar.getMenus().addAll(menuAdmin, menuHelp, menuLogout);

        String userType = "user";
        if (currentUser.isAdmin()){
            userType = "admin";
        }

        Text purchaseTxt = new Text("Purchase Tickets");
        purchaseTxt.setStyle("-fx-font-size: 18px;");

        Label userLbl = new Label(String.format("Logged in as: %s (%s)", currentUser.getUserName(), userType));

        ObservableList<Movie> moviesRoom1 = FXCollections.observableArrayList(db.getRoom1());
        ObservableList<Movie> moviesRoom2 = FXCollections.observableArrayList(db.getRoom2());

        TableView<Movie> room1 = new TableView<>();
        room1.setPlaceholder(new Label("No movies to display for room 1"));
        room1.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        TableView<Movie> room2 = new TableView<>();
        room2.setPlaceholder(new Label("No movies to display for room 2"));
        room2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

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

        TableColumn<Movie, String> startColumn2 = new TableColumn<>("Start Date");
        startColumn2.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        room2.getColumns().add(startColumn2);
        TableColumn<Movie, String> endColumn2 = new TableColumn<>("End Date");
        endColumn2.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        room2.getColumns().add(endColumn2);
        TableColumn<Movie, String> titleColumn2 = new TableColumn<>("Title");
        titleColumn2.setCellValueFactory(new PropertyValueFactory<>("title"));
        room2.getColumns().add(titleColumn2);
        TableColumn<Movie, String> seatsColumn2 = new TableColumn<>("Seats");
        seatsColumn2.setCellValueFactory(new PropertyValueFactory<>("seats"));
        room2.getColumns().add(seatsColumn2);
        TableColumn<Movie, String> priceColumn2 = new TableColumn<>("Price");
        priceColumn2.setCellValueFactory(new PropertyValueFactory<>("price"));
        room2.getColumns().add(priceColumn2);

        // Load objects in room
        for (Movie mov : moviesRoom1){
            room1.getItems().add(mov);
        }
        for (Movie mov : moviesRoom2){
            room2.getItems().add(mov);
        }

        //Botbar Controls
        //column 1
        Label roomLbl = new Label("Room");
        roomLbl.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label startLbl = new Label("Start");
        startLbl.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label endLbl = new Label("End");
        endLbl.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        //column 2
        Label room = new Label("");
        Label start = new Label("");
        Label end = new Label("");
        //column 3
        Label titleLbl = new Label("Movie Title");
        titleLbl.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label seatsLbl = new Label("Nº of tickets");
        seatsLbl.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label nameLbl = new Label("Name");
        nameLbl.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        //column 4
        Label title = new Label("");
        Spinner<Integer> seatSpinner = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200);
        seatSpinner.setValueFactory(valueFactory);
        TextField name = new TextField();
        //column 5
        Button purchaseBtn = new Button("Purchase");
        Button clearBtn = new Button("Clear");

        //ManageBar Controls
        //column 1
        Label movieTitle = new Label("Movie title");
        movieTitle.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label movieRoom = new Label("Room");
        movieRoom.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label movieSeats = new Label("Nº of seats");
        movieSeats.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        //column 2
        ComboBox comboMovies = new ComboBox();
        for(Movie movie : moviesRoom1){
            comboMovies.getItems().add(movie.getTitle());
        }
        ComboBox comboRoom = new ComboBox();
        comboRoom.getItems().add("Room 1");
        comboRoom.getItems().add("Room 2");
        Label seats = new Label("");
        comboRoom.setOnAction((event) -> {
            if(comboRoom.getSelectionModel().getSelectedIndex() == 1){
                seats.setText("100");
            }
            else {
                seats.setText("200");
            }

        });

        //column 3
        Label movieStart = new Label("Start");
        movieStart.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label movieEnd = new Label("End");
        movieEnd.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label moviePrice = new Label("Name");
        moviePrice.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        //column 4
        DatePicker datePicker = new DatePicker();
        Label endDate = new Label();
        Label price = new Label("Price");
        //column 5
        TextField timePicker = new TextField();
        //column 6
        Button addBtn = new Button("Add showing");

        //controls
        VBox leftPanel = new VBox();
        leftPanel.getChildren().add(room1);
        leftPanel.setPrefWidth(600);
        myGrid.add(leftPanel, 0,4);

        VBox rightPanel = new VBox();
        rightPanel.getChildren().add(room2);
        rightPanel.setPrefWidth(600);
        myGrid.add(rightPanel, 10,4);

        HBox topBar = new HBox(100, menuBar, userLbl);
        myGrid.add(topBar, 0,0);

        HBox botBar = new HBox(10);
        botBar.setVisible(false);
        VBox column1 = new VBox(10);
        column1.getChildren().addAll(roomLbl, startLbl, endLbl);
        VBox column2 = new VBox(10);
        column2.getChildren().addAll(room, start, end);
        VBox column3 = new VBox(10);
        column3.getChildren().addAll(titleLbl, seatsLbl, nameLbl);
        VBox column4 = new VBox(10);
        column4.getChildren().addAll(title, seatSpinner, name);
        VBox column5 = new VBox(15);
        column5.getChildren().addAll(purchaseBtn, clearBtn);
        botBar.getChildren().addAll(column1, column2, column3, column4, column5);


        HBox manageBar = new HBox();
        manageBar.setVisible(false);
        VBox col1 = new VBox(10);
        col1.setPadding(new Insets(15, 15, 15, 15));
        col1.getChildren().addAll(movieTitle, movieRoom, movieSeats);
        VBox col2 = new VBox(10);
        col2.setPadding(new Insets(15, 15, 15, 15));
        col2.getChildren().addAll(comboMovies, comboRoom, seats);
        VBox col3 = new VBox(10);
        col3.setPadding(new Insets(15, 15, 15, 15));
        col3.getChildren().addAll(movieStart, movieEnd, moviePrice);
        VBox col4 = new VBox(10);
        //col4.setPadding(new Insets(15, 15, 15, 15));
        col4.getChildren().addAll(datePicker, endDate, price);
        VBox col5 = new VBox(10);
        //col5.setPadding(new Insets(15, 15, 15, 15));
        col5.getChildren().addAll(timePicker);
        VBox col6 = new VBox(10);
        //col6.setPadding(new Insets(15, 15, 15, 15));
        col6.getChildren().addAll(addBtn, clearBtn);
        manageBar.getChildren().addAll(col1, col2, col3, col4, col5, col6);

        myGrid.add(manageBar, 0, 6);
        myGrid.add(botBar, 0,6);
        myGrid.add(purchaseTxt, 0, 2);
        stage.setScene(new Scene(myGrid));
        stage.show();

        purchaseTickets.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                manageBar.setVisible(false);
                botBar.setVisible(true);
                purchaseTickets.setVisible(false);
                manageShowings.setVisible(true);
                purchaseTxt.setText("Purchase Tickets");
            }
        });
        manageShowings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                manageBar.setVisible(true);
                botBar.setVisible(false);
                purchaseTickets.setVisible(true);
                manageShowings.setVisible(false);
                purchaseTxt.setText("Manage Showings");
            }
        });

        Logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Are you sure you wish to logout?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    new LoginScreen().start(new Stage());
                    stage.close();
                }
            }
        });

        purchaseBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                if (seatSpinner.getValue() > 0 && !name.getText().isEmpty()){
                    if (room1.getSelectionModel().getSelectedIndex() != -1){
                        Movie selectedMovie = room1.getSelectionModel().getSelectedItem();

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Do you wish to purchase " + seatSpinner.getValue() + " tickets?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            Integer seatsLeft = selectedMovie.getSeats() - seatSpinner.getValue();
                            if (seatsLeft >= 0){
                                selectedMovie.setSeats(selectedMovie.getSeats() - seatSpinner.getValue());
                            }
                            else {
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("ERROR");
                                alert1.setHeaderText("Tickets not available");
                                alert1.showAndWait();
                            }
                        }
                        else {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("INFORMATION");
                            alert1.setHeaderText("Purchase cancelled by user");
                            alert1.showAndWait();
                        }
                        room1.refresh();
                        room1.getSelectionModel().clearSelection();
                    }
                    else if (room2.getSelectionModel().getSelectedIndex() != -1){
                        Movie selectedMovie = room2.getSelectionModel().getSelectedItem();

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Do you wish to purchase " + seatSpinner.getValue() + " tickets?");
                        //alert.showAndWait();
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            Integer seatsLeft = selectedMovie.getSeats() - seatSpinner.getValue();
                            if (seatsLeft >= 0){
                                selectedMovie.setSeats(selectedMovie.getSeats() - seatSpinner.getValue());
                            }
                            else {
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("ERROR");
                                alert1.setHeaderText("Tickets not available");
                                alert1.showAndWait();
                            }
                        }
                        else {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("INFORMATION");
                            alert1.setHeaderText("Purchase cancelled by user");
                            alert1.showAndWait();
                        }
                        room2.refresh();
                        room2.getSelectionModel().clearSelection();
                    }
                }
            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //comboMovies.getSelectionModel().getSelectedItem();
            }
        });

        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(botBar.isVisible()){
                    room.setText("");
                    start.setText("");
                    end.setText("");
                    title.setText("");
                    seatSpinner.getEditor().clear();
                    name.clear();
                }
                else{
                    comboMovies.getSelectionModel().clearSelection();
                    comboRoom.getSelectionModel().clearSelection();
                    seats.setText("");
                    datePicker.getEditor().clear();
                    endDate.setText("");
                    timePicker.clear();
                    price.setText("");
                }

            }
        });

        room1.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Movie>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Movie> change) {
                if(room1.getSelectionModel().getSelectedIndex() > -1){
                    botBar.setVisible(true);
                    room.setText("1");
                    start.setText(room1.getSelectionModel().getSelectedItem().getStartDate().toString());
                    end.setText(room1.getSelectionModel().getSelectedItem().getEndDate().toString());
                    title.setText(room1.getSelectionModel().getSelectedItem().getTitle());
                }
            }
        });

        room2.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Movie>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Movie> change) {
                if(room2.getSelectionModel().getSelectedIndex() > -1){
                    botBar.setVisible(true);
                    room.setText("2");
                    start.setText(room2.getSelectionModel().getSelectedItem().getStartDate().toString());
                    end.setText(room2.getSelectionModel().getSelectedItem().getEndDate().toString());
                    title.setText(room2.getSelectionModel().getSelectedItem().getTitle());
                }
            }
        });
    }

}
