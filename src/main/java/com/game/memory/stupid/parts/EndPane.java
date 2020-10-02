package com.game.memory.stupid.parts;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

/**
 * Created by Chi Joung so <chijoung@gmail.com on 2-10-2020.
 */
public class EndPane extends VBox {

    private static final Font font = Font.font("Cooper Black", FontPosture.REGULAR, 50);

    public EndPane(int tries, int seconds) {

        Text txtGameOver = new Text("Game is over!");
        Text txtTriesNeeded = new Text("Number of tries needed: " + tries);
        Text txtTimeNeeded = new Text("Number of seconds needed: " + seconds);
        txtGameOver.setFont(font);
        txtTriesNeeded.setFont(font);
        txtTimeNeeded.setFont(font);
        this.getChildren().addAll(txtGameOver, txtTriesNeeded, txtTimeNeeded);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: antiquewhite");
    }
}