package dao.sqlite;

import controller.QuestionManagementController;
import dao.QuestionDao;
import domain.Answer;
import domain.Category;
import domain.Question;
import utils.SQLiteDBConnection;

import java.sql.Array;
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
                    "INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)",
                    TABLE_NAME,
                    FIELD_TEXT,
                    FIELD_CATEGORY,
                    FIELD_ACTIVE
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, question.getText());
            ps.setInt(2, question.getCategory().getIdCategory());
            ps.setBoolean(3, true);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            question.setIdQuestion(rs.getInt(1));

            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(Question question) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sqlAnswer = String.format(
                    "UPDATE %s SET active=0 WHERE %s=?",
                    AnswerDaoSQLite.TABLE_NAME,
                    AnswerDaoSQLite.FIELD_QUESTION_ID
            );
            PreparedStatement psAnswer = c.prepareStatement(sqlAnswer);
            psAnswer.setInt(1, question.getId());
            psAnswer.executeUpdate();

            String sql = String.format(
                    "UPDATE %s SET active=0 WHERE %s=?",
                    TABLE_NAME,
                    FIELD_ID
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, question.getId());
            ps.executeUpdate();

            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Question question) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = String.format(
                    "UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=?",
                    TABLE_NAME,
                    FIELD_TEXT,
                    FIELD_CATEGORY,
                    FIELD_ACTIVE,
                    FIELD_ID
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, question.getText());
            ps.setInt(2, question.getCategory().getIdCategory());
            ps.setBoolean(3, question.isActive());
            ps.setInt(4, question.getId());
            ps.executeUpdate();

            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                newQuestion.setIdQuestion(rs.getInt("id_question"));
                questions.add(newQuestion);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questions;
    }

    public ArrayList<QuestionManagementController.QuestionCategory> getAllWithCategory() {
        Connection c = SQLiteDBConnection.getConnection();
        ArrayList<QuestionManagementController.QuestionCategory> questions = new ArrayList<>();
        try {
            String sql = String.format(
                    "SELECT q.%s question_id, q.%s question, c.%s category_id, c.%s category FROM %s q JOIN %s c ON (c.%s=q.%s) AND q.%s=1",
                    FIELD_ID,
                    FIELD_TEXT,
                    CategoryDaoSQLite.FIELD_ID,
                    CategoryDaoSQLite.FIELD_NAME,
                    TABLE_NAME, CategoryDaoSQLite.TABLE_NAME,
                    CategoryDaoSQLite.FIELD_ID, FIELD_CATEGORY,
                    FIELD_ACTIVE
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            int counter = 0;
            while (rs.next()) {
                counter++;
                QuestionManagementController.QuestionCategory newQuestionCategory = new QuestionManagementController.QuestionCategory();
                newQuestionCategory.setPosition(counter);

                Category tmpCategory = new Category();
                tmpCategory.setIdCategory(rs.getInt("category_id"));
                tmpCategory.setName(rs.getString("category"));
                newQuestionCategory.setCategory(tmpCategory);

                Question tmpQuestion = new Question();
                tmpQuestion.setIdQuestion(rs.getInt("question_id"));
                tmpQuestion.setText(rs.getString("question"));
                tmpQuestion.setCategory(tmpCategory);
                newQuestionCategory.setQuestion(tmpQuestion);

                questions.add(newQuestionCategory);
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
        Connection c = SQLiteDBConnection.getConnection();
        Question q = null;
        try {
            String sql = String.format(
                    "SELECT * FROM %s q LEFT JOIN %s c ON (c.%s=q.%s) WHERE q.%s=?",
                    TABLE_NAME,
                    CategoryDaoSQLite.TABLE_NAME,
                    CategoryDaoSQLite.FIELD_ID, FIELD_CATEGORY,
                    FIELD_ID
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, question.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Category newCategory = new Category();
                newCategory.setIdCategory(rs.getInt("id_category"));
                newCategory.setName(rs.getString("name"));

                question = new Question();
                question.setIdQuestion(rs.getInt("id_question"));
                question.setText(rs.getString("text"));
                question.setCategory(newCategory);

                ArrayList<Answer> listAnswer = new AnswerDaoSQLite().getAllFromQuestion(question);
                question.setAnswers(listAnswer);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return question;
    }
}
