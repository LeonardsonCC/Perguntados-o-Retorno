package controller;

import dao.sqlite.UserDaoSQLite;
import domain.Admin;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.Main;
import utils.Message;

public class LoginController {

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;

    public static User loggedUser = null;

    @FXML
    void lblLogar() {
        if (txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            Message.error("Erro", "Necessário que todos os campos sejam preenchidos para prosseguir", "Algum dos campos não foi preenchido.");
            return;
        }

        User u = new User();
        u.setEmail(txtEmail.getText());
        u.setPassword(txtSenha.getText());
        u = new UserDaoSQLite().getByEmailPassword(u);

        if (u == null) {
            Message.error("Erro", "Usuário ou senha incorretos", "Erro ao logar");
            return;
        }

        try {
            loggedUser = u;

            if (u instanceof Admin) {
                AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/QuestionRegister.fxml"));
                Scene scene = new Scene(playPane);
                Main.mainStage.setScene(scene);
            } else {
                AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
                Scene scene = new Scene(playPane);
                Main.mainStage.setScene(scene);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
