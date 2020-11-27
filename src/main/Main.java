package main;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

    public static Stage mainStage = null;

    @Override
    public void start(Stage primaryStage) {
        Main.mainStage = primaryStage;

        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("GAME");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
