package com.example.universityproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

        ObservableList<Movie> movieList = db.getMovieList();
        Room room1 = db.getRoom1();
        Room room2 = db.getRoom2();

        TableView<Showing> tableRoom1 = new TableView<>();
        tableRoom1.setPlaceholder(new Label("No movies to display for room 1"));
        addToRoom(tableRoom1, room1);

        TableView<Showing> tableRoom2 = new TableView<>();
        tableRoom2.setPlaceholder(new Label("No movies to display for room 2"));
        addToRoom(tableRoom2, room2);

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
        ComboBox<Movie> comboMovies = new ComboBox<>();
        comboMovies.setItems(movieList);

        ComboBox<Room> comboRoom = new ComboBox<>();
        comboRoom.getItems().add(room1);
        comboRoom.getItems().add(room2);
        Label seats = new Label("");
        comboRoom.setOnAction(event -> seats.setText(String.valueOf(comboRoom.getSelectionModel().getSelectedItem().getTotalSeats())));

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
        HBox leftPanel = new HBox(tableRoom1);
        myGrid.add(leftPanel, 0, 4);

        HBox rightPanel = new HBox(tableRoom2);
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

        purchaseTickets.setOnAction(actionEvent -> {
            manageBar.setVisible(false);
            botBar.setVisible(true);
            purchaseTickets.setVisible(false);
            manageShowings.setVisible(true);
            purchaseTxt.setText("Purchase Tickets");
        });
        manageShowings.setOnAction(actionEvent -> {
            manageBar.setVisible(true);
            botBar.setVisible(false);
            purchaseTickets.setVisible(true);//menuitems
            manageShowings.setVisible(false);
            purchaseTxt.setText("Manage Showings");
        });

        Logout.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Are you sure you wish to logout?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                new LoginScreen(db);
                stage.close();
            }
        });

        purchaseBtn.setOnAction(actionEvent -> {
            if (seatSpinner.getValue() < 0 || name.getText().isEmpty()) {
                return;
            }

            if (tableRoom1.getSelectionModel().getSelectedIndex() != -1) {
                purchaseTickets(tableRoom1, seatSpinner.getValue());

            } else if (tableRoom2.getSelectionModel().getSelectedIndex() != -1) {
                purchaseTickets(tableRoom2, seatSpinner.getValue());
            }
            //if no showing is selected..
        });

        addBtn.setOnAction(actionEvent -> {

            String selectedMovieTitle = comboMovies.getSelectionModel().getSelectedItem().toString();
            LocalDate datePart = datePicker.getValue();
            LocalTime timePart = LocalTime.parse(timePicker.getText());
            LocalDateTime dateTime = LocalDateTime.of(datePart, timePart);

            Movie selectedMovie = validateShowing(selectedMovieTitle, dateTime, movieList);
            if (selectedMovie == null){
                //TODO: error when adding showing
                return;
            }

            if (comboRoom.getSelectionModel().getSelectedItem().toString().equals("Room 1")){
                room1.addShowing(selectedMovie, dateTime);
            }
            else{
                room2.addShowing(selectedMovie, dateTime);
            }

            //get data
            //add to list.
        });

        clearBtn.setOnAction(actionEvent -> {
            room.setText("");
            start.setText("");
            end.setText("");
            title.setText("");
            seatSpinner.getEditor().clear();
            name.clear();
        });


        clearButton.setOnAction(actionEvent -> {
            comboMovies.getSelectionModel().clearSelection();
            comboRoom.getSelectionModel().clearSelection();
            seats.setText("");
            datePicker.getEditor().clear();
            endDate.setText("");
            timePicker.clear();
            price.setText("");
        });

        tableRoom1.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Showing>) change -> {
            if (tableRoom1.getSelectionModel().getSelectedIndex() == -1) {return; }

            room.setText(String.valueOf(room1.getId()));
            start.setText(tableRoom1.getSelectionModel().getSelectedItem().getStart());
            end.setText(tableRoom1.getSelectionModel().getSelectedItem().getEnd());
            title.setText(tableRoom1.getSelectionModel().getSelectedItem().getMovieTitle());

            tableRoom2.getSelectionModel().clearSelection();
        });

        tableRoom2.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Showing>) change -> {
            if (tableRoom2.getSelectionModel().getSelectedIndex() == -1) {return;}

            room.setText(String.valueOf(room2.getId()));
            start.setText(tableRoom2.getSelectionModel().getSelectedItem().getStart());
            end.setText(tableRoom2.getSelectionModel().getSelectedItem().getEnd());
            title.setText(tableRoom2.getSelectionModel().getSelectedItem().getMovieTitle());

            tableRoom1.getSelectionModel().clearSelection();
        });
    }

    private void addToRoom(TableView<Showing> table, Room room) {
        table.setItems(room.getShowings());
        table.setMinSize(450, 200);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        TableColumn<Showing, String> startColumn = new TableColumn<>("Start Date");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        table.getColumns().add(startColumn);
        TableColumn<Showing, String> endColumn = new TableColumn<>("End Date");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        table.getColumns().add(endColumn);
        TableColumn<Showing, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMovieTitle()));
        table.getColumns().add(titleColumn);
        TableColumn<Showing, String> seatsColumn = new TableColumn<>("Seats");
        seatsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAvailableSeats()));
        table.getColumns().add(seatsColumn);
        TableColumn<Showing, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMoviePrice()));
        table.getColumns().add(priceColumn);
    }

    private Movie validateShowing(String selectedMovie, LocalDateTime dateTime, ObservableList<Movie> movieList){
        if (selectedMovie != null || dateTime != null){
            for (Movie movie : movieList){
                if (movie.getTitle().equals(selectedMovie)){
                    return movie;
                }
            }
        }
        return null;
    }

    private void purchaseTickets(TableView<Showing> table, int amount){
        Showing selectedShowing = table.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Do you wish to purchase " + amount + " tickets?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                selectedShowing.purchaseSeats(amount);
            }
            catch (IllegalArgumentException e) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("ERROR");
                alert1.setHeaderText(e.getMessage());
                alert1.showAndWait();
            }
        }
        else
        {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("INFORMATION");
            alert1.setHeaderText("Purchase cancelled by user");
            alert1.showAndWait();
        }
        table.refresh();
        table.getSelectionModel().clearSelection();
    }
}
