import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.swing.*;

public class ConnectFourController {

    @FXML
    private GridPane grid;

    private final int ROW_SIZE = 6;
    private final int COL_SIZE = 7;
    private Button[][] btns;
    private Circle[][] circles; // Store the circles placed on the grid
    private boolean isPlayerOneTurn = true; // Track player turns
    private ConnectFourGame game;


    public void initialize(){
        game = new ConnectFourGame();
        btns = new Button[ROW_SIZE][COL_SIZE];
        circles = new Circle[ROW_SIZE][COL_SIZE];

        double cellWidth = grid.getPrefWidth() / COL_SIZE;
        double cellHeight = grid.getPrefHeight() / ROW_SIZE;

        initializeGrid(cellWidth, cellHeight); // Initialize the grid with empty placeholders
        initializeButtons(cellWidth, cellHeight);// Initialize buttons on the last row
    }

    private void initializeGrid(double cellWidth, double cellHeight) {
        for (int i = 0; i < ROW_SIZE-1; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                initializePlaceholder(cellWidth, cellHeight, i, j);
                initializeCircle(cellWidth, i, j);
            }
        }
    }

    private void initializePlaceholder(double cellWidth, double cellHeight, int row, int col) {
        Button placeholder = new Button("");
        placeholder.setDisable(true); // Make the placeholders non-interactive
        placeholder.setPrefSize(cellWidth, cellHeight);
        grid.add(placeholder, col, row);
    }

    private void initializeCircle(double cellWidth, int row, int col) {
        circles[row][col] = new Circle(cellWidth / 2); // Circle radius
        circles[row][col].setFill(Color.TRANSPARENT); // Initially transparent
        grid.add(circles[row][col], col, row);
    }

    private void initializeButtons(double cellWidth, double cellHeight) {
        for (int j = 0; j < COL_SIZE; j++) {
            btns[ROW_SIZE-1][j] = new Button((j + 1) + ""); // With numbers 1-7
            btns[ROW_SIZE-1][j].setPrefSize(cellWidth, cellHeight); // Size of the cells
            grid.add(btns[ROW_SIZE-1][j], j, ROW_SIZE - 1); // Add buttons

            btns[ROW_SIZE-1][j].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    handleButton(event);
                }// Anonymous class
            });
        }
    }

    private void handleButton(ActionEvent event){
        try {
            int clickedCol = GridPane.getColumnIndex((Button) event.getSource());// Get the column index of the button

            // Check if the column is full
            if (isColumnFull(clickedCol)) {
                showJOptionPane("This column is full. Choose another column.");
                return;
            }

            // Fill with circles from the bottom to top
            for (int i = ROW_SIZE - 2; i >= 0; i--) { // ROW_SIZE - 2 = above the buttons row
                if (isCircleColorTransparent(i, clickedCol)){ // If true, fill with the player color
                    if (game.checkWin(i, clickedCol, circles)) {
                        showJOptionPane("Player " + (isPlayerOneTurn ? "One" : "Two") + " wins!");
                        disableButtons();
                    }
                    isPlayerOneTurn = !isPlayerOneTurn; // Switch turns
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            showJOptionPane("An error occurred while process");
        }
    }

    private boolean isCircleColorTransparent(int row, int col){
        if (circles[row][col].getFill() == Color.TRANSPARENT) {
            circles[row][col].setFill(isPlayerOneTurn ? Color.RED : Color.BLUE);
            return true;
        }
        return false;
    }

    private boolean isColumnFull(int col) {
        return circles[0][col].getFill() != Color.TRANSPARENT;
    }

    @FXML
    void clearPressed(ActionEvent event) {
        clearCircles();
        enableButtons();
        isPlayerOneTurn = true; // Reset to player one's turn

    }

    private void clearCircles() {
        for (int i = 0; i < ROW_SIZE - 1; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                circles[i][j].setFill(Color.TRANSPARENT);
            }
        }
    }

    private void disableButtons() {
        for (Button button : btns[ROW_SIZE - 1]) {
            button.setDisable(true);
        }
    }

    private void enableButtons() {
        for (Button button : btns[ROW_SIZE - 1]) {
            button.setDisable(false);
        }
    }

    private void showJOptionPane(String message) {
        JOptionPane.showMessageDialog(null, message, "Alert", JOptionPane.INFORMATION_MESSAGE);
    }
}