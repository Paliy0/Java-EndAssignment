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
import javafx.stage.Stage;

import java.util.ArrayList;


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

        menuLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                LoginScreen login = new LoginScreen();
                stage.close();
            }
        });

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

        // Load objects into table
        for (Movie mov : moviesRoom1){
            room1.getItems().add(mov);
        }

        for (Movie mov : moviesRoom2){
            room2.getItems().add(mov);
        }

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
        Label seatsLbl = new Label("Nº of seats");
        seatsLbl.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));
        Label nameLbl = new Label("Name");
        nameLbl.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 12));

        //column 4
        Label title = new Label("Movie Title");
        Spinner<Integer> seats = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200);
        seats.setValueFactory(valueFactory);
        TextField name = new TextField();

        //column 5
        Button purchaseBtn = new Button("Purchase");
        Button clearBtn = new Button("Clear");

        VBox leftPanel = new VBox();
        leftPanel.getChildren().add(room1);
        leftPanel.setPrefWidth(600);
        myGrid.add(leftPanel, 0,4);

        VBox rightPanel = new VBox();
        rightPanel.getChildren().add(room2);
        rightPanel.setPrefWidth(600);
        myGrid.add(rightPanel, 10,4);

        HBox topBar = new HBox();
        topBar.getChildren().add(menuBar);
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
        column4.getChildren().addAll(title, seats, name);
        VBox column5 = new VBox(15);
        column5.getChildren().addAll(purchaseBtn, clearBtn);

        botBar.getChildren().addAll(column1, column2, column3, column4, column5);
        myGrid.add(botBar, 0,6);
        myGrid.add(userLbl, 9,0);
        myGrid.add(purchaseTxt, 0, 2);

        stage.setScene(new Scene(myGrid));
        stage.show();

        purchaseBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                /*
                if (room1.getSelectionModel().getSelectedItem().getSeats() > 0 && room2.getSelectionModel().getSelectedItem().getSeats() <= moviesRoom1  && !name.getText().isEmpty()){

                }

                 */
            }
        });

        clearBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                room.setText("");
                start.setText("");
                end.setText("");
                title.setText("");
                seats.getEditor().clear();
                name.clear();
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
