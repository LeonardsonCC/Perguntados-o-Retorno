package dao;

import domain.Answer;
import domain.Question;

import java.util.ArrayList;

public interface AnswerDao {

    String TABLE_NAME = "";
    String FIELD_ID = "";
    String FIELD_TEXT = "";
    String FIELD_QUESTION_ID = "";
    String FIELD_IS_CORRECT = "";
    String FIELD_ACTIVE = "";

    boolean add(Answer answer);

    boolean remove(Answer answer);

    boolean update(Answer answer);

    default ArrayList<Answer> getAll() {
        return getAll(true);
    }

    default ArrayList<Answer> getAll(String filter) {
        return getAll(true, filter);
    }

    default ArrayList<Answer> getAll(String filter, String field) {
        return getAll(true, filter, field);
    }

    default ArrayList<Answer> getAll(boolean onlyActives, String filter) {
        return getAll(onlyActives, filter, FIELD_TEXT);
    }

    ArrayList<Answer> getAll(boolean onlyActives);

    ArrayList<Answer> getAll(boolean onlyActives, String filter, String field);

    ArrayList<Answer> getAllFromQuestion(Question question);

    Answer get();

    Answer get(String filter);

    Answer get(Answer answer);

}
