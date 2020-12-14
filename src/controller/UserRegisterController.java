package controller;

import dao.sqlite.UserDaoSQLite;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.Main;
import utils.Message;

public class UserRegisterController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btRegistrar;

    @FXML
    void lblLogar() {
        if (txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty()) {
            Message.error("Erro", "Necessário que todos os campos sejam preenchidos para prosseguir", "Algum dos campos não foi preenchido.");
            return;
        }

        User newUser = new User();
        newUser.setName(txtNome.getText());
        newUser.setEmail(txtEmail.getText());
        newUser.setPassword(txtSenha.getText());

        new UserDaoSQLite().add(newUser);

        Message.success("Sucesso", "Usuário cadastrado com sucesso", "Usuário cadastrado");


        try {
            AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
