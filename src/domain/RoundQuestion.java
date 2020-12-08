package domain;

public class RoundQuestion {

    private int idRoundQuestion;
    private Question question;
    private Round round;
    private boolean isRight;

    public int getIdRoundQuestion() {
        return idRoundQuestion;
    }

    public RoundQuestion setIdRoundQuestion(int idRoundQuestion) {
        this.idRoundQuestion = idRoundQuestion;
        return this;
    }

    public Question getQuestion() {
        return question;
    }

    public RoundQuestion setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public boolean isRight() {
        return isRight;
    }

    public RoundQuestion setRight(boolean right) {
        isRight = right;
        return this;
    }

    public Round getRound() {
        return round;
    }

    public RoundQuestion setRound(Round round) {
        this.round = round;
        return this;
    }
}
