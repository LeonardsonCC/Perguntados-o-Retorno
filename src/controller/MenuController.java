package controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import main.Main;

public class MenuController {

    @FXML
    void btPlay() {
        try {
            AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Question.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btQuit() {
        Main.mainStage.close();
    }

    @FXML
    void btRanking() {

    }

}
