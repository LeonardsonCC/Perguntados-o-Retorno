package dao.impl;

import dao.CategoryDao;
import domain.Category;
import utils.MariaDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public final class CategoryDaoMariaDB implements CategoryDao {

    final static String TABLE_NAME = "category";
    final static String FIELD_ID = "category_id";
    final static String FIELD_NAME = "name";
    final static String FIELD_ACTIVE = "active";

    public boolean add(Category category) {
        Connection c = MariaDBConnection.getConnection();
        try {
            String sql = "INSERT INTO " + TABLE_NAME + " (" + FIELD_NAME + ", " + FIELD_ACTIVE + ") VALUES (?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setBoolean(1, category.isActive());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(Category category) {
        Connection c = MariaDBConnection.getConnection();
        try {
            String sql = "UPDATE " + TABLE_NAME + " SET " + FIELD_ACTIVE + "=? WHERE " + FIELD_ID + "=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, category.getId());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Category category) {
        Connection c = MariaDBConnection.getConnection();
        try {
            String sql = "UPDATE " + TABLE_NAME + " SET " + FIELD_NAME + "=? WHERE " + FIELD_ID + "=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Category> getAll(boolean onlyActives) {
        Connection c = MariaDBConnection.getConnection();
        ArrayList<Category> categoryList = new ArrayList<Category>();
        try {
            String sql = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + FIELD_NAME;
            if (onlyActives)
                sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + FIELD_ACTIVE + "=1 ORDER BY " + FIELD_NAME;

            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt(FIELD_ID));
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
