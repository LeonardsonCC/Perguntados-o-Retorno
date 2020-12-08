package controller;

import dao.sqlite.AnswerDaoSQLite;
import dao.sqlite.CategoryDaoSQLite;
import dao.sqlite.QuestionDaoSQLite;
import domain.Answer;
import domain.Category;
import domain.Question;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class QuestionRegisterController {

    @FXML
    private ComboBox<Category> cbCategoria;

    @FXML
    private TextArea txtPergunta;

    @FXML
    private RadioButton rbResposta1;

    @FXML
    private ToggleGroup respostas;

    @FXML
    private TextField txtResposta1;

    @FXML
    private RadioButton rbResposta2;

    @FXML
    private TextField txtResposta2;

    @FXML
    private RadioButton rbResposta3;

    @FXML
    private TextField txtResposta3;

    @FXML
    private RadioButton rbResposta4;

    @FXML
    private TextField txtResposta4;

    public RadioButton[] rbRespostas = new RadioButton[4];
    public TextField[] txtRespostas = new TextField[4];

    public void initialize() {
        rbRespostas[0] = rbResposta1;
        rbRespostas[1] = rbResposta2;
        rbRespostas[2] = rbResposta3;
        rbRespostas[3] = rbResposta4;

        txtRespostas[0] = txtResposta1;
        txtRespostas[1] = txtResposta2;
        txtRespostas[2] = txtResposta3;
        txtRespostas[3] = txtResposta4;

        ArrayList<Category> categoryList = new CategoryDaoSQLite().getAll(true);
        if (categoryList.size() > 0) {
            cbCategoria.setItems(FXCollections.observableArrayList(categoryList));
        }
    }

    @FXML
    void btRegistrar() {
        if (cbCategoria.getSelectionModel() != null && !txtPergunta.getText().isEmpty()) {
            Question tmpQuestion = new Question();
            tmpQuestion.setText(txtPergunta.getText());
            tmpQuestion.setActive(true);
            tmpQuestion.setCategory(cbCategoria.getSelectionModel().getSelectedItem());
            new QuestionDaoSQLite().add(tmpQuestion);

            if (rbRespostas[0].getToggleGroup().getSelectedToggle() != null) {
                for(int i=0; i<rbRespostas.length; i++) {
                    if (!txtRespostas[i].getText().isEmpty()) {
                        Answer tmpAnswer = new Answer();
                        tmpAnswer.setQuestion(tmpQuestion);
                        tmpAnswer.setText(txtRespostas[i].getText());
                        tmpAnswer.setIsCorrect(rbRespostas[i].isSelected());
                        new AnswerDaoSQLite().add(tmpAnswer);
                    }
                }
            }
        }
    }
}
