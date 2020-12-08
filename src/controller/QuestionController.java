package controller;

import dao.sqlite.AnswerDaoSQLite;
import dao.sqlite.QuestionDaoSQLite;
import dao.sqlite.RoundDaoSQLite;
import domain.Answer;
import domain.Category;
import domain.Question;
import domain.RoundQuestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import main.Main;
import utils.Message;

import java.util.ArrayList;

public class QuestionController {

    @FXML
    private TextArea txtPergunta;

    @FXML
    private RadioButton rbResposta1;
    @FXML
    private RadioButton rbResposta2;
    @FXML
    private RadioButton rbResposta3;
    @FXML
    private RadioButton rbResposta4;

    @FXML
    private Button btResponder;

    public RadioButton[] rbRespostas = new RadioButton[4];
    public ArrayList<Answer> answerList = null;
    private static int currentQuestionIndex = 0;
    private static RoundQuestion currentRoundQuestion = null;

    @FXML
    protected void initialize() {
        rbRespostas[0] = rbResposta1;
        rbRespostas[1] = rbResposta2;
        rbRespostas[2] = rbResposta3;
        rbRespostas[3] = rbResposta4;

        Category selectedCategory = QuestionCategorySelectController.selectedCategory;
        if (selectedCategory != null) {
            try {

                // Obtém a questão atual da variavel estatica
                Question selectedQuestion = QuestionCategorySelectController.currentRound.getQuestions()
                                                .get(currentQuestionIndex).getQuestion();

                currentRoundQuestion = new RoundQuestion();
                currentRoundQuestion.setQuestion(selectedQuestion);
                currentRoundQuestion.setRound(QuestionCategorySelectController.currentRound);

                txtPergunta.setText(selectedQuestion.getText());

                this.answerList = new AnswerDaoSQLite().getAllFromQuestion(selectedQuestion);
                for (int i = 0; i < rbRespostas.length; i++) {
                    rbRespostas[i].setText(this.answerList.get(i).getText());
                }
            } catch (IndexOutOfBoundsException e) {
                Message.success("Fim", "", "Todas as questões foram respondidas");
                redirectToMenu();
            }
        }
    }

    @FXML
    void responder() {
        if (answerList.size() > 0) {
            Answer selectedAnswer = null;
            for (int i = 0; i < rbRespostas.length; i++) {
                if (rbRespostas[i].isSelected()) {
                    selectedAnswer = answerList.get(i);
                    break;
                }
            }

            try{
                if (selectedAnswer != null && selectedAnswer.isIsCorrect()) {
                    Message.success("SUCESSO", "", "Resposta correta!");
                    QuestionCategorySelectController.currentRound.getQuestions().get(currentQuestionIndex)
                            .setRight(true);

                    currentRoundQuestion.setRight(true);
                } else {
                    Message.error("FALHOU", "", "Resposta errada!");
                    QuestionCategorySelectController.currentRound.getQuestions().get(currentQuestionIndex)
                            .setRight(false);
                    currentRoundQuestion.setRight(false);
                }

                new RoundDaoSQLite().addRQ(currentRoundQuestion);
                currentQuestionIndex++;
                initialize();
            } catch (IndexOutOfBoundsException e) {
                Message.success("Fim", "", "Todas as questões foram respondidas");
                redirectToMenu();
            }
        }
    }

    private void redirectToMenu() {
        currentQuestionIndex = 0;
        try {
            AnchorPane playPane = FXMLLoader.load(getClass().getResource("/view/Menu.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
