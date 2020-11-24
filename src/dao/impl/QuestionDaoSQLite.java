package dao.impl;

import dao.QuestionDao;
import domain.Admin;
import domain.Category;
import domain.Question;
import domain.User;
import utils.PasswordHasher;
import utils.SQLiteDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public final class QuestionDaoSQLite implements QuestionDao {

    final static String TABLE_NAME = "question";
    final static String FIELD_ID = "id_question";
    final static String FIELD_TITLE = "title";
    final static String FIELD_TEXT = "text";
    final static String FIELD_CATEGORY = "category";
    final static String FIELD_ACTIVE = "active";

    public boolean add(Question question) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = String.format(
                    "INSERT INTO %s (%s, %s, %s, %s) VALUES (?,?,?,?)",
                    TABLE_NAME,
                    FIELD_TITLE,
                    FIELD_TEXT,
                    FIELD_CATEGORY,
                    FIELD_ACTIVE
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, question.getTitle());
            ps.setString(2, question.getText());
            ps.setInt(3, question.getCategory().getIdCategory());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(Question question) {
        return false;
    }

    public boolean update(Question question) {
        return false;
    }

    public ArrayList<Question> getAll(boolean onlyActives) {
        return null;
    }

    public ArrayList<Question> getAll(boolean onlyActives, String filter, String field) {
        return null;
    }

    public ArrayList<Question> getAllByCategory(Category category) {
        Connection c = SQLiteDBConnection.getConnection();
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = String.format(
                    "SELECT * FROM %s WHERE %s=? AND %s=1",
                    TABLE_NAME,
                    FIELD_CATEGORY,
                    FIELD_ACTIVE
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, category.getIdCategory());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Question newQuestion = new Question();
                newQuestion.setActive(true);
                newQuestion.setCategory(category);
                newQuestion.setText(rs.getString("text"));
                newQuestion.setTitle(rs.getString("title"));
                newQuestion.setIdQuestion(rs.getInt("id_question"));
                questions.add(newQuestion);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    public Question get() {
        return null;
    }

    public Question get(String filter) {
        return null;
    }

    public Question get(Question question) {
        return null;
    }
}
