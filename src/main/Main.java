package main;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

    public static Stage mainStage = null;

    @Override
    public void start(Stage primaryStage) {
        Main.mainStage = primaryStage;

        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Certamente não é perguntados");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void centralize() {
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        Main.mainStage.setX((primScreenBounds.getWidth() - Main.mainStage.getWidth()) / 2);
        Main.mainStage.setY((primScreenBounds.getHeight() - Main.mainStage.getHeight()) / 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
