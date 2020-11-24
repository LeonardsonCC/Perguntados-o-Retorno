package dao;

import domain.Question;

import java.util.ArrayList;

public interface QuestionDao {

    String TABLE_NAME = "";
    String FIELD_ID = "";
    String FIELD_NAME = "";
    String FIELD_ACTIVE = "";

    boolean add(Question question);

    boolean remove(Question question);

    boolean update(Question question);

    default ArrayList<Question> getAll() {
        return getAll(true);
    }

    default ArrayList<Question> getAll(String filter) {
        return getAll(true, filter);
    }

    default ArrayList<Question> getAll(String filter, String field) {
        return getAll(true, filter, field);
    }

    default ArrayList<Question> getAll(boolean onlyActives, String filter) {
        return getAll(onlyActives, filter, FIELD_NAME);
    }

    ArrayList<Question> getAll(boolean onlyActives);

    ArrayList<Question> getAll(boolean onlyActives, String filter, String field);

    Question get();

    Question get(String filter);

    Question get(Question question);

}
