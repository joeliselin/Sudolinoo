package main.java.ch.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Main extends Application {

    private Button[][] sudokuFields = new Button[9][9];

    // Get a new sudoku from sudokuProvider
    SudokuProvider sudokuProvider = new SudokuProvider();
    int[][] sudoku = sudokuProvider.getNewSudoku();


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create grid pane to display sudoku
        GridPane sudokuGrid = new GridPane();
        sudokuGrid.setPadding(new Insets(20, 20, 20, 20));
        sudokuGrid.setHgap(8);
        sudokuGrid.setVgap(8);
        sudokuGrid.setAlignment(Pos.CENTER);

        // Create sudoku fields in grid pane
        for (int row = 0; row < sudokuFields.length; row++) {
            for (int col = 0; col < sudokuFields[0].length; col++) {
                sudokuFields[row][col] = new Button();
                sudokuFields[row][col].setStyle("-fx-background-color: POWDERBLUE; -fx-font-weight: bold");
                sudokuGrid.getChildren().add(sudokuFields[row][col]);
                GridPane.setConstraints(sudokuFields[row][col], row, col);
            }
        }

        // Fill sudoku fields with numbers
        for (int row = 0; row < sudokuFields.length; row++) {
            for (int col = 0; col < sudokuFields[0].length; col++) {
                // Leave fields with value 0 empty
                if (sudoku[row][col] != 0) {
                    sudokuFields[row][col].setText(sudoku[col][row] + "");
                } else {
                    sudokuFields[row][col].setText("  ");
                }
            }
        }

        // Button to get a new Sudoku
        Button getGameButton = new Button("Get Sudoku");

        // Add function to Button:
        getGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                sudokuFields[5][5].setText(":-) :-) :-)");
            }
        });

        // Button to solve the Sudoku
        Button solveButton = new Button("Solve Sudoku");
        solveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        // Create a "created by" comment
        Label createdByMsg = new Label("Created by Roland Jaggi, Michael Wettstein, Joel Iselin");
        createdByMsg.setPadding(new Insets(10, 0, 0, 0));

        // Put buttons and message in a VBox
        VBox buttonVBox = new VBox();
        buttonVBox.setAlignment(Pos.CENTER);
        buttonVBox.getChildren().addAll(getGameButton, solveButton, createdByMsg);

        // Get fxml and add it to grid
        FxController controller = new FxController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setController(controller);
        GridPane fxmlGrid = loader.load();

        // Add all elements to mainVBox
        VBox mainVBox = new VBox(0);
        mainVBox.getChildren().addAll(fxmlGrid, sudokuGrid, buttonVBox);

        // Make mainVBox a scene
        Scene scene = new Scene(mainVBox, 600, 500);

        // Show scene @ primary stage
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
