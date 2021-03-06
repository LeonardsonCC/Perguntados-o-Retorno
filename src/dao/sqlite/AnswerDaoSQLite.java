package dao.sqlite;

import dao.AnswerDao;
import domain.Answer;
import domain.Question;
import utils.SQLiteDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AnswerDaoSQLite implements AnswerDao {

    static String TABLE_NAME = "answer";
    static String FIELD_ID = "id_answer";
    static String FIELD_TEXT = "text";
    static String FIELD_QUESTION_ID = "question_id";
    static String FIELD_IS_CORRECT = "is_correct";
    static String FIELD_ACTIVE = "active";

    public boolean add(Answer answer) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = String.format(
                    "INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)",
                    TABLE_NAME,
                    FIELD_TEXT,
                    FIELD_QUESTION_ID,
                    FIELD_IS_CORRECT,
                    FIELD_ACTIVE
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, answer.getText());
            ps.setInt(2, answer.getQuestion().getId());
            ps.setBoolean(3, answer.isIsCorrect());
            ps.setBoolean(4, answer.isActive());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(Answer answer) {
        return false;
    }

    public boolean update(Answer answer) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = String.format(
                    "UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=?",
                    TABLE_NAME,
                    FIELD_TEXT,
                    FIELD_IS_CORRECT,
                    FIELD_QUESTION_ID,
                    FIELD_ID
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, answer.getText());
            ps.setBoolean(2, answer.isIsCorrect());
            ps.setInt(3, answer.getQuestion().getId());
            ps.setInt(4, answer.getAnswer());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                newAnswer.setAnswer(rs.getInt(FIELD_ID));
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
