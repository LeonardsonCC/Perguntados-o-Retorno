package domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

import java.util.ArrayList;

public class Round {

    private IntegerProperty id;
    private User player;
    private Category category;
    private ArrayList<Question> questions = new ArrayList<>();
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

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
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

    public void setId(int id) {
        this.id.set(id);
    }
}
