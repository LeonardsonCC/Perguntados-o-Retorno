package controller;

import dao.sqlite.CategoryDaoSQLite;
import dao.sqlite.QuestionDaoSQLite;
import domain.Category;
import domain.Question;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.util.ArrayList;

public class QuestionManagementController {

    @FXML
    private TableView<QuestionCategory> tbPerguntas;

    @FXML
    private TableColumn<QuestionCategory, Number> colPosicao;

    @FXML
    private TableColumn<QuestionCategory, String> colCategoria;

    @FXML
    private TableColumn<QuestionCategory, String> colPergunta;

    public static Question editQuestion = null;

    public void initialize() {
        colPosicao.setCellValueFactory(cellData -> cellData.getValue().positionProperty());
        colCategoria.setCellValueFactory(cellData -> cellData.getValue().getCategory().nameProperty());
        colPergunta.setCellValueFactory(cellData -> cellData.getValue().getQuestion().textProperty());

        ArrayList<QuestionCategory> questionCategoriesList = new QuestionDaoSQLite().getAllWithCategory();
        if (questionCategoriesList.size() > 0) {
            tbPerguntas.setItems(FXCollections.observableArrayList(questionCategoriesList));
        }
    }

    @FXML
    void btAdicionar() {
        try {
            AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/QuestionRegister.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btEditar() {
        editQuestion = tbPerguntas.getSelectionModel().getSelectedItem().getQuestion();
        if (editQuestion == null) return;

        try {
            AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/QuestionRegister.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btRemover() {
        editQuestion = tbPerguntas.getSelectionModel().getSelectedItem().getQuestion();
        new QuestionDaoSQLite().remove(editQuestion);
    }

    @FXML
    void btVoltar() {
        try {
            AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MenuAdm.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class QuestionCategory {
        private final IntegerProperty position = new SimpleIntegerProperty(0);
        private Category category = null;

        public int getPosition() {
            return position.get();
        }

        public IntegerProperty positionProperty() {
            return position;
        }

        public void setPosition(int position) {
            this.position.set(position);
        }

        public Category getCategory() {
            return category;
        }

        public QuestionCategory setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Question getQuestion() {
            return question;
        }

        public QuestionCategory setQuestion(Question question) {
            this.question = question;
            return this;
        }

        private Question question = null;
    }

}
