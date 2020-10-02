package com.game.memory.stupid.parts;

import com.game.memory.stupid.MemoryGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chi Joung so <chijoung@gmail.com on 2-10-2020.
 */
public class ScoreBox extends VBox {

    private static Logger log = LoggerFactory.getLogger(ScoreBox.class);

    private int seconds = 0;
    private int tries = 0;

    private Text txtTimer = new Text(seconds + ""); //initial time
    private Text txtTries = new Text(tries + ""); //initial tries


    private Timeline animation;

    private final MemoryGame game;

    public ScoreBox(MemoryGame game) {
        this.game = game;

        //Timer
        animation = new Timeline(
                new KeyFrame(Duration.millis(1000), e -> {
                    seconds++;
                    txtTimer.setText(seconds + "");
                }));
        animation.setCycleCount(Timeline.INDEFINITE);

        Text txtPlayerName = new Text("Foo Bar");
        txtPlayerName.setFont(Font.font("Cooper Black", 20));
        txtPlayerName.setFill(Color.ANTIQUEWHITE);


        Label lblTimer = new Label("Seconds");
        Label lblTries = new Label("Tries");
        lblTimer.setFont(Font.font("Cooper Black", 18));
        lblTries.setFont(Font.font("Cooper Black", 18));
        txtTries.setFont(Font.font("Cooper Black", FontPosture.REGULAR, 40));
        txtTimer.setFont(Font.font("Cooper Black", FontPosture.REGULAR, 50));
        txtTimer.setFill(Color.ANTIQUEWHITE);
        txtTries.setFill(Color.ANTIQUEWHITE);


        this.setSpacing(80);
        this.setPadding(new Insets(100, 20, 5, 20));
        this.getChildren().addAll(txtPlayerName, lblTries, txtTries, lblTimer, txtTimer);

        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle("-fx-background-color: FA2436");
    }

    public void reset() {
        animation.stop();
        tries = 0;
        seconds = 0;

        txtTries.setText(tries + "");
        txtTimer.setText(seconds + "");
    }

    public void addTry(String txt) {
        tries++;
        txtTries.setText(tries + "");
        log.info("Try number: {}. {}", txt, tries);
    }

    public void endGame() {
        animation.stop();
        game.addEnding(new EndPane(tries, seconds));
    }

    public void startTimer() {
        animation.play();
    }
}
