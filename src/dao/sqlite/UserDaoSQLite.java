package dao.sqlite;

import dao.UserDao;
import domain.Admin;
import domain.Category;
import domain.User;
import utils.PasswordHasher;
import utils.SQLiteDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;

public final class UserDaoSQLite implements UserDao {

    final static String TABLE_NAME = "user";
    final static String FIELD_ID = "id_user";
    final static String FIELD_EMAIL = "email";
    final static String FIELD_PASSWORD = "password";
    final static String FIELD_NAME = "name";
    final static String FIELD_SCORE = "score";
    final static String FIELD_ADMIN = "is_admin";
    final static String FIELD_ACTIVE = "active";

    public boolean add(User user) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = String.format(
                    "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?,?,?,?,?,?)",
                    TABLE_NAME,
                    FIELD_EMAIL,
                    FIELD_PASSWORD,
                    FIELD_NAME,
                    FIELD_SCORE,
                    FIELD_ADMIN,
                    FIELD_ACTIVE
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, PasswordHasher.hash(user.getPassword()));
            ps.setString(3, user.getName());
            ps.setInt(4, 0);
            ps.setBoolean(5, user instanceof Admin);
            ps.setBoolean(6, true);
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(User user) {
        return false;
    }

    public boolean update(User user) {
        return false;
    }

    public ArrayList<User> getAll(boolean onlyActives) {
        return null;
    }

    public ArrayList<User> getAll(boolean onlyActives, String filter, String field) {
        return null;
    }

    public User get() {
        return null;
    }

    public User get(String filter) {
        return null;
    }

    public User get(User user) {
        return null;
    }

    public User getByEmailPassword(User user) {
        Connection c = SQLiteDBConnection.getConnection();
        User u = null;
        try {
            String sql = String.format(
                    "SELECT * FROM %s WHERE %s=? AND %s=? LIMIT 1",
                    TABLE_NAME,
                    FIELD_EMAIL,
                    FIELD_PASSWORD
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, PasswordHasher.hash(user.getPassword()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getBoolean(FIELD_ACTIVE)) {
                    u = rs.getBoolean(FIELD_ADMIN) ? new Admin() : new User();
                    u.setIdUser(rs.getInt(FIELD_ID));
                    u.setActive(true);
                    u.setEmail(rs.getString(FIELD_EMAIL));
                    u.setName(rs.getString(FIELD_NAME));
                }
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public ArrayList<User> getAllWithPontuation(int limit) {
        Connection c = SQLiteDBConnection.getConnection();
        ArrayList<User> userList = new ArrayList<>();
        try {
            String sql = String.format(
                    "SELECT u.%s name, COUNT(rq.%s) pontuation " +
                    "FROM %s u " +
                    "JOIN %s r ON (r.%s = u.%s) " +
                    "JOIN %s rq ON (r.%s = rq.%s) " +
                    "AND rq.%s = 1 " +
                    "GROUP BY u.%s ORDER BY pontuation DESC LIMIT %d",
                    FIELD_NAME,
                    RoundDaoSQLite.FILED_RQ_ID,
                    TABLE_NAME,
                    RoundDaoSQLite.TABLE_NAME, RoundDaoSQLite.FIELD_USER_ID, FIELD_ID,
                    RoundDaoSQLite.TABLE_RQ, RoundDaoSQLite.FIELD_ID, RoundDaoSQLite.FILED_RQ_ROUND_ID,
                    RoundDaoSQLite.FIELD_RQ_IS_RIGHT,
                    FIELD_ID, limit
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            User u = null;
            while (rs.next()) {
                u = new User();
                u.setName(rs.getString(FIELD_NAME));
                u.setActive(true);
                u.setScore(rs.getInt("pontuation"));
                userList.add(u);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
