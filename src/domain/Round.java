package domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Round {

    private IntegerProperty idRound = new SimpleIntegerProperty();
    private User player = null;
    private Category category = null;
    private ArrayList<RoundQuestion> questions = new ArrayList<>();
    private BooleanProperty active = new SimpleBooleanProperty();

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
