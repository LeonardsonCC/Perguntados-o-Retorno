package controller;

import dao.sqlite.CategoryDaoSQLite;
import dao.sqlite.QuestionDaoSQLite;
import dao.sqlite.RoundDaoSQLite;
import domain.Category;
import domain.Question;
import domain.Round;
import domain.RoundQuestion;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionCategorySelectController {

    @FXML
    private TableView<Category> tbCategorias;
    @FXML
    private TableColumn<Category, String> tbColName;
    @FXML
    private TableColumn<Category, Number> tbColQuantity;

    public static Category selectedCategory = null;
    public static Round currentRound = null;

    @FXML
    void btPlay() {
        selectedCategory = tbCategorias.getSelectionModel().getSelectedItem();

        try {
            currentRound = new Round();
            currentRound.setCategory(selectedCategory);
            currentRound.setPlayer(LoginController.loggedUser);
            currentRound.setActive(true);
            new RoundDaoSQLite().add(currentRound);

            // Obtém todas as questões, ramdomiza elas, e pega apenas as 10 primeiras
            ArrayList<Question> questions = new QuestionDaoSQLite().getAllByCategory(currentRound.getCategory());
            Collections.shuffle(questions);
            List<Question> questionsList = questions.subList(0, Math.min(questions.size(), 5));
            ArrayList<RoundQuestion> roundQuestions = new ArrayList<>();
            for (Question question: questionsList) {
                RoundQuestion tmpQuestion = new RoundQuestion();
                tmpQuestion.setQuestion(question);
                tmpQuestion.setRight(false);
                roundQuestions.add(tmpQuestion);
            }
            currentRound.setQuestions(roundQuestions);

            AnchorPane playPane = FXMLLoader.load(getClass().getResource("/view/Question.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        tbColName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tbColQuantity.setCellValueFactory(cellData -> cellData.getValue().questionsQuantityProperty());

        ArrayList<Category> categoryList = new CategoryDaoSQLite().getAllWithQuantity();
        if (categoryList.size() > 0) {
            tbCategorias.setItems(FXCollections.observableArrayList(categoryList));
        }
    }

}
