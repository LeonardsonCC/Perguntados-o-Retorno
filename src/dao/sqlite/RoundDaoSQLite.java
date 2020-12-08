package dao.sqlite;

import dao.RoundDao;
import domain.Round;
import domain.RoundQuestion;
import utils.SQLiteDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public final class RoundDaoSQLite implements RoundDao {

    public static String TABLE_NAME = "round";
    public static String FIELD_ID = "id_round";
    public static String FIELD_CATEGORY_ID = "category_id";
    public static String FIELD_USER_ID = "user_id";
    public static String FIELD_ACTIVE = "active";

    public static String TABLE_RQ = "round_question";
    public static String FILED_RQ_ID = "id_round_question";
    public static String FILED_RQ_QUESTION_ID = "question_id";
    public static String FILED_RQ_ROUND_ID = "round_id";
    public static String FIELD_RQ_IS_RIGHT = "is_right";

    public boolean add(Round round) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = "INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)";
            sql = String.format(sql,
                    TABLE_NAME,
                    FIELD_CATEGORY_ID,
                    FIELD_USER_ID,
                    FIELD_ACTIVE
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, round.getCategory().getIdCategory());
            ps.setInt(2, round.getPlayer().getIdUser());
            ps.setBoolean(3, round.isActive());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            round.setIdRound(rs.getInt(1));

            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addRQ(RoundQuestion roundQuestion) {
        Connection c = SQLiteDBConnection.getConnection();
        try {
            String sql = "INSERT INTO %s (%s, %s, %s) VALUES (?,?,?)";
            sql = String.format(sql,
                    TABLE_RQ,
                    FILED_RQ_QUESTION_ID,
                    FILED_RQ_ROUND_ID,
                    FIELD_RQ_IS_RIGHT
            );
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, roundQuestion.getQuestion().getId());
            ps.setInt(2, roundQuestion.getRound().getIdRound());
            ps.setBoolean(3, roundQuestion.isRight());
            ps.executeUpdate();
            c.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(Round round) {
        return false;
    }

    public boolean update(Round round) {
        return false;
    }

    public ArrayList<Round> getAll(boolean onlyActives) {
        return null;
    }

}
