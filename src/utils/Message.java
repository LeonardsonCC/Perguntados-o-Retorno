package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Message {
    public static void error(String title, String message, String header) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    public static void success(String title, String message, String header) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
