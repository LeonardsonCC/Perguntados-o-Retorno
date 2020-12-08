package dao;

import domain.Round;
import domain.Question;
import domain.RoundQuestion;

import java.util.ArrayList;

public interface RoundDao {

    String TABLE_NAME = "";
    String FIELD_ID = "";
    String FIELD_CATEGORY_ID = "";
    String FIELD_USER_ID = "";
    String FIELD_ACTIVE = "";

    String TABLE_RQ = "";
    String FILED_RQ_ID = "";
    String FILED_RQ_QUESTION_ID = "";
    String FILED_RQ_ROUND_ID = "";
    String FIELD_RQ_IS_RIGHT = "";

    boolean add(Round round);

    boolean addRQ(RoundQuestion roundQuestion);

    boolean remove(Round round);

    boolean update(Round round);

    ArrayList<Round> getAll(boolean onlyActives);

}
