package domain;

import javafx.beans.property.*;

public class Answer {

    private IntegerProperty answer = new SimpleIntegerProperty();
    private StringProperty text = new SimpleStringProperty();
    private Question question = new Question();
    private BooleanProperty isCorrect = new SimpleBooleanProperty();

    public int getAnswer() {
        return answer.get();
    }

    public IntegerProperty answerProperty() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer.set(answer);
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public Question getQuestion() {
        return question;
    }

    public Answer setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public boolean isIsCorrect() {
        return isCorrect.get();
    }

    public BooleanProperty isCorrectProperty() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect.set(isCorrect);
    }

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    private BooleanProperty active = new SimpleBooleanProperty();

}
