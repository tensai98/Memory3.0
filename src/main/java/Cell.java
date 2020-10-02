//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.StackPane;
//
///**
// * Created by Chi Joung so <chijoung@gmail.com on 2-10-2020.
// */
//public class Cell extends StackPane {
//    private boolean flipped;
//    private int i;
//    private int j;
//
//    public Cell(int i, int j) {
//        this.i = i;
//        this.j = j;
//
//        this.setPrefSize(200, 150);
//        this.setOnMouseClicked(e -> handleMouseClick());
//
//        if (!flipped) {
//            setBack();
//        } else {
//            setFront();
//        }
//    }
//
//    private void setBack() {
//        imageView = new ImageView(new Image("/image/" + cardType
//                + "/backCard.jpg"));
//        this.getChildren().add(imageView);
//    }
//
//    private void setFront() {
//        imageView = new ImageView(new Image("/image/" + cardType + "/"
//                + board[getI()][getJ()] + ".jpg"));
//        this.getChildren().add(imageView);
//    }
//
//    public boolean isFlipped() {
//        return flipped;
//    }
//
//    private void handleMouseClick() {
//        scoreBox.startTimer();
//        if (first) {
//            if (!flipped) {
//                if (!cell[i1][j1].flipped)
//                    cell[i1][j1].setBack();
//                if (!cell[i2][j2].flipped)
//                    cell[i2][j2].setBack();
//
//                cell[getI()][getJ()].flipped = true;
//                setStyle("-fx-border-color: skyblue; -fx-border-width: 5px");
//                i1 = getI();
//                j1 = getJ();
//                setFront();
//                first = false;
//            } else
//                log.info("Pick another cards");
//        } else {
//            if (!flipped) {
//                i2 = getI();
//                j2 = getJ();
//                cell[i2][j2].flipped = true;
//                setStyle("-fx-border-color: skyblue; -fx-border-width: 5px");
//                setFront();
//                if (match(i1, j1, i2, j2)) {
//                    scoreBox.addTry("Match!");
//
//                    count++;
//                    // This part is a self check, will be replaced
//                    log.info("Count is " + count);
//                    if (checkEndGame()) {
//                        log.info("Game is over");
//                        log.info("Time taken: " + (System.currentTimeMillis() - startTime));
//                        scoreBox.endGame();
//                        setScore();
//                    }
//
//                } else {
//                    scoreBox.addTry("No match!");
//                    cell[i1][j1].flipped = false;
//                    cell[i2][j2].flipped = false;
//                    //startTime -= 3000;		// penalty for wrong guess
//                }
//                first = true;
//                cell[i1][j1].setStyle("-fx-border-color: white; -fx-border-width: 0px");
//                this.setStyle("-fx-border-color: white; -fx-border-width: 0px");
//            } else {
//                log.info("Pick another cards");
//                cell[i2][j2].flipped = false;
//            }
//        }
//    }
//
//    private void setScore() {
//        // needs to implemented
//    }
//
//    private boolean match(int i1, int j1, int i2, int j2) {
//        log.info("Checking if value '{}' is equal to '{}'.", board[i1][j1], board[i2][j2]);
//        return board[i1][j1] == board[i2][j2];
//    }
//
//    public int getI() {
//        return i;
//    }
//
//    public int getJ() {
//        return j;
//    }
//
//}
