package domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

import java.util.ArrayList;

public class Round {

    private IntegerProperty idRound;
    private User player;
    private Category category;
    private ArrayList<RoundQuestion> questions = new ArrayList<>();
    private BooleanProperty active;

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public int getIdRound() {
        return idRound.get();
    }

    public IntegerProperty idRoundProperty() {
        return idRound;
    }

    public User getPlayer() {
        return player;
    }

    public Round setPlayer(User player) {
        this.player = player;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Round setCategory(Category category) {
        this.category = category;
        return this;
    }

    public void setIdRound(int idRound) {
        this.idRound.set(idRound);
    }

    public ArrayList<RoundQuestion> getQuestions() {
        return questions;
    }

    public Round setQuestions(ArrayList<RoundQuestion> questions) {
        this.questions = questions;
        return this;
    }
}
