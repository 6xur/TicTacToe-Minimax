import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML
    private Button button0;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    public static String starter = "player";

    public static ArrayList<Button> buttons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<>(Arrays.asList(button0,button1,button2,button3,button4,button5,button6,button7,button8));
        buttons.forEach(this::setupButton);
    }

    public void setupButton(Button button) {
        button.setOnMouseClicked(mouseEvent -> {
            setSymbol(button, "player");
            button.setDisable(true);

            String winner = getWinner(buttons);
            if(winner.length() > 0){
                disableButtons(buttons);
            }

            // AI makes a move
            boolean boardIsFull = isFull(buttons);
            if(!boardIsFull && getWinner(buttons).length() == 0){
                int move = minimax(buttons, "ai")[1];
                Button b = buttons.get(move);
                setSymbol(b, "ai");
                b.setDisable(true);
            }

            // check for winner after AI makes a move
            winner = getWinner(buttons);
            if(winner.length() > 0){
                disableButtons(buttons);
            }
        });
    }

    public static void setSymbol(Button button, String player){
        button.setFont(new Font(40));
        switch (player) {
            case "player" -> button.setText("X");
            case "ai"     -> button.setText("0");
            case "empty"  -> button.setText("");
        }
    }

    public static boolean hasText(Button a){
        return a.getText().length() > 0;
    }

    public static boolean checkLine(Button a, Button b, Button c){
        return a.getText().equals(b.getText()) && b.getText().equals(c.getText());
    }

    public static String getWinner(List<Button> buttons){
        String symbol = "";  // symbol of the winner
        String winner = "";  // name of the winner

        // rows
        for(int i = 0; i < 7; i = i + 3){
            if(checkLine(buttons.get(i), buttons.get(i + 1), buttons.get(i + 2)) && hasText(buttons.get(i))){
                symbol = buttons.get(i).getText();
            }
        }

        // columns
        for(int i = 0; i < 3; i++){
            if(checkLine(buttons.get(i), buttons.get(i + 3), buttons.get(i + 6)) && hasText(buttons.get(i))){
                symbol = buttons.get(i).getText();
            }
        }

        // diagonals
        if(checkLine(buttons.get(0), buttons.get(4), buttons.get(8)) && hasText(buttons.get(0))){
            symbol = buttons.get(0).getText();
        }
        if(checkLine(buttons.get(2), buttons.get(4), buttons.get(6)) && hasText(buttons.get(2))){
            symbol = buttons.get(2).getText();
        }

        boolean boardIsFull = isFull(buttons);
        if(symbol.equals("X")){ // player wins
            winner = "player";
        } else if(symbol.equals("0")){ // AI wins
            winner = "ai";
        } else if(boardIsFull && symbol.length() == 0){ // draw
            winner = "nobody";
        }
        return winner;
    }

    public static int[] minimax(List<Button> buttons, String player) {
        String winner = getWinner(buttons);
        if (!(winner.equals(""))) {
            if (winner.equals("nobody")) {
                return new int[]{0, 0};
            }
            if (winner.equals("player")) {
                return new int[]{-1, 0};
            }
            if (winner.equals("ai")) {
                return new int[]{1, 0};
            }
        }

        if(player.equals("ai")) {
            int bestScore = -99999;
            int bestMove = 0;

            for (int pos = 0; pos < 9; pos++) {
                Button button = buttons.get(pos);
                if (!hasText(button)) {
                    setSymbol(button, player);

                    // see what it's like to be there (by making the minimising player take a step)
                    int score = minimax(buttons, "player")[0];
                    if (score >= bestScore) {
                        bestScore = score;
                        bestMove = pos;
                    }

                    // move back to where we were
                    setSymbol(button, "empty");
                }
            }
            return new int[]{bestScore, bestMove};
        } else {
            int bestScore = 99999;
            int bestMove = 0;

            for (int pos = 0; pos < 9; pos++) {
                Button button = buttons.get(pos);
                if (!hasText(button)) {
                    setSymbol(button, player);
                    int score = minimax(buttons, "ai")[0];
                    if (score <= bestScore) {
                        bestScore = score;
                        bestMove = pos;
                    }
                    setSymbol(button, "empty");
                }
            }
            return new int[]{bestScore, bestMove};
        }
    }

    public static void disableButtons(List<Button> buttons){
        for(Button button : buttons){
            button.setDisable(true);
        }
    }

    public static void resetButtons(List<Button> buttons){
        for(Button button: buttons){
            setSymbol(button, "empty");
            button.setDisable(false);
        }
        starter = starter.equals("player") ? "ai":"player";
    }

    public static boolean isFull(List<Button> buttons){
        for(int i = 0; i < 9; i++){
            if(buttons.get(i).getText().length() == 0){
                return false;
            }
        }
        return true;
    }
}
