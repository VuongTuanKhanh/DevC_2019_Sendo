package repository;

import main.GlobalVariables;
import mapping.Converter;
import org.json.JSONArray;
import play.Logger;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSearchKeywordRepository {

    private static final String SUCCESS = "SUCCESS";

    private Connection conn;
    private Converter converter;

    @Inject
    public UserSearchKeywordRepository(GlobalVariables db, Converter converter) {
        this.conn = db.getConnectionDB();
        this.converter = converter;
    }

    public JSONArray getByUserKeyword(String user_id, String keyword) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT * FROM user_search_keyword WHERE user_id = ? AND keyword = ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setString(1, user_id);
            preparedStatement.setString(2, keyword);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public JSONArray getKeywordByUser(String user_id, int offset, int limit) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery =
                    "SELECT * FROM user_search_keyword WHERE user_id = ? " +
                            "ORDER BY time_create DESC " +
                            "LIMIT ? OFFSET ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setString(1, user_id);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);

            resultSet = preparedStatement.executeQuery();

            resultSet.isFirst();

            jsonArray = converter.convertResultSetIntoJSON(resultSet);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                resultSet = null;
            }
        }
        return jsonArray;
    }

    public int updateTimeCreate(String user_id, String keyword, Long time_create) throws SQLException {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            String SQLQuery = "UPDATE user_search_keyword SET time_create = ? WHERE user_id = ? AND keyword = ?";
            preparedStatement = conn.prepareStatement(SQLQuery);

            preparedStatement.setLong(1, time_create);
            preparedStatement.setString(2, user_id);
            preparedStatement.setString(3, keyword);

            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }finally {
            preparedStatement.close();
        }

        return result;
    }
}
