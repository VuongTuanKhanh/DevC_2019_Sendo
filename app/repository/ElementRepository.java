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

public class ElementRepository {

    private Connection conn;
    private Converter converter;

    @Inject
    public ElementRepository(GlobalVariables db, Converter converter) {
        this.conn = db.getConnectionDB();
        this.converter = converter;
    }

    public JSONArray select(String element) throws SQLException {

        JSONArray jsonArray = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String searchTerm = element;

            String SQLQuery = "Select * from periodic_elements WHERE LOWER(ELEMENT) LIKE ?";
            preparedStatement = conn.prepareStatement(SQLQuery);

            String queryParam = String.format("%%%s%%", searchTerm.toLowerCase());

            preparedStatement.setString(1, queryParam);
            resultSet = preparedStatement.executeQuery();

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
