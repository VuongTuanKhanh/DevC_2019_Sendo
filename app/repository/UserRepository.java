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

public class UserRepository {

    private static final String SUCCESS = "SUCCESS";

    private Connection conn;
    private Converter converter;

    @Inject
    public UserRepository(GlobalVariables db, Converter converter) {
        this.conn = db.getConnectionDB();
        this.converter = converter;
    }

    public JSONArray getById(Long user_id) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT * FROM user WHERE user.user_id = ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setLong(1, user_id);

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
}
