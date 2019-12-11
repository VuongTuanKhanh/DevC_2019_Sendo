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

public class CategoriesRepository {

    private static final String SUCCESS = "SUCCESS";

    private Connection conn;
    private Converter converter;

    @Inject
    public CategoriesRepository(GlobalVariables db, Converter converter) {
        this.conn = db.getConnectionDB();
        this.converter = converter;
    }

    public JSONArray getCategoriesLv1() throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT * FROM categories_lv1 ";

            preparedStatement = conn.prepareStatement(SQLQuery);

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

    public JSONArray getCategoriesLv2() throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT * FROM categories_lv2 ";

            preparedStatement = conn.prepareStatement(SQLQuery);

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

    public JSONArray getCategoriesLv3() throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT * FROM categories_lv3 ";

            preparedStatement = conn.prepareStatement(SQLQuery);

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
