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

            // check for winner after player makes a move
            String winner = getWinner(buttons);
            if(winner.length() > 0){  // always evaluates to false because of Minimax
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

    public boolean checkLine(Button a, Button b, Button c){
        return a.getText().equals(b.getText()) && b.getText().equals(c.getText()) && a.getText().length() > 0;
    }

    public String getWinner(List<Button> buttons){
        String symbol = "";  // symbol of the winner (0 or X)
        String winner = "";  // name of the winner

        if(checkLine(button0, button1, button2)){         // rows
            symbol = button0.getText();
        } else if(checkLine(button3, button4, button5)){
            symbol = button3.getText();
        } else if(checkLine(button6, button7, button8)){
            symbol = button6.getText();
        } else if(checkLine(button0, button3, button6)){  // columns
            symbol = button0.getText();
        } else if(checkLine(button1, button4, button7)){
            symbol = button1.getText();
        } else if(checkLine(button2, button5, button8)){
            symbol = button2.getText();
        } else if(checkLine(button0, button4, button8)){  // diagonals
            symbol = button0.getText();
        } else if(checkLine(button2, button4, button6)){
            symbol = button2.getText();
        }

        boolean boardIsFull = isFull(buttons);
        if(symbol.equals("X")){                          // player wins
            winner = "player";
        } else if(symbol.equals("0")){                   // AI wins
            winner = "ai";
        } else if(boardIsFull && symbol.length() == 0){  // draw
            winner = "nobody";
        }
        return winner;
    }

    public static boolean isFull(List<Button> buttons){
        for(int i = 0; i < 9; i++){
            if(buttons.get(i).getText().length() == 0){
                return false;
            }
        }
        return true;
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
        starter = starter.equals("player") ? "ai":"player";  // starting player alternates
    }

    public int[] minimax(List<Button> buttons, String player) {
        String winner = getWinner(buttons);
        if (!(winner.equals(""))) {
            if (winner.equals("player")) {
                return new int[]{-1, 0};
            }
            if (winner.equals("ai")) {
                return new int[]{1, 0};
            }
            if (winner.equals("nobody")) {
                return new int[]{0, 0};
            }
        }

        if(player.equals("ai")) {
            int bestScore = -99999;
            int bestMove = 0;

            for (int pos = 0; pos < 9; pos++) {
                Button button = buttons.get(pos);
                if (button.getText().length() == 0) {
                    setSymbol(button, player);

                    // see what it's like to be there
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
                if (button.getText().length() == 0) {
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
}