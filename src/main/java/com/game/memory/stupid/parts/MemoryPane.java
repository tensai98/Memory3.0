package com.game.memory.stupid.parts;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Chi Joung so <chijoung@gmail.com on 2-10-2020.
 */
public class MemoryPane extends GridPane {

    private static Logger log = LoggerFactory.getLogger(MemoryPane.class);

    private static final String noBorderStyle = "-fx-border-color: white; -fx-border-width: 0px";
    private static final String blueBorderStyle = "-fx-border-color: skyblue; -fx-border-width: 5px";

    private int[][] board;
    private Long startTime;
    private Cell[][] cell;
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

        setAlignment(Pos.CENTER); // set center
        setStyle("-fx-background-color: antiquewhite");
        setPadding(new Insets(5, 5, 5, 5));
        setHgap(15);
        setVgap(15);

        randomizeBoard();

        displayBoard(cardType);

        startTime = System.currentTimeMillis();

        if (checkEndGame()) {
            log.info("Game over!!");
        }
    }

    private void randomizeBoard() {
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
    }

    private boolean checkEndGame() {
        return count == numberOfCouples;
    }

    private void displayBoard(String cardType) {
        this.getChildren().clear();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                add(cell[i][j] = new Cell(this, cardType, i, j, board[i][j]), i, j);
                this.setAlignment(Pos.CENTER);
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

    public void handleMouseClick(int i, int j) {
        scoreBox.startTimer();
        if (first) {
            // not flipped yet
            if (!cell[i][j].isFlipped()) {

                // reset cards on first pick
                if (!cell[i1][j1].isFlipped()) {
                    cell[i1][j1].setBack();
                }
                if (!cell[i2][j2].isFlipped()) {
                    cell[i2][j2].setBack();
                }

                cell[i][j].setFlipped(true);
                cell[i][j].setStyle(blueBorderStyle);
                cell[i][j].setFront();

                i1 = i;
                j1 = j;
                first = false;
            } else {
                log.info("Pick another cards");
            }
        } else {
            if (!cell[i][j].isFlipped()) {
                i2 = i;
                j2 = j;
                cell[i][j].setFlipped(true);
                cell[i][j].setStyle(blueBorderStyle);
                cell[i][j].setFront();
                if (match(i1, j1, i2, j2)) {
                    scoreBox.addTry("Match!");

                    count++;
                    // This part is a self check, will be replaced
                    log.info("Count is " + count);
                    if (checkEndGame()) {
                        log.info("Game is over! Time taken: {}.", (System.currentTimeMillis() - startTime));
                        scoreBox.endGame();
                     }

                } else {
                    scoreBox.addTry("No match!");
                    cell[i1][j1].setFlipped(false);
                    cell[i2][j2].setFlipped(false);
                    //startTime -= 3000;		// penalty for wrong guess
                }
                first = true;
                cell[i1][j1].setStyle(noBorderStyle);
                cell[i2][j2].setStyle(noBorderStyle);
            } else {
                log.info("Pick another card");
                cell[i2][j2].setFlipped(false);
            }
        }
    }

    private boolean match(int i1, int j1, int i2, int j2) {
        log.info("Checking if value '{}' is equal to '{}'.", board[i1][j1], board[i2][j2]);
        return board[i1][j1] == board[i2][j2];
    }
}