package edu.sdccd.cisc191.template;

import javafx.scene.control.Button;

//import static jdk.internal.org.jline.terminal.Terminal.MouseTracking.Button;

/**
 * Extends the basic JavaFX Button with game functionality
 */
public class GameBoardButton extends Button {
    private final int row;
    private final int col;
    private final boolean hasFish;
    private boolean isGuessed;

    public GameBoardButton(int row, int col, boolean hasFish)
    {
        this.row = row;
        this.col = col;
        this.hasFish = hasFish;

        // TODO: set min/pref width, default text
        setText("?");
        setMinSize(40,20);
        setPrefSize(60, 40);
        setMaxSize(200,200);

    }

    public void handleClick() {
        // TODO: update text
        if(hasFish) {
            // TODO "<><"
            setText("<><");
        } else {
            // TODO "X"
            setText("X");
        }
        isGuessed = true;
        setDisabled(true);
    }
    public void setShowFalse(){
        isGuessed=false;
    }
    public Object getRow() {
        return null;
    }

    public Object getColumn() {
        return null;
    }

    public boolean isFish() {
        return false;
    }
}