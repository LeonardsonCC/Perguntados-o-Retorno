package domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class User {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty username;
    private StringProperty password;
    private StringProperty createdAt;
    private StringProperty updatedAt;

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getCreatedAt() {
        return createdAt.get();
    }

    public StringProperty createdAtProperty() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt.set(createdAt);
    }

    public String getUpdatedAt() {
        return updatedAt.get();
    }

    public StringProperty updatedAtProperty() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt.set(updatedAt);
    }
}
