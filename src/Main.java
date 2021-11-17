import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Application{
    public void start(Stage stage) throws Exception{
        stage.setTitle("TicTacToe");
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.R){
                ArrayList<Button> buttons = Controller.buttons;
                Controller.resetButtons(buttons);
                String starter = Controller.starter;

                // first move by AI is random
                if(starter.equals("ai")){
                    Button button = buttons.get(new Random().nextInt(9));
                    Controller.setSymbol(button, "ai");
                    button.setDisable(true);
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}