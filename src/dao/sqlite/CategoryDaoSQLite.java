package dao.sqlite;

import dao.CategoryDao;
import domain.Category;
import utils.SQLiteDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public final class CategoryDaoSQLite implements CategoryDao {

    final static String TABLE_NAME = "category";
    final static String FIELD_ID = "id_category";
    final static String FIELD_NAME = "name";
    final static String FIELD_ACTIVE = "active";
    final static String FIELD_QUESTIONS_QUANTITY = "questions_quantity";

    public boolean add(Category category) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = "INSERT INTO " + TABLE_NAME + " (" + FIELD_NAME + ", " + FIELD_ACTIVE + ") VALUES (?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setBoolean(1, category.isActive());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            category.setIdCategory(rs.getInt(1));

            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(Category category) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = "UPDATE " + TABLE_NAME + " SET " + FIELD_ACTIVE + "=? WHERE " + FIELD_ID + "=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, category.getIdCategory());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Category category) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = "UPDATE " + TABLE_NAME + " SET " + FIELD_NAME + "=? WHERE " + FIELD_ID + "=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getIdCategory());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Category> getAll(boolean onlyActives) {
        Connection c = SQLiteDBConnection.getConnection();
        ArrayList<Category> categoryList = new ArrayList<Category>();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + FIELD_NAME;
            if (onlyActives)
                sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + FIELD_ACTIVE + "=1 ORDER BY " + FIELD_NAME;

            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setIdCategory(rs.getInt(FIELD_ID));
                category.setName(rs.getString(FIELD_NAME));
                category.setActive(rs.getBoolean(FIELD_ACTIVE));
                categoryList.add(category);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public ArrayList<Category> getAllWithQuantity() {
        Connection c = SQLiteDBConnection.getConnection();
        ArrayList<Category> categoryList = new ArrayList<Category>();
        try {
            String sql = "SELECT %s.%s, %s.%s, %s.%s, COUNT(%s.%s) AS questions_quantity " +
                    "FROM %s JOIN %s ON (%s = %s) WHERE %s.%s=1 AND %s.%s=1 GROUP BY %s.%s";
            sql = String.format(
                    sql,
                    TABLE_NAME, FIELD_ID,
                    TABLE_NAME, FIELD_NAME,
                    TABLE_NAME, FIELD_ACTIVE,
                    QuestionDaoSQLite.TABLE_NAME, QuestionDaoSQLite.FIELD_ID,
                    TABLE_NAME,
                    QuestionDaoSQLite.TABLE_NAME,
                    QuestionDaoSQLite.FIELD_CATEGORY, FIELD_ID,
                    TABLE_NAME, FIELD_ACTIVE,
                    QuestionDaoSQLite.TABLE_NAME, QuestionDaoSQLite.FIELD_ACTIVE,
                    TABLE_NAME, FIELD_ID
            );

            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setIdCategory(rs.getInt(FIELD_ID));
                category.setName(rs.getString(FIELD_NAME));
                category.setActive(rs.getBoolean(FIELD_ACTIVE));
                category.setQuestionsQuantity(rs.getInt(FIELD_QUESTIONS_QUANTITY));
                categoryList.add(category);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public ArrayList<Category> getAll(boolean onlyActives, String filter, String field) {
        return null;
    }

    public Category get() {
        return null;
    }

    public Category get(String filter) {
        return null;
    }

    public Category get(Category category) {
        return null;
    }
}
