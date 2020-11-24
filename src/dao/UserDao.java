package dao;

import domain.User;

import java.util.ArrayList;

public interface UserDao {

    String TABLE_NAME = "";
    String FIELD_ID = "";
    String FIELD_NAME = "";
    String FIELD_ACTIVE = "";

    boolean add(User user);

    boolean remove(User user);

    boolean update(User user);

    default ArrayList<User> getAll() {
        return getAll(true);
    }

    default ArrayList<User> getAll(String filter) {
        return getAll(true, filter);
    }

    default ArrayList<User> getAll(String filter, String field) {
        return getAll(true, filter, field);
    }

    default ArrayList<User> getAll(boolean onlyActives, String filter) {
        return getAll(onlyActives, filter, FIELD_NAME);
    }

    ArrayList<User> getAll(boolean onlyActives);

    ArrayList<User> getAll(boolean onlyActives, String filter, String field);

    User get();

    User get(String filter);

    User get(User user);

}
