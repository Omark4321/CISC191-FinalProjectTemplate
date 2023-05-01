package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Presents the user with the game graphical user interface
 */
public class ViewGameBoard extends Application
{
    private Canvas gameCanvas;
    private ControllerGameBoard controller;
    private GameBoardLabel fishRemaining;
    private GameBoardLabel guessesRemaining;
    private GameBoardLabel message;
    private Button resetBoard;
    public static void main(String[] args)
    {
        // TODO: launch the app ✔
        launch(args);

    }

    public void updateHeader() {
        //TODO update labels ✔

        fishRemaining.setText("Fish: " + controller.modelGameBoard.getFishRemaining());
        guessesRemaining.setText("Bait: " + controller.modelGameBoard.getGuessesRemaining());

        if(controller.fishWin()) {
            message.setText("Fishes win!");
        } else if(controller.playerWins()) {
            message.setText("You win!");
        } else {
            message.setText("Find the fish!");
        }
    }
    public void revealFish(HBox labelContainer, Stage stage){

        VBox mainContainer = new VBox(labelContainer);
        VBox buttonContainer = new VBox();
        for(int row=0;row<6;row++){
            HBox buttonRow = new HBox();
            for(int col=0;col<6;col++){
                GameBoardButton button = new GameBoardButton(row, col, controller.modelGameBoard.fishAt(row,col));
                controller.makeGuess(row, col);
                if(!controller.isGameOver()) {
                    updateHeader();
                }
                buttonRow.getChildren().add(button);
            }
            buttonContainer.getChildren().add(buttonRow);
        }
        mainContainer.getChildren().add(buttonContainer);

        BorderPane root = new BorderPane();
        root.setTop(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gone Fishing");
        stage.show();
    }
    @Override
    public void start(Stage stage) throws Exception {
        controller = new ControllerGameBoard();
        // TODO initialize gameCanvas ✓
        gameCanvas = new Canvas();

        fishRemaining = new GameBoardLabel();
        guessesRemaining = new GameBoardLabel();
        message = new GameBoardLabel();
        Button revealButton = new Button("play automatically");
        // TODO display game there are infinite ways to do this, I used BorderPane with HBox and VBox ✓
        BorderPane root = new BorderPane();
        HBox labelContainer = new HBox(fishRemaining, guessesRemaining, message, revealButton);

        VBox buttonContainer = new VBox();
        buttonContainer.setSpacing(8);

        VBox mainContainer = new VBox(labelContainer);

        updateHeader();

        for (int row=0; row < ModelGameBoard.DIMENSION; row++) {
            // TODO: create row container ✓
            HBox buttonRow = new HBox();
            buttonRow.setSpacing(8);
            for (int col=0; col < ModelGameBoard.DIMENSION; col++) {
                GameBoardButton button = new GameBoardButton(row, col, controller.modelGameBoard.fishAt(row,col));
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e -> {
                    controller.makeGuess(finalRow, finalCol);
                    if(!controller.isGameOver()) {
                        button.handleClick();
                        updateHeader();
                    }
                });
                // TODO: add button to row ✓
                buttonRow.getChildren().add(button);

            }
            // TODO: add row to column ✓
            buttonContainer.getChildren().add(buttonRow);
        }

        // TODO: create scene, stage, set title, and show ✓

        mainContainer.getChildren().add(buttonContainer);

        revealButton.setOnAction(e-> revealFish(labelContainer, stage));
        root.setTop(mainContainer);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gone Fishing");
        stage.show();


    }
}