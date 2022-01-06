package com.example.universityproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        if (currentUser.isAdmin()) {
            userType = "admin";
        }

        Text purchaseTxt = new Text("Purchase Tickets");
        purchaseTxt.setStyle("-fx-font-size: 18px;");

        Label userLbl = new Label(String.format("Logged in as: %s (%s)", currentUser.getUserName(), userType));

        ObservableList<Movie> movieList = FXCollections.observableArrayList(db.getMovieList());
        ObservableList<Showing> moviesRoom1 = FXCollections.observableArrayList(db.getRoom1());
        ObservableList<Showing> moviesRoom2 = FXCollections.observableArrayList(db.getRoom2());

        TableView<Showing> room1 = new TableView<>();
        room1.setPlaceholder(new Label("No movies to display for room 1"));
        addToRoom(room1, moviesRoom1);

        TableView<Showing> room2 = new TableView<>();
        room2.setPlaceholder(new Label("No movies to display for room 2"));
        addToRoom(room2, moviesRoom2);

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
        for (Movie movie : movieList) {
            comboMovies.getItems().add(movie.getTitle());
        }
        ComboBox comboRoom = new ComboBox();
        comboRoom.getItems().add("Room 1");
        comboRoom.getItems().add("Room 2");
        Label seats = new Label("");
        comboRoom.setOnAction((event) -> {
            if (comboRoom.getSelectionModel().getSelectedIndex() == 1) {
                seats.setText("100");
            } else {
                seats.setText("200");
            }
        });

        //column 3
        Label movieStart = new Label("Start");
        movieStart.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label movieEnd = new Label("End");
        movieEnd.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label moviePrice = new Label("Price");
        moviePrice.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        //column 4
        DatePicker datePicker = new DatePicker();
        Label endDate = new Label("endDate");
        Label price = new Label("Price");
        //column 5
        TextField timePicker = new TextField();
        //column 6
        Button addBtn = new Button("Add showing");
        Button clearButton = new Button("Clear");

        //controls
        HBox leftPanel = new HBox(room1);
        myGrid.add(leftPanel, 0, 4);

        HBox rightPanel = new HBox(room2);
        myGrid.add(rightPanel, 7, 4);

        HBox topBar = new HBox(100, menuBar, userLbl);
        myGrid.add(topBar, 0, 0);

        HBox botBar = new HBox(10);
        VBox column1 = new VBox(10, roomLbl, startLbl, endLbl);
        VBox column2 = new VBox(10, room, start, end);
        VBox column3 = new VBox(10, titleLbl, seatsLbl, nameLbl);
        VBox column4 = new VBox(10, title, seatSpinner, name);
        VBox column5 = new VBox(15, purchaseBtn, clearBtn);

        botBar.getChildren().addAll(column1, column2, column3, column4, column5);

        HBox manageBar = new HBox(10);
        manageBar.setPrefWidth(3000);
        VBox col1 = new VBox(20, movieTitle, movieRoom, movieSeats);
        col1.setPrefWidth(350);
        VBox col2 = new VBox(20, comboMovies, comboRoom, seats);
        col2.setPrefWidth(350);
        VBox col3 = new VBox(20, movieStart, movieEnd, moviePrice);
        col3.setPrefWidth(350);
        VBox col4 = new VBox(18, datePicker, endDate, price);
        col4.setPrefWidth(350);
        VBox col5 = new VBox(20, timePicker);
        col5.setPrefWidth(350);
        VBox col6 = new VBox(10, addBtn, clearButton);
        col6.setPrefWidth(350);
        manageBar.getChildren().addAll(col1, col2, col3, col4, col5, col6);

        myGrid.add(manageBar, 0, 6);
        manageBar.setVisible(false);
        myGrid.add(botBar, 0, 6);
        botBar.setVisible(true);
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
                purchaseTickets.setVisible(true);//menuitems
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
                if (result.get() == ButtonType.OK) {
                    new LoginScreen(db);
                    stage.close();
                }
            }
        });

        purchaseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (seatSpinner.getValue() > 0 && !name.getText().isEmpty()) {
                    if (room1.getSelectionModel().getSelectedIndex() != -1) {
                        Showing selectedShowing = room1.getSelectionModel().getSelectedItem();

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Do you wish to purchase " + seatSpinner.getValue() + " tickets?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            Integer seatsLeft = selectedShowing.getSeats() - seatSpinner.getValue();
                            if (seatsLeft >= 0) {
                                selectedShowing.setSeats(selectedShowing.getSeats() - seatSpinner.getValue());
                            } else {
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("ERROR");
                                alert1.setHeaderText("Tickets not available");
                                alert1.showAndWait();
                            }
                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("INFORMATION");
                            alert1.setHeaderText("Purchase cancelled by user");
                            alert1.showAndWait();
                        }
                        room1.refresh();
                        room1.getSelectionModel().clearSelection();
                    } else if (room2.getSelectionModel().getSelectedIndex() != -1) {
                        Showing selectedShowing = room2.getSelectionModel().getSelectedItem();

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText("Do you wish to purchase " + seatSpinner.getValue() + " tickets?");
                        //alert.showAndWait();
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            int seatsLeft = selectedShowing.getSeats() - seatSpinner.getValue();
                            if (seatsLeft >= 0) {
                                selectedShowing.setSeats(selectedShowing.getSeats() - seatSpinner.getValue());
                            } else {
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setTitle("ERROR");
                                alert1.setHeaderText("Tickets not available");
                                alert1.showAndWait();
                            }
                        } else {
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

                String selectedMovie = comboMovies.getSelectionModel().getSelectedItem().toString();
                int seats = 200;
                if(comboRoom.getSelectionModel().getSelectedItem().toString().equals("Room 2")){
                    seats = 100;
                }
                LocalDate datePart = datePicker.getValue();
                LocalTime timePart = LocalTime.parse(timePicker.getText());
                LocalDateTime dateTime = LocalDateTime.of(datePart, timePart);

                if (comboRoom.getSelectionModel().getSelectedItem().toString().equals("Room 1")){
                    moviesRoom1.add(checkData(selectedMovie, seats, dateTime, movieList));
                }
                else{
                    moviesRoom2.add(checkData(selectedMovie, seats, dateTime, movieList));
                }


                //get data
                //add to list.
            }
        });

        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                room.setText("");
                start.setText("");
                end.setText("");
                title.setText("");
                seatSpinner.getEditor().clear();
                name.clear();
            }
        });


        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                comboMovies.getSelectionModel().clearSelection();
                comboRoom.getSelectionModel().clearSelection();
                seats.setText("");
                datePicker.getEditor().clear();
                endDate.setText("");
                timePicker.clear();
                price.setText("");
            }
        });

        room1.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Showing>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Showing> change) {
                if (room1.getSelectionModel().getSelectedIndex() > -1) {
                    if (botBar.isVisible()) {
                        room.setText("1");
                        start.setText(room1.getSelectionModel().getSelectedItem().getStart());
                        end.setText(room1.getSelectionModel().getSelectedItem().getEnd());
                        title.setText(room1.getSelectionModel().getSelectedItem().getMovieTitle());
                    }
                    //comboMovies
                    //comboMovies.comboRoom, seats
                }
            }
        });

        room2.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Showing>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Showing> change) {
                if (room2.getSelectionModel().getSelectedIndex() > -1) {
                    //botBar.setVisible(true);
                    room.setText("2");
                    start.setText(room2.getSelectionModel().getSelectedItem().getStart().toString());
                    end.setText(room2.getSelectionModel().getSelectedItem().getEnd().toString());
                    title.setText(room2.getSelectionModel().getSelectedItem().getMovieTitle());
                }
            }
        });
    }

    private void addToRoom(TableView<Showing> room, ObservableList<Showing> showings) {
        room.setItems(showings);
        room.setMinSize(450, 200);
        room.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        TableColumn<Showing, String> startColumn = new TableColumn<>("Start Date");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        room.getColumns().add(startColumn);
        TableColumn<Showing, String> endColumn = new TableColumn<>("End Date");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        room.getColumns().add(endColumn);
        TableColumn<Showing, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMovieTitle()));
        room.getColumns().add(titleColumn);
        TableColumn<Showing, String> seatsColumn = new TableColumn<>("Seats");
        seatsColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        room.getColumns().add(seatsColumn);
        TableColumn<Showing, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMoviePrice()));
        room.getColumns().add(priceColumn);
    }

    private Showing checkData(String selectedMovie, int seats, LocalDateTime dateTime, ObservableList<Movie> movieList){
        if (selectedMovie != null || seats > 0 || dateTime != null){
            for (Movie movie : movieList){
                if (movie.getTitle().equals(selectedMovie)){
                    return new Showing(movie, seats, dateTime, movie.getEnd(dateTime));
                }
            }
        }
        return null;
    }

}
