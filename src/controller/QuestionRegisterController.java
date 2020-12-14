package controller;

import dao.sqlite.AnswerDaoSQLite;
import dao.sqlite.CategoryDaoSQLite;
import dao.sqlite.QuestionDaoSQLite;
import domain.Answer;
import domain.Category;
import domain.Question;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import main.Main;

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
    public Question editingQuestion = null;

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

        if (QuestionManagementController.editQuestion != null) {
            this.editingQuestion = new QuestionDaoSQLite().get(QuestionManagementController.editQuestion);

            if (this.editingQuestion != null) {
                for (int i=0; i<categoryList.size(); i++) {
                    if (categoryList.get(i).getIdCategory() == this.editingQuestion.getCategory().getIdCategory()) {
                        cbCategoria.getSelectionModel().select(i);
                    }
                }

                txtPergunta.setText(this.editingQuestion.getText());

                ArrayList<Answer> answers = this.editingQuestion.getAnswers();
                for(int i=0; i<answers.size(); i++) {
                    rbRespostas[i].setSelected(answers.get(i).isIsCorrect());
                    txtRespostas[i].setText(answers.get(i).getText());
                }
            }
        } else {
            txtPergunta.setText("");
            cbCategoria.getSelectionModel().select(0);
            for(int i=0; i<rbRespostas.length; i++) {
                rbRespostas[i].setSelected(false);
                txtRespostas[i].setText("");
            }
        }
    }

    @FXML
    void btVoltar() {
        try {
            AnchorPane playPane = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/QuestionManagement.fxml"));
            Scene scene = new Scene(playPane);
            Main.mainStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btRegistrar() {
        boolean hasError = false;
        if (cbCategoria.getSelectionModel() != null && !txtPergunta.getText().isEmpty()) {
            if (this.editingQuestion != null) {
                this.editingQuestion.setText(txtPergunta.getText());
                this.editingQuestion.setActive(true);
                this.editingQuestion.setCategory(cbCategoria.getSelectionModel().getSelectedItem());
                if (!new QuestionDaoSQLite().update(this.editingQuestion)) hasError = true;

                if (respostas.getSelectedToggle() != null) {
                    for(int i=0; i<rbRespostas.length; i++) {
                        if (!txtRespostas[i].getText().isEmpty()) {
                            this.editingQuestion.getAnswers().get(i).setQuestion(this.editingQuestion);
                            this.editingQuestion.getAnswers().get(i).setText(txtRespostas[i].getText());
                            this.editingQuestion.getAnswers().get(i).setIsCorrect(rbRespostas[i].isSelected());
                            if (!new AnswerDaoSQLite().update(this.editingQuestion.getAnswers().get(i))) hasError = true;
                        }
                    }
                } else {
                    hasError = true;
                }
            }
            else {
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
}
