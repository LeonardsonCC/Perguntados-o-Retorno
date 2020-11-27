package controller;

import dao.impl.AnswerDaoSQLite;
import dao.impl.CategoryDaoSQLite;
import dao.impl.QuestionDaoSQLite;
import domain.Answer;
import domain.Category;
import domain.Question;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
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

    RadioButton[] rbRespostas = new RadioButton[4];
    ArrayList<Answer> answerList = null;

    @FXML
    protected void initialize() {
        ArrayList<Category> categoryList = new CategoryDaoSQLite().getAll(true);
        rbRespostas[0] = rbResposta1;
        rbRespostas[1] = rbResposta2;
        rbRespostas[2] = rbResposta3;
        rbRespostas[3] = rbResposta4;

        Category selectedCategory = categoryList.get((int) (Math.random() * categoryList.size()));
        if (selectedCategory != null) {
            ArrayList<Question> questionList = new QuestionDaoSQLite().getAllByCategory(selectedCategory);
            try {
                Question selectedQuestion = questionList.get((int) (Math.random() * questionList.size()));
                txtPergunta.setText(selectedQuestion.getText());

                this.answerList = new AnswerDaoSQLite().getAllFromQuestion(selectedQuestion);
                for (int i = 0; i < rbRespostas.length; i++) {
                    rbRespostas[i].setText(this.answerList.get(i).getText());
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                Message.error("Erro", "", "Houve um problema ao obter a questão");
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

            if (selectedAnswer != null && selectedAnswer.isIsCorrect()) {
                Message.success("SUCESSO", "", "Resposta correta!");
            } else {
                Message.error("FALHOU", "", "Resposta errada!");
            }
        }
    }

}
