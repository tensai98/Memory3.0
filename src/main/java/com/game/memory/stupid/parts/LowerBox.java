package com.game.memory.stupid.parts;

import com.game.memory.stupid.MemoryGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by Chi Joung so <chijoung@gmail.com on 2-10-2020.
 */
public class LowerBox extends HBox {

    private static final Color color = Color.ANTIQUEWHITE;

    private final MemoryGame game;

    public LowerBox(MemoryGame game) {
        this.game = game;

        VBox difficultyBox = createDifficultyBox();

        VBox cardTypeBox = createCardTypeBox();

        HBox buttonsBox = createButtonsBox();

        this.setSpacing(25);
        this.setStyle("-fx-background-color: #4097f4");
        this.getChildren().addAll(difficultyBox, buttonsBox, cardTypeBox);
        this.setAlignment(Pos.BOTTOM_CENTER);
    }

    private VBox createCardTypeBox() {

        RadioButton rbType1 = getCardTypeButton("Fruits & Vegetables", "fruit");
        RadioButton rbType2 = getCardTypeButton("Figures", "figures");
        RadioButton rbType3 = getCardTypeButton("Cards", "cards");
        ToggleGroup group2 = new ToggleGroup();
        rbType1.setToggleGroup(group2);
        rbType2.setToggleGroup(group2);
        rbType3.setToggleGroup(group2);
        rbType1.setSelected(true);

        Text titleCT = new Text("CARD TYPE");
        titleCT.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        titleCT.setFill(color);
        VBox cardTypeBox = new VBox(10);
        cardTypeBox.setPadding(new Insets(5));
        cardTypeBox.getChildren().addAll(titleCT, rbType1, rbType2, rbType3);

        return cardTypeBox;
    }

    private RadioButton getCardTypeButton(String label, String cardType) {
        RadioButton radioButton = new RadioButton(label);
        radioButton.setTextFill(color);
        radioButton.setOnAction(e -> {
            game.setCardType(cardType);
            game.reset();
        });
        return radioButton;
    }

    private VBox createDifficultyBox() {
        RadioButton rbEasy = getDifficultyButton("Easy", 8);
        RadioButton rbNormal = getDifficultyButton("Normal", 18);
        RadioButton rbHard = getDifficultyButton("Hard", 32);
        rbEasy.setTextFill(color);
        rbNormal.setTextFill(color);
        rbHard.setTextFill(color);

        ToggleGroup group1 = new ToggleGroup();
        rbEasy.setToggleGroup(group1);
        rbNormal.setToggleGroup(group1);
        rbHard.setToggleGroup(group1);
        rbNormal.setSelected(true);

        //lower screen - Button and Radio buttons
        Text titleDif = new Text("DIFFICULTY");
        titleDif.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        titleDif.setFill(color);
        VBox difficultyBox = new VBox(10);
        difficultyBox.setPadding(new Insets(5));
        difficultyBox.getChildren().addAll(titleDif, rbEasy, rbNormal, rbHard);

        return difficultyBox;
    }

    private RadioButton getDifficultyButton(String label, int difficulty) {
        RadioButton radioButton = new RadioButton(label);
        radioButton.setTextFill(color);
        radioButton.setOnAction(e -> {
            game.setDifficulty(difficulty);
            game.reset();
        });
        return radioButton;
    }

    private HBox createButtonsBox() {

        Button btStop = getButton("Stop");

        Button btNewGame = getButton("New Game");

        HBox buttonsBox = new HBox(10);
        buttonsBox.setPadding(new Insets(25));
        buttonsBox.getChildren().addAll(btNewGame, btStop);

        return buttonsBox;
    }

    private Button getButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(80);
        button.setStyle("-fx-base: paleturquoise");
        button.setOnMouseEntered(e -> button.setEffect(new DropShadow()));
        button.setOnMouseExited(e -> button.setEffect(null));
        button.setOnAction(e -> {
            game.reset();
            //terug naar suite
            //verder te implementeren
        });

        return button;
    }

}
