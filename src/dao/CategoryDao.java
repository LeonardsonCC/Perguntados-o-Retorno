package dao;

import domain.Category;

import java.util.ArrayList;

public interface CategoryDao {

    String TABLE_NAME = "";
    String FIELD_ID = "";
    String FIELD_NAME = "";
    String FIELD_ACTIVE = "";

    boolean add(Category category);

    boolean remove(Category category);

    boolean update(Category category);

    default ArrayList<Category> getAll() {
        return getAll(true);
    }

    default ArrayList<Category> getAll(String filter) {
        return getAll(true, filter);
    }

    default ArrayList<Category> getAll(String filter, String field) {
        return getAll(true, filter, field);
    }

    default ArrayList<Category> getAll(boolean onlyActives, String filter) {
        return getAll(onlyActives, filter, FIELD_NAME);
    }

    ArrayList<Category> getAll(boolean onlyActives);

    ArrayList<Category> getAll(boolean onlyActives, String filter, String field);

    Category get();

    Category get(String filter);

    Category get(Category category);

}
