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

public class UserProductLogRepository {

    private static final String SUCCESS = "SUCCESS";

    private Connection conn;
    private Converter converter;

    @Inject
    public UserProductLogRepository(GlobalVariables db, Converter converter) {
        this.conn = db.getConnectionDB();
        this.converter = converter;
    }

    public JSONArray getLog(String user_id, int offset, int limit) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        JSONArray jsonArray = null;
        try {
            String SQLQuery = "SELECT DISTINCT product.*, categories_lv1.name as categories_lv1_name, categories_lv2.name as categories_lv2_name, categories_lv3.name as categories_lv3_name, user_product_log.time_create FROM user_product_log " +
                    "INNER JOIN product ON user_product_log.product_id = product.product_id " +
                    "INNER JOIN categories_lv1 ON categories_lv1.id_lv1 = product.belong_cate_level1_id " +
                    "INNER JOIN categories_lv2 ON categories_lv2.id_lv2 = product.belong_cate_level2_id " +
                    "INNER JOIN categories_lv3 ON categories_lv3.id_lv3 = product.belong_cate_level3_id " +
                    "WHERE user_product_log.user_id = ? " +
                    "ORDER BY user_product_log.time_create DESC " +
                    "LIMIT ? OFFSET ?";

            preparedStatement = conn.prepareStatement(SQLQuery);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
            preparedStatement.setString(1, user_id);

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
