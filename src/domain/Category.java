package domain;

import javafx.beans.property.*;

public class Category {

    private IntegerProperty idCategory = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private BooleanProperty active = new SimpleBooleanProperty();
    private IntegerProperty questionsQuantity = new SimpleIntegerProperty(0);

    public boolean isActive() {
        return active.get();
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public int getIdCategory() {
        return idCategory.get();
    }

    public void setIdCategory(int idCategory) {
        this.idCategory.set(idCategory);
    }

    public IntegerProperty idProperty() {
        return idCategory;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getQuestionsQuantity() {
        return questionsQuantity.get();
    }

    public IntegerProperty questionsQuantityProperty() {
        return questionsQuantity;
    }

    public void setQuestionsQuantity(int questionsQuantity) {
        this.questionsQuantity.set(questionsQuantity);
    }
}
