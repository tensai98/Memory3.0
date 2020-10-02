//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.layout.GridPane;
//
///**
// * Created by Chi Joung so <chijoung@gmail.com on 2-10-2020.
// */
//public class MemoryPane extends GridPane {
//    private int[][] board;
//
//    private Cell[][] cell;
//
//    private int numberOfCouples;
//
//    private String cardType;
//    private Long startTime;
//
//    int count = 0;
//
//    public MemoryPane(int numberOfCouples, String cardType) {
//        this.numberOfCouples = numberOfCouples;
//        this.cardType = cardType;
//        this.setAlignment(Pos.CENTER); // set center
//        this.setStyle("-fx-background-color: antiquewhite");
//        // creeer matrix board met integers
//        int numberRows = (int) Math.sqrt(2 * numberOfCouples);
//        int numberColumns = (2 * numberOfCouples) / numberRows;
//        board = new int[numberRows][numberColumns];
//        cell = new Cell[numberRows][numberColumns];
//        int couple = 0;
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                board[i][j] = couple / 2;
//                couple++;
//            }
//        }
//        shuffleCards();
//
//        setPadding(new Insets(5, 5, 5, 5));
//        setHgap(15);
//        setVgap(15);
//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                add(cell[i][j] = new Cell(i, j), i, j);
//                this.setAlignment(Pos.CENTER);
//            }
//        }
//
//        startTime = System.currentTimeMillis();
//
//        if(checkEndGame())
//            System.out.println("Game over!!");
//
//    }
//    private boolean checkEndGame() {
//        return count == numberOfCouples;
//    }
//
//    private void displayBoard() {
//        this.getChildren().clear();
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                add(cell[i][j], i, j);
//            }
//        }
//    }
//
//    private void shuffleCards() {
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                int temp = board[i][j];
//                int randomI = (int) (Math.random() * board.length);
//                int randomJ = (int) (Math.random() * board[i].length);
//                board[i][j] = board[randomI][randomJ];
//                board[randomI][randomJ] = temp;
//            }
//        }
//    }
//
//    public int getTries(){
//        return tries;
//    }
//}