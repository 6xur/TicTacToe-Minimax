import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application{
//    public static final int BOARD_WIDTH = 400;
//    public static final int BOARD_HEIGHT = 450;
//    public static int aiScore = 0;
//    public static String starter = "player";
//    public static Text scoreBoard;
//    public static FlowPane flowPane = new FlowPane();
//    public static BorderPane rootPane = new BorderPane();
//
//    public static void setSymbol(Button button, String player){
//        button.setFont(new Font(30));
//        switch (player) {
//            case "player" -> button.setText("X");
//            case "ai"     -> button.setText("0");
//            case "empty"  -> button.setText("");
//        }
//    }
//
//    public static boolean hasText(Button a){
//        return a.getText().length() > 0;
//    }
//
//    public static boolean checkLine(Button a, Button b, Button c){
//        return a.getText().equals(b.getText()) && b.getText().equals(c.getText());
//    }
//
//    public static String getWinner(List<Button> buttons){
//        String symbol = "";  // symbol of the winner
//        String winner = "";  // name of the winner
//
//        // rows
//        for(int i = 0; i < 7; i = i + 3){
//            if(checkLine(buttons.get(i), buttons.get(i + 1), buttons.get(i + 2)) && hasText(buttons.get(i))){
//                symbol = buttons.get(i).getText();
//            }
//        }
//
//        // columns
//        for(int i = 0; i < 3; i++){
//            if(checkLine(buttons.get(i), buttons.get(i + 3), buttons.get(i + 6)) && hasText(buttons.get(i))){
//                symbol = buttons.get(i).getText();
//            }
//        }
//
//        // diagonals
//        if(checkLine(buttons.get(0), buttons.get(4), buttons.get(8)) && hasText(buttons.get(0))){
//            symbol = buttons.get(0).getText();
//        }
//        if(checkLine(buttons.get(2), buttons.get(4), buttons.get(6)) && hasText(buttons.get(2))){
//            symbol = buttons.get(2).getText();
//        }
//
//        boolean boardIsFull = isFull(buttons);
//        if(symbol.equals("X")){ // player wins
//            winner = "player";
//        } else if(symbol.equals("0")){ // AI wins
//            winner = "ai";
//        } else if(boardIsFull && symbol.length() == 0){ // draw
//            winner = "nobody";
//        }
//        return winner;
//    }
//
//    public static int[] minimax(List<Button> buttons, String player) {
//        String winner = getWinner(buttons);
//        if (!(winner.equals(""))) {
//            if (winner.equals("nobody")) {
//                return new int[]{0, 0};
//            }
//            if (winner.equals("player")) {
//                return new int[]{-1, 0};
//            }
//            if (winner.equals("ai")) {
//                return new int[]{1, 0};
//            }
//        }
//
//        if(player.equals("ai")) {
//            int bestScore = -99999;
//            int bestMove = 0;
//
//            for (int pos = 0; pos < 9; pos++) {
//                Button button = buttons.get(pos);
//                if (!hasText(button)) {
//                    setSymbol(button, player);
//
//                    // see what it's like to be there (by making the minimising player take a step)
//                    int score = minimax(buttons, "player")[0];
//                    if (score >= bestScore) {
//                        bestScore = score;
//                        bestMove = pos;
//                    }
//
//                    // move back to where we were
//                    setSymbol(button, "empty");
//                }
//            }
//            return new int[]{bestScore, bestMove};
//        } else {
//            int bestScore = 99999;
//            int bestMove = 0;
//
//            for (int pos = 0; pos < 9; pos++) {
//                Button button = buttons.get(pos);
//                if (!hasText(button)) {
//                    setSymbol(button, player);
//                    int score = minimax(buttons, "ai")[0];
//                    if (score <= bestScore) {
//                        bestScore = score;
//                        bestMove = pos;
//                    }
//                    setSymbol(button, "empty");
//                }
//            }
//            return new int[]{bestScore, bestMove};
//        }
//    }
//
//    public static void disableButtons(List<Button> buttons){
//        for(Button button : buttons){
//            button.setDisable(true);
//        }
//    }
//
//    public static void resetButtons(List<Button> buttons){
//        for(Button button: buttons){
//            setSymbol(button, "empty");
//            button.setDisable(false);
//        }
//    }
//
//    public static boolean isFull(List<Button> buttons){
//        for(int j = 0; j < 9; j++){
//            if(buttons.get(j).getText().length() == 0){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public List<Button> makeButtons(){
//        List<Button> buttons = new ArrayList<>();
//        for(int i = 0; i < 9; i++){
//            Button button = new Button();
//            button.setPrefSize(100, 100);
//            button.setFocusTraversable(false);
//            button.setOnMouseClicked(mouseEvent -> {
//                setSymbol(button, "player");
//                button.setDisable(true);
//
//                boolean boardIsFull = isFull(buttons);
//
//                // check for winner after player makes a move
//                String winner = getWinner(buttons);
//                if(winner.length() > 0){  // if there is a winner
//                    disableButtons(buttons);
//                    if(winner.equals("ai")){
//                        aiScore++;
//                        scoreBoard.setText("0 : " + aiScore);
//                    }
//                }
//
//                // AI's turn
//                if(!boardIsFull && getWinner(buttons).length() == 0){
//                    int move = minimax(buttons, "ai")[1];
//                    Button but = buttons.get(move);
//                    setSymbol(but, "ai");
//                    but.setDisable(true);
//
//                    // check for winner after AI makes a move
//                    winner = getWinner(buttons);
//                    if(winner.length() > 0){
//                        disableButtons(buttons);
//                        if(winner.equals("ai")){
//                            aiScore++;
//                            scoreBoard.setText("0 : " + aiScore);
//                        }
//                    }
//                }
//            });
//            buttons.add(button);
//        }
//        return buttons;
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Tic Tac Toe");
//        primaryStage.setResizable(false);
//
//        scoreBoard = new Text("0 : " + aiScore);
//        scoreBoard.setFont(Font.font("verdana", FontWeight.BLACK, FontPosture.REGULAR, 20));
//
//        List<Button> buttons = makeButtons();
//        flowPane.getChildren().addAll(buttons);
//        flowPane.setPadding(new Insets(30, 50, 50 ,50));
//        rootPane.setCenter(flowPane);
//        rootPane.setTop(scoreBoard);
//        BorderPane.setMargin(scoreBoard, new Insets(40, 50, 0, 177));
//
//        Scene scene = new Scene(rootPane, BOARD_WIDTH, BOARD_HEIGHT);
//
//        // reset the buttons if "r" is pressed
//        scene.setOnKeyPressed(e -> {
//            if(e.getCode() == KeyCode.R){
//                resetButtons(buttons);
//                starter = starter.equals("player") ? "ai":"player";  // starting player alternates
//
//                // first move by AI is random
//                if(starter.equals("ai")){
//                    Button button = buttons.get(new Random().nextInt(9));
//                    setSymbol(button, "ai");
//                    button.setDisable(true);
//                }
//            }
//        });
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
    public void start(Stage stage) throws Exception{
        stage.setTitle("TicTacToe");
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}