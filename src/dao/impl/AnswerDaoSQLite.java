package dao.impl;

import dao.AnswerDao;
import domain.Answer;
import domain.Question;
import utils.SQLiteDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AnswerDaoSQLite implements AnswerDao {

    String TABLE_NAME = "answer";
    String FIELD_ID = "id_answer";
    String FIELD_TEXT = "text";
    String FIELD_QUESTION_ID = "question_id";
    String FIELD_IS_CORRECT = "is_correct";
    String FIELD_ACTIVE = "active";

    public boolean add(Answer answer) {
        return false;
    }

    public boolean remove(Answer answer) {
        return false;
    }

    public boolean update(Answer answer) {
        return false;
    }

    public ArrayList<Answer> getAll(boolean onlyActives) {
        return null;
    }

    public ArrayList<Answer> getAll(boolean onlyActives, String filter, String field) {
        return null;
    }

    @Override
    public ArrayList<Answer> getAllFromQuestion(Question question) {
        Connection c = SQLiteDBConnection.getConnection();
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            String sql = String.format(
                    "SELECT * FROM %s WHERE %s=?",
                    TABLE_NAME,
                    FIELD_QUESTION_ID
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, question.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Answer newAnswer = new Answer();
                newAnswer.setText(rs.getString(FIELD_TEXT));
                newAnswer.setIsCorrect(rs.getBoolean(FIELD_IS_CORRECT));
                newAnswer.setActive(rs.getBoolean(FIELD_ACTIVE));
                answers.add(newAnswer);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answers;
    }

    public Answer get() {
        return null;
    }

    public Answer get(String filter) {
        return null;
    }

    public Answer get(Answer answer) {
        return null;
    }
}
