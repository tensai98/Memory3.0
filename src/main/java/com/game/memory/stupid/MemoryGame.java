package com.game.memory.stupid;

import com.game.memory.stupid.parts.EndPane;
import com.game.memory.stupid.parts.LowerBox;
import com.game.memory.stupid.parts.MemoryPane;
import com.game.memory.stupid.parts.ScoreBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemoryGame extends Application {

    private static Logger log = LoggerFactory.getLogger(MemoryGame.class);

    private int difficulty = 18; //initial difficulty
    private String cardType = "fruit"; //initial cardType

    private BorderPane pane = new BorderPane();

    private ScoreBox scoreBox;

    @Override
    public void start(Stage memoryStage) {

        LowerBox lowerBox = new LowerBox(this);

        //leftside of screen - player name, score, time
        scoreBox = new ScoreBox(this);

        //Memory Pane
        MemoryPane memoryPane = new MemoryPane(scoreBox, difficulty, cardType);

        //style
        pane.setBottom(lowerBox);
        pane.setLeft(scoreBox);
        pane.setCenter(memoryPane);

        //set Scene
        Scene scene = new Scene(pane, 1600, 1080);
        memoryStage.setTitle("Memory");
        memoryStage.setScene(scene);
        memoryStage.show();
    }

    public void reset() {
        scoreBox.reset();

        pane.setCenter(new MemoryPane(scoreBox, difficulty, cardType));
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void addEnding(EndPane endPane) {
        pane.setCenter(endPane);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
