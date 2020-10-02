package com.game.memory.stupid.parts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chi Joung so <chijoung@gmail.com on 2-10-2020.
 */
public class MemoryPane extends GridPane {

    private static Logger log = LoggerFactory.getLogger(MemoryPane.class);

    private int[][] board;
    private String cardType;
    private Long startTime;
    private Cell[][] cell;
    private ImageView imageView;
    private boolean first = true;
    private int numberOfCouples;
    private int i1;
    private int j1;
    private int i2;
    private int j2;
    private int count = 0;

    private final ScoreBox scoreBox;

    public MemoryPane(ScoreBox scoreBox, int numberOfCouples, String cardType) {
        this.scoreBox = scoreBox;
        this.numberOfCouples = numberOfCouples;
        this.cardType = cardType;
        this.setAlignment(Pos.CENTER); // set center
        this.setStyle("-fx-background-color: antiquewhite");
        // creeer matrix board met integers
        int numberRows = (int) Math.sqrt(2 * numberOfCouples);
        int numberColumns = (2 * numberOfCouples) / numberRows;
        board = new int[numberRows][numberColumns];
        cell = new Cell[numberRows][numberColumns];
        int couple = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = couple / 2;
                couple++;
            }
        }
        shuffleCards();

        setPadding(new Insets(5, 5, 5, 5));
        setHgap(15);
        setVgap(15);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                add(cell[i][j] = new Cell(i, j), i, j);
                this.setAlignment(Pos.CENTER);
            }
        }

        startTime = System.currentTimeMillis();

        if (checkEndGame())
            log.info("Game over!!");

    }

    private boolean checkEndGame() {
        return count == numberOfCouples;
    }

    private void displayBoard() {
        this.getChildren().clear();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                add(cell[i][j], i, j);
            }
        }
    }

    private void shuffleCards() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int temp = board[i][j];
                int randomI = (int) (Math.random() * board.length);
                int randomJ = (int) (Math.random() * board[i].length);
                board[i][j] = board[randomI][randomJ];
                board[randomI][randomJ] = temp;
            }
        }
    }

    public class Cell extends StackPane {
        private boolean flipped;
        private int i;
        private int j;

        public Cell(int i, int j) {
            this.i = i;
            this.j = j;

            this.setPrefSize(200, 150);
            this.setOnMouseClicked(e -> handleMouseClick());

            if (!flipped) {
                setBack();
            } else {
                setFront();
            }
        }

        private void setBack() {
            imageView = new ImageView(new Image("/image/" + cardType
                    + "/backCard.jpg"));
            this.getChildren().add(imageView);
        }

        private void setFront() {
            imageView = new ImageView(new Image("/image/" + cardType + "/"
                    + board[getI()][getJ()] + ".jpg"));
            this.getChildren().add(imageView);
        }

        public boolean isFlipped() {
            return flipped;
        }

        private void handleMouseClick() {
            scoreBox.startTimer();
            if (first) {
                if (!flipped) {
                    if (!cell[i1][j1].flipped)
                        cell[i1][j1].setBack();
                    if (!cell[i2][j2].flipped)
                        cell[i2][j2].setBack();

                    cell[getI()][getJ()].flipped = true;
                    setStyle("-fx-border-color: skyblue; -fx-border-width: 5px");
                    i1 = getI();
                    j1 = getJ();
                    setFront();
                    first = false;
                } else
                    log.info("Pick another cards");
            } else {
                if (!flipped) {
                    i2 = getI();
                    j2 = getJ();
                    cell[i2][j2].flipped = true;
                    setStyle("-fx-border-color: skyblue; -fx-border-width: 5px");
                    setFront();
                    if (match(i1, j1, i2, j2)) {
                        scoreBox.addTry("Match!");

                        count++;
                        // This part is a self check, will be replaced
                        log.info("Count is " + count);
                        if (checkEndGame()) {
                            log.info("Game is over");
                            log.info("Time taken: " + (System.currentTimeMillis() - startTime));
                            scoreBox.endGame();
                            setScore();
                        }

                    } else {
                        scoreBox.addTry("No match!");
                        cell[i1][j1].flipped = false;
                        cell[i2][j2].flipped = false;
                        //startTime -= 3000;		// penalty for wrong guess
                    }
                    first = true;
                    cell[i1][j1].setStyle("-fx-border-color: white; -fx-border-width: 0px");
                    this.setStyle("-fx-border-color: white; -fx-border-width: 0px");
                } else {
                    log.info("Pick another cards");
                    cell[i2][j2].flipped = false;
                }
            }
        }

        private void setScore() {
            // needs to implemented
        }

        private boolean match(int i1, int j1, int i2, int j2) {
            log.info("Checking if value '{}' is equal to '{}'.", board[i1][j1], board[i2][j2]);
            return board[i1][j1] == board[i2][j2];
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

    }
}